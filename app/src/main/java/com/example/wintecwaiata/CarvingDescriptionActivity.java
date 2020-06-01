package com.example.wintecwaiata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import java.util.List;

import static com.example.wintecwaiata.CarvingListFragment.*;

public class CarvingDescriptionActivity extends AppCompatActivity {
    private CarvingListViewModel carvingListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carving_description);
        int id = getIntent().getIntExtra(CARVING_ID_NAME, 0);
        RecyclerView recyclerView = findViewById(R.id.recycler_view_desc);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final CarvingDescriptionAdapter carvingDescriptionAdapter = new CarvingDescriptionAdapter();
        recyclerView.setAdapter(carvingDescriptionAdapter);

        carvingListViewModel = new ViewModelProvider(this, new CarvingListViewModelFactory(this.getApplication())).get(CarvingListViewModel.class);
        carvingListViewModel.getCarvingDescriptionView(id).observe(this, new Observer<List<CarvingDescriptionView>>() {
            @Override
            public void onChanged(List<CarvingDescriptionView> carvingDescriptionViews) {
                carvingDescriptionAdapter.setCarvingDescriptionViewList(carvingDescriptionViews);
            }
        });

    }
}
