package com.scnu.imutest;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class Aty_PerformerMyTask extends Activity implements OnClickListener, OnItemClickListener {

	private ArrayList<Data_ClubInformation> personnelList=null;//下面通过bundle获取到的数据，可以使用
	
	private ListView lv;
	private ArrayAdapter<Data_TaskDistribute>  adapter;
	private ArrayList<Data_TaskDistribute> TaskContent=Aty_Main.taskList;
	private int Couter=TaskContent.size();
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_performer_assignment);
		personnelList=Aty_Main.bundlePersonnelPlacement.getParcelableArrayList("personnelList");
		Aty_Main.bundlePersonnelPlacement.putParcelableArrayList("personnelList", personnelList);
        
        adapter=new ArrayAdapter<Data_TaskDistribute>(this, android.R.layout.simple_expandable_list_item_1);
        lv=(ListView)findViewById(R.id.lv);
        lv.setAdapter(adapter);
        for(int t=0;t<Couter;t++)
        {
        	adapter.add(TaskContent.get(t));
        }
        
        lv.setOnItemClickListener(this);
    }


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
		
		Intent iPerformerActivityList=new Intent(Aty_PerformerMyTask.this,Aty_PerformerTaskList.class);
		Bundle value=new Bundle();
		Data_TaskDistribute data=adapter.getItem(position);
		value.putString("id", data.getTaskName());
		value.putString("context", data.getTaskContent());
		value.putString("time", data.getTaskcutofftime());
		//value.putString("theme", data.getTaskSubject());
		iPerformerActivityList.putExtras(value);
		startActivity(iPerformerActivityList);
	}


	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}




}
