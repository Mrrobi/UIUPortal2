package io.github.mrrobi.uiuportal2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Feedback extends AppCompatActivity {

    Button sendBtn;
    EditText sendText;
    AutoCompleteTextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        t = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
    }
    @SuppressLint("LongLogTag")
    public void perform(View view) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        String msgBody=t.getText().toString();
//        Toast.makeText(getApplicationContext(),""+t,Toast.LENGTH_SHORT).show();
//        Toast.makeText(getApplicationContext(),msgBody,Toast.LENGTH_SHORT).show();

       if(msgBody.matches("")){

       }else{
//           Intent email = new Intent(Intent.ACTION_SEND);
//           email.putExtra(Intent.EXTRA_EMAIL, new String[]{"mrrobi040@hotmail.com"});
//           email.putExtra(Intent.EXTRA_SUBJECT, "App feedback");
//           email.putExtra(Intent.EXTRA_TEXT, msgBody);
//           email.setType("message/rfc822");
//           startActivity(Intent.createChooser(email, "Choose an Email client :"));

           Intent testIntent = new Intent(Intent.ACTION_VIEW);
           Uri data = Uri.parse("mailto:?subject=" + "App Feedback" + "&body=" + msgBody + "&to=" + "mrrobi040@hotmail.com");
           testIntent.setData(data);
           startActivity(testIntent);
       }
    }
}
