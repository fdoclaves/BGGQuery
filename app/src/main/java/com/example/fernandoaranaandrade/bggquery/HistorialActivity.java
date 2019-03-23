package com.example.fernandoaranaandrade.bggquery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.fernandoaranaandrade.bggquery.beans.Username;
import com.example.fernandoaranaandrade.bggquery.database.Queries;

import java.util.ArrayList;
import java.util.List;

public class HistorialActivity extends AppCompatActivity {

    List<Username> usernames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);
        final Queries queries = new Queries(this);
        ListView listView = findViewById(R.id.Historiallist);
        usernames = queries.getAllUsername();
        final UsernameAdapter adapter = new UsernameAdapter(this, usernames);
        adapter.setUserListManager(new UserListManager() {
            @Override
            public void add(Username username) {

            }

            @Override
            public void delete(Username username) {
                queries.deleteUsername(username.getUsername());
                usernames.remove(username);
                adapter.notifyDataSetChanged();
            }
        });
        listView.setAdapter(adapter);
    }
}
