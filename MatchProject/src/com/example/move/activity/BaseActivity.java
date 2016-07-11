/**
 * 
 */
package com.example.move.activity;


import team.yjcollege.matchproject.myapplication.MyApplication;

import android.app.Activity;
import android.view.KeyEvent;
import android.widget.Toast;

/**
 * @author YHX
 *2014-2-27上午11:26:18
 */
public class BaseActivity extends Activity {
	private long exit_time;
	private MyApplication instance;
//	@Override
//	public boolean onKeyDown(int keyCode, KeyEvent event) {
//		if(keyCode == KeyEvent.KEYCODE_BACK){//是后退键
//			
//			long temp = System.currentTimeMillis();
//			if(temp - exit_time <= 3000){
//				instance  = (MyApplication) this.getApplicationContext();
//				instance.exit();
//				instance.onLowMemory();
//			}else{
//				exit_time = temp;
//				Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
//			}
//			return true;
//		}
//		
//		return super.onKeyDown(keyCode, event);
//	}

}
