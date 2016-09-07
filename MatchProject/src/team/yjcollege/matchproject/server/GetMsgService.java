package team.yjcollege.matchproject.server;

import com.way.chat.common.bean.User;
import com.way.chat.common.tran.bean.TextMessage;
import com.way.chat.common.tran.bean.TranObject;
import com.way.chat.common.tran.bean.TranObjectType;

import android.R.integer;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;
import team.yjcollege.matchproject.myapplication.MyApplication;
import team.yjcollege.matchproject.commonUtil.Constants;
import team.yjcollege.matchproject.client.Client;
import team.yjcollege.matchproject.client.ClientInputThread;
import team.yjcollege.matchproject.client.ClientOutputThread;
import team.yjcollege.matchproject.client.MessageListener;
import team.yjcollege.matchproject.database.MessageDB;
import team.yjcollege.matchproject.util.MyDate;
import team.yjcollege.matchproject.util.SharePreferenceUtil;

/**
 * 收取消息服务
 * 
 * @author show-mark
 * 
 */
public class GetMsgService extends Service {
	private static final int MSG = 0x001;
	private MyApplication application;
	private Client client;
	private NotificationManager mNotificationManager;
	private boolean isStart = false;// 是否与服务器连接上
	private Notification mNotification;
	private Context mContext = this;
	private SharePreferenceUtil util;
	private MessageDB messageDB;
	// 收到用户按返回键发出的广播，就显示通知栏
	private BroadcastReceiver backKeyReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			Toast.makeText(context, "该应用进入后台运行", 0).show();
			
		}
	};
	

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {// 在onCreate方法里面注册广播接收者
		// TODO Auto-generated method stub
		super.onCreate();
		messageDB = new MessageDB(this);
		IntentFilter filter = new IntentFilter();
		filter.addAction(Constants.BACKKEY_ACTION);
		registerReceiver(backKeyReceiver, filter);
		mNotificationManager = (NotificationManager) getSystemService(android.content.Context.NOTIFICATION_SERVICE);
		application = (MyApplication) this.getApplicationContext();
		client = application.getClient();
	}

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		util = new SharePreferenceUtil(getApplicationContext(),
				Constants.SAVE_USER);
		Log.v("GetMsgService", "在服务里开启Client的start()函数");
		// 在服务里面启动client，服务kill到，client也跟着kill掉 
		isStart = client.start();
		Log.v("GetMsgService", "在服务里开启Client的start()函数返回的结果:"+isStart);
		application.setClientStart(isStart);
		System.out.println("client start:" + isStart);
		if (isStart==true) {
			//在服务里的onStart()方法里用client对象创建对象输入流
			//从而接收从服务器端发回来的消息
			ClientInputThread in = client.getClientInputThread();
			in.setMessageListener(new MessageListener() {

				@Override
				public void Message(TranObject msg) {
					// System.out.println("GetMsgService:" + msg);
					if (util.getIsStart()) {// 如果 是在后台运行，就更新通知栏，否则就发送广播给Activity
						if (msg.getType() == TranObjectType.MESSAGE) {// 只处理文本消息类型
							// System.out.println("收到新消息");
							// 把消息对象发送到handler去处理
							
						}
					} else {
						//否则就创建一个广播,其实这个广播就是一个意图
						Intent broadCast = new Intent();
						//为这个意图设置动作
						broadCast.setAction(Constants.ACTION);
						//为这个意图添加额外的携带的数据
						broadCast.putExtra(Constants.MSGKEY, msg);
						//将这个广播发送出去(意图)
						sendBroadcast(broadCast);// 把收到的消息以广播的形式发送出去
					}
				}
			});
		}
	}

	@Override
	// 在服务被摧毁时，做一些事情
	public void onDestroy() {
		super.onDestroy();
		if (messageDB != null)
			messageDB.close();
		unregisterReceiver(backKeyReceiver);
		mNotificationManager.cancel(Constants.NOTIFY_ID);
		// 给服务器发送下线消息
		if (isStart) {
			ClientOutputThread out = client.getClientOutputThread();
			TranObject<User> o = new TranObject<User>(TranObjectType.LOGOUT);
			User u = new User();
			u.setId(Integer.parseInt(util.getIp()));
			o.setObject(u);
			out.setMsg(o);
			// 发送完之后，关闭client
			out.setStart(false);
			client.getClientInputThread().setStart(false);
		}
		// Intent intent = new Intent(this, GetMsgService.class);
		// startService(intent);
	}

}
