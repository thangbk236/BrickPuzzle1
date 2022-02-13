package com.brickpuzzle1.game.Assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class GameConstant {

    public static final float   V_HEIGHT       = Gdx.graphics.getHeight();     // gia tri chieu cao man hinh mac dinh
    public static final float   V_WIDTH       = Gdx.graphics.getWidth();      // gia tri chieu rong man hinh mac dinh

    public static final float   S_HEIGHT       = 1440.0f;     // gia tri chieu cao man hinh sau khi fix
    public static final float   S_WIDTH       = V_WIDTH*S_HEIGHT/V_HEIGHT;      // gia tri chieu rong man hinh sau khi fix

    //----------------------------------------------------------------------------------------------
    // MenuSetting
    //
    // Trang thai cac nut
    public static final boolean isNewGame = false;
    public static final boolean isSoundOn = false;
    public static final boolean isVibrate = false;
    public static final boolean isExit = false;
    public static final boolean isClose = false;




    //
    public static final Vector2 _WELCOME_STR = new Vector2(S_WIDTH/2.0f,S_HEIGHT-72*0.2f);
    public static final Vector2 _TO_STR = new Vector2(S_WIDTH/2.0f,S_HEIGHT-72*3.0f);
    public static final Vector2 _BLOCKPZZLE_STR = new Vector2(S_WIDTH/2.0f,S_HEIGHT-72*6.0f);
    public static final Vector2 _8BIT_STR = new Vector2(S_WIDTH/2.0f,S_HEIGHT-72*9.0f);

    //
    public static final Vector2 MAIN_GAME_GRID = new Vector2(12.0f,20.0f);// luoi ma tran 12x20
    public static final Vector2 MAIN_GAME_GRID_OFFSET = new Vector2((S_WIDTH-MAIN_GAME_GRID.x*72)/2.0f,0.0f);// offset cho luoi ma tran

    public static final Vector2 SUB_GAME_GRID = new Vector2(4.0f,4.0f);// luoi ma tran 12x20
    public static final Vector2 SUB_GAME_GRID_OFFSET = new Vector2((MAIN_GAME_GRID_OFFSET.x-(4.0f)*72),(MAIN_GAME_GRID.y-4.0f)*72);// offset cho luoi ma tran

    public static final Vector2 HOME_GAME_GRID = new Vector2(20.0f,20.0f);// luoi ma tran 20x20
    public static final Vector2 HOME_GAME_GRID_OFFSET = new Vector2((S_WIDTH-72*HOME_GAME_GRID.x)/2.0f,0.0f);// offset cho luoi ma tran
    //
    public static final Vector2 BUTTON_LEADERBOARD = new Vector2(HOME_GAME_GRID_OFFSET.x+72+512/2.0f,72+512/2.0f);
    public static final Vector2 BUTTON_PLAYGAME = new Vector2(HOME_GAME_GRID_OFFSET.x+72*(HOME_GAME_GRID.x-1.0f)-512/2.0f,72+512/2.0f);
    public static final Vector2 BUTTON_SETTING = new Vector2(48+88,S_HEIGHT-48-88);

    // toa do cac nut setting
    public static final Vector2 SettingButtonSize = new Vector2(432,144);
    public static final Vector2 SettingButtonSpace = new Vector2(0, 15);
    public static final Vector2 SettingBackGround = new Vector2(S_WIDTH/2.0f,S_HEIGHT/2.0f);
    public static final Vector2 SettingSoundOn = new Vector2(SettingBackGround.x,SettingBackGround.y+SettingButtonSpace.y+SettingButtonSize.y);
    public static final Vector2 SettingVibrate = new Vector2(SettingBackGround.x,SettingBackGround.y-SettingButtonSpace.y-SettingButtonSize.y);
    public static final Vector2 SettingNewGame = new Vector2(SettingBackGround.x, SettingBackGround.y+SettingButtonSpace.y*3.0f+SettingButtonSize.y*3.0f);
    public static final Vector2 SettingExit = new Vector2(SettingBackGround.x, SettingBackGround.y-SettingButtonSpace.y*3.0f-SettingButtonSize.y*3.0f);
    public static final Vector2 SettingClose = new Vector2(BUTTON_SETTING.x,BUTTON_SETTING.y);
    // toa do cac nut play
    public static final Vector2 BUTTON_CENTER = new Vector2(420.0f,380.0f);
    public static final Vector2 BUTTON_FIX = new Vector2(240.0f,210.0f);

    public static final Vector2 BUTTON_FIRE = new Vector2(S_WIDTH-384/2.0f-100.0f,BUTTON_CENTER.y);
    public static final Vector2 BUTTON_RUN = new Vector2(BUTTON_FIRE.x-384/2.0f-100.0f,BUTTON_CENTER.y-BUTTON_FIX.y);


    public static final Vector2 BUTTON_UP = new Vector2(BUTTON_CENTER.x,BUTTON_CENTER.y+BUTTON_FIX.y);
    public static final Vector2 BUTTON_DOWN = new Vector2(BUTTON_CENTER.x,BUTTON_CENTER.y-BUTTON_FIX.y);
    public static final Vector2 BUTTON_LEFT = new Vector2(BUTTON_CENTER.x-BUTTON_FIX.x,BUTTON_CENTER.y);
    public static final Vector2 BUTTON_RIGHT = new Vector2(BUTTON_CENTER.x+BUTTON_FIX.x,BUTTON_CENTER.y);
    public static final Vector2 BUTTON_STOP =new Vector2(BUTTON_LEFT.x,BUTTON_LEFT.y+288+96);

    // SCORE

    public static final Vector2 HI_SCORE_STR = new Vector2((S_WIDTH+MAIN_GAME_GRID_OFFSET.x+MAIN_GAME_GRID.x*72)/2.0f,S_HEIGHT-72*1.0f);
    public static final Vector2 SCORE_STR = new Vector2(HI_SCORE_STR.x,HI_SCORE_STR.y-72*3.5f);
    public static final Vector2 LEVEL_STR = new Vector2(HI_SCORE_STR.x,HI_SCORE_STR.y-72*7.0f);

    public static final Vector2 _HI_SCORE_STR = new Vector2(HI_SCORE_STR.x,HI_SCORE_STR.y-72*1.5f);
    public static final Vector2 _SCORE_STR = new Vector2(HI_SCORE_STR.x,HI_SCORE_STR.y-72*5.0f);
    public static final Vector2 _LEVEL_STR = new Vector2(HI_SCORE_STR.x,HI_SCORE_STR.y-72*8.5f);

}
