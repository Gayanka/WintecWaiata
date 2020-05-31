package com.example.wintecwaiata;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;


public class CarvingListFragment extends Fragment {

    public static final String CARVING_ID_NAME = "carving_id";
    private CarvingListViewModel carvingListViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.activity_carving_list, container, false);
        RecyclerView recyclerView = v.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setHasFixedSize(true);

        final CarvingAdapter carvingAdapter = new CarvingAdapter();
        recyclerView.setAdapter(carvingAdapter);

        carvingListViewModel = new ViewModelProvider(this, new CarvingListViewModelFactory(this.getActivity().getApplication())).get(CarvingListViewModel.class);
        carvingListViewModel.getCarvingListView().observe(this, new Observer<List<CarvingListView>>() {
            @Override
            public void onChanged(final List<CarvingListView> carvingListViews) {
                carvingAdapter.setCarvingList(carvingListViews);

                carvingAdapter.setOnItemClickListener(new CarvingAdapter.OnItemClickListener() {
                    @Override
                    public void OnItemClick(int position) {
                        Intent intent = new Intent(v.getContext(), CarvingDescriptionActivity.class);
                        intent.putExtra(CARVING_ID_NAME, carvingListViews.get(position).getId());
                        startActivity(intent);
                    }
                });
            }
        });
        return v;
    }

}
