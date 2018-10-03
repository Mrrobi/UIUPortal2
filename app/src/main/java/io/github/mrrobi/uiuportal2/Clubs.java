package io.github.mrrobi.uiuportal2;

import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class Clubs extends AppCompatActivity {

    CardView debate,sports,photo,theatre,culture,english,writers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clubs);
        debate = (CardView)findViewById(R.id.debate);
        sports = (CardView)findViewById(R.id.sports);
        photo = (CardView)findViewById(R.id.photo);
        theatre = (CardView)findViewById(R.id.film);
        culture = (CardView)findViewById(R.id.culture);
        english = (CardView)findViewById(R.id.english);
        writers = (CardView)findViewById(R.id.writer);

        debate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nw = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/UIU.Debate.Club/"));
                startActivity(nw);
            }
        });

        sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nw = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/SCUIU/"));
                startActivity(nw);
            }
        });

        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nw = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/UIUPC/"));
                startActivity(nw);
            }
        });

        theatre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nw = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/uiutfclub/"));
                startActivity(nw);
            }
        });

        culture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nw = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/UIU.Cultural.club.uiucc/"));
                startActivity(nw);
            }
        });

        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nw = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/UIU-English-Language-Forum-654917121346257/"));
                startActivity(nw);
            }
        });

        writers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nw = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/UIU.Writers.Official/"));
                startActivity(nw);
            }
        });


    }
}
