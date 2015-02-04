package com.scnu.imutest;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Aty_Main extends Activity implements OnClickListener, OnViewChangeListener {

	private MyScrollLayout mScrollLayout;
	private LinearLayout[] mImageViews;
	private int mViewCount;
	private int mCurSel;
	
	private TextView organizer;
	private TextView performer;

	private ArrayList<Data_Task> currentData_Task=null;
	private Data_ClubInformation pl1=new Data_ClubInformation(2014021349, "池雪辉", "组长", "安卓组", "逗比比",1);
	private Data_ClubInformation pl2=new Data_ClubInformation(2014021348, "冼立志", "组员", "安卓组", "逗比比",0);
	private Data_ClubInformation pl3=new Data_ClubInformation(2014021347, "黄炫", "组员", "安卓组", "逗比比",0);
	private Data_ClubInformation pl4=new Data_ClubInformation(2014021346, "龙宇文", "组员", "安卓组", "逗比比",0);
	private Data_ClubInformation pl5=new Data_ClubInformation(2014021345, "吴伟峰", "组员", "安卓组", "逗比比",0);
	//任务信息
	private Data_TaskDistribute p21=new Data_TaskDistribute("任务",1, "一月一日", "搬凳子", "女生节", null);
	private Data_TaskDistribute p22=new Data_TaskDistribute("任务",2, "二月一日", "搬凳子", "妇女节", null);
	private Data_TaskDistribute p23=new Data_TaskDistribute("任务",3, "三月一日", "搬凳子", "男生节", null);
	private Data_TaskDistribute p24=new Data_TaskDistribute("任务",4, "四月一日", "搬凳子", "情人节", null);
	private ArrayList<Data_ClubInformation> personnelList=null;
	public static ArrayList<Data_TaskDistribute> taskList=null;
	public static Bundle bundlePersonnelPlacement=null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		findViewById(R.id.btnOrganiger).setOnClickListener(this);
		findViewById(R.id.btnPerformer).setOnClickListener(this);
		personnelList = new ArrayList<Data_ClubInformation>();
		personnelList.add(pl1);
		personnelList.add(pl2);
		personnelList.add(pl3);
		personnelList.add(pl4);
		personnelList.add(pl5);
		taskList=new ArrayList<Data_TaskDistribute>();
		taskList.add(p21);
		taskList.add(p22);
		taskList.add(p23);
		taskList.add(p24);
		Data_TaskDistribute activityTask = new Data_TaskDistribute("任务",1, "2015.2.14", "完成小木社团app初稿，并通过审核", "完成小木社团app初稿", personnelList);
		currentData_Task=new ArrayList<Data_Task>();
		currentData_Task.add(new Data_Task(this, "任务",1,"","","", null, activityTask));
		for (int i = 0; i < personnelList.size(); i++) {

			personnelList.get(i).setData_TaskList(currentData_Task);

		}
		bundlePersonnelPlacement=new Bundle();
		bundlePersonnelPlacement.putParcelableArrayList("personnelList", personnelList);
	}

	private void init(){
		organizer = (TextView) findViewById(R.id.organizer);
		performer = (TextView) findViewById(R.id.performer);
		mScrollLayout = (MyScrollLayout) findViewById(R.id.ScrollLayout);
		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.lllayout);
		mViewCount = mScrollLayout.getChildCount();
		mImageViews = new LinearLayout[mViewCount];
		for(int i = 0; i < mViewCount; i++){
			mImageViews[i] = (LinearLayout) linearLayout.getChildAt(i);
			mImageViews[i].setEnabled(true);
			mImageViews[i].setOnClickListener(this);
			mImageViews[i].setTag(i);
		}
		mCurSel = 0;
		mImageViews[mCurSel].setEnabled(false);
		mScrollLayout.SetOnViewChangeListener(this);
		
	}
	
	private void setCurPoint(int index){
		if(index < 0 || index > mViewCount - 1 || mCurSel == index){
			return;
		}
		mImageViews[mCurSel].setEnabled(true);
		mImageViews[index].setEnabled(false);
		mCurSel = index;
		
		if(index == 0){
			organizer.setTextColor(0xff228B22);
			performer.setTextColor(Color.BLACK);
		}else if(index == 1){
			organizer.setTextColor(Color.BLACK);
			performer.setTextColor(0xff228b22);
		}
	}
	
	@Override
	public void onClick(View v) {
		if(v.getTag() != null){
			int pos = (Integer) (v.getTag());
			setCurPoint(pos);
			mScrollLayout.snapToScreen(pos);
		}
		switch (v.getId()) {
		case R.id.btnOrganiger:
			Intent iOrganiger=new Intent(Aty_Main.this, Aty_TaskList.class);
			startActivity(iOrganiger);
			break;

		case R.id.btnPerformer:
			Intent iPerformer=new Intent(Aty_Main.this, Aty_PerformerMyTask.class);
			startActivity(iPerformer);
			break;
		default:
			break;
		}

	}

	@Override
	public void OnViewChange(int view) {
		setCurPoint(view);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if((keyCode == KeyEvent.KEYCODE_MENU)){
			return true;
		}
		return super.onKeyDown(keyCode, event);
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
				finish();
			}else{
				Toast.makeText(this, "再按一次后退键退出应用", Toast.LENGTH_SHORT).show();
				lastClickTime = currentClickTime;
			}
		}
	}
}
