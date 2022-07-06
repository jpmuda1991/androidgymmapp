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
import com.elab.elabo.R;

import java.util.ArrayList;

public class CatAdapter extends RecyclerView.Adapter<CatAdapter.CatViewHolder> {

    public interface OnItemClickListener{

        void onCardClicked(int pos);
    }

    public OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    private Context context;
    private ArrayList<CategoriesModel> categoriesModelArrayList;


    public CatAdapter(Context context, ArrayList<CategoriesModel> categoriesModelArrayList) {
        this.context = context;
        this.categoriesModelArrayList = categoriesModelArrayList;
    }

    @NonNull
    @Override
    public CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.categories_adpt_lyt, parent, false);

        return new CatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CatViewHolder holder, int position) {

        CategoriesModel categoriesModel = categoriesModelArrayList.get(position);

        holder.img.setImageResource(categoriesModel.getImg());
        holder.title.setText(categoriesModel.getTitle());

    }

    @Override
    public int getItemCount() {
        return categoriesModelArrayList.size();
    }

    public class CatViewHolder extends RecyclerView.ViewHolder{

        private CardView cardView;
        private AppCompatImageView img;
        private AppCompatTextView title;

        public CatViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.main_container);
            img = itemView.findViewById(R.id.cat_img);
            title = itemView.findViewById(R.id.cat_title);


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
