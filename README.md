<p align="center">
  <a href="https://github.com/i-rin-eam/meta-ads">
    <img src="https://raw.githubusercontent.com/i-rin-eam/meta-ads/main/app/src/main/res/drawable/meta-ads.png" alt="Logo" width="100" height="100">
  </a> 
  <br>
  <a href="video_url">Watch Video for Implementing the Meta Ads Library</a>
</p>

<h1 align='center'>Implement Meta Ads</h1>

## Step 1: Add the dependency in `build.gradle (Module :app)`.
```gradle
implementation 'com.github.i-rin-eam:meta-ads:1.0.0'
implementation 'androidx.annotation:annotation:1.0.0'
implementation 'com.facebook.android:audience-network-sdk:6.+'
```
<img src="https://raw.githubusercontent.com/i-rin-eam/meta-ads/main/app/src/main/res/drawable/build-gradle.png" alt="build-gradle.png">

> [!NOTE]
> Replace meta audience network sdk version with the <a href="https://developers.facebook.com/docs/audience-network/setting-up/platform-setup/android/add-sdk">latest available</a>

## Step 2: Add it in your root `settings.gradle (Project Settings)` under repositories..

```gradle
maven { url 'https://jitpack.io' }
```
<img src="https://raw.githubusercontent.com/i-rin-eam/meta-ads/main/app/src/main/res/drawable/settings-gradle.png" alt="settings-gradle.png">

## Step 3: Add it in your root `gradle.propertise (Project Propertise).`
```gradle
android.enableJetifier=true
```
<img src="https://raw.githubusercontent.com/i-rin-eam/meta-ads/main/app/src/main/res/drawable/gradle-properties.png" alt="gradle-properties.png">

## Step 4: In `AndroidManifest.xml` <br>

`Add below Permissions.`
```xml
<!-- Permissions required by the application -->
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" /> 
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" /> 
<uses-permission android:name="com.google.android.gms.permission.AD_ID" />
```

`Enable Hardware Acceleration:` *In your `AndroidManifest.xml` file, add the following attribute to the `<application>` tag to enable hardware acceleration for your entire application.*
```xml
android:hardwareAccelerated="true"
```
<img src="https://raw.githubusercontent.com/i-rin-eam/meta-ads/main/app/src/main/res/drawable/android-manifest-xml.jpeg" alt="android-manifest-xml.jpeg">

## Step 5: Create a Java Class (e.g. `AdManager.java`) to Set Ad Placement IDs.
```java
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
        AdPlacementIDManager.SET_TEST_MODE = true; //Change the test mode to false before releasing.
        AdPlacementIDManager.META_AD_IS_ON = true; 
    }
```
<img src="https://raw.githubusercontent.com/i-rin-eam/meta-ads/main/app/src/main/res/drawable/ad-manager-java.png" alt="ad-manager-java.png">

## Step 6: Initialize `meta-ads` Library in your Activity or Fragment.
```java
// Declaration of MetaAdsManager variable
MetaAdsManager metaAdsManager;  
```
```java   
// Set ad placement IDs
AdManager.setAdPlacementID();

// Initialize MetaAdsManager and Meta ads
metaAdsManager = new MetaAdsManager(MainActivity.this).initializeMetaAds();
```
<img src="https://raw.githubusercontent.com/i-rin-eam/meta-ads/main/app/src/main/res/drawable/init-meta-ads.png" alt="init-meta-ads.png">

## Step 7: Add Banner Ads <br>
*This guide explains how to add banner and medium rectangle ads to your app.*

`Adding a Container for the Banner Ad in your layout file (for example: activity_main.xml)`
```xml
    <LinearLayout
        android:id="@+id/adContainer"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_alignParentBottom="true"
        android:orientation="vertical" />
```
<img src="https://raw.githubusercontent.com/i-rin-eam/meta-ads/main/app/src/main/res/drawable/banner-xml.png" alt="banner-xml.png">

`Implementing the Banner Ad in your Activity`
```java
// Show banner ad
metaAdsManager.showBannerAd(findViewById(R.id.adContainer), AdSize.BANNER_HEIGHT_50);
```
*Lastly, add the following code to your activity's onDestroy() function to release resources the AdView uses.*

```java
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Destroy banner ad
        metaAdsManager.destroyBannerAd();
    }
```
<img src="https://raw.githubusercontent.com/i-rin-eam/meta-ads/main/app/src/main/res/drawable/banner-java.png" alt="banner-java.png">

## Step 8: Add Interstitial Ads <br>
*This guide explains how to add Interstitial ads to your app.* <br>

`Implementing the Interstitial Ad in your Activity`
```java
// Preload interstitial ad
metaAdsManager.loadInterstitialAd();
```
```java
        // Show the interstitial ad. Write this code inside the click event.
        metaAdsManager.showInterstitialAd(new Runnable() {
            @Override
            public void run() {
                // Display toast message after dismiss ads.
                Toast.makeText(MainActivity.this, "Hello User", Toast.LENGTH_SHORT).show();
            }
        });
```
<img src="https://raw.githubusercontent.com/i-rin-eam/meta-ads/main/app/src/main/res/drawable/inter-ads1.png" alt="inter-ads1.png">

*Lastly, add the following code to your activity's onDestroy() function to release resources the interstitial uses.*
```java
 metaAdsManager.destroyInterstitialAd();
```
<img src="https://raw.githubusercontent.com/i-rin-eam/meta-ads/main/app/src/main/res/drawable/inter-ads2.png" alt="inter-ads2.png">

## License

Distributed under the MIT License. See <a href="https://github.com/i-rin-eam/meta-ads/blob/main/LICENSE">LICENSE</a> for more information.

## Authors

* **MD YEAMIN** - *Android Software Developer* - <a href="https://github.com/i-rin-eam">MD YEAMIN</a> - *Learn with Ease*

<h1 align="center">Thank You ❤️</h1>
