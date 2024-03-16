<p align="center">
  <a href="https://github.com/i-rin-eam/meta-ads">
    <img src="https://raw.githubusercontent.com/i-rin-eam/meta-ads/main/app/src/main/res/drawable/meta-ads.png" alt="Logo" width="100" height="100">
  </a> 
  <br>
  <a href="video_url">Watch Video for Implementing the Meta Ads Library</a>
</p>

<h1 align='center'>Implement Meta Ads</h1>

> **Step 1: Add the dependency in `build.gradle (Module :app)`.**
```gradle
dependencies {

    implementation 'com.github.i-rin-eam:meta-ads:1.0.0'
    implementation 'androidx.annotation:annotation:1.0.0'
    implementation 'com.facebook.android:audience-network-sdk:6.+'
}
```
> [!NOTE]
> Replace meta audience network sdk version with the <a href="https://developers.facebook.com/docs/audience-network/setting-up/platform-setup/android/add-sdk">latest available</a>

<br>
> **Step 2: Add it in your root `settings.gradle (Project Settings)` at the end of repositories.**
```gradle
    repositories {

        maven { url 'https://jitpack.io' }
    }
```
<br>

> **Step 3: Add it in your root `gradle.propertise (Project Propertise)` at the end of repositories.**
```gradle
android.enableJetifier=true
```
<br>

> **Step 4: In `AndroidManifest.xml`** <br>

`Add below Permissions.`
```xml
    <!-- Permissions required by the application -->
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" /> 
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" /> 
    <uses-permission android:name="com.google.android.gms.permission.AD_ID" />
```
<br>

`Enable Hardware Acceleration:` *In your AndroidManifest.xml file, add the following attribute to the `<application>` tag to enable hardware acceleration for your entire application.*
```xml
<application android:hardwareAccelerated="true" ...>
```

> **Step 5: Create a Java Class (e.g. `AdManager.java`) to Set Ad Placement IDs.**
```java
package com.irineam.metaads;

public class AdManager {

    /**
     * Method to set up ad placement IDs and related settings.
     */
    public static void setAdPlacementID(){
        // Define constants for ad placement IDs and related settings
        
        // Banner Ad Settings
        AdPlacementIDManager.BANNER_PLACEMENT_ID = "IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID"; 
        AdPlacementIDManager.BANNER_AD_CLICK_COUNT = 2; 
        AdPlacementIDManager.BANNER_AD_CONTROLLER = true; 

        // Interstitial Ad Settings
        AdPlacementIDManager.INTERSTITIAL_PLACEMENT_ID = "YOUR_PLACEMENT_ID"; 
        AdPlacementIDManager.INTERSTITIAL_AD_DISPLAY_COUNT = 2; 
        AdPlacementIDManager.INTERSTITIAL_AD_CONTROLLER = true;

        // Test Mode and Meta Ad Settings
        AdPlacementIDManager.SET_TEST_MODE = true; // Flag to enable test mode
        AdPlacementIDManager.META_AD_IS_ON = true; 
    }
}
```
> **Step 6: Initialize `meta-ads` Library in your Activity or Fragment.**
```java
package com.irineam.metaads;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Declaration of MetaAdsManager variable
    MetaAdsManager metaAdsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set ad placement IDs
        AdManager.setAdPlacementID();

        // Initialize MetaAdsManager and Meta ads
        metaAdsManager = new MetaAdsManager(MainActivity.this).initializeMetaAds();

    }
}
```
> **Step 7: Add Banner Ads** <br>
*This guide explains how to add banner and medium rectangle ads to your app.*

`Adding a Layout Container for the Banner Ad`
```xml
    <!-- In your layout file (for example: activity_main.xml),
    add a layout that will act as a container for your Ad.-->
    <LinearLayout
        android:id="@+id/adContainer"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_alignParentBottom="true"
        android:orientation="vertical" />
```
`Implementing the Banner Ad in your Activity`
```java
        // Show banner ad
        metaAdsManager.showBannerAd(findViewById(R.id.adContainer), AdSize.BANNER_HEIGHT_50);
```
<br>
*Lastly, add the following code to your activity's onDestroy() function to release resources the AdView uses:*

```java
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Destroy banner ad
        metaAdsManager.destroyBannerAd();
    }
```
> **Step 8: Add Interstitial Ads** <br>
*This guide explains how to add Interstitial ads to your app.*
`Implementing the Banner Ad in your Activity`
```java
        // Preload interstitial ad
        metaAdsManager.loadInterstitialAd();

        // Show interstitial ad. write this code inside click event.
        metaAdsManager.showInterstitialAd(new Runnable() {
            @Override
            public void run() {
                // Display toast message after dismiss ads.
                Toast.makeText(MainActivity.this, "Hello User", Toast.LENGTH_SHORT).show();
            }
        });
```
<br>
*Lastly, add the following code to your activity's onDestroy() function to release resources the interstitial uses:*

```java
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Destroy interstitial ad
        metaAdsManager.destroyInterstitialAd();
    }
```
## License

Distributed under the MIT License. See <a href="https://github.com/i-rin-eam/meta-ads/blob/main/LICENSE">LICENSE</a> for more information.

## Authors

* **MD YEAMIN** - *Android Software Developer* - <a href="https://github.com/i-rin-eam">MD YEAMIN</a> - *Learn with Ease*

<h1 align="center">Thank You ❤️</h1>
