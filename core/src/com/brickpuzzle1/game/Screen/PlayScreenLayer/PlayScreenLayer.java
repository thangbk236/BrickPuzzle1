package com.brickpuzzle1.game.Screen.PlayScreenLayer;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.brickpuzzle1.game.Assets.GameAsset;
import com.brickpuzzle1.game.Assets.GameConstant;
import com.brickpuzzle1.game.Assets.GameJson;
import com.brickpuzzle1.game.BrickPuzzle1;
import com.brickpuzzle1.game.Screen.MainMenuScreen;
import com.brickpuzzle1.game.Screen.MainMenuScreenLayer.GridLayer;
import com.brickpuzzle1.game.Screen.PlayScreen;
import com.brickpuzzle1.game.Screen.SettingScreen;

import sun.rmi.runtime.Log;

public class PlayScreenLayer {
    protected final Game game;
    GridLayer gridLayer;
    PlayButtonLayer playButtonLayer;
    SizeBarLayer sizeBarLayer;
    TetrisLayer tetrisLayer;
    TetrisManager tetrisManager;

    private int PlayScreenState;

    private float time;
    private int gameCountDown;
    private boolean playButtonLayerLatch;
    private boolean settingButtonLayerLatch;

    public PlayScreenLayer(Game game){
        this.game=game;
        PlayScreenState=0;

        tetrisManager = new TetrisManager();
        tetrisManager.load_from_json();

        gridLayer = new GridLayer(1);

        sizeBarLayer = new SizeBarLayer(tetrisManager);
        tetrisLayer = new TetrisLayer(tetrisManager);
        playButtonLayer = new PlayButtonLayer(game);
    }

    public void render(float delta, SpriteBatch batch){
        switch (PlayScreenState){
            case 0:// game init
                Game_Init(delta,batch);
                break;
            case 1:
                Game_Play(delta,batch);
                break;
            case 2:
                Game_Pause(delta,batch);
                break;
            case 3:
                Game_Setting(delta,batch);
                break;
            case 4:
                Game_Count(delta,batch);
                break;
            case 5:
                Game_Over(delta,batch);
                break;
        }
        Game_Render(delta,batch);
    }

    public void Game_Render(float delta,SpriteBatch batch){// doan nay viet van con ngu lam
        gridLayer.GameUpdate(delta,batch);
        tetrisLayer.GameDraw(batch);
        playButtonLayer.render(delta,batch);
        sizeBarLayer.render(delta,batch);
        tetrisManager.render(delta,batch);
    }

    public void Game_Init(float delta, SpriteBatch batch){// dat lai cac tham so khi vao man choi moi
        BrickPuzzle1.playservices.hideBannerAd();
        //Gdx.app.log("playscreenlayer","Game_Init");
        tetrisLayer.Init();
        playButtonLayer.Init();
        gameCountDown=3;
        time=0.0f;
        playButtonLayerLatch=false;
        settingButtonLayerLatch=false;
        PlayScreenState=4;
    }
    public void Game_Pause(float delta, SpriteBatch batch){
        //Gdx.app.log("playscreenlayer","Game_Pause");
        if(!playButtonLayerLatch){
            if (PlayButtonLayer.isStopButton){
                playButtonLayerLatch=true;
                PlayScreenState=1;
                BrickPuzzle1.playservices.hideBannerAd();
            }
        }
        if (!PlayButtonLayer.isStopButton){
            playButtonLayerLatch=false;
        }

    }
    public void Game_Setting(float delta, SpriteBatch batch){// goi luon man hinh setting roi nen khong can ham nay nuwa
        //Gdx.app.log("playscreenlayer","Game_Setting");

    }
    public void Game_Count(float delta, SpriteBatch batch){
        //Gdx.app.log("playscreenlayer","Game_Count");
        // dem nguoc tu 3-2-1
        if(gameCountDown>0){
            batch.begin();
            GameAsset.GoodMorningBigfont.draw(batch,String.format("%d",gameCountDown), GameConstant._TO_STR.x,GameConstant._TO_STR.y,0, Align.center,false);
            batch.end();
            time+=delta;
            if(time>1.0f) {// cu 1s thi tang 1 don vi
                gameCountDown -= 1;
                time = 0.0f;
            }
        }
        else {
            gameCountDown=3;
            PlayScreenState=1;

        }
    }
    public void Game_Over(float delta, SpriteBatch batch){
        //Gdx.app.log("playscreenlayer","Game_Over");
        tetrisLayer.GameOverView=true;// luc nay se chua mot khoang trong tren man hinh de hien thi gameover
        if(gameCountDown>0){
            batch.begin();
            GameAsset.GoodMorningfont.draw(batch,"GAME OVER",GameConstant._TO_STR.x,GameConstant._TO_STR.y,0, Align.center,false);
            batch.end();
            time+=delta;
            if(time>1.0f) {// cu 1s thi tang 1 don vi
                gameCountDown -= 1;
                time = 0.0f;
            }
        }
        else {
            gameCountDown=3;
            PlayScreenState=0;
            tetrisManager.save_to_json();
            BrickPuzzle1.playservices.submitScore(tetrisManager.hi_score);
            game.setScreen(MainMenuScreen.getInstance(game,false));
        }
    }
    public void Game_Play(float delta, SpriteBatch batch){
        //Gdx.app.log("playscreenlayer","Game_Play");
        tetrisLayer.render(delta,batch);
        if (tetrisManager.is_gameover){// chuyen sang game over
            PlayScreenState=5;
        }
        if(!playButtonLayerLatch){
            if (PlayButtonLayer.isStopButton){
                playButtonLayerLatch=true;
                PlayScreenState=2;
                BrickPuzzle1.playservices.showBannerAd();
            }
        }
        if (!PlayButtonLayer.isStopButton){
            playButtonLayerLatch=false;
        }
    }
    public void Set_State(int PlayScreenState){
        this.PlayScreenState = PlayScreenState;
        if(this.PlayScreenState==4){
            gameCountDown=3;
        }
    }
    public void dispose(){
        playButtonLayer.dipose();
        gridLayer.dispose();
        tetrisLayer.dispose();
        sizeBarLayer.dispose();
        tetrisManager.dispose();
    }
}
