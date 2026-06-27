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
 * All the Actions in the Coffee Window
 * @author Angela Ding
 */
public class CoffeeActivity extends AppCompatActivity
{
    private CheckBox whippedCream, vanilla, two_percent_milk, caramel, mocha;
    private ImageView coffeePic;
    private Spinner sizeSpinner, quantitySpinner;
    private TextView priceText;
    private Button addButton, cancelButton;
    private int quantity = 1;
    private Cupsize defaultsize = Cupsize.SHORT;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coffee_activity);
        sizeSpinner = findViewById(R.id.sizeSpinner);
        quantitySpinner = findViewById(R.id.quantitySpinner);
        whippedCream = findViewById(R.id.whippedCream);
        vanilla = findViewById(R.id.vanilla);
        two_percent_milk = findViewById(R.id.two_percent_milk);
        caramel = findViewById(R.id.caramel);
        mocha = findViewById(R.id.mocha);
        priceText =findViewById(R.id.priceText);
        addButton = findViewById(R.id.addButton);
        cancelButton = findViewById(R.id.cancelButton);
        coffeePic = findViewById(R.id.coffeePic);

        setupSpinners();
        setupListeners();
        updatePrice();
    }
    private void setupSpinners() {
        ArrayAdapter<Cupsize> sizeAdapter = new ArrayAdapter<> (this, android.R.layout.simple_spinner_item, Cupsize.values());
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizeSpinner.setAdapter(sizeAdapter);
        ArrayAdapter<CharSequence> quantityAdapter = ArrayAdapter.createFromResource(this, R.array.quantity_values, android.R.layout.simple_spinner_item);
        quantityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        quantitySpinner.setAdapter(quantityAdapter);
        sizeSpinner.setOnItemSelectedListener(new SimpleItemListener() {
            @Override
            public void onItemSelected(int pos) {
                defaultsize = Cupsize.values()[pos];
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
        whippedCream.setOnClickListener(listener);
        vanilla.setOnClickListener(listener);
        two_percent_milk.setOnClickListener(listener);
        caramel.setOnClickListener(listener);
        mocha.setOnClickListener(listener);
        addButton.setOnClickListener(v -> addToOrder());
        cancelButton.setOnClickListener(v -> finish());
    }

    private List<AddIn> getSelectedAddIns()
    {
        List<AddIn> list = new ArrayList<>();
        if(whippedCream.isChecked()) list.add(AddIn.WHIPPED_CREAM);
        if(vanilla.isChecked()) list.add(AddIn.VANILLA);
        if(two_percent_milk.isChecked()) list.add(AddIn.TWO_PERCENT_MILK);
        if(caramel.isChecked()) list.add(AddIn.CARAMEL);
        if(mocha.isChecked()) list.add(AddIn.MOCHA);
        return list;
    }
    private void updatePrice()
    {
        Coffee coffee = new Coffee(defaultsize, getSelectedAddIns(),quantity);
        priceText.setText(String.format("$%.2f", coffee.price()));
    }
    private void addToOrder()
    {
        Coffee coffee = new Coffee(defaultsize, getSelectedAddIns(),quantity);
        AppData.getInstance().getCurrentOrder().addItem(coffee);
        Toast.makeText(this, "com.example.project5.Coffee added!", Toast.LENGTH_SHORT).show();
        finish();
    }
}
