package com.velkyos.mywinecellar.Database.DataEntity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Fts4;
import androidx.room.Index;
import androidx.room.PrimaryKey;


@Entity(tableName = "wine_name_list",indices = {@Index(value = {"wine_name_name"}, unique = true)} )
public class EntityWineName{

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int wine_name_id;

    @NonNull
    public String wine_name_name;

    public EntityWineName(@NonNull String wine_name_name) {
        this.wine_name_name = wine_name_name;
    }
}
