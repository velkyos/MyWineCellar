package com.velkyos.mywinecellar.Database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.velkyos.mywinecellar.Database.DAO.*;
import com.velkyos.mywinecellar.Database.DataEntity.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {EntityWine.class,
        EntityWineName.class,
        EntityWineType.class,
        EntityWineRegion.class,
        EntityWineMaker.class,
        EntityWineSender.class}, version = 1)
public abstract class WineDatabase extends RoomDatabase {

    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);


    private static WineDatabase instance;

    public abstract MasterDAO masterDAO();

    public static synchronized WineDatabase getInstance(Context context){
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    WineDatabase.class, "Wine_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    MasterDAO masterDAO = instance.masterDAO();
                    masterDAO.insert(new EntityWineName("A Définir"));
                    masterDAO.insert(new EntityWineType("A Définir"));
                    masterDAO.insert(new EntityWineRegion("A Définir"));
                    masterDAO.insert(new EntityWineMaker("A Définir"));
                    masterDAO.insert(new EntityWineSender("A Définir"));
                }
            });
           // new PopulateDbAsyncTask(instance).execute();
        }
    };



//    private static class PopulateDbAsyncTask extends AsyncTask <Void, Void, Void>{
//
//        private MasterDAO masterDAO;
//
//        private PopulateDbAsyncTask(WineDatabase db){
//            masterDAO = db.masterDAO();
//        }
//        @Override
//        protected Void doInBackground(Void... voids) {
//            masterDAO.insert(new EntityWineName("A Définir"));
//            masterDAO.insert(new EntityWineType("A Définir"));
//            masterDAO.insert(new EntityWineRegion("A Définir"));
//            masterDAO.insert(new EntityWineMaker("A Définir"));
//            masterDAO.insert(new EntityWineSender("A Définir"));
//
//            masterDAO.insert(new EntityWine(true,5,1,1,1895,1,1,15,1,1,5));
//            masterDAO.insert(new EntityWine(true,1,1,1,2405,1,1,142,1,1,4));
//            masterDAO.insert(new EntityWine(false,0,1,1,2016,1,1,28,1,1,5));
//            masterDAO.insert(new EntityWine(true,3,1,1,2034,1,1,96,1,1,0));
//            masterDAO.insert(new EntityWine(true,2,1,1,1654,1,1,1,1,1,1));
//            return null;
//        }
//    }

}
