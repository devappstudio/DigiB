package io.codehouse.stocksolution.adaptors;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import io.codehouse.stocksolution.R;
import io.codehouse.stocksolution.db.*;
import io.codehouse.stocksolution.db.Products;
import io.realm.Realm;

/**
 * Created by finxl-isaac on 12/9/16.
 */

public class ProductCategoriesAdaptor  extends RecyclerView.Adapter<ProductCategoriesAdaptor.MyViewHolder>  {
    private List<ProductCategories> productCategoriesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, quantity;

        public MyViewHolder(View view) {
            super(view);
            quantity = (TextView) view.findViewById(R.id.number_products);
            name = (TextView) view.findViewById(R.id.name);

        }
    }
    public ProductCategoriesAdaptor(List<ProductCategories> productCategoriesList) {
        this.productCategoriesList = productCategoriesList;
    }

    @Override
    public ProductCategoriesAdaptor.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_categories_list, parent, false);

        return new ProductCategoriesAdaptor.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProductCategoriesAdaptor.MyViewHolder holder, int position) {
        ProductCategories productCategories = productCategoriesList.get(position);
        Realm realm = Realm.getDefaultInstance();
        holder.name.setText(productCategories.getName());
        holder.quantity.setText(realm.where(Products.class).equalTo("id_product_category",productCategories.getId()).findAll().size()+" Product(s)");
    }

    @Override
    public int getItemCount() {
        return productCategoriesList.size();
    }


}

