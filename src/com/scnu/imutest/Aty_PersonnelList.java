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

	private ArrayList<Data_ClubInformation> personnelList=null;//下面通过bundle获取到的数据，可以使用

	@SuppressWarnings("unused")
	private LinearLayout.LayoutParams LP_MM=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
	private LinearLayout.LayoutParams LP_MW=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personnel_list);
		personnelList=Aty_Main.bundlePersonnelPlacement.getParcelableArrayList("personnelList");
		Aty_Main.bundlePersonnelPlacement.putParcelableArrayList("personnelList", personnelList);
		
		findViewById(R.id.btnYes).setOnClickListener(this);
		findViewById(R.id.btnAllSelect).setOnClickListener(this);
		for (int i = 0; i < personnelList.size(); i++) {
			personnelList.get(i).personnelCheckBox=new CheckBox(this);
			personnelList.get(i).personnelCheckBox.setText(personnelList.get(i).getDepartment()+personnelList.get(i).getPosition()+personnelList.get(i).getName());
		}
		selectPersonnel(personnelList);
		judgeIsChecked(personnelList);
	}

	private void judgeIsChecked(ArrayList<Data_ClubInformation> personnelList2) {
		for (int i = 0; i < personnelList2.size(); i++) {

			if (personnelList2.get(i).personnelCheckBox.isChecked()) {
				personnelList2.get(i).isCheck=true;
			}else {
				personnelList2.get(i).isCheck=false;
			}
		}
	}

	private void selectPersonnel(ArrayList<Data_ClubInformation> personnelList2) {
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
		Intent iPersonnelPlacement=new Intent(Aty_PersonnelList.this, Aty_PersonnelArrange.class);
		Aty_Main.bundlePersonnelPlacement.putParcelableArrayList("personnelList", personnelList);
		startActivity(iPersonnelPlacement);
		finish();
	}
}
