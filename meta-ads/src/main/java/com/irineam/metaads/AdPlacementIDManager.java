package com.irineam.metaads;

/*
 * Author: MD YEAMIN
 * YouTube: https://www.youtube.com/@CodeCraftArena
 * Copyright © I-RIN-EAM
 */

// Class to manage ad placement IDs
public class AdPlacementIDManager {

    // Define constants for ad placement IDs
    public static String BANNER_PLACEMENT_ID; // Banner placement ID constant
    public static int BANNER_AD_CLICK_COUNT; // Banner ad click count threshold
    public static boolean BANNER_AD_CONTROLLER = false; // Flag to control banner ad behavior

    public static String INTERSTITIAL_PLACEMENT_ID; // Interstitial placement ID constant
    public static int INTERSTITIAL_AD_DISPLAY_COUNT; // Interstitial ad display count threshold
    public static boolean INTERSTITIAL_AD_CONTROLLER = false; // Flag to control interstitial ad behavior

    public static boolean SET_TEST_MODE = false; // Flag to enable test mode
    public static boolean META_AD_IS_ON = true; // Flag to determine if Meta ads are enabled

    // Method to get the banner placement ID
    public static String getBannerPlacementID() {
        return BANNER_PLACEMENT_ID; // Return banner placement ID
    }

    // Method to get the interstitial placement ID
    public static String getInterstitialPlacementID() {
        return INTERSTITIAL_PLACEMENT_ID; // Return interstitial placement ID
    }

    // Method to get the interstitial ad display count
    public static int getInterstitialAdDisplayCount() {
        return INTERSTITIAL_AD_DISPLAY_COUNT; // Return interstitial ad display count
    }

    // Method to get the banner ad click count
    public static int getBannerAdClickCount() {
        return BANNER_AD_CLICK_COUNT; // Return banner ad click count threshold
    }

    // Method to check if banner ad controller is enabled
    public static boolean isBannerAdController() {
        return BANNER_AD_CONTROLLER; // Return flag indicating if banner ad controller is enabled
    }

    // Method to check if interstitial ad controller is enabled
    public static boolean isInterstitialAdController() {
        return INTERSTITIAL_AD_CONTROLLER; // Return flag indicating if interstitial ad controller is enabled
    }

    // Method to check if test mode is enabled
    public static boolean isTestMode() {
        return SET_TEST_MODE; // Return flag indicating if test mode is enabled
    }

    // Method to check if Meta ads are enabled
    public static boolean isMetaAdIsOn() {
        return META_AD_IS_ON; // Return flag indicating if Meta ads are enabled
    }
}
