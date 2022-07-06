package com.elab.elabo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.elab.elabo.Activities.ExercisesActivity
import com.elab.elabo.Adapters.CatAdapter
import com.elab.elabo.Application.BodyApplication
import com.elab.elabo.DbHelpers.Dbhelper
import com.elab.elabo.Models.CategoriesModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    lateinit var recView: RecyclerView
    lateinit var  contactBtn: FloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )


        recView = findViewById(R.id.cat_rView) as RecyclerView

        contactBtn = findViewById(R.id.contact_btn) as FloatingActionButton

        registerForContextMenu(contactBtn);

        val db: Dbhelper = Dbhelper(this);


        recView.layoutManager = GridLayoutManager(this,2)


        var data = ArrayList<CategoriesModel>();
        data  = db.allCategories;

        System.out.println("DATA IS: "+ data.size)


        val adapter = CatAdapter(this,data);


        recView.adapter = adapter


        adapter.setOnItemClickListener(object : CatAdapter.OnItemClickListener {
            override fun onCardClicked(pos: Int) {

                var model : CategoriesModel = data.get(pos)

                val intent = Intent(this@MainActivity, ExercisesActivity::class.java)
                BodyApplication.setCategoriesModel(model)
                startActivity(intent)
            }
        })

        contactBtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {

                openContextMenu(view);
            }

        })

    }

    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu.setHeaderTitle("Nous contacter")
        menu.add(0, v.id, 0, "e-mail")
        menu.add(0, v.id, 0, "numéro de téléphone")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        if (item.getTitle() === "e-mail") {

            val emailIntent = Intent(Intent.ACTION_SENDTO)
            emailIntent.data =
                Uri.parse("mailto:" + resources.getString(R.string.email))
            startActivity(Intent.createChooser(emailIntent, "Send e-mail"))

            return true
        }

        if (item.getTitle() === "numéro de téléphone") {

            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:" + resources.getString(R.string.contact_no))
            startActivity(intent)

            return true
        }

        return true
    }
}