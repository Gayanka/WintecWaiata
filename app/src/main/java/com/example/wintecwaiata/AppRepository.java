package com.example.wintecwaiata;

import android.app.Application;
import android.os.AsyncTask;

import androidx.room.Update;

import java.util.List;

public class AppRepository {
    // Tables
    private CarvingDao carvingDao;
    private CarvingDescriptionDao carvingDescriptionDao;
    private MultimediaDao multimediaDao;

    // Views
    private CarvingListViewDao carvingListViewDao;
    private CarvingDescriptionViewDao carvingDescriptionViewDao;

    // Data
    private List<CarvingListView> carvingListViews;
    //private List<CarvingDescriptionView> carvingDescriptionViews;

    public AppRepository(Application application) {
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        // Tables Dao
        carvingDao = appDatabase.carvingDao();
        carvingDescriptionDao = appDatabase.carvingDescriptionDao();
        multimediaDao = appDatabase.multimediaDao();

        // Views Dao
        carvingListViewDao = appDatabase.carvingListViewDao();
        carvingDescriptionViewDao = appDatabase.carvingDescriptionViewDao();

        // Data
        carvingListViews = carvingListViewDao.getCarvingList();
        //carvingDescriptionViews = carvingDescriptionViewDao.getDescription(0);
    }

    // Getters
    public List<CarvingListView> getCarvingListViews() {
        return carvingListViews;
    }

    public List<CarvingDescriptionView> getCarvingDescriptionView(int carvingId) {
        return carvingDescriptionViewDao.getDescription(carvingId);
    }

    // Insert, update and delete data
    // ---- Carving
    public void insert(Carving carving) {
        new InsertCarvingAsyncTask(carvingDao).execute(carving);
    }

    private static class InsertCarvingAsyncTask extends AsyncTask<Carving, Void, Void> {
        private CarvingDao carvingDao;

        public InsertCarvingAsyncTask(CarvingDao carvingDao) {
            this.carvingDao = carvingDao;
        }

        @Override
        protected Void doInBackground(Carving... carvings) {
            carvingDao.insert(carvings[0]);
            return null;
        }
    }

    public void update(Carving carving) {
        new UpdateCarvingAsyncTask(carvingDao).execute(carving);
    }

    private static class UpdateCarvingAsyncTask extends AsyncTask<Carving, Void, Void> {
        private CarvingDao carvingDao;

        public UpdateCarvingAsyncTask(CarvingDao carvingDao) {
            this.carvingDao = carvingDao;
        }

        @Override
        protected Void doInBackground(Carving... carvings) {
            carvingDao.update(carvings[0]);
            return null;
        }
    }

    public void delete(Carving carving) {
        new DeleteCarvingAsyncTask(carvingDao).execute(carving);
    }

    private static class DeleteCarvingAsyncTask extends AsyncTask<Carving, Void, Void> {
        private CarvingDao carvingDao;

        public DeleteCarvingAsyncTask(CarvingDao carvingDao) {
            this.carvingDao = carvingDao;
        }

        @Override
        protected Void doInBackground(Carving... carvings) {
            carvingDao.delete(carvings[0]);
            return null;
        }
    }

    // ---- CarvingDescription
    public void insert(CarvingDescription carvingDescription) {
        new InsertCarvingDescriptionAsyncTask(carvingDescriptionDao).execute(carvingDescription);
    }

    private static class InsertCarvingDescriptionAsyncTask extends AsyncTask<CarvingDescription, Void, Void> {
        private CarvingDescriptionDao carvingDescriptionDao;

        public InsertCarvingDescriptionAsyncTask(CarvingDescriptionDao carvingDescriptionDao) {
            this.carvingDescriptionDao = carvingDescriptionDao;
        }

        @Override
        protected Void doInBackground(CarvingDescription... carvingDescriptions) {
            carvingDescriptionDao.insert(carvingDescriptions[0]);
            return null;
        }
    }

    public void update(CarvingDescription carvingDescription) {
        new UpdateCarvingDescriptionAsyncTask(carvingDescriptionDao).execute(carvingDescription);
    }

    private static class UpdateCarvingDescriptionAsyncTask extends AsyncTask<CarvingDescription, Void, Void> {
        private CarvingDescriptionDao carvingDescriptionDao;

        public UpdateCarvingDescriptionAsyncTask(CarvingDescriptionDao carvingDescriptionDao) {
            this.carvingDescriptionDao = carvingDescriptionDao;
        }

        @Override
        protected Void doInBackground(CarvingDescription... carvingDescriptions) {
            carvingDescriptionDao.update(carvingDescriptions[0]);
            return null;
        }
    }

    public void delete (CarvingDescription carvingDescription) {
        new DeleteCarvingDescriptionAsyncTask(carvingDescriptionDao).execute(carvingDescription);
    }

    private static class DeleteCarvingDescriptionAsyncTask extends AsyncTask<CarvingDescription, Void, Void> {
        private CarvingDescriptionDao carvingDescriptionDao;

        public DeleteCarvingDescriptionAsyncTask(CarvingDescriptionDao carvingDescriptionDao) {
            this.carvingDescriptionDao = carvingDescriptionDao;
        }

        @Override
        protected Void doInBackground(CarvingDescription... carvingDescriptions) {
            carvingDescriptionDao.delete(carvingDescriptions[0]);
            return null;
        }
    }

    // ---- Multimedia
    public void insert(Multimedia multimedia) {
        new InsertMultimediaAsyncTask(multimediaDao).execute(multimedia);
    }

    private static class InsertMultimediaAsyncTask extends AsyncTask<Multimedia, Void, Void> {
        private MultimediaDao multimediaDao;

        public InsertMultimediaAsyncTask(MultimediaDao multimediaDao) {
            this.multimediaDao = multimediaDao;
        }

        @Override
        protected Void doInBackground(Multimedia... multimedia) {
            multimediaDao.insert(multimedia[0]);
            return null;
        }
    }

    public void update(Multimedia multimedia) {
        new UpdateMultimediaAsyncTask(multimediaDao).execute(multimedia);
    }

    private static class UpdateMultimediaAsyncTask extends AsyncTask<Multimedia, Void, Void> {
        private MultimediaDao multimediaDao;

        public UpdateMultimediaAsyncTask(MultimediaDao multimediaDao) {
            this.multimediaDao = multimediaDao;
        }

        @Override
        protected Void doInBackground(Multimedia... multimedia) {
            multimediaDao.update(multimedia[0]);
            return null;
        }
    }

    public void delete(Multimedia multimedia) {
        new DeleteMultimediaAsyncTask(multimediaDao).execute(multimedia);
    }

    private static class DeleteMultimediaAsyncTask extends AsyncTask<Multimedia, Void, Void> {
        private MultimediaDao multimediaDao;

        public DeleteMultimediaAsyncTask(MultimediaDao multimediaDao) {
            this.multimediaDao = multimediaDao;
        }

        @Override
        protected Void doInBackground(Multimedia... multimedia) {
            multimediaDao.delete(multimedia[0]);
            return null;
        }
    }





}
