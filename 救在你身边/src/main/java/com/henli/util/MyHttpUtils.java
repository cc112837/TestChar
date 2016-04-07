package com.henli.util;

import android.os.Handler;

import com.henli.adapter.Inter;
import com.henli.data.DrugDetail;
import com.henli.data.Medium;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest;

/**

 */
public class MyHttpUtils extends HttpUtils implements Inter {
	private static MyHttpUtils httpUtils = new MyHttpUtils();


	public static void handData(Handler handler, int what, int id,String keyword) {
		RequestParams params = new RequestParams();
		params.addHeader("apikey", "b862f48bd4aa0ab1b033f083a4e5a6a8");
		 if (what == 6) {
			params.addBodyParameter("id", id + "");
			httpUtils.send(HttpRequest.HttpMethod.POST, Inter.detaildrug,
					params, new MyCallBack(new DrugDetail(), handler, what));

		} else{
			params.addBodyParameter("name", "drug");
			params.addBodyParameter("keyword", keyword);
			params.addBodyParameter("page", "1");
			params.addBodyParameter("rows", "30");
			params.addBodyParameter("type", "name");
			httpUtils.send(HttpRequest.HttpMethod.POST, Inter.totalurl, params,
					new MyCallBack(new Medium(), handler, what));
		}
	}

}
