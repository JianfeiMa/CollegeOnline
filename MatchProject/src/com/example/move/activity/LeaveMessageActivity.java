/**
 * 
 */
package com.example.move.activity;

import java.util.HashMap;

import org.apache.http.HttpStatus;

import team.yjcollege.matchproject.R;
import com.example.move.common.AppException;
import com.example.move.common.Constants;
import com.example.move.common.HttpHelper;
import com.example.move.common.HttpHelper.Callback;
import com.example.move.entity.Users;
import team.yjcollege.matchproject.myapplication.MyApplication;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author YHX 2014-3-4下午4:29:58
 */
public class LeaveMessageActivity extends Activity {
	private MyApplication myApplication;
	private Button btn_back, btn_right, leaveMssage;
	private TextView title, tv_message;
	private String username, receiveName;
	private EditText et_message;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.trade_activity_leave_message);
		myApplication = (MyApplication) this.getApplicationContext();
		myApplication.addActivity(this);
		Intent intent = getIntent();
		receiveName = intent.getStringExtra("username");
		Users users = (Users) myApplication.userMap.get("user");
		if (users != null) {
			username = users.getUserName();
		} else {
			View dialogView = getLayoutInflater().inflate(R.layout.trade_my_dialog,
					null, false);
			TextView title = (TextView) dialogView.findViewById(R.id.title);
			title.setText("温馨提示");
			TextView message = (TextView) dialogView.findViewById(R.id.message);
			message.setVisibility(View.VISIBLE);
			message.setText("你好没有登录，请先登录");
			TextView gallery = (TextView) dialogView.findViewById(R.id.gallery);
			gallery.setVisibility(View.GONE);
			TextView camera = (TextView) dialogView.findViewById(R.id.camera);
			camera.setVisibility(View.GONE);
			final Dialog dialog = new Dialog(LeaveMessageActivity.this,
					R.style.myDialogTheme);
			dialog.setContentView(dialogView);
			dialog.show();
			Button cancel = (Button) dialogView
					.findViewById(R.id.button_cancel);
			cancel.setVisibility(View.VISIBLE);
			cancel.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					dialog.dismiss();
					LeaveMessageActivity.this.finish();
				}
			});
			Button ok = (Button) dialogView.findViewById(R.id.button_ok);
			ok.setVisibility(View.VISIBLE);
			ok.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					dialog.dismiss();
					Intent intent = new Intent(LeaveMessageActivity.this,
							LoginActivity.class);
					LeaveMessageActivity.this.startActivityForResult(intent, 1);
				}
			});
		}
		title = (TextView) this.findViewById(R.id.title_tv);
		title.setText("留言");
		et_message = (EditText) this.findViewById(R.id.et_message);
		// 返回按钮监听
		btn_back = (Button) this.findViewById(R.id.button_back);
		btn_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LeaveMessageActivity.this,
						MainActivity.class);
				LeaveMessageActivity.this.startActivity(intent);
				LeaveMessageActivity.this.finish();
			}
		});
		btn_right = (Button) this.findViewById(R.id.button_right);
		btn_right.setVisibility(View.GONE);
		// 留言按钮
		leaveMssage = (Button) this.findViewById(R.id.leave);
		leaveMssage.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!et_message.getText().toString().equals("") && et_message.getText().toString() != null) {
					HashMap<String, Object> params = new HashMap<String, Object>();
					params.put("content", et_message.getText().toString());
					params.put("username", username);
					params.put("receivename", receiveName);
					HttpHelper.asyncPost(Constants.URL
							+ "/second-hand/msg_add.do", params,
							new Callback() {
								@Override
								public void dataLoaded(Message msg) {
									if (HttpStatus.SC_OK != msg.what) {
										AppException.http(msg.what).makeToast(
												LeaveMessageActivity.this);
										return;
									}
									Toast.makeText(LeaveMessageActivity.this,
											msg.obj.toString(),
											Toast.LENGTH_SHORT).show();
									LeaveMessageActivity.this.finish();
								}
							});
				}else {
					Toast.makeText(LeaveMessageActivity.this, "请填写内容", 0).show();
				}
			}
		});

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1 && resultCode == 2) {
			username = data.getStringExtra("username");
		}
	}
}
