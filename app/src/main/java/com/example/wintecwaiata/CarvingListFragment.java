package com.example.wintecwaiata;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;


public class CarvingListFragment extends Fragment {
    private CarvingListViewModel carvingListViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        carvingListViewModel = new ViewModelProvider(this, new CarvingListViewModelFactory(this.getActivity().getApplication())).get(CarvingListViewModel.class);
        carvingListViewModel.getCarvingListView().observe(this, new Observer<List<CarvingListView>>() {
            @Override
            public void onChanged(List<CarvingListView> carvingListViews) {
                int a = carvingListViews.size();
            }
        });
        return inflater.inflate(R.layout.activity_carving_list, container, false);


    }

}
