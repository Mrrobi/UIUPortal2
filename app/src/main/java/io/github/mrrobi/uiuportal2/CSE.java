package io.github.mrrobi.uiuportal2;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class CSE extends AppCompatActivity {

    CardView cseWeb,cseFb,cseCCL,cseAF;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cse);
        cseWeb = (CardView) findViewById(R.id.csewebcardId);
        cseFb = (CardView) findViewById(R.id.csefbcardId);
        cseCCL = (CardView) findViewById(R.id.csecclcardId);
        cseAF = (CardView) findViewById(R.id.cseafcardId);

        cseWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nw = new Intent(Intent.ACTION_VIEW,Uri.parse("https://cse.uiu.ac.bd"));
                startActivity(nw);
            }
        });

        cseFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nw = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.facebook.com/cse.uiuinfo/"));
                startActivity(nw);
            }
        });

        cseCCL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nw = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.facebook.com/uiuccl/"));
                startActivity(nw);
            }
        });

        cseAF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nw = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.facebook.com/uiuappf/"));
                startActivity(nw);
            }
        });
    }
}
