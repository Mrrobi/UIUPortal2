package io.github.mrrobi.uiuportal2;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class MSIMAGINE extends AppCompatActivity {

    CardView login,fb,query;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msimagine);
        login =(CardView)findViewById(R.id.dreamspark);
        fb = (CardView)findViewById(R.id.msimagineFB);
        query =(CardView)findViewById(R.id.msimaginQuery);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nw = new Intent(Intent.ACTION_VIEW, Uri.parse("http://dreamspark.uiu.ac.bd/"));
                startActivity(nw);
            }
        });

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nw = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/MSFTImagine/"));
                startActivity(nw);
            }
        });

        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nw = new Intent(Intent.ACTION_VIEW, Uri.parse("http://cse.uiu.ac.bd/facilities/dreamspark/"));
                startActivity(nw);
            }
        });
    }
}
