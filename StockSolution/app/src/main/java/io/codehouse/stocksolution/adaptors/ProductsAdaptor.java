package io.codehouse.stocksolution.adaptors;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import io.codehouse.stocksolution.R;
import io.codehouse.stocksolution.db.ProductCategories;
import io.realm.Realm;

/**
 * Created by finxl-isaac on 12/8/16.
 */

public class ProductsAdaptor extends RecyclerView.Adapter<ProductsAdaptor.MyViewHolder>  {
    private List<Products> productsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, category,unit;

        public MyViewHolder(View view) {
            super(view);
            category = (TextView) view.findViewById(R.id.category);
            name = (TextView) view.findViewById(R.id.name);
            unit = (TextView) view.findViewById(R.id.unit);

        }
    }
    public ProductsAdaptor(List<Products> productsList) {
        this.productsList = productsList;
    }

    @Override
    public ProductsAdaptor.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.products_list, parent, false);

        return new ProductsAdaptor.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProductsAdaptor.MyViewHolder holder, int position) {
        Products products = productsList.get(position);
        holder.name.setText(products.getName());
        holder.unit.setText(products.getMeasurement());
        Realm realm = Realm.getDefaultInstance();
        ProductCategories productCategories = realm.where(ProductCategories.class).equalTo("id",products.getId_product_category()).findFirst();
        holder.category.setText(productCategories.getName());
    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }


}
