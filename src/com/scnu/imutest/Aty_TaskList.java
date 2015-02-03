package com.scnu.imutest;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;


public class Aty_TaskList extends Activity {

	private ArrayList<Data_ClubInformation> personnelList=null;//下面通过bundle获取到的数据，可以使用
	private int firstpersonnel=1;//以第一个成员进入组织者身份

	private ListView mListView;
	private SimpleAdapter mAdapter=null;
	private int numTouch=1;

	private LinkedList<HashMap<String, Object>> list;  //显示在adapter中的列表
	private HashMap<String, Object> data=null;

	private Data_TaskDistribute datatask;
	private LinkedList<Data_TaskDistribute> DataList;//存储数据类型为Data_TaskDistribute的表

	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_activity_list);
	    
	    personnelList=Aty_Main.bundlePersonnelPlacement.getParcelableArrayList("personnelList");
		Aty_Main.bundlePersonnelPlacement.putParcelableArrayList("personnelList", personnelList);
	    
	    mListView=(ListView) findViewById(R.id.activitylist);
	    list=new LinkedList<HashMap<String,Object>>();
	    DataList=new LinkedList<Data_TaskDistribute>();
	   
	    //添加任务按钮
	    findViewById(R.id.btnAdd).setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent=new Intent(Aty_TaskList.this, Aty_EditTask.class);
				datatask = new Data_TaskDistribute("", numTouch++, "", "", "", null);
				Bundle bundle=new Bundle();
				bundle.putSerializable("task", datatask);
				intent.putExtras(bundle);
				startActivityForResult(intent, numTouch);				
			}
		});
	    
	    //列表的相应按钮
	    mListView.setOnItemClickListener(new OnItemClickListener() {		
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				Data_TaskDistribute d=DataList.get(position);  
				Bundle bundle=new Bundle();
				Intent i=new Intent(Aty_TaskList.this, Aty_EditTask.class);
				bundle.putSerializable("task", d);
				i.putExtras(bundle);
				startActivityForResult(i, position);
			}
		});
	    
	    //返回按钮
	    findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				finish();				
			}
		});
	}



	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		switch(resultCode)
		{
		case RESULT_OK:
			datatask=new Data_TaskDistribute("", 0, "", "", "", null);
			datatask=(Data_TaskDistribute) intent.getSerializableExtra("task");    		
			
			data=new HashMap<String, Object>();
			data.put("taskName","任务" +datatask.getTaskId());
			data.put("taskSubject","活动主题"+datatask.getTaskSubject());
		    data.put("taskTime","截止时间"+datatask.getTaskcutofftime());
			    		
			if(datatask.getTaskId()>list.size())
			{
				list.add(data);
				DataList.add(datatask);
			}			
			else
			{
				DataList.add(datatask.getTaskId(), datatask);
				DataList.remove(datatask.getTaskId());
				list.add(datatask.getTaskId(), data);
				list.remove(datatask.getTaskId());			
			}			
			mAdapter=new SimpleAdapter(Aty_TaskList.this,list, R.layout.list_cell_activity_list, new String[]{"taskName","taskSubject","taskTime"},new int[]{R.id.taskName,R.id.taskSubject,R.id.taskTime} );
			mListView.setAdapter(mAdapter);
			break;
			default:
				break;
			
		}
	}



	}

