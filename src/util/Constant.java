package util;




public class Constant {

	public static ScreenScaleResult ssr;//��Ļ�Ľ��
	public static float wRatio;//��Ӧȫ�������ű���
	public static float hRatio;
	public static int SCREEN_WIDTH;//��Ļ�Ŀ��
	public static int SCREEN_HEIGHT;//��Ļ�ĸ߶�
	//��Ϸ�������Ͻǵ�����
	public static float X_OFFSET;
	public static float Y_OFFSET;

	
	//���ڻ������˵��ĳ���
	static int screenWidthTest=480;//���Ի���Ļ���
	static int screenHeightTest=800;//���Ի���Ļ�߶�
	//��ʼ�������ķ���
		public static void initConst(int screenWidth,int screenHeight)
		{
			
			SCREEN_WIDTH=screenWidth;//��Ļ�ĳߴ�
			SCREEN_HEIGHT=screenHeight;
			//��Ӧȫ�������ű���
			wRatio=screenWidth/(float)screenWidthTest;
			hRatio=screenHeight/(float)screenHeightTest;
			//������Ļ�Ľ��
			ssr=ScreenScaleUtil.calScale(screenWidth, screenHeight);	
			X_OFFSET=ssr.lucX;
			Y_OFFSET=ssr.lucY;
			
		}
	
}
