package com.henli.user.aty;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.save.R;

public class CollectNextActivity extends Activity implements OnClickListener{
	
    private Intent intent;
    private TextView tv_detail;
	private TextView tv_title;
	private Button btn_detail_return;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_detail);
        initView();
        intent=getIntent();
        String detail = intent.getStringExtra("text");
        String item = intent.getStringExtra("item");
        tv_title.setText(item);
        tv_detail.setText(detail);
}

	private void initView() {
		btn_detail_return=(Button) findViewById(R.id.btn_detail_return);
		btn_detail_return.setOnClickListener(this);
		tv_detail=(TextView) findViewById(R.id.tv_detail);
        tv_title=(TextView) findViewById(R.id.tv_title);
		
	}

	@Override
	public void onClick(View v) {
		if (v==btn_detail_return) {
			finish();
		}
		
	}

	
}