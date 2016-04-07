package com.henli.user.aty;

import com.example.save.R;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends Activity implements OnClickListener{
    private TextView tv_detail;
    
    private Button btn_detail_return;
    private TextView tv_title;
	private int TAG;
	private Intent intent;
	private ImageView iv_video;
	private int point;
	private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_detail);
        initView();
        
        intent = getIntent();
        point = intent.getIntExtra("point", 0);
        String title = intent.getStringExtra("title");
        TAG = intent.getIntExtra("TAG", 0);
        tv_title.setText(title);//标题=item
        find();//判断传值 
        
        
       
       
        
    }
	private void find() {
		switch (TAG) {
		  case 1:
		  case 2:
		  case 3:
		  case 4:
		  case 5:
		  case 6:
		  case 7:
		  case 8:
			if (point==0) {
		    	String single=intent.getStringExtra("single");
		    	iv_video.setVisibility(View.VISIBLE);
		    	tv_detail.setText(single);
			}else if (point==1) {
				String text = intent.getStringExtra("text");
				tv_detail.setText(text);
			}
			break;

		
		}
	}
	private void video(String url) {
		Intent intent=new Intent();
		intent.setAction(intent.ACTION_VIEW);
		intent.setDataAndType(Uri.parse(url), "video/*");
		startActivity(intent);
	}
	private void initView() {
		btn_detail_return=(Button) findViewById(R.id.btn_detail_return);
		btn_detail_return.setOnClickListener(this);
        tv_detail=(TextView) findViewById(R.id.tv_detail);
        tv_title=(TextView) findViewById(R.id.tv_title);
        iv_video=(ImageView) findViewById(R.id.iv_video);
        iv_video.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_detail_return:
			finish();
			break;

		case R.id.iv_video:
			if (TAG==1&&point==0) {
				url="";
		    	video(url);//调取视频
			}
			if (TAG==2&&point==0) {
				url="";
		    	video(url);//调取视频
			}
			if (TAG==3&&point==0) {
				url="";
		    	video(url);//调取视频
			}
			if (TAG==4&&point==0) {
				url="";
		    	video(url);//调取视频
			}
			if (TAG==5&&point==0) {
				url="";
		    	video(url);//调取视频
			}
			if (TAG==6&&point==0) {
				url="child.mp4";
		    	video(url);//调取视频
			}
			if (TAG==7&&point==0) {
				url="";
		    	video(url);//调取视频
			}
			if (TAG==8&&point==0) {
				url="";
		    	video(url);//调取视频
			}
			break;
		}
		
	}
}
