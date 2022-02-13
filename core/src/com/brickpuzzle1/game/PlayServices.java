package com.brickpuzzle1.game;

/**
 * Created by ThangPro on 1/9/2018.
 */

public interface PlayServices {
    //----------------------------------

    // ADS
    public boolean isNetworkConnected();
    public void showBannerAd();
    public void hideBannerAd();
    public void showInterstitialAd(Runnable then);
    public void showRewardedVideoAd();
    //----------------------------------
    // Google Service
    public void startSignInIntent();
    public void signInSilently();
    public void signOut();
    public boolean isSignedIn();
    //
    public void submitScore(int score);
    public void showAchievements();
    public void showLeaderBoard();
    public void unlockAchievements();

    //----------------------------------
}
