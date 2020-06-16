package com.example.wintecwaiata;

import android.graphics.BitmapFactory;
import android.text.Html;
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

public class CarvingDescriptionAdapter extends RecyclerView.Adapter<CarvingDescriptionAdapter.CarvingDescriptionHolder> {
    private List<CarvingDescriptionView> carvingDescriptionViewList = new ArrayList<>();

    @NonNull
    @Override
    public CarvingDescriptionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.carving_with_description, parent, false);
        return new CarvingDescriptionHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CarvingDescriptionHolder holder, int position) {
        CarvingDescriptionView currentDescription = carvingDescriptionViewList.get(position);
        holder.textViewDescription.setText(Html.fromHtml(currentDescription.getDescription()));
        try {
            String filename = currentDescription.getFilename();
            if (filename.contains(".")) {
                filename = filename.substring(0, filename.indexOf("."));
            }
            Field field = R.drawable.class.getField(filename);
            holder.imageViewCarvingDescription.setImageResource(field.getInt(null));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void setCarvingDescriptionViewList(List<CarvingDescriptionView> carvingDescriptionViewList) {
        this.carvingDescriptionViewList = carvingDescriptionViewList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return carvingDescriptionViewList.size();
    }

    class CarvingDescriptionHolder extends RecyclerView.ViewHolder {
        private TextView textViewDescription;
        private ImageView imageViewCarvingDescription;


        public CarvingDescriptionHolder(@NonNull View itemView) {
            super(itemView);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            imageViewCarvingDescription = itemView.findViewById(R.id.imageViewCarvingDescription);

        }
    }

}
