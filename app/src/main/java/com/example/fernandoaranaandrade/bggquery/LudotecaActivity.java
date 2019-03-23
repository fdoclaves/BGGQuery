package com.example.fernandoaranaandrade.bggquery;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.fernandoaranaandrade.bggquery.beans.Username;
import com.example.fernandoaranaandrade.bggquery.database.Queries;
import com.example.fernandoaranaandrade.bggquery.selectBussines.DataGetter;
import com.example.fernandoaranaandrade.bggquery.selectBussines.DataGetterData;
import com.example.fernandoaranaandrade.bggquery.selectBussines.GamesRated;
import com.example.fernandoaranaandrade.bggquery.selectBussines.InvalidUserName;
import com.example.fernandoaranaandrade.bggquery.selectBussines.XmlConverter;
import com.example.fernandoaranaandrade.bggquery.selectBussines.items.Items;
import com.example.fernandoaranaandrade.bggquery.selectBussines.items.item.Item;

import java.io.File;
import java.util.List;

public class LudotecaActivity extends AppCompatActivity {

    private Queries queries;
    private UserListManager userListManager;
    private XmlConverter xmlConverter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ludoteca);
        queries = new Queries(this);
        xmlConverter = new XmlConverter();
        userListManager = new UserListManager() {

            @Override
            public void add(Username username) {
                Button button = findViewById(R.id.button2);
                button.setText(username.getUsername());
                new InternetGetter().execute(username.getUsername());
            }

            @Override
            public void delete(Username username) {

            }
        };
    }

    public void add_username(View v) {
        if (queries.getCountUsername() == 0) {
            showNewUser();
        } else {
            List<Username> usernameList = queries.getAllUsername();
            if (usernameList.size() == 0) {
                showNewUser();
            } else {
                AddOldOrNewUserFragment addOldOrNewUserFragment = AddOldOrNewUserFragment.newInstance("", new NuevoUser() {
                    @Override
                    public void add() {
                        showNewUser();
                    }

                    @Override
                    public void add(final Username username) {
                        Button button = findViewById(R.id.button2);
                        button.setText(username.getUsername());
                        new InternetGetter().execute(username.getUsername());
                    }
                }, usernameList);
                addOldOrNewUserFragment.show(getSupportFragmentManager(), "fragment_edit_internet");
            }
        }
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
        private int index;
        private boolean isWiFi;
        private Items itemses;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            gettingDataDialog = ProgressDialog.show(LudotecaActivity.this, "", getString(R.string.Cargando), true, false);
        }

        @Override
        protected String doInBackground(String... usernames) {
            try {
                users = usernames;
                if (isOnline(LudotecaActivity.this)) {
                    DataGetterData[] dataGetterData = new DataGetterData[usernames.length];
                    File cacheDir = LudotecaActivity.this.getCacheDir();
                    for (int i = 0; i < usernames.length; i++) {
                        index = i;
                        dataGetterData[i] = dataGetter.getDataFromUser(usernames[i], "own", 5, cacheDir);
                        itemses = xmlConverter.convertToItems(dataGetterData[0]);
                        if(queries.getUsername(usernames[i]) == null){
                            queries.saveOrUpdateUsername(new Username(usernames[i]));
                        }
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
            if (networkInfo != null && networkInfo.isConnected()){
                isWiFi = networkInfo.getType() == ConnectivityManager.TYPE_WIFI;
                return true;
            }
            return false;
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                if (result.equals(OK)) {
                    ListView listview = findViewById(R.id.listLudoteca);
                    listview.setAdapter(new ItemAdapter(LudotecaActivity.this, itemses.getItem(), isWiFi));
                    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                            final Item item = (Item) parent.getItemAtPosition(position);
                            Intent intent = new Intent(LudotecaActivity.this, WebActivity.class);
                            intent.putExtra(WebActivity.URL_WEB, "https://boardgamegeek.com/boardgame/"+item.getObjectid());
                            startActivity(intent);
                        }
                    });
                } else if (result.equals(NOT_INTERNET)) {
                    InvalidDataFragment invalidDataFragment = InvalidDataFragment.newInstance("");
                    invalidDataFragment.setText(getString(R.string.NotInternet));
                    invalidDataFragment.show(getSupportFragmentManager(), "fragment_edit_internet");
                } else if (result.equals(INVALID_USER_NAME)) {
                    InvalidUserFragment invalidUserFragment = InvalidUserFragment.newInstance("");
                    invalidUserFragment.setInvalidUser(users[index]);
                    invalidUserFragment.show(getSupportFragmentManager(), "fragment_edit_invalid_user");
                }
            } catch (Exception e) {
                InvalidDataFragment invalidDataFragment = InvalidDataFragment.newInstance("");
                invalidDataFragment.setText(getString(R.string.ErrorConElServidorIntenteloMasTarde));
                invalidDataFragment.show(getSupportFragmentManager(), "fragment_edit_internet");
            } finally {
                gettingDataDialog.dismiss();
            }
        }
    }
}
