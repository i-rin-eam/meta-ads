package com.irineam.metaads;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.facebook.ads.AdSize;

/*
 * Author: MD YEAMIN
 * YouTube: https://www.youtube.com/@CodeCraftArena
 * Copyright © I-RIN-EAM
 */

public class MainActivity extends AppCompatActivity {

    MetaAdsManager metaAdsManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyMeta.setAdPlacementID();
        metaAdsManager = new MetaAdsManager(MainActivity.this).initializeMetaAds();
        metaAdsManager.showBannerAd(findViewById(R.id.adContainer), AdSize.BANNER_HEIGHT_50);
        metaAdsManager.loadInterstitialAd();


        findViewById(R.id.btnActivity).setOnClickListener(v -> metaAdsManager.showInterstitialAd(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "Hello User", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(MainActivity.this, MainActivity2.class));
            }
        }));

    }
}