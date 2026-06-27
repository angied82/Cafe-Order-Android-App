package com.example.project5;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author Angela Ding
 */
public class CurrentOrder extends AppCompatActivity
{
    private ListView orderList;
    private TextView subtotalText, taxText, totalText;
    private Button removeButton, clearButton, placeButton, backButton;
    private ArrayAdapter<MenuItem> adapter;
    private Order order;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.currentorder);
        orderList = findViewById(R.id.orderList);
        subtotalText = findViewById(R.id.subtotalText);
        taxText = findViewById(R.id.taxText);
        totalText = findViewById(R.id.totalText);
        removeButton = findViewById(R.id.removeButton);
        clearButton = findViewById(R.id.clearButton);
        placeButton = findViewById(R.id.placeButton);
        backButton = findViewById(R.id.backButton);
        order = AppData.getInstance().getCurrentOrder();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, order.getMenuItems());
        orderList.setAdapter(adapter);
        orderList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        setupButtons();
        updateTotals();
    }
    private void setupButtons()
    {
        removeButton.setOnClickListener(v ->
        {
            int position = orderList.getCheckedItemPosition();
            if(position == ListView.INVALID_POSITION)
            {
                Toast.makeText(this, "Please select an item to remove.", Toast.LENGTH_SHORT).show();
                return;
            }
            new AlertDialog.Builder(this).setTitle("Remove Item").setMessage("Are you sure you want to remove this item?").setPositiveButton("Remove",(dialog,which)-> {
                MenuItem item = adapter.getItem(position);
                order.removeItem(item);
                refreshList();
                orderList.clearChoices();
                updateTotals();
            })
                    .setNegativeButton("Cancel", null).show();
        });
        clearButton.setOnClickListener(v ->
        {
                if(order.getMenuItems().isEmpty())
                {
                    Toast.makeText(this, "com.example.project5.Order is already empty.", Toast.LENGTH_SHORT).show();
                    return;
                }
                new AlertDialog.Builder(this).setTitle("Clear com.example.project5.Order").setMessage("Are you sure you want to clear this order?").setPositiveButton("Clear",(dialog,which)-> {
                order.clear();
                refreshList();
                adapter.notifyDataSetChanged();
                updateTotals();
                })
                        .setNegativeButton("Cancel", null).show();
        });
        placeButton.setOnClickListener(v ->
        {
            if(order.getMenuItems().isEmpty())
            {
                Toast.makeText(this, "Cannot place an empty order!", Toast.LENGTH_SHORT).show();
                return;
            }
            AppData.getInstance().placeCurrentOrder();
            Toast.makeText(this,"com.example.project5.Order placed!", Toast.LENGTH_SHORT).show();
            finish();
        });
        backButton.setOnClickListener(v -> finish());

    }
    private void updateTotals()
    {
        subtotalText.setText("Subtotal: $" + String.format("%.2f",order.getSubtotal()));
        taxText.setText("Tax: $" + String.format("%.2f", order.getTax()));
        totalText.setText("Total: $" + String.format("%.2f", order.getTotal()));
    }
    private void refreshList() {
        adapter.clear();
        adapter.addAll(order.getMenuItems());
        adapter.notifyDataSetChanged();
    }
}
