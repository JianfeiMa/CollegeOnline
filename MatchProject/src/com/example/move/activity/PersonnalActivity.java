package com.example.move.activity;

import team.yjcollege.matchproject.R;
import team.yjcollege.matchproject.R.id;
import com.example.move.entity.Users;
import team.yjcollege.matchproject.myapplication.MyApplication;
import team.yjcollege.matchproject.ui.TitleActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author showmark
 * 个人中心activity
 */
public class PersonnalActivity extends TitleActivity implements OnClickListener {
	private TextView title, publish,message,collection,look;
	private Button btn_back, btn_right;
	private MyApplication myApplication;
	private Intent intent;
	private Button btnBack = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.trade_personal_center);
		setTitle("个人中心");
		btnBack = super.showBackwardView(R.string.button_backward, true);
		btnBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				PersonnalActivity.this.finish();
			}
		});
		myApplication = (MyApplication) this.getApplicationContext();
		myApplication.addActivity(this);
		Users users = (Users) myApplication.userMap.get("user");
		if (users != null) {
		} else {
			View dialogView = getLayoutInflater().inflate(R.layout.trade_my_dialog,
					null, false);
			TextView title = (TextView) dialogView.findViewById(R.id.title);
			title.setText("温馨提示");
			TextView message = (TextView) dialogView.findViewById(R.id.message);
			message.setVisibility(View.VISIBLE);
			message.setText("你还没有登录，请先登录");
			TextView gallery = (TextView) dialogView.findViewById(R.id.gallery);
			gallery.setVisibility(View.GONE);
			TextView camera = (TextView) dialogView.findViewById(R.id.camera);
			camera.setVisibility(View.GONE);
			final Dialog dialog = new Dialog(PersonnalActivity.this,
					R.style.myDialogTheme);
			dialog.setContentView(dialogView);
			dialog.setCancelable(false);
			dialog.show();
			Button cancel = (Button) dialogView
					.findViewById(R.id.button_cancel);
			cancel.setVisibility(View.VISIBLE);
			cancel.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					dialog.dismiss();
					PersonnalActivity.this.finish();
				}
			});
			Button ok = (Button) dialogView.findViewById(R.id.button_ok);
			ok.setVisibility(View.VISIBLE);
			ok.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					dialog.dismiss();
					Intent intent = new Intent(PersonnalActivity.this,
							LoginActivity.class);
					PersonnalActivity.this.startActivity(intent);
				}
			});
		}
//		title = (TextView) this.findViewById(R.id.title_tv);
//		title.setText("个人中心");
		//我的发布
		publish = (TextView) this.findViewById(R.id.publish);
		publish.setOnClickListener(this);
		//返回按钮
//		btn_back = (Button) this.findViewById(R.id.button_back);
//		btn_back.setOnClickListener(this);
		// 注册按钮
//		btn_right = (Button) this.findViewById(R.id.button_right);
//		btn_right.setOnClickListener(this);
		//我的留言
		message = (TextView) this.findViewById(R.id.message);
		message.setOnClickListener(this);
		collection = (TextView) this.findViewById(R.id.collection);
		collection.setOnClickListener(this);
		look = (TextView) this.findViewById(R.id.look);
		look.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case id.publish:
			intent = new Intent(PersonnalActivity.this,
					MyReleaseActivity.class);
			startActivity(intent);
			break;
		case id.button_back:
			//PersonnalActivity.this.finish();
			break;
		case id.button_right:
			if (!myApplication.userMap.isEmpty()) {
				View view = getLayoutInflater().inflate(R.layout.trade_my_dialog,
						null, false);
				TextView title = (TextView) view.findViewById(R.id.title);
				title.setText("温馨提示");
				TextView message = (TextView) view
						.findViewById(R.id.message);
				message.setVisibility(View.VISIBLE);
				message.setText("你确定要注销吗？");
				TextView gallery = (TextView) view
						.findViewById(R.id.gallery);
				gallery.setVisibility(View.GONE);
				TextView camera = (TextView) view.findViewById(R.id.camera);
				camera.setVisibility(View.GONE);
				final Dialog dialog = new Dialog(PersonnalActivity.this,
						R.style.myDialogTheme);
				dialog.setContentView(view);
				dialog.show();
				Button cancel = (Button) view
						.findViewById(R.id.button_cancel);
				cancel.setVisibility(View.VISIBLE);
				cancel.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						dialog.dismiss();
					}
				});
				Button ok = (Button) view.findViewById(R.id.button_ok);
				ok.setVisibility(View.VISIBLE);
				ok.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						dialog.dismiss();
						myApplication.userMap.clear();
						Intent intent = new Intent(PersonnalActivity.this,
								MainActivity.class);
						startActivity(intent);
						PersonnalActivity.this.finish();
					}
				});
			}
			break;
		case id.message:
			intent = new Intent(PersonnalActivity.this,
					MyMsgActivity.class);
			startActivity(intent); 
			break;
		case id.collection:
			intent = new Intent(PersonnalActivity.this,
					MyCollectionActivity.class);
			startActivity(intent); 
			break;
		case id.look:
			intent = new Intent(PersonnalActivity.this,
					MyLookActivity.class);
			startActivity(intent); 
			break;
		default:
			break;
		}
	}

}
