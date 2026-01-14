package com.example.green_home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 1. Link this Java file to your existing XML
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        //cart
        ImageView cartIcon = view.findViewById(R.id.iv_cart_icon);
        cartIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new ShoppingCartFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
        // 2. Setup Chairs Card Click
        CardView cardChairs = view.findViewById(R.id.card_chairs);
        cardChairs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateTo(new ChairsFragment());
            }
        });

        // 3. Setup Beds Card Click
        CardView cardBeds = view.findViewById(R.id.card_beds);
        cardBeds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateTo(new BedsFragment());
            }
        });

        // 4. Setup Tables Card Click
        CardView cardTables = view.findViewById(R.id.card_tables);
        cardTables.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateTo(new TablesFragment());
            }
        });


        // Setup Sofas Card Click
        CardView cardSofas = view.findViewById(R.id.card_sofas);
        cardSofas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // This opens the new SofasFragment we just built
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new SofasFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }

    // Helper method to keep the code clean
    private void navigateTo(Fragment fragment) {
        getParentFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null) // This lets the user press 'Back' to return home
                .commit();
    }
}
