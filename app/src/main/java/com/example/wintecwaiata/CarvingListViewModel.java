package com.example.wintecwaiata;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CarvingListViewModel extends AndroidViewModel {
    private AppRepository appRepository;
    private LiveData<List<CarvingListView>> carvingList;

    public CarvingListViewModel(@NonNull Application application) {
        super(application);
        appRepository = new AppRepository(application);
        carvingList = appRepository.getCarvingListViews();
    }

    public void insert(Carving carving) {
        appRepository.insert(carving);
    }

    public void insert(CarvingDescription carvingDescription) {
        appRepository.insert(carvingDescription);
    }

    public void  insert(Multimedia multimedia) {
        appRepository.insert(multimedia);
    }

    public void update(Carving carving) {
        appRepository.update(carving);
    }

    public void update(CarvingDescription carvingDescription) {
        appRepository.update(carvingDescription);
    }

    public  void update(Multimedia multimedia) {
        appRepository.update(multimedia);
    }

    public void delete(Carving carving) {
        appRepository.delete(carving);
    }

    public void delete(CarvingDescription carvingDescription) {
        appRepository.delete(carvingDescription);
    }

    public void delete(Multimedia multimedia) {
        appRepository.delete(multimedia);
    }

    public LiveData<List<CarvingListView>> getCarvingListView() {
        return carvingList;
    }

    public LiveData<List<CarvingDescriptionView>> getCarvingDescriptionView(int carvingId) {
        return appRepository.getCarvingDescriptionView(carvingId);
    }

    public LiveData<String> getExternalLink(String activityName) {
        return appRepository.getExternalLink(activityName);
    }

    public LiveData<String> getExternalLink(String activityName, int carvingId) {
        return appRepository.getExternalLink(activityName, carvingId);
    }
}
