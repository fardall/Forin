package com.example.forin;

import android.content.Intent;
import android.app.Activity;
import android.os.Bundle;


public class Keterangan extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //menghilangkan ActionBar
        setContentView(R.layout.activity_keterangan);

        Thread thread = new Thread(){
            public void run() {
                try {
                    sleep(3000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    startActivity(new Intent(Keterangan.this, SplashScreen.class));
                }
            }
        };
        thread.start();
    }
}