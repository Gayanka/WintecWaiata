package com.example.wintecwaiata;


import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.VideoHolder> {
    private List<VideoListView> videoListViews = new ArrayList<>();
    private OnItemClickListener itemClickListener;

    public interface OnItemClickListener{
        void OnItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        itemClickListener = listener;
    }

    public class VideoHolder extends RecyclerView.ViewHolder{
        CardView mCardView;
        ImageView imageThumbnail;
        TextView textViewTitle;
        private int videoId;

        public VideoHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            mCardView = itemView.findViewById(R.id.cardView_cardview);
            imageThumbnail = itemView.findViewById(R.id.cardView1_thumbnail);
            textViewTitle = itemView.findViewById(R.id.cardView1_title);
            videoId = 0;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.OnItemClick(position);
                        }
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public VideoHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cardview_videoitem, viewGroup, false);
        VideoHolder vh = new VideoHolder(mView, itemClickListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull VideoHolder videoHolder, int i) {
        //videoHolder.imageThumbnail.setImageBitmap(videoList.get(i).imageItemThumbnail);
        VideoListView currentVideo = videoListViews.get(i);
        videoHolder.textViewTitle.setText(currentVideo.getTitle());
        try {
            String filename = videoListViews.get(i).getFilename();
            if (filename.contains(".")) {
                filename = filename.substring(0, filename.indexOf("."));
            }
            Field field = R.drawable.class.getField(filename);
            videoHolder.imageThumbnail.setImageResource(field.getInt(null));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        videoHolder.videoId = currentVideo.getId();
    }

    @Override
    public int getItemCount() {
        return videoListViews.size();
    }

    public void setVideoList(List<VideoListView> videoListView) {
        this.videoListViews = videoListView;
        notifyDataSetChanged();
    }

}
