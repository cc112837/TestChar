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
        
        result();//���뾭γֵ
        
        //���ò�����ȫ���ɼ�
        AMapNaviViewOptions viewOptions = naviView.getViewOptions();
        viewOptions.setLayoutVisible(false);

        //�������������
        viewOptions.setTrafficBarEnabled(false);


        //�������������Գ�λ�ã���һ������Ϊ�ڿ�İٷ�֮���ٴ�
        //�ڶ�������Ϊ�ڸߵİٷ�֮���ٴ�
        viewOptions.setPointToCenter(1.0 / 2, 1.0 / 2);
       
        naviView.onCreate(savedInstanceState);
        naviView.setAMapNaviViewListener(this);
        
        myCustomTrafficBar = (CustomTmcView) findViewById(R.id.myTrafficBar);
        //����ԭʼ�߶ȵİٷֱȣ�������ֵ<0.1 ����0.1 ��ֵ>1 ����1
        //���ú���ʱ�߶�ΪԭʼͼƬ��0.5
        myCustomTrafficBar.setTmcBarHeightWhenLandscape(0.5);
        //��������ʱ�߶Ⱥ�ԭʼͼƬһ��
        myCustomTrafficBar.setTmcBarHeightWhenPortrait(1);
        
        //Ϊ�˾������ܱ����ڴ�й¶���⣬������ôд
        ttsManager = TTSController.getInstance(getApplicationContext());
        ttsManager.init();
        ttsManager.startSpeaking();

        //Ϊ�˾������ܱ����ڴ�й¶���⣬������ôд
        aMapNavi = AMapNavi.getInstance(getApplicationContext());
        aMapNavi.setAMapNaviListener(this);
        aMapNavi.setAMapNaviListener(ttsManager);
        aMapNavi.setEmulatorNaviSpeed(150);
    }
    
	@Override
    public void onNaviInfoUpdate(NaviInfo naviinfo) {
         lastNaviInfo = naviinfo;

        //ÿ��NaviInfo���µ�ʱ�� ׼ȷ�Ļ�ȡ��������·�����Լ���������·��
        remainingDistance = lastNaviInfo.getPathRetainDistance();
    }

    @Override
    public void onCalculateRouteSuccess() {
      
        if (aMapNavi != null) {
            naviPath = aMapNavi.getNaviPath();
        }

        int end = naviPath.getAllLength();
        int start = 0;
        //��һ����·�ɹ���������·���Ļ���
        //���Ȼ�ȡ����·����Ϣ
        List<AMapTrafficStatus> totalRoadCondition = aMapNavi.getTrafficStatuses(start, end);

        //��·����·���������л���
        myCustomTrafficBar.update(totalRoadCondition, end);
        myCustomTrafficBar.invalidate();
    }

    @Override
    public void onTrafficStatusUpdate() {
        //·���仯��ʱ�����»�������·���������

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

//	        ������ֹͣ�㵱ǰ��˵����仰��һ�ᵽ�µ�·�ڻ��ǻ���˵��
	        ttsManager.stopSpeaking();
	//
//	        ֹͣ����֮�󣬻ᴥ���ײ�stop��Ȼ��Ͳ������лص��ˣ�����Ѷ�ɵ�ǰ����û��˵��İ�仰���ǻ�˵��
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
