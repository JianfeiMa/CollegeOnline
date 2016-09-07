package team.yjcollege.matchproject.login;

import java.io.IOException;
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
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import team.yjcollege.matchproject.R;
import team.yjcollege.matchproject.myapplication.MyApplication;
import team.yjcollege.matchproject.ui.TitleActivity;

public class RegistActivity extends TitleActivity {
	private TextView tvPhoneNumber=null;
	private TextView tvPassword=null;
	private TextView tvScondPassword=null;
    private TextView btnRegist=null;
    private int MSG_CREATE_RESULT=1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(R.string.register_name);
		showBackwardView(R.string.login_activity_name, true);
		setContentView(R.layout.activity_regist);
		MyApplication.getInstance().addActivity(this);
//		ActionBar ab=this.getActionBar();
//		ab.setDisplayHomeAsUpEnabled(true);
//		ab.setDisplayShowHomeEnabled(false);
//		ab.setTitle(" 返回");
//		ab.setBackgroundDrawable(getResources().getDrawable(R.drawable.fb_send_btn_unpressed));
		init();
	}

	private void init(){
		tvPhoneNumber=(TextView) this.findViewById(R.id.register_phone);
		tvPassword=(TextView) this.findViewById(R.id.register_password);
		tvScondPassword=(TextView) this.findViewById(R.id.register_scondpassword);
		btnRegist=(TextView) this.findViewById(R.id.register_btnRegist);
		btnRegist.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				judge();		
			}
		});
	}
	
	private void judge(){
		String phone=tvPhoneNumber.getText().toString();
		int isOk=isMobileNo(phone);
		if(isOk==1){
			Toast.makeText(RegistActivity.this, "亲!你还没有输入手机号码哦", 3).show();
		}else if(isOk==3){
			Toast.makeText(RegistActivity.this, "亲!请输入正确的手机号码", 3).show();
		}else if(isOk==2){
			int checkpa=checkPassword();
			if(checkpa==1){
				Toast.makeText(RegistActivity.this, "亲!密码不能为空噢", 3).show();
			}else if(checkpa==2){
				Toast.makeText(RegistActivity.this, "亲!两次密码输入不一致呢", 3).show();
			}else if(checkpa==3){
				
				Regist r=new Regist();
				r.start();
				//Toast.makeText(RegistActivity.this, "test", 3).show();
			}
		}

	}
	
	private int isMobileNo(String mobiles){
		String telRegex = "[1][358]\\d{9}";
		if (TextUtils.isEmpty(mobiles)) {
			return 1;
		}
		else if(mobiles.matches(telRegex)==true){
			return 2;
		}else{
			return 3;
		}
	}
	
	private int checkPassword(){
		String onePassword=tvPassword.getText().toString();
		String twoPassword=tvScondPassword.getText().toString();
		if(TextUtils.isEmpty(onePassword)==true){
			return 1;
		}else if(!onePassword.equals(twoPassword)){
			return 2;
		}else{
			return 3;
		}
	}
	
	Handler handler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			try {
				JSONObject j=(JSONObject) msg.obj;
				int result=j.getInt("result_code");
				if(result==0){
					Toast.makeText(RegistActivity.this, "注册成功", 3).show();
					//Intent intent=new Intent(packageContext, cls);
					//startActivity(intent);
				}else if(result==1){
					Toast.makeText(RegistActivity.this, "用户名已存在", 3).show();
				}else if(result==2){
					Toast.makeText(RegistActivity.this, "服务器异常", 3).show();
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		
	};
	
	private class Regist extends Thread{

		@Override
		public void run() {
			
			HttpPost post=new HttpPost("http://192.168.0.106:8080/androidWeb/servlet/NewAccount");
			BasicHttpParams params=new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(params, 5000);	
			try {
				//Toast.makeText(RegistActivity.this, "test", 3).show();
				JSONObject javascriptobjectnotation=new JSONObject();
				javascriptobjectnotation.put("uid", tvPhoneNumber.getText().toString());
				javascriptobjectnotation.put("pwd", tvPassword.getText().toString());
				BasicNameValuePair namevaluepair=new BasicNameValuePair("phone", javascriptobjectnotation.toString());
				List<NameValuePair> list=new ArrayList<NameValuePair>();
				list.add(namevaluepair);
				post.setEntity(new UrlEncodedFormEntity(list));
				HttpClient client=new DefaultHttpClient();
				HttpResponse response=client.execute(post);
				Log.v("a", "ppp");
				String responseBody=EntityUtils.toString(response.getEntity());
				JSONObject j=new JSONObject(responseBody);
				Message mes=Message.obtain(handler, MSG_CREATE_RESULT);
				mes.obj=j;
				mes.sendToTarget();
				
				
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
