package com.henli.user.aty;

import com.example.save.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class FanKuiActivity extends Activity implements OnClickListener{
	    private TextView tv_left;
	    private Button btn_back;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
        	// TODO Auto-generated method stub
        	requestWindowFeature(Window.FEATURE_NO_TITLE);
        	super.onCreate(savedInstanceState);
        	setContentView(R.layout.fankui);
        	initview();
        }
		private void initview() {
			tv_left=(TextView) findViewById(R.id.tv_left);
			tv_left.setText("·´À¡");
			btn_back=(Button) findViewById(R.id.btn_back);
			btn_back.setOnClickListener(this);
			
			
		}
		@Override
		public void onClick(View v) {
			if (v==btn_back) {
				finish();
			}
			
		}
}
