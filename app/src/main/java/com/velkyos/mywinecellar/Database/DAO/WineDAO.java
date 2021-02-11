package com.velkyos.mywinecellar.Database.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;


import com.velkyos.mywinecellar.Database.DataEntity.Wine;

import java.util.List;

@Dao
public interface WineDAO {

    @Transaction
    @Query("SELECT * FROM wine_list WHERE wine_status = 1 ORDER BY wine_id ASC")
    LiveData<List<Wine>> selectAllWinesInCavesWithStrings();

    @Transaction
    @Query("SELECT * FROM wine_list WHERE wine_status = 1 ORDER BY wine_id ASC")
    LiveData<List<Wine>> selectAllWinesWithStrings();

    @Transaction
    @Query("SELECT * FROM wine_list WHERE wine_id = :id")
    LiveData<List<Wine>> selectWineById(int id);
}
