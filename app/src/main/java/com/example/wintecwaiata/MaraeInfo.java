package com.example.wintecwaiata;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MaraeInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marae_info);
        TextView mainContent = findViewById(R.id.textView);
        mainContent.setText("Kia ora and welcome to Wintec Marae, Te Kōpū Mānia o Kirikiriroa.\n"
                + "\n" + "The marae complex takes its name from an area famous for its rich, fertile lands and gardens that linked the network of Waikato sub tribes (hapū) who lived along the banks of the Waikato River.\n"
                + "\n" + "The design of our marae is a contemporary version of traditional aspects with an emphasis on matauranga Māori (learning) and tikanga (customs and traditions). " +
                "Our marae is multipurpose, where students and staff can conduct and experience teaching, learning and pastoral support in a uniquely Māori environment.");
    }
}
