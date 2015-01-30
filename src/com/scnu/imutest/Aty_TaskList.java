package com.scnu.imutest;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class Aty_TaskList extends Activity implements OnItemClickListener {

	private ListView lv;
	private ArrayAdapter<Data_Task> adapter;
	private int numTouch=1;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity_list);

		lv=(ListView) findViewById(R.id.listView1);
		adapter=new ArrayAdapter<Data_Task>(this, android.R.layout.simple_list_item_1);
		findViewById(R.id.btnAdd).setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				adapter.add(new Data_Task(Aty_TaskList.this, getResources().getString(R.string.task)+numTouch+"\n",new Intent(Aty_TaskList.this, Aty_EditTask.class)));
				numTouch++;				
			}
		});

		lv.setAdapter(adapter);
		lv.setOnItemClickListener(this);


	}




	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Data_Task data=adapter.getItem(position);

		Intent iPersonnelArrange = new Intent().setClass(this, Aty_PersonnelArrange.class);
		startActivity(iPersonnelArrange);
		

	}
}
