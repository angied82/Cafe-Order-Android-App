package com.example.project5;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * All the Actions in the Donut Window
 * @author Lucas Banderia
 */
public class DonutActivity extends AppCompatActivity implements DonutAdapter.OnDonutClickListener {

    private RecyclerView recyclerDonuts;
    private Spinner spinnerQuant;
    private Button buttonAddDonut;
    private ImageView imageDonutPreview;
    private Button buttonBackDonut;
    private TextView priceText;



    private DonutFlavor flavorSelection = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donut_activity);
        recyclerDonuts = findViewById(R.id.recyclerDonuts);
        spinnerQuant = findViewById(R.id.spinnerQuant);
        buttonAddDonut = findViewById(R.id.buttonAddDonut);
        imageDonutPreview = findViewById(R.id.imageDonutPreview);
        priceText = findViewById(R.id.priceText);
        buttonBackDonut = findViewById(R.id.buttonBackDonut);
        buttonBackDonut.setOnClickListener(v -> finish());

        setupRecyclerView();
        setupQuantitySpinner();
        setupAddButton();
        updatePrice();
    }

    private void setupRecyclerView() {
        recyclerDonuts.setLayoutManager(new LinearLayoutManager(this));
        DonutFlavor[] allFlavorsList = DonutFlavor.values();
        List<DonutFlavor> flavorsList= new ArrayList<DonutFlavor>();
        for (DonutFlavor flavor : allFlavorsList)
            flavorsList.add(flavor);

        DonutAdapter adapter = new DonutAdapter(flavorsList, this);
        recyclerDonuts.setAdapter(adapter);
    }

    private void setupQuantitySpinner() {
        List<Integer> quantities = new ArrayList<Integer>();
        for (int i = 1; i <= 12; i++)
            quantities.add(i);
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item,quantities);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerQuant.setAdapter(adapter);
        spinnerQuant.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, View view, int position, long id) {
                updatePrice();
            }
            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) { }
        });
    }

    private void setupAddButton() {
        buttonAddDonut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDonutOrder();
            }
        });
    }

    private void addDonutOrder() {
        if (flavorSelection == null){
            Toast.makeText(this, "Please select a donut flavor.", Toast.LENGTH_SHORT).show(); return;
        }

        Integer quant = (Integer) spinnerQuant.getSelectedItem();

        Donut donut = new Donut(flavorSelection, quant);
        AppData.getInstance().getCurrentOrder().addItem(donut);
        Toast.makeText(this, "Added " + quant + " " + flavorSelection.toString() + " to the order.", Toast.LENGTH_SHORT).show();

    }

    private int getFlavorImage(DonutFlavor flavor) {

        switch (flavor) {
            case YEAST_GLAZED:
                return R.drawable.yeast_glazed;
            case YEAST_CHOCOLATE:
                return R.drawable.yeast_choco;
            case YEAST_STRAWBERRY:
                return R.drawable.yeast_strawberry;
            case YEAST_CREAM:
                return R.drawable.yeast_cream;
            case YEAST_JELLY:
                return R.drawable.yeast_jelly;
            case YEAST_SPRINKLES:
                return R.drawable.yeast_sprinkle;
            case CAKE_PLAIN:
                return R.drawable.cake_plain;
            case CAKE_CHOCOLATE:
                return R.drawable.cake_chocolate;
            case CAKE_STRAWBERRY:
                return R.drawable.cake_strawberry;
            case HOLE_SUGAR:
                return R.drawable.hole_sugar;
            case HOLE_CINNAMON:
                return R.drawable.hole_cinnamon;
            case HOLE_POWDERED:
                return R.drawable.hole_powder;
            case SPOOKY:
                return R.drawable.seasonal_spooky;
            case PUMPKIN_SPICE:
                return R.drawable.seasonal_pumpkin_spice;
            default:
                return R.drawable.yeast_glazed;
        }
    }

    @Override
    public void onDonutClick(DonutFlavor flavor) {
        flavorSelection = flavor;

        int resId = getFlavorImage(flavor);
        imageDonutPreview.setImageResource(resId);
        updatePrice();

        Toast.makeText(this, "Selected: " + flavor.toString(), Toast.LENGTH_SHORT).show();
    }

    private void updatePrice() {
        if (flavorSelection == null) {
            priceText.setText("$0.00");
            return;
        }
        int quantity = (Integer) spinnerQuant.getSelectedItem();
        Donut donut = new Donut(flavorSelection, quantity);

        priceText.setText(String.format("$%.2f", donut.price()));
    }

}