package tjulkune.balloonpop;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.widget.ImageView;

public class Balloon extends ImageView 
{
	private Rect ballRect;
	private boolean evil = false;
	private boolean active = false;
	private Bitmap balloonmap;
	private int speed; 
			
	public Balloon(Context context, int left, int top, boolean isEvil, Bitmap bmap) 
	{
		super(context);
		this.ballRect = new Rect (left, top, left+40 ,top+50);
		this.evil = isEvil;
		balloonmap = bmap;
	}
	
	public int getSpeed()
	{
		return this.speed;
	}
	
	public void setSpeed(int newspeed)
	{
		this.speed = newspeed;
	}
	
	public boolean isActive()
	{
		return active;
	}
	
	public void makeActive()
	{
		if (this.ballRect.bottom != 0 && this.ballRect.left != 0) this.active = true;
	}
	
	public void makeInactive()
	{
		this.ballRect.bottom = 0;
	    this.ballRect.left = 0;
	    this.ballRect.top = 0; 
	    this.ballRect.right = 0;
	    this.active = false;
	}
	
	public boolean isEvil()
	{
		return this.evil;
	}
	
	// collision detection for touching the ball
 	public boolean isPopped(MotionEvent event) 
 	{
 	    if((event.getX(0)>= this.ballRect.left) && (event.getY(0)>=this.ballRect.top+this.speed) 
 		&&( event.getX(0)<=this.ballRect.right) && (event.getY(0)<=this.ballRect.bottom+this.speed))
 	    {
 	       this.makeInactive();
 	       return true;
 	    }
        else return false;
 	}
 	
 	public void draw(Canvas canvas) 
 	{
 		if(this.active == true)canvas.drawBitmap(this.balloonmap, null, this.ballRect, null);
 	}	

	public Rect getRect()
	{
		return this.ballRect;	
	}
	
	// move balloon upwards if not at upper edge of screen
	public void move() 
	{
		if (ballRect.bottom >= 0 && this.active == true)
		{
			this.ballRect.top -= this.speed;
			if (this.ballRect.left % 3 == 0) this.ballRect.left -= 2;
			else this.ballRect.left += 2;
			
			// to keep aspect ratio
			this.ballRect.right = this.ballRect.left + 40;
			this.ballRect.bottom = this.ballRect.top + 50 ;	
		}
		else this.makeInactive();
	}
	
}