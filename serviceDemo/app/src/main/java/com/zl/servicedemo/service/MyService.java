package com.zl.servicedemo.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.zl.servicedemo.MainActivity;

/**
 * Created by neuyuandaima on 2015/9/25.
 */
public class MyService extends Service {
	private DownloadBinder mDownloadBinder=new DownloadBinder();
	public class DownloadBinder extends Binder{
		public void startDownload(){
			Log.d("service","start");
		}
		public int getProgress(){
			Log.d("service","getProgress");
			return 0;
		}
	}



	@Override
	public IBinder onBind(Intent intent) {
		Log.d("service", "onBind");
		return mDownloadBinder;
	}

	@Override
	public void onCreate() {
		Log.d("service", "onCreate");
		super.onCreate();
		Intent intent=new Intent(this, MainActivity.class);
		PendingIntent pendingIntent=PendingIntent.getActivity(this, 0, intent, 0);
		Notification notification = new Notification.Builder(getBaseContext())
				.setContentTitle("Notification comes")
				.setContentText("hello")
				.setSmallIcon(android.R.drawable.sym_def_app_icon)
				.setContentIntent(pendingIntent)
				.build();
		NotificationManager notificationManager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		notificationManager.notify(1,notification);
	}

	//使用startaService时候调用
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d("service", "onStartCommand");
		stopSelf();
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d("service", "onDestroy");
	}
}
