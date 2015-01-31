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
import android.widget.TextView;
import android.widget.Toast;


public class Aty_PersonnelArrange extends Activity implements OnItemClickListener {

	private ArrayList<Data_ClubInformation> personnelList=null;//下面通过bundle获取到的数据，可以使用
	
	private ListView lv;
	private ArrayAdapter<Data_ClubInformation> adapter;
	private TextView m_theme;
	private Data_TaskDistribute taskDistribute;
	public static ArrayList<Data_ClubInformation> arrangePeople =new ArrayList<Data_ClubInformation>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_people_arrange);
		personnelList=Aty_Main.bundlePersonnelPlacement.getParcelableArrayList("personnelList");
/*从编辑任务获取主题、截止时间、任务内容、活动名	*/	
		taskDistribute = (Data_TaskDistribute) getIntent().getExtras().getSerializable("task");
		m_theme = (TextView) findViewById(R.id.tvTaskTheme);
		m_theme.setText(taskDistribute.getTaskSubject());
		
		adapter = new ArrayAdapter<Data_ClubInformation>(this, R.layout.list_cell_people_arrange);
		lv = (ListView) findViewById(R.id.lvPeople);

		lv.setOnItemClickListener(this);
		
	}
/*点击每个被安排的人的反应*/
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Data_ClubInformation data = adapter.getItem(position);

		Toast.makeText(this, String.format("姓名:%s 职位:%s 部门:%s 社团:%s", data.getName(),data.getPosition(),data.getDepartment(),data.getClub()), Toast.LENGTH_SHORT).show();
	}
/*选择人员*/
	public void startListPeople(View view){
		Intent intent = new Intent(this, Aty_PersonnelList.class);
		startActivityForResult(intent, 0);
	}

	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {

		switch(arg1){
		case 0:
			adapter.clear();
			arrangePeople = arg2.getExtras().getParcelableArrayList("add");
			for(Data_ClubInformation d:arrangePeople){
				adapter.add(d);
			}
			
			adapter.notifyDataSetChanged();
			lv.setAdapter(adapter);
			break;
		default:
			break;
			
		}

		super.onActivityResult(arg0, arg1, arg2);
	}

/*返回编辑任务*/
	public void returnLast(View view){
		Intent intent = new Intent(this, Aty_EditTask.class);
		startActivity(intent);

	}
/*发布数据，服务器可以从这里获得，任务和人员安排信息*/
	public void btnRelease(View view){
		taskDistribute.setTaskArrangePersonnel(arrangePeople);
		for(Data_ClubInformation d:arrangePeople){
			System.out.println(d.toString());
			
		}
		Toast.makeText(this, "此功能coding中。。。", Toast.LENGTH_SHORT).show();

	}

}
