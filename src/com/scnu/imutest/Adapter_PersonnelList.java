package com.scnu.imutest;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class Adapter_PersonnelList<T> extends BaseAdapter {

	public Adapter_PersonnelList(Context context, int listCellId, ArrayList<T> list) {
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
	
	private ArrayList<T> personnelList = new ArrayList<T>();
	
	public void copy(ArrayList<T> list){
		personnelList = list;
	}
	
	public ArrayList<T> getList(){
		return personnelList;
	}
	
	public void remove(int position){
		personnelList.remove(position);
	}
	
	public void removeLast(){
		personnelList.remove(getCount()-1);
	}
	
	public void removeItem(T item){
		personnelList.remove(item);
	}
	
	public void add(T item){
		personnelList.add(item);
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
