package com.scnu.imutest;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Aty_PersonnelList extends Activity implements OnClickListener {


	@SuppressWarnings("unused")
	private LinearLayout.LayoutParams LP_MM=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
	private LinearLayout.LayoutParams LP_MW=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
	private ClubInformationData pl1=new ClubInformationData(2014021349, "池雪辉", "组长", "安卓组", "逗比比");
	private ClubInformationData pl2=new ClubInformationData(2014021348, "冼立志", "组员", "安卓组", "逗比比");
	private ClubInformationData pl3=new ClubInformationData(2014021347, "黄炫", "组员", "安卓组", "逗比比");
	private ClubInformationData pl4=new ClubInformationData(2014021346, "龙宇文", "组员", "安卓组", "逗比比");
	private ClubInformationData pl5=new ClubInformationData(2014021345, "吴伟峰", "组员", "安卓组", "逗比比");
	private ArrayList<ClubInformationData> personnelList=new ArrayList<ClubInformationData>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personnel_list);
		findViewById(R.id.btnYes).setOnClickListener(this);
		findViewById(R.id.btnAllSelect).setOnClickListener(this);
		personnelList.add(pl1);
		personnelList.add(pl2);
		personnelList.add(pl3);
		personnelList.add(pl4);
		personnelList.add(pl5);
		selectPersonnel(personnelList);
		
		judgeIsChecked(personnelList);
	}

	private void judgeIsChecked(ArrayList<ClubInformationData> personnelList2) {
		for (int i = 0; i < personnelList2.size(); i++) {
			if (personnelList2.get(i).isCheck==true) {
				personnelList2.get(i).personnelCheckBox.setChecked(true);
			}
			if (personnelList2.get(i).personnelCheckBox.isChecked()) {
				personnelList2.get(i).isCheck=true;
			}else {
				personnelList2.get(i).isCheck=false;
			}
		}
	}

	private void selectPersonnel(ArrayList<ClubInformationData> personnelList2) {
		for (int i = 0; i < personnelList2.size(); i++) {
			LinearLayout lllist=(LinearLayout) findViewById(R.id.lllist);
			CheckBox cb=personnelList2.get(i).personnelCheckBox;
			cb.setLayoutParams(LP_MW);
			cb.setText(personnelList2.get(i).getDepartment()+personnelList2.get(i).getPosition()+personnelList2.get(i).getName());
			personnelList2.get(i).personnelCheckBox=cb;
			lllist.addView(cb, LP_MW);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnYes:
			finishPersonnelChoice();
			break;
		case R.id.btnAllSelect:
			personnelAllSelect();
			break;

		default:
			break;
		}
	}

	private void personnelAllSelect() {
		for (int i = 0; i < personnelList.size(); i++) {
			personnelList.get(i).personnelCheckBox.setChecked(true);
			personnelList.get(i).isCheck=true;
		}
	}

	private void finishPersonnelChoice() {
		
		Toast.makeText(this, "跳转至人员安排", Toast.LENGTH_SHORT).show();
		Intent iPersonnelPlacement=new Intent(Aty_PersonnelList.this, Aty_PeopleArrange.class);
		Bundle bundlePersonnelPlacement=new Bundle();
		bundlePersonnelPlacement.putParcelableArrayList("isCheckedPersonnelList", personnelList);
		iPersonnelPlacement.putExtras(bundlePersonnelPlacement);
		startActivity(iPersonnelPlacement);
	}
}
