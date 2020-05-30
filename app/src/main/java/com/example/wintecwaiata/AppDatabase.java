package com.example.wintecwaiata;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

public abstract class AppDatabase extends RoomDatabase {
    private static  final String DATABASE_NAME = "app_database";
    private static AppDatabase instance;

    public abstract CarvingDao carvingDao();
    public abstract CarvingDescriptionDao carvingDescriptionDao();
    public abstract MultimediaDao multimediaDao();
    public abstract CarvingDescriptionViewDao carvingDescriptionViewDao();
    public abstract CarvingListViewDao carvingListViewDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, DATABASE_NAME).fallbackToDestructiveMigration().addCallback(roomCallback).build();
        }
        return instance;
    }

    public static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private CarvingDao carvingDao;
        private CarvingDescriptionDao carvingDescriptionDao;
        private MultimediaDao multimediaDao;

        public PopulateDbAsyncTask(AppDatabase appDatabase) {
            this.carvingDao = appDatabase.carvingDao();
            this.carvingDescriptionDao = appDatabase.carvingDescriptionDao();
            this.multimediaDao = appDatabase.multimediaDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            multimediaDao.insert(new Multimedia("gateway_entrance_01.jpg"));
            multimediaDao.insert(new Multimedia("gateway_entrance_02.jpg"));
            multimediaDao.insert(new Multimedia("internal_wharenui_post_01.jpg"));
            multimediaDao.insert(new Multimedia("internal_wharenui_post_02.png"));
            multimediaDao.insert(new Multimedia("memorial_pillar_01.jpg"));
            multimediaDao.insert(new Multimedia("pillars_01.jpg"));
            multimediaDao.insert(new Multimedia("doorway_lintels_01.png"));
            multimediaDao.insert(new Multimedia("doorway_lintels_02.jpg"));
            multimediaDao.insert(new Multimedia("window_lintel_01.jpg"));
            multimediaDao.insert(new Multimedia("window_lintel_02.jpg"));
            multimediaDao.insert(new Multimedia("memorial_pillar_02.jpg"));
            multimediaDao.insert(new Multimedia("pillars_02.jpg"));

            carvingDao.insert(new Carving("Waka Maumahara (Memorial Pillar)", 5, 1));
            carvingDao.insert(new Carving("Pou Whakarae (Pillars)", 6, 2));
            carvingDao.insert(new Carving("Pou-tūā-rangi (Internal Wharenui Post)", 4, 3));
            carvingDao.insert(new Carving("Pou-tūā-rongo - Tawhaki - Internal Wharenui Post", 3, 4));
            carvingDao.insert(new Carving("Tomokanga (Gateway Entrance)", 1, 5));
            carvingDao.insert(new Carving("Pare and Whakawae (Doorway Lintels)", 7, 6));
            carvingDao.insert(new Carving("Kōrupe (Window Lintel)", 9, 7));

            carvingDescriptionDao.insert(new CarvingDescription(1, "Description 1", 5));
            carvingDescriptionDao.insert(new CarvingDescription(1, "Description 2", 11));
            carvingDescriptionDao.insert(new CarvingDescription(2, "Description 1", 6));
            carvingDescriptionDao.insert(new CarvingDescription(2, "Description 2", 12));
            carvingDescriptionDao.insert(new CarvingDescription(3, "Description 1", 4));
            carvingDescriptionDao.insert(new CarvingDescription(4, "Description 1", 3));
            carvingDescriptionDao.insert(new CarvingDescription(5, "Description 1", 1));
            carvingDescriptionDao.insert(new CarvingDescription(5, "Description 2", 2));
            carvingDescriptionDao.insert(new CarvingDescription(6, "Description 1", 7));
            carvingDescriptionDao.insert(new CarvingDescription(6, "Description 2", 8));
            carvingDescriptionDao.insert(new CarvingDescription(7, "Description 1", 9));
            carvingDescriptionDao.insert(new CarvingDescription(7, "Description 2", 10));
            return null;
        }
    }


}
