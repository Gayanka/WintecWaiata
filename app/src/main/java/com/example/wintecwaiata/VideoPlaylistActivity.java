package com.example.wintecwaiata;


import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

public class VideoPlaylistActivity extends AppCompatActivity {

    private ArrayList<VideoItem> videoItems;
    private RecyclerView recyclerView;
    private VideoListAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Bitmap> listBitmapThumbnail = new ArrayList<Bitmap>();
    ReadAllRawData readAllRawData = new ReadAllRawData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_playlist);

        createVideoThumbnail();
        initialiseData();
        buildRecyclerView();

    }

    private void initialiseData(){
        ArrayList<String> songName = new ArrayList<String>(Arrays.asList("E Kore Koe E Ngaro"
                , "He Maimai Aroha nā Tāwhiao", "Waikato Te Awa", "Tutira Mai Nga Iwi"
                , "Pupuke Te Hihiri", "I Te Whare Whakapiri", "Pua Te Kōwhai"));

        videoItems = new ArrayList<>();

        for (int i = 0 ; i < listBitmapThumbnail.size() ; i++){
            videoItems.add(new VideoItem(listBitmapThumbnail.get(i), songName.get(i)));
        }
    }

    private void buildRecyclerView(){
        recyclerView = this.findViewById(R.id.recyclerView_videolist);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new VideoListAdapter(videoItems);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new VideoListAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                Intent intent = new Intent(VideoPlaylistActivity.this, VideoPlayerActivity.class);
                intent.putExtra("videoCode", position);
                intent.putExtra("videoName", videoItems.get(position).getVideoTitle());

                startActivity(intent);
            }
        });
    }

    private void createVideoThumbnail(){
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        ArrayList<Uri> url = new ArrayList<>();

        try {
            readAllRawData.listRaw();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        url.add(Uri.parse(readAllRawData.listEkorekoe.get(0)));
        url.add(Uri.parse(readAllRawData.listHemaimaiaroha.get(0)));
        url.add(Uri.parse(readAllRawData.listWaikatoteawa.get(0)));
        url.add(Uri.parse(readAllRawData.listTutiramainga.get(0)));
        url.add(Uri.parse(readAllRawData.listpupuketehihiri.get(0)));
        url.add(Uri.parse(readAllRawData.listItewhare.get(0)));
        url.add(Uri.parse(readAllRawData.listPuatekowhai.get(0)));

        for (int i = 0 ; i < url.size() ; i++){
            mmr.setDataSource(getApplicationContext(), url.get(i));
            listBitmapThumbnail.add(mmr.getFrameAtTime(0));
        }
    }


}
