package com.example.project5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author Angela Ding
 */
public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        Button donut = findViewById(R.id.donutbutton);
        Button coffee = findViewById(R.id.coffeebutton);
        Button sandwich = findViewById(R.id.sandwichbutton);
        Button current = findViewById(R.id.currentbutton);
        Button allorders = findViewById(R.id.allordersbutton);

        donut.setOnClickListener(v -> startActivity(new Intent(this, DonutActivity.class)));
        coffee.setOnClickListener(v -> startActivity(new Intent(this, CoffeeActivity.class)));
        sandwich.setOnClickListener(v -> startActivity(new Intent(this, SandwichActivity.class)));
        current.setOnClickListener(v -> startActivity(new Intent(this, CurrentOrder.class)));
        allorders.setOnClickListener(v -> startActivity(new Intent(this, PlacedOrders.class)));
    }
}
