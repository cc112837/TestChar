package com.henli.user.aty;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.example.save.R;
import com.henli.util.ExitManager;

public class SettingActivity extends Activity {
	private ImageView img_back;
	private Button btn_logout;
           
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		initView();
	}

	private void initView() {
		img_back=(ImageView) findViewById(R.id.img_back);
		btn_logout=(Button) findViewById(R.id.btn_logout);
		btn_logout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ExitManager.getInstance().exit();
			}
		});
		img_back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
}
