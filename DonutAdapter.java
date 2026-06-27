package com.example.project5;

import java.util.List;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author Lucas Banderia
 */
public class DonutAdapter extends RecyclerView.Adapter<DonutAdapter.DonutViewHolder> {

    public interface OnDonutClickListener {
        void onDonutClick(DonutFlavor flavor);
    }

    private OnDonutClickListener listener;
    private List<DonutFlavor> flavors;

    public DonutAdapter(List<DonutFlavor> flavors, OnDonutClickListener listener) {
        this.flavors = flavors;
        this.listener = listener;
    }

    @NonNull
    @Override
    public DonutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_donut, parent, false);
        return new DonutViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull DonutViewHolder holder, int pos) {
        final DonutFlavor flavor = flavors.get(pos);
        holder.donutNameText.setText(flavor.toString());

        int imageResId = getFlavorImage(flavor);
        holder.donutImage.setImageResource(imageResId);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onDonutClick(flavor);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (flavors == null)
            return 0;
        return flavors.size();
    }

    public static class DonutViewHolder extends RecyclerView.ViewHolder {
        TextView donutNameText;
        ImageView donutImage;

        public DonutViewHolder(@NonNull View itemView) {
            super(itemView);
            donutImage = itemView.findViewById(R.id.donutImage);
            donutNameText = itemView.findViewById(R.id.donutNameText);
        }

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
}