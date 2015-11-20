package com.betox.mygame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Betox on 20-Nov-15.
 */
public class Background {

    private Bitmap image;
    private int x, y, dx, dy;

    public Background(Bitmap res){
        image=res;
    }

    public void setVectorX(int dx)
    {
        this.dx=dx;
    }

    public void setVectorY(int dy){
        this.dy=dy;
    }

    public void update()
    {
        /*
        //move background image right
        x+=dx;
        if(x<-GamePanel.WIDTH){
            x=0;
        }
        */


        //move background image up
        y+=dy;
        if(y<-GamePanel.HEIGHT){
            y=0;
        }

    }

    public void draw(Canvas canvas)
    {

        canvas.drawBitmap(image, x , y ,null);
        if(y<0)
        {
            canvas.drawBitmap(image,x ,y+GamePanel.HEIGHT,null);
        }

    }


}
