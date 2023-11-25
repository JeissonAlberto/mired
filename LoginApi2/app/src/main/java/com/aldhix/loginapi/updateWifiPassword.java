package com.aldhix.loginapi;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link updateWifiPassword#newInstance} factory method to
 * create an instance of this fragment.
 */
public class updateWifiPassword extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    EditText etPassword, etSSID;
    Button btnUpdate, btnCancel;

    String ssid, password;
    private LocalStorage localStorage;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public updateWifiPassword() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment updateWifiPassword.
     */
    // TODO: Rename and change types and number of parameters
    public static updateWifiPassword newInstance(String param1, String param2) {
        updateWifiPassword fragment = new updateWifiPassword();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update_wifi_password, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        localStorage = new LocalStorage(requireContext());
        etPassword = requireView().findViewById(R.id.etPassword);
        etSSID = requireView().findViewById(R.id.etSSID);
        btnUpdate = requireView().findViewById(R.id.btnUpdate);
        btnCancel = requireView().findViewById(R.id.btnCancel);
        getSSID();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendRegister();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelar();
            }
        });

    }
    private void cancelar() {
        replaceFragment(new services());
    }
    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss();
    }
//    public void onBackPressed() {
//        Intent intent = new Intent(requireContext(), ServicesActivityModem.class);
//        startActivity(intent);
//        requireActivity().finish();
//    }
    private void getSSID() {
        etSSID.setText(localStorage.getSSID());
    }
    private void sendRegister() {
        ssid = etSSID.getText().toString();
        password = etPassword.getText().toString();
        JSONObject params = new JSONObject();
        try {
            params.put("ssid", ssid);
            params.put("password", password);
        }catch (JSONException e){
            e.printStackTrace();
        }
        String data = params.toString();
        String url = getString(R.string.api_server)+"/updatePassword/"+localStorage.getServiceId();
        Log.e("url", url);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Http http = new Http(requireContext(), url);
                http.setMethod("PUT");
                http.setData(data);
                http.setToken(true);
                http.send();

                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Integer code = http.getStatusCode();
                        if (code ==201 || code ==200){
                            alertSuccess("Contraseña actualizada.");
                        }
                        else if (code == 422){
                            try {
                                JSONObject response = new JSONObject(http.getResponse());
                                String msg = response.getString("message");
                                alertFail(msg);

                            }catch (JSONException e){
                                e.printStackTrace();

                            }
                        }
                        else {
                            alertFail("Estamos teniendo inconvenientes en estos momentos");
                            Toast.makeText(requireContext(), "Error",+ Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        }).start();

    }

    private void alertSuccess(String s) {
        new AlertDialog.Builder(requireContext())
            .setTitle("Success")
            .setIcon(R.drawable.ic_check)
            .setMessage(s)
            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int which) {
                    Intent intent = new Intent(requireContext(), ServicesActivityModem.class);
                    startActivity(intent);
                    requireActivity().finish();

                }
            })
            .show();
    }

    private void alertFail(String s) {
        new AlertDialog.Builder(requireContext())
            .setTitle("Failed")
            .setIcon(R.drawable.ic_warning)
            .setMessage(s)
            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();

                }
            }).show();

    }
}
