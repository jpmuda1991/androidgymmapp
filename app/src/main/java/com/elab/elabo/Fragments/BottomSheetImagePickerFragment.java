package com.elab.elabo.Fragments;

import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.elab.elabo.Adapters.ImagePickerAdapter;
import com.elab.elabo.Application.BodyApplication;
import com.elab.elabo.Models.ImagePickerModel;
import com.elab.elabo.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class BottomSheetImagePickerFragment extends BottomSheetDialogFragment implements View.OnClickListener {

    private View view;
    private RecyclerView recyclerView;
    private AppCompatTextView mainTxt;
    private ImagePickerAdapter imagePickerAdapter;

    private String editVal;

    public OnBottomSheetClick onBottomSheetClick;

    private ArrayList<ImagePickerModel> imagePickerModelArrayList;

    public interface OnBottomSheetClick{

        void onDoneClicked(String n);

    }

    public void setOnBottomSheetClick(OnBottomSheetClick onBottomSheetClick) {
        this.onBottomSheetClick = onBottomSheetClick;
    }

    public BottomSheetImagePickerFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.fragment_bottom_sheet_dialog, container, false);

        imagePickerModelArrayList = new ArrayList<>();

        prepareDateForAdapter();

        mainTxt = view.findViewById(R.id.textView8);
        recyclerView = view.findViewById(R.id.img_rView);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recyclerView.setHasFixedSize(true);

        imagePickerAdapter = new ImagePickerAdapter(getActivity(),imagePickerModelArrayList);

        recyclerView.setAdapter(imagePickerAdapter);


        imagePickerAdapter.setOnItemClickListener(new ImagePickerAdapter.OnItemClickListener() {
            @Override
            public void onCardClicked(int pos) {

                // previous Selected Image //
                ImagePickerModel imagePickerModel =  BodyApplication.getImagePickerModel();


                ImagePickerModel currentSelectedImg = imagePickerModelArrayList.get(pos);

                if (imagePickerModel == null) {

                    ImagePickerModel newPickerModel = new ImagePickerModel(currentSelectedImg.getImg(),true);
                    BodyApplication.setImagePickerModel(newPickerModel);

                    imagePickerModelArrayList.remove(pos);
                    imagePickerModelArrayList.add(pos,newPickerModel);
                    imagePickerAdapter.notifyDataSetChanged();

                }else{

                    ImagePickerModel model =   findPreviousSelection(imagePickerModel,imagePickerModelArrayList);
                    int position  =   findPreviousPos(imagePickerModel,imagePickerModelArrayList);

                    ImagePickerModel changingOldPickerModel = new ImagePickerModel(model.getImg(),false);

                    imagePickerModelArrayList.remove(position);
                    imagePickerModelArrayList.add(position,changingOldPickerModel);
                    imagePickerAdapter.notifyDataSetChanged();

                    ImagePickerModel newPickerModel = new ImagePickerModel(currentSelectedImg.getImg(),true);
                    BodyApplication.setImagePickerModel(newPickerModel);

                    imagePickerModelArrayList.remove(pos);
                    imagePickerModelArrayList.add(pos,newPickerModel);
                    imagePickerAdapter.notifyDataSetChanged();


                }

            }
        });

        mainTxt.setOnClickListener(this);
        return view;

    }

    private int findPreviousPos(ImagePickerModel imagePickerModel, ArrayList<ImagePickerModel> imagePickerModelArrayList) {

        for (int i = 0 ; i < imagePickerModelArrayList.size() ; i++){

            if (imagePickerModelArrayList.get(i).getImg() == imagePickerModel.getImg()){

                return i;
            }
        }

        return -1;
    }

    private ImagePickerModel findPreviousSelection(ImagePickerModel imagePickerModel, ArrayList<ImagePickerModel> imagePickerModelArrayList) {

        for (int i = 0 ; i < imagePickerModelArrayList.size() ; i++){

            if (imagePickerModelArrayList.get(i).getImg() == imagePickerModel.getImg()){

                return imagePickerModelArrayList.get(i);
            }
        }

        return null;

    }

    private void prepareDateForAdapter() {

        if (imagePickerModelArrayList != null && imagePickerModelArrayList.size() > 0){

            imagePickerModelArrayList.clear();
        }

        imagePickerModelArrayList.add(new ImagePickerModel(R.drawable.tri_ext_two,false));
        imagePickerModelArrayList.add(new ImagePickerModel(R.drawable.bicep_exr_two,false));
        imagePickerModelArrayList.add(new ImagePickerModel(R.drawable.bicep_exr_three,false));
        imagePickerModelArrayList.add(new ImagePickerModel(R.drawable.abd_exr_second,false));
        imagePickerModelArrayList.add(new ImagePickerModel(R.drawable.tri_exr_one,false));
        imagePickerModelArrayList.add(new ImagePickerModel(R.drawable.tri_exr_three,false));
        imagePickerModelArrayList.add(new ImagePickerModel(R.drawable.abd_exr_one,false));
        imagePickerModelArrayList.add(new ImagePickerModel(R.drawable.abd_exr_three,false));
        imagePickerModelArrayList.add(new ImagePickerModel(R.drawable.bicep_exr_one,false));
        imagePickerModelArrayList.add(new ImagePickerModel(R.drawable.tri_exr_one,false));


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.textView8:

                if (BodyApplication.getImagePickerModel() != null) {

                    if (onBottomSheetClick != null){

                        onBottomSheetClick.onDoneClicked("new");
                    }


                }else{

                    if (onBottomSheetClick != null){

                        onBottomSheetClick.onDoneClicked("old");
                    }
                }


                break;

            default:

                break;
        }
    }

    private boolean validateEmailAddress(String editVal) {

        if (android.util.Patterns.EMAIL_ADDRESS.matcher(editVal).matches()) {
            return true;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(editVal).matches()) {
            return false;
        }
        return false;
    }
}
