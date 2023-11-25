package com.aldhix.loginapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.aldhix.loginapi.databinding.ActivityUserBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarMenuView;

import org.json.JSONException;
import org.json.JSONObject;

public class UserActivity extends AppCompatActivity {
    TextView tvName, tvEmail,tvTelefono, tvDireccion, tvCedula ;
    Button btnLogout, btnUpdate, btnServicios;

    private LocalStorage localStorage;
    ActivityUserBinding binding;


    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        localStorage = new LocalStorage(UserActivity.this);

        BottomNavigationView nav = findViewById(R.id.bottomNavigation);
        Log.e("nav", String.valueOf(nav.getSelectedItemId()));
        nav.setOnItemSelectedListener(item -> {
            Log.e("bottomNavigation", "1");
            switch (nav.getSelectedItemId()) {
                case R.id.item_1:
                    Log.e("estoy en la dos", "1");
                    replaceFragment(new profile());
                    break;

                case R.id.item_2:
                    Log.e("estoy en la dos", "2");
                    replaceFragment(new services());
                    // Respond to navigation item 2 click
                    break;
                case R.id.item_3:
                    Log.e("estoy en la dos", "3");
                    break;
            }
            return true;
        });
        replaceFragment(new profile());
        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            Log.e("bottomNavigation", String.valueOf(item.getItemId()));
            Log.e("bottomNavigation", String.valueOf(R.id.item_1));
            Log.e("bottomNavigation", String.valueOf(nav.getSelectedItemId()));
            switch (item.getItemId()) {
                case R.id.item_1:
                    Log.e("estoy en la dos", "1");
                    replaceFragment(new profile());
                    break;

                case R.id.item_2:
                    Log.e("estoy en la dos", "2");
                    replaceFragment(new services());
                    // Respond to navigation item 2 click
                    break;
                case R.id.item_3:
                    Log.e("estoy en la dos", "3");
                    replaceFragment(new contact());
                    break;
            }

            return true;
        });





    }
    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss();
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(UserActivity.this, LoginActivity.class);

        startActivity(intent);
        finish();
    }
}
