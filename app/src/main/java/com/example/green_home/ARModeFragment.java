package com.example.green_home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ARModeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 1. Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ar_mode, container, false);

        // 2. Initialize the Back Button (Figma: ← AR Mode)
        TextView backBtn = view.findViewById(R.id.tv_ar_back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // This takes the user back to the Product Details page
                if (getParentFragmentManager().getBackStackEntryCount() > 0) {
                    getParentFragmentManager().popBackStack();
                }
            }
        });

        // 3. Initialize the Scan Button
        Button btnScan = view.findViewById(R.id.btn_scan_room);
        btnScan.setOnClickListener(v -> {
            // Use getActivity() because we are inside a Fragment
            Intent intent = new Intent(getActivity(), ARActivity.class);
            startActivity(intent);
        });

        return view;
    }
}