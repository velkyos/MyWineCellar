package com.velkyos.mywinecellar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Configuration;
import android.os.Bundle;

import com.velkyos.mywinecellar.Database.DataEntity.Wine;
import com.velkyos.mywinecellar.Adapter.WineListAdapter;

import java.util.List;

public class WinelistActivity extends AppCompatActivity {

    RecyclerView wineListView;


    WineViewModel wineViewModel;


    WineListAdapter wineAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winelist);



        wineListView = findViewById(R.id.wineList);
        updateCollumWineView(this.getResources().getConfiguration());     //On update le nombre de colonne pour afficher la liste de vin
        wineListView.setHasFixedSize(true);

        wineAdapter = new WineListAdapter();
        wineListView.setAdapter(wineAdapter);

        wineViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(WineViewModel.class);

        wineViewModel.getWineList().observe(this, new Observer<List<Wine>>() {
            @Override
            public void onChanged(List<Wine> Wines) {
                wineAdapter.setAllList(Wines);
            }
        });

        wineAdapter.setOnItemClickListener(new WineListAdapter.OnWineClickListener() {
            @Override
            public void onItemClick(Wine wine) {
                removeWine( wine._wine.wine_id );
            }
        });

    }



    //#region RemoveWine


    public void removeWine(int wineId){
        if (wineId != (-1)){ wineViewModel.removeWineFromCave(wineId);}
    }
    //endregion


    //#region Orientation
    @Override
    public void onConfigurationChanged(Configuration newConfig) {

        super.onConfigurationChanged(newConfig);        //On met à jour la configuration

        updateCollumWineView(newConfig);     //On update le nombre de colonne pour afficher la liste de vin
    }

    private void updateCollumWineView(Configuration config){
        if (config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            wineListView.setLayoutManager(new GridLayoutManager(this, 2));
        } else if (config.orientation == Configuration.ORIENTATION_PORTRAIT){
            wineListView.setLayoutManager(new GridLayoutManager(this, 1));
        }
    }
    //endregion
}