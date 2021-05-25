package kr.ac.kpu.game.s2016180003.cookierun.game;

import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

import kr.ac.kpu.game.s2016180003.cookierun.framework.game.BaseGame;
import kr.ac.kpu.game.s2016180003.cookierun.framework.iFace.GameObject;
import kr.ac.kpu.game.s2016180003.cookierun.framework.view.GameView;

public class StageMap implements GameObject {
    private static final String TAG = StageMap.class.getSimpleName();
    private int rows;
    private int colums;
    private ArrayList<String> lines  = new ArrayList<String>();
    private int current;
    private MainGame game;

    public StageMap(String filename){
        AssetManager assets = GameView.view.getContext().getAssets();
        try {
            InputStream is = assets.open(filename);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader reader  = new BufferedReader(isr);
            String header = reader.readLine();
            String[] comps = header.split(" ");
            colums = Integer.parseInt(comps[0]);
            rows = Integer.parseInt(comps[1]);
            while(true){
                String line = reader.readLine();
                if(line == null){
                    break;
                }
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void update() {
        game = (MainGame) BaseGame.get();
        createColums();
//        ArrayList<GameObject> objects = game.objectsAt(MainGame.Layer.platform);
//        float rightMost = 0;
//        for (GameObject obj: objects) {
//            Platform platform = (Platform) obj;
//            float right = platform.getRight();
//            if (rightMost < right) {
//                rightMost = right;
//            }
//        }
//        float vw = GameView.view.getWidth();
//        float vh = GameView.view.getHeight();
//        if (rightMost < vw) {
//            Log.d(TAG, "create a Platform here !! @" + rightMost + " Platforms=" + objects.size());
//            float tx = rightMost, ty = vh - Platform.Type.T_2x2.height();
//            Platform platform = new Platform(Platform.Type.RANDOM, tx, ty);
//            game.add(MainGame.Layer.platform, platform);
//
//            Random r = new Random();
//            game.add(MainGame.Layer.item, new Jelly(r.nextInt(60), tx, r.nextInt((int) ty)));
//        }
    }
    private float xPos;
    private void createColums(){
        float  y = 0;
        for(int row = 0; row < rows ; row++){
            char ch = getAt(current, row);
            createObject(ch, xPos, y);
            y += Platform.UNIT_SIZE * GameView.MULTIPLIER;
        }
        current++;
        xPos += Platform.UNIT_SIZE * GameView.MULTIPLIER;
    }

    private void createObject(char ch, float x, float y) {
        if (ch >= '1' && ch <= '9') {
            Jelly item = new Jelly(ch - '1', x, y);
            game.add(MainGame.Layer.item, item);
        } else if (ch >= '0' && ch <= 'Q') {
            Platform platform = new Platform(Platform.Type.values()[ch - '0'], x, y);
            game.add(MainGame.Layer.platform,platform);
        }
    }

    private char getAt(int x, int y){
        try {
            int lineIndex = x / colums * rows + y;
            String line = lines.get(lineIndex);
            return line.charAt(x % colums);
        } catch (Exception e){
            return 0;
        }
    }

    @Override
    public void draw(Canvas canvas) {
    }
}
