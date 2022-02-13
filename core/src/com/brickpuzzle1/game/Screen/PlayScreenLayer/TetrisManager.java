package com.brickpuzzle1.game.Screen.PlayScreenLayer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.brickpuzzle1.game.Assets.GameJson;

public class TetrisManager {
    // chi chua cac bien khi chay game
    public int hi_score;
    public int score;
    public int level;

    public boolean is_music;
    public boolean is_vibrate;

    public boolean is_pause;
    public boolean is_gameover;

    public void TetrisManager(){
        score=0;
        level=0;
        is_pause=false;
        is_gameover=false;
    }
    public void render(float delta, SpriteBatch batch){
        Gdx.app.log("Score",Integer.toString(score));
        if(hi_score<score){
            hi_score=score;
            GameJson.gameJsonData.HiScore=hi_score;
        }
    }
    public void load_from_json(){
        hi_score=GameJson.gameJsonData.HiScore;
        is_music=GameJson.gameJsonData.isMusic;
        is_vibrate=GameJson.gameJsonData.isVibrate;
    }
    public void save_to_json(){// sau khi cai dat xong thi se cho luu du lieu
        GameJson.gameJsonData.HiScore=hi_score;
        GameJson.gameJsonData.isMusic=is_music;
        GameJson.gameJsonData.isVibrate=is_vibrate;
        GameJson.save();
    }
    public void dispose(){

    }
}
