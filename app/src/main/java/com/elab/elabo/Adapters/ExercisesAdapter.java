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
import com.elab.elabo.Models.ExercisesModel;
import com.elab.elabo.R;

import java.util.ArrayList;

public class ExercisesAdapter extends RecyclerView.Adapter<ExercisesAdapter.ExercisesViewHolder> {

    public interface OnItemClickListener{

        void onCardClicked(int pos);
        void onEditClicked(int pos);
        void onDeleteClicked(int pos);

    }

    public OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    private Context context;
    private ArrayList<ExercisesModel> exercisesModelArrayList;


    public ExercisesAdapter(Context context, ArrayList<ExercisesModel> exercisesModelArrayList) {
        this.context = context;
        this.exercisesModelArrayList = exercisesModelArrayList;
    }

    @NonNull
    @Override
    public ExercisesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.exr_adpt_lyt, parent, false);

        return new ExercisesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExercisesViewHolder holder, int position) {

        ExercisesModel exercisesModel = exercisesModelArrayList.get(position);

        holder.img.setImageResource(exercisesModel.getImg());
        holder.title.setText(exercisesModel.getTitle());
        holder.desc.setText(exercisesModel.getDescription());

    }

    @Override
    public int getItemCount() {
        return exercisesModelArrayList.size();
    }

    public class ExercisesViewHolder extends RecyclerView.ViewHolder{

        private CardView cardView;
        private AppCompatImageView img;
        private AppCompatTextView title,desc;
        private AppCompatImageView editImg,delImg;

        public ExercisesViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.main_container);
            img = itemView.findViewById(R.id.cat_img);
            title = itemView.findViewById(R.id.exr_title);
            desc = itemView.findViewById(R.id.desc);
            editImg = itemView.findViewById(R.id.edit_img);
            delImg = itemView.findViewById(R.id.del_img);


            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int p = getAdapterPosition();

                    if (onItemClickListener != null && p != RecyclerView.NO_POSITION){

                        onItemClickListener.onCardClicked(p);
                    }
                }
            });

            editImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int p = getAdapterPosition();

                    if (onItemClickListener != null && p != RecyclerView.NO_POSITION){

                        onItemClickListener.onEditClicked(p);
                    }
                }
            });

            delImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int p = getAdapterPosition();

                    if (onItemClickListener != null && p != RecyclerView.NO_POSITION){

                        onItemClickListener.onDeleteClicked(p);
                    }
                }
            });
        }
    }
}
