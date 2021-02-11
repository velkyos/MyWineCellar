package com.velkyos.mywinecellar.Database.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.velkyos.mywinecellar.Database.DataEntity.EntityWineName;

import java.util.List;

@Dao
public interface WineNameDAO {

    @Insert
    long insert(EntityWineName wineName);

    @Delete
    void delete(EntityWineName... wineName);

    @Update
    void update(EntityWineName... wineName);

    @Query("DELETE FROM wine_name_list")
    void deleteNameList();

    @Query("SELECT * FROM wine_name_list WHERE wine_name_name LIKE :option")
    LiveData<List<EntityWineName>> getAllWineNames(String option);

}

