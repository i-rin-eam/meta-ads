package com.irineam.metaads;

import android.app.Activity;
import android.view.View;
import android.widget.LinearLayout;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdSettings;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;

/*
 * Author: MD YEAMIN
 * YouTube: https://www.youtube.com/@CodeCraftArena
 * Copyright © I-RIN-EAM
 */

// MetaAdsManager class helps in managing Meta ads integration
public class MetaAdsManager {

    private Activity mActivity; // Reference to the activity
    private int mInterstitialAdDisplayCount; // Counter for interstitial ad display count
    private int mBannerAdClickCount; // Counter for banner ad click count
    private AdView mAdView; // Banner ad view
    private InterstitialAd mInterstitialAd; // Interstitial ad instance
    private Runnable mExecuteAfterDismissedCallback; // Callback after interstitial ad dismissed

    // Constructor for initializing MetaAdsManager with an Activity
    public MetaAdsManager(Activity activity) {
        this.mActivity = activity; // Initialize the activity reference
    }

    // Default constructor
    public MetaAdsManager() {
    }

    // Method to initialize Meta ads
    public MetaAdsManager initializeMetaAds() {

        // Check if Meta ads are enabled
        if (AdPlacementIDManager.META_AD_IS_ON) {
            // Initialize Meta ads with the activity
            AudienceNetworkAds.initialize(mActivity);
            // Set test mode if enabled
            AdSettings.setTestMode(AdPlacementIDManager.isTestMode());
        }
        return this; // Return current instance for method chaining
    }

    // Method to show banner ad
    public void showBannerAd(LinearLayout adContainer, AdSize adSize) {

        // Check if Meta ads are enabled
        if (AdPlacementIDManager.META_AD_IS_ON) {
            // Create banner ad view
            mAdView = new AdView(mActivity, AdPlacementIDManager.getBannerPlacementID(), adSize);
            // Add banner ad view to the specified container
            adContainer.addView(mAdView);

            // Define ad listener for banner ad
            AdListener adListener = new AdListener() {
                @Override
                public void onError(Ad ad, AdError adError) {
                    // Handle error if banner ad fails to load
                }

                @Override
                public void onAdLoaded(Ad ad) {
                    // Handle event when banner ad is loaded
                }

                @Override
                public void onAdClicked(Ad ad) {
                    // Check if banner ad controller is enabled
                    if (AdPlacementIDManager.isBannerAdController()) {
                        // Increment banner ad click count
                        mBannerAdClickCount++;
                        // If click count exceeds the threshold, hide the ad container and destroy the banner ad
                        if (mBannerAdClickCount >= AdPlacementIDManager.getBannerAdClickCount()) {
                            // Hide ad container if click count exceeds the threshold
                            adContainer.setVisibility(View.GONE);
                            // Destroy banner ad
                            destroyBannerAd();
                        }
                    }
                }

                @Override
                public void onLoggingImpression(Ad ad) {
                    // Handle impression logging event
                }
            };

            // Load banner ad with specified listener
            mAdView.loadAd(mAdView.buildLoadAdConfig().withAdListener(adListener).build());
        }
    }

    // Method to destroy banner ad
    public void destroyBannerAd() {
        // Check if Meta ads are enabled
        if (AdPlacementIDManager.META_AD_IS_ON) {
            if (mAdView != null) {
                // Destroy banner ad view
                mAdView.destroy();
            }
        }
    }

    // Method to load interstitial ad
    public void loadInterstitialAd() {
        // Check if Meta ads are enabled
        if (AdPlacementIDManager.META_AD_IS_ON) {
            // Create interstitial ad instance
            mInterstitialAd = new InterstitialAd(mActivity, AdPlacementIDManager.getInterstitialPlacementID());
            // Load interstitial ad
            mInterstitialAd.loadAd();
        }
    }

    // Method to show interstitial ad
    public void showInterstitialAd(Runnable callback) {
        // Set callback for when interstitial ad dismissed
        mExecuteAfterDismissedCallback = callback;

        // Check if Meta ads are enabled
        if (AdPlacementIDManager.META_AD_IS_ON) {
            // Check if interstitial ad is loaded
            if (mInterstitialAd != null && mInterstitialAd.isAdLoaded()) {
                // Define ad listener for interstitial ad
                InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
                    @Override
                    public void onInterstitialDisplayed(Ad ad) {
                        // Handle event when interstitial ad is displayed
                    }

                    @Override
                    public void onInterstitialDismissed(Ad ad) {
                        // Set interstitial ad instance to null after dismissal
                        mInterstitialAd = null;
                        // Increment interstitial ad display count
                        mInterstitialAdDisplayCount++;
                        // Check if interstitial ad controller is enabled
                        if (AdPlacementIDManager.isInterstitialAdController()) {
                            // Load next interstitial ad if display count is within limit
                            if (mInterstitialAdDisplayCount < AdPlacementIDManager.getInterstitialAdDisplayCount()) {
                                loadInterstitialAd();
                            }
                        } else {
                            loadInterstitialAd();
                        }

                        // Execute callback after interstitial ad dismissed
                        if (mExecuteAfterDismissedCallback != null) {
                            mExecuteAfterDismissedCallback.run();
                            // Reset callback after execution
                            mExecuteAfterDismissedCallback = null;
                        }
                    }

                    @Override
                    public void onError(Ad ad, AdError adError) {
                        // Handle error if interstitial ad fails to load
                    }

                    @Override
                    public void onAdLoaded(Ad ad) {
                        // Handle event when interstitial ad is loaded
                    }

                    @Override
                    public void onAdClicked(Ad ad) {
                        // Handle event when interstitial ad is clicked
                    }

                    @Override
                    public void onLoggingImpression(Ad ad) {
                        // Handle impression logging event
                    }
                };

                // Load interstitial ad with specified listener
                mInterstitialAd.loadAd(mInterstitialAd.buildLoadAdConfig().withAdListener(interstitialAdListener).build());
                // Show interstitial ad
                mInterstitialAd.show();
            } else {
                // Execute callback if interstitial ad not loaded or null
                if (mExecuteAfterDismissedCallback != null) {
                    mExecuteAfterDismissedCallback.run();
                    // Reset callback after execution
                    mExecuteAfterDismissedCallback = null;
                }
            }
        } else {
            // Execute callback if Meta ads are not enabled
            if (mExecuteAfterDismissedCallback != null) {
                mExecuteAfterDismissedCallback.run();
                // Reset callback after execution
                mExecuteAfterDismissedCallback = null;
            }
        }
    }

    // Method to destroy interstitial ad
    public void destroyInterstitialAd() {
        // Check if Meta ads are enabled
        if (AdPlacementIDManager.META_AD_IS_ON) {
            if (mInterstitialAd != null) {
                // Destroy interstitial ad instance
                mInterstitialAd.destroy();
            }
        }
    }
}
