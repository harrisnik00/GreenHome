package com.example.green_home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;

public class ARIntroFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 1. Inflate the layout (make sure the XML name matches your fixed filename)
        View view = inflater.inflate(R.layout.fragment_ar_intro, container, false);

        // 2. Find the "Next" button
        // Replace 'btn_next_to_home' with the actual ID you used in your XML
        Button btnNext = view.findViewById(R.id.btn_ar_next);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 3. Navigate to the Home Dashboard
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new HomeFragment() )
                        .commit();

                // Note: We don't use .addToBackStack(null) here because
                // we usually don't want the user to go "back" into the intro screen.
            }
        });

        return view;
    }
}
