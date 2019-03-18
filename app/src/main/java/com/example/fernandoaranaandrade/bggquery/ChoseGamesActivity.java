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
import android.widget.ListView;

import com.example.fernandoaranaandrade.bggquery.selectBussines.DataGetter;
import com.example.fernandoaranaandrade.bggquery.selectBussines.DataGetterData;
import com.example.fernandoaranaandrade.bggquery.selectBussines.GamesProcessor;
import com.example.fernandoaranaandrade.bggquery.selectBussines.GamesRated;
import com.example.fernandoaranaandrade.bggquery.selectBussines.InvalidUserName;
import com.example.fernandoaranaandrade.bggquery.selectBussines.Result;
import com.example.fernandoaranaandrade.bggquery.selectBussines.XmlConverter;
import com.example.fernandoaranaandrade.bggquery.selectBussines.items.Items;

import java.io.File;


public class ChoseGamesActivity extends AppCompatActivity {

    public static final String MainActivityMetadata = "MainActivityMetadata";
    private static final String INVALID_USER = "INVALID_USER";
    private static final String ERROR = "ERROR";
    private XmlConverter xmlConverter = new XmlConverter();
    private MainActivityMetadata extra;
    private Result gamesResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_chose_games);
            extra = (MainActivityMetadata) getIntent().getSerializableExtra(MainActivityMetadata);
            new InternetGetter().execute();
        } catch (Exception e) {
            //MOSTRAR MESANJE EMERGENTE
        }
    }

    private class InternetGetter extends AsyncTask<String, Void, String> {
        public static final String OK = "OK";
        public static final String ERROR = "ERROR";
        private ProgressDialog gettingDataDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            gettingDataDialog = ProgressDialog.show(ChoseGamesActivity.this, "", getString(R.string.Procesando), true, false);
        }

        @Override
        protected String doInBackground(String... usernames) {
            try {
                DataGetterData[] dataGetterDataArray = extra.getDataGetterDataArray();
                Items[] itemsArray = new Items[dataGetterDataArray.length];
                for (int i = 0; i < itemsArray.length; i++) {
                    itemsArray[i] = fillItemsArray(dataGetterDataArray[i], 5);
                }
                gamesResult = new GamesProcessor().bestGame(itemsArray);
                return OK;
            } catch (Exception e) {
                e.printStackTrace();
                return ERROR;
            }
        }

        private Items fillItemsArray(DataGetterData dataGetterData, int tries) throws InterruptedException {
            try {
                return xmlConverter.convertToItems(dataGetterData);
            } catch (Exception e){
                System.out.println(dataGetterData.getXml().toString());
                if (dataGetterData.getXml().toString().contains("Please try again later for access")) {
                    System.out.println("vuelve a intentar");
                    Thread.sleep(4000l);
                    return fillItemsArray(dataGetterData, tries--);
                }
                if (dataGetterData.getXml().toString().contains("Invalid username specified")) {
                    System.out.println("error usuario");
                    Intent intent = new Intent(ChoseGamesActivity.this, ChoseGamesActivity.class);
                    intent.putExtra(ChoseGamesActivity.INVALID_USER, true);
                    startActivity(intent);
                }
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            gettingDataDialog.dismiss();
            if(result.equals(OK)){
                fillLists(gamesResult, extra.getUsers());
            } else {
                Intent intent = new Intent(ChoseGamesActivity.this, MainActivity.class);
                intent.putExtra(ChoseGamesActivity.ERROR, true);
                startActivity(intent);
            }
        }
    }

    private void fillLists(Result gamesResult, String[] users) {
        final ListView listview = findViewById(R.id.realList);
        listview.setAdapter(new GamesRatedAdapter(this, gamesResult.getRatingGames(), users));
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                final GamesRated item = (GamesRated) parent.getItemAtPosition(position);
                System.out.println("click:"+item.getName());
            }
        });

        final ListView listviewSuggest = findViewById(R.id.wantToPlayList);
        listviewSuggest.setAdapter(new WantToPlayGamesRatedAdapter(this, gamesResult.getWantToPlayGames(), users));
        listviewSuggest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                final GamesRated item = (GamesRated) parent.getItemAtPosition(position);
                System.out.println("click:"+item.getName());
                /*view.animate().setDuration(2000).alpha(0)
                        .withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                System.out.println("hola!!!!!!");
                                adapter.notifyDataSetChanged();
                                view.setAlpha(1);
                            }
                        });*/
            }
        });
    }

}
