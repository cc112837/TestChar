package com.henli.user.aty;

import android.R.color;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.save.R;
import com.henli.adapter.MyRecyItemClickListener;
import com.henli.adapter.MyRecylerAdapter;
import com.henli.data.Medium;
import com.henli.util.MyHttpUtils;

import java.util.List;


public class ChildDrugActivity extends Activity implements
SwipeRefreshLayout.OnRefreshListener{
	private MyRecylerAdapter adapter;
	private RecyclerView rv_showid;
	private SwipeRefreshLayout sr_flush;
	private List<Medium.TngouEntity> list;
	private TextView tv_title;
	private Button btn_can;
	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 7:
				final Medium medium = (Medium) msg.obj;
				sr_flush.setRefreshing(false);
				adapter.setTngou(medium.getTngou());
				adapter.notifyDataSetChanged();
				adapter.setOnItemClickListener(new MyRecyItemClickListener() {
					@Override
					public void onItemClick(View view, int postion) {
						Intent intent=new Intent(ChildDrugActivity.this,DrugActivity.class);
						intent.putExtra("id", medium.getTngou().get(postion).getId());
					    startActivity(intent);						
					}
				});
				break;
			default:
				break;
			}
		}
	};
	private String key;
	private String title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_child_drug);
		Intent intent = getIntent();
		key = intent.getStringExtra("key");
		title = intent.getStringExtra("title");
		init();
	}

	private void init() {
		rv_showid = ((RecyclerView) findViewById(R.id.rv_showid));
		btn_can = ((Button) findViewById(R.id.btn_can));
		tv_title = ((TextView) findViewById(R.id.tv_title));
		tv_title.setText(title);
		btn_can.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ChildDrugActivity.this.finish();
			}
		});
		GridLayoutManager manager = new GridLayoutManager(this, 2);
		sr_flush = (SwipeRefreshLayout) findViewById(R.id.sr_flush);
		sr_flush.setColorSchemeResources(color.black, color.holo_green_light,
				color.holo_red_light, color.holo_blue_bright);
		sr_flush.setOnRefreshListener(this);
		rv_showid.setLayoutManager(manager);
		adapter = new MyRecylerAdapter(
				ChildDrugActivity.this,list);
		rv_showid.setAdapter(adapter);
		MyHttpUtils.handData(handler, 7, 1, key);
		rv_showid.setHasFixedSize(true);
		rv_showid.addOnScrollListener(new RecyclerView.OnScrollListener() {
			@Override
			public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
				int topRowVerticalPosition =
						(recyclerView == null || recyclerView.getChildCount() == 0) ? 0 : recyclerView.getChildAt(0).getTop();
				sr_flush.setEnabled(topRowVerticalPosition >= 0);
			}

			@Override
			public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
				super.onScrollStateChanged(recyclerView, newState);
			}
		});


	}

	@Override
	public void onRefresh() {		
			adapter.clear();
			adapter.notifyDataSetChanged();
			MyHttpUtils.handData(handler, 7, 1,key);
	}
}
