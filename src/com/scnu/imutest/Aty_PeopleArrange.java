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


public class Aty_PeopleArrange extends Activity implements OnItemClickListener {

	private ListView lv;
	private ArrayAdapter<ClubInformationData> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_people_arrange);
		adapter = new ArrayAdapter<ClubInformationData>(this, R.layout.list_cell_people_arrange);

		lv = (ListView) findViewById(R.id.lvPeople);

		ArrayList<ClubInformationData> taskTheme = this.getIntent().getExtras().getParcelableArrayList("isCheckedPersonnelList");
		for (int i = 0; i < taskTheme.size(); i++) {
		TextView tv=new TextView(this);
		tv.setText(taskTheme.get (i).getName());
		}
		lv.setOnItemClickListener(this);
	}





	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		ClubInformationData data = adapter.getItem(position);

		Toast.makeText(this, String.format("����:%s ְλ:%s ����:%s ����:%s", data.getName(),data.getPosition(),data.getDepartment(),data.getClub()), Toast.LENGTH_SHORT).show();
	}

	public void startListPeople(View view){
		Intent intent = new Intent(this, Aty_PersonnelList.class);
		adapter.clear();
		startActivityForResult(intent, 0);
	}

	public ArrayList<ClubInformationData> data=new ArrayList<ClubInformationData>();
	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {

		if(arg1==1)return;
		data = arg2.getExtras().getParcelableArrayList("list");
		for(ClubInformationData d:data){
			adapter.add(d);
		}
		lv.setAdapter(adapter);

		super.onActivityResult(arg0, arg1, arg2);
	}


	public void returnLast(View view){
		Intent intent = new Intent(this, Aty_EditAssignment.class);
		startActivity(intent);

	}

	public void btnRelease(View view){

		Toast.makeText(this, "�˹��ܴ�����", Toast.LENGTH_SHORT).show();

	}
}
