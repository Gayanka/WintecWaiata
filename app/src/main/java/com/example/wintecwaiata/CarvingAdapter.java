package com.example.wintecwaiata;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class CarvingAdapter extends RecyclerView.Adapter<CarvingAdapter.CarvingHolder> {
    private List<CarvingListView> carvingListView = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener{
        void OnItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    class CarvingHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewPicturesNum;
        private ImageView imageViewCarvingPicture;
        private int carvingId;

        public CarvingHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textView_title);
            textViewPicturesNum = itemView.findViewById(R.id.textView_pictureNum);
            imageViewCarvingPicture = itemView.findViewById(R.id.imageView_carving);
            carvingId = 0;

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
    public CarvingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.carving_item, parent, false);
        return new CarvingHolder(itemView, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CarvingHolder holder, int position) {
        CarvingListView currentCarving = carvingListView.get(position);
        holder.textViewTitle.setText(currentCarving.getTitle());
        holder.textViewPicturesNum.setText(String.valueOf(currentCarving.getPicture_num()) + " pictures");
        holder.carvingId = currentCarving.getId();
        try {
            String filename = currentCarving.getFilename();
            if (filename.contains(".")) {
                filename = filename.substring(0, filename.indexOf("."));
            }
            Field field = R.raw.class.getField(filename);
            holder.imageViewCarvingPicture.setImageResource(field.getInt(null));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return carvingListView.size();
    }


    public void setCarvingList(List<CarvingListView> carvingListView) {
        this.carvingListView = carvingListView;
        notifyDataSetChanged();
    }


}
