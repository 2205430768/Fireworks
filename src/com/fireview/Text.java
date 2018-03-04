package com.fireview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Text {

	public int status=0;
	int status_start=0;
	int status_song_yan_hua=1;
	int status_over=2;
	String text;
	public float scaleX=1;//文字x方向缩放比例
	public float scaleY=1;//文字y方向缩放比例
	public float x=0;//文字的x坐标
	public float y=0;//文字的y坐标
	public float offset_x=0;
	public float offset_y=0;
	public float textSize=15f;
	int color=Color.WHITE;
	public float RotateAngle=0;
	 float k=0;
	 float ySpan=0;
	 float oldscaleX;
	 float oldscaleY;
	 int count=0;
	 boolean ParabolaOver=false;
	 public int sleepSpan=2;
	 String another_text="阿姨";
	 float another_x=130;
	 float another_y=0;
	 float another_scale=1;
	 String yan_text="烟花";
	 float yan_x=130;
	 float yan_y=0;
	 float yan_scale=1;
	 public boolean allover=false;
	public Text(String text){
		this.text=text;
	}
	public Text(String text,float x,float y,float textSize){
		this.text=text;
		this.x=x;
		this.y=y;
		this.textSize=textSize;
		another_scale=textSize;
	}
	/**
	 * 文字以抛物线运动
	 * @param dstX 目标x
	 * @param dstY 目标y
	 */
	public void runParabola(final float dstX,final float dstY){
		oldscaleX=scaleX;
		oldscaleY=scaleY;
		scaleX=0;
		scaleY=0;
		float span=(dstY-y)*(dstY-y);
		k=(dstX-x)/span;
		count=(int) (Math.abs(dstY-y)/0.5f);
		if(dstY>y)//下
		{
			ySpan=0.5f;
		}
		else//上
		{
			ySpan=-0.5f;
		}
		
		new Thread(){
			public void run() {
				float startX=x;
				float startY=y;
				int index=0;
				while(Math.abs(x-dstX)>0.5f)
				{
					scaleX=oldscaleX*index/count;
					scaleY=oldscaleY*index/count;
					index++;
					y+=ySpan;
					x=startX+(y-startY)*(y-startY)*k;
					try {
						sleep(sleepSpan);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				x=dstX;
				y=dstY;
				scaleX=oldscaleX;
				scaleY=oldscaleY;
				ParabolaOver=true;
			};
			
		}.start();
		
	}
	public void runParabola_move1(final float dstX,final float dstY){
		
		float span=(dstY-another_y)*(dstY-another_y);
		k=(dstX-another_x)/span;
		if(dstY>another_y)//下
		{
			ySpan=0.5f;
		}
		else//上
		{
			ySpan=-0.5f;
		}
		
		new Thread(){
			public void run() {
				float startX=another_x;
				float startY=another_y;
				another_scale+=10f;
				while(Math.abs(another_x-dstX)>0.5f)
				{
					another_y+=ySpan;
					another_x=startX+(another_y-startY)*(another_y-startY)*k;
					try {
						sleep(sleepSpan);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				another_x=dstX;
				another_y=dstY;
				if(another_x<230)
				runParabola_move2(230,0);
				else{
					runParabola_move2(330,0);	
				}
			};
			
		}.start();
		
	}
public void song_yan_hua_move1(final float dstX,final float dstY){
		
		float span=(dstY-yan_y)*(dstY-yan_y);
		k=(dstX-yan_x)/span;
		if(dstY>yan_y)//下
		{
			ySpan=0.5f;
		}
		else//上
		{
			ySpan=-0.5f;
		}
		
		new Thread(){
			public void run() {
				float startX=yan_x;
				float startY=yan_y;
				
				while(Math.abs(yan_x-dstX)>0.5f)
				{
					yan_scale+=1f;
					yan_y+=ySpan;
					yan_x=startX+(yan_y-startY)*(yan_y-startY)*k;
					try {
						sleep(5);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				yan_x=dstX;
				yan_y=dstY;
				song_yan_hua_move2(200,0);
			};
			
		}.start();
		
	}
 protected void ren_also_move() {
	 if(another_x<250){
	 new Thread(){
		 int index=0;
			public void run() {
				while(offset_x<100){
					offset_x++;
					if(index<8)
					{
					RotateAngle=-5;	
					}
					else{
						RotateAngle=1;
					}
					index=(index+1)%16;
					sleep(36);
				}
				RotateAngle=0;
				ren_koudou2();
			}
			void sleep(int timespan){
				try {
					Thread.sleep(timespan);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}.start();
	 }else{
		 new Thread(){
			 int index=0;
				public void run() {
					while(offset_x<200){
						offset_x++;
						if(index<8)
						{
						RotateAngle=-5;	
						}
						else{
							RotateAngle=1;
						}
						index=(index+1)%16;
						sleep(36);
					}
					RotateAngle=0;
					ren_koudou3();
				}
				void sleep(int timespan){
					try {
						Thread.sleep(timespan);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}.start(); 
		 
	 }
		
	}
protected void ren_koudou3() {
	new Thread(){
		public void run() {
			sleep(1000);
			RotateAngle=90;
			sleep(1000);
			RotateAngle=0;
			sleep(1000);
			RotateAngle=90;
			sleep(1000);
			RotateAngle=0;
			sleep(1000);
			RotateAngle=90;
			sleep(1000);
			RotateAngle=0;
			status=status_song_yan_hua;
			song_yan_hua();
		}
		void sleep(int timespan){
			try {
				Thread.sleep(timespan);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}.start();
	
}
protected void song_yan_hua() {
	yan_x=another_x;
	yan_y=another_y;
	song_yan_hua_move1(265, -100);
	
}
protected void ren_koudou2() {
	new Thread(){
		public void run() {
			sleep(1000);
			RotateAngle=90;
			sleep(1000);
			RotateAngle=0;
			sleep(1000);
			RotateAngle=90;
			sleep(1000);
			RotateAngle=0;
			runParabola_move1(280,-60);
		}
		void sleep(int timespan){
			try {
				Thread.sleep(timespan);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}.start();
	
}

public void runParabola_move2(final float dstX,final float dstY){
		
		float span=(dstY-another_y)*(dstY-another_y);
		k=(dstX-another_x)/span;
		if(dstY>another_y)//下
		{
			ySpan=0.5f;
		}
		else//上
		{
			ySpan=-0.5f;
		}
		
		new Thread(){
			public void run() {
				float startX=another_x;
				float startY=another_y;
				
				while(Math.abs(another_x-dstX)>0.5f)
				{
					another_y+=ySpan;
					another_x=startX+(another_y-startY)*(another_y-startY)*k;
					try {
						sleep(sleepSpan);
					} catch (Exception e) {
						
					}
				}
				another_x=dstX;
				another_y=dstY;
				another_scale=textSize;
				ren_also_move();
			};
			
		}.start();
		
	}
public void song_yan_hua_move2(final float dstX,final float dstY){
	
	float span=(dstY-yan_y)*(dstY-yan_y);
	k=(dstX-yan_x)/span;
	if(dstY>yan_y)//下
	{
		ySpan=0.5f;
	}
	else//上
	{
		ySpan=-0.5f;
	}
	
	new Thread(){
		public void run() {
			float startX=yan_x;
			float startY=yan_y;
			
			while(Math.abs(yan_x-dstX)>0.5f)
			{
				yan_scale-=1f;
				yan_y+=ySpan;
				yan_x=startX+(yan_y-startY)*(yan_y-startY)*k;
				try {
					sleep(5);
				} catch (Exception e) {
					
				}
			}
			yan_x=dstX;
			yan_y=dstY;
			yan_scale=0;
			status=status_over;
			yaotou();
		};
		
	}.start();
	
}
public boolean flag=true;
public void setFlag(boolean flag){
	this.flag=flag;
}
	protected void yaotou() {
		new Thread(){
			public void run() {
				allover=true;
				while(flag){
					sleep(200);
					RotateAngle=-5;
					x-=2;
					y-=1;
					sleep(200);
					RotateAngle=5;
					x+=2;
					y+=1;
				}
				RotateAngle=0;
				
			}
			void sleep(int timespan){
				try {
					Thread.sleep(timespan);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}.start();
	
}
	public void first_ask(){
		new Thread(){
			public void run() {
				sleep(1000);
				RotateAngle=90;
				sleep(1000);
				RotateAngle=0;
				sencond_move();
			}
			void sleep(int timespan){
				try {
					Thread.sleep(timespan);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}.start();
	}
	protected void sencond_move() {
		new Thread(){
			public void run() {
				
				runParabola_move1(180,-60);
			}
			void sleep(int timespan){
				try {
					Thread.sleep(timespan);
				} catch (Exception e) {
				}
			}
		}.start();
		
	}
	public void drawSlef(Canvas canvas,Paint paint){
		float oldTextSize=paint.getTextSize();
		int oldColor=paint.getColor();
		paint.setTextSize(textSize);
		paint.setColor(color);
		if(status==status_start){
			canvas.save();
			canvas.translate(x, y);
			canvas.scale(scaleX, scaleY);
			canvas.save();
			canvas.rotate(RotateAngle, offset_x, offset_y);
			canvas.drawText(text, offset_x, offset_y, paint);
			canvas.restore();
			canvas.save();
			canvas.rotate(RotateAngle, offset_x+30, offset_y);
			canvas.drawText("丽", offset_x+30, offset_y, paint);
			canvas.restore();
			canvas.save();
			canvas.rotate(RotateAngle, 60+offset_x, offset_y);
			canvas.drawText("娜", 60+offset_x, offset_y, paint);
			canvas.restore();
			paint.setTextSize(another_scale);
			canvas.drawText(another_text, another_x, another_y, paint);
			canvas.restore();
		}
		else if(status==status_song_yan_hua){
			canvas.save();
			canvas.translate(x, y);
			canvas.scale(scaleX, scaleY);
			canvas.save();
			canvas.rotate(RotateAngle, offset_x, offset_y);
			canvas.drawText(text, offset_x, offset_y, paint);
			canvas.restore();
			canvas.save();
			canvas.rotate(RotateAngle, offset_x+30, offset_y);
			canvas.drawText("丽", offset_x+30, offset_y, paint);
			canvas.restore();
			canvas.save();
			canvas.rotate(RotateAngle, 60+offset_x, offset_y);
			canvas.drawText("娜", 60+offset_x, offset_y, paint);
			canvas.restore();
			paint.setTextSize(another_scale);
			canvas.drawText(another_text, another_x, another_y, paint);
			paint.setTextSize(yan_scale);
			canvas.drawText(yan_text, yan_x, yan_y, paint);
			canvas.restore();
		}
		else if(status==status_over){
			canvas.save();
			canvas.translate(x, y);
			canvas.scale(scaleX, scaleY);
			canvas.save();
			canvas.rotate(RotateAngle, offset_x, offset_y);
			canvas.drawText(text, offset_x, offset_y, paint);
			canvas.restore();
			canvas.save();
			canvas.rotate(RotateAngle, offset_x+30, offset_y);
			canvas.drawText("丽", offset_x+30, offset_y, paint);
			canvas.restore();
			canvas.save();
			canvas.rotate(RotateAngle, 60+offset_x, offset_y);
			canvas.drawText("娜", 60+offset_x, offset_y, paint);
			canvas.restore();
			
		}
		paint.setTextSize(oldTextSize);
		paint.setColor(oldColor);
		
	}
}
