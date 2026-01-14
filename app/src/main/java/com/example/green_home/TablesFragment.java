package com.example.green_home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class TablesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tables, container, false);

        // --- 1. BACK HEADER LOGIC ---
        // Clicking "← Tables" takes you back to the Home Dashboard
        TextView backHeader = view.findViewById(R.id.tv_category_back_header);
        backHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new HomeFragment())
                        .commit();
            }
        });

        // --- 2. NAVIGATION BAR LOGIC (Switching Categories) ---

        // Chairs Tab
        view.findViewById(R.id.btn_nav_chairs_from_tables).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateTo(new ChairsFragment());
            }
        });

        // Sofas Tab
        view.findViewById(R.id.btn_nav_sofas_from_tables).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateTo(new SofasFragment());
            }
        });

        // Beds Tab
        view.findViewById(R.id.btn_nav_beds_from_tables).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateTo(new BedsFragment());
            }
        });

        // --- 3. PRODUCT CLICK (Prototype Path) ---
        Button btnViewTable = view.findViewById(R.id.btn_view_table_1);
        btnViewTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDetails("Oak Dining Table", "€450", R.drawable.tables);
            }
        });

        return view;
    }

    // Helper method to handle navigation to other categories
    private void navigateTo(Fragment fragment) {
        getParentFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
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