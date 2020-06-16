package com.example.wintecwaiata;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class CarvingListViewModelFactory implements ViewModelProvider.Factory {
    private Application application;

    public CarvingListViewModelFactory(Application application) {
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new CarvingListViewModel(application);
    }
}
