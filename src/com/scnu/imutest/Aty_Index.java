package com.scnu.imutest;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

public class Aty_Index extends Activity {
	private String app_key = "801416605"; // 获取的appkey
	private String clientSecret = "afb3b09cc0ae263d8d992c141b71973a"; // 注册应用获取
	private String app_uri = "http://www.xiaoxiu.com";
//	public  static OAuthV2 oAuth;
	private int index = 0;
	private Timer timer;
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
//			if(index>0){
				//txfenxiang();
				timer.cancel();
//			}
			Intent intent = new Intent(Aty_Index.this,
					Aty_Main.class);
			startActivity(intent);
			finish();
			super.handleMessage(msg);
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_index);
		
		timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				index = index+1;
				handler.sendEmptyMessage(0);
			}
		},1500,1000);
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 2) {
			if (resultCode == 2) {
				/*******此处没有执行！！！！！！！！！！！！！***********/
//				oAuth = (OAuthV2) data.getExtras().getSerializable("oauth");
//				SaveDate.saveDate(Aty_Index.this, oAuth);
				// 调用API获取用户信息
//				UserAPI userAPI = new UserAPI(OAuthConstants.OAUTH_VERSION_2_A);
//				String response = null;
//				try {
//					response = userAPI.info(oAuth, "json");// 获取用户信息
//					Log.i(TAG,"qq::"+response);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
				Intent intent = new Intent(Aty_Index.this,
						Aty_Main.class);
				startActivity(intent);
				finish();
			}
		}
	}
	
	private long lastClickTime = 0;
	
	@Override
	public void onBackPressed() {
		if(lastClickTime<=0){
			Toast.makeText(this, "再按一次后退键退出应用", Toast.LENGTH_SHORT).show();
			lastClickTime = System.currentTimeMillis();
		}else{
			long currentClickTime = System.currentTimeMillis();
			if(currentClickTime-lastClickTime<1000){
				Toast.makeText(this, "再按一次后退键退出应用", Toast.LENGTH_SHORT).show();
				lastClickTime = currentClickTime;
			}
		}
		super.onBackPressed();
	}
	
}
