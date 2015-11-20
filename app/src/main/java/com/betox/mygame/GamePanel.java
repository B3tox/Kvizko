package com.betox.mygame;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Betox on 19-Nov-15.
 */
public class GamePanel extends SurfaceView implements SurfaceHolder.Callback{

    public static final float WIDTH=768;
    public static final float HEIGHT=768;
    private GameLoop thread;
    private Background bg;

    public GamePanel(Context context)
    {
        super(context);



        //add callback to the surfaceHolder to intercapt events
        getHolder().addCallback(this);

        thread=new GameLoop(getHolder(), this);

        //make GamePanel focusable so it can handle events
        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){}

    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {
        boolean retry=true;
        while(retry)
        {
            try{
                thread.setRunning(false);
                thread.join();
            }catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            retry=false;
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {
        //create background
        bg=new Background(BitmapFactory.decodeResource(getResources(), R.drawable.road));
        bg.setVectorY(-5);


        //we can safely start the game loop
        thread.setRunning(true);
        thread.start();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        return super.onTouchEvent(event);
    }

    public void update()
    {
        bg.update();

    }

    @Override
    public void draw(Canvas canvas){





        final float scaleFactorX= (getWidth()/WIDTH);
        final float scaleFactorY= (getHeight()/HEIGHT);

        if(canvas!=null){
            final int savedState=canvas.save();
            canvas.scale(scaleFactorX, scaleFactorY);
            bg.draw(canvas);
            canvas.restoreToCount(savedState);
        }



    }
}
