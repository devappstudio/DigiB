package io.codehouse.stocksolution.adaptors;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import io.codehouse.stocksolution.R;

/**
 * Created by finxl-isaac on 12/8/16.
 */

public class StoresAdaptor extends RecyclerView.Adapter<StoresAdaptor.MyViewHolder> {
    private List<Stores> storesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, location;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.name);
            location = (TextView) view.findViewById(R.id.location);
        }
    }
    public StoresAdaptor(List<Stores> storesList) {
        this.storesList = storesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.store_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Stores store = storesList.get(position);
        holder.title.setText(store.getName());
        holder.location.setText(store.getLocation());
    }

    @Override
    public int getItemCount() {
        return storesList.size();
    }

}
