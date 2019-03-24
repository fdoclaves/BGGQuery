package com.faaya.fernandoaranaandrade.bggquery;

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

import com.faaya.fernandoaranaandrade.bggquery.selectBussines.DataGetter;
import com.faaya.fernandoaranaandrade.bggquery.selectBussines.HotItems.HotItem;
import com.faaya.fernandoaranaandrade.bggquery.selectBussines.HotItems.HotItems;
import com.faaya.fernandoaranaandrade.bggquery.selectBussines.XmlConverter;

import java.io.File;

public class HotActivity extends AppCompatActivity {

    private XmlConverter xmlConverter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot);
        xmlConverter = new XmlConverter();

        new InternetGetter().execute();
    }

    private class InternetGetter extends AsyncTask<String, Void, String> {
        public static final String OK = "OK";
        public static final String ERROR = "ERROR";
        public static final String NOT_INTERNET = "NOT_INTERNET";
        private DataGetter dataGetter = new DataGetter();
        private ProgressDialog gettingDataDialog;
        private boolean isWiFi;
        private HotItems itemses;
        private OkAction okAction = new OkAction() {
            @Override
            public void action() {
                Intent intent = new Intent(HotActivity.this, MainActivity.class);
                System.out.println("fer");
                startActivity(intent);
            }
        };

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            gettingDataDialog = ProgressDialog.show(HotActivity.this, "", getString(R.string.Cargando), true, false);
        }

        @Override
        protected String doInBackground(String... args) {
            try {
                if (isOnline(HotActivity.this)) {
                    File cacheDir = HotActivity.this.getCacheDir();
                    File file = dataGetter.getHotnessData(5, cacheDir);
                    itemses = xmlConverter.convertToItems(file);
                    return OK;
                }
                return NOT_INTERNET;
            } catch (Exception e) {
                e.printStackTrace();
                return ERROR;
            }
        }

        private boolean isOnline(Context ctx) {
            ConnectivityManager connMgr = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                isWiFi = networkInfo.getType() == ConnectivityManager.TYPE_WIFI;
                return true;
            }
            return false;
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                switch (result) {
                    case OK:
                        ListView listview = findViewById(R.id.listLudoteca);
                        listview.setAdapter(new HotItemAdapter(HotActivity.this, itemses.getItem(), isWiFi));
                        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                                final HotItem item = (HotItem) parent.getItemAtPosition(position);
                                Intent intent = new Intent(HotActivity.this, WebActivity.class);
                                intent.putExtra(WebActivity.URL_WEB, "https://boardgamegeek.com/boardgame/" + item.getId());
                                startActivity(intent);
                            }
                        });
                        break;
                    case NOT_INTERNET: {
                        InvalidDataFragment invalidDataFragment = InvalidDataFragment.newInstance("", okAction);
                        invalidDataFragment.setText(getString(R.string.NotInternet));
                        invalidDataFragment.show(getSupportFragmentManager(), "fragment_edit_internet");
                        break;
                    }
                    case ERROR: {
                        InvalidDataFragment invalidDataFragment = InvalidDataFragment.newInstance("", okAction);
                        invalidDataFragment.setText(getString(R.string.ErrorConElServidorIntenteloMasTarde));
                        invalidDataFragment.show(getSupportFragmentManager(), "fragment_edit_internet");
                        break;
                    }
                }
            } catch (Exception e) {
                InvalidDataFragment invalidDataFragment = InvalidDataFragment.newInstance("", okAction);
                invalidDataFragment.setText(getString(R.string.ErrorConElServidorIntenteloMasTarde));
                invalidDataFragment.show(getSupportFragmentManager(), "fragment_edit_internet");
            } finally {
                gettingDataDialog.dismiss();
            }
        }
    }
}
