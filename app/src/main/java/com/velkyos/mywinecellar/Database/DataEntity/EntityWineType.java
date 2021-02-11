package com.velkyos.mywinecellar.Database.DataEntity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Fts4;
import androidx.room.Index;
import androidx.room.PrimaryKey;


@Entity(tableName = "wine_type_list",indices = {@Index(value = {"wine_type_name"}, unique = true)} )
public class EntityWineType {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int wine_type_id;

    @NonNull
    public String wine_type_name;

    public EntityWineType(@NonNull String wine_type_name) {
        this.wine_type_name = wine_type_name;
    }
}
