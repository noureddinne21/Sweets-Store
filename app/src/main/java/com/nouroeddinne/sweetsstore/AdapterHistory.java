package com.nouroeddinne.sweetsstore;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.chip.Chip;
import java.util.List;
public class AdapterHistory extends RecyclerView.Adapter<AdapterHistory.AdapterItemViewHolder> {

    private List<String> items;

    public AdapterHistory(List<String> items) {
        this.items = items;
    }

    @Override
    public AdapterItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history_search, parent, false);
        return new AdapterItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterItemViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addNewItem(String itemName) {
        items.add(itemName);
        notifyItemInserted(items.size() - 1); // Notify adapter about the new item inserted
    }

    public class AdapterItemViewHolder extends RecyclerView.ViewHolder {

        private TextView tvItem;

        public AdapterItemViewHolder(View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(R.id.textView_item);
        }

        public void bind(String item) {
            tvItem.setText(item);
        }
    }











































}
