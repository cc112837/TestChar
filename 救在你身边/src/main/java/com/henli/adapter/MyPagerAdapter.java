package com.henli.adapter;

import com.example.save.R;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MyPagerAdapter extends PagerAdapter {

	private Context context;
	int images[] = { R.drawable.photoselect6, R.drawable.photoselect10,
			R.drawable.photoselect7, };

	public MyPagerAdapter(Context context) {

		this.context = context;
	}

	@Override
	public int getCount() {
		return Integer.MAX_VALUE;
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {

		View view = LayoutInflater.from(context).inflate(R.layout.activity_new,
				container, false);
		ImageView iv_show = (ImageView) view.findViewById(R.id.iv_show);
		iv_show.setImageResource(images[position % images.length]);
		container.addView(view);
		return view;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}
}
