package com.example.wintecwaiata;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import static com.example.wintecwaiata.CarvingListFragment.*;

public class CarvingDescriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carving_description);
        int id = getIntent().getIntExtra(CARVING_ID_NAME, 0);
        TextView tv = findViewById(R.id.textViewID);
        tv.setText("ID: " + id);
    }
}
