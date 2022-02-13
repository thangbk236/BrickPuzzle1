package com.brickpuzzle1.game.Screen.MainMenuScreenLayer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.brickpuzzle1.game.Assets.GameConstant;

public class GridLayer {
    // GameBrick
    public static Texture BrickTexture48;
    public static Sprite BrickSprite48;
    public static Texture BrickTexture72;
    public static Sprite BrickSprite72;

    public float TransparentHi = 0.3f;
    public float TransparentLo = 0.1f;
    public int GridMode;
    public GridLayer(int GridMode){
        this.GridMode=GridMode;


        BrickTexture48  = new Texture("Brick/Brick48.png");
        BrickSprite48 = new Sprite(BrickTexture48);
        BrickTexture72  = new Texture("Brick/Brick72.png");
        BrickSprite72 = new Sprite(BrickTexture72);
    }
    public void GameUpdate(float deltaTime, SpriteBatch batch) {

        batch.begin();
        switch (GridMode){
            case 0: // man hinh home
                for (int x = 0; x< GameConstant.HOME_GAME_GRID.x; x++){ // truc x
                    for (int y=0;y<GameConstant.HOME_GAME_GRID.y;y++){ // truc y
                        CalignPosition(batch, BrickSprite72,x,y, GameConstant.HOME_GAME_GRID_OFFSET,TransparentLo);
                    }
                }
                break;
            case 1: // man hinh game play
                for (int x=0;x<GameConstant.MAIN_GAME_GRID.x;x++){ // truc x
                    for (int y=0;y<GameConstant.MAIN_GAME_GRID.y;y++){ // truc y
                        CalignPosition(batch, BrickSprite72,x,y, GameConstant.MAIN_GAME_GRID_OFFSET,TransparentLo);
                    }
                }
                for (int x=0;x<GameConstant.SUB_GAME_GRID.x;x++){ // truc x
                    for (int y=0;y<GameConstant.SUB_GAME_GRID.y;y++){ // truc y
                        CalignPosition(batch, BrickSprite48,x,y, GameConstant.SUB_GAME_GRID_OFFSET,TransparentLo);
                    }
                }
                break;
            case 2:
                break;
        }
        batch.end();
    }
    public void CalignPosition(SpriteBatch batch, Sprite sprite, int x, int y, Vector2 offsetVal, float transparent){
        sprite.setPosition(offsetVal.x+sprite.getHeight()*x,offsetVal.y+sprite.getHeight()*y);
        sprite.draw(batch,transparent);
    }
    public void dispose(){
        BrickTexture48.dispose();
        BrickTexture72.dispose();
    }
}
