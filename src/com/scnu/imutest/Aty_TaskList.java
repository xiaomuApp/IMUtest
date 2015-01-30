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


public class Aty_TaskList extends Activity implements OnItemClickListener {

	private ArrayList<Data_ClubInformation> personnelList=null;//下面通过bundle获取到的数据，可以使用
	private ListView lv;
	private ArrayAdapter<Data_Task> adapter;
	private ArrayList<Data_Task> listData_Task=null;
	private Data_Task data_Task=null;
	private int numTouch=0;
	private int firstpersonnel=1;//以第一个成员进入组织者身份

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity_list);
		
		personnelList=Aty_Main.bundlePersonnelPlacement.getParcelableArrayList("personnelList");
		Aty_Main.bundlePersonnelPlacement.putParcelableArrayList("personnelList", personnelList);
		listData_Task=new ArrayList<Data_Task>();
		lv=(ListView) findViewById(R.id.listView1);
		adapter=new ArrayAdapter<Data_Task>(this, android.R.layout.simple_list_item_1);
		findViewById(R.id.btnAdd).setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				listData_Task=personnelList.get(firstpersonnel).data_TaskList;
				numTouch=listData_Task.size();
				data_Task=new Data_Task(Aty_TaskList.this, "任务"+(++numTouch), null, null);
				listData_Task.add(data_Task);
				adapter.add(data_Task);
			}
		});
		showTaskList();
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(this);


	}

	private void showTaskList() {
		for (int i = 0; i < personnelList.get(firstpersonnel).data_TaskList.size(); i++) {
			adapter.add(new Data_Task(this, personnelList.get(firstpersonnel).data_TaskList.get(i).getActivityListName(), new Intent(Aty_TaskList.this, Aty_EditTask.class), null));
		}

	}
	



	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent iEditTask = new Intent(this, Aty_EditTask.class);
		Bundle bundle=new Bundle();
		bundle.putInt("taskPosition", position);
		iEditTask.putExtra("bundleTaskPosition", bundle);
		startActivity(iEditTask);
		

	}
}
