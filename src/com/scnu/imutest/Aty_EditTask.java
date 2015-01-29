package com.scnu.imutest;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Aty_EditTask extends Activity {
	
	//��������
		@SuppressWarnings("unused")
		private TextView theme;      //�����ʶ�����
		@SuppressWarnings("unused")
		private TextView time;       //��ֹʱ���ʶ�����
		@SuppressWarnings("unused")
		private TextView message;    //���ݱ�ʶ�����
		private EditText m_theme;    //������������
		private EditText m_time;     //��ֹʱ����������
		private EditText m_message;  //������������
		private String s_department;  //���������ַ���
		private String s_theme;          //���������ַ���
		private String s_time;            //�����ֹʱ���ַ���
		private String s_message;     //���������ַ���
		private Bundle bundle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_task);
		/*���TextView����*/
		theme=(TextView)findViewById(R.id.tvActivityID);
		time=(TextView)findViewById(R.id.tvActivityTime);
		message=(TextView)findViewById(R.id.tvActivityContext);
		/*���EditText����*/
		m_theme=(EditText)findViewById(R.id.tvActivityID);
		m_time=(EditText)findViewById(R.id.tvActivityTime);
		m_message=(EditText)findViewById(R.id.tvActivityContext);
		
		//receiveMessage();
		
		/*����EditText��ʾ������*/
		m_theme.setHint("����������");
		m_time.setHint("�������ֹʱ��");
		m_message.setHint("��������������");
		/*���Button����*/
		final Button back=(Button)findViewById(R.id.btnReturnActivity);
		final Button save=(Button)findViewById(R.id.btnSaveEditTask);
		final Button next=(Button)findViewById(R.id.btnPersonnelArrange);
		
		/*����button�齨���¼�listener*/
		back.setOnClickListener(backOnClick);
		save.setOnClickListener(saveOnClick);
		next.setOnClickListener(nextOnClick);
	}
	
	/*���������һ��Activity���ݵĺ���*/
	@SuppressWarnings("unused")
	private void receiveMessage()
	{
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
