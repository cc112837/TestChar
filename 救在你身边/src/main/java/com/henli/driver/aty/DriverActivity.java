package com.henli.driver.aty;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.AMap.OnInfoWindowClickListener;
import com.amap.api.maps2d.AMap.OnMarkerClickListener;
import com.amap.api.maps2d.LocationSource.OnLocationChangedListener;
import com.amap.api.maps2d.model.BitmapDescriptor;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.nearby.NearbyInfo;
import com.amap.api.services.nearby.NearbySearch;
import com.amap.api.services.nearby.NearbySearch.NearbyListener;
import com.amap.api.services.nearby.NearbySearch.NearbyQuery;
import com.amap.api.services.nearby.NearbySearchFunctionType;
import com.amap.api.services.nearby.NearbySearchResult;
import com.amap.api.services.nearby.UploadInfo;
import com.amap.api.services.nearby.UploadInfoCallback;
import com.example.save.R;
import com.henli.adapter.MysildeAdapter;
import com.henli.user.aty.PersonalActivity;
import com.henli.user.aty.SettingActivity;
import com.henli.util.CacheUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DownloadManager.Query;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DriverActivity extends Activity implements  
OnMarkerClickListener, OnInfoWindowClickListener,LocationSource,AMapLocationListener,OnItemClickListener,OnClickListener,NearbyListener{
	private DrawerLayout mDrawerLayout;
	private ListView lv_item;
	
	private AMap aMap;
	private MapView map_driver;
	private OnLocationChangedListener mListener;
	private AMapLocationClient mlocationClient;
	private AMapLocationClientOption mLocationOption;
	private MarkerOptions markerOptions=new MarkerOptions();
	
	private double a;
	private double b;
	private LatLonPoint lp;
	private String address;
	private NearbyQuery query;
	ArrayList<BitmapDescriptor> giflist;
	private String detail;
    private NearbySearch mNearbySearch;
	UploadInfo loadInfo = new UploadInfo();
	private double lat;
	private double log;
	private ImageView iv_driver;
	private AlertDialog albumDialog;
	protected String dateTime;
	
	private String iconUrl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_driver);
		
		map_driver = (MapView) findViewById(R.id.map_driver);
   	    map_driver.onCreate(savedInstanceState);// 此方法必须重写
   	     initView();
   	     init();
   	     UpLoad();
         Query();
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		lv_item = (ListView) findViewById(R.id.lv_item);
		lv_item.setOnItemClickListener(this);
		MysildeAdapter adapter = new MysildeAdapter(DriverActivity.this);
		lv_item.setAdapter(adapter);
		Button button = (Button) findViewById(R.id.btn);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 按钮按下，将抽屉打开
				mDrawerLayout.openDrawer(Gravity.LEFT);

			}
		});
	}

	private void initView() {
		iv_driver=(ImageView) findViewById(R.id.iv_driver);
		iv_driver.setOnClickListener(this);
	}
    

	@Override
	public void onClick(View v) {
		if (v==iv_driver) {
			showAlbumDialog();
		}
		
	}
    
	public void showAlbumDialog() {
		   
	    albumDialog = new AlertDialog.Builder(this).create();
		albumDialog.setCanceledOnTouchOutside(true);
		View v = LayoutInflater.from(DriverActivity.this).inflate(R.layout.dialog_usericon, null);
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
					iconUrl =saveToSdCard(bitmap);
					iv_driver.setImageBitmap(bitmap);
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
	
	private void UpLoad() {
		mNearbySearch = NearbySearch.getInstance(getApplicationContext());
        mNearbySearch.addNearbyListener(this);
        mNearbySearch.startUploadNearbyInfoAuto(new UploadInfoCallback() { 
                // 设置自动上传数据和上传的间隔时间
                @Override
                public UploadInfo OnUploadInfoCallback() {
                        loadInfo.setCoordType(NearbySearch.AMAP);
                        // 位置信息
                        System.out.println("--->>>>>>UpLoad"+a+b+"**************");
                        loadInfo.setPoint(new LatLonPoint(a,b));
                        // 用户id信息
                        loadInfo.setUserID("haha");
                        return loadInfo;
                }
        }, 10000);
        
	}
    
	 private void Query() {
		 System.out.println("--->>>>>>Query"+a+b+"**************");
	    query = new NearbyQuery();
		//设置搜索的中心点
		query.setCenterPoint(new LatLonPoint(a, b));
		//设置搜索的坐标体系
		query.setCoordType(NearbySearch.AMAP);
		//设置搜索半径
		query.setRadius(10000);
		//设置查询的时间
		query.setTimeRange(10000);
		//设置查询的方式驾车还是距离
		query.setType(NearbySearchFunctionType.DISTANCE_SEARCH);
		//调用异步查询接口
		NearbySearch.getInstance(getApplicationContext()).searchNearbyInfoAsyn(query);
		 
		
	}
     
	 //周边检索的回调函数
	 public void onNearbyInfoSearched(NearbySearchResult nearbySearchResult,
	         int resultCode) {
	         //搜索周边附近用户回调处理
		
	     if(resultCode==0){
	    	
	     if (nearbySearchResult != null
	         && nearbySearchResult.getNearbyInfoList() != null
	         && nearbySearchResult.getNearbyInfoList().size() > 0) 
	       {
	    	
	         NearbyInfo nearbyInfo = nearbySearchResult.getNearbyInfoList().get(0);
	         detail= nearbyInfo.getUserID() + "离我" + nearbyInfo.getDistance()+ "米远";
	               
	            
	         } else {
	             detail="周围结果为空";
	            
	         }
	    
	         }
	     customPic();
	     System.out.println("--->>>>>>result"+detail+"    "+resultCode);
	     }
	 
	/**
  	 * 初始化AMap对象
  	 */
  	private void init() {
  		
  			if (aMap == null) {
  				aMap = map_driver.getMap();
  				setUpMap();
  			}
  		    
  		}

	private void setUpMap() {
		    
		    aMap.setOnMarkerClickListener(this);// 添加点击marker监听事件
			aMap.setLocationSource(this);// 设置定位监听
			aMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
			aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
			
				
		
	}
	

    /**
	 * 方法必须重写
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		map_driver.onSaveInstanceState(outState);
	}
  
    /**
	 * 方法必须重写
	 */
	@Override
	protected void onResume() {
		super.onResume();
		map_driver.onResume();
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onPause() {
		NearbySearch.destroy();
		super.onPause();
		map_driver.onPause();
		deactivate();
	}
     
	/**
	 * 方法必须重写
	 */
	@Override
	protected void onDestroy() {
		super.onDestroy();
		map_driver.onDestroy();
	}
	
	/**
	 * 停止定位
	 */
	@Override
	public void deactivate() {
		mListener = null;
		if (mlocationClient != null) {
			mlocationClient.stopLocation();
			mlocationClient.onDestroy();
		}
		mlocationClient = null;
	}

	/**
	 * 定位成功后回调函数    
	 */
	public void onLocationChanged(AMapLocation amapLocation) {
		if (mListener != null && amapLocation != null) {			
			if (amapLocation != null
					&& amapLocation.getErrorCode() == 0) {
				mListener.onLocationChanged(amapLocation);// 显示系统小蓝点
				lat=amapLocation.getLatitude();
				log=amapLocation.getLongitude();
				 a=lat;
				 b=log;
				
			    address=amapLocation.getAddress();
			    
			   // UpLoad();
  		        Query();
			    
				
			} else {
				String errText = "定位失败";
				Log.e("AmapErr",errText);
			}
		}
		
	}

	//定义自动定位动画
		private void customPic() {
			
			        // 动画效果
				    giflist = new ArrayList<BitmapDescriptor>();
					giflist.add(BitmapDescriptorFactory
							.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
					giflist.add(BitmapDescriptorFactory
							.defaultMarker(BitmapDescriptorFactory.HUE_RED));
					giflist.add(BitmapDescriptorFactory
							.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
					 aMap.addMarker(new MarkerOptions().anchor(0.5f, 0.5f)
								.position(new LatLng(a,b)).title(detail).icons(giflist)
				 				.draggable(true).period(5));
					
		}		
	
	/**
	 * 激活定位
	 */
	public void activate(OnLocationChangedListener listener) {
		mListener = listener;
		if (mlocationClient == null) {
			mlocationClient = new AMapLocationClient(this);
			mLocationOption = new AMapLocationClientOption();
			//设置定位监听
			mlocationClient.setLocationListener(this);
			//设置为高精度定位模式
			mLocationOption.setLocationMode(AMapLocationMode.Hight_Accuracy);
			//设置定位参数
			mlocationClient.setLocationOption(mLocationOption);
			mLocationOption.setNeedAddress(true);
			mLocationOption.setOnceLocation(true);
			// 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
			// 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
			// 在定位结束后，在合适的生命周期调用onDestroy()方法
			// 在单次定位情况下，定位无论成功与否，都无需调用stopLocatit
			mlocationClient.startLocation();
		}
	}

	@Override
	public void onInfoWindowClick(Marker arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onMapClick(LatLng arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onMarkerClick(Marker arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		
		switch (position) {
		case 0:
			
			startActivity(new Intent(DriverActivity.this, DriverActivity.class));
			
			break;

		case 1:
			
			
			break;
			
        case 2:
			
			break;
			
        case 3:
			
			break;
			
        case 4:
			
			break;
		}
		
	}

	@Override
	public void onNearbyInfoUploaded(int arg0) {
		// TODO Auto-generated method stub
		System.out.println("--<<<"+arg0);
	}

	@Override
	public void onUserInfoCleared(int arg0) {
		// TODO Auto-generated method stub
		
	}

	
	
}
