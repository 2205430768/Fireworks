package util;




public class Constant {

	public static ScreenScaleResult ssr;//屏幕的结果
	public static float wRatio;//适应全屏的缩放比例
	public static float hRatio;
	public static int SCREEN_WIDTH;//屏幕的宽度
	public static int SCREEN_HEIGHT;//屏幕的高度
	//游戏界面左上角的坐标
	public static float X_OFFSET;
	public static float Y_OFFSET;

	
	//关于滑动主菜单的常量
	static int screenWidthTest=480;//测试机屏幕宽度
	static int screenHeightTest=800;//测试机屏幕高度
	//初始化常量的方法
		public static void initConst(int screenWidth,int screenHeight)
		{
			
			SCREEN_WIDTH=screenWidth;//屏幕的尺寸
			SCREEN_HEIGHT=screenHeight;
			//适应全屏的缩放比例
			wRatio=screenWidth/(float)screenWidthTest;
			hRatio=screenHeight/(float)screenHeightTest;
			//计算屏幕的结果
			ssr=ScreenScaleUtil.calScale(screenWidth, screenHeight);	
			X_OFFSET=ssr.lucX;
			Y_OFFSET=ssr.lucY;
			
		}
	
}
