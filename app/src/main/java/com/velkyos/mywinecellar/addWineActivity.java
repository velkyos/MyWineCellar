package com.velkyos.mywinecellar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
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

        wineStatsView = findViewById(R.id.am_recycler);
        wineStatsView.setHasFixedSize(true);

        wineStatsView.setAdapter(wineStatsAdapter);
        wineStatsView.setLayoutManager(gridLayout);


    }



    public void buttonClicked(View view){
        EditText text = findViewById(R.id.am_find_bar);
        switch (view.getId()){
            case R.id.am_buton_name:
                currentMode = OPTION_NAME;
                break;
            case R.id.am_buton_type:
                currentMode = OPTION_TYPE;
                break;
            case R.id.am_buton_maker:
                currentMode = OPTION_MAKER;
                break;
            case R.id.am_buton_sender:
                currentMode = OPTION_SENDER;
                break;
            case R.id.am_buton_region:
                currentMode = OPTION_REGION;
                break;
        }
        findViewById(R.id.am_background).setVisibility(View.VISIBLE);
        findViewById(R.id.am_recycler).setVisibility(View.VISIBLE);
        text.setVisibility(View.VISIBLE);
        text.setText("");

        text.requestFocus();

        InputMethodManager imm = (InputMethodManager) getSystemService(view.getContext().INPUT_METHOD_SERVICE);
        imm.showSoftInput(text, InputMethodManager.SHOW_IMPLICIT);
    }

    private void initialiseText() {
        EditText text = findViewById(R.id.am_find_bar);

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
                    Toast.makeText(addWineActivity.this, ""+wineViewModel.insert(type) , Toast.LENGTH_SHORT).show();
                }
                closeScreen();
            }

            @Override
            public void onItemClick(EntityWineMaker maker) {
                if (maker.wine_maker_id == 0){
                    Toast.makeText(addWineActivity.this, ""+wineViewModel.insert(maker) , Toast.LENGTH_SHORT).show();
                }
                closeScreen();
            }

            @Override
            public void onItemClick(EntityWineName name) {
                if (name.wine_name_id == 0){
                    Toast.makeText(addWineActivity.this, ""+wineViewModel.insert(name) , Toast.LENGTH_SHORT).show();
                }
                closeScreen();
            }

            @Override
            public void onItemClick(EntityWineRegion region) {
                if (region.wine_region_id == 0){
                    Toast.makeText(addWineActivity.this, ""+wineViewModel.insert(region) , Toast.LENGTH_SHORT).show();
                }
                closeScreen();
            }

            @Override
            public void onItemClick(EntityWineSender sender) {
                if (sender.wine_sender_id == 0){
                    Toast.makeText(addWineActivity.this, ""+wineViewModel.insert(sender) , Toast.LENGTH_SHORT).show();
                }
                closeScreen();
            }

            private void closeScreen(){
                EditText currentText = findViewById(R.id.am_find_bar);

                findViewById(R.id.am_background).setVisibility(View.GONE);
                findViewById(R.id.am_recycler).setVisibility(View.GONE);
                currentText.setVisibility(View.GONE);

                InputMethodManager imm = (InputMethodManager) getSystemService(addWineActivity.this.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(currentText.getWindowToken(), 0);
            }
        });

    }
}