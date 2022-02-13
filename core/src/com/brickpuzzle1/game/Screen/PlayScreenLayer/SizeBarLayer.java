package com.brickpuzzle1.game.Screen.PlayScreenLayer;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.brickpuzzle1.game.Assets.GameAsset;
import com.brickpuzzle1.game.Assets.GameConstant;

public class SizeBarLayer {
    TetrisManager tetrisManager;
    public SizeBarLayer(TetrisManager tetrisManager){
        this.tetrisManager = tetrisManager;
    }
    public void render(float deltaTime, SpriteBatch batch){

        batch.begin();
        GameAsset.font.draw(batch,"HI-SCORE", GameConstant.HI_SCORE_STR.x,GameConstant.HI_SCORE_STR.y,0,Align.center,false);
        GameAsset.font.draw(batch,"SCORE",GameConstant.SCORE_STR.x,GameConstant.SCORE_STR.y,0, Align.center,false);
        GameAsset.font.draw(batch,"LEVEL",GameConstant.LEVEL_STR.x,GameConstant.LEVEL_STR.y,0,Align.center,false);

        GameAsset.normalfont.draw(batch,String.format("%d",tetrisManager.hi_score),GameConstant._HI_SCORE_STR.x,GameConstant._HI_SCORE_STR.y,0,Align.center,false);
        GameAsset.normalfont.draw(batch,String.format("%d",tetrisManager.score),GameConstant._SCORE_STR.x,GameConstant._SCORE_STR.y,0,Align.center,false);
        GameAsset.normalfont.draw(batch,String.format("%d",tetrisManager.level),GameConstant._LEVEL_STR.x,GameConstant._LEVEL_STR.y,0,Align.center,false);
        batch.end();
    }
    public void dispose(){

    }
}
