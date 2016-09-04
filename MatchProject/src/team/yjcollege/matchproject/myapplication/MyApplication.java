package team.yjcollege.matchproject.myapplication;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import android.app.Activity;
import android.app.Application;
import android.graphics.Bitmap;
import android.util.Log;
import team.yjcollege.matchproject.client.Client;
import team.yjcollege.matchproject.commonUtil.Constants;
import team.yjcollege.matchproject.util.SharePreferenceUtil;

public class MyApplication extends Application {
	private Client client;
	private boolean isClientStart;
	public ConcurrentHashMap<String, Object> userMap;
	private List<Activity> mList = new LinkedList<Activity>();
	private List<Bitmap> listBitmap = new ArrayList<Bitmap>();
	private static MyApplication instance;
	private String cusid=null;

	public MyApplication() {
	}

	@Override
	public void onCreate() {
		super.onCreate();
		userMap = new ConcurrentHashMap<String, Object>();
		SharePreferenceUtil util=new SharePreferenceUtil(this, Constants.SAVE_USER);
		Log.v("MyApplication", util.getIp()+util.getPort());
		client=new Client(util.getIp(), util.getPort());
	}

	public synchronized static MyApplication getInstance() {
		if (null == instance) {
			instance = new MyApplication();
		}
		return instance;
	}

	// add Activity
	public void addActivity(Activity activity) {
		mList.add(activity);
	}

	public List<Bitmap> getListBitmap() {
		return listBitmap;
	}

	public void setListBitmap(List<Bitmap> listBitmap) {
		this.listBitmap = listBitmap;
	}

	public void destroy() {
		if (!listBitmap.isEmpty()) {
			listBitmap.clear();
		}
	}

	public void exit() {
		try {
			for (Activity activity : mList) {
				if (activity != null)
					activity.finish();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.exit(0);
		}
	}

	public void onLowMemory() {
		super.onLowMemory();
		System.gc();
	}

	public String getCusid() {
		return cusid;
	}

	public void setCusid(String cusid) {
		this.cusid = cusid;
	}
	
	public Client getClient(){
		return client;
	}
	
	public boolean isClientStart(){
		return isClientStart;
	}
	
	public void setClientStart(boolean isClientStart){
		this.isClientStart=isClientStart;
	}
}
