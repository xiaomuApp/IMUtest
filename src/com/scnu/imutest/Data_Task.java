package com.scnu.imutest;

import android.content.Context;
import android.content.Intent;
import android.widget.HeterogeneousExpandableList;

public class Data_Task {
	private Context acitvityContext=null;
	private Intent activityRelatIntent=null;
	private Data_TaskDistribute activityTask=null;
	

	private String activityTaskName="";
	private String activityTaskSubject="";
	private String activityTaskTime="";
	private String activityTaskContent="";
	private int taskId=0;
	
	public Data_Task(Context context,String activityTaskName,int taskId,String activityTaskSubject,String activityTaskTime,String activityTaskContent,Intent relatIntent,Data_TaskDistribute activityTask) {
		this.acitvityContext=context;
		this.activityRelatIntent=relatIntent;
		
		this.activityTaskName=activityTaskName;
		this.taskId=taskId;
		this.activityTaskSubject=activityTaskSubject;
		this.activityTaskTime=activityTaskTime;
		this.activityTaskContent=activityTaskContent;
		
		this.activityTask=activityTask;
	}
	


	public Context getAcitvityContext() {
		return acitvityContext;
	}

	public String getActivityTaskContent() {
		return activityTaskContent;
	}
	
	public String getActivityTaskName() {
		return activityTaskName;
	}
	
	public String getActivityTaskSubject() {
		return activityTaskSubject;
	}
	
	public String getActivityTaskTime() {
		return activityTaskTime;
	}
	
	public int getTaskId() {
		return taskId;
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



	@Override
	public String toString() {
		return (getActivityTaskName()+getTaskId()+"\n"+getActivityTaskSubject()+"\n"+getActivityTaskTime());
	}

}
