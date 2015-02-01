package com.scnu.imutest;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
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
		
		adapter = new MyAdapter();
		listViewPesonnel.setAdapter(adapter);
	}

	private class MyAdapter extends BaseAdapter{

		public MyAdapter(){
				personnelList=Aty_Main.bundlePersonnelPlacement.getParcelableArrayList("personnelList");
				
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
		@Override
		public int getCount() {
			return personnelList.size();
		}

		@Override
		public ArrayList<Data_ClubInformation> getItem(int position) {
			return personnelList;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder viewHolder;  
			if(convertView == null){  
				LayoutInflater inflater = LayoutInflater.from(Aty_PersonnelList.this);  
				convertView = inflater.inflate(R.layout.list_cell_people_list, null);  
				viewHolder = new ViewHolder();  
				viewHolder.textView = (TextView) convertView.findViewById(R.id.tvPeople);
				viewHolder.checkBox = (CheckBox)convertView.findViewById(R.id.checkBox);  
				convertView.setTag(viewHolder);  
			}else{  
				viewHolder = (ViewHolder)convertView.getTag();  
			}  
			final Data_ClubInformation msg = personnelList.get(position);  
			viewHolder.textView.setText(msg.toString());  
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
		
	}
	
	private class ViewHolder{  
		TextView textView;
		CheckBox checkBox;  
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
