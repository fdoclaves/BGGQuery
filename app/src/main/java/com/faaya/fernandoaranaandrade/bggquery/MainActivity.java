package com.faaya.fernandoaranaandrade.bggquery;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.faaya.fernandoaranaandrade.bggquery.beans.Username;
import com.faaya.fernandoaranaandrade.bggquery.database.Queries;
import com.faaya.fernandoaranaandrade.bggquery.selectBussines.DataGetter;
import com.faaya.fernandoaranaandrade.bggquery.selectBussines.DataGetterData;
import com.faaya.fernandoaranaandrade.bggquery.selectBussines.InvalidUserName;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private List<Username> users = new ArrayList<>();
    private UsernameAdapter adapter;
    private UserListManager userListManager;
    private Queries queries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        queries = new Queries(MainActivity.this);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        userListManager = new UserListManager() {
            @Override
            public void add(Username username) {
                users.add(username);
                adapter.notifyDataSetChanged();
                if(users.size() == 6){
                    Button button = findViewById(R.id.buttonAddUserMain);
                    button.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void delete(Username username) {
                users.remove(username);
                adapter.notifyDataSetChanged();
                if(users.size() < 6){
                    Button button = findViewById(R.id.buttonAddUserMain);
                    button.setVisibility(View.VISIBLE);
                }
            }
        };
        adapter = new UsernameAdapter(this, users);
        adapter.setUserListManager(userListManager);
        final ListView listview = findViewById(R.id.username_list);
        listview.setAdapter(adapter);

        boolean error = getIntent().getBooleanExtra(ChoseGamesActivity.ERROR, false);
        if(error){
            InvalidDataFragment invalidDataFragment = InvalidDataFragment.newInstance("");
            invalidDataFragment.setText(getString(R.string.ErrorConElServidorIntenteloMasTarde));
            invalidDataFragment.show(getSupportFragmentManager(), "fragment_error_internet");
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_historial) {
            Intent intent = new Intent(this, HistorialActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_own) {
            Intent intent = new Intent(this, LudotecaActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_hot) {
            Intent intent = new Intent(this, HotActivity.class);
            startActivity(intent);
        } else if (id == R.id.exit) {
            finish();
            System.exit(0);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void choose_games(View v) {
        String[] args = new String[users.size()];
        for (int i = 0; i < args.length; i++) {
            args[i] = users.get(i).getUsername();
        }
        if(users.size() == 0){
            InvalidDataFragment invalidDataFragment = InvalidDataFragment.newInstance("");
            invalidDataFragment.setText(getString(R.string.NotUsers));
            invalidDataFragment.show(getSupportFragmentManager(), "fragment_edit_internet");
        } else {
            new InternetGetter().execute(args);
        }

    }

    public void add_username(View v) {
        if(queries.getCountUsername() == 0){
            showNewUser();
        } else {
            List<Username> usernameList = getUsernameList();
            if(usernameList.size() == 0){
                showNewUser();
            } else {
                AddOldOrNewUserFragment addOldOrNewUserFragment = AddOldOrNewUserFragment.newInstance("", new NuevoUser() {
                    @Override
                    public void add() {
                        showNewUser();
                    }

                    @Override
                    public void add(final Username username) {
                        userListManager.add(username);
                    }
                }, usernameList);
                addOldOrNewUserFragment.show(getSupportFragmentManager(), "fragment_edit_internet");
            }
        }
    }

    private List<Username> getUsernameList() {
        List<Username> allUsername = queries.getAllUsername();
        for (Username user : users) {
            for (Username username : allUsername) {
                if(username.getUsername().equals(user.getUsername())){
                    allUsername.remove(username);
                    break;
                }
            }
        }
        return allUsername;
    }

    private void showNewUser() {
        AddUserFragment addUserFragment = AddUserFragment.newInstance("", userListManager);
        addUserFragment.show(getSupportFragmentManager(), "fragment_edit_internet");
    }

    private class InternetGetter extends AsyncTask<String, Void, String> {
        public static final String OK = "OK";
        public static final String ERROR = "ERROR";
        public static final String INVALID_USER_NAME = "INVALID_USER_NAME";
        public static final String NOT_INTERNET = "NOT_INTERNET";
        private DataGetter dataGetter = new DataGetter();
        private ProgressDialog gettingDataDialog;
        private String[] users;
        private DataGetterData[] dataGetterData;
        private int index;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            gettingDataDialog = ProgressDialog.show(MainActivity.this, "", getString(R.string.Cargando), true, false);
        }

        @Override
        protected String doInBackground(String... usernames) {
            try {
                if (isOnline(MainActivity.this)) {
                    users = usernames;
                    dataGetterData = new DataGetterData[users.length];
                    File cacheDir = MainActivity.this.getCacheDir();
                    for (int i = 0; i < usernames.length; i++) {
                        index = i;
                        dataGetterData[i] = dataGetter.getDataFromUser(usernames[i], null, 5, cacheDir);
                    }
                    return OK;
                }
                return NOT_INTERNET;
            } catch (InvalidUserName e) {
                return INVALID_USER_NAME;
            } catch (Exception e) {
                return ERROR;
            }
        }

        private boolean isOnline(Context ctx) {
            ConnectivityManager connMgr = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected())
                return true;
            else
                return false;
        }

        @Override
        protected void onPostExecute(String result) {
            switch (result) {
                case OK:
                    for (String user : users) {
                        queries.saveOrUpdateUsername(new Username(user));
                    }
                    Intent intent = new Intent(MainActivity.this, ChoseGamesActivity.class);
                    intent.putExtra(ChoseGamesActivity.MainActivityMetadata, new MainActivityMetadata(dataGetterData, users));
                    startActivity(intent);
                    gettingDataDialog.dismiss();
                    break;
                case NOT_INTERNET:
                    InvalidDataFragment invalidDataFragment = InvalidDataFragment.newInstance("");
                    invalidDataFragment.setText(getString(R.string.NotInternet));
                    invalidDataFragment.show(getSupportFragmentManager(), "fragment_edit_internet");
                    gettingDataDialog.dismiss();
                    break;
                case INVALID_USER_NAME:
                    InvalidUserFragment invalidUserFragment = InvalidUserFragment.newInstance("");
                    invalidUserFragment.setInvalidUser(users[index]);
                    invalidUserFragment.show(getSupportFragmentManager(), "fragment_edit_invalid_user");
                    gettingDataDialog.dismiss();
                    break;
            }
        }
    }
}
