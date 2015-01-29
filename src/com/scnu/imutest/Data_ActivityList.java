package com.scnu.imutest;

import android.content.Context;
import android.content.Intent;

public class Data_ActivityList {
	private String activityListName="";
	private Context acitvityContext=null;
	private Intent activityRelatIntent=null;
	private Data_TaskDistribute activityTask=null;
	
	public Data_ActivityList(Context context,String ListName,Intent RelatIntent) {
		this.acitvityContext=context;
		this.activityListName=ListName;
		this.activityRelatIntent=RelatIntent;
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
