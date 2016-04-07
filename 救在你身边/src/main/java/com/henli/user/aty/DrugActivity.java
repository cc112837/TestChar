package com.henli.user.aty;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;

import com.example.save.R;
import com.henli.adapter.Inter;
import com.henli.data.DrugDetail;
import com.henli.util.MyHttpUtils;
import com.lidroid.xutils.BitmapUtils;

import java.util.LinkedList;
import java.util.List;

@SuppressLint("HandlerLeak")
public class DrugActivity extends Activity {
	private WebView wv_show;
	private ImageView iv_head;
	private Button btn_can;
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 6:
				BitmapUtils bitmapUtils = new BitmapUtils(DrugActivity.this);
				DrugDetail drugDetail = (DrugDetail) msg.obj;
				bitmapUtils.display(iv_head, Inter.base + drugDetail.getImg());
				WebSettings webSettings = wv_show.getSettings();
				webSettings.setUseWideViewPort(true);
				webSettings.setLoadWithOverviewMode(true);
				webSettings.setBuiltInZoomControls(true);
				StringBuffer f = getCurrent(drugDetail.getMessage());
				wv_show.loadData(f.toString(),
						"text/html; charset=utf-8", "utf-8");
				break;

			default:
				break;
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_drug);
		Intent intent = getIntent();
		int id = intent.getIntExtra("id",1);
		MyHttpUtils.handData(handler, 6, id, null);
		wv_show = (WebView) findViewById(R.id.wv_show);
		iv_head = (ImageView) findViewById(R.id.iv_head);
		btn_can=(Button) findViewById(R.id.btn_can);
		btn_can.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				DrugActivity.this.finish();
			}
		});

	} 
	
    private StringBuffer getCurrent(String s) {
        StringBuffer f = new StringBuffer(s);
        char  a='[';
        int index=-1;
        int  count=0;
        List<Integer> b=new LinkedList<Integer>();
        for(int i=0;i<s.length();i++) {
            if ((s.indexOf(a, index + 1)) != -1) {
                index = s.indexOf(a, index + 1);
                b.add(index);
                count++;
            }
        }
        for (int i=count/2;i>0;i--){
            f.delete(b.get(2*i-2),b.get(2*i-1)+4);
        }
        return f;
    }
	
}
