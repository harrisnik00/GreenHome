package com.example.green_home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class ChairsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chairs, container, false);

// Tables Button
        view.findViewById(R.id.btn_nav_tables_from_chairs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new TablesFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

// Beds Button
        view.findViewById(R.id.btn_nav_beds_from_chairs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new BedsFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
            //Sofas
            view.findViewById(R.id.btn_nav_beds_from_chairs).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getParentFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new BedsFragment())
                            .addToBackStack(null)
                            .commit();
                                                                                    }
        });


        //Back Button

        TextView backHeader = view.findViewById(R.id.tv_category_back_header);
        backHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // This takes you back to the Home Dashboard
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new HomeFragment())
                        .commit();
            }
        });


        // Chair 1 Button
        view.findViewById(R.id.btn_view_chair_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDetails("Desk Armchair", "€200", R.drawable.chairs);
            }
        });

        // Chair 2 Button
        view.findViewById(R.id.btn_view_chair_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDetails("Dining Armchair", "€100", R.drawable.dchair);
            }
        });

        // Chair 3 Button
        view.findViewById(R.id.btn_view_chair_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDetails("Lounge Armchair", "€200", R.drawable.lounge);
            }
        });

        // Chair 4 Button
        view.findViewById(R.id.btn_view_chair_4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDetails("Rustic Dining Chair", "€80", R.drawable.dining);
            }
        });

        return view;
    }

    // This helper method packs the "Suitcase" (Bundle) and switches screens
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
