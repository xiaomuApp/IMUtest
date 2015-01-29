package com.scnu.imutest;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.CheckBox;


public class Data_ClubInformation implements Parcelable{

	public Data_ClubInformation(int id,String name, String position, String department,
			String club) {
		super();
		this.id=id;
		this.name = name;
		this.position = position;
		this.department = department;
		this.club = club;
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

	private int id=0;
	private String name=" ";
	private String position=" ";
	private String department=" ";
	private String club=" ";
	public boolean isCheck = false;
	public CheckBox personnelCheckBox=new CheckBox(null);

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

