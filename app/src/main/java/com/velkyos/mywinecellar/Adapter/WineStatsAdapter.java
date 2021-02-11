package com.velkyos.mywinecellar.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.velkyos.mywinecellar.Database.DataEntity.*;
import com.velkyos.mywinecellar.R;

import java.util.ArrayList;
import java.util.List;

public class WineStatsAdapter extends RecyclerView.Adapter<WineStatsAdapter.StatsHolder> {

    private List<EntityWineName> wineNames = new ArrayList<>();
    private List<EntityWineType> wineTypes = new ArrayList<>();
    private List<EntityWineRegion> wineRegions = new ArrayList<>();
    private List<EntityWineMaker> wineMakers = new ArrayList<>();
    private List<EntityWineSender> wineSenders = new ArrayList<>();

    private OnStatsClickListener listener;

    private String mode;
    private String currentSearch;

    @NonNull
    @Override
    public StatsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.wine_stats_card, parent , false);
        return new StatsHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StatsHolder holder, int position) {
        String name = "None";
        Context ctx = holder.itemView.getContext();
        int color = ctx.getColor(R.color.colorPrimary);
        switch (mode.toLowerCase()){
            case "name":
                name = wineNames.get(position).wine_name_name;
                color = ctx.getColor(R.color.Wine_Name_Background);
                break;
            case "type":
                name = wineTypes.get(position).wine_type_name;
                color = ctx.getColor(R.color.Wine_Type_Background);
                break;
            case "region":
                name = wineRegions.get(position).wine_region_name;
                color = ctx.getColor(R.color.Wine_Region_Background);
                break;
            case "sender":
                name = wineSenders.get(position).wine_sender_name;
                color = ctx.getColor(R.color.Wine_Sender_Background);
                break;
            case "maker":
                name = wineMakers.get(position).wine_maker_name;
                color = ctx.getColor(R.color.Wine_Maker_Background);
                break;
        }
        holder.text.setText(name);
        holder.card.setCardBackgroundColor(color);
    }

    @Override
    public int getItemCount() {
        int total = 0;
        switch (mode){
            case "name":
                total = wineNames.size();
                break;
            case "type":
                total = wineTypes.size();
                break;
            case "region":
                total = wineRegions.size();
                break;
            case "sender":
                total = wineSenders.size();
                break;
            case "maker":
                total = wineMakers.size();
                break;
        }
        return total;
    }

    public class StatsHolder extends RecyclerView.ViewHolder{

        private CardView card;
        private TextView text;


        public StatsHolder(@NonNull View itemView) {
            super(itemView);

            text = itemView.findViewById(R.id.ws_text);
            card = itemView.findViewById(R.id.ws_card);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener != null && position != RecyclerView.NO_POSITION){
                        switch (mode){
                            case "name":
                                listener.onItemClick(wineNames.get(position));
                                break;
                            case "type":
                                listener.onItemClick(wineTypes.get(position));
                                break;
                            case "region":
                                listener.onItemClick(wineRegions.get(position));
                                break;
                            case "sender":
                                listener.onItemClick(wineSenders.get(position));
                                break;
                            case "maker":
                                listener.onItemClick(wineMakers.get(position));
                                break;
                        }
                    }
                }
            });
        }
    }

    public interface OnStatsClickListener {
        void onItemClick(EntityWineType type);
        void onItemClick(EntityWineMaker maker);
        void onItemClick(EntityWineName name);
        void onItemClick(EntityWineRegion region);
        void onItemClick(EntityWineSender sender);
    }

    public void setListN(List<EntityWineName> names){
        mode="name";this.wineNames = names;
        this.wineNames.add(new EntityWineName(currentSearch));
        notifyDataSetChanged();}

    public void setListT(List<EntityWineType> types){
        mode="type";this.wineTypes = types;
        this.wineTypes.add(new EntityWineType(currentSearch));
        notifyDataSetChanged();}

    public void setListR(List<EntityWineRegion> regions){
        mode="region";this.wineRegions = regions;
        this.wineRegions.add(new EntityWineRegion(currentSearch));
        notifyDataSetChanged();}

    public void setListM(List<EntityWineMaker> makers){
        mode="maker";this.wineMakers = makers;
        this.wineMakers.add(new EntityWineMaker(currentSearch));
        notifyDataSetChanged();}

    public void setListS(List<EntityWineSender> senders){
        mode="sender";this.wineSenders = senders;
        this.wineSenders.add(new EntityWineSender(currentSearch));
        notifyDataSetChanged();}

    public void setOnItemClickListener(OnStatsClickListener listener){this.listener = listener;}

    public void setCurrentSearch(String currentSearch){
        this.currentSearch = currentSearch;
    }
}
