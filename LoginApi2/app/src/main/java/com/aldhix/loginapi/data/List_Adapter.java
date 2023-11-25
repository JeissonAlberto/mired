package com.aldhix.loginapi.data;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.aldhix.loginapi.LocalStorage;
import com.aldhix.loginapi.R;
import com.aldhix.loginapi.updateWifiPassword;

import java.util.ArrayList;
import java.util.List;

public class List_Adapter extends ArrayAdapter<Service> {

    public List_Adapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public List_Adapter(@NonNull Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public List_Adapter(@NonNull Context context, int resource, @NonNull Service[] objects) {
        super(context, resource, objects);
    }

    public List_Adapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull Service[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public List_Adapter(@NonNull Context context, int resource, @NonNull List<Service> objects) {
        super(context, resource, objects);
    }

    public List_Adapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<Service> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public List_Adapter(@NonNull Context context, ArrayList<Service> serviceArrayList, LocalStorage localStorage) {
        super(context, R.layout.list_item, serviceArrayList);
    }
    private LocalStorage localStorage;

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Service service = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        TextView tvVelocidad = convertView.findViewById(R.id.tvVelocidad);
        TextView tvPrecio = convertView.findViewById(R.id.tvPrecio);
        TextView tvTecnologia = convertView.findViewById(R.id.tvTecnologia);
        TextView tvSSID = convertView.findViewById(R.id.tvSSID);
        Button btnUpdate = convertView.findViewById(R.id.btnUpdate);

        tvVelocidad.setText("Velocidad: " + service.getVelocidad());
        tvPrecio.setText("Precio: " + service.getPrecio());
        tvTecnologia.setText("Tecnología: " + service.getTipo_de_tecnologia());
        tvSSID.setText("SSID: " + service.getSsid());

        btnUpdate.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    localStorage = new LocalStorage(getContext());
                    localStorage.setServiceId(service.getId());
                    localStorage.setSSID(service.getSsid());
                    FragmentManager fragmentManager = ((AppCompatActivity) getContext()).getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frame, new updateWifiPassword());
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commitAllowingStateLoss();
                }
            }
        );



        return convertView;
    }

}
