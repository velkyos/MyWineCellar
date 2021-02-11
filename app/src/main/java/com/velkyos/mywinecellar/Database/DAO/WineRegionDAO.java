package com.velkyos.mywinecellar.Database.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.velkyos.mywinecellar.Database.DataEntity.EntityWineRegion;

import java.util.List;

@Dao
public interface WineRegionDAO {

    @Insert
    long insert(EntityWineRegion wineRegion);

    @Delete
    void delete(EntityWineRegion... wineRegion);

    @Update
    void update(EntityWineRegion... wineRegion);

    @Query("DELETE FROM wine_region_list")
    void deleteRegionList();

    @Query("SELECT * FROM wine_region_list WHERE wine_region_name LIKE :option")
    LiveData<List<EntityWineRegion>> getAllWineRegions(String option);

}
