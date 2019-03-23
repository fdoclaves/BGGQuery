package com.faaya.fernandoaranaandrade.bggquery;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.faaya.fernandoaranaandrade.bggquery.selectBussines.DataGetterData;
import com.faaya.fernandoaranaandrade.bggquery.selectBussines.GamesProcessor;
import com.faaya.fernandoaranaandrade.bggquery.selectBussines.GamesRated;
import com.faaya.fernandoaranaandrade.bggquery.selectBussines.Result;
import com.faaya.fernandoaranaandrade.bggquery.selectBussines.XmlConverter;
import com.faaya.fernandoaranaandrade.bggquery.selectBussines.items.Items;


public class ChoseGamesActivity extends AppCompatActivity {

    public static final String MainActivityMetadata = "MainActivityMetadata";
    public static final String ERROR = "ERROR";
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
        protected String doInBackground(String... args) {
            try {
                DataGetterData[] dataGetterDataArray = extra.getDataGetterDataArray();
                Items[] itemsArray = new Items[dataGetterDataArray.length];
                for (int i = 0; i < itemsArray.length; i++) {
                    itemsArray[i] = fillItemsArray(dataGetterDataArray[i]);
                }
                gamesResult = new GamesProcessor(getString(R.string.JuegosVotos),getString(R.string.JuegosVotosPlural)).bestGame(itemsArray);
                return OK;
            } catch (Exception e) {
                e.printStackTrace();
                return ERROR;
            }
        }

        private Items fillItemsArray(DataGetterData dataGetterData) throws Exception {
            return xmlConverter.convertToItems(dataGetterData);
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
                Intent intent = new Intent(ChoseGamesActivity.this, WebActivity.class);
                intent.putExtra(WebActivity.URL_WEB, "https://boardgamegeek.com/boardgame/"+item.getId());
                startActivity(intent);
            }
        });

        final ListView listviewSuggest = findViewById(R.id.wantToPlayList);
        listviewSuggest.setAdapter(new WantToPlayGamesRatedAdapter(this, gamesResult.getWantToPlayGames(), users));
        listviewSuggest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                final GamesRated item = (GamesRated) parent.getItemAtPosition(position);
                Intent intent = new Intent(ChoseGamesActivity.this, WebActivity.class);
                intent.putExtra(WebActivity.URL_WEB, "https://boardgamegeek.com/boardgame/"+item.getId());
                startActivity(intent);
                /*view.animate().setDuration(2000).alpha(0)
                        .withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                view.setAlpha(1);
                            }
                        });*/

            }
        });
    }

}
