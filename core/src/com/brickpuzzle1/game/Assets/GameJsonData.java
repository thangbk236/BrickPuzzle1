package com.brickpuzzle1.game.Assets;

public class GameJsonData {
    public int HiScore;
    public int Score;
    public int Level;
    public boolean isVibrate;
    public boolean isMusic;
    public GameJsonData(){
        HiScore=0;
        Score=0;
        Level=0;
        isVibrate=true;
        isMusic=true;
    }
}
