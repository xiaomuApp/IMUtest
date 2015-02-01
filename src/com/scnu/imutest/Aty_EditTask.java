package com.scnu.imutest;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Aty_EditTask extends Activity {
	/*定义变量*/
	private EditText m_theme;    //定义主题输入框变量
	private EditText m_time;     //定义截止时间输入框变量
	private EditText m_message;  //定义内容输入框变量
	Data_TaskDistribute data;     //定义任务的数据变量
	private Bundle bundle;         //定义Activity间传递数据的打包变量
	private ArrayList<Data_ClubInformation> personnelList=null;//下面通过bundle获取到的数据，可以使用

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		setContentView(R.layout.activity_edit_task);
		/*获得EditText对象*/
		m_theme=(EditText)findViewById(R.id.evTaskTheme);
		m_time=(EditText)findViewById(R.id.evTaskDealtime);
		m_message=(EditText)findViewById(R.id.evTaskContent);
	
		/*获得Button对象监听事件*/
		findViewById(R.id.btnReturnActivity).setOnClickListener(backOnClick);
		findViewById(R.id.btnSaveEditTask).setOnClickListener(saveOnClick);
		findViewById(R.id.btnPersonnelArrange).setOnClickListener(nextOnClick);
	}

	/*获取前一个Activity传递过来的数据*/
	public void GetMessageFromAty_Activity()
	{
		personnelList=Aty_Main.bundlePersonnelPlacement.getParcelableArrayList("personnelList");
		Bundle bundle=this.getIntent().getExtras();
		m_theme.setText(Integer.toString(bundle.getInt("position")));
	}

	/*将Activity的数据打包并传递到下一个Activity*/
	public void GetMessageInThisActivity()
	{
		data = new Data_TaskDistribute(null, m_time.getText().toString(), m_message.getText().toString(), m_theme.getText().toString(), null);
		bundle = new Bundle();
		bundle.putSerializable("task", data);
	}

/*提醒已保存显示函数*/
public void DisplayToast(String str)
{
	Toast toast=Toast.makeText(this,str,Toast.LENGTH_SHORT);
	toast.setGravity(Gravity. TOP, 0,220);
	toast.show();
}

//返回按钮响应函数
private Button.OnClickListener backOnClick=new Button.OnClickListener()
{
	public void onClick(View u)
	{
		finish();
	}
};

//保存按钮响应函数
private Button.OnClickListener saveOnClick=new Button.OnClickListener()
{
	public void onClick(View v)
	{

		Intent intent=new Intent();
//		GetMessageInThisActivity();
//	    intent.putExtras(bundle);
	    setResult(RESULT_OK,intent);
		DisplayToast("保存任务信息成功");
	}
};

//人员分配按钮响应函数
private Button.OnClickListener nextOnClick=new Button.OnClickListener()
{
	public void onClick(View w)
	{
		AlertDialog.Builder nextDlg=new AlertDialog.Builder(Aty_EditTask.this);
		nextDlg.setTitle("提醒");
		nextDlg.setMessage("是否跳转到人员分配界面？");
		nextDlg.setIcon(android.R.drawable.ic_dialog_info);
		nextDlg.setCancelable(false);
		nextDlg.setNegativeButton("否",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						finish();
					}
				});
		nextDlg.setPositiveButton("是", 
				new DialogInterface.OnClickListener() {	
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent it=new Intent();
						it.setClass(Aty_EditTask.this,Aty_PersonnelArrange.class);
						GetMessageInThisActivity();
						it.putExtras(bundle);
						startActivity(it);	
						finish();
					}
				});
		nextDlg.show();
	}
};

}
