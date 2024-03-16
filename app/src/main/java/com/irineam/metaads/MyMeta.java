package com.irineam.metaads;

public class MyMeta {

    public static void setAdPlacementID(){
        // Define constants for ad placement IDs
        AdPlacementIDManager.BANNER_PLACEMENT_ID = "IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID"; // Banner placement ID constant
        AdPlacementIDManager.BANNER_AD_CLICK_COUNT = 2; // Banner ad click count threshold
        AdPlacementIDManager.BANNER_AD_CONTROLLER = true; // Flag to control banner ad behavior

        AdPlacementIDManager.INTERSTITIAL_PLACEMENT_ID = "YOUR_PLACEMENT_ID"; // Interstitial placement ID constant
        AdPlacementIDManager.INTERSTITIAL_AD_DISPLAY_COUNT = 2; // Interstitial ad display count threshold
        AdPlacementIDManager.INTERSTITIAL_AD_CONTROLLER = true; // Flag to control interstitial ad behavior

        AdPlacementIDManager.SET_TEST_MODE = true; // Flag to enable test mode
        AdPlacementIDManager.META_AD_IS_ON = true; // Flag to determine if Meta ads are enabled

    }
}
