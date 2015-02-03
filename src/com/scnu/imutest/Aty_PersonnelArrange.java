package com.scnu.imutest;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class Aty_PersonnelArrange extends Activity implements OnItemClickListener {

	private ListView lv;
	private Adapter_PersonnelArrange adapter;
	private TextView m_theme;
	private Data_TaskDistribute taskDistribute;
	public ArrayList<Data_ClubInformation> arrangePeople;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_people_arrange);
/*从编辑任务获取主题、截止时间、任务内容、活动名	*/	
		taskDistribute = (Data_TaskDistribute) getIntent().getExtras().getSerializable("task");
		m_theme = (TextView) findViewById(R.id.tvTaskTheme);
		m_theme.setText("任务主题："+taskDistribute.getTaskSubject());
		arrangePeople =new ArrayList<Data_ClubInformation>();
		
		adapter = new Adapter_PersonnelArrange(this, R.layout.list_cell_people_arrange, arrangePeople);
		lv = (ListView) findViewById(R.id.lvPeople);
		lv.setAdapter(adapter);

		lv.setOnItemClickListener(this);
		
	}
	
	public class Adapter_PersonnelArrange extends Adapter_Gerneral<Data_ClubInformation>{

		public Adapter_PersonnelArrange(Context context, int listCellId,
				ArrayList<Data_ClubInformation> list) {
			super(context, listCellId, list);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView text;
			if(convertView == null){
				convertView=LayoutInflater.from(getContext()).inflate(R.layout.list_cell_people_arrange, null);
				text = (TextView) convertView.findViewById(R.id.tvCheckedPeople);
				convertView.setTag(text);
			}else{
				text = (TextView) convertView.getTag();
			}
			final Data_ClubInformation msg = personnelList.get(position);
			text.setText(msg.getDepartment()+" - "+msg.getName());
			return convertView;
		}
		
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
			break;
		case 1:
			arrangePeople = arg2.getExtras().getParcelableArrayList("add");
			for(Data_ClubInformation d:arrangePeople){
				adapter.add(d);
				System.out.println("添加"+d.getName());
			}
			arrangePeople = arg2.getExtras().getParcelableArrayList("concel");
			for(Data_ClubInformation d:arrangePeople){
				adapter.removeItem(d);
				System.out.println("取消"+d.getName());
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
		finish();

	}
/*发布数据，服务器可以从这里获得，任务和人员安排信息*/
	public void btnRelease(View view){
		arrangePeople = adapter.getList();
		taskDistribute.setTaskArrangePersonnel(arrangePeople);
		for(Data_ClubInformation d:arrangePeople){
			System.out.println(d.toString());
		}
		Toast.makeText(this, "此功能coding中。。。", Toast.LENGTH_SHORT).show();

	}

}
