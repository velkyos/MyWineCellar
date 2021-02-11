package com.velkyos.mywinecellar.Database.DataEntity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Fts4;
import androidx.room.Index;
import androidx.room.PrimaryKey;


@Entity(tableName = "wine_sender_list",indices = {@Index(value = {"wine_sender_name"}, unique = true)} )
public class EntityWineSender {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int wine_sender_id;

    @NonNull
    public String wine_sender_name;

    public EntityWineSender(@NonNull String wine_sender_name) {
        this.wine_sender_name = wine_sender_name;
    }
}
