package com.scnu.imutest;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Aty_PerformerTaskList extends Activity {

	private ArrayList<Data_ClubInformation> personnelList=null;//下面通过bundle获取到的数据，可以使用
	private TextView tvActivityContext,tvActivityTime,tvActivityID;
 	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_performer_activity_list);
		personnelList=Aty_Main.bundlePersonnelPlacement.getParcelableArrayList("personnelList");
		Aty_Main.bundlePersonnelPlacement.putParcelableArrayList("personnelList", personnelList);
		
		tvActivityID=(TextView) findViewById(R.id.tvActivityID);
		tvActivityTime=(TextView) findViewById(R.id.tvActivityTime);
		tvActivityContext=(TextView) findViewById(R.id.tvActivityContext);
		Bundle bundle=new Bundle();
		bundle=this.getIntent().getExtras();
		tvActivityID.setText(bundle.getString("id"));
		tvActivityContext.setText(bundle.getString("context"));
		tvActivityTime.setText(bundle.getString("time"));
		
		findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i=new Intent(Aty_PerformerTaskList.this, Aty_Main.class);
				
				startActivity(i);
			}
		});
	}
}
