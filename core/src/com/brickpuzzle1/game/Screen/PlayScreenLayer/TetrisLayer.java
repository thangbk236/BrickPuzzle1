package com.brickpuzzle1.game.Screen.PlayScreenLayer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.brickpuzzle1.game.Assets.GameAsset;
import com.brickpuzzle1.game.Assets.GameConstant;

public class TetrisLayer {
    //--------------------------
    TetrisManager tetrisManager;
    // Matrix
    public int[][] Tetrisgrid;
    public int[][] TetrisgridRotate;
    public int[][] MapGrid;
    public int[][] NewTetrisGrid;
    //Positon
    public Vector2 TetrisPosition;
    public Vector2 TetrisPositionMove;
    public Vector2 MatrixMove;

    //Tetris level
    public int TetrisLine;
    public int TetrisLevel;

    public boolean inFloor;
    public boolean FirstPlay;
    //Score
    public int SumScore;
    public boolean isSumScore;
    //TetrisButtonTime
    public boolean TimeLeftLatch;
    public boolean TimeRightLatch;
    public float TimeLeft;
    public float TimeRight;
    public float TimeDown;

    public float DownSpeed;
    public float _ButtonDownSpeed;
    public float FloorTime;
    public float LimitRunTime;
    //Limit Dir
    public boolean RotateLatch;
    public boolean LimitDown;
    public boolean LimitRunLatch;
    public boolean TimeDownLatch;
    public boolean GameOverView;

    public float gameGetMapTime;
    public boolean gameGetMapLock;

    public TetrisLayer(TetrisManager tetrisManager){
        this.tetrisManager=tetrisManager;
        Init();
    }
    public void Init(){
        Init_Val();
        InitMap();
        InitTetris();
    }
    private void Init_Val(){
        tetrisManager.score=0;
        tetrisManager.level=0;
        TetrisLine=0;

        tetrisManager.is_gameover=false;
        GameOverView=false;
        FirstPlay = true;
        inFloor = false;
        gameGetMapLock=false;

        TetrisPositionMove = new Vector2();
        TetrisPosition = new Vector2();
    }

    private void InitMap(){
        int[][] _MapGrid = {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},// cot 0
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},// cot 1
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};// cot n
        MapGrid = new int[_MapGrid.length][_MapGrid[0].length];
        MapGrid = _MapGrid;
    }
    public void render(float delta, SpriteBatch batch) {
        if(!gameGetMapLock){
            CheckButton(delta);
        }
        GameGetMap(delta);
    }

    private void CheckButton(float delta){
        CheckRotate(delta);
        CheckLeft(delta);
        CheckRight(delta);
        CheckRun(delta);
        CheckDown(delta);
    }
    private void CheckLeft(float delta){
        if(PlayButtonLayer.isLeftButton){
            // khi duoc bam nut lan dau tien thi se cho dich sang trai 1 don vi
            // sau mot khoang thoi gian bam nut thi se tang toc do dich len
            if (!TimeLeftLatch){
                TimeLeftLatch=true;
                TimeLeft=0.0f;
                TetrisPosition.x-=1.0f;
                if (CheckMatrixCollision(MapGrid,Tetrisgrid,TetrisPosition))   {
                    TetrisPosition.x+=1.0f;
                }
            }
            else {
                TimeLeft+=delta;
                if (TimeLeft>=1.0f/8.0f){
                    TimeLeft=0.0f;
                    TetrisPosition.x-=1.0f;
                    if (CheckMatrixCollision(MapGrid,Tetrisgrid,TetrisPosition))   {
                        TetrisPosition.x+=1.0f;
                    }
                }
            }
        }
        else {
            TimeLeftLatch=false;
        }
    }
    private void CheckRight(float delta){
        if(PlayButtonLayer.isRightButton){
            // khi duoc bam nut lan dau tien thi se cho dich sang trai 1 don vi
            // sau mot khoang thoi gian bam nut thi se tang toc do dich len
            if (!TimeRightLatch){
                TimeRightLatch=true;
                TimeRight=0.0f;
                TetrisPosition.x+=1.0f;
                if (CheckMatrixCollision(MapGrid,Tetrisgrid,TetrisPosition))   {
                    TetrisPosition.x-=1.0f;
                }
            }
            else {
                TimeRight+=delta;
                if (TimeRight>=1.0f/8.0f){
                    TimeRight=0.0f;
                    TetrisPosition.x+=1.0f;
                    if (CheckMatrixCollision(MapGrid,Tetrisgrid,TetrisPosition))   {
                        TetrisPosition.x-=1.0f;
                    }
                }
            }
        }
        else {
            TimeRightLatch=false;
        }
    }
    private void CheckRun(float delta){
        // neu nut run duoc bam thi se cho tetris xuong duoi ngay lap tuc
        // tim toa do cua bong tetris thap nhat
        MatrixMove = new Vector2(0.0f,0.0f);
        for (int y=(int)TetrisPosition.y;y>0;y--){
            MatrixMove.y=y;
            MatrixMove.x=TetrisPosition.x;
            if (CheckMatrixCollision(MapGrid,Tetrisgrid,MatrixMove))   {
                MatrixMove.y+=1.0f;
                break;
            }
        }
        TetrisPositionMove=MatrixMove;
        if (PlayButtonLayer.isRunButton){
            if(!LimitRunLatch){
                LimitRunLatch=true;
                inFloor=true;
            }
        }
        else {
            if (LimitRunLatch) {
                LimitRunTime += delta;
                if (LimitRunTime > 0.1f) {
                    LimitRunTime = 0.0f;
                    LimitRunLatch = false;
                }
            }
        }
    }

    private void CheckDown(float delta){
        if(PlayButtonLayer.isDownButton){
            _ButtonDownSpeed=5.0f;
        }
        else {
            _ButtonDownSpeed=0.0f;
        }

        DownSpeed=3.0f+_ButtonDownSpeed+(float)tetrisManager.level*0.2f;
        if(DownSpeed>15.0f){
            DownSpeed=15.0f;
        }
        TimeDown+=delta;
        if (TimeDown>1.0f/DownSpeed){
            TimeDown=0.0f;
            TimeDownLatch=true;
        }
        if(TimeDownLatch){
            TimeDownLatch=false;
            TetrisPosition.y-=1.0f;
            if (CheckMatrixCollision(MapGrid,Tetrisgrid,TetrisPosition))   {
                TetrisPosition.y+=1.0f;
                LimitDown=true;
            }
        }
        if(LimitDown){
            FloorTime+=delta;
            if (FloorTime>0.25f){
                FloorTime=0.0f;
                inFloor=true;
            }
        }
        if(inFloor){
            FloorTime=0.0f;
            LimitDown=false;
            TetrisPosition=TetrisPositionMove;
        }
    }
    private void CheckRotate(float delta){
        // neu nut xoay duoc bam thi se kiem tra khi tetris xoay
        if(PlayButtonLayer.isUpButton||PlayButtonLayer.isFireButton){
            if(!RotateLatch){
                RotateLatch=true;
                TetrisgridRotate=Rotate(Tetrisgrid);    // tao mot khoi xoay
                //==================================================================================

                if (!CheckMatrixCollision(MapGrid,TetrisgridRotate,TetrisPosition)){
                    Tetrisgrid=TetrisgridRotate;
                    return;
                }
                //==================================================================================
                TetrisPosition.x-=1;
                if (!CheckMatrixCollision(MapGrid,TetrisgridRotate,TetrisPosition)){
                    Tetrisgrid=TetrisgridRotate;
                    return;
                }
                TetrisPosition.x+=1;

                TetrisPosition.x+=1;
                if (!CheckMatrixCollision(MapGrid,TetrisgridRotate,TetrisPosition)){
                    Tetrisgrid=TetrisgridRotate;
                    return;
                }
                TetrisPosition.x-=1;
                //==================================================================================
                TetrisPosition.y+=1;
                if (!CheckMatrixCollision(MapGrid,TetrisgridRotate,TetrisPosition)){
                    Tetrisgrid=TetrisgridRotate;
                    return;
                }
                TetrisPosition.y-=1;
                //==================================================================================
                TetrisPosition.x-=2;
                if (!CheckMatrixCollision(MapGrid,TetrisgridRotate,TetrisPosition)){
                    Tetrisgrid=TetrisgridRotate;
                    return;
                }
                TetrisPosition.x+=2;

                TetrisPosition.x+=2;
                if (!CheckMatrixCollision(MapGrid,TetrisgridRotate,TetrisPosition)){
                    Tetrisgrid=TetrisgridRotate;
                    return;
                }
                TetrisPosition.x-=2;
                //==================================================================================
            }
        }
        else {
            RotateLatch=false;
        }
    }


    private void GameGetMap(float delta){
        if (inFloor){
            gameGetMapLock=true;
            gameGetMapTime+=delta;
            if(gameGetMapTime>0.25f){
                gameGetMapTime=0.0f;
                EditMap();
                ClearMap();
                CheckGameOver();
                InitTetris();
                inFloor = false;
                gameGetMapLock=false;
            }
        }
    }
    private void EditMap(){
        for (int x = 0; x < Tetrisgrid.length; x++) {
            for (int y = 0; y < Tetrisgrid[0].length; y++) {
                if (Tetrisgrid[x][y]!=0) {
                    MapGrid[(int)TetrisPosition.x+x][(int)TetrisPosition.y+y]=1;
                }
            }
        }
    }

    private void ClearMap() {
        SumScore=0;
        isSumScore=false;
        for (int y=3;y<MapGrid[0].length-3;y++){
            boolean LineFull=false;
            for (int x=3;x<MapGrid.length-3;x++){
                if (MapGrid[x][y]==0){
                    LineFull=true;
                    break;
                }
            }
            if (!LineFull){
                DeleteLine(y);
                y=y-1;
                SumScore+=1;
                TetrisLine++;
                isSumScore=true;
            }
        }
        if(isSumScore){
            isSumScore=false;
            tetrisManager.score+=GetScore(SumScore);
            tetrisManager.level=GetLevel(TetrisLine);
            if (tetrisManager.is_music){
                GameAsset.eatSound.play();
            }
            if (tetrisManager.is_vibrate){
                Gdx.input.vibrate(80);
            }
        }
    }
    private int GetLevel(int val){
        return val/10;
    }
    private int GetScore(int val){
        int new_score;
            switch (val){
                case 1:
                    new_score = 10*(tetrisManager.level+1);
                    break;
                case 2:
                    new_score = 20*(tetrisManager.level+1);
                    break;
                case 3:
                    new_score = 50*(tetrisManager.level+1);
                    break;
                case 4:
                    new_score = 100*(tetrisManager.level+1);
                    break;
                default:
                    new_score=0;
            }
        return new_score;
    }
    private void DeleteLine(int index) {
        for (int y = index; y<MapGrid[0].length-3; y++) {
            for (int x = 3; x < MapGrid.length-3; x++) {
                MapGrid[x][y] = MapGrid[x][y+1];
            }
        }
    }
    private void CheckGameOver(){
        for (int x=3;x<MapGrid.length-3;x++){
            if (MapGrid[x][MapGrid[0].length-4]==1){
                tetrisManager.is_gameover=true;
                if (tetrisManager.is_music){
                    GameAsset.gameOverSound.play();
                }
                if (tetrisManager.is_vibrate){
                    Gdx.input.vibrate(100);
                }
            }
        }
    }

    private boolean CheckMatrixCollision(int[][] Matrix1,int[][] Matrix2,Vector2 MatrixPos){
        // chi don gian la kiem tra xem brick co nam trong grid khong, neu nam trong grid thi se tra ve true, neu khong tra ve false
        // Matrix1 MapGrid
        // Matrix2 TetrisGrid
        for(int x=0;x<Matrix2[0].length;x++){
            for(int y=0;y<Matrix2[0].length;y++){
                if(Matrix2[x][y]==1){
                    if(Matrix1[x+(int)MatrixPos.x][y+(int)MatrixPos.y]==1){
                        return true;
                    }
                }
            }
        }
        return  false;
    }

    public void GameDraw(SpriteBatch batch){
        batch.begin();
        for (int x=0;x<Tetrisgrid.length;x++){
            for (int y=0;y<Tetrisgrid[0].length;y++){
                if(GameOverView){
                    if (TetrisPosition.y+y>=16&&TetrisPosition.y+y<20){
                        continue;
                    }
                }
                if (Tetrisgrid[x][y]!=0){
                    CalignPosition(batch, GameAsset.BrickSprite72,TetrisPosition.x+x,TetrisPosition.y+y, GameConstant.MAIN_GAME_GRID_OFFSET,1.0f);
                }
            }
        }
        for (int x=0;x<Tetrisgrid.length;x++){
            for (int y=0;y<Tetrisgrid[0].length;y++){
                if(GameOverView){
                    if (TetrisPositionMove.y+y>=16&&TetrisPositionMove.y+y<20){
                        continue;
                    }
                }
                if (Tetrisgrid[x][y]!=0){
                    CalignPosition(batch, GameAsset.BrickSprite72,TetrisPositionMove.x+x,TetrisPositionMove.y+y, GameConstant.MAIN_GAME_GRID_OFFSET,0.2f);
                }
            }
        }

        for (int x=0;x<NewTetrisGrid.length;x++){
            for (int y=0;y<NewTetrisGrid[0].length;y++){
                if (NewTetrisGrid[x][y]!=0){
                    CalignPosition(batch, GameAsset.BrickSprite48,x+3,y+3, GameConstant.SUB_GAME_GRID_OFFSET,1.0f);
                }
            }
        }
        for (int x = 3; x < MapGrid.length-3; x++) {//MapGrid.length so cot trong mang, voi ma tran [18][27] thi se tra ve 18, ung voi so luong truc x
            for (int y = 3; y < MapGrid[0].length-4; y++) {// MapGrid[0].length so phan tu trong mang thu nhat, voi ma tran [18][27] thi se tra ve 27, ung voi so luong truc y
                if(GameOverView){
                    if (y>=16&&y<20){
                        continue;
                    }
                }
                if (MapGrid[x][y]!=0) {
                    CalignPosition(batch, GameAsset.BrickSprite72,x,y, GameConstant.MAIN_GAME_GRID_OFFSET,1.0f);
                }
            }
        }
        batch.end();
    }

    private void CalignPosition(SpriteBatch batch, Sprite sprite, float x, float y, Vector2 offsetVal, float transparent){
        sprite.setPosition(offsetVal.x+sprite.getHeight()*(x-3),offsetVal.y+sprite.getHeight()*(y-3));
        sprite.draw(batch,transparent);
    }

    private void InitTetris(){

        TetrisPosition.set((MapGrid.length-1)/2,MapGrid[0].length-4);
        if (FirstPlay){
            TetrisLevel=MathUtils.random(0,6);
            GetTetris(TetrisLevel);
            Tetrisgrid=new int[NewTetrisGrid.length][NewTetrisGrid[0].length];
            Tetrisgrid=NewTetrisGrid;
            TetrisLevel=MathUtils.random(0,6);
            GetTetris(TetrisLevel);
            FirstPlay=false;
        }
        else {
            Tetrisgrid=new int[NewTetrisGrid.length][NewTetrisGrid[0].length];
            Tetrisgrid=NewTetrisGrid;

            TetrisLevel= MathUtils.random(0,6);
            GetTetris(TetrisLevel);
        }
    }

    private void GetTetris(int index){
        switch (index)
        {
            case 0:
                int[][] TetrisTypeI = {{0,0,0,0},{1,1,1,1},{0,0,0,0},{0,0,0,0}};
                NewTetrisGrid = new int[TetrisTypeI.length][TetrisTypeI[0].length];
                NewTetrisGrid = TetrisTypeI;
                break;
            case 1:
                int[][] TetrisTypeJ = {{0,0,1},{1,1,1},{0,0,0}};
                NewTetrisGrid = new int[TetrisTypeJ.length][TetrisTypeJ[0].length];
                NewTetrisGrid = TetrisTypeJ;
                break;
            case 2:
                int[][] TetrisTypeL = {{1,0,0},{1,1,1},{0,0,0}};
                NewTetrisGrid = new int[TetrisTypeL.length][TetrisTypeL[0].length];
                NewTetrisGrid = TetrisTypeL;
                break;
            case 3:
                int[][] TetrisTypeO = {{1,1},{1,1}};
                NewTetrisGrid = new int[TetrisTypeO.length][TetrisTypeO[0].length];
                NewTetrisGrid = TetrisTypeO;
                break;
            case 4:
                int[][] TetrisTypeS = {{1,1,0},{0,1,1},{0,0,0}};
                NewTetrisGrid = new int[TetrisTypeS.length][TetrisTypeS[0].length];
                NewTetrisGrid = TetrisTypeS;
                break;
            case 5:
                int[][] TetrisTypeT = {{0,1,0},{1,1,1},{0,0,0}};
                NewTetrisGrid = new int[TetrisTypeT.length][TetrisTypeT[0].length];
                NewTetrisGrid = TetrisTypeT;
                break;
            case 6:
                int[][] TetrisTypeZ = {{0,1,1},{1,1,0},{0,0,0}};
                NewTetrisGrid = new int[TetrisTypeZ.length][TetrisTypeZ[0].length];
                NewTetrisGrid = TetrisTypeZ;
                break;
        }
    }

    private int[][] Rotate(int[][] index){
        int[][] index1 = new int[index.length][index[0].length];
        for (int j=0;j<index[0].length;j++){
            for (int i=0;i<index.length;i++){
                index1[i][index[0].length-j-1]=index[j][i];
            }
        }
        return index1;
    }
    public void dispose(){

    }
}
