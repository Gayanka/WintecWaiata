package com.example.wintecwaiata;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import java.util.List;

import static com.example.wintecwaiata.CarvingListFragment.*;

public class CarvingDescriptionActivity extends AppCompatActivity {
    private CarvingListViewModel carvingListViewModel;
    private int id;
    private String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carving_description);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));

        id = getIntent().getIntExtra(CARVING_ID_NAME, 0);
        title = getIntent().getStringExtra(CARVING_TITLE_NAME);
        if (title.indexOf("(") != -1) {
            getSupportActionBar().setTitle(title.substring(0, title.indexOf("(") - 1));
        } else {
            getSupportActionBar().setTitle(title);
        }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.open_in_browser, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_open_in_browser:
                carvingListViewModel.getExternalLink(this.getClass().getSimpleName(), id).observe(this, new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        Uri uri = Uri.parse(s);
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                });
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
