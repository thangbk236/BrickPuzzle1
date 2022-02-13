package com.brickpuzzle1.game.Assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;

import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;


public class GameAsset {

    // Font
    public static BitmapFont font;
    public static BitmapFont bigfont;
    public static BitmapFont normalfont;
    public static FreeTypeFontGenerator generator;

    public static FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    public static BitmapFont GoodMorningBigfont;
    public static BitmapFont GoodMorningfont;
    public static FreeTypeFontGenerator GoodMorninggenerator;
    public static FreeTypeFontGenerator.FreeTypeFontParameter GoodMorningparameter;

    // GameBrick
    public static Texture BrickTexture48;
    public static Sprite BrickSprite48;
    public static Texture BrickTexture72;
    public static Sprite BrickSprite72;
    //GameSound
    public static Sound eatSound;
    public static Sound gameOverSound;
    public static Sound moveSound;
    public static Sound rotateSound;
    public static Sound runSound;
    public GameAsset(){

        GameLoadFont();
        GameLoadBrick();
        GameLoadSound();
    }
    public void GameLoadSound(){
        eatSound=Gdx.audio.newSound(Gdx.files.internal("Music/eat.wav"));
        gameOverSound=Gdx.audio.newSound(Gdx.files.internal("Music/gameover.wav"));
        moveSound=Gdx.audio.newSound(Gdx.files.internal("Music/move.wav"));
        rotateSound=Gdx.audio.newSound(Gdx.files.internal("Music/rotate.wav"));
        runSound=Gdx.audio.newSound(Gdx.files.internal("Music/run.wav"));

    }

    public void GameLoadBrick(){
        BrickTexture48  = new Texture("Brick/Brick48.png");
        BrickSprite48 = new Sprite(BrickTexture48);
        BrickTexture72  = new Texture("Brick/Brick72.png");
        BrickSprite72 = new Sprite(BrickTexture72);
    }
    public void GameLoadFont(){
        generator = new FreeTypeFontGenerator(Gdx.files.internal("Font/Pixel_LCD-7.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 64;
        parameter.color = Color.BLACK;
        parameter.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!'()>?:-";
        font = generator.generateFont(parameter);
        font.setColor(1.0f,1.0f,1.0f,0.6f);
        parameter.size = 192;
        bigfont = generator.generateFont(parameter);
        bigfont.setColor(1.0f,1.0f,1.0f,0.8f);
        parameter.size = 96;
        normalfont = generator.generateFont(parameter);
        normalfont.setColor(1.0f,1.0f,1.0f,0.8f);

        GoodMorninggenerator = new FreeTypeFontGenerator(Gdx.files.internal("Font/GoodMorning.ttf"));
        GoodMorningparameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        GoodMorningparameter.size = 112;
        GoodMorningparameter.color = Color.BLACK;
        GoodMorningparameter.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!'()>?:-";
        GoodMorningfont = GoodMorninggenerator.generateFont(GoodMorningparameter);
        GoodMorningfont.setColor(1.0f,1.0f,1.0f,1.0f);

        GoodMorningparameter.size = 336;
        GoodMorningBigfont = GoodMorninggenerator.generateFont(GoodMorningparameter);
        GoodMorningBigfont.setColor(1.0f,1.0f,1.0f,1.0f);
    }
}
