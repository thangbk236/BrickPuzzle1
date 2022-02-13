package com.brickpuzzle1.game;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.backends.android.AndroidGraphics;
import com.brickpuzzle1.game.BrickPuzzle1;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.games.AchievementsClient;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.GamesClient;
import com.google.android.gms.games.LeaderboardsClient;
import com.google.android.gms.games.PlayersClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class AndroidLauncher extends AndroidApplication implements PlayServices {
	//ADS
	AdView bannerAd;
	InterstitialAd interstitialAd;
	RewardedVideoAd rewardedvideoAd;
	//GoogleService
	private GoogleSignInClient mGoogleSignInClient;
	private LeaderboardsClient mLeaderboardsClient;
	private AchievementsClient mAchievementsClient;
	private PlayersClient mPlayersClient;

	private static final String TAG=AndroidLauncher.class.getSimpleName();

	private static final int RC_SIGN_IN = 9001;
	private static final int RC_UNUSED = 5001;
	private static final int RC_LEADERBOARD_UI = 9004;
	private static final int RC_ACHIEVEMENT_UI = 9003;

	private boolean IsSignedIn;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		View gameView = initializeForView(new BrickPuzzle1(this), config);
		setupAds();

		RelativeLayout layout = new RelativeLayout(this);
		layout.addView(gameView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		layout.addView(bannerAd, params);
		setContentView(layout);
		// Create the client used to sign in to Google services.
		mGoogleSignInClient = GoogleSignIn.getClient(this,
				new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN).build());
		IsSignedIn = false;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			getWindow().getDecorView().setSystemUiVisibility(
					View.SYSTEM_UI_FLAG_LAYOUT_STABLE
							| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
							| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
							| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
							| View.SYSTEM_UI_FLAG_FULLSCREEN
							| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
		}
	}


	public void setupAds() {
		bannerAd = new AdView(this);
		bannerAd.setVisibility(View.INVISIBLE);
		bannerAd.setBackgroundColor(0xff000000);
		bannerAd.setAdUnitId(getString(R.string.BANNER_AD_UNIT_ID));
		bannerAd.setAdSize(AdSize.SMART_BANNER);

		interstitialAd = new InterstitialAd(this);
		interstitialAd.setAdUnitId(getString(R.string.INTERSTITIAL_UNIT_ID));
		AdRequest.Builder builder = new AdRequest.Builder();
		AdRequest ad = builder.build();
		interstitialAd.loadAd(ad);

		rewardedvideoAd = MobileAds.getRewardedVideoAdInstance(this);
		rewardedvideoAd.setRewardedVideoAdListener(new RewardedVideoAdListener() {
			@Override
			public void onRewardedVideoAdLoaded() {

			}

			@Override
			public void onRewardedVideoAdOpened() {

			}

			@Override
			public void onRewardedVideoStarted() {

			}

			@Override
			public void onRewardedVideoAdClosed() {
				loadRewardedVideoAd();
			}

			@Override
			public void onRewarded(RewardItem rewardItem) {
				loadRewardedVideoAd();  // Load for next Reward Point
			}

			@Override
			public void onRewardedVideoAdLeftApplication() {

			}

			@Override
			public void onRewardedVideoAdFailedToLoad(int i) {

			}
			/*
			@Override
			public void onRewardedVideoCompleted() {

			}*/
		});
		loadRewardedVideoAd();
	}

	public void loadRewardedVideoAd() {
		rewardedvideoAd.loadAd(getString(R.string.REWARDEDVIDEO_UNIT_ID), new AdRequest.Builder().build());
	}

	//----------------------------------------------------------------------------------------------
	@Override
	public boolean isNetworkConnected() {
		ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		if(ni!=null) {
			if(ni.getType()==ConnectivityManager.TYPE_WIFI){
				return true;
			}
			else if (ni.getType()==ConnectivityManager.TYPE_MOBILE) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void onStart() {
		super.onStart();
	}
	@Override
	public void onStop() {
		super.onStop();
	}

	@Override
	public void showBannerAd () {
		if (isNetworkConnected()){
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					bannerAd.setVisibility(View.VISIBLE);
					AdRequest.Builder builder = new AdRequest.Builder();
					AdRequest ad = builder.build();
					bannerAd.loadAd(ad);
				}
			});
		}

	}

	@Override
	public void hideBannerAd() {
		{
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					bannerAd.setVisibility(View.INVISIBLE);
				}
			});
		}

	}

	@Override
	public void showInterstitialAd(final Runnable then) {
		if (isNetworkConnected()){
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					if(then!=null) {
						interstitialAd.setAdListener(new AdListener() {
							@Override
							public void onAdClosed() {
								Gdx.app.postRunnable(then);
								AdRequest.Builder builder = new AdRequest.Builder();
								AdRequest ad = builder.build();
								interstitialAd.loadAd(ad);
							}
						});
					}
					interstitialAd.show();
				}
			});
		}
	}

	@Override
	public void showRewardedVideoAd() {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if(rewardedvideoAd.isLoaded()){
					rewardedvideoAd.show();
				}
				else loadRewardedVideoAd();
			}
		});
	}


	//--------------------------------------------------------------------------------------------------
	@Override
	public boolean isSignedIn() {
		if (isNetworkConnected()){
			if (IsSignedIn){
				return IsSignedIn;
			}
			else {
				startSignInIntent();
				return IsSignedIn;
			}
		}
		else  {
			return false;
		}

	}
	@Override
	public void signInSilently() {

		mGoogleSignInClient.silentSignIn().addOnCompleteListener(this,
				new OnCompleteListener<GoogleSignInAccount>() {
					@Override
					public void onComplete(@NonNull Task<GoogleSignInAccount> task) {
						if (task.isSuccessful()) {
							onConnected(task.getResult());
						} else {
							onDisconnected();
						}
					}
				});
	}

	private void onConnected(GoogleSignInAccount googleSignInAccount) {

		mLeaderboardsClient = Games.getLeaderboardsClient(this, googleSignInAccount);
		mAchievementsClient = Games.getAchievementsClient(this, googleSignInAccount);
		mPlayersClient = Games.getPlayersClient(this, googleSignInAccount);

		GamesClient gamesClient = Games.getGamesClient(AndroidLauncher.this,googleSignInAccount);
		gamesClient.setGravityForPopups(Gravity.TOP|Gravity.CENTER_HORIZONTAL);
		gamesClient.setViewForPopups(((AndroidGraphics)AndroidLauncher.this.getGraphics()).getView());
		IsSignedIn=true;
	}

	@Override
	public void startSignInIntent() {
		if (isNetworkConnected()){
			startActivityForResult(mGoogleSignInClient.getSignInIntent(), RC_SIGN_IN);
		}
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);

		if (requestCode == RC_SIGN_IN) {
			Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(intent);

			try {
				GoogleSignInAccount account = task.getResult(ApiException.class);
				onConnected(account);
			} catch (ApiException apiException) {
				onDisconnected();
			}
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		rewardedvideoAd.resume(this);
		signInSilently();
	}
	@Override
	protected void onPause() {
		super.onPause();
		rewardedvideoAd.pause(this);
	}
	@Override
	protected void onDestroy(){
		super.onDestroy();
		rewardedvideoAd.destroy(this);
	}
	public void signOut() {

		if (!isSignedIn()) {
			Log.w(TAG, "signOut() called, but was not signed in!");
			return;
		}

		mGoogleSignInClient.signOut().addOnCompleteListener(this,
				new OnCompleteListener<Void>() {
					@Override
					public void onComplete(@NonNull Task<Void> task) {
						boolean successful = task.isSuccessful();
						Log.d(TAG, "signOut(): " + (successful ? "success" : "failed"));

						onDisconnected();
					}
				});
	}
	private void onDisconnected() {
		mAchievementsClient = null;
		mLeaderboardsClient = null;
		mPlayersClient = null;
		IsSignedIn = false;
	}
	//----------------------------------------------------------------------------------------------
	@Override
	public void submitScore(final int score){
		if(IsSignedIn){
			// Example
			mLeaderboardsClient.submitScore(getString(R.string.leaderboard_my_score), score);
			// Write code in here
		}
	}

	@Override
	public void showLeaderBoard() {
		if(isSignedIn()){
			// Example
			mLeaderboardsClient.getLeaderboardIntent(getString(R.string.leaderboard_my_score))
					.addOnSuccessListener(new OnSuccessListener<Intent>() {
						@Override
						public void onSuccess(Intent intent) {
							startActivityForResult(intent, RC_LEADERBOARD_UI);
						}
					});
			// Write code in here

		}
	}

	@Override
	public void showAchievements() {
		if (isSignedIn()){
			mAchievementsClient.getAchievementsIntent()
					.addOnSuccessListener(new OnSuccessListener<Intent>() {
						@Override
						public void onSuccess(Intent intent) {
							startActivityForResult(intent, RC_ACHIEVEMENT_UI);
						}
					});
		}
	}
	@Override
	public void unlockAchievements(){
		if (IsSignedIn) {
			// Example
			// mAchievementsClient.unlock(getString(R.string.achievement_blue_medal));
			// Write code in here
		}
	}
}


