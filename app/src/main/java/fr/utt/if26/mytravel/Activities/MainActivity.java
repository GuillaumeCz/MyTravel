package fr.utt.if26.mytravel.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Iterator;

import fr.utt.if26.mytravel.Config.Bdd;
import fr.utt.if26.mytravel.DAO.CarnetDAO;
import fr.utt.if26.mytravel.DAO.PageDAO;
import fr.utt.if26.mytravel.Helpers.MenuHeader;
import fr.utt.if26.mytravel.Model.Carnet;
import fr.utt.if26.mytravel.Model.Page;

import fr.utt.if26.mytravel.R;

public class MainActivity extends MenuHeader {
    private boolean first_run = true;
    private Bdd database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            // ==== Exemples pour interagire avec la base de données simplement
        // initialisation/creation de la bdd
        database = new Bdd(this);

        // instanciation d'un Objet PageDao pour interagir avec la table Page
        PageDAO pdao = new PageDAO(database);

        if(first_run) {
            deleteDatabase(Bdd.DATABASE_NAME);
            // Création d'objets Page
            Page t = new Page("TitleTest", "ContentTest","SummaryTest");


            // INSERTION d'une page dans la table Page
            // Idée de récupéré l'id que lui a attribué la base de données pour completer l'objet Page
            // precedemment construit
            t.setId(pdao.insertRow(t));


            first_run = false;
        }

        Button page_listButton = (Button)findViewById(R.id.page_listButton);
        Button carnet_listButton = (Button)findViewById(R.id.carnet_listButton);
        page_listButton.setOnClickListener(page_list);
        carnet_listButton.setOnClickListener(carnet_list);

        CarnetDAO cdao = new CarnetDAO(database);

        Carnet c = new Carnet("test");
        Carnet c2 = new Carnet("ctest2");

        c.setId(cdao.insertRow(c));
        c2.setId(cdao.insertRow(c2));

        Page t2 = new Page("Title2", "ContentTest2","SummaryTest2");
        Page t3 = new Page("Title3", "ContentTest3","Summary3");
        t2.setId(pdao.insertRow(t2));
        t3.setId(pdao.insertRow(t3));

        cdao.addPage(c, t2);
        cdao.addPage(c2, t3);
    }

    private View.OnClickListener page_list = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Class page_listActivityClass =Page_listActivity.class;
            Intent page_listIntent = new Intent(MainActivity.this, page_listActivityClass);
            startActivity(page_listIntent);
        }
    };

    private View.OnClickListener carnet_list = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Class carnet_listActivityClass =Carnet_listActivity.class;
            Intent carnet_listIntent = new Intent(MainActivity.this, carnet_listActivityClass);
            startActivity(carnet_listIntent);
        }
    };



    @Override
    public void onDestroy() {
        database.close();
        super.onDestroy();
    }
}
