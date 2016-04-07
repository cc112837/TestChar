package com.henli.adapter;




import com.example.save.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {

	private String mTitleArr[] = null;
	private int mImgIdArr[] = null;
	private LayoutInflater inflater = null;

	/**
	 * ���췽��
	 * 
	 * @param context
	 *            // �����Ķ���
	 * @param titleArr
	 *            // Title����
	 * @param imgId
	 *            // imageView��Դ����
	 */
	public MyAdapter(Context context, String[] titleArr, int[] imgId) {
		super();
		this.mTitleArr = titleArr;
		this.mImgIdArr = imgId;
		inflater = LayoutInflater.from(context);
	}

	/**
	 * ��ȡItem����
	 */
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mTitleArr.length;
	}

	/**
	 * ��ȡItem
	 */
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	/**
	 * ��ȡItemID
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

			convertView = inflater.inflate(R.layout.grid_item, null);
			holder = new ViewHolder();
			holder.titleName = (TextView) convertView
					.findViewById(R.id.gridview_text);
			holder.image = (ImageView) convertView
					.findViewById(R.id.gridview_img);

			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();

		}

		// ��������
		holder.titleName.setText(mTitleArr[position]);
		holder.image.setBackgroundResource(mImgIdArr[position]);

		return convertView;
	}

	/**
	 * ������
	 */
	private class ViewHolder {
		TextView titleName ;
		ImageView image ;
	}

}
