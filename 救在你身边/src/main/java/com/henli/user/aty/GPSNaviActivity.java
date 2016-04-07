package com.henli.user.aty;





import java.util.ArrayList;
import java.util.List;

import com.amap.api.navi.AMapNavi;
import com.amap.api.navi.AMapNaviListener;
import com.amap.api.navi.AMapNaviView;
import com.amap.api.navi.AMapNaviViewListener;
import com.amap.api.navi.AMapNaviViewOptions;
import com.amap.api.navi.model.AMapLaneInfo;
import com.amap.api.navi.model.AMapNaviCross;
import com.amap.api.navi.model.AMapNaviInfo;
import com.amap.api.navi.model.AMapNaviLocation;
import com.amap.api.navi.model.AMapNaviPath;
import com.amap.api.navi.model.AMapTrafficStatus;
import com.amap.api.navi.model.NaviInfo;
import com.amap.api.navi.model.NaviLatLng;
import com.amap.api.navi.view.CustomTmcView;

import com.autonavi.tbt.TrafficFacilityInfo;
import com.example.save.R;
import com.henli.speak.TTSController;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;

public class GPSNaviActivity extends Activity implements AMapNaviListener, AMapNaviViewListener{
	
	AMapNaviView naviView;
    AMapNavi aMapNavi;
    TTSController ttsManager;
    NaviLatLng endLatlng ;
    NaviLatLng startLatlng ;
    private CustomTmcView myCustomTrafficBar;
    private AMapNaviPath naviPath;
    private NaviInfo lastNaviInfo;
    private int remainingDistance;
    
    List<NaviLatLng> startList = new ArrayList<NaviLatLng>();
    List<NaviLatLng> endList = new ArrayList<NaviLatLng>();
    List<NaviLatLng> wayPointList;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_navi);
        naviView = (AMapNaviView) findViewById(R.id.navi_view);
        
        result();//传入经纬值
        
        //设置布局完全不可见
        AMapNaviViewOptions viewOptions = naviView.getViewOptions();
        viewOptions.setLayoutVisible(false);

        //主动隐藏蚯蚓线
        viewOptions.setTrafficBarEnabled(false);


        //可以自由设置自车位置，第一个参数为在宽的百分之多少处
        //第二个参数为在高的百分之多少处
        viewOptions.setPointToCenter(1.0 / 2, 1.0 / 2);
       
        naviView.onCreate(savedInstanceState);
        naviView.setAMapNaviViewListener(this);
        
        myCustomTrafficBar = (CustomTmcView) findViewById(R.id.myTrafficBar);
        //设置原始高度的百分比，其中若值<0.1 则用0.1 若值>1 则用1
        //设置横屏时高度为原始图片的0.5
        myCustomTrafficBar.setTmcBarHeightWhenLandscape(0.5);
        //设置竖屏时高度和原始图片一致
        myCustomTrafficBar.setTmcBarHeightWhenPortrait(1);
        
        //为了尽最大可能避免内存泄露问题，建议这么写
        ttsManager = TTSController.getInstance(getApplicationContext());
        ttsManager.init();
        ttsManager.startSpeaking();

        //为了尽最大可能避免内存泄露问题，建议这么写
        aMapNavi = AMapNavi.getInstance(getApplicationContext());
        aMapNavi.setAMapNaviListener(this);
        aMapNavi.setAMapNaviListener(ttsManager);
        aMapNavi.setEmulatorNaviSpeed(150);
    }
    
	@Override
    public void onNaviInfoUpdate(NaviInfo naviinfo) {
         lastNaviInfo = naviinfo;

        //每次NaviInfo更新的时候 准确的获取接下来的路长，以及接下来的路况
        remainingDistance = lastNaviInfo.getPathRetainDistance();
    }

    @Override
    public void onCalculateRouteSuccess() {
      
        if (aMapNavi != null) {
            naviPath = aMapNavi.getNaviPath();
        }

        int end = naviPath.getAllLength();
        int start = 0;
        //第一次算路成功进行整个路况的绘制
        //首先获取整个路况信息
        List<AMapTrafficStatus> totalRoadCondition = aMapNavi.getTrafficStatuses(start, end);

        //将路况和路长两项传入进行绘制
        myCustomTrafficBar.update(totalRoadCondition, end);
        myCustomTrafficBar.invalidate();
    }

    @Override
    public void onTrafficStatusUpdate() {
        //路况变化的时候重新绘制余下路况的蚯蚓线

        int end = naviPath.getAllLength();
        int start = end - remainingDistance;

        List<AMapTrafficStatus> remainingRoadCondition = aMapNavi.getTrafficStatuses(start, end);
        myCustomTrafficBar.update(remainingRoadCondition, naviPath.getAllLength());
        myCustomTrafficBar.invalidate();
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);


        boolean isLandscape = isOrientationLandscape();
        myCustomTrafficBar.onConfigurationChanged(isLandscape);
        myCustomTrafficBar.invalidate();
    }

    private boolean isOrientationLandscape() {
        return this.getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE || (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE);
    }


	
	private void result() {
		Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();
        double lat=bundle.getDouble("lat");
        double log=bundle.getDouble("log");
        double endLat=bundle.getDouble("endLat");
        double endLog=bundle.getDouble("endLog");
        startLatlng=new NaviLatLng(lat, log);
        endLatlng=new NaviLatLng(endLat, endLog);
	}
	
	 @Override
	    protected void onResume() {
	        super.onResume();
	        naviView.onResume();
	        startList.add(startLatlng);
	        endList.add(endLatlng);
	    }

	    @Override
	    protected void onPause() {
	        super.onPause();
	        naviView.onPause();

//	        仅仅是停止你当前在说的这句话，一会到新的路口还是会再说的
	        ttsManager.stopSpeaking();
	//
//	        停止导航之后，会触及底层stop，然后就不会再有回调了，但是讯飞当前还是没有说完的半句话还是会说完
//	        aMapNavi.stopNavi();
	    }

	    @Override
	    protected void onDestroy() {
	        super.onDestroy();
	        naviView.onDestroy();
	        aMapNavi.destroy();
	        ttsManager.destroy();
	    }
	    
	    /*@Override
	    public void onCalculateRouteSuccess() {
	        aMapNavi.startNavi(AMapNavi.GPSNaviMode);
	    }*/
	    
	    @Override
	    public void onInitNaviFailure() {
	    }

	    @Override
	    public void onInitNaviSuccess() {
	        aMapNavi.calculateDriveRoute(startList, endList, wayPointList, 5);
	    }

	    @Override
	    public void onStartNavi(int type) {

	    }

	    

	    @Override
	    public void onLocationChange(AMapNaviLocation location) {

	    }

	    @Override
	    public void onGetNavigationText(int type, String text) {

	    }

	    @Override
	    public void onEndEmulatorNavi() {
	    }

	    @Override
	    public void onArriveDestination() {
	    }

	   

	    @Override
	    public void onCalculateRouteFailure(int errorInfo) {
	    }

	    @Override
	    public void onReCalculateRouteForYaw() {

	    }

	    @Override
	    public void onReCalculateRouteForTrafficJam() {

	    }

	    @Override
	    public void onArrivedWayPoint(int wayID) {

	    }

	    @Override
	    public void onGpsOpenStatus(boolean enabled) {
	    }

	    @Override
	    public void onNaviSetting() {
	    }

	    @Override
	    public void onNaviMapMode(int isLock) {

	    }

	    


	    @Override
	    public void onNaviTurnClick() {

	    }

	    @Override
	    public void onNextRoadClick() {

	    }


	    @Override
	    public void onScanViewButtonClick() {
	    }

	  

	  

	    @Override
	    public void OnUpdateTrafficFacility(TrafficFacilityInfo trafficFacilityInfo) {

	    }

	    @Override
	    public void showCross(AMapNaviCross aMapNaviCross) {
	    }

	    @Override
	    public void hideCross() {
	    }

	    @Override
	    public void showLaneInfo(AMapLaneInfo[] laneInfos, byte[] laneBackgroundInfo, byte[] laneRecommendedInfo) {

	    }

	    @Override
	    public void hideLaneInfo() {

	    }


	    @Override
	    public void onLockMap(boolean isLock) {
	    }

	    @Override
	    public boolean onNaviBackClick() {
	        return false;
	    }

		@Override
		public void onNaviInfoUpdated(AMapNaviInfo arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onNaviCancel() {
			// TODO Auto-generated method stub
			
		}

}
