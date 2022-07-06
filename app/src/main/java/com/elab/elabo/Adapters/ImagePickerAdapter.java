package com.elab.elabo.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.elab.elabo.Models.CategoriesModel;
import com.elab.elabo.Models.ImagePickerModel;
import com.elab.elabo.R;

import java.util.ArrayList;

public class ImagePickerAdapter extends RecyclerView.Adapter<ImagePickerAdapter.ImageViewHolder> {

    public interface OnItemClickListener{

        void onCardClicked(int pos);
    }

    public OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    private Context context;
    private ArrayList<ImagePickerModel> imagePickerModelArrayList;


    public ImagePickerAdapter(Context context, ArrayList<ImagePickerModel> imagePickerModelArrayList) {
        this.context = context;
        this.imagePickerModelArrayList = imagePickerModelArrayList;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.img_picker_adpt_lyt, parent, false);

        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {

        ImagePickerModel imagePickerModel = imagePickerModelArrayList.get(position);

        holder.img.setImageResource(imagePickerModel.getImg());

        if (imagePickerModel.isChecked()){

            holder.checkImg.setVisibility(View.VISIBLE);

        }else{

            holder.checkImg.setVisibility(View.GONE);

        }

    }

    @Override
    public int getItemCount() {
        return imagePickerModelArrayList.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder{

        private CardView cardView;
        private AppCompatImageView img,checkImg;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.main_container);
            img = itemView.findViewById(R.id.cat_img);
            checkImg = itemView.findViewById(R.id.check_img);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int p = getAdapterPosition();

                    if (onItemClickListener != null && p != RecyclerView.NO_POSITION){

                        onItemClickListener.onCardClicked(p);
                    }
                }
            });
        }
    }
}
