package com.henli.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.save.R;
import com.henli.data.Medium.TngouEntity;
import com.henli.util.PicUtil;
import com.henli.util.ScreenL;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadCallBack;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;

import java.util.List;

/**

 */
public class MyRecylerAdapter extends
		RecyclerView.Adapter<MyRecylerAdapter.MyViewHolder> {
	private List<TngouEntity> tngou;
	private Context context;
	private MyRecyItemClickListener listener;

	public void setOnItemClickListener(MyRecyItemClickListener listener) {
		this.listener = listener;
	}

	public MyRecylerAdapter(Context context, List<TngouEntity> tngou) {
		this.tngou = tngou;
		this.context = context;
	}

	public List<TngouEntity> getTngou() {
		return tngou;
	}

	public void setTngou(List<TngouEntity> tngou) {
		this.tngou = tngou;
	}

	public void addTngou(List<TngouEntity> list) {
		tngou.addAll(list);
	}
 
	public void clear() {
		tngou.clear();
	}
	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context)
				.inflate(R.layout.recycle_item, parent, false));
		return holder;
	}

	@Override
	public void onBindViewHolder(final MyViewHolder holder, int position) {
		BitmapUtils bitmaputils = new BitmapUtils(context);
		int screenWidth = ScreenL.getScreenWidth(context);
		final int h = ScreenL.Dp2Px(context, 220);
		int ww = ScreenL.Px2Dp(context, screenWidth) + 20;
		final int w = ScreenL.Dp2Px(context, ww);
		bitmaputils.display(holder.iv_icon, tngou.get(position).getImg(),
				new BitmapLoadCallBack<ImageView>() {

					@Override
					public void onLoadCompleted(ImageView container,
							String uri, Bitmap bitmap,
							BitmapDisplayConfig config, BitmapLoadFrom from) {
						Bitmap bm = PicUtil.zoomBitmap(bitmap, w, h);
						holder.iv_icon.setImageBitmap(bm);

					}

					@Override
					public void onLoadFailed(ImageView container, String uri,
							Drawable drawable) {
						holder.iv_icon.setBackgroundColor(234);
					}
				});
		holder.tv_bao.setText(tngou.get(position).getName() + "");
	}

	@Override
	public int getItemCount() {
		if (tngou != null)
			return tngou.size();
		return 0;
	}

	public class MyViewHolder extends RecyclerView.ViewHolder implements
			View.OnClickListener {
		private ImageView iv_icon;
		private TextView tv_bao;

		public MyViewHolder(View itemView) {
			super(itemView);
			iv_icon = ((ImageView) itemView.findViewById(R.id.iv_type));
			tv_bao = (TextView) itemView.findViewById(R.id.tv_titleid);
			itemView.setOnClickListener(this);
		}

		@Override
		public void onClick(View v) {
			if (listener != null) {
				listener.onItemClick(v, getPosition());
			}
		}
	}

}