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
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;
import java.util.ArrayList;
import java.util.List;

public class ARActivity extends AppCompatActivity {

    private Product selectedProduct = null;
    private ModelRenderable selectedRenderable = null;

    private ArFragment arFragment;
    private TextView txtSelected;
    private Button btnCatalog, btnAddToCart;
    private final List<Product> catalog = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar); // We will create this XML next

        // Initialize UI
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

        catalog.add(new Product("Modern Chair", 125.00, "my_chair.glb"));
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

        // This tells Sceneform to treat the file as a raw GLB
        ModelRenderable.builder()
                .setSource(this, com.google.ar.sceneform.assets.RenderableSource.builder().setSource(this,
                                Uri.parse(selectedProduct.glbAssetName),
                                com.google.ar.sceneform.assets.RenderableSource.SourceType.GLB)
                        .setScale(1.0f)  // Set a default scale so it's not giant
                        .build())
                .setRegistryId(selectedProduct.glbAssetName)
                .build()
                .thenAccept(renderable -> {
                    selectedRenderable = renderable;
                    runOnUiThread(() -> {
                        Toast.makeText(this, "Ready to place: " + selectedProduct.name, Toast.LENGTH_SHORT).show();
                    });
                })
                .exceptionally(throwable -> {
                    runOnUiThread(() -> {
                        new AlertDialog.Builder(this)
                                .setTitle("Loading Error")
                                .setMessage(throwable.getMessage())
                                .show();
                    });
                    return null;
                });
    }

    private void placeModel(Anchor anchor, ModelRenderable renderable) {
        AnchorNode anchorNode = new AnchorNode(anchor);
        anchorNode.setParent(arFragment.getArSceneView().getScene());

        TransformableNode node = new TransformableNode(arFragment.getTransformationSystem());

        node.setParent(anchorNode);
        node.setRenderable(renderable);

        node.getRotationController().setEnabled(true);
        node.getScaleController().setEnabled(true);
        node.getTranslationController().setEnabled(true);

        node.setLocalScale(new Vector3(1.0f, 1.0f, 1.0f));
        node.select(); // Allows user to move/rotate the furniture [cite: 20]
    }
}
