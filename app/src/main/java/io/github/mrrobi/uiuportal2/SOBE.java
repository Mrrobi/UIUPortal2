package io.github.mrrobi.uiuportal2;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class SOBE extends AppCompatActivity {

    CardView sobe,bc,mf,hr,ff;
    WebView sobeFb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobe);
        sobe = (CardView) findViewById(R.id.sobecardId);
        bc = (CardView) findViewById(R.id.sobebccardId);
        hr = (CardView) findViewById(R.id.sobehrcardId);
        mf = (CardView) findViewById(R.id.sobemfcardId);
        ff = (CardView) findViewById(R.id.sobeffcardId);
        sobe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nw = new Intent(Intent.ACTION_VIEW, Uri.parse("https://sobe.uiu.ac.bd/"));
                startActivity(nw);
            }
        });

        bc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nw = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/pg/uiu.bc/"));
                startActivity(nw);
            }
        });

        hr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nw = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/uiuhrforum/"));
                startActivity(nw);
            }
        });

        mf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nw = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/UIU.MF/"));
                startActivity(nw);
            }
        });

        ff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nw = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/UIU.CCC.FF/"));
                startActivity(nw);
            }
        });
    }

}
