package com.velkyos.mywinecellar.Database.DataEntity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Fts4;
import androidx.room.Index;
import androidx.room.PrimaryKey;


@Entity(tableName = "wine_region_list",indices = {@Index(value = {"wine_region_name"}, unique = true)} )
public class EntityWineRegion {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int wine_region_id;

    @NonNull
    public String wine_region_name;

    public EntityWineRegion(@NonNull String wine_region_name) {
        this.wine_region_name = wine_region_name;
    }
}
