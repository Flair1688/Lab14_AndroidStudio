package com.example.lab_14;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    ScrollView mScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.tex1);
        Button but = (Button) findViewById(R.id.but1);
        mScrollView = (ScrollView) findViewById(R.id.SCROLLER_ID);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new textparse().execute();
            }
        });
    }
    public class textparse extends AsyncTask<Void, Void, Void> {
        String words;

        @Override
        protected Void doInBackground(Void...params) {
            try {
                Document doc = Jsoup.connect("https://www.fifa.com/tournaments/mens/worldcup/qatar2022/media-releases/fifa-world-cup-tm-trophy-tour-by-coca-cola-kicks-off-global-journey-in-dubai").get();
                words = doc.text();
            } catch(Exception e){e.printStackTrace();}
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            textView.setText(words);
        }



        private void scrollToBottom()
        {
            mScrollView.post(new Runnable()
            {
                public void run()
                {
                    mScrollView.smoothScrollTo(0, textView.getBottom());
                }
            });
        }
    }
}

