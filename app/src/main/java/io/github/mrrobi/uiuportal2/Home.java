package io.github.mrrobi.uiuportal2;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    CardView ucam,elms,library,website,unb;
    Button sendBtn;
    EditText sendText;
    AutoCompleteTextView t;
    String notice="notice";
    boolean notice_enable = false;
    FirebaseRemoteConfig remoteConfig = FirebaseRemoteConfig.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        read();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        remoteConfig.setConfigSettings(new FirebaseRemoteConfigSettings.Builder()
                .setDeveloperModeEnabled(true)
                .build()
        );

        HashMap<String,Object> defaults = new HashMap<>();
        defaults.put("update_req",false);
        defaults.put("app_version","1.0.8");
        defaults.put("update_url","https://drive.google.com/open?id=1CxgY3Hfm4oSqG5hbDZBCCgPeg_MEPmfi");
        defaults.put("notice_push","notice");
        defaults.put("notice","http://www.uiu.ac.bd/notices/");
        defaults.put("notice_txt","new notice");

        final Task<Void> fetch = remoteConfig.fetch(0);

        fetch.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                remoteConfig.activateFetched();
                checkUpdate();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ucam = (CardView) findViewById(R.id.ucamcardId);
        elms = (CardView) findViewById(R.id.elmscardId);
        library = (CardView) findViewById(R.id.librarycardId);
        website = (CardView) findViewById(R.id.webcardId);
        unb = (CardView) findViewById(R.id.newsboxId);
        ucam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nw = new Intent(Intent.ACTION_VIEW, Uri.parse("http://ucam.uiu.ac.bd/Security/LogIn.aspx"));
                startActivity(nw);
            }
        });
        elms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nw = new Intent(Intent.ACTION_VIEW, Uri.parse("https://elms.uiu.ac.bd/"));
                startActivity(nw);
            }
        });
        library.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nw = new Intent(Intent.ACTION_VIEW, Uri.parse("http://library.uiu.ac.bd/"));
                startActivity(nw);
            }
        });
        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nw = new Intent(Intent.ACTION_VIEW, Uri.parse("http://uiu.ac.bd/"));
                startActivity(nw);
            }
        });
        unb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nw = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/groups/www.UNB/"));
                startActivity(nw);
            }
        });

    }

    private void checkUpdate() {
        String version = remoteConfig.getString("app_version");
        boolean check = remoteConfig.getBoolean("update_req");
        String nw = remoteConfig.getString("notice_push");
        if(check){
            if(version.matches(BuildConfig.VERSION_NAME)){

            }
            else{
                //Toast.makeText(getApplicationContext(),"Update Required",Toast.LENGTH_SHORT).show();
                onUpdateNeeded(remoteConfig.getString("update_url"));
            }
        }

        if(remoteConfig.getBoolean("notice_enable")){
            if(!nw.matches(notice)){
                notice = nw;
                String notice = remoteConfig.getString("notice");
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(notice));
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

                Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext())
                        .setSmallIcon(R.drawable.ic_android_black_24dp)  //a resource for your custom small icon
                        .setContentTitle("New Notice from UIU") //the "title" value you sent in your notification
                        .setContentText(remoteConfig.getString("notice_txt").toString()) //ditto
                        .setPriority(100)
                        .setContentIntent(pendingIntent)
                        .setSubText("Tap to view the website.")//dismisses the notification on click
                        .setSound(defaultSoundUri)
                        .setAutoCancel(true);


                save();
                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                int notificationId = new Random().nextInt(60000);
                notificationManager.notify(notificationId /* ID of notification */, notificationBuilder.build());
            }
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent nw = new Intent(Home.this,Feedback.class);
            startActivity(nw);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        LayoutInflater inflater = getLayoutInflater();

        //View layout = inflater.inflate(R.layout.alert, null);
        if(id == R.id.bba){
//            Toast.makeText(getApplicationContext(),"BBA, App Under Development",Toast.LENGTH_SHORT).show();
            Intent nw = new Intent(Home.this,SOBE.class);
            startActivity(nw);

        }
        else if(id == R.id.cse){
//            Toast.makeText(getApplicationContext(),"CSE, App Under Development",Toast.LENGTH_SHORT).show();
            Intent nw = new Intent(Home.this,SOSE.class);
            startActivity(nw);
        }
        else if(id == R.id.club){
//            Toast.makeText(getApplicationContext(),"CSE, App Under Development",Toast.LENGTH_SHORT).show();
            Intent nw = new Intent(Home.this,Clubs.class);
            startActivity(nw);
        }
        else if(id == R.id.msimagine){
            //Toast.makeText(getApplicationContext(),"CSE, App Under Development",Toast.LENGTH_SHORT).show();
            Intent nw = new Intent(Home.this,MSIMAGINE.class);
            startActivity(nw);
        }
        else if(id == R.id.nav_send){
            Toast.makeText(getApplicationContext(),"This App Developed\n\tBy\nRobiuddin Robi,CSEUIU,171",Toast.LENGTH_LONG).show();
        }
        else if(id == R.id.nav_share){
            try {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT,"UIU Portal");
                String sAux = "\nLet me recommend you this application\n\n";
                sAux = sAux + "https://drive.google.com/open?id=1CxgY3Hfm4oSqG5hbDZBCCgPeg_MEPmfi\n\n";
                i.putExtra(Intent.EXTRA_TEXT, sAux);
                startActivity(Intent.createChooser(i, "choose one"));
            } catch(Exception e) {
                //e.toString();
            }
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onUpdateNeeded(final String updateUrl) {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("New version available")
                .setMessage("Please, update app to new version.")
                .setPositiveButton("Update",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                redirectStore(updateUrl);
                            }
                        }).create();
        dialog.show();
    }

    private void redirectStore(String updateUrl) {
        final Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(updateUrl));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    void save(){
        try {
            FileOutputStream fos = openFileOutput("data1.txt", Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(notice);
            fos.close();
           // Toast.makeText(getApplicationContext(),"Saved"+notice,Toast.LENGTH_SHORT).show();
        }catch(Exception e){
           // Toast.makeText(getApplicationContext(),e.getMessage().toString(),Toast.LENGTH_SHORT).show();
        }
    }
    void read(){
        try {
            FileInputStream fis = openFileInput("data1.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            notice = (String)ois.readObject();
            fis.close();

            //Toast.makeText(getApplicationContext(),"Read"+notice,Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            e.getMessage();
        }
    }

}
