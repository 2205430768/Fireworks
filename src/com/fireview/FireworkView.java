package com.fireview;

import java.io.InputStream;
import java.util.Vector;

import util.Constant;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.view.MotionEvent;
import android.view.View;

import com.dot.Dot;
import com.dot.DotFactory;
import com.example.zhoumoyukuaiyanhua.R;

/**     
 * �����ƣ�FireworkView   
 * ���������̳�android��View��ʵ���̻�Ч��
 * �����ˣ�anan   
 * ����ʱ�䣺2012-12-16 ����1:02:36   
 * �޸��ˣ�anan  
 * �޸�ʱ�䣺2012-12-16 ����1:02:36   
 * �޸ı�ע��   
 * @version        
 * */
public class FireworkView extends View {

	final static int TIME = 5; // Ȧ��
	/**�����е��̻���*/
	private Vector<Dot> lList = new Vector<Dot>();
	private DotFactory df = null;
	boolean running = true;
	Bitmap backGroundBitmap;
	Context mContext;
	Text text;
	Paint paint;
	public boolean over=false;
	public FireworkView(Context context) {
		super(context);
		df = new DotFactory();
		new MyThread().start();
		mContext = context;
		backGroundBitmap = ReadBitMap(mContext, R.drawable.night);
		text=new Text("��", 20, 700, 30);
		text.first_ask();
		paint=new Paint();
		paint.setAntiAlias(true);
		paint.setTextAlign(Align.CENTER);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.save();
		canvas.translate(Constant.X_OFFSET, Constant.Y_OFFSET);
		canvas.scale(Constant.ssr.ratio, Constant.ssr.ratio);
		canvas.drawBitmap(backGroundBitmap, 0, 0, null);
		synchronized (lList) {
			for (int i = 0; i < lList.size(); i++) {
				lList.get(i).myPaint(canvas, lList);
			}
		}
		text.drawSlef(canvas, paint);
		
		canvas.restore();
		invalidate();
		
	}


	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public Bitmap ReadBitMap(Context context, int resId) {
		BitmapFactory.Options opt = new BitmapFactory.Options();
		opt.inPreferredConfig = Bitmap.Config.RGB_565;
		opt.inPurgeable = true;
		opt.inInputShareable = true;
		// ��ȡ��ԴͼƬ
		InputStream is = context.getResources().openRawResource(resId);
		return BitmapFactory.decodeStream(is, null, opt);
	}

	public Bitmap resizeImage(Bitmap mBitmap, int w, int h) {
		Bitmap BitmapOrg = mBitmap;
		int width = BitmapOrg.getWidth();
		int height = BitmapOrg.getHeight();
		int newWidth = w;
		int newHeight = h;
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;

		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);
		Bitmap tmp = Bitmap.createBitmap(BitmapOrg, 0, 0, width, height,
				matrix, true);
		return tmp;
	}

	/**     
	 * �����ƣ�MyThread   
	 * ���������ػ��߳�
	 * �����ˣ�anan   
	 * ����ʱ�䣺2012-12-16 ����1:06:50   
	 * �޸��ˣ�anan  
	 * �޸�ʱ�䣺2012-12-16 ����1:06:50   
	 * �޸ı�ע��   
	 * @version        
	 * */
	 Dot dotf = null;
	 int yancount=0;
	class MyThread extends Thread {
		// ���ڿ����̻��ڿ���������ʱ��
		int times = 0;
         
		public void run() {
			Dot dot = null;
			while (running) {

				try {
					Thread.sleep(100);
				} catch (Exception e) {
					System.out.println(e);
				}
              if(text.allover){
            	if(yancount<30){
            	  int rand = (int) (Math.random() * 99);
            	  int randomx=50+(int) (Math.random() * 400);
      			  dotf = df.makeDot(mContext, rand,randomx,100);
      			synchronized (lList) {
      				lList.add(dotf);
      			}
      			yancount++;
            	}
            	else{
            		if(yancount<75){
            			yancount++;	
            		}
            		else{
            			text.flag=false;	
            			over=true;
            		}
            		
            		
            	}
              }
				for (int i = 0; i < lList.size(); i++) {
					dot = (Dot) lList.get(i);
					if (dot.state == 1 && !dot.whetherBlast()) {  //��ʼʱ�����е��̻�������
						dot.rise();
					}
					// �����whetherBlast()���ص���true����ô�ͰѸ�dot��state����Ϊ2
					else if (dot.state == 1 && dot.state != 2) {
						dot.state = 2;
					} else if (dot.state == 3) {

					}
					// �涨��ÿ����ը�������TIMEȦ�������ͻ���ʧ
					if (dot.circle >= TIME) {
						// �ڿ�������һ�����ʧ
						if (times >= 10) {
							dot.state = 4;
							times = 0;
						} else {
							times++;
						}
						// dot.state = 4;
					}
				}
			}
		}
	}
}
