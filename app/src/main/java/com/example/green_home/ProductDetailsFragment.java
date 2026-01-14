package com.example.green_home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;

public class ProductDetailsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        // 1. Inflate the layout

        View view = inflater.inflate(R.layout.fragment_product_details, container, false);

        // 2. Handle the "Back" button in the green bar
        TextView tvBack = view.findViewById(R.id.tv_back_to_list);
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Returns to the Chairs list
                if (getParentFragmentManager().getBackStackEntryCount() > 0) {
                    getParentFragmentManager().popBackStack();
                }
            }
        });

        if (getArguments() != null) {
            String name = getArguments().getString("product_name");
            String price = getArguments().getString("product_price");
            int imageId = getArguments().getInt("product_image");

            // Connect to your XML views
            TextView tvName = view.findViewById(R.id.tv_detail_name);
            TextView tvPrice = view.findViewById(R.id.tv_detail_price);
            ImageView ivImage = view.findViewById(R.id.iv_product_large);

            // Fill the views with the "packed" data
            tvName.setText(name);
            tvPrice.setText("Price " + price);
            ivImage.setImageResource(imageId);
        }


        // 1. Add to Cart Button -> Navigates to Shopping Cart
        Button btnCart = view.findViewById(R.id.btn_add_to_cart);
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // This replaces Product Details with the Shopping Cart
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new ShoppingCartFragment())
                        .addToBackStack(null) // Allows user to go back to the product
                        .commit();
            }
        });

// 2. Preview in AR Button -> Navigates to AR Mode
        Button btnAR = view.findViewById(R.id.btn_preview_ar);
        btnAR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // This replaces Product Details with the AR Mode page
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new ARModeFragment())
                        .addToBackStack(null) // Allows user to go back to the product
                        .commit();
            }
        });


        TextView backBtn = view.findViewById(R.id.tv_back_to_list);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // This "pops" the current detail page off the stack
                // and reveals whatever category was underneath it.
                getParentFragmentManager().popBackStack();
            }
        });
        return view;
    }
}
