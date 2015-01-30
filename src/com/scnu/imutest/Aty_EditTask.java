package com.scnu.imutest;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Aty_EditTask extends Activity {
	
	//��������
		private EditText m_theme;    //������������
		private EditText m_time;     //��ֹʱ����������
		private EditText m_message;  //������������
		private String s_department;  //���������ַ���
		private String s_theme;          //���������ַ���
		private String s_time;            //�����ֹʱ���ַ���
		private String s_message;     //���������ַ���
		private Bundle bundle;
		private ArrayList<Data_ClubInformation> personnelList=null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_task);
		/*���EditText����*/
		m_theme=(EditText)findViewById(R.id.evTaskTheme);
		System.out.println(5);
		m_time=(EditText)findViewById(R.id.evTaskCutOffTime);
		System.out.println(6);
		m_message=(EditText)findViewById(R.id.evTaskContent);
		System.out.println(7);
		
		//receiveMessage();
		
		/*����EditText��ʾ������*/
		System.out.println(8);
		m_theme.setHint("任务主题");
		System.out.println(9);
		m_time.setHint("截止时间");
		System.out.println(10);
		m_message.setHint("任务内容");
		System.out.println(11);
		/*���Button����*/
		System.out.println(12);
		Button back=(Button) findViewById(R.id.btnReturnActivity);
		System.out.println(13);
		Button save=(Button)findViewById(R.id.btnSaveEditTask);
		System.out.println(14);
		Button next=(Button)findViewById(R.id.btnPersonnelArrange);
		System.out.println(15);
		
		/*����button�齨���¼�listener*/
		System.out.println(16);
		back.setOnClickListener(backOnClick);
		System.out.println(17);
		save.setOnClickListener(saveOnClick);
		System.out.println(18);
		next.setOnClickListener(nextOnClick);
		System.out.println(19);
	}
	
	/*���������һ��Activity���ݵĺ���*/
	@SuppressWarnings("unused")
	private void receiveMessage()
	{
		Aty_Main.bundlePersonnelPlacement.putParcelableArrayList("personnelList", personnelList);//返回数据
		System.out.println(20);
		Bundle bundle=this.getIntent().getExtras();
		
		s_department=bundle.getString("KEY_DEPARTMENT");
		s_theme=bundle.getString("KEY_THEME");
		s_time=bundle.getString("S_TIME");
		s_message=bundle.getString("KEY_MESSAGE");
		
		m_theme.setText(s_theme);
		m_time.setText(s_time);
		m_message.setText(s_message);
	}
	
	/*��ȡ�༭���ڵ����ݣ����������*/
	public void getMessage()
	{
		personnelList=Aty_Main.bundlePersonnelPlacement.getParcelableArrayList("personnelList");//获取数据
		s_theme=m_theme.getText().toString();
		s_time=m_time.getText().toString();
		s_message=m_message.getText().toString();
		
		bundle=new Bundle();
		bundle.putString("KEY_DEPARTMENT",s_department);
		bundle.putString("KEY_THEME",s_theme);
		bundle.putString("KEY_TIME",s_time);
		bundle.putString("KEY_MESSAGE",s_message);
	}
	
	public void DisplayToast(String str)
	{
		Toast toast=Toast.makeText(this,str,Toast.LENGTH_SHORT);
		toast.setGravity(Gravity. TOP, 0,220);
		toast.show();
	}
	
	//���ذ�ť��Ӧ����
	private Button.OnClickListener backOnClick=new Button.OnClickListener()
	{
		public void onClick(View u)
		{
			finish();
		}
	};
	
	//���水ť��Ӧ����
	private Button.OnClickListener saveOnClick=new Button.OnClickListener()
	{
		public void onClick(View v)
		{
			Intent intent=new Intent();
			getMessage();
		    intent.putExtras(bundle);
			DisplayToast("����������Ϣ�ɹ���");
		}
	};
	
	//��Ա������Ӧ����
	private Button.OnClickListener nextOnClick=new Button.OnClickListener()
	{
		public void onClick(View w)
		{
			AlertDialog.Builder nextDlg=new AlertDialog.Builder(Aty_EditTask.this);
			nextDlg.setTitle("����");
			nextDlg.setMessage("�Ƿ���ת����Ա������棿");
			nextDlg.setIcon(android.R.drawable.ic_dialog_info);
			nextDlg.setCancelable(false);
			nextDlg.setPositiveButton("��",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
						}
					});
			nextDlg.setNegativeButton("��", 
					new DialogInterface.OnClickListener() {	
						@Override
						public void onClick(DialogInterface dialog, int which) {
							Intent it=new Intent();
							it.setClass(Aty_EditTask.this,Aty_PersonnelArrange.class);
							getMessage();
							it.putExtras(bundle);
							startActivity(it);	
						}
					});
			nextDlg.show();
		}
	};

}
