package com.example.wintecwaiata;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class MareaFragment extends Fragment {
    private ExternalLinkViewModel externalLinkViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_marea, container, false);
        TextView mainContent = view.findViewById(R.id.textView);
        setHasOptionsMenu(true);
        mainContent.setText("Kia ora and welcome to Wintec Marae, Te Kōpū Mānia o Kirikiriroa.\n"
                + "\n" + "The marae complex takes its name from an area famous for its rich, fertile lands and gardens that linked the network of Waikato sub tribes (hapū) who lived along the banks of the Waikato River.\n"
                + "\n" + "The design of our marae is a contemporary version of traditional aspects with an emphasis on matauranga Māori (learning) and tikanga (customs and traditions). " +
                "Our marae is multipurpose, where students and staff can conduct and experience teaching, learning and pastoral support in a uniquely Māori environment.");

        externalLinkViewModel = new ViewModelProvider(this, new ExternalLinkFactory(this.getActivity().getApplication())).get(ExternalLinkViewModel.class);
        return view;
    }

    //https://www.wintec.ac.nz/about-wintec/māori-and-pasifika/wintec-marae

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.open_in_browser, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_open_in_browser) {
            externalLinkViewModel.getExternalLink(this.getClass().getSimpleName()).observe(this, new Observer<String>() {
                @Override
                public void onChanged(String s) {
                    Uri uri = Uri.parse(s);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
            });
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
