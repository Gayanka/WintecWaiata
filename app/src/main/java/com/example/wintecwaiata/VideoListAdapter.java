package com.example.wintecwaiata;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.VideoHolder> {
    private ArrayList<VideoItem> videoList;
    private OnItemClickListener itemClickListener;

    public interface OnItemClickListener{
        void OnItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        itemClickListener = listener;
    }

    public VideoListAdapter(ArrayList<VideoItem> videoList) {
        this.videoList = videoList;
    }

    public class VideoHolder extends RecyclerView.ViewHolder{
        CardView mCardView;
        ImageView imageThumbnail;
        TextView textViewFileName;

        public VideoHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            mCardView = itemView.findViewById(R.id.cardView_cardview);
            imageThumbnail = itemView.findViewById(R.id.cardView1_thumbnail);
            textViewFileName = itemView.findViewById(R.id.cardView1_title);

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
        videoHolder.imageThumbnail.setImageBitmap(videoList.get(i).imageItemThumbnail);
        videoHolder.textViewFileName.setText(videoList.get(i).videoTitle);
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

}
