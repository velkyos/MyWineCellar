package com.velkyos.mywinecellar.Database.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.velkyos.mywinecellar.Database.DataEntity.*;

import java.util.List;

@Dao
public interface WineListDAO {

    @Insert
    long insert(EntityWine wine);

    @Delete
    void delete(EntityWine wine);

    @Update
    void update(EntityWine wine);

    @Query("DELETE FROM wine_list")
    void deleteWineList();

    @Query("UPDATE wine_list SET wine_number = wine_number - 1 WHERE wine_id = :id AND wine_number > 0")
    void removeBottleFromCave(int id);

    @Query("UPDATE wine_list SET wine_status = 0 WHERE wine_id = :id AND wine_number = 0")
    void removeWinesFromCave(int id);
}

//@Query("SELECT wine_id" +
//        "wine_id AS id, " +
//        "wine_list.wine_number AS number, " +
//        "wine_name_list.wine_name AS name, " +
//        "wine_type_list.wine_type_name AS type, " +
//        "wine_list.wine_year AS year, " +
//        "wine_region_list.wine_region_name AS region, " +
//        "wine_maker_list.wine_maker_name AS maker, " +
//        "wine_list.wine_price AS price, " +
//        "wine_sender_list.wine_sender_name AS sender, " +
//        "wine_list.wine_spoil AS spoil, " +
//        "wine_list.wine_rating AS rating, " +
//
//        "FROM wine_list " +
//
//        "JOIN " +
//        "name ON " +
//        "wine_list.wine_name = wine_name_list.wine_name_id" +
//
//        " JOIN " +
//        "type ON " +
//        "wine_list.wine_type = wine_type_list.wine_type_id" +
//
//        " JOIN " +
//        "region ON " +
//        "wine_list.wine_region = wine_region_list.wine_region_id" +
//
//        " JOIN " +
//        "maker ON " +
//        "wine_list.wine_maker = wine_maker_list.wine_maker_id" +
//
//        " JOIN " +
//        "sender ON " +
//        "wine_list.wine_sender = wine_sender_list.wine_sender_id" +
//
//        " WHERE wine_list.wine_status = 1")