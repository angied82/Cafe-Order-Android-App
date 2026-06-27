package com.example.project5;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * All the Actions in the Sandwich Window
 * @author Angela Ding
 */
public class SandwichActivity extends AppCompatActivity
{
    private CheckBox lettuce, tomato, onion, cheese;
    private ImageView sandwichPic;
    private Spinner breadSpinner, proteinSpinner, quantitySpinner;
    private TextView priceText;
    private Button addButton, cancelButton;
    private Bread defaultbread = Bread.BAGEL;
    private Protein defaultprotein = Protein.BEEF;
    private int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sandwich_activity);
        breadSpinner = findViewById(R.id.breadSpinner);
        proteinSpinner = findViewById(R.id.proteinSpinner);
        quantitySpinner = findViewById(R.id.quantitySpinner);
        lettuce = findViewById(R.id.lettuce);
        tomato = findViewById(R.id.tomato);
        onion = findViewById(R.id.onion);
        cheese = findViewById(R.id.cheese);
        priceText = findViewById(R.id.priceText);
        sandwichPic = findViewById(R.id.sandwichPic);
        addButton = findViewById(R.id.addButton);
        cancelButton = findViewById(R.id.cancelButton);

        setupSpinners();
        setupListeners();
        updatePrice();
    }

    private void setupSpinners()
    {
        breadSpinner.setAdapter(new ArrayAdapter<> (this, android.R.layout.simple_spinner_item, Bread.values()));
        proteinSpinner.setAdapter(new ArrayAdapter<> (this, android.R.layout.simple_spinner_item, Protein.values()));
        ArrayAdapter<CharSequence> quantityAdapter = ArrayAdapter.createFromResource(this, R.array.quantity_values, android.R.layout.simple_spinner_item);
        quantitySpinner.setAdapter(quantityAdapter);
        breadSpinner.setOnItemSelectedListener(new SimpleItemListener() {
            @Override
            public void onItemSelected(int pos) {
                defaultbread = Bread.values()[pos];
                updatePrice();
            }
        });
        proteinSpinner.setOnItemSelectedListener(new SimpleItemListener() {
            @Override
            public void onItemSelected(int pos) {
                defaultprotein = Protein.values()[pos];
                updatePrice();
            }
        });
        quantitySpinner.setOnItemSelectedListener(new SimpleItemListener() {
            @Override
            public void onItemSelected(int pos) {
                quantity = Integer.parseInt(quantitySpinner.getItemAtPosition(pos).toString());
                updatePrice();
            }
        });
    }
    private void setupListeners()
    {
        View.OnClickListener listener = v -> updatePrice();
        lettuce.setOnClickListener(listener);
        tomato.setOnClickListener(listener);
        onion.setOnClickListener(listener);
        cheese.setOnClickListener(listener);
        addButton.setOnClickListener(v -> addToOrder());
        cancelButton.setOnClickListener(v -> finish());
    }
    private List<AddOn> getSelectedAddOns()
    {
        List<AddOn> list = new ArrayList<>();
            if(lettuce.isChecked()) list.add(AddOn.LETTUCE);
            if(tomato.isChecked()) list.add(AddOn.TOMATO);
            if(onion.isChecked()) list.add(AddOn.ONION);
            if(cheese.isChecked()) list.add(AddOn.CHEESE);
            return list;
    }
    private void updatePrice()
    {
        Sandwich sandwich = new Sandwich(defaultbread,defaultprotein,getSelectedAddOns(),quantity);
        priceText.setText(String.format("$%.2f", sandwich.price()));

    }

    private void addToOrder()
    {
        Sandwich sandwich = new Sandwich(defaultbread,defaultprotein,getSelectedAddOns(),quantity);
        AppData.getInstance().getCurrentOrder().addItem(sandwich);
        Toast.makeText(this, "com.example.project5.Sandwich added!", Toast.LENGTH_SHORT).show();
        finish();
    }
}
