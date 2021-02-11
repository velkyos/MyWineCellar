package com.velkyos.mywinecellar.Database.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.velkyos.mywinecellar.Database.DataEntity.EntityWineType;

import java.util.List;

@Dao
public interface WineTypeDAO {

    @Insert
    long insert(EntityWineType wineType);

    @Delete
    void delete(EntityWineType... wineType);

    @Update
    void update(EntityWineType... wineType);

    @Query("DELETE FROM wine_type_list")
    void deleteTypeList();

    @Query("SELECT * FROM wine_type_list WHERE wine_type_name LIKE :option")
    LiveData<List<EntityWineType>> getAllWineTypes(String option);

}

