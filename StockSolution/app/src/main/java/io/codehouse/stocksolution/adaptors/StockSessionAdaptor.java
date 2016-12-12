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

public class StockSessionAdaptor extends RecyclerView.Adapter<StockSessionAdaptor.MyViewHolder>  {
    private List<StockSessions> stockSessionsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView date, name,status;

        public MyViewHolder(View view) {
            super(view);
            date = (TextView) view.findViewById(R.id.date_opened);
            name = (TextView) view.findViewById(R.id.user);
            status = (TextView) view.findViewById(R.id.state);

        }
    }
    public StockSessionAdaptor(List<StockSessions> stockSessionsList) {
        this.stockSessionsList = stockSessionsList;
    }

    @Override
    public StockSessionAdaptor.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.stock_session_list, parent, false);

        return new StockSessionAdaptor.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(StockSessionAdaptor.MyViewHolder holder, int position) {
        StockSessions stockSessions = stockSessionsList.get(position);
        holder.name.setText(stockSessions.getUser());
        holder.date.setText(stockSessions.getDate_opened());
        if(stockSessions.getDate_closed().equalsIgnoreCase(null) ||  stockSessions.getDate_closed().equalsIgnoreCase(""))
        {
            holder.status.setText("Opened");
        }
        else
        {
            holder.status.setText("Closed");
        }
    }

    @Override
    public int getItemCount() {
        return stockSessionsList.size();
    }


}
