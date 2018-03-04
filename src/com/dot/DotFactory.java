package com.dot;

import android.content.Context;

/**     
 * 类名称：DotFactory   
 * 类描述：烟火加工厂
 * 创建人：anan   
 * 创建时间：2012-12-16 下午1:06:09   
 * 修改人：anan  
 * 修改时间：2012-12-16 下午1:06:09   
 * 修改备注：   
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
