package com.example.green_home;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.SharedPreferences;


public class RegistrationFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_registration, container, false);

        // 1. Find the inputs and button
        EditText etName = view.findViewById(R.id.et_fullname_reg);
        EditText etEmail = view.findViewById(R.id.et_email_reg);
        EditText etPass = view.findViewById(R.id.et_password_reg);
        EditText etConfirm = view.findViewById(R.id.et_confirm_reg);
        Button btnSignUp = view.findViewById(R.id.btn_signup_submit);

        // 2. Set the Listener
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String password = etPass.getText().toString();
                String confirm = etConfirm.getText().toString();

                // Basic check: do passwords match?
                if (password.equals(confirm) && !password.isEmpty()) {

                    // --- NEW CODE START: SAVE USER LOCALLY ---
                    // This creates the "Sticky Note" file called UserPrefs
                    SharedPreferences pref = getActivity().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();

                    // Save the email and password entered by the user
                    editor.putString("registered_email", email);
                    editor.putString("registered_password", password);
                    editor.apply(); // Commit the changes

                    Toast.makeText(getActivity(), "Account Created Locally!", Toast.LENGTH_SHORT).show();
                    // --- NEW CODE END ---

                    // Success! Move to the AR Intro screen
                    getParentFragmentManager().beginTransaction()
                            .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                            .replace(R.id.fragment_container, new ARIntroFragment())
                            .commit();
                } else {
                    Toast.makeText(getActivity(), "Passwords do not match!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}
