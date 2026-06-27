package com.example.project5;

import android.view.View;
import android.widget.AdapterView;

/**
 * Helper Function
 * @author Angela Ding and Lucas Bandeira
 */
public abstract class SimpleItemListener implements AdapterView.OnItemSelectedListener
{
    public abstract void onItemSelected(int position);

    @Override
    public void onItemSelected(AdapterView<?> parent,View view, int position, long id)
    {
        onItemSelected(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {}

}
