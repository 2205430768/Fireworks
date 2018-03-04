package com.dot;

import java.util.Random;
import java.util.Vector;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;

import com.example.zhoumoyukuaiyanhua.R;
import com.fireview.Animation;

//所有烟花式样的父类，方便以后添加烟花式样
public abstract class Dot {
	int x = 30; // 该点的横坐标
	int y = 30; // 该点的竖坐标

	int color; // 该点的颜色

	int pace = 30; // 该点上升的速度
	int size = 6; // 点的大小

	final Point endPoint = new Point(); // 记录该点完结的位置

	/** 记录该点的状态，就是是否是爆破状态,1是上升状态，2是爆炸状态的初始化，3是爆炸状态，4是消失状态*/
	public int state = 1;
	
	public int circle = 1;


	int WALLOP = 20; // 设爆炸后每个点所受到的冲击力是10N
	int GRAVITY = 20;

	Animation mFireAnim = null;
	Context mContext;
	
	int maxCircle;
/**
 * Dot构造函数
 * @param context 上下文
 * @param color 颜色
 * @param endX 结束点x开始点x
 * @param endY 结束点y开始点y=800
 */
	public Dot(Context context, int color, int endX, int endY) {
		circle = 1;
		state = 1;
		pace = 25;
		this.x = endX;
		this.y = 700;
		this.color = color;
		endPoint.x = endX;
		endPoint.y = endY;
		mContext = context;
		new loadFireSrc().start();// 用线程去加载图片数据，避免数据量过大造成屏幕一卡一卡的 
		maxCircle = new Random().nextInt(6) + 5;
	}

	class loadFireSrc extends Thread {
		public void run() {
			mFireAnim = new Animation(mContext, new int[] { R.drawable.trail1,
					 R.drawable.trail6 }, true);
		}
	}

	/** 烟花上升 */
	public void rise() {
		// 处理上升的的情况
		if (mFireAnim != null) {
			y -= pace;//y坐标减小表示上升
			if (y <= endPoint.y) {
				y = endPoint.y;
			}
			if (x <= endPoint.x) {
				x = endPoint.x;
			}

		}
		
	}

	/** 判断是否爆炸 如果y到达目的点即爆炸*/
	public boolean whetherBlast() {
		// 判断是否爆炸
		if (y <= endPoint.y && x <= endPoint.x) {
			return true;
		}
		return false;
	}



	public void myPaint(Canvas canvas, Vector<Dot> lList) {
		
	}

	
}
