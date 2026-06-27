package com.example.project5;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author Angela Ding
 */
public class PlacedOrders extends AppCompatActivity
{
    private ListView placedOrdersList;
    private Button backButton;
    private ArrayAdapter<Order> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.placedorders);
        placedOrdersList = findViewById(R.id.placedOrdersList);
        backButton = findViewById(R.id.backButton);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, AppData.getInstance().getPlacedOrders());
        placedOrdersList.setAdapter(adapter);
        backButton.setOnClickListener(v -> finish());
    }

}
