package com.example.green_home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class ShoppingCartFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping_cart, container, false);

        // Back Button
        TextView backBtn = view.findViewById(R.id.tv_cart_back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().popBackStack();
            }
        });

        // Inside ShoppingCartFragment.java
        Button checkoutBtn = view.findViewById(R.id.btn_proceed_checkout);
        checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show a final "Success" message for your demo
                Toast.makeText(getActivity(), "Order Placed! Thank you for choosing GreenHome.", Toast.LENGTH_LONG).show();

                // Optional: Take the user back to the Home screen after checkout
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new HomeFragment())
                        .commit();
            }
        });
        return view;
    }
}