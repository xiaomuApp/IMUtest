package com.scnu.imutest;

import android.content.Context;
import android.content.Intent;

public class Data_Task {
	private String activityListName="";
	private Context acitvityContext=null;
	private Intent activityRelatIntent=null;
	private Data_TaskDistribute activityTask=null;
	
	public Data_Task(Context context,String listName,Intent relatIntent,Data_TaskDistribute activityTask) {
		this.acitvityContext=context;
		this.activityListName=listName;
		this.activityRelatIntent=relatIntent;
		this.activityTask=activityTask;
	}
	
	
	

	
	public String getActivityListName() {
		return activityListName;
	}





	public void setActivityListName(String activityListName) {
		this.activityListName = activityListName;
	}





	public Context getAcitvityContext() {
		return acitvityContext;
	}





	public void setAcitvityContext(Context acitvityContext) {
		this.acitvityContext = acitvityContext;
	}





	public Intent getActivityRelatIntent() {
		return activityRelatIntent;
	}





	public void setActivityRelatIntent(Intent activityRelatIntent) {
		this.activityRelatIntent = activityRelatIntent;
	}





	public Data_TaskDistribute getActivityTask() {
		return activityTask;
	}





	public void setActivityTask(Data_TaskDistribute activityTask) {
		this.activityTask = activityTask;
	}





	public void StarActivity()
	{
		getAcitvityContext().startActivity(getActivityRelatIntent());
	}
	
	@Override
	public String toString() {
		return getActivityListName();
	}

}
