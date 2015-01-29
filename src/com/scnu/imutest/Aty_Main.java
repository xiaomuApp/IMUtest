package com.scnu.imutest;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class Aty_Main extends Activity implements OnClickListener {

	
	private Data_ClubInformation pl1=new Data_ClubInformation(2014021349, "池雪辉", "组长", "安卓组", "逗比比");
	private Data_ClubInformation pl2=new Data_ClubInformation(2014021348, "冼立志", "组员", "安卓组", "逗比比");
	private Data_ClubInformation pl3=new Data_ClubInformation(2014021347, "黄炫", "组员", "安卓组", "逗比比");
	private Data_ClubInformation pl4=new Data_ClubInformation(2014021346, "龙宇文", "组员", "安卓组", "逗比比");
	private Data_ClubInformation pl5=new Data_ClubInformation(2014021345, "吴伟峰", "组员", "安卓组", "逗比比");
	private ArrayList<Data_ClubInformation> personnelList=null;
	public static Bundle bundlePersonnelPlacement=null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		System.out.println(1);
		super.onCreate(savedInstanceState);
		System.out.println(2);
		setContentView(R.layout.activity_main);
		System.out.println(3);
		findViewById(R.id.btnOrganiger).setOnClickListener(this);
		System.out.println(4);
		findViewById(R.id.btnPerformer).setOnClickListener(this);
		System.out.println(5);
		personnelList.add(pl1);
		personnelList.add(pl2);
		personnelList.add(pl3);
		personnelList.add(pl4);
		personnelList.add(pl5);
		bundlePersonnelPlacement=new Bundle();
		personnelList=new ArrayList<Data_ClubInformation>();
		bundlePersonnelPlacement.putParcelableArrayList("personnelList", personnelList);
		
	}

	@Override
	public void onClick(View v) {
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
}
