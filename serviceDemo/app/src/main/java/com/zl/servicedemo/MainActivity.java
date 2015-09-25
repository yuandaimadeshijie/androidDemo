package com.zl.servicedemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.zl.servicedemo.service.MyIntentService;
import com.zl.servicedemo.service.MyService;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
	private MyService.DownloadBinder mDownloadBinder;
	@Bind(R.id.startService)Button mStratService;
	@Bind(R.id.stopService)Button mStopService;
	@Bind(R.id.bind_service)Button mBindService;
	@Bind(R.id.unbind_service)Button mUnbindService;
	@Bind(R.id.intent_service)Button mIntentService;
	@OnClick(R.id.intent_service)void intentService(){
		Intent intent=new Intent(this, MyIntentService.class);
		startService(intent);
	}
	@OnClick(R.id.bind_service)void bindService(){
		Intent intent=new Intent(this,MyService.class);
		/*BIND_AUTO_CREATE 活动和服务进行绑定后自动创建服务
		 使得 MyService 中的 onCreate()方法得到执行，但 onStartCommand()方法不
		 会执行。*/
		bindService(intent,mConnection,BIND_AUTO_CREATE);
	}
	@OnClick(R.id.unbind_service)void unBindService(){
		unbindService(mConnection);
	}
	@OnClick(R.id.stopService)void stopService(){
		Intent intent=new Intent(this,MyService.class);
		stopService(intent);
	}
	@OnClick(R.id.startService)void startService(){
		Intent intent=new Intent(this, MyService.class);
		startService(intent);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);
	}
	private ServiceConnection mConnection=new ServiceConnection() {
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			//向下转型
			mDownloadBinder= (MyService.DownloadBinder) service;
			mDownloadBinder.startDownload();
			mDownloadBinder.getProgress();
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {

		}
	};


}
