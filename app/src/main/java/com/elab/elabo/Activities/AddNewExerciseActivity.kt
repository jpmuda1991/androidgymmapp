package com.elab.elabo.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import com.elab.elabo.Application.BodyApplication
import com.elab.elabo.DbHelpers.Dbhelper
import com.elab.elabo.Fragments.BottomSheetImagePickerFragment
import com.elab.elabo.Models.CategoriesModel
import com.elab.elabo.Models.ExercisesModel
import com.elab.elabo.Models.ImagePickerModel
import com.elab.elabo.R
import com.google.android.material.textfield.TextInputEditText
import de.hdodenhof.circleimageview.CircleImageView

class AddNewExerciseActivity : AppCompatActivity(), BottomSheetImagePickerFragment.OnBottomSheetClick{

    lateinit var model : CategoriesModel
    lateinit var allExercisesList: ArrayList<ExercisesModel>
    lateinit var backImg : AppCompatImageView
    lateinit var addImgBtn : AppCompatImageView
    lateinit var exrImg : CircleImageView
    lateinit var titleEdit : TextInputEditText
    lateinit var descEdit : TextInputEditText
    lateinit var videoIdEdit : TextInputEditText
    lateinit var musclesEdit : TextInputEditText
    lateinit var executionEdit : TextInputEditText
    lateinit var updateBtn : AppCompatButton

    var exrPos : Int = -1
    var catdbId : Int = -1
    var catdbImg : Int = -1
    lateinit var catdbTitle : String


    var curr_exr_img : Int = -1;
    var curr_exr_tit : String = ""
    var curr_exr_desc : String = ""
    var curr_vid_id : String = ""
    var curr_muscle : String = ""
    var curr_execution : String = ""

    private var bottomSheetFragment: BottomSheetImagePickerFragment? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_exercise)

        val db: Dbhelper = Dbhelper(this);

        model = BodyApplication.getCategoriesModel()
        allExercisesList = model.exercisesModelArrayList
        exrPos = BodyApplication.getPos()

        catdbTitle = model.title
        catdbId = model.id
        catdbImg = model.img


        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        backImg = findViewById(R.id.back) as AppCompatImageView
        addImgBtn = findViewById(R.id.imageView2) as AppCompatImageView
        exrImg = findViewById(R.id.exr_img) as CircleImageView
        titleEdit = findViewById(R.id.title) as TextInputEditText
        descEdit = findViewById(R.id.desc) as TextInputEditText

        videoIdEdit = findViewById(R.id.video_id) as TextInputEditText
        musclesEdit = findViewById(R.id.muscles) as TextInputEditText
        executionEdit = findViewById(R.id.execution) as TextInputEditText


//        exrImg.setImageResource(curr_exr_img)
//        titleEdit.setText(curr_exr_tit)
//        descEdit.setText(curr_exr_desc)

        updateBtn = findViewById(R.id.update_btn) as AppCompatButton

        backImg.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {

                val intent = Intent(this@AddNewExerciseActivity, ExercisesActivity::class.java)
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }

        })

        addImgBtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {

                bottomSheetFragment = BottomSheetImagePickerFragment()
                bottomSheetFragment!!.show(supportFragmentManager, bottomSheetFragment!!.tag)
                bottomSheetFragment!!.setOnBottomSheetClick(this@AddNewExerciseActivity)

            }

        })


        exrImg.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {

                bottomSheetFragment = BottomSheetImagePickerFragment()
                bottomSheetFragment!!.show(supportFragmentManager, bottomSheetFragment!!.tag)
                bottomSheetFragment!!.setOnBottomSheetClick(this@AddNewExerciseActivity)

            }

        })

        updateBtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {

                val tit : String  = titleEdit.text.toString().trim();
                val desc : String  = descEdit.text.toString().trim();

                val videoId : String  = videoIdEdit.text.toString().trim();
                val mus : String  = musclesEdit.text.toString().trim();
                val exec : String  = executionEdit.text.toString().trim();


                if(curr_exr_img == -1){
                    Toast.makeText(this@AddNewExerciseActivity, "L'image est manquante", Toast.LENGTH_SHORT).show()
                    return
                }

                if (TextUtils.isEmpty(tit)){
                    Toast.makeText(this@AddNewExerciseActivity, "le titre manque", Toast.LENGTH_SHORT).show()
                    return
                }

                if (TextUtils.isEmpty(desc)){
                    Toast.makeText(this@AddNewExerciseActivity, "la description est manquante", Toast.LENGTH_SHORT).show()
                    return
                }

                if (TextUtils.isEmpty(videoId)){
                    Toast.makeText(this@AddNewExerciseActivity, "L'identifiant de la vidéo est manquant", Toast.LENGTH_SHORT).show()
                    return
                }

                if (TextUtils.isEmpty(mus)){
                    Toast.makeText(this@AddNewExerciseActivity, "Les muscles cibles sont manquants", Toast.LENGTH_SHORT).show()
                    return
                }

                if (TextUtils.isEmpty(exec)){
                    Toast.makeText(this@AddNewExerciseActivity, "Le détail de l'exécution est manquant", Toast.LENGTH_SHORT).show()
                    return
                }



                // update your existing list and then update your database //
                allExercisesList.add(ExercisesModel(tit,desc,curr_exr_img,videoId,mus,exec))

                val catModel : CategoriesModel = CategoriesModel(catdbId,catdbTitle,catdbImg,allExercisesList)

                val b: Boolean = db.update(catdbId,catModel)

                if (b){

                    Toast.makeText(this@AddNewExerciseActivity, "Ajouté avec succès", Toast.LENGTH_SHORT).show()


                }else{

                    Toast.makeText(this@AddNewExerciseActivity, "Quelque chose s'est mal passé", Toast.LENGTH_SHORT).show()

                }
            }

        })


    }

    override fun onDoneClicked(n: String?) {

        if (bottomSheetFragment != null && bottomSheetFragment!!.isVisible) {
            bottomSheetFragment!!.dismiss()
            bottomSheetFragment = null
        }


        Toast.makeText(this@AddNewExerciseActivity, "Type is: " + n, Toast.LENGTH_SHORT).show()


        if (n.equals("new")) {

            val imgPickrData : ImagePickerModel =  BodyApplication.getImagePickerModel();

            curr_exr_img = imgPickrData.img
            exrImg.setImageResource(imgPickrData.img)

        }else{


            // Dont Do Anything //

        }
    }


}