package com.example.wintecwaiata;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class ExternalLinkViewModel extends AndroidViewModel {
    private AppRepository appRepository;
    private LiveData<String> externalLink;

    public ExternalLinkViewModel(@NonNull Application application) {
        super(application);
        appRepository = new AppRepository(application);
    }

    public LiveData<String> getExternalLink(String activityName) {
        return appRepository.getExternalLink(activityName);
    }

    public LiveData<String> getExternalLink(String activityName, int carvingId) {
        return appRepository.getExternalLink(activityName, carvingId);
    }
}
