/**
 * 
 */
package com.example.move.activity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.io.InputStream;
import java.text.SimpleDateFormat;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.LinePageIndicator;
import com.viewpagerindicator.PageIndicator;
import team.yjcollege.matchproject.R;
import com.example.move.activity.GuideFragment.GuidePageAdapter;
import com.example.move.common.AppException;
import com.example.move.common.Constants;
import com.example.move.common.HttpHelper;
import com.example.move.common.HttpHelper.Callback;
import com.example.move.entity.ItemList;
import com.example.move.entity.Users;
import team.yjcollege.matchproject.myapplication.MyApplication;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author YHX 2014-2-28下午4:03:26
 */
public class LookInfoActivity extends Activity {
	private MyApplication myApplication;
	private Button btn_back, btn_right;
	private TextView title, createTime, tx_title, type, location,
			describe, linkMan, link, tx_toPhone, tx_toMsg, tx_toLeaveMessage;
	private ItemList itemList;// 商品详细信息集合

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.trade_look_info);
		myApplication = (MyApplication) this.getApplicationContext();
		myApplication.addActivity(this);
		Intent intent = getIntent();
		itemList = (ItemList) intent.getSerializableExtra("shopinfo");
		btn_back = (Button) this.findViewById(R.id.button_back);
		btn_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LookInfoActivity.this,
						TradeMainActivity.class);
				LookInfoActivity.this.startActivity(intent);
				LookInfoActivity.this.finish();
			}
		});
		btn_right = (Button) this.findViewById(R.id.button_right);
		btn_right.setVisibility(View.GONE);		
		title = (TextView) this.findViewById(R.id.title_tv);
		title.setText("求购信息详情");
		tx_title = (TextView) this.findViewById(R.id.tx_title);
		createTime = (TextView) this.findViewById(R.id.tx_createTime);
		type = (TextView) this.findViewById(R.id.tx_itemType);
		location = (TextView) this.findViewById(R.id.tx_location);
		describe = (TextView) this.findViewById(R.id.tx_describe);
		link = (TextView) this.findViewById(R.id.t_link);
		linkMan = (TextView) this.findViewById(R.id.t_linkMan);
		tx_title.setText(itemList.getShopname());
		Date date = itemList.getPut_time();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		createTime.setText(df.format(date));
		type.setText(itemList.getCategory());
		location.setText(itemList.getSchool() + "/" + itemList.getCourt());
		describe.setText(itemList.getDescription());
		linkMan.setText(itemList.getUserName());
		link.setText(itemList.getUserPhone());
		// 拨打电话
		tx_toPhone = (TextView) this.findViewById(R.id.tx_toPhone);
		tx_toPhone.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				// 系统默认的action，用来打开默认的电话界面
				intent.setAction(Intent.ACTION_DIAL);
				// 需要拨打的号码
				intent.setData(Uri.parse("tel:" + link.getText().toString()));
				LookInfoActivity.this.startActivity(intent);
			}
		});
		// 发送短信
		tx_toMsg = (TextView) this.findViewById(R.id.tx_toMsg);
		tx_toMsg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				// 系统默认的action，用来打开默认的短信界面
				intent.setAction(Intent.ACTION_SENDTO);
				// 需要发短息的号码
				intent.setData(Uri.parse("smsto:" + link.getText().toString()));
				LookInfoActivity.this.startActivity(intent);
			}
		});
		// 发送留言
		tx_toLeaveMessage = (TextView) this.findViewById(R.id.tx_toLeaveMsg);
		tx_toLeaveMessage.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LookInfoActivity.this,
						LeaveMessageActivity.class);
				intent.putExtra("username", itemList.getUserName());
				LookInfoActivity.this.startActivity(intent);
			}
		});
	}
}
