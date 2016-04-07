package com.henli.user.aty;

import com.example.save.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends Activity implements OnClickListener{
       private Button btn_back;
       private TextView tv_register;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login);
		btn_back=(Button) findViewById(R.id.btn_back);
		btn_back.setOnClickListener(this);
		tv_register=(TextView) findViewById(R.id.tv_register);
		tv_register.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v==btn_back) {
			finish();
		}
		
		if (v==tv_register) {
			startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
		}
	}
}
