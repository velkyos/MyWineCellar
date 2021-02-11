package com.velkyos.mywinecellar.Database.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.velkyos.mywinecellar.Database.DataEntity.EntityWineMaker;

import java.util.List;

@Dao
public interface WineMakerDAO {

    @Insert
    long insert(EntityWineMaker wineMaker);

    @Delete
    void delete(EntityWineMaker... wineMaker);

    @Update
    void update(EntityWineMaker... wineMaker);

    @Query("DELETE FROM wine_maker_list")
    void deleteMakerList();

    @Query("SELECT * FROM wine_maker_list WHERE wine_maker_name LIKE :option")
    LiveData<List<EntityWineMaker>> getAllWineMakers(String option);

}
