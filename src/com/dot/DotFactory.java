package com.dot;

import android.content.Context;

/**     
 * �����ƣ�DotFactory   
 * ���������̻�ӹ���
 * �����ˣ�anan   
 * ����ʱ�䣺2012-12-16 ����1:06:09   
 * �޸��ˣ�anan  
 * �޸�ʱ�䣺2012-12-16 ����1:06:09   
 * �޸ı�ע��   
 * @version        
 * */
public class DotFactory {

	public DotFactory() {

	}

	public Dot makeDot(Context context, int kind, int x, int y) {

		int red = (int) (Math.random() * 255);
		int green = (int) (Math.random() * 255);
		int blue = (int) (Math.random() * 255);

		int col = 0xff000000 | red << 16 | green << 8 | blue;

		Dot dot = null;


		dot = new DotAnimFW(context, col, x, y);

		return dot;
	}
}
