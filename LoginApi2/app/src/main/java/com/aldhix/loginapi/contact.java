package com.aldhix.loginapi;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewFragment;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link contact#newInstance} factory method to
 * create an instance of this fragment.
 */
public class contact extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ImageButton btnLlamar, btnEnviarCorreo, btnTest, btnPagina;
    public contact() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment contact.
     */
    // TODO: Rename and change types and number of parameters
    public static contact newInstance(String param1, String param2) {
        contact fragment = new contact();
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
        return inflater.inflate(R.layout.fragment_contact, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        btnLlamar = requireView().findViewById(R.id.btnLlamar);
        btnEnviarCorreo = requireView().findViewById(R.id.btnEnviarCorreo);
        btnTest = requireView().findViewById(R.id.btnTest);
        btnPagina = requireView().findViewById(R.id.btnPagina);


        btnLlamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realizarLlamada();
            }
        });

        btnEnviarCorreo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarCorreo();
            }
        });

        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testVelocidad();
            }
        });
        btnPagina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paginaPrincipal();
            }
        });
    }
    private void testVelocidad() {
        String url = "https://fiber.google.com/intl/es/speedtest/"; // Reemplaza con la URL de la página web que desees mostrar
        webView webViewFragment = new webView();
        new webView();
        Bundle args = new Bundle();
        args.putString("url", url);
        webViewFragment.setArguments(args);

        // Reemplaza R.id.contenedor_fragment por el ID del contenedor donde deseas mostrar el WebViewFragment
        requireActivity().getSupportFragmentManager().beginTransaction()
            .replace(R.id.frame, webViewFragment)
            .commit();
    }
    private void paginaPrincipal() {
        String url = "https://miredcomunicaciones.com"; // Reemplaza con la URL de la página web que desees mostrar
        webView webViewFragment = new webView();
        new webView();
        Bundle args = new Bundle();
        args.putString("url", url);
        webViewFragment.setArguments(args);

        // Reemplaza R.id.contenedor_fragment por el ID del contenedor donde deseas mostrar el WebViewFragment
        requireActivity().getSupportFragmentManager().beginTransaction()
            .replace(R.id.frame, webViewFragment)
            .commit();
    }
    private void enviarCorreo() {
        String[] destinatarios = {"miredcomunicaciones@gmail.com"}; // Reemplaza con el correo electrónico que desees
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, destinatarios);

        try {
            startActivity(Intent.createChooser(intent, "Enviar correo"));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(requireContext(), "No hay aplicaciones de correo instaladas.", Toast.LENGTH_SHORT).show();
        }
    }
        private void realizarLlamada() {
            Intent intent = new Intent(Intent.ACTION_CALL);
            String phoneNumber = "3148138808"; // Reemplaza con el número de teléfono que desees llamar
            intent.setData(Uri.parse("tel:" + phoneNumber));
            if (ActivityCompat.checkSelfPermission(requireContext(), android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                startActivity(intent);
            } else {
                // Solicitar permiso para realizar llamadas en dispositivos con Android 6.0 o superior
                requestPermissions(new String[]{android.Manifest.permission.CALL_PHONE}, 1);
            }
        }

        // Método para manejar la respuesta del permiso de llamada telefónica
        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            if (requestCode == 1) {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    realizarLlamada();
                } else {
                    Toast.makeText(requireContext(), "Permiso de llamada denegado.", Toast.LENGTH_SHORT).show();
                }
            }
        }

}
