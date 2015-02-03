package com.scnu.imutest;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class Adapter_Gerneral<T extends Data_ClubInformation> extends BaseAdapter {

	public Adapter_Gerneral(Context context, int listCellId, ArrayList<T> list) {
		super();
		this.context = context;
		this.listCellId = listCellId;
		this.personnelList = list;
	}

	private Context context;
	protected int listCellId=0;
	
	public Context getContext() {
		return context;
	}
	
	protected ArrayList<T> personnelList = new ArrayList<T>();
	
	public void copy(ArrayList<T> list){
		personnelList = list;
	}
	
	public ArrayList<T> getList(){
		return personnelList;
	}
	
	public void remove(int position){
		personnelList.remove(position);
	}
	
	
	public void removeItem(T item){
		int pos = getPos(item);
		if(pos == -1){
			System.out.println("没有移除元素");
			return;
		}else{
			System.out.println("找到移除元素"+item.toString());
			remove(getPos(item));
		}
		notifyDataSetChanged();
	}
	
	public void add(T item){
		personnelList.add(item);
	}
	
	public int getPos(T item){
		for(int i=0; i<personnelList.size(); i++){
			if(personnelList.get(i).equals(item)){
				System.out.println(personnelList.get(i)+" = "+item);
				return i;
			}else{
				System.out.println(personnelList.get(i)+" != "+item);
			}
		}
		return -1;
		
	}
	
	@Override
	public int getCount() {
		return personnelList.size();
	}

	@Override
	public T getItem(int position) {
		return personnelList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	abstract public View getView(int position, View convertView, ViewGroup parent);
	
}
