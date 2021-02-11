package com.velkyos.mywinecellar;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.velkyos.mywinecellar.Adapter.WineInfoAdapter;

import com.velkyos.mywinecellar.Adapter.WineListAdapter;
import com.velkyos.mywinecellar.Database.DataEntity.EntityWine;
import com.velkyos.mywinecellar.Database.DataEntity.Wine;


import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    WineViewModel wineViewModel;

    RecyclerView wineListView;

    Random random;
    WineInfoAdapter wineAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        random = new Random();
        wineAdapter = new WineInfoAdapter();
        GridLayoutManager gridLayout = new GridLayoutManager(this, 1);

        Observer<List<Wine>> observer = new Observer<List<Wine>> () {
            @Override
            public void onChanged(@NotNull List<Wine> wines) {
                if (wines.size() != 0){ wineAdapter.setWine(wines.get(0), MainActivity.this);
                }else { Toast.makeText(MainActivity.this, "Aucune Bouteille Trouvée", Toast.LENGTH_LONG).show(); }}};

        wineViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(WineViewModel.class);


        wineListView = findViewById(R.id.main_menu_info);
        wineListView.setHasFixedSize(true);

        wineListView.setLayoutManager(gridLayout);
        wineListView.setAdapter(wineAdapter);

        wineViewModel.getWineById(1).observe(this,observer);

        initialiseText();
    }

    public void showListView(View view){
        Intent intent = new Intent(this, WinelistActivity.class);
        startActivity(intent);
    }

    public void addWineButton(View view) {
        Intent intent = new Intent(this, addWineActivity.class);
        startActivity(intent);
    }

    private void initialiseText(){
        EditText text = findViewById(R.id.RemoveWineNumberText);

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
                if (text.length() !=0){
                    int id = Integer.parseInt(s.toString());
                    if (id > 0){
                        findViewById(R.id.main_menu_info).setVisibility(View.VISIBLE);
                        wineViewModel.setSelectedWineId(id);
                    }

                }

            }
        };

        TextView.OnEditorActionListener actionListener = new TextView.OnEditorActionListener(){
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {

                    EditText currentText = findViewById(R.id.RemoveWineNumberText);
                    int wineId;
                    String wineIdText = currentText.getText().toString();
                    if (wineIdText.length() == 0) {
                        wineId = -1;
                    } else {

                        wineId = Integer.parseInt(wineIdText);
                    }

                    removeWine(wineId);

                    findViewById(R.id.RemoveWineBackground).setVisibility(View.GONE);
                    findViewById(R.id.main_menu_info).setVisibility(View.GONE);
                    currentText.setVisibility(View.GONE);

                    InputMethodManager imm = (InputMethodManager) getSystemService(v.getContext().INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(currentText.getWindowToken(), 0);
                    handled = true;
                }
                return handled;
            }
        };

        text.addTextChangedListener( watcher );

        text.setOnEditorActionListener( actionListener );
    }


    public void removeButtonClicked(View view) {

        EditText text = findViewById(R.id.RemoveWineNumberText);
        findViewById(R.id.RemoveWineBackground).setVisibility(View.VISIBLE);

        text.setVisibility(View.VISIBLE);
        text.setText("");

        text.requestFocus();

        InputMethodManager imm = (InputMethodManager) getSystemService(view.getContext().INPUT_METHOD_SERVICE);
        imm.showSoftInput(text, InputMethodManager.SHOW_IMPLICIT);

    }

    public void removeWine(int wineId) {
        if (wineId != (-1)) {
            wineViewModel.removeWineFromCave(wineId);
        }
    }


}