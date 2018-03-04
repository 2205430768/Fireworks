package com.dot;

import java.util.Random;
import java.util.Vector;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;

import com.example.zhoumoyukuaiyanhua.R;
import com.fireview.Animation;

//�����̻�ʽ���ĸ��࣬�����Ժ�����̻�ʽ��
public abstract class Dot {
	int x = 30; // �õ�ĺ�����
	int y = 30; // �õ��������

	int color; // �õ����ɫ

	int pace = 30; // �õ��������ٶ�
	int size = 6; // ��Ĵ�С

	final Point endPoint = new Point(); // ��¼�õ�����λ��

	/** ��¼�õ��״̬�������Ƿ��Ǳ���״̬,1������״̬��2�Ǳ�ը״̬�ĳ�ʼ����3�Ǳ�ը״̬��4����ʧ״̬*/
	public int state = 1;
	
	public int circle = 1;


	int WALLOP = 20; // �豬ը��ÿ�������ܵ��ĳ������10N
	int GRAVITY = 20;

	Animation mFireAnim = null;
	Context mContext;
	
	int maxCircle;
/**
 * Dot���캯��
 * @param context ������
 * @param color ��ɫ
 * @param endX ������x��ʼ��x
 * @param endY ������y��ʼ��y=800
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
		new loadFireSrc().start();// ���߳�ȥ����ͼƬ���ݣ��������������������Ļһ��һ���� 
		maxCircle = new Random().nextInt(6) + 5;
	}

	class loadFireSrc extends Thread {
		public void run() {
			mFireAnim = new Animation(mContext, new int[] { R.drawable.trail1,
					 R.drawable.trail6 }, true);
		}
	}

	/** �̻����� */
	public void rise() {
		// ���������ĵ����
		if (mFireAnim != null) {
			y -= pace;//y�����С��ʾ����
			if (y <= endPoint.y) {
				y = endPoint.y;
			}
			if (x <= endPoint.x) {
				x = endPoint.x;
			}

		}
		
	}

	/** �ж��Ƿ�ը ���y����Ŀ�ĵ㼴��ը*/
	public boolean whetherBlast() {
		// �ж��Ƿ�ը
		if (y <= endPoint.y && x <= endPoint.x) {
			return true;
		}
		return false;
	}



	public void myPaint(Canvas canvas, Vector<Dot> lList) {
		
	}

	
}
