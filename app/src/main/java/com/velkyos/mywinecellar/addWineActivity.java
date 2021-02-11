package com.velkyos.mywinecellar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.velkyos.mywinecellar.Adapter.WineStatsAdapter;
import com.velkyos.mywinecellar.Database.DataEntity.*;
import com.velkyos.mywinecellar.View.FixedGridLayoutManager;



import java.util.List;

public class addWineActivity extends AppCompatActivity {

    private static final String OPTION_NAME = "name";
    private static final String OPTION_TYPE = "type";
    private static final String OPTION_MAKER = "maker";
    private static final String OPTION_REGION = "region";
    private static final String OPTION_SENDER = "sender";

    private addWineStats stats;

    private String currentMode;



    RecyclerView wineStatsView;

    WineViewModel wineViewModel;

    WineStatsAdapter wineStatsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_wine);


        wineStatsAdapter = new WineStatsAdapter();
        FixedGridLayoutManager gridLayout = new FixedGridLayoutManager(this, 1);
        wineViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(WineViewModel.class);


        initialiseObserver();
        initialiseText();
        initialiseClickEvent();
        initialiseClickEventMore();

        wineStatsView = findViewById(R.id.aw_recycler);
        wineStatsView.setHasFixedSize(true);

        wineStatsView.setAdapter(wineStatsAdapter);
        wineStatsView.setLayoutManager(gridLayout);

        stats = new addWineStats();
    }


    private void initialiseText() {
        EditText text = findViewById(R.id.aw_find_bar);

        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = s.toString();
                wineStatsAdapter.setCurrentSearch(text);
                wineViewModel.setOption(currentMode,text);

            }
        };
        text.addTextChangedListener(watcher);
    }

    private void initialiseObserver() {
        Observer<List<EntityWineName>> observerN = new Observer<List<EntityWineName>>() {
            @Override
            public void onChanged(List<EntityWineName> entityWineNameList) {
                wineStatsAdapter.setListN(entityWineNameList);
            }
        };
        Observer<List<EntityWineType>> observerT = new Observer<List<EntityWineType>>() {
            @Override
            public void onChanged(List<EntityWineType> entityWineTypeList) {
                wineStatsAdapter.setListT(entityWineTypeList);
            }
        };
        Observer<List<EntityWineMaker>> observerM = new Observer<List<EntityWineMaker>>() {
            @Override
            public void onChanged(List<EntityWineMaker> entityWineMakerList) {
                wineStatsAdapter.setListM(entityWineMakerList);
            }
        };
        Observer<List<EntityWineSender>> observerS = new Observer<List<EntityWineSender>>() {
            @Override
            public void onChanged(List<EntityWineSender> entityWineSenderList) {
                wineStatsAdapter.setListS(entityWineSenderList);
            }
        };
        Observer<List<EntityWineRegion>> observerR = new Observer<List<EntityWineRegion>>() {
            @Override
            public void onChanged(List<EntityWineRegion> entityWineRegionList) {
                wineStatsAdapter.setListR(entityWineRegionList);
            }
        };

        wineViewModel.getWineTypeList("").observe(this,observerT);
        wineViewModel.getWineNameList("").observe(this,observerN);
        wineViewModel.getWineMakerList("").observe(this,observerM);
        wineViewModel.getWineRegionList("").observe(this,observerR);
        wineViewModel.getWineSenderList("").observe(this,observerS);
    }

    private void initialiseClickEvent() {
        wineStatsAdapter.setOnItemClickListener(new WineStatsAdapter.OnStatsClickListener() {
            @Override
            public void onItemClick(EntityWineType type) {
                if (type.wine_type_id == 0){
                   type.wine_type_id = wineViewModel.insert(type);
                }
                if (type.wine_type_id != 0){
                    addWineActivity.this.stats.type = type;
                }
                closeScreen();
            }

            @Override
            public void onItemClick(EntityWineMaker maker) {
                if (maker.wine_maker_id == 0){
                   maker.wine_maker_id = wineViewModel.insert(maker);
                }
                if (maker.wine_maker_id != 0){
                    addWineActivity.this.stats.maker = maker;
                }
                closeScreen();
            }

            @Override
            public void onItemClick(EntityWineName name) {
                if (name.wine_name_id == 0){
                    name.wine_name_id = wineViewModel.insert(name);
                }
                if (name.wine_name_id != 0){
                    addWineActivity.this.stats.name = name;
                }
                closeScreen();
            }

            @Override
            public void onItemClick(EntityWineRegion region) {
                if (region.wine_region_id == 0){
                    region.wine_region_id = wineViewModel.insert(region);
                }
                if (region.wine_region_id != 0){
                    addWineActivity.this.stats.region = region;
                }
                closeScreen();
            }

            @Override
            public void onItemClick(EntityWineSender sender) {
                if (sender.wine_sender_id == 0){
                    sender.wine_sender_id = wineViewModel.insert(sender);
                }
                if (sender.wine_sender_id != 0){
                    addWineActivity.this.stats.sender = sender;
                }
                closeScreen();
            }

            private void closeScreen(){
                addWineActivity.this.stats.dataHasBeenModified();
                EditText currentText = findViewById(R.id.aw_find_bar);

                findViewById(R.id.aw_background).setVisibility(View.GONE);
                findViewById(R.id.aw_recycler).setVisibility(View.GONE);
                currentText.setVisibility(View.GONE);


                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(currentText.getWindowToken(), 0);
            }
        });

    }

    private void initialiseClickEventMore() {
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText text = findViewById(R.id.aw_find_bar);
                switch (view.getId()){
                    case R.id.aw_edit_name:
                        currentMode = OPTION_NAME;
                        break;
                    case R.id.aw_edit_type:
                        currentMode = OPTION_TYPE;
                        break;
                    case R.id.aw_edit_maker:
                        currentMode = OPTION_MAKER;
                        break;
                    case R.id.aw_edit_sender:
                        currentMode = OPTION_SENDER;
                        break;
                    case R.id.aw_edit_region:
                        currentMode = OPTION_REGION;
                        break;
                }
                findViewById(R.id.aw_background).setVisibility(View.VISIBLE);
                findViewById(R.id.aw_recycler).setVisibility(View.VISIBLE);
                text.setVisibility(View.VISIBLE);
                text.setText("");

                text.requestFocus();

                view.getContext();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(text, InputMethodManager.SHOW_IMPLICIT);
            }
        };
        findViewById(R.id.aw_edit_name).setOnClickListener(clickListener);
        findViewById(R.id.aw_edit_type).setOnClickListener(clickListener);
        findViewById(R.id.aw_edit_region).setOnClickListener(clickListener);
        findViewById(R.id.aw_edit_maker).setOnClickListener(clickListener);
        findViewById(R.id.aw_edit_sender).setOnClickListener(clickListener);
    }


    public void addWine(View view){
        int number = customIntParse(((EditText)findViewById(R.id.aw_edit_number)).getText());
        int price = customIntParse(((EditText)findViewById(R.id.aw_edit_price)).getText());
        int rating = customIntParse(((EditText)findViewById(R.id.aw_edit_rating)).getText());
        int year = customIntParse(((EditText)findViewById(R.id.aw_edit_year)).getText());
        int spoil = customIntParse(((EditText)findViewById(R.id.aw_edit_spoil)).getText());
        boolean error = false;

        String errorText = "";

        if( number == -1){
            error = true;
            errorText = "Number Invalid";
        }
        if (year == -1){
            error = true;
            errorText = "Year Invalid";
        }

        if (!error){
            EntityWine wine = new EntityWine(
                    true,
                    number,
                    stats.name.wine_name_id,
                    stats.type.wine_type_id,
                    year,
                    stats.region.wine_region_id,
                    stats.maker.wine_maker_id,
                    price,
                    stats.sender.wine_sender_id,
                    spoil,
                    rating);

            wineViewModel.insert(wine);
            finish();
        }else{
            Toast.makeText(addWineActivity.this,errorText,Toast.LENGTH_LONG).show();
        }
    }

    private int customIntParse(Editable s){
        String text = s.toString();
        int number = -1;
        if (text.length() != 0){
            number = Integer.parseInt(text);
        }
        return number;
    }

    private class addWineStats{
        public EntityWineName name;
        public EntityWineSender sender;
        public EntityWineMaker maker;
        public EntityWineType type;
        public EntityWineRegion region;

        public addWineStats(){
            name = new EntityWineName("A définir");
            name.wine_name_id = 1;
            sender = new EntityWineSender("A définir");
            sender.wine_sender_id = 1;
            maker = new EntityWineMaker("A définir");
            maker.wine_maker_id = 1;
            type = new EntityWineType("A définir");
            type.wine_type_id = 1;
            region = new EntityWineRegion("A définir");
            region.wine_region_id = 1;
            dataHasBeenModified();
        }

        public void dataHasBeenModified(){
            ((TextView)findViewById(R.id.aw_edit_name)).setText(name.wine_name_name);
            ((TextView)findViewById(R.id.aw_edit_type)).setText(type.wine_type_name);
            ((TextView)findViewById(R.id.aw_edit_region)).setText(region.wine_region_name);
            ((TextView)findViewById(R.id.aw_edit_maker)).setText(maker.wine_maker_name);
            ((TextView)findViewById(R.id.aw_edit_sender)).setText(sender.wine_sender_name);
        }
    }

}