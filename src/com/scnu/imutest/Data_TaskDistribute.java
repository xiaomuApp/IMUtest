package com.scnu.imutest;

import java.io.Serializable;
import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class Data_TaskDistribute implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//任务分配数据
	private String taskName="";//任务名
	private String taskcutofftime="";//暂定使用String类型，任务截止时间
	private String taskContent="";//任务内容
	private String taskSubject="";//任务主题
	private ArrayList<Data_ClubInformation> taskArrangePersonnel=null;
	
	public Data_TaskDistribute(String taskName,String taskcutofftime,String taskContent,String taskSubject,ArrayList<Data_ClubInformation> taskArrangePersonnel) {
		this.taskName=taskName;
		this.taskcutofftime=taskcutofftime;
		this.taskContent=taskContent;
		this.taskSubject=taskSubject;
		this.taskArrangePersonnel=taskArrangePersonnel;
	}
	
	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskcutofftime() {
		return taskcutofftime;
	}

	public void setTaskcutofftime(String taskcutofftime) {
		this.taskcutofftime = taskcutofftime;
	}

	public String getTaskContent() {
		return taskContent;
	}

	public void setTaskContent(String taskContent) {
		this.taskContent = taskContent;
	}

	public String getTaskSubject() {
		return taskSubject;
	}

	public void setTaskSubject(String taskSubject) {
		this.taskSubject = taskSubject;
	}

	public ArrayList<Data_ClubInformation> getTaskArrangePersonnel() {
		return taskArrangePersonnel;
	}

	public void setTaskArrangePersonnel(ArrayList<Data_ClubInformation> taskArrangePersonnel) {
		this.taskArrangePersonnel = taskArrangePersonnel;
	}

}
