package com.example.wintecwaiata;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Update;

import java.util.List;

public class AppRepository {
    // Tables
    private CarvingDao carvingDao;
    private CarvingDescriptionDao carvingDescriptionDao;
    private MultimediaDao multimediaDao;
    private VideoContentDao videoContentDao;
    private VideoContentDetailsDao videoContentDetailsDao;

    // Views
    private CarvingListViewDao carvingListViewDao;
    private CarvingDescriptionViewDao carvingDescriptionViewDao;
    private VideoListViewDao videoListViewDao;
    private VideoDetailsViewDao videoDetailsViewDao;

    // Data
    private LiveData<List<CarvingListView>> carvingListViews;
    private LiveData<List<VideoListView>> videoListViews;


    public AppRepository(Application application) {
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        // Tables Dao
        carvingDao = appDatabase.carvingDao();
        carvingDescriptionDao = appDatabase.carvingDescriptionDao();
        multimediaDao = appDatabase.multimediaDao();
        videoContentDao = appDatabase.videoContentDao();
        videoContentDetailsDao = appDatabase.videoContentDetailsDao();

        // Views Dao
        carvingListViewDao = appDatabase.carvingListViewDao();
        carvingDescriptionViewDao = appDatabase.carvingDescriptionViewDao();
        videoListViewDao = appDatabase.videoListViewDao();
        videoDetailsViewDao = appDatabase.videoDetailsViewDao();

        // Data
        carvingListViews = carvingListViewDao.getCarvingList();
        videoListViews = videoListViewDao.getAllVideos();
    }

    // Getters
    public LiveData<List<CarvingListView>> getCarvingListViews() {
        return carvingListViews;
    }

    public LiveData<List<CarvingDescriptionView>> getCarvingDescriptionView(int carvingId) {
        return carvingDescriptionViewDao.getDescription(carvingId);
    }

    public LiveData<List<VideoListView>> getVideoListViews() {
        return videoListViews;
    }

    public LiveData<List<VideoDetailsView>> getVideoDetails(int videoId) {
        return videoDetailsViewDao.getVideoDetails(videoId);
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

    // ---- VideoContent
    public void insert(VideoContent videoContent) {
        new InsertVideoContentAsyncTask(videoContentDao).execute(videoContent);
    }

    private static class InsertVideoContentAsyncTask extends AsyncTask<VideoContent, Void, Void> {
        private VideoContentDao videoContentDao;

        public InsertVideoContentAsyncTask(VideoContentDao videoContentDao) {
            this.videoContentDao = videoContentDao;
        }

        @Override
        protected Void doInBackground(VideoContent... videoContents) {
            videoContentDao.insert(videoContents[0]);
            return null;
        }
    }

    public void update(VideoContent videoContent) {
        new UpdateVideoContentAsyncTask(videoContentDao).execute(videoContent);
    }

    private static class UpdateVideoContentAsyncTask extends AsyncTask<VideoContent, Void, Void> {
        private VideoContentDao videoContentDao;

        public UpdateVideoContentAsyncTask(VideoContentDao videoContentDao) {
            this.videoContentDao = videoContentDao;
        }

        @Override
        protected Void doInBackground(VideoContent... videoContents) {
            videoContentDao.update(videoContents[0]);
            return null;
        }
    }

    public void delete(VideoContent videoContent) {
        new DeleteVideoContentAsyncTask(videoContentDao).execute(videoContent);
    }

    private static class DeleteVideoContentAsyncTask extends AsyncTask<VideoContent, Void, Void> {
        private VideoContentDao videoContentDao;

        public DeleteVideoContentAsyncTask(VideoContentDao videoContentDao) {
            this.videoContentDao = videoContentDao;
        }

        @Override
        protected Void doInBackground(VideoContent... videoContents) {
            videoContentDao.delete(videoContents[0]);
            return null;
        }
    }

    // ---- VideoContentDetails
    public void insert(VideoContentDetails videoContentDetails) {
        new InsertVideoContentDetailsAsyncTask(videoContentDetailsDao).execute(videoContentDetails);
    }

    private static class InsertVideoContentDetailsAsyncTask extends AsyncTask<VideoContentDetails, Void, Void> {
        private VideoContentDetailsDao videoContentDetailsDao;

        public InsertVideoContentDetailsAsyncTask(VideoContentDetailsDao videoContentDetailsDao) {
            this.videoContentDetailsDao = videoContentDetailsDao;
        }

        @Override
        protected Void doInBackground(VideoContentDetails... videoContentsDetails) {
            videoContentDetailsDao.insert(videoContentsDetails[0]);
            return null;
        }
    }

    public void update(VideoContentDetails videoContentDetails) {
        new UpdateVideoContentDetailsAsyncTask(videoContentDetailsDao).execute(videoContentDetails);
    }

    private static class UpdateVideoContentDetailsAsyncTask extends AsyncTask<VideoContentDetails, Void, Void> {
        private VideoContentDetailsDao videoContentDetailsDao;

        public UpdateVideoContentDetailsAsyncTask(VideoContentDetailsDao videoContentDetailsDao) {
            this.videoContentDetailsDao = videoContentDetailsDao;
        }

        @Override
        protected Void doInBackground(VideoContentDetails... videoContentsDetails) {
            videoContentDetailsDao.update(videoContentsDetails[0]);
            return null;
        }
    }

    public void delete(VideoContentDetails videoContentDetails) {
        new DeleteVideoContentDetailsAsyncTask(videoContentDetailsDao).execute(videoContentDetails);
    }

    private static class DeleteVideoContentDetailsAsyncTask extends AsyncTask<VideoContentDetails, Void, Void> {
        private VideoContentDetailsDao videoContentDetailsDao;

        public DeleteVideoContentDetailsAsyncTask(VideoContentDetailsDao videoContentDetailsDao) {
            this.videoContentDetailsDao = videoContentDetailsDao;
        }

        @Override
        protected Void doInBackground(VideoContentDetails... videoContentsContentDetails) {
            videoContentDetailsDao.delete(videoContentsContentDetails[0]);
            return null;
        }
    }
}
