package com.example.green_home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.fragment.app.Fragment;

public class LoginFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        // 1. Find the inputs and button
        EditText etEmail = view.findViewById(R.id.et_email_login);
        EditText etPassword = view.findViewById(R.id.et_password_login);
        Button btnSignIn = view.findViewById(R.id.btn_signin_submit);

        // 2. Set the Listener
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputEmail = etEmail.getText().toString().trim();
                String inputPassword = etPassword.getText().toString().trim();

                // 1. Check if fields are empty first
                if (inputEmail.isEmpty() || inputPassword.isEmpty()) {
                    Toast.makeText(getActivity(), "Please enter all details", Toast.LENGTH_SHORT).show();
                    return; // Stop here if empty
                }

                // 2. Open the "Sticky Note" (SharedPreferences)
                SharedPreferences pref = getActivity().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
                String savedEmail = pref.getString("registered_email", "");
                String savedPassword = pref.getString("registered_password", "");

                // 3. Compare Input vs Saved Data OR Master Admin
                boolean isSavedUser = inputEmail.equals(savedEmail) && inputPassword.equals(savedPassword);
                boolean isAdmin = inputEmail.equals("admin@greenhome.com") && inputPassword.equals("1234");

                if (isSavedUser || isAdmin) {
                    getParentFragmentManager().beginTransaction()
                            .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                            .replace(R.id.fragment_container, new ARIntroFragment())
                            .commit();
                } else {
                    // Fail!
                    Toast.makeText(getActivity(), "Invalid Email or Password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}