package com.velkyos.mywinecellar.Database.DAO;

import androidx.room.Dao;

@Dao
public interface MasterDAO extends WineListDAO, WineSenderDAO, WineTypeDAO, WineRegionDAO, WineNameDAO, WineMakerDAO, WineDAO{

}
