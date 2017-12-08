package com.mine.myapplication.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtil {

	/**
	 * 判断网络是否可用
	 *
	 * @return -1：网络不可用 0：移动网�? 1：wifi网络 2：未知网�?
	 */
	public static int isNetworkEnabled(Context context) {
		int status = -1;
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

		if (networkInfo != null && networkInfo.isConnected()) {
			switch (networkInfo.getType()) {
			case ConnectivityManager.TYPE_MOBILE: { // 移动网络
				status = 0;
				break;
			}
			case ConnectivityManager.TYPE_WIFI: { // wifi网络
				status = 1;
				break;
			}
			default: {
				status = 2;
			}
			}
		}
		return status;
	}
	/**
	 * @param context Context
	 * @return true 表示网络可用
	 */
	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null) {
			NetworkInfo info = connectivity.getActiveNetworkInfo();
			if (info != null && info.isConnected())
			{
				// 当前网络是连接的
				if (info.getState() == NetworkInfo.State.CONNECTED)
				{
					// 当前所连接的网络可用
					return true;
				}
			}
		}
		return false;
	}
}
