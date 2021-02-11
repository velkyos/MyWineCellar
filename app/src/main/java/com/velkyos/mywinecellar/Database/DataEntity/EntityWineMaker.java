package com.velkyos.mywinecellar.Database.DataEntity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Fts4;
import androidx.room.Index;
import androidx.room.PrimaryKey;


@Entity(tableName = "wine_maker_list",indices = {@Index(value = {"wine_maker_name"}, unique = true)} )
public class EntityWineMaker {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int wine_maker_id;

    @NonNull
    public String wine_maker_name;

    public EntityWineMaker(@NonNull String wine_maker_name) {
        this.wine_maker_name = wine_maker_name;
    }
}
