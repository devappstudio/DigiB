package io.codehouse.stocksolution.adaptors;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import io.codehouse.stocksolution.R;
import io.codehouse.stocksolution.db.ProductCategories;
import io.codehouse.stocksolution.db.Products;
import io.realm.Realm;

/**
 * Created by finxl-isaac on 12/8/16.
 */

public class StockDetailsAdaptors  extends RecyclerView.Adapter<StockDetailsAdaptors.MyViewHolder> {
    private List<StockDetails> stockDetailsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView product, quantity,category;

        public MyViewHolder(View view) {
            super(view);
            product = (TextView) view.findViewById(R.id.name);
            quantity = (TextView) view.findViewById(R.id.quantity);
            category = (TextView) view.findViewById(R.id.category);

        }
    }
    public StockDetailsAdaptors(List<StockDetails> stockDetailsList) {
        this.stockDetailsList = stockDetailsList;
    }

    @Override
    public StockDetailsAdaptors.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.stock_session_list, parent, false);
        return new StockDetailsAdaptors.MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(StockDetailsAdaptors.MyViewHolder holder, int position) {
        StockDetails stockDetails = stockDetailsList.get(position);
        Realm realm = Realm.getDefaultInstance();
        Products product = realm.where(Products.class).equalTo("id",stockDetails.getId_product()).findFirst();
        ProductCategories productCategories = realm.where(ProductCategories.class).equalTo("id",product.getId_product_category()).findFirst();
        holder.product.setText(product.getName());
        holder.quantity.setText(stockDetails.getQuantity()+"");
        holder.category.setText(productCategories.getName());
    }

    @Override
    public int getItemCount() {
        return stockDetailsList.size();
    }


}
