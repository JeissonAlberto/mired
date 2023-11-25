package com.aldhix.loginapi;

import android.annotation.SuppressLint;
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link profile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class profile extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    TextView tvName, tvEmail,tvTelefono, tvDireccion, tvCedula ;
    Button btnLogout, btnUpdate;

    private LocalStorage localStorage;
    public profile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment profile.
     */
    // TODO: Rename and change types and number of parameters
    public static profile newInstance(String param1, String param2) {
        profile fragment = new profile();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        localStorage = new LocalStorage(requireContext());

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        tvName = requireView().findViewById(R.id.tvName);
        tvEmail = requireView().findViewById(R.id.tvEmail);
        tvTelefono = requireView().findViewById(R.id.tvTelefono);
        tvCedula = requireView().findViewById(R.id.tvCedula);
        tvDireccion = requireView().findViewById(R.id.tvDireccion);
        btnLogout = requireView().findViewById(R.id.btnLogout);
        btnUpdate = requireView().findViewById(R.id.btnUpdate);




        getUser();



        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizar();
            }
        });




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    private void getUser() {
        String url = getString(R.string.api_server)+"/user/"+localStorage.getEmail();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Http http = new Http(requireContext(), url);
                Log.e("URL USER", url);
                http.setToken(true);
                http.setMethod("GET");
                http.send();

                requireActivity().runOnUiThread(new Runnable() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void run() {
                        Integer code = http.getStatusCode();
                        Log.e("err code user", code.toString());
                        if (code ==200){
                            try {
                                JSONObject response = new JSONObject(http.getResponse());
                                String name = response.getString("name");
                                localStorage.setName(name);
                                String email = response.getString("email");
                                String cedula = response.getString("cedula");
                                localStorage.setCedula(cedula);
                                String direccion = response.getString("direccion");
                                localStorage.setDireccion(direccion);
                                String telefono = response.getString("telefono");
                                localStorage.setTelefono(telefono);
                                tvName.setText("Nombre: " + name);
                                tvEmail.setText("Email: " + email);
                                tvCedula.setText("Cédula: " + cedula);
                                tvDireccion.setText("Dirección: " + direccion);
                                tvTelefono.setText("Teléfono: " + telefono);
                                localStorage.setUserId(response.getString("id"));
                            }catch (JSONException e){
                                e.printStackTrace();
                            }
                        }
                        else {
                            Toast.makeText(requireContext(), "Error"+code, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }).start();
    }

    private void logout() {
        Intent intent = new Intent(requireContext(), LoginActivity.class);
        startActivity(intent);
        requireActivity().finish();

    }

    private void actualizar() {
        replaceFragment(new updateUser());
    }



    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss();
    }

    private void servicios() {
        Log.e("token", "termine");
        Intent intent = new Intent(requireContext(), ServicesActivityModem.class);
        startActivity(intent);
        requireActivity().finish();
    }
}
