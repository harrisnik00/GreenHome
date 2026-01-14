package com.example.green_home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class BedsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 1. Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_beds, container, false);

        // --- NAVIGATION BAR LOGIC ---

        // Back Arrow (← Beds)
        TextView tvBack = view.findViewById(R.id.tv_beds_header);
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().popBackStack();
            }
        });

        // --- PRODUCT BUTTON LOGIC (Dynamic) ---

        // Bed 1
        Button btnBed1 = view.findViewById(R.id.btn_view_bed_1);
        btnBed1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDetails("King Eco Bed", "€800", R.drawable.beds);
            }
        });

        // Bed 2
        Button btnBed2 = view.findViewById(R.id.btn_view_bed_2);
        btnBed2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDetails("Single Frame", "€350", R.drawable.beds);
            }
        });


        return view;
    }

    /**
     * This helper method handles the "suitcasing" of data.
     * It sends the name, price, and image to the ProductDetailsFragment.
     */
    private void openDetails(String name, String price, int imageResId) {
        // Create the destination fragment
        ProductDetailsFragment detailsFragment = new ProductDetailsFragment();

        // Pack the data into a Bundle
        Bundle args = new Bundle();
        args.putString("product_name", name);
        args.putString("product_price", price);
        args.putInt("product_image", imageResId);

        // Give the bundle to the fragment
        detailsFragment.setArguments(args);

        // Perform the fragment switch
        getParentFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, detailsFragment)
                .addToBackStack(null) // This lets the back button work!
                .commit();
    }
}
