package com.example.wintecwaiata;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MareaFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_marea, container, false);
        TextView mainContent = view.findViewById(R.id.textView);
        mainContent.setText("Kia ora and welcome to Wintec Marae, Te Kōpū Mānia o Kirikiriroa.\n"
                + "\n" + "The marae complex takes its name from an area famous for its rich, fertile lands and gardens that linked the network of Waikato sub tribes (hapū) who lived along the banks of the Waikato River.\n"
                + "\n" + "The design of our marae is a contemporary version of traditional aspects with an emphasis on matauranga Māori (learning) and tikanga (customs and traditions). " +
                "Our marae is multipurpose, where students and staff can conduct and experience teaching, learning and pastoral support in a uniquely Māori environment.");
        return view;
    }
}
