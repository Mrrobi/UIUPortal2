package io.github.mrrobi.uiuportal2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class SOSE extends AppCompatActivity {

    CardView cseCard , eeeCard , clubCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sose);

        cseCard = (CardView) findViewById(R.id.csecardId);
        eeeCard = (CardView) findViewById(R.id.eeecardId);

        cseCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nw = new Intent(SOSE.this,CSE.class);
                startActivity(nw);
            }
        });

        eeeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nw = new Intent(SOSE.this,EEE.class);
                startActivity(nw);
            }
        });
    }
}
