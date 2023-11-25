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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.aldhix.loginapi.data.List_Adapter;
import com.aldhix.loginapi.data.Service;
import com.aldhix.loginapi.databinding.FragmentServicesBinding;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link services#newInstance} factory method to
 * create an instance of this fragment.
 */
public class services extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private LocalStorage localStorage;
    TextView notFound, tvPrecio, tvTecnologia, tvSSID;

    ImageView planes;
    Button btnUpdate;
    ListView ser;
    FragmentServicesBinding binding;
    public services() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment services.
     */
    // TODO: Rename and change types and number of parameters
    public static services newInstance(String param1, String param2) {
        services fragment = new services();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_services, container, false);
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        planes = requireView().findViewById(R.id.planes);
        notFound = requireView().findViewById(R.id.notFound);
//        tvPrecio = requireView().findViewById(R.id.tvPrecio);
//        tvTecnologia = requireView().findViewById(R.id.tvTecnologia);
//        tvSSID = requireView().findViewById(R.id.tvSSID);
//        btnUpdate = requireView().findViewById(R.id.btnUpdate);



        getUser();





//        btnUpdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                actualizar();
//            }
//        });

    }


//    public void onBackPressed() {
//        Intent intent = new Intent(requireContext(), UserActivity.class);
//        startActivity(intent);
//        requireActivity().finish();
//    }
    private void actualizar() {
        replaceFragment(new updateWifiPassword());
    }

    private void getUser() {
        String url = getString(R.string.api_server)+"/servicesByUserEmail/"+localStorage.getEmail();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Http http = new Http(requireContext(), url);
                Log.e("URL USER", url);
                http.setToken(true);
                http.setMethod("GET");
                http.send();

                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Integer code = http.getStatusCode();
                        Log.e("err code user", code.toString());
                        if (code ==200){
                            try {
                                JSONArray response = new JSONArray(http.getResponse());
                                Gson gson = new Gson();

                                Service []services = gson.fromJson(String.valueOf(response), Service[].class);
                                ArrayList<Service> serviceArrayList = new ArrayList<>(Arrays.asList(services));
                                if (serviceArrayList.isEmpty()){
                                    notFound.setText("Usted no tiene servicios relacionados, comuniquese con la empresa para registrar sus servicios contratados.");
                                    planes.setVisibility(View.VISIBLE);
                                    return;
                                } else {
                                    Fragment fragment = requireActivity().getSupportFragmentManager().findFragmentById(R.id.frame);
                                    if (fragment == null || fragment.isDetached()) {
                                        return;
                                    }else {
                                        if (getView() == null){
                                            return;
                                        } else {
                                            List_Adapter listAdapter = new List_Adapter(requireContext(), serviceArrayList, localStorage);
                                            ser = requireView().findViewById(R.id.listServices);
                                            ser.setAdapter(listAdapter);
                                        }
                                    }
                                }
                                //binding = FragmentServicesBinding.inflate(getLayoutInflater());
                                //binding.listServices.setAdapter(listAdapter);
//                                String velocidad = services[0].getVelocidad();
//                                String precio = services[0].getPrecio();
//                                String tecnologia = services[0].getTipo_de_tecnologia();
//                                String ssid = services[0].getSsid();
//                                localStorage.setSSID(ssid);
//                                localStorage.setServiceId(services[0].getId());
//                                tvTecnologia.setText(tecnologia);
//                                tvPrecio.setText(precio);
//                                tvSSID.setText(ssid);
//                                tvVelocidad.setText(velocidad);
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
    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss();
    }
}
