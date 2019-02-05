package com.example.nigakolczan.apk2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*ImageView imageView = fi
        GifImageView gifImageView = (GifImageView) findViewById(R.id.GifImageView);
        gifImageView.setGifImageResource(R.drawable.doggoss);*/
    }
public void Start(View v){
    Intent FSA = new Intent(getApplicationContext(), RestActivity.class);
        startActivity(FSA);
}
/*public void Options(View v){
    Intent options = new Intent(getApplicationContext(),OptionsActivity.class );
        startActivity(options);
}*/
public void Exit(View v){
    finish();
}
}
