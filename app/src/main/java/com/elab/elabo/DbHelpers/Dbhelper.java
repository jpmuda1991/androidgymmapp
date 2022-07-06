package com.elab.elabo.DbHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.elab.elabo.Models.CategoriesModel;
import com.elab.elabo.Models.ExercisesModel;
import com.elab.elabo.R;
import com.google.gson.Gson;

import java.util.ArrayList;

public class Dbhelper extends SQLiteOpenHelper {



    private static final int DATABASE_VERSION = 1;


    private static final String DATABASE_NAME = "bdmusculation";


    private static final String TABLE_CATEGORIES= "categories";



    private static final String KEY_ID = "_id";
    private static final String KEY_DATA = "date";



    public Dbhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CATEGORIES + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_DATA+ " TEXT not null" + ")";

        db.execSQL(CREATE_CONTACTS_TABLE);



        ArrayList<ExercisesModel> exercisesModelArrayList = new ArrayList<>();
        exercisesModelArrayList.add(new ExercisesModel("Curl barre","Cet exercice sollicite et développe les biceps. Le curl est l’exercice d’isolation de base pour les biceps et est généralement réalisé debout, avec une barre droite ou coudée…", R.drawable.bicep_exr_one,"ZLRBO5wiPwM","Le biceps brachial, courte et longue portion, le brachial antérieur et le long supinateur.","En position de départ debout, le dos immobile et droit, les genoux fléchis ou une jambe avancée pour éviter de tricher en s'aidant de l'élan et les coudes prés du corps. Monter et descendre la barre sans à-coups. Vous pouvez varier l'écartement des mains en utilisant une prise large, moyenne ou serrée."));
        exercisesModelArrayList.add(new ExercisesModel("Curl haltère","Une alternative à la barre qui permet de travailler les bras séparément. Les haltères permettent pas mal de variantes intéressantes. Avec elles, vous allez vous forger des biceps", R.drawable.bicep_exr_two,"dh6Tcwy9a_o","Le biceps brachial, courte et longue portion, le brachial antérieur et le long supinateur.\n" +
                "Le brachial antérieur est utilisé pendant l’exécution du curl avec haltère, quel que soit la position de la main. Il se trouve sous le biceps.\n" +
                "La rotation de poignet pendant le mouvement, appelée \"supination\" (paume de la main vers soi), permet de plus solliciter le biceps brachial.\n" +
                "Les curls marteaux (pouce vers le haut) sollicitent quant à eux plus intensément le long supinateur.","En position de départ, le dos collé contre le banc ou assis à son extrémité, les bras contre les flancs, les pieds à plat sur le sol et les genoux serrés pour éviter que les haltères touchent les cuisses. Amener l'haltère au niveau de l'épaule à la force des biceps, en effectuant une rotation du poignet. Les coudes ne doivent pas dévier vers l'avant ou vers l'arrière. Ne pas utiliser l'élan pour soulever la charge, ce sont seulement les avant-bras doivent bouger."));
        exercisesModelArrayList.add(new ExercisesModel("Curl concentration","Un exercice d'isolation avec haltère qui va vous permettre de peaufiner vos biceps. Il a la réputation de donner du « pic » au biceps. Tous les conseils pour une exécution parfaite…", R.drawable.bicep_exr_three,"AeHpcMxhqgI","Le biceps brachial, courte et longue portion, le brachial antérieur et le long supinateur.","Assis sur un banc ou une chaise, le dos droit et légèrement penché en avant, le bras perpendiculaire au sol, le coude appuyé sur l’intérieur de la cuisse près du genou et la main en pronation qui tient l’haltère."));

        CategoriesModel categoriesModel = new CategoriesModel("Exercices biceps",R.drawable.biceps,exercisesModelArrayList);
        this.addCategoriesAndExercises(db,categoriesModel);




        ArrayList<ExercisesModel> exercisesModelArrayListSec = new ArrayList<>();
        exercisesModelArrayListSec.add(new ExercisesModel("Relevés de genoux","Un exercice souvent pratiqué en salle. Les relevés de genoux travaillent les abdominaux et les fléchisseurs de la hanche. Ils nécessitent une chaise à abdos…", R.drawable.abd_exr_one,"2_3aOp6j9m8","Il sollicite le grand droit des abdominaux en statique, le droit antérieur et le psoas iliaque.\n" +
                "Le grand droit fait partie de la sangle abdominale avec les obliques et le transverse. L’action du grand droit est la flexion du tronc.","Pendu à une barre, sur un espalier ou callé sur une chaise romaine, monter les genoux le plus haut possible en contractant fort le grand droit des abdominaux. Redescendre lentement sans cambrer le bas du dos jusqu'à ce que les cuisses soient parallèles au sol. L'amplitude n'a pas besoin d'être grande, il faut essayer d'enrouler abdominaux."));
        exercisesModelArrayListSec.add(new ExercisesModel("Sit-up","Cet exercice a la réputation de travailler le bas du ventre, là où le crunch au sol sollicite plutôt le haut des abdominaux. Les sit-up peuvent être réalisés au sol ou sur un banc à abdominaux.", R.drawable.abd_exr_second,"nKFxkSzYr80","Il sollicite le grand droit des abdominaux en statique, le droit antérieur et le psoas iliaque.\n" +
                "Le grand droit fait partie de la sangle abdominale avec les obliques et le transverse. L’action du grand droit est la flexion du tronc.","Assis au travers du banc ou sur un tapis de sol, mains au bord du banc pour l'équilibre, tendre les jambes puis ramener les genoux vers la poitrine. Garder les abdos sous tension pendant le mouvement."));
        exercisesModelArrayListSec.add(new ExercisesModel("Relevés de jambes renversés","Si vous voulez des abdominaux en acier alors cet exercice de musculation est fait pour vous. Les relevés de jambes renversés à la barre est un exercice difficile mais très efficace…", R.drawable.abd_exr_three,"JwkwUz5vdcc","Les relevés de jambes renversés à la barre travaillent le grand droit des abdominaux et les obliques. Les muscles du dos, les triceps et les avant-bras (prise de barre) sont également mis à contribution. En fait, la première partie du mouvement ressemble à un pull-over ; on retrouve aussi les sensations d'un tirage bras tendus pour le dos.","Position de départ bras tendus, suspendu à la barre, et le corps immobile. Monter les jambes en fléchissant les hanches et les genoux, tout en amenant les pieds au niveau de la barre. Revenir à la position de départ, en freinant la descente. Plus l’exercice est fait lentement, aussi bien dans la phase positive que négative du mouvement, plus la difficulté augmente."));

        CategoriesModel categoriesModelSec = new CategoriesModel("Exercices abdominaux",R.drawable.abdominal,exercisesModelArrayListSec);
        this.addCategoriesAndExercises(db,categoriesModelSec);




        ArrayList<ExercisesModel> exercisesModelArrayListThird = new ArrayList<>();
        exercisesModelArrayListThird.add(new ExercisesModel("Barre front","Cet exercice de base sollicite et développe les triceps à l'arrière du bras. Le Barre-front est l'un des meilleurs exercices de musculation pour gagner du volume aux triceps…", R.drawable.tri_exr_one,"-a4FR3zmdJ8","Cet exercice de musculation sollicite l'ensemble des muscles du triceps. Le triceps se situe sur la face postérieure du bras et permet l'extension de l'avant-bras sur le bras. Il est composé de 3 faisceaux qui interviennent plus ou moins suivant la position du bras, la prise et la résistance plus ou moins forte appliquée sur le muscle.","Position de départ allongé sur le sol ou sur un banc, les bras tendus. Les jambes peuvent être bloquées ou fléchies sur la poitrine pour éviter de cambrer trop le bas du dos. Prendre une barre droite ou coudée, prise de la largeur des épaules, les mains en pronation.\n" +
                "\n" +
                "Amener la barre au-dessus de la tête en tendant les bras. Ensuite, fléchir les bras jusqu'à effleurer le front avec la barre, en veillant à garder les coudes immobiles et les bras serrés (les bras ne doivent pas s'écarter l'un de l'autre). Pousser presque jusqu'à l'extension complète.\n" +
                "\n" +
                "Le mouvement décrit un quart d'arc de cercle et seuls les avants bras bougent durant l'exercice."));
        exercisesModelArrayListThird.add(new ExercisesModel("Extensions au-dessus de la tête","Un exercice d'isolation pour les triceps. Les extensions au-dessus de la tête vous permettent de muscler plus particulièrement la longue portion du triceps, ce qui vous donnera des bras", R.drawable.tri_ext_two,"kJ32PRK3LRo","Cet exercice de musculation sollicite l'ensemble des muscles du triceps. Le triceps permet l'extension de l’avant-bras sur le bras. Il est composé de trois faisceaux qui interviennent plus ou moins suivant la position du bras, la prise et la résistance plus ou moins forte appliquée sur les muscles.\n" +
                "Si vous faites des extensions avec les bras au-dessus de la tête à l'aide d'un haltère ou à deux mains avec la barre, la longue portion est dans ce cas étirée et donc plus sollicitée.", "Position de départ assis sur un banc, le dos bien droit, un haltère dans la main en position « marteau » et le bras tendu au-dessus de la tête. Descendre l'haltère derrière la tête en gardant le coude pointé vers le plafond (bras vertical), le plus bas possible et sans heurter le cou votre cou. Remonter la charge et arrêter l'extension avant de tendre complètement le bras. Seul l'avant-bras doit bouger pendant l'exécution du mouvement, de sorte à se concentrer sur le triceps."));
        exercisesModelArrayListThird.add(new ExercisesModel("kick back","Le kick back est un exercice d'isolation pour les triceps. C'est un exercice de finition qui permet de bien congestionner ce muscle à la fin de la séance. On peut le réaliser avec un haltère ou à la poulie basse…", R.drawable.tri_exr_three,"uZZ2630ELp8","Il sollicite les muscles du triceps plus particulièrement les faisceaux internes et externes. L'arrière d'épaule est également mise à contribution pour maintenir le bras immobile.","Position de départ buste penché en avant, un genou et la main libre en appuis sur le banc. Le bras qui tient l'haltère prés du flanc est parallèle au sol, et le coude est plus haut que le torse. Tendre le bras en amenant votre main en pronation, paume vers le plafond. Le corps reste en statique et seul l'avant-bras bouge durant l'exercice."));

        CategoriesModel categoriesModelThird = new CategoriesModel("Exercices triceps",R.drawable.triceps,exercisesModelArrayListThird);
        this.addCategoriesAndExercises(db,categoriesModelThird);


    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIES);

        onCreate(db);
    }



    public void addCategoriesAndExercises(SQLiteDatabase db, CategoriesModel categoriesModel) {

        Gson gson = new Gson();

        String stringJson = gson.toJson(categoriesModel);

        System.out.println("STRING JSON IS: " + stringJson);

        ContentValues values = new ContentValues();
        values.put(KEY_DATA,stringJson);


        db.insert(TABLE_CATEGORIES, null, values);

    }




    public ArrayList<CategoriesModel> getAllCategories() {

        ArrayList<CategoriesModel> categoriesModelArrayList = new ArrayList<>();
        // Select All Query

        String selectQuery = "SELECT  * FROM " + TABLE_CATEGORIES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) {
            do {


                CategoriesModel categoriesModel = new CategoriesModel();

                categoriesModel.setId(Integer.parseInt(cursor.getString(0)));

                String jsonStr = cursor.getString(1);

                Gson gson = new Gson();

                CategoriesModel categoriesModelJson = gson.fromJson(jsonStr, CategoriesModel.class);

                String title = categoriesModelJson.getTitle();
                int img = categoriesModelJson.getImg();
                ArrayList<ExercisesModel> exercisesModelListJson = categoriesModelJson.getExercisesModelArrayList();

                categoriesModel.setTitle(title);
                categoriesModel.setImg(img);
                categoriesModel.setExercisesModelArrayList(exercisesModelListJson);

                categoriesModelArrayList.add(categoriesModel);


            } while (cursor.moveToNext());
        }


        return categoriesModelArrayList;
    }




    public boolean update(int id,CategoriesModel categoriesModel) {

        SQLiteDatabase db = this.getWritableDatabase();

        Gson gson = new Gson();

        String stringJson = gson.toJson(categoriesModel);

        System.out.println("STRING JSON IS: " + stringJson);

        ContentValues values = new ContentValues();
        values.put(KEY_DATA,stringJson);

        long res =  db.update(TABLE_CATEGORIES, values, "_id = ?", new String[]{String.valueOf(id)});

        if (res == -1){

            db.close();
            return false;

        }else{

            db.close();
            return true;
        }
    }

    public boolean deleteCategory(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long res  =  db.delete(TABLE_CATEGORIES, KEY_ID + " = ?",
                new String[] { String.valueOf(id) });

        if (res == -1){

            db.close();
            return false;

        }else{

            db.close();
            return true;
        }
    }




}
