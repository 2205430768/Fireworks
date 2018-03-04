package com.example.zhoumoyukuaiyanhua;

import util.Constant;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import com.fireview.FireworkView;

public class MainActivity extends Activity {
	FireworkView fireworkView;
	private boolean initscreen=true;
	TestOver mTestOver;
	public Handler handler=new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch(msg.what){
			case 0:
				if (fireworkView.isRunning()) {
					fireworkView.setRunning(false);
				}
				Intent intent=new Intent(MainActivity.this,LastActivity.class);
				MainActivity.this.startActivity(intent);
				finish();
				break;
			
			}
			
		};
		
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		 if(initscreen){
       	  Constant.initConst(dm.widthPixels, dm.heightPixels);//初始化常量
       	  initscreen=false;
      }
		
		fireworkView = new FireworkView(this);
		setContentView(fireworkView);
		 mTestOver=new TestOver();
		 mTestOver.start();
	}

	@Override
	protected void onDestroy() {
		
		super.onDestroy();
		if (fireworkView.isRunning()) {
			fireworkView.setRunning(false);
		}
	}
	class TestOver extends Thread{
		@Override
		public void run() {
		while(true){
			if(fireworkView.over){
				handler.sendEmptyMessage(0);
				break;
			}
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		}
		
		
	}

}
