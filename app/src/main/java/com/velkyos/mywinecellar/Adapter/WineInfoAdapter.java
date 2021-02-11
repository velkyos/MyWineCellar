package com.velkyos.mywinecellar.Adapter;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.velkyos.mywinecellar.Database.DataEntity.Wine;
import com.velkyos.mywinecellar.R;

public class WineInfoAdapter extends RecyclerView.Adapter<WineInfoAdapter.WineHolder> {
    private Wine wine;
    Context ctx;

    @NonNull
    @Override
    public WineHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.info_card, parent, false);
        return new WineHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull WineHolder holder, int position) {
            String tempText;

            int tempInt;
            holder.tId.setText("# " + wine._wine.wine_id);

            holder.tName.setText(wine._name.wine_name_name);

            holder.tType.setText(wine._type.wine_type_name);

            holder.tRegion.setText(wine._region.wine_region_name);

            holder.tYear.setText("" + wine._wine.wine_year);

            holder.tSender.setText(wine._sender.wine_sender_name);

            if (wine._wine.wine_price == -1) {
                holder.tPrice.setText(R.string.ic_noprice);
            } else {
                holder.tPrice.setText(wine._wine.wine_price + " €");
            }

            tempInt = wine._wine.wine_rating;
            tempText =  ctx.getResources().getQuantityString(R.plurals.ic_rating_format, tempInt, tempInt);
            holder.tRating.setText(tempText);

            tempInt = wine._wine.wine_number;
            tempText =  ctx.getResources().getQuantityString(R.plurals.ic_bottles_format, tempInt, tempInt);
            holder.tNumber.setText(tempText);

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public void setWine(Wine wine, Context ctx){
        this.wine = wine;
        this.ctx = ctx;

        notifyDataSetChanged();
    }

    public class WineHolder extends RecyclerView.ViewHolder{
        private TextView tId, tType, tRegion, tSender , tName, tPrice, tNumber, tYear, tRating;


        public WineHolder(@NonNull View itemView) {
            super(itemView);
            tId = itemView.findViewById(R.id.IC_id_text);
            tName = itemView.findViewById(R.id.IC_wine_name_text);
            tType = itemView.findViewById(R.id.IC_wine_type_text);

            tRegion = itemView.findViewById(R.id.IC_wine_region_text);
            tYear = itemView.findViewById(R.id.IC_wine_year_text);
            tNumber = itemView.findViewById(R.id.IC_number_text);

            tSender = itemView.findViewById(R.id.IC_wine_sender_text);
            tPrice = itemView.findViewById(R.id.IC_price_text);
            tRating = itemView.findViewById(R.id.IC_rating_text);
        }
    }
}
