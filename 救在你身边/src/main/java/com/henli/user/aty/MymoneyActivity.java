package com.henli.user.aty;


import com.example.save.R;


import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class MymoneyActivity extends Activity implements OnClickListener{
	private Button btn_return;
	private ImageView iv_userphoto;
	private TextView tv_username;
	
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	        setContentView(R.layout.activity_mymoney);
	        init();
	       

	    }

		private void init() {
			btn_return=(Button)findViewById(R.id.btn_return);
			iv_userphoto=(ImageView)findViewById(R.id.iv_userphoto);
			tv_username=(TextView)findViewById(R.id.tv_username);
			btn_return.setOnClickListener(this);
		}

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_return:
				MymoneyActivity.this.finish();
				break;

			default:
				break;
			}
			
		}

	  
		
}
