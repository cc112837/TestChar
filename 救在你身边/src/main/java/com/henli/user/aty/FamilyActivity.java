package com.henli.user.aty;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.example.save.R;
import com.henli.adapter.MyPagerAdapter;

import java.util.List;

/**
 * 鐎硅泛娑甸幀銉︽櫝閸栵拷
 * 
 * 
 */

public class FamilyActivity extends Activity implements OnClickListener {
	private ViewPager vp_showid;
	private List<ImageView> imagelist;
	private boolean isShowing = true;// 閸掋倖鏌囪ぐ鎾冲閸ュ墽澧栨潪顔款嚄閻ㄥ墘iewpager閺勵垰鎯佸锝呮躬閺勫墽銇�
	private RadioGroup radiogroup;
	private LinearLayout ll_home;
	private LinearLayout ll_outdoor;
	private LinearLayout ll_gan;
	private LinearLayout ll_wai;
	private LinearLayout ll_big;
	private LinearLayout ll_near;
	private LinearLayout ll_equip;
	private LinearLayout ll_child;
	private LinearLayout ll_necese;

	/**
     *
     */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_family);
		init();//
	}

	private void init() {
		ll_home = (LinearLayout) findViewById(R.id.ll_home);
		ll_gan = (LinearLayout) findViewById(R.id.ll_gan);
		ll_outdoor = (LinearLayout) findViewById(R.id.ll_outdoor);
		ll_big = (LinearLayout) findViewById(R.id.ll_big);
		ll_wai = (LinearLayout) findViewById(R.id.ll_wai);
		ll_near = (LinearLayout) findViewById(R.id.ll_near);
		ll_equip = (LinearLayout) findViewById(R.id.ll_equip);
		ll_child = (LinearLayout) findViewById(R.id.ll_child);
		ll_necese = (LinearLayout) findViewById(R.id.ll_necese);
		ll_home.setOnClickListener(this);
		ll_gan.setOnClickListener(this);
		ll_outdoor.setOnClickListener(this);
		ll_big.setOnClickListener(this);
		ll_wai.setOnClickListener(this);
		ll_near.setOnClickListener(this);
		ll_equip.setOnClickListener(this);
		ll_child.setOnClickListener(this);
		ll_necese.setOnClickListener(this);
		vp_showid = ((ViewPager) findViewById(R.id.vp_showid));
		
		radiogroup = (RadioGroup) findViewById(R.id.radiogroup);
		
		radiogroup.check(R.id.radione);
		
		radiogroup.setClickable(false);
		aboutViewPager();

	}

	private void aboutViewPager() {
		MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getApplicationContext());
		vp_showid.setAdapter(myPagerAdapter);
		vp_showid.setCurrentItem(Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2
				%3);
		vp_showid.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				int checkedId = R.id.radione;
				switch (position % 3) {
				case 0:
					checkedId = R.id.radione;
					break;
				case 1:
					checkedId = R.id.raditwo;
					break;
				case 2:
					checkedId = R.id.radithree;
					break;
			
				}
				radiogroup.check(checkedId);

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {		
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				

			}
		});
		handler.sendEmptyMessageDelayed(6, 3000);
	}

	@Override
	protected void onStop() {
		super.onStop();
		isShowing = false;
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		isShowing = true;
		handler.sendEmptyMessageDelayed(6, 3000);
	}

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 6:
				if (isShowing) {
					vp_showid.setCurrentItem(vp_showid.getCurrentItem() + 1);
					sendEmptyMessageDelayed(6, 3000);
				}
				break;
			}
		}
	};

	@Override
	public void onClick(View v) {
		Intent intent=null;
		switch (v.getId()) {
		case R.id.ll_home:
			intent=new Intent(FamilyActivity.this,ChildDrugActivity.class);
			intent.putExtra("key", "胶囊");
			intent.putExtra("title","家庭用药");
			startActivity(intent);
			break;
		case R.id.ll_gan:
			intent=new Intent(FamilyActivity.this,ChildDrugActivity.class);
			intent.putExtra("key", "感冒");
			intent.putExtra("title","感冒用药");
			startActivity(intent);
			break;
		case R.id.ll_outdoor:
			intent=new Intent(FamilyActivity.this,ChildDrugActivity.class);
			intent.putExtra("key", "外用");
			intent.putExtra("title","户外用药");
			startActivity(intent);

			break;
		case R.id.ll_wai:
			intent=new Intent(FamilyActivity.this,ChildDrugActivity.class);
			intent.putExtra("key", "外用");
			intent.putExtra("title","外伤用药");
			startActivity(intent);
			break;
		case R.id.ll_necese:
			intent=new Intent(FamilyActivity.this,ChildDrugActivity.class);
			intent.putExtra("key", "胃");
			intent.putExtra("title","家庭必备");
			startActivity(intent);
			break;
		case R.id.ll_near:

			break;
		case R.id.ll_equip:

			break;
		case R.id.ll_big:
			intent=new Intent(FamilyActivity.this,ChildDrugActivity.class);
			intent.putExtra("key", "心");
			intent.putExtra("title","重疾用药");
			startActivity(intent);

			break;
		case R.id.ll_child:
			intent=new Intent(FamilyActivity.this,ChildDrugActivity.class);
			intent.putExtra("key", "儿童");
			intent.putExtra("title","儿童用药");
			startActivity(intent);
			break;

		default:
			break;
		}

	}
}
