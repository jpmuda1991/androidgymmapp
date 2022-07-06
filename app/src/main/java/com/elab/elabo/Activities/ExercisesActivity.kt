package com.elab.elabo.Activities

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.elab.elabo.Adapters.CatAdapter
import com.elab.elabo.Adapters.ExercisesAdapter
import com.elab.elabo.Application.BodyApplication
import com.elab.elabo.DbHelpers.Dbhelper
import com.elab.elabo.MainActivity
import com.elab.elabo.Models.CategoriesModel
import com.elab.elabo.Models.ExercisesModel
import com.elab.elabo.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ExercisesActivity : AppCompatActivity() {

    lateinit var model : CategoriesModel
    lateinit var allExercisesList: ArrayList<ExercisesModel>
    lateinit var list : ArrayList<ExercisesModel>
    lateinit var recView: RecyclerView
    lateinit var titleBar : AppCompatTextView
    lateinit var backImg : AppCompatImageView
    lateinit var addBtn : FloatingActionButton
    lateinit var alertDialog: AlertDialog


    var catdbId : Int = -1
    var catdbImg : Int = -1
    lateinit var catdbTitle : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercises)

        val db: Dbhelper = Dbhelper(this);

        model = BodyApplication.getCategoriesModel()
        allExercisesList = model.exercisesModelArrayList

        list = model.exercisesModelArrayList



        catdbTitle = model.title
        catdbId = model.id
        catdbImg = model.img

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        titleBar = findViewById(R.id.textView) as AppCompatTextView
        addBtn = findViewById(R.id.add_exr) as FloatingActionButton

        titleBar.setText(model.title)

        backImg = findViewById(R.id.back) as AppCompatImageView

        recView = findViewById(R.id.cat_rView) as RecyclerView


        recView.layoutManager = LinearLayoutManager(this)


        val adapter = ExercisesAdapter(this,list);


        recView.adapter = adapter

        addBtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {

                val intent = Intent(this@ExercisesActivity, AddNewExerciseActivity::class.java)
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }

        })


        backImg.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {

                val intent = Intent(this@ExercisesActivity, MainActivity::class.java)
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }

        })

        adapter.setOnItemClickListener(object : ExercisesAdapter.OnItemClickListener{

            override fun onCardClicked(pos: Int) {

                var model: ExercisesModel  = list.get(pos)
                BodyApplication.setExercisesModel(model)
                BodyApplication.setPos(pos)
                val intent = Intent(this@ExercisesActivity, YoutubeVidActivity::class.java)
                startActivity(intent)

            }

            override fun onEditClicked(pos: Int) {

                var model: ExercisesModel  = list.get(pos)
                BodyApplication.setExercisesModel(model)
                BodyApplication.setPos(pos)
                val intent = Intent(this@ExercisesActivity, EditExerciseActivity::class.java)
                startActivity(intent)
            }


            override fun onDeleteClicked(pos: Int) {

                var model: ExercisesModel  = list.get(pos)

                val builder = AlertDialog.Builder(this@ExercisesActivity,R.style.MyDialogTheme)
                builder.setMessage("Êtes-vous sûr de vouloir supprimer ceci ?")

                builder.setPositiveButton("yes") { dialog, which ->


                    allExercisesList.removeAt(pos)

                    val catModel : CategoriesModel = CategoriesModel(catdbId,catdbTitle,catdbImg,allExercisesList)

                    val b: Boolean = db.update(catdbId,catModel)

                    if (b){

                        Toast.makeText(this@ExercisesActivity, "Supprimé", Toast.LENGTH_SHORT).show()
                        alertDialog.dismiss()

                        // Refreshing an activity //
                        finish();
                        overridePendingTransition( 0, 0);
                        startActivity(getIntent());
                        overridePendingTransition( 0, 0);

                    }else{

                        Toast.makeText(this@ExercisesActivity, "Quelque chose s'est mal passé", Toast.LENGTH_SHORT).show()
                        alertDialog.dismiss()

                    }



                }

                builder.setNegativeButton("no") { dialog, which ->

                    alertDialog.dismiss()

                }


                alertDialog = builder.create()
                alertDialog.show()

            }


        })
    }
}