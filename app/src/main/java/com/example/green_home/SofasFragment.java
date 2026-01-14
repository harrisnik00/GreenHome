package com.example.green_home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class SofasFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sofas, container, false);

        // --- NAV BAR LOGIC ---

        // Go to Chairs
        view.findViewById(R.id.btn_nav_chairs_from_sofas).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new ChairsFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        // Go to Tables
        view.findViewById(R.id.btn_nav_tables_from_sofas).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new TablesFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
        // Go to Beds
        view.findViewById(R.id.btn_nav_tables_from_sofas).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new BedsFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        // Back Arrow

        TextView backHeader = view.findViewById(R.id.tv_category_back_header);
        backHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new HomeFragment())
                        .commit();
            }
        });



// Sofa 1 (Active)
        view.findViewById(R.id.btn_view_sofa_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDetails("Velvet Sofa", "€1200", R.drawable.sofas);
            }
        });

// Sofa 2 (Active)
        view.findViewById(R.id.btn_view_sofa_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDetails("Modern Sofa", "€950", R.drawable.sofas);
            }
        });

// You can leave Sofa 3 and 4 without listeners so they stay as non-clickable visual elements for the prototype.

        // --- PRODUCT DETAILS LOGIC ---

        view.findViewById(R.id.btn_view_sofa_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDetails("Velvet Sofa", "€1200", R.drawable.sofas);
            }
        });

        return view;
    }

    private void openDetails(String name, String price, int imageResId) {
        ProductDetailsFragment detailsFragment = new ProductDetailsFragment();
        Bundle args = new Bundle();
        args.putString("product_name", name);
        args.putString("product_price", price);
        args.putInt("product_image", imageResId);
        detailsFragment.setArguments(args);

        getParentFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, detailsFragment)
                .addToBackStack(null)
                .commit();
    }
}
