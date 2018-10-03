package io.github.mrrobi.uiuportal2;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.webkit.WebView;

public class EEE extends AppCompatActivity {

    CardView eeeWeb,eeeFB,eeeRC,eeeEEC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eee);

        eeeWeb = (CardView) findViewById(R.id.eeewebcardId);
        eeeFB = (CardView) findViewById(R.id.eeefbcardId);
        eeeRC = (CardView) findViewById(R.id.eeerccardId);
        eeeEEC = (CardView) findViewById(R.id.eeeeeccardId);


        eeeWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nw = new Intent(Intent.ACTION_VIEW, Uri.parse("https://eee.uiu.ac.bd"));
                startActivity(nw);
            }
        });

        eeeFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nw = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/EEEofUIU/"));
                startActivity(nw);
            }
        });

        eeeRC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nw = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/uiurobotics/"));
                startActivity(nw);
            }
        });

        eeeEEC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nw = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/pg/uiueec/"));
                startActivity(nw);
            }
        });
    }
}
