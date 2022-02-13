package com.brickpuzzle1.game.Assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;

public class GameJson {
    public static FileHandle fileHandle = Gdx.files.local("TetrisJson.json");
    public static Json json = new Json();
    public static GameJsonData gameJsonData;
    public static void save() {
        json.setOutputType(JsonWriter.OutputType.json);
        fileHandle.writeString(json.prettyPrint(gameJsonData),false);
    }
    public static void load() {
        gameJsonData = new GameJsonData();
        if (saveFileExists()) {    // neu file chi dinh ton tai
            gameJsonData=json.fromJson(GameJsonData.class,fileHandle);
        }
    }
    public static boolean saveFileExists() {
        return fileHandle.exists();
    }
}