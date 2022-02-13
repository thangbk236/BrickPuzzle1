package com.brickpuzzle1.game.Screen.PlayScreenLayer;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.brickpuzzle1.game.Assets.GameButton;
import com.brickpuzzle1.game.Assets.GameConstant;
import com.brickpuzzle1.game.Screen.SettingScreen;

public class PlayButtonLayer {
    protected final Game game;
    private GameButton GamePlayUpButton;
    private GameButton GamePlayDownButton;
    private GameButton GamePlayLeftButton;
    private GameButton GamePlayRightButton;
    private GameButton GamePlayFireButton;
    private GameButton GamePlayRunButton;
    private GameButton GamePlayStopButton;
    private GameButton GamePlaySettingButton;

    public static boolean isUpButton;
    public static boolean isDownButton;
    public static boolean isLeftButton;
    public static boolean isRightButton;
    public static boolean isFireButton;
    public static boolean isRunButton;
    public static boolean isStopButton;
    public boolean isPlaySettingButton;
    public PlayButtonLayer(Game game){
        this.game=game;
        Init();
    }
    public void Init(){
        GamePlayUpButton = new GameButton("Button/GamePlayUpButton.png","Button/_GamePlayUpButton.png","Music/move.wav", GameConstant.BUTTON_UP,true);
        GamePlayDownButton = new GameButton("Button/GamePlayDownButton.png","Button/_GamePlayDownButton.png","Music/move.wav",GameConstant.BUTTON_DOWN,true);
        GamePlayLeftButton = new GameButton("Button/GamePlayLeftButton.png","Button/_GamePlayLeftButton.png","Music/move.wav",GameConstant.BUTTON_LEFT,true);
        GamePlayRightButton = new GameButton("Button/GamePlayRightButton.png","Button/_GamePlayRightButton.png","Music/move.wav",GameConstant.BUTTON_RIGHT,true);
        GamePlayFireButton = new GameButton("Button/GamePlayFireButton.png","Button/_GamePlayFireButton.png","Music/rotate.wav",GameConstant.BUTTON_FIRE,true);
        GamePlayRunButton = new GameButton("Button/GamePlayRunButton.png","Button/_GamePlayRunButton.png","Music/run.wav",GameConstant.BUTTON_RUN,true);
        GamePlayStopButton = new GameButton("Button/GamePlayStopButton.png","Button/_GamePlayStopButton.png","Music/move.wav",GameConstant.BUTTON_STOP,true);
        GamePlaySettingButton = new GameButton("ButtonEdge/SettingGame.png","ButtonEdge/SettingGame.png","Music/move.wav",GameConstant.BUTTON_SETTING,true);
    }

    public void CheckButtonEvent(float delta){
        GamePlayUpButton.isClickLatch=true;
        if (GamePlayUpButton.checkOnClick()){
            if (!isUpButton){
                isUpButton=true;
                GamePlayUpButton.isClick=true;
            }
        }
        else {
            if (isUpButton){
                isUpButton=false;
                GamePlayUpButton.isClick=false;
            }
        }
        GamePlayDownButton.isClickLatch=true;
        if (GamePlayDownButton.checkOnClick()){
            if (!isDownButton){
                isDownButton=true;
                GamePlayDownButton.isClick=true;
            }
        }
        else {
            if (isDownButton){
                isDownButton=false;
                GamePlayDownButton.isClick=false;
            }
        }
        GamePlayLeftButton.isClickLatch=true;
        if (GamePlayLeftButton.checkOnClick()){
            if (!isLeftButton){
                isLeftButton=true;
                GamePlayLeftButton.isClick=true;
            }
        }
        else {
            if (isLeftButton){
                isLeftButton=false;
                GamePlayLeftButton.isClick=false;
            }
        }
        GamePlayRightButton.isClickLatch=true;
        if (GamePlayRightButton.checkOnClick()){
            if (!isRightButton){
                isRightButton=true;
                GamePlayRightButton.isClick=true;
            }
        }
        else {
            if (isRightButton){
                isRightButton=false;
                GamePlayRightButton.isClick=false;
            }
        }
        GamePlayFireButton.isClickLatch=true;
        if (GamePlayFireButton.checkOnClick()){
            if (!isFireButton){
                isFireButton=true;
                GamePlayFireButton.isClick=true;
            }
        }
        else {
            if (isFireButton){
                isFireButton=false;
                GamePlayFireButton.isClick=false;
            }
        }
        GamePlayRunButton.isClickLatch=true;
        if (GamePlayRunButton.checkOnClick()){
            if (!isRunButton){
                isRunButton=true;
                GamePlayRunButton.isClick=true;
            }
        }
        else {
            if (isRunButton){
                isRunButton=false;
                GamePlayRunButton.isClick=false;
            }
        }
        GamePlayStopButton.isClickLatch=true;
        if (GamePlayStopButton.checkOnClick()){
            if (!isStopButton){
                isStopButton=true;
                GamePlayStopButton.isClick=true;
            }
        }
        else {
            if (isStopButton){
                isStopButton=false;
                GamePlayStopButton.isClick=false;
            }
        }
        GamePlaySettingButton.isClickLatch=true;
        if (GamePlaySettingButton.checkOnClick()){
            if (!isPlaySettingButton){
                isPlaySettingButton=true;
                GamePlaySettingButton.isClick=true;
            }
        }
        else {
            if (isPlaySettingButton){
                isPlaySettingButton=false;
                GamePlaySettingButton.isClick=false;
                game.setScreen(SettingScreen.getInstance(game,1,true));
            }
        }
    }

    public void render(float deltaTime, SpriteBatch batch){
        CheckButtonEvent(deltaTime);
        //Gdx.gl.glClearColor(0.8f, 0.8f, 0.8f, 0.8f);
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        GamePlayUpButton.render(batch);
        GamePlayDownButton.render(batch);
        GamePlayLeftButton.render(batch);
        GamePlayRightButton.render(batch);
        GamePlayFireButton.render(batch);
        GamePlayRunButton.render(batch);
        GamePlayStopButton.render(batch);
        GamePlaySettingButton.render(batch);
    }
    public void dipose(){
        GamePlayUpButton.dipose();
        GamePlayDownButton.dipose();
        GamePlayLeftButton.dipose();
        GamePlayRightButton.dipose();
        GamePlayFireButton.dipose();
        GamePlayRunButton.dipose();
        GamePlayStopButton.dipose();
        GamePlaySettingButton.dipose();
    }
}
