package com.henli.user.aty;
 
import com.example.save.R;


import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.StrictMode;
import android.util.Log;
import android.view.Window;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import cn.bmob.sms.BmobSMS;
import cn.bmob.sms.exception.BmobException;
import cn.bmob.sms.listener.RequestSMSCodeListener;
import cn.bmob.sms.listener.VerifySMSCodeListener;

public class RegisterActivity extends Activity implements OnClickListener{
	 private EditText et_phone, et_code,register_name,register_password;  
	 private Button Message_btn, register_btn;  
	 private Button btn_back;
	
       @Override
    protected void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
    	
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.register);
    	initBomb();  
        initView();  
        initEvent();
        
    	
		
    }
    
       
       
       
       private void initEvent() {  
    	   register_btn.setOnClickListener(this);  
           Message_btn.setOnClickListener(this); 
           btn_back.setOnClickListener(this);
       }  
     
       /** 
        * Ŀ��Ҫ�������ֻ��ţ������ȡ��֤�룬�û�����֤����д��ϣ������¼ 
        * ϸ�ڣ� 
        * 1�������ֻ���ʱ���ж��ǲ���11λ�ֻ��ţ�����et_code11λ���������ȡ 
        * ��֤�밴ťʱ����ʾ-->������11λ��Ч�ֻ����룬��11λ������е����ȡ�� 
        * ֤�����������ʾ��֤���ѷ��ͣ��뾡��ʹ�� 
        * 2�������л�ȡ��֤������󣬻�ȡ��֤�밴ť��ɻ�ɫ���Ҳ��ɵ���������� 
        * ����ʱ����������ʱ1���Ӻ�������µ�����ٴη�����֤�� 
        * 3�������¼��ťʱ�����ֻ��ź���֤����һ��Ϊ�գ�����ʾ�ֻ�������֤�� 
        * ����Ϊ�գ�����֤������д�����ж��û���д����֤����ϵͳ������֤���Ƿ�һ�£� 
        * һ������ʾ��¼�ɹ�����������ʾ��֤��������� 
        */  
       private void initBomb() {  
           BmobSMS.initialize(RegisterActivity.this, "68082840878fde1dac458cbbd6498daf");  
       }  
     
     
       @Override  
       public void onClick(View v) {  
           Log.e("MESSAGE:", "1");  
           String userPhone = et_phone.getText().toString();  
           String Phonecode = et_code.getText().toString();  
           switch (v.getId()) {  
               case R.id.Message_btn:  
                   Log.e("MESSAGE:", "2");  
                   if (userPhone.length() != 11) {  
                       Toast.makeText(this, "������11λ��Ч�ֻ�����", Toast.LENGTH_SHORT).show();  
                   }  
                   else {  
                       Log.e("MESSAGE:", "3");  
                       //���л�ȡ��֤������͵���ʱ1���Ӳ���  
                       BmobSMS.requestSMSCode(this, userPhone, "����ģ��", new RequestSMSCodeListener() {  
                           @Override  
                           public void done(Integer integer, BmobException e) {  
                               if (e == null) {  
                                   //���ͳɹ�ʱ���û�ȡ��֤�밴ť���ɵ������Ϊ��ɫ  
                                   Message_btn.setClickable(false);  
                                   Message_btn.setBackgroundColor(Color.GRAY);  
                                   Toast.makeText(RegisterActivity.this, "��֤�뷢�ͳɹ����뾡��ʹ��", Toast.LENGTH_SHORT).show();  
                                   /** 
                                    * ����ʱ1���Ӳ��� 
                                    * ˵���� 
                                    * new CountDownTimer(60000, 1000),��һ������Ϊ����ʱ��ʱ�䣬�ڶ�������Ϊ����ʱ�ļ��ʱ�� 
                                    * ��λ��Ϊms�����б���Ҫʵ��onTick()��onFinish()����������onTick()����Ϊ������ʱ�ڽ�����ʱ�� 
                                    * �����Ĳ��������Ĳ���millisUntilFinishedΪ���뵹��ʱ����ʱ��ʱ�䣬�Դ���Ϊ�����ܵ���ʱ�� 
                                    * Ϊ60000ms,����ʱ�ļ��ʱ��Ϊ1000ms��Ȼ��59000ms��58000ms��57000ms...�÷����Ĳ��� 
                                    * millisUntilFinished�͵�����Щÿ��仯������Ȼ�����1000���ѵ�λ����룬��ʾ��textView 
                                    * ��Button�ϣ���ʵ�ֵ���ʱ��Ч����onFinish()����Ϊ����ʱ����ʱҪ���Ĳ�����������Ժܺõ� 
                                    * ˵���÷������÷������Ҫע����ǵ�new CountDownTimer(60000, 1000)֮��һ��Ҫ����start() 
                                    * �����Ѹõ���ʱ��������������������start()�����Ļ����ǲ�����е���ʱ������ 
                                    */  
                                   new CountDownTimer(60000, 1000) {  
                                       @Override  
                                       public void onTick(long millisUntilFinished) {  
                                           Message_btn.setBackgroundResource(R.drawable.button_shape02);  
                                           Message_btn.setText(millisUntilFinished / 1000 + "��");  
                                       }  
     
                                       @Override  
                                       public void onFinish() {  
                                           Message_btn.setClickable(true);  
                                           Message_btn.setBackgroundResource(R.drawable.register_normal);  
                                           Message_btn.setText("���·���");  
                                       }  
                                   }.start();  
                                   Log.e("MESSAGE:", "4");  
                               }  
                               else {  
                                   Toast.makeText(RegisterActivity.this, "��֤�뷢��ʧ�ܣ�������������", Toast.LENGTH_SHORT).show();  
                               }  
                           }  
                       });  
     
                   }  
                   break;  
               case R.id.register_btn:  
                   Log.e("MESSAGE:", "5");  
                   if (userPhone.length() == 0 || Phonecode.length() == 0 || userPhone.length() != 11) {  
                       Log.e("MESSAGE:", "6");  
                       Toast.makeText(this, "�ֻ��Ż���֤�����벻�Ϸ�", Toast.LENGTH_SHORT).show();  
                   }  
                   else {  
                       BmobSMS.verifySmsCode(this,userPhone, Phonecode, new VerifySMSCodeListener() {  
                           @Override  
                           public void done(BmobException e) {  
                               if (e == null) {  
                                   Log.e("MESSAGE:", "7");  
                                   Toast.makeText(RegisterActivity.this, "��¼�ɹ�", Toast.LENGTH_SHORT).show();  
                               }  
                               else {  
                                   Log.e("MESSAGE:", "8");  
                                   Toast.makeText(RegisterActivity.this, "��֤�����", Toast.LENGTH_SHORT).show();  
                               }  
                           }  
                       });  
                   }  
                   break; 
               case R.id.btn_back:
            	   finish();
            	   break;
           }  
       }  
     
       private void initView() {  
           et_phone = (EditText) findViewById(R.id.et_phone);  
           et_code = (EditText) findViewById(R.id.et_code);
           register_name=(EditText) findViewById(R.id.register_name);
           register_password=(EditText) findViewById(R.id.register_password);
           Message_btn = (Button) findViewById(R.id.Message_btn);  
           register_btn = (Button) findViewById(R.id.register_btn); 
           btn_back=(Button) findViewById(R.id.btn_back);
       }  
   
}
