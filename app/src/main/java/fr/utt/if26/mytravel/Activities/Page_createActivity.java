package fr.utt.if26.mytravel.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import fr.utt.if26.mytravel.Config.Bdd;
import fr.utt.if26.mytravel.DAO.PageDAO;
import fr.utt.if26.mytravel.Helpers.MenuHeader;
import fr.utt.if26.mytravel.Model.Page;
import fr.utt.if26.mytravel.R;

public class Page_createActivity extends MenuHeader {
    private Bdd database;
    private PageDAO pdao;
    private EditText layout_title;
    private EditText layout_summary;
    private EditText layout_content;
    private int carnet_id;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_create);
        Bundle extras = getIntent().getExtras();
        password = extras.getString("password");
        carnet_id = extras.getInt("carnet_id");

        database = new Bdd(this);
        pdao = new PageDAO(database, password);

        layout_title = (EditText) findViewById(R.id.page_title);
        layout_summary = (EditText) findViewById(R.id.page_summary);
        layout_content = (EditText) findViewById(R.id.page_content);
        Button layout_saveButton = (Button) findViewById(R.id.page_saveButton);

        layout_saveButton.setOnClickListener(save_action);
    }

    private View.OnClickListener save_action = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String title = layout_title.getText().toString();
            String summary = layout_summary.getText().toString();
            String content = layout_content.getText().toString();
            Page page = new Page(title, content, summary, carnet_id);

            pdao.insertRow(page);
            Intent page_listIntent = new Intent(Page_createActivity.this, Page_listActivity.class);
            Bundle extras = new Bundle();
            extras.putInt("carnet_id", carnet_id);
            extras.putString("password", password);
            page_listIntent.putExtras(extras);
            startActivity(page_listIntent);
        }
    };

    @Override
    public void onDestroy() {
        database.close();
        super.onDestroy();
    }
}
