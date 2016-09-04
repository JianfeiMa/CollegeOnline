package team.yjcollege.matchproject.login;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.way.chat.common.bean.User;
import com.way.chat.common.tran.bean.TranObject;
import com.way.chat.common.tran.bean.TranObjectType;

import android.os.Bundle;
import android.util.Log;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import team.yjcollege.matchproject.R;
import team.yjcollege.matchproject.client.Client;
import team.yjcollege.matchproject.client.ClientOutputThread;
import team.yjcollege.matchproject.commonUtil.Constants;
import team.yjcollege.matchproject.customview.CircleImg;
import team.yjcollege.matchproject.customview.MyActivity;
import team.yjcollege.matchproject.myapplication.MyApplication;
import team.yjcollege.matchproject.server.GetMsgService;
import team.yjcollege.matchproject.ui.StartActivity;
import team.yjcollege.matchproject.util.DialogFactory;
import team.yjcollege.matchproject.util.Encode;
import team.yjcollege.matchproject.util.SharePreferenceUtil;

public class LoginActivity extends MyActivity implements OnClickListener {
	private String sessionID=null;
	private MyInputBox etUsername;
	private MyInputBox etPassword;
	private Button btnLogin;
	private TextView tvForgetPassword;
	private TextView tvRegedit;
	private CircleImg loginHeadPhoto=null;
	private Dialog mDialog=null;
	private MyApplication myApplication;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		MyApplication.getInstance().addActivity(this);
		myApplication=(MyApplication) this.getApplicationContext();
		etUsername=(MyInputBox) this.findViewById(R.id.login_username);
		etPassword=(MyInputBox) this.findViewById(R.id.login_passWord);
		btnLogin=(Button) this.findViewById(R.id.login_login);
		tvForgetPassword=(TextView) this.findViewById(R.id.login_forget);
		tvRegedit=(TextView) this.findViewById(R.id.login_regedit);
		ReadDate();
		tvRegedit.setOnClickListener(this);
		tvForgetPassword.setOnClickListener(this);
		btnLogin.setOnClickListener(this);
		loginHeadPhoto=(CircleImg) findViewById(R.id.activity_login_headPhoto);
		Bitmap b=BitmapFactory.decodeFile("/storage/sdcard0/MatchProject/20160630/temphead.jpg");
		if(b!=null){
			loginHeadPhoto.setImageBitmap(b);
		}
	}

	class LoginThread extends Thread{

		@Override
		public void run() {
			HttpPost httpPost=new HttpPost("");
			HttpParams httpParam=new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpParam, 5000);
			if(sessionID!=null){
				httpPost.setHeader("cookie", "SESSION="+sessionID);
			}
			httpPost.setParams(httpParam);
			
			JSONObject params=new JSONObject();
			try {
				params.put("uid", etUsername.getText().toString());
				params.put("pwd", etPassword.getText().toString());
				List<NameValuePair> p=new ArrayList<NameValuePair>();
				BasicNameValuePair n=new BasicNameValuePair("params", params.toString());
				p.add(n);
				UrlEncodedFormEntity urlendodedFormEntity=new UrlEncodedFormEntity(p);
				httpPost.setEntity(urlendodedFormEntity);
				HttpClient client=new DefaultHttpClient();
				HttpResponse response=client.execute(httpPost);
				String responseBody=EntityUtils.toString(response.getEntity());  //���ص���������
				JSONObject json=new JSONObject(responseBody);
				boolean isOk=json.getBoolean("isOk");
				if(isOk){
					MyApplication.getInstance().setCusid(json.getString("cusid"));
					//Intent intent=new Intent(LoginActivity.this,);
					//intent.putExtra("cusName", etUser.getText().toString());
					//startActivity(intent);
				}else{
					//Message msg=Message.obtain(handler, LoginHandler.SHOW_MESSAGE);
					//msg.obj=json.getString("errorinfo");
					//msg.sendToTarget();
				}
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	void RemenberMe(String username,String password)
	{
		SharedPreferences sp=getSharedPreferences("config", MODE_PRIVATE);
		SharedPreferences.Editor editor=sp.edit();
		editor.putString("userName",username);
		editor.putString("passWord", password);
		editor.commit();
	}
	void ReadDate()
	{
		String username;
		String password;
		SharedPreferences sp=getSharedPreferences("config", MODE_PRIVATE);
		username=sp.getString("userName", null);
		password=sp.getString("passWord", null);
		etUsername.setText(username);
		etPassword.setText(password);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login_login:
			if(etUsername.getText().length()>0&&etPassword.getText().length()>0)
				RemenberMe(etUsername.getText().toString(), etPassword.getText().toString());
//			startActivity(new Intent(LoginActivity.this, StartActivity.class));
//			this.finish();
			submit();
			break;
		case R.id.login_regedit:
			startActivity(new Intent(LoginActivity.this, RegistActivity.class));
			overridePendingTransition(R.anim.left, R.anim.right);
			break;
		case R.id.login_forget:
			startActivity(new Intent(LoginActivity.this, ForgetPasswordActivity.class));
			overridePendingTransition(R.anim.left, R.anim.right);
			break;
		}
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if(isNetworkAvailable()==true){
			Intent startservice=new Intent(this, GetMsgService.class);
			startService(startservice);
			Log.v("LoginActivity", "执行完startService()");
		}else{
			Toast.makeText(this, "无网络连接", 3).show();
		}
	}
	
	public boolean isNetworkAvailable(){
		Context context=getApplicationContext();
		ConnectivityManager cmr=(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo[] info=cmr.getAllNetworkInfo();
		if(info!=null){
			for(int i=0;i<info.length;i++){
				if(info[i].getState()==NetworkInfo.State.CONNECTED){
					return true;
				}
			}
		}
		return false;
	}
	
	private void showRequestDialog(){
		if(mDialog!=null){
			mDialog.dismiss();
			mDialog = null;
		}
		mDialog=DialogFactory.creatRequestDialog(this, "正在验证...");
		mDialog.show();
	}
	
	private void submit(){
		String accounts=etUsername.getText().toString();
		String password=etPassword.getText().toString();
		if(accounts.length()==0 || password.length()==0){
			Toast.makeText(this, "账号和密码不能为空!", 3).show();
		}else{
			showRequestDialog();
			if(myApplication.isClientStart()==true){
				Client client = myApplication.getClient();
				ClientOutputThread out = client.getClientOutputThread();
				TranObject<User> o = new TranObject<User>(TranObjectType.LOGIN);
				User u = new User();
				u.setId(Integer.parseInt(accounts));
				u.setPassword(Encode.getEncode("MD5", password));
				o.setObject(u);
				out.setMsg(o);
			}else{
				if(mDialog.isShowing()){
					mDialog.dismiss();
					mDialog=null;
				}
				DialogFactory.ToastDialog(LoginActivity.this, "登录界面","亲！服务器暂未开放哦");
			}
		}
	}
	@Override
	public void getMessage(TranObject msg) {
		// TODO Auto-generated method stub
		if(msg!=null){
			switch (msg.getType()) {
			case LOGIN:
				List<User> list=(List<User>) msg.getObject();
				if(list.size()>0){
					SharePreferenceUtil util=new SharePreferenceUtil(LoginActivity.this, Constants.SAVE_USER);
					util.setId(etUsername.getText().toString());
					util.setPasswd(etPassword.getText().toString());
					util.setName(list.get(0).getName());
					util.setEmail(list.get(0).getEmail());
					Intent mainViewIntent=new Intent(this, StartActivity.class);
					mainViewIntent.putExtra(Constants.MSGKEY, msg);
					startActivity(mainViewIntent);
					if(mDialog.isShowing())
						mDialog.dismiss();
					this.finish();
					Toast.makeText(this, "登录成功", 3).show();
				}else{
					DialogFactory.ToastDialog(LoginActivity.this, "登录界面","亲！您的帐号或密码错误哦");
					if (mDialog.isShowing())
						mDialog.dismiss();
				}
				break;

			default:
				break;
			}
		}
	}
}
