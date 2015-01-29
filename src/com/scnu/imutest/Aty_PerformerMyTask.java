package com.scnu.imutest;

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

	
	private ListView lv;
	private ArrayAdapter<String>  adapter;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_performer_assignment);
        
        
        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1);
        lv=(ListView)findViewById(R.id.lv);
        lv.setAdapter(adapter);
        adapter.add("����һ");
        adapter.add("�����");
        adapter.add("������");
        adapter.add("������");
        adapter.add("������");
        adapter.add("������");
        
        lv.setOnItemClickListener(this);
    }


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
		
		Intent iPerformerActivityList=new Intent(Aty_PerformerMyTask.this,Aty_PerformerTaskList.class);
		Bundle value=new Bundle();
		value.putString("id", "任务1");
		value.putString("context", "女生节搬桌子");
		value.putString("time", "2015年2月27日");
		iPerformerActivityList.putExtras(value);
		startActivity(iPerformerActivityList);
	}


	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}




}
