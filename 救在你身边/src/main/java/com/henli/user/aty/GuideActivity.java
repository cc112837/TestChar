package com.henli.user.aty;

import com.example.save.R;
import com.henli.driver.aty.DriverActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;


/**
 * 
 * »¶Ó­Ò³
 * 
 */

public class GuideActivity extends Activity implements OnClickListener{

    private Button btn_user;
    private Button btn_driver;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_guide);
		initView();
		  /*new Thread() {
	            @Override
	            public void run() {
	                try {
	                    Thread.sleep(1000);
	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                }
	                Intent intent=new Intent(GuideActivity.this,RadioButtonActivity.class);
	                startActivity(intent);
	                finish();
	                super.run();
	            }
	        }.start();*/
	}
	private void initView() {
		btn_user=(Button) findViewById(R.id.btn_user);
		btn_user.setOnClickListener(this);
		btn_driver=(Button) findViewById(R.id.btn_driver);
		btn_driver.setOnClickListener(this);
		
	}
	@Override
	public void onClick(View v) {
		if(v==btn_user) {
			startActivity(new Intent(GuideActivity.this, RadioButtonActivity.class));
		}
		
		if (v==btn_driver) {
			startActivity(new Intent(GuideActivity.this, DriverActivity.class));
		}
		
	}



}
