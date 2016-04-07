package com.henli.adapter;

import com.example.save.R;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemAdapter extends BaseAdapter {


	private String mItemTitle[] = null;
	private int mItemimgId[] = null;
	private LayoutInflater inflater = null;

	/**
	 * 构造方法
	 * 
	 * @param context
	 *            // 上下文对象
	 * @param titleArr
	 *            // Title数组
	 * @param imgId
	 *            // imageView资源数组
	 */
	 public ItemAdapter(Context context, String[] titleArr, int[] imgId) {
		super();
		this.mItemTitle = titleArr;
		this.mItemimgId = imgId;
		inflater = LayoutInflater.from(context);
	}

/**
	 * 获取Item总数
	 */
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mItemTitle.length;
	}

	/**
	 * 获取Item
	 */
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	/**
	 * 获取ItemID
	 */
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if (convertView == null) {

			convertView =inflater.inflate(R.layout.outdoor_item, null);
			holder = new ViewHolder();
			holder.tv_title = (TextView) convertView
					.findViewById(R.id.tv_outdoor);
			holder.iv_pic = (ImageView) convertView
					.findViewById(R.id.iv_pic);
			holder.iv_next = (ImageView) convertView
					.findViewById(R.id.iv_next);

			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();

		}

		// 设置内容
		holder.tv_title.setText(mItemTitle[position]);
		holder.iv_pic.setBackgroundResource(mItemimgId[position]);
		holder.iv_next.setBackgroundResource(R.drawable.ic_arrow);
		return convertView;
	}
	/**
	 * 工具类
	 */
	private class ViewHolder {
		TextView tv_title ;
		ImageView iv_pic ;
		ImageView iv_next;
	}


}
