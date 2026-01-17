package com.example.green_home;


import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.google.ar.core.Anchor;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;
import java.util.ArrayList;
import java.util.List;

public class ARActivity extends AppCompatActivity {

    private ArFragment arFragment;
    private TextView txtSelected;
    private Button btnCatalog, btnAddToCart;
    private final List<Product> catalog = new ArrayList<>();
    private Product selectedProduct = null;
    private ModelRenderable selectedRenderable = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar); // We will create this XML next

        // Initialize UI [cite: 6]
        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arFragment);
        txtSelected = findViewById(R.id.txtSelected);
        btnCatalog = findViewById(R.id.btnCatalog);
        btnAddToCart = findViewById(R.id.btnAddToCart);

        seedCatalog();

        btnCatalog.setOnClickListener(v -> showCatalogDialog());
        btnAddToCart.setOnClickListener(v -> Toast.makeText(this, "Added to cart!", Toast.LENGTH_SHORT).show());

        // Listen for taps on the floor to place furniture [cite: 7, 8]
        arFragment.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {
            if (selectedProduct == null || selectedRenderable == null) {
                Toast.makeText(this, "Select an item from Catalog first", Toast.LENGTH_SHORT).show();
                return;
            }
            Anchor anchor = hitResult.createAnchor();
            placeModel(anchor, selectedRenderable);
        });
    }

    private void seedCatalog() {
        // Matches your previous project items
        catalog.add(new Product("Velvet Sofa", 1200.00, "velvet_sofa.glb"));
        catalog.add(new Product("Oak Dining Table", 450.00, "oak_table.glb"));
        catalog.add(new Product("Desk Armchair", 200.00, "desk_chair.glb"));
    }

    private void showCatalogDialog() {
        String[] names = new String[catalog.size()];
        for (int i = 0; i < catalog.size(); i++) {
            names[i] = catalog.get(i).name + " - €" + catalog.get(i).price;
        }

        new AlertDialog.Builder(this)
                .setTitle("Select Furniture")
                .setItems(names, (dialog, which) -> {
                    selectedProduct = catalog.get(which);
                    txtSelected.setText("Selected: " + selectedProduct.name);
                    loadSelectedModel();
                })
                .show();
    }

    private void loadSelectedModel() {
        selectedRenderable = null;
        Uri modelUri = Uri.parse("file:///android_asset/" + selectedProduct.glbAssetName);

        ModelRenderable.builder()
                .setSource(this, modelUri)
                .setIsFilamentGltf(true)
                .build()
                .thenAccept(renderable -> {
                    selectedRenderable = renderable;
                    Toast.makeText(this, "Ready to place: " + selectedProduct.name, Toast.LENGTH_SHORT).show();
                })
                .exceptionally(throwable -> {
                    Toast.makeText(this, "Error loading model", Toast.LENGTH_SHORT).show();
                    return null;
                });
    }

    private void placeModel(Anchor anchor, ModelRenderable renderable) {
        AnchorNode anchorNode = new AnchorNode(anchor);
        anchorNode.setParent(arFragment.getArSceneView().getScene());

        TransformableNode node = new TransformableNode(arFragment.getTransformationSystem());
        node.setParent(anchorNode);
        node.setRenderable(renderable);
        node.select(); // Allows user to move/rotate the furniture [cite: 20]
    }
}
