package com.scnu.imutest;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.CheckBox;


public class Data_ClubInformation implements Parcelable{

	
	public static final int personnel=0;//执行者身份
	public static final int organier=1;//组织者身份
	private int identity=personnel;//默认为执行者
	private int id=0;
	private String name=" ";
	private String position=" ";
	private String department=" ";
	private String club=" ";
	public ArrayList<Data_Task> data_TaskList=null; //任务列表
	public boolean isCheck = false;
	public CheckBox personnelCheckBox=null;
	

	public Data_ClubInformation(int id,String name, String position, String department,
			String club,int identity) {
		super();
		this.id=id;
		this.name = name;
		this.position = position;
		this.department = department;
		this.club = club;
		this.identity=identity;
	}

	public int getIdentity() {
		return identity;
	}

	public void setIdentity(int identity) {
		this.identity = identity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getClub() {
		return club;
	}
	public void setClub(String club) {
		this.club = club;
	}

	@Override
	public String toString() {
		return department + " - " + name;
	}


	public boolean isCheck() {
		return isCheck;
	}

	public void setCheck(boolean isCheck) {
		this.isCheck = isCheck;
	}

	public CheckBox getPersonnelCheckBox() {
		return personnelCheckBox;
	}

	public void setPersonnelCheckBox(CheckBox personnelCheckBox) {
		this.personnelCheckBox = personnelCheckBox;
	}



	@Override
	public int describeContents() {
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(id);
		dest.writeString(name);
		dest.writeString(position);
		dest.writeString(department);
		dest.writeString(club);
	}

	public static final Parcelable.Creator<Data_ClubInformation> CREATOR = new Parcelable.Creator<Data_ClubInformation>() {

		@Override
		public Data_ClubInformation createFromParcel(Parcel source) {			
			return new Data_ClubInformation(source);
		}

		@Override
		public Data_ClubInformation[] newArray(int size) {
			return new Data_ClubInformation[size];
		}
	};

	public Data_ClubInformation(Parcel source){
		id=source.readInt();
		name = source.readString();
		position = source.readString();
		department = source.readString();
		club = source.readString();
	}
}

