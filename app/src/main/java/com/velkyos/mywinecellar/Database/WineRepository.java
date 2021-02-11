package com.velkyos.mywinecellar.Database;

import android.app.Application;

import android.os.AsyncTask;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.velkyos.mywinecellar.Database.DataEntity.*;
import com.velkyos.mywinecellar.Database.DAO.*;
import com.velkyos.mywinecellar.Database.DataEntity.Wine;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class WineRepository {

    private static final String OPTION_NAME = "name";
    private static final String OPTION_TYPE = "type";
    private static final String OPTION_MAKER = "maker";
    private static final String OPTION_REGION = "region";
    private static final String OPTION_SENDER = "sender";

    private MasterDAO masterDAO;

    private LiveData<List<Wine>> selectedWine;
    private MutableLiveData<Integer> selectWineId = new MutableLiveData<>();

    private LiveData<List<Wine>> wineList;

    private String statsOption;
    private MutableLiveData<String> statsFilter = new MutableLiveData<>();
    private LiveData<List<EntityWineName>> wineNameList;
    private LiveData<List<EntityWineMaker>> wineMakerList;
    private LiveData<List<EntityWineRegion>> wineRegionList;
    private LiveData<List<EntityWineType>> wineTypeList;
    private LiveData<List<EntityWineSender>> wineSenderList;



    public WineRepository(Application application){
        WineDatabase wineDatabase = WineDatabase.getInstance(application);

        masterDAO = wineDatabase.masterDAO();

        wineList = masterDAO.selectAllWinesInCavesWithStrings();

        wineNameList = Transformations.switchMap(statsFilter, new Function<String, LiveData<List<EntityWineName>>>() {
            @Override
            public LiveData<List<EntityWineName>> apply(String input) {
                if (statsOption == OPTION_NAME){
                return masterDAO.getAllWineNames(input);}else{return null;}
            }
        });

        wineMakerList = Transformations.switchMap(statsFilter, new Function<String, LiveData<List<EntityWineMaker>>>() {
            @Override
            public LiveData<List<EntityWineMaker>> apply(String input) {
                if (statsOption == OPTION_MAKER){
                    return masterDAO.getAllWineMakers(input);}else{return null;}
            }
        });

        wineRegionList = Transformations.switchMap(statsFilter, new Function<String, LiveData<List<EntityWineRegion>>>() {
            @Override
            public LiveData<List<EntityWineRegion>> apply(String input) {
                if (statsOption == OPTION_REGION){
                    return masterDAO.getAllWineRegions(input);}else{return null;}
            }
        });

        wineSenderList = Transformations.switchMap(statsFilter, new Function<String, LiveData<List<EntityWineSender>>>() {
            @Override
            public LiveData<List<EntityWineSender>> apply(String input) {
                if (statsOption == OPTION_SENDER){
                    return masterDAO.getAllWineSenders(input);}else{return null;}
            }
        });

        wineTypeList = Transformations.switchMap(statsFilter, new Function<String, LiveData<List<EntityWineType>>>() {
            @Override
            public LiveData<List<EntityWineType>> apply(String input) {
                if (statsOption == OPTION_TYPE){
                    return masterDAO.getAllWineTypes(input);}else{return null;}
            }
        });


        selectedWine = Transformations.switchMap( selectWineId, new Function<Integer, LiveData<List<Wine>> >() {
            @Override
            public LiveData<List<Wine>>  apply(Integer v) {
                return masterDAO.selectWineById(v);
            }
        });
    }


    //#region Edit
    public void removeBottleFromCave(final int id){
        WineDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                masterDAO.removeBottleFromCave(id);
                masterDAO.removeWinesFromCave(id);
            }
        });
    }
    //endregion

    //#region Options
    public void setSelectedWineId(int id){ selectWineId.setValue(id);}

    public void setSelectedOptions(String option, String filter){
        statsOption = option;
        statsFilter.setValue( "%" + filter + "%");
    }
    //endregion


    //#region Select
    public LiveData<List<Wine>>  getSelectedWine(){
        return selectedWine;
    }

    public LiveData<List<Wine>> getWinesForView(){
        return wineList;
    }
    
    public LiveData<List<EntityWineName>> getWineNames(){
        return wineNameList;
    }

    public LiveData<List<EntityWineRegion>> getWineRegions(){
        return wineRegionList;
    }

    public LiveData<List<EntityWineType>> getWineTypes(){
        return wineTypeList;
    }

    public LiveData<List<EntityWineMaker>> getWineMakers(){
        return wineMakerList;
    }

    public LiveData<List<EntityWineSender>> getWineSenders(){
        return wineSenderList;
    }

    //endregion

    //#region Insert
    public int insert(final EntityWine entityWine){
        int value = 0;
        try {
            value = WineDatabase.databaseWriteExecutor.submit(new Callable<Integer>() {
                @Override
                public Integer call() {
                    return (int) masterDAO.insert(entityWine);
                }
            }).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return value;
    }

    public int insert(final EntityWineName entityWineName){
        int value = 0;
        try {
            value = WineDatabase.databaseWriteExecutor.submit(new Callable<Integer>() {
                @Override
                public Integer call() {
                    return (int) masterDAO.insert(entityWineName);
                }
            }).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return value;
    }

    public int insert(final EntityWineRegion entityWineRegion){
        int value = 0;
        try {
            value = WineDatabase.databaseWriteExecutor.submit(new Callable<Integer>() {
                @Override
                public Integer call() {
                    return (int) masterDAO.insert(entityWineRegion);
                }
            }).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return value;
    }

    public int insert(final EntityWineType entityWineType){
        int value = 0;
        try {
            value = WineDatabase.databaseWriteExecutor.submit(new Callable<Integer>() {
                @Override
                public Integer call() {
                    return (int) masterDAO.insert(entityWineType);
                }
            }).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return value;
    }

    public int insert(final EntityWineMaker entityWineMaker){
        int value = 0;
        try {
            value = WineDatabase.databaseWriteExecutor.submit(new Callable<Integer>() {
                @Override
                public Integer call() {
                    return (int) masterDAO.insert(entityWineMaker);
                }
            }).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return value;
    }

    public int insert(final EntityWineSender entityWineSender){
        int value = 0;
        try {
            value = WineDatabase.databaseWriteExecutor.submit(new Callable<Integer>() {
                @Override
                public Integer call() {
                    return (int) masterDAO.insert(entityWineSender);
                }
            }).get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        return value;
    }
    //endregion
    
    //#region Update
    public void update(final EntityWine entityWine){
            WineDatabase.databaseWriteExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    masterDAO.update(entityWine);
                }
            });
        }

    public void update(final EntityWineName entityWineName){
        WineDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                masterDAO.update(entityWineName);
            }
        });
    }

    public void update(final EntityWineRegion entityWineRegion){
        WineDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                masterDAO.update(entityWineRegion);
            }
        });
    }

    public void update(final EntityWineType entityWineType){
        WineDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                masterDAO.update(entityWineType);
            }
        });
    }

    public void update(final EntityWineMaker entityWineMaker){
        WineDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                masterDAO.update(entityWineMaker);
            }
        });
    }

    public void update(final EntityWineSender entityWineSender){
        WineDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                masterDAO.update(entityWineSender);
            }
        });
    }

    //endregion

    //#region Delete
    public void delete(final EntityWine entityWine){
        WineDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                masterDAO.delete(entityWine);
            }
        });
    }

    public void delete(final EntityWineName entityWineName){
        WineDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                masterDAO.delete(entityWineName);
            }
        });
    }

    public void delete(final EntityWineRegion entityWineRegion){
        WineDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                masterDAO.delete(entityWineRegion);
            }
        });
    }

    public void delete(final EntityWineType entityWineType){
        WineDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                masterDAO.delete(entityWineType);
            }
        });
    }

    public void delete(final EntityWineMaker entityWineMaker){
        WineDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                masterDAO.delete(entityWineMaker);
            }
        });
    }

    public void delete(final EntityWineSender entityWineSender){
        WineDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                masterDAO.delete(entityWineSender);
            }
        });
    }
    //endregion
    
    //#region Delete All List
//    public void deleteAllWines(){
//        new DeleteAllListAsyncTask(masterDAO).execute("wines");
//    }
//    public void deleteAllWineNames(){
//        new DeleteAllListAsyncTask(masterDAO).execute("names");
//    }
//    public void deleteAllWineRegions(){
//        new DeleteAllListAsyncTask(masterDAO).execute("regions");
//    }
//    public void deleteAllWineTypes(){
//        new DeleteAllListAsyncTask(masterDAO).execute("types");
//    }
//    public void deleteAllWineMakers(){
//        new DeleteAllListAsyncTask(masterDAO).execute("makers");
//    }
//    public void deleteAllWineSenders(){
//        new DeleteAllListAsyncTask(masterDAO).execute("senders");
//    }
//
//        public static class DeleteAllListAsyncTask extends AsyncTask<String, Void, Void>{
//            private final MasterDAO masterDAO;
//
//            public DeleteAllListAsyncTask(MasterDAO masterDAO){
//                this.masterDAO = masterDAO;
//            }
//
//            @Override
//            protected Void doInBackground(String... strings) {
//                switch (strings[0]){
//                    case "wines": masterDAO.deleteWineList();
//                        break;
//                    case "names": masterDAO.deleteNameList();
//                        break;
//                    case "regions": masterDAO.deleteRegionList();
//                        break;
//                    case "types": masterDAO.deleteTypeList();
//                        break;
//                    case "makers": masterDAO.deleteMakerList();
//                        break;
//                    case "senders": masterDAO.deleteSenderList();
//                        break;
//                }
//                return null;
//            }
//        }

    //endregion

}
