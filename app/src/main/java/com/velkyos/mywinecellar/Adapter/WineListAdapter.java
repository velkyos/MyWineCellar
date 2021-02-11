package com.velkyos.mywinecellar.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.velkyos.mywinecellar.Database.DataEntity.Wine;

import com.velkyos.mywinecellar.R;


import java.util.ArrayList;
import java.util.List;

public class WineListAdapter extends RecyclerView.Adapter<WineListAdapter.WineHolder> {
    private List<Wine> wineList = new ArrayList<>();

    private OnWineClickListener listener;


    @NonNull
    @Override
    public WineHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.wine_card, parent, false);

        return new WineHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull WineHolder holder, int position) {
        Context ctx = holder.itemView.getContext();
        Wine currentWine = wineList.get(position);

        int id = currentWine._wine.wine_id;
        String name = currentWine._name.wine_name_name;
        int year = currentWine._wine.wine_year;
        int rating = currentWine._wine.wine_rating;
        int price = currentWine._wine.wine_price;
        int number = currentWine._wine.wine_number;


        holder.textViewId.setText("# " + id);
        holder.textViewRating.setText(""+rating);
        holder.textViewName.setText(name);
        holder.textViewYear.setText(""+year);
        holder.textViewPrice.setText( price + " €");
        holder.textViewNumber.setText(ctx.getResources().getQuantityString(R.plurals.lc_bottles_format, number, number));


    }



    @Override
    public int getItemCount() {
        return wineList.size();
    }

    public void setAllList(List<Wine> wineList){
        this.wineList = wineList;
        notifyDataSetChanged();
    }


    class WineHolder extends RecyclerView.ViewHolder{
        private TextView textViewId;
        private TextView textViewName;
        private TextView textViewYear;
        private TextView textViewPrice;
        private TextView textViewRating;
        private TextView textViewNumber;


        public WineHolder(@NonNull View itemView) {
            super(itemView);
            textViewId = itemView.findViewById(R.id.wine_id_case);
            textViewName = itemView.findViewById(R.id.IC_wine_name_text);
            textViewYear = itemView.findViewById(R.id.wine_year_case);
            textViewPrice = itemView.findViewById(R.id.wine_price_case);
            textViewRating = itemView.findViewById(R.id.wine_rating_case);
            textViewNumber = itemView.findViewById(R.id.wine_number_case);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION){
                        listener.onItemClick(wineList.get(position));
                    }
                }
            });
        }


    }

    public interface OnWineClickListener {
        void onItemClick(Wine wine);
    }

    public void setOnItemClickListener(OnWineClickListener listener){
        this.listener = listener;
    }
}
