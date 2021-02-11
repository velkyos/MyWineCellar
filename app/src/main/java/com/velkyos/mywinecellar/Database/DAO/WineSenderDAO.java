package com.velkyos.mywinecellar.Database.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.velkyos.mywinecellar.Database.DataEntity.EntityWineSender;

import java.util.List;

@Dao
public interface WineSenderDAO {

    @Insert
    long insert(EntityWineSender wineSender);

    @Delete
    void delete(EntityWineSender... wineSender);

    @Update
    void update(EntityWineSender... wineSender);

    @Query("DELETE FROM wine_sender_list")
    void deleteSenderList();

    @Query("SELECT * FROM wine_sender_list WHERE wine_sender_name LIKE :option")
    LiveData<List<EntityWineSender>> getAllWineSenders(String option);

}

