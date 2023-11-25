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
 * Use the {@link updateUser#newInstance} factory method to
 * create an instance of this fragment.
 */
public class updateUser extends Fragment {

    EditText etName, etEmail, etPassword, etCedula,etTelefono, etDireccion, etConfirmation;
    Button btnRegister, btnCancel;
    String name, email, password, cedula,direccion,telefono,confirmation;
    private LocalStorage localStorage;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public updateUser() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment updateUser.
     */
    // TODO: Rename and change types and number of parameters
    public static updateUser newInstance(String param1, String param2) {
        updateUser fragment = new updateUser();
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
        return inflater.inflate(R.layout.fragment_update_user, container, false);
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        localStorage = new LocalStorage(requireContext());
        etName = requireView().findViewById(R.id.etName);
        etCedula = requireView().findViewById(R.id.etCedula);
        etEmail = requireView().findViewById(R.id.etEmail);
        etTelefono = requireView().findViewById(R.id.etTelefono);
        etDireccion = requireView().findViewById(R.id.etDireccion);

        etPassword = requireView().findViewById(R.id.etPassword);
        etConfirmation = requireView().findViewById(R.id.etConfirmation);
        btnRegister = requireView().findViewById(R.id.btnRegister);
        btnCancel = requireView().findViewById(R.id.btnCancel);

        etEmail.setText(localStorage.getEmail());
        etName.setText(localStorage.getName());
        etCedula.setText(localStorage.getCedula());
        etTelefono.setText(localStorage.getTelefono());
        etDireccion.setText(localStorage.getDireccion());

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkRegister();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelar();
            }
        });
    }
    private void checkRegister() {
        name = etName.getText().toString();
        cedula = etCedula.getText().toString();
        email = etEmail.getText().toString();
        telefono = etTelefono.getText().toString();
        direccion = etDireccion.getText().toString();
        password = etPassword.getText().toString();
        confirmation = etConfirmation.getText().toString();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || cedula.isEmpty() || telefono.isEmpty() || direccion.isEmpty() ) {

            alertFail("Datos requeridos ");
        } else if (!password.equals(confirmation)) {
            alertFail("Contraceña no coincide.");
        } else {
            sendRegister();
        }
    }
    private void cancelar() {
        replaceFragment(new profile());
    }
    private void sendRegister() {
        JSONObject params = new JSONObject();
        try {
            params.put("name", name);
            params.put("email", email);
            params.put("direccion", direccion);
            params.put("telefono", telefono);
            params.put("cedula", cedula);
            params.put("password", password);
            params.put("password_confirmation", confirmation);

        }catch (JSONException e){
            e.printStackTrace();
        }
        String data = params.toString();
        String url = getString(R.string.api_server)+"/userUpdate/"+localStorage.getUserId();

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
                            alertSuccess("Actualizado correctamente.");
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
            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int which) {
                    onBackPressed();

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

    public void onBackPressed() {
        replaceFragment(new profile());
    }
    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss();
    }
}
