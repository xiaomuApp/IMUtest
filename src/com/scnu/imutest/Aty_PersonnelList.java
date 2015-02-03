package com.scnu.imutest;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Aty_PersonnelList extends Activity implements OnClickListener {

	private MyAdapter adapter;
	private ListView listViewPesonnel;
	private static ArrayList<Data_ClubInformation> personnelList=new ArrayList<Data_ClubInformation>();
	private static ArrayList<Data_ClubInformation> addPersonnelList=new ArrayList<Data_ClubInformation>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personnel_list);
		
		listViewPesonnel = (ListView) findViewById(R.id.lvAllPeople);
		
		findViewById(R.id.btnCommit).setOnClickListener(this);
		findViewById(R.id.btnAllSelect).setOnClickListener(this);
		findViewById(R.id.btnReturnPeopleArrange).setOnClickListener(this);
		personnelList=Aty_Main.bundlePersonnelPlacement.getParcelableArrayList("personnelList");
		adapter = new MyAdapter(this, R.layout.list_cell_people_list, personnelList);
		listViewPesonnel.setAdapter(adapter);
	}

	public class MyAdapter extends Adapter_PersonnelList<Data_ClubInformation> {
		
		public MyAdapter(Context context, int listCellId,
				ArrayList<Data_ClubInformation> list) {
			super(context, listCellId, list);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder viewHolder;  
			if(convertView == null){ 
				convertView=LayoutInflater.from(getContext()).inflate(listCellId, null);
				viewHolder = new ViewHolder();  
				viewHolder.tvNameDepartment = (TextView) convertView.findViewById(R.id.tvNameDepartment);
				viewHolder.tvPosition = (TextView) convertView.findViewById(R.id.tvPosition);
				viewHolder.checkBox = (CheckBox)convertView.findViewById(R.id.checkBox);
				viewHolder.portrait = (ImageView) convertView.findViewById(R.id.ivPortrait);
				convertView.setTag(viewHolder);  
			}else{  
				viewHolder = (ViewHolder)convertView.getTag();  
			}  
			final Data_ClubInformation msg = personnelList.get(position);  
			viewHolder.tvNameDepartment.setText(msg.getName()+"("+msg.getDepartment()+")"); 
			viewHolder.tvPosition.setText("职位："+msg.getPosition());
			viewHolder.checkBox.setChecked(msg.isCheck);
			
			viewHolder.checkBox.setOnClickListener(new OnClickListener() {  

				@Override  
				public void onClick(View v) {  
					if(msg.isCheck){  
						msg.isCheck = false; 
						addPersonnelList.remove(msg);
					}else{  
						msg.isCheck = true;  
						addPersonnelList.add(msg);
					}  

				}  
			});
			return convertView;
		}
		
		public void checkAll(){
			for(Data_ClubInformation msg:personnelList){
				if(msg.isCheck == false){
					msg.isCheck = true;
					addPersonnelList.add(msg);
				}
			}
			notifyDataSetChanged();
		}
		
	};
	
	private class ViewHolder{  
		TextView tvNameDepartment;
		TextView tvPosition;
		CheckBox checkBox;
		ImageView portrait;
	}  

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnCommit:
			finishPersonnelChoice();
			break;
		case R.id.btnAllSelect:
			personnelAllSelect();
			break;
		case R.id.btnReturnPeopleArrange:
			returnPeopleArrange();
			break;

		default:
			break;
		}
	}

	private void personnelAllSelect() {
		adapter.checkAll();
	}

	private void finishPersonnelChoice() {

		Toast.makeText(this, "跳转至人员安排", Toast.LENGTH_SHORT).show();
		Intent iPersonnelPlacement=new Intent();
		Bundle data = new Bundle();
		data.putParcelableArrayList("add", addPersonnelList);
		iPersonnelPlacement.putExtras(data);
		setResult(1, iPersonnelPlacement);
		finish();
	}
	
	private void returnPeopleArrange(){
		setResult(0);
		finish();
	}
}
