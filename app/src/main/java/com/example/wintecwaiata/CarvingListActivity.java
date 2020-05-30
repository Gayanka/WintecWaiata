package com.example.wintecwaiata;


import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.List;

public class CarvingListActivity extends AppCompatActivity {
    private CarvingListViewModel carvingListViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carving_list);

        carvingListViewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(CarvingListViewModel.class);
        List<CarvingListView> data = carvingListViewModel.getCarvingListView();
        Toast.makeText(this, "" + data.size(), Toast.LENGTH_SHORT).show();

    }
}
