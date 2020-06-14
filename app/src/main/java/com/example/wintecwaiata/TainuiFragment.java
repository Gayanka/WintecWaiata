package com.example.wintecwaiata;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


public class TainuiFragment extends Fragment {
    private ExternalLinkViewModel externalLinkViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tainui, container, false);
        setHasOptionsMenu(true);
        TextView textView = v.findViewById(R.id.textTainui);
        textView.setText(Html.fromHtml("<html>\n" +
                "<body>\n" +
                "<p>We maintain a close relationship\n" +
                "with Tainui and are committed to enhancing the educational experience and\n" +
                "outcomes for M&#257;ori in the Waikato.</p>\n" +
                "<p>We have a number of key positions\n" +
                "and initiatives to ensure we meet the educational and training aspirations of\n" +
                "wh&#257;nau, hap&#363;, iwi and M&#257;ori community organisations in our\n" +
                "region:</p>\n" +
                "<p><span><span>\n" +
                "</span></span>Kaum&#257;tua - advisor to the Council and CE</p>\n" +
                "<p><span><span>\n" +
                "</span></span>Director M&#257;ori - executive committee member</p>\n" +
                "<p><span><span>\n" +
                "</span></span>Scholarships - for both M&#257;ori students and staff</p>\n" +
                "<p><span><span>\n" +
                "</span></span>M&#257;ori Trades Training - a joint initiative with Te W&#257;nanga o Aotearoa and Tainui</p>\n" +
                "<p><span><span>\n" +
                "</span></span>The land - a history of the land on which our city campus sits</p>\n" +
                "</body>\n" +
                "</html>\n"));
        externalLinkViewModel = new ViewModelProvider(this, new ExternalLinkFactory(this.getActivity().getApplication())).get(ExternalLinkViewModel.class);

        return v;
    }

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

    private void openInBrowser(String link){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        startActivity(browserIntent);
    }
}