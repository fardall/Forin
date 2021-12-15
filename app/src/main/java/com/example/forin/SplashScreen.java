package com.example.forin;

import android.content.Intent;
import android.app.Activity;
import android.os.Bundle;


public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //menghilangkan ActionBar
        setContentView(R.layout.activity_splash_screen);

        Thread thread = new Thread(){
            public void run() {
                try {
                    sleep(3000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    Intent toHome = new Intent(SplashScreen.this, MainActivity.class);
                    toHome.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(toHome);
                }
            }
        };
        thread.start();
    }
}