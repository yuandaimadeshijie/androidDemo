package com.zl.servicedemo.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by neuyuandaima on 2015/9/25.
 */
public class MyIntentService extends IntentService {
	/**
	 * Creates an IntentService.  Invoked by your subclass's constructor.
	 *
	 * @param name Used to name the worker thread, important only for debugging.
	 */
	public MyIntentService() {
		super("MyIntentService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		//处理逻辑，不用新建线程，因为IntentService已经做了
		Log.d("MyIntentService", "Thread id is " + Thread.currentThread().
				getId());
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d("MyIntentService", "onDestroy executed");
	}
}
