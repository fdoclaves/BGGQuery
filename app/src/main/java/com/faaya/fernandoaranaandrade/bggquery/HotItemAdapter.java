package com.faaya.fernandoaranaandrade.bggquery;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.faaya.fernandoaranaandrade.bggquery.selectBussines.HotItems.HotItem;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HotItemAdapter extends ArrayAdapter<HotItem> {

    private boolean isWiFi;

    private static Map<String, Bitmap> cache = new HashMap<>();

    public HotItemAdapter(Context context, List<HotItem> items, boolean isWiFi) {
        super(context, 0, items);
        this.isWiFi = isWiFi;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        HotItem item = getItem(position);
        view = inflater.inflate(R.layout.item_layout, parent, false);
        TextView name = view.findViewById(R.id.textViewName);
        String gameName = item.getName().getValue();
        name.setText(gameName);
        if (gameName.length() > 20) {
            name.setTextSize(20);
        }
        if (gameName.length() > 29) {
            name.setTextSize(16);
        }
        if (gameName.length() > 35) {
            name.setTextSize(14);
        }
        ImageView imageView = view.findViewById(R.id.imageView2);
        Bitmap bitmap = cache.get(item.getThumbnail().getValue());
        if (bitmap == null) {
            if (isWiFi) {
                new InternetGetter(imageView).execute(item.getThumbnail().getValue());
            } else {
                //imageView.setImageDrawable(view.getResources().getDrawable(R.drawable.boargamelogo));
                imageView.setVisibility(View.INVISIBLE);
            }
        } else {
            imageView.setImageBitmap(bitmap);
        }
        return view;
    }


    private class InternetGetter extends AsyncTask<String, Void, String> {
        public static final String OK = "OK";
        public static final String ERROR = "ERROR";
        private Bitmap bmp;
        private ImageView imageView;

        public InternetGetter(ImageView imageView) {
            this.imageView = imageView;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... thumbnails) {
            try {
                URL url = new URL(thumbnails[0]);
                bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                cache.put(thumbnails[0], bmp);
                return OK;
            } catch (Exception e) {
                e.printStackTrace();
                return ERROR;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if (result.equals(OK)) {
                imageView.setImageBitmap(bmp);
            }
        }
    }

}
