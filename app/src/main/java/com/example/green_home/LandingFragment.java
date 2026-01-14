package com.example.green_home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class LandingFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_landing, container, false);

        // 1. Find the Log In button
        Button btnLogin = view.findViewById(R.id.btn_login_main);

        // 2. Set the Listener for Login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction()
                        .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                        .replace(R.id.fragment_container, new LoginFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        // 3. Find the Sign Up button
        Button btnSignUp = view.findViewById(R.id.btn_signup_main);

        // 4. Set the Listener for Registration
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction()
                        .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                        .replace(R.id.fragment_container, new RegistrationFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        // IMPORTANT: You must return the view here!
        return view;
    }
}

