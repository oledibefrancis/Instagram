package com.example.instagram;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("HFzeNCio98fUQdfzJWanBDO25UXup9WeyiojCfmh")
                .clientKey("urazO7Umx8Vs3DvO0YmquEyVYVh4iALTVejjkfpg")
                .server("https://parseapi.back4app.com")
                .build()
        );

        ParseObject.registerSubclass(Post.class);

    }
}
