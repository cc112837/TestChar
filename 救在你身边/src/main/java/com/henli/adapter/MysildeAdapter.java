package com.henli.adapter;


import com.example.save.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MysildeAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private Context context;
	private int[] list = { R.drawable.icon_add, R.drawable.icon_creditcard,
			R.drawable.icon_documents, R.drawable.icon_message,
			R.drawable.icon_welfare };
	private String[] listtv = { "我的位置", "我的钱包", "司机招募", "更多", "分享" };

	public MysildeAdapter( Context context) {
		super();
		this.inflater = LayoutInflater.from(context);
		this.context = context;
	}

	@Override
	public int getCount() {
		if (list != null) {
			return list.length;
		}
		return 0;
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = null;
		final ViewHolder vh;
		if (convertView == null) {
			vh = new ViewHolder();
	     	view = inflater.inflate(R.layout.left_item, null);
			vh.iv_img = (ImageView) view.findViewById(R.id.iv_img);
			vh.tv_con = (TextView) view.findViewById(R.id.tv_con);
			view.setTag(vh);
		} else {
			view = convertView;
			vh = (ViewHolder) view.getTag();
		}
		vh.iv_img.setImageResource(list[position]);
		vh.tv_con.setText(listtv[position]);
		return view;
	}

	private final class ViewHolder {
		private ImageView iv_img;// 用户头像
		private TextView tv_con;//

	}

}
