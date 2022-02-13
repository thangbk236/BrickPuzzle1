package com.brickpuzzle1.game.Screen.MainMenuScreenLayer;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.brickpuzzle1.game.Assets.GameAsset;
import com.brickpuzzle1.game.Assets.GameButton;
import com.brickpuzzle1.game.Assets.GameConstant;
import com.brickpuzzle1.game.Assets.GameJson;
import com.brickpuzzle1.game.BrickPuzzle1;
import com.brickpuzzle1.game.Screen.MainMenuScreen;
import com.brickpuzzle1.game.Screen.PlayScreen;
import com.brickpuzzle1.game.Screen.SettingScreen;

public class MainMenuButtonLayer {
    private GameButton LeaderBoard;
    private GameButton PlayGame;
    private GameButton SettingGame;

    private boolean isLeaderBoard;
    private boolean isPlayGame;
    private boolean isSettingGame;
    protected final Game game;
    public MainMenuButtonLayer(Game game){
        this.game = game;
        Init();
    }
    public void Init(){
        LeaderBoard = new GameButton("ButtonEdge/LeaderBoard.png","ButtonEdge/LeaderBoard.png","Music/move.wav",GameConstant.BUTTON_LEADERBOARD,false);
        PlayGame = new GameButton("ButtonEdge/PlayGame.png","ButtonEdge/PlayGame.png","Music/move.wav",GameConstant.BUTTON_PLAYGAME,true);
        SettingGame = new GameButton("ButtonEdge/SettingGame.png","ButtonEdge/SettingGame.png","Music/move.wav",GameConstant.BUTTON_SETTING,true);

    }
    public void CheckButtonEvent(float delta){

        //------------------------------------------------------------------------------------------
        //-----------Nut leaderboard----------------------------------------------------------------
        LeaderBoard.isClickLatch=true;
        if (LeaderBoard.checkOnClick()){
            if (!isLeaderBoard){
                isLeaderBoard=true;
                LeaderBoard.isClick=true;
            }
        }
        else {
            if (isLeaderBoard){
                isLeaderBoard=false;
                LeaderBoard.isClick=false;
                BrickPuzzle1.playservices.showLeaderBoard();
                //game.setScreen(SettingScreen.getInstance(game,false,0));
            }
        }
        //------------------------------------------------------------------------------------------
        //-----------Nut play-----------------------------------------------------------------------
        PlayGame.isClickLatch=true;
        if (PlayGame.checkOnClick()){
            if (!isPlayGame){
                isPlayGame=true;
                PlayGame.isClick=true;
            }
        }
        else {
            if (isPlayGame){
                isPlayGame=false;
                PlayGame.isClick=false;
                MainMenuScreen.firstTimePlay=false;
                game.setScreen(PlayScreen.getInstance(game,true));
            }
        }
        //------------------------------------------------------------------------------------------
        //-----------Nut setting--------------------------------------------------------------------
        SettingGame.isClickLatch=true;
        if (SettingGame.checkOnClick()){
            if (!isSettingGame){
                isSettingGame=true;
                SettingGame.isClick=true;
            }
        }
        else {
            if (isSettingGame){
                isSettingGame=false;
                SettingGame.isClick=false;
                game.setScreen(SettingScreen.getInstance(game,0,true));//

            }
        }
        //------------------------------------------------------------------------------------------
    }

    public void  GameUpdate(float deltaTime, SpriteBatch batch){
        CheckButtonEvent(deltaTime);
        LeaderBoard.render(batch);
        PlayGame.render(batch);
        SettingGame.render(batch);
        batch.begin();
        if (MainMenuScreen.firstTimePlay){
            GameAsset.GoodMorningfont.draw(batch,"WELCOME", GameConstant._WELCOME_STR.x,GameConstant._WELCOME_STR.y,0, Align.center,false);
            GameAsset.GoodMorningfont.draw(batch,"TO",GameConstant._TO_STR.x,GameConstant._TO_STR.y,0, Align.center,false);
            GameAsset.GoodMorningfont.draw(batch,"BRICK PUZZLE",GameConstant._BLOCKPZZLE_STR.x,GameConstant._BLOCKPZZLE_STR.y,0, Align.center,false);
            //GameAsset.GoodMorningfont.draw(batch,"8BIT",GameConstant._8BIT_STR.x,GameConstant._8BIT_STR.y,0, Align.center,false);
        }
        else {
            GameAsset.GoodMorningfont.draw(batch,"HI-SCORE",GameConstant._WELCOME_STR.x,GameConstant._WELCOME_STR.y,0, Align.center,false);
            GameAsset.GoodMorningfont.draw(batch,String.format("%d", GameJson.gameJsonData.HiScore),GameConstant._TO_STR.x,GameConstant._TO_STR.y,0, Align.center,false);
            GameAsset.GoodMorningfont.draw(batch,"SCORE",GameConstant._BLOCKPZZLE_STR.x,GameConstant._BLOCKPZZLE_STR.y,0, Align.center,false);
            GameAsset.GoodMorningfont.draw(batch,String.format("%d",GameJson.gameJsonData.Score),GameConstant._8BIT_STR.x,GameConstant._8BIT_STR.y,0, Align.center,false);
        }
        batch.end();
    }
    public void dispose(){
        LeaderBoard.dipose();
        PlayGame.dipose();
        SettingGame.dipose();
    }
}
