package com.henli.user.aty;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import com.example.save.R;
import com.henli.util.CacheUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;


public class PersonalActivity  extends Activity implements OnClickListener{
	private ImageView iv_login;
	private ImageView iv_photo;
	String dateTime;
	AlertDialog albumDialog;
	String iconUrl;
	private LinearLayout ll_setting;
	private LinearLayout ll_user_fankui;
	private LinearLayout ll_user_shoucang;
	private LinearLayout ll_user_bl;
	private LinearLayout ll_user_money;
	
  @Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	setContentView(R.layout.main_personal);
	initView();
}

private void initView() {
	iv_login=(ImageView) findViewById(R.id.iv_login);
	iv_login.setOnClickListener(this);
	iv_photo=(ImageView) findViewById(R.id.iv_photo);
	iv_photo.setOnClickListener(this);
	ll_setting=(LinearLayout) findViewById(R.id.ll_setting);
	ll_setting.setOnClickListener(this);
	ll_user_fankui=(LinearLayout) findViewById(R.id.ll_user_fankui);
	ll_user_fankui.setOnClickListener(this);
	ll_user_shoucang=(LinearLayout)findViewById(R.id.ll_user_shoucang);
	ll_user_shoucang.setOnClickListener(this);
	ll_user_bl=(LinearLayout)findViewById(R.id.ll_user_bl);
	ll_user_bl.setOnClickListener(this);
	ll_user_money=(LinearLayout)findViewById(R.id.ll_user_money);
	ll_user_money.setOnClickListener(this);
}

@Override
public void onClick(View v) {
	if (v==iv_login) {
		startActivity(new Intent(PersonalActivity.this, LoginActivity.class));
	}
	//拍照上传
	if (v==iv_photo) {
		showAlbumDialog();
	}
	if (v==ll_setting) {
		startActivity(new Intent(PersonalActivity.this, SettingActivity.class));
	}
	if (v==ll_user_fankui) {
		startActivity(new Intent(PersonalActivity.this, FanKuiActivity.class));
	}
	
	if (v==ll_user_bl) {
		startActivity(new Intent(PersonalActivity.this, BingliActivity.class));
	}
	if (v==ll_user_money) {
		startActivity(new Intent(PersonalActivity.this, MymoneyActivity.class));
	}
	if (v==ll_user_shoucang) {
		startActivity(new Intent(PersonalActivity.this, MycollectActivity.class));
	}
}

   public void showAlbumDialog() {
	   
	    albumDialog = new AlertDialog.Builder(this).create();
		albumDialog.setCanceledOnTouchOutside(true);
		View v = LayoutInflater.from(PersonalActivity.this).inflate(R.layout.dialog_usericon, null);
		albumDialog.show();
		albumDialog.setContentView(v);
		albumDialog.getWindow().setGravity(Gravity.CENTER);
		
		
		TextView albumPic = (TextView)v.findViewById(R.id.album_pic);
		TextView cameraPic = (TextView)v.findViewById(R.id.camera_pic);
		albumPic.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				albumDialog.dismiss();
				Date date1 = new Date(System.currentTimeMillis());
				dateTime = date1.getTime() + "";
				getAvataFromAlbum();
			}
		});
		cameraPic.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				albumDialog.dismiss();
				Date date = new Date(System.currentTimeMillis());
				dateTime = date.getTime() + "";
				getAvataFromCamera();
			}
		});
	
  }

   private void getAvataFromCamera(){
		File f = new File(CacheUtils.getCacheDirectory(this, true, "icon") + dateTime);
		if (f.exists()) {
			f.delete();
		}
		try {
			f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Uri uri = Uri.fromFile(f);
		Log.e("uri", uri + "");
		
		Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		camera.putExtra(MediaStore.EXTRA_OUTPUT, uri);
		startActivityForResult(camera, 1);
	}

private void getAvataFromAlbum(){
	Intent intent2 = new Intent(Intent.ACTION_GET_CONTENT);
	intent2.setType("image/*");
	startActivityForResult(intent2, 2);
}

   @Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	// TODO Auto-generated method stub
	super.onActivityResult(requestCode, resultCode, data);
	if(resultCode == Activity.RESULT_OK){
		switch (requestCode) {
		
		case 1:
			String files =CacheUtils.getCacheDirectory(this, true, "icon") + dateTime;
			File file = new File(files);
			if(file.exists()&&file.length() > 0){
				Uri uri = Uri.fromFile(file);
				startPhotoZoom(uri);
			}else{
				
			}
			break;
		case 2:
			if (data == null) {
				return;
			}
			startPhotoZoom(data.getData());
			break;
		case 3:
			if (data != null) {
				Bundle extras = data.getExtras();
				if (extras != null) {
					Bitmap bitmap = extras.getParcelable("data");
					// 锟斤拷锟斤拷图片
					iconUrl = saveToSdCard(bitmap);
					iv_photo.setImageBitmap(bitmap);
					//updateIcon(iconUrl);
				}
			}
			break;
		default:
			break;
		}
	}
}
   public String saveToSdCard(Bitmap bitmap){
		String files =CacheUtils.getCacheDirectory(this, true, "icon") + dateTime+"_12";
		File file=new File(files);
       try {
           FileOutputStream out=new FileOutputStream(file);
           if(bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)){
               out.flush();
               out.close();
           }
       } catch (FileNotFoundException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       } catch (IOException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }
      // LogUtils.i(TAG, file.getAbsolutePath());
       return file.getAbsolutePath();
	}   
   
   public void startPhotoZoom(Uri uri) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		// 锟斤拷锟斤拷锟斤拷锟絚rop=true锟斤拷锟斤拷锟斤拷锟节匡拷锟斤拷锟斤拷Intent锟斤拷锟斤拷锟斤拷锟斤拷示锟斤拷VIEW锟缴裁硷�?
		// aspectX aspectY 锟角匡拷叩谋锟斤拷锟�?
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		// outputX outputY 锟角裁硷拷图片锟斤拷锟�
		intent.putExtra("outputX", 120);
		intent.putExtra("outputY", 120); 
		intent.putExtra("crop", "true");
		intent.putExtra("scale", true);// 去锟斤拷锟节憋拷
		intent.putExtra("scaleUpIfNeeded", true);// 去锟斤拷锟节憋拷
		// intent.putExtra("noFaceDetection", true);//锟斤拷锟斤拷识锟斤拷
		intent.putExtra("return-data", true);
		startActivityForResult(intent, 3);

	}
}

