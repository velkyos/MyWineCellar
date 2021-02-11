package com.velkyos.mywinecellar;

import android.app.Application;

import androidx.annotation.NonNull;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.velkyos.mywinecellar.Database.DataEntity.*;

import com.velkyos.mywinecellar.Database.DataEntity.Wine;
import com.velkyos.mywinecellar.Database.WineRepository;
import java.util.List;

public class WineViewModel extends AndroidViewModel {

    private static final String OPTION_NAME = "name";
    private static final String OPTION_TYPE = "type";
    private static final String OPTION_MAKER = "maker";
    private static final String OPTION_REGION = "region";
    private static final String OPTION_SENDER = "sender";


    private WineRepository wineRepository;

    private LiveData<List<Wine>>  selectedWine;
    private LiveData<List<Wine>> wineList;
    private LiveData<List<EntityWineName>> wineNameList;
    private LiveData<List<EntityWineMaker>> wineMakerList;
    private LiveData<List<EntityWineRegion>> wineRegionList;
    private LiveData<List<EntityWineType>> wineTypeList;
    private LiveData<List<EntityWineSender>> wineSenderList;


    public WineViewModel(@NonNull Application application) {
        super(application);
        wineRepository = new WineRepository(application);
        wineList = wineRepository.getWinesForView();
        wineNameList = wineRepository.getWineNames();
        wineMakerList = wineRepository.getWineMakers();
        wineRegionList = wineRepository.getWineRegions();
        wineTypeList = wineRepository.getWineTypes();
        wineSenderList = wineRepository.getWineSenders();
        selectedWine = wineRepository.getSelectedWine();
    }


    public void insert(EntityWine entityWine){
        wineRepository.insert(entityWine);
    }

    public void update(EntityWine entityWine){
        wineRepository.update(entityWine);
    }

    public void delete(EntityWine entityWine){
        wineRepository.delete(entityWine);
    }

    //#region deleteAllData

//    public void deleteAllWines(){wineRepository.deleteAllWines();}
//    public void deleteAllWinesName(){wineRepository.deleteAllWineNames();}
//    public void deleteAllWinesMaker(){wineRepository.deleteAllWineMakers();}
//    public void deleteAllWinesType(){wineRepository.deleteAllWineTypes();}
//    public void deleteAllWinesRegion(){wineRepository.deleteAllWineRegions();}
//    public void deleteAllWinesSender(){wineRepository.deleteAllWineSenders();}

    //#endregion

    public int insert(EntityWineRegion entityWineRegion){ return wineRepository.insert(entityWineRegion); }
    public int insert(EntityWineType entityWineType){ return wineRepository.insert(entityWineType); }
    public int insert(EntityWineMaker entityWineMaker){ return wineRepository.insert(entityWineMaker); }
    public int insert(EntityWineSender entityWineSender){ return wineRepository.insert(entityWineSender); }
    public int insert(EntityWineName entityWineName){ return wineRepository.insert(entityWineName); }



    //#region getData
    public LiveData<List<Wine>>  getWineById(int id){
        wineRepository.setSelectedWineId(id);
        return selectedWine;
    }

    public void setSelectedWineId(int id){wineRepository.setSelectedWineId(id);}

    public void setOption(String option,String filter){wineRepository.setSelectedOptions(option,filter);}

    public void removeWineFromCave(int id){
        wineRepository.removeBottleFromCave(id);
    }

    public LiveData<List<Wine>> getWineList() {
        return wineList;
    }

    public LiveData<List<EntityWineName>> getWineNameList(String filter) {
        wineRepository.setSelectedOptions(OPTION_NAME,filter);
        return wineNameList;
    }

    public LiveData<List<EntityWineMaker>> getWineMakerList(String filter) {
        wineRepository.setSelectedOptions(OPTION_MAKER,filter);
        return wineMakerList;
    }

    public LiveData<List<EntityWineRegion>> getWineRegionList(String filter) {
        wineRepository.setSelectedOptions(OPTION_REGION,filter);
        return wineRegionList;
    }

    public LiveData<List<EntityWineType>> getWineTypeList(String filter) {
        wineRepository.setSelectedOptions(OPTION_TYPE,filter);
        return wineTypeList;
    }

    public LiveData<List<EntityWineSender>> getWineSenderList(String filter) {
        wineRepository.setSelectedOptions(OPTION_SENDER,filter);
        return wineSenderList;
    }

    //#endregion

}
