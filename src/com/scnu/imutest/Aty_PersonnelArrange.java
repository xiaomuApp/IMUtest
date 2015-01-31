package com.scnu.imutest;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class Aty_PersonnelArrange extends Activity implements OnItemClickListener {

	private ArrayList<Data_ClubInformation> personnelList=null;//下面通过bundle获取到的数据，可以使用
	
	private ListView lv;
	private ArrayAdapter<Data_ClubInformation> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_people_arrange);
		personnelList=Aty_Main.bundlePersonnelPlacement.getParcelableArrayList("personnelList");
		Aty_Main.bundlePersonnelPlacement.putParcelableArrayList("personnelList", personnelList);
		
		
		adapter = new ArrayAdapter<Data_ClubInformation>(this, R.layout.list_cell_people_arrange);
		lv = (ListView) findViewById(R.id.lvPeople);

		lv.setOnItemClickListener(this);
	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Data_ClubInformation data = adapter.getItem(position);

		Toast.makeText(this, String.format("姓名:%s 职位:%s 部门:%s 社团:%s", data.getName(),data.getPosition(),data.getDepartment(),data.getClub()), Toast.LENGTH_SHORT).show();
	}

	public void startListPeople(View view){
		Intent intent = new Intent(this, Aty_PersonnelList.class);
		startActivityForResult(intent, 0);
	}

	public ArrayList<Data_ClubInformation> add=new ArrayList<Data_ClubInformation>();
	public ArrayList<Data_ClubInformation> concel=new ArrayList<Data_ClubInformation>();
	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {

		if(arg1==1)return;
		adapter.clear();
		add = arg2.getExtras().getParcelableArrayList("add");
		for(Data_ClubInformation d:add){
			adapter.add(d);
		}
		
		adapter.notifyDataSetChanged();
		lv.setAdapter(adapter);

		super.onActivityResult(arg0, arg1, arg2);
	}


	public void returnLast(View view){
		Intent intent = new Intent(this, Aty_EditTask.class);
		startActivity(intent);

	}

	public void btnRelease(View view){

		Toast.makeText(this, "此功能coding中。。。", Toast.LENGTH_SHORT).show();

	}

}
