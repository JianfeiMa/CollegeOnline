package com.example.move.activity;

import java.util.ArrayList;
import java.util.HashMap;

import team.yjcollege.matchproject.R;
import team.yjcollege.matchproject.myapplication.MyApplication;
import team.yjcollege.matchproject.ui.TitleActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class MainActivity extends TitleActivity {
	private int[] images = { R.drawable.trade_gv_1, R.drawable.trade_gv_2, R.drawable.trade_gv_3,
			R.drawable.trade_gv_4, R.drawable.trade_gv_5, R.drawable.trade_gv_6, R.drawable.trade_gv_7,
			R.drawable.trade_gv_8, };// gridview的图片
	private String[] titles = { "二手体育用品","二手生活用品", "二手自行车", "二手电子产品", "二手图书",
			"二手办公用品","二手电脑配件",  "个人中心", };// gridview的标题
	private GridView gridView;
	private Button title_back, title_right;
	private MyApplication myApplication;
	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.trade_activity_main);
		setTitle(R.string.trade_main_activityname);
		showBackwardView(R.string.button_backward, true);
		myApplication = (MyApplication) this.getApplicationContext();
		myApplication.addActivity(this);
//		title_back = (Button) this.findViewById(R.id.button_back);
//		title_back.setVisibility(View.GONE);
//		title_right = (Button) this.findViewById(R.id.button_right);
//		title_right.setVisibility(View.GONE);
		gridView = (GridView) this.findViewById(R.id.sub_gv);
		gridView.setAdapter(getMenuAdapter(titles, images));
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				switch (arg2) {
				case 0:
					intent = new Intent(MainActivity.this,
							ShopListActivity.class);
					intent.putExtra("type", "体育用品");
					MainActivity.this.startActivity(intent);
					break;
				case 1:
					intent = new Intent(MainActivity.this,
							ShopListActivity.class);
					intent.putExtra("type", "生活用品");
					MainActivity.this.startActivity(intent);
					break;

				case 2:
					intent = new Intent(MainActivity.this,
							ShopListActivity.class);
					intent.putExtra("type", "自行车");
					MainActivity.this.startActivity(intent);
					break;

				case 3:
					intent = new Intent(MainActivity.this,
							ShopListActivity.class);
					intent.putExtra("type", "电子产品");
					MainActivity.this.startActivity(intent);
					break;

				case 4:
					intent = new Intent(MainActivity.this,
							ShopListActivity.class);
					intent.putExtra("type", "图书");
					MainActivity.this.startActivity(intent);
					break;

				case 5:
					intent = new Intent(MainActivity.this,
							ShopListActivity.class);
					intent.putExtra("type", "办公用品");
					MainActivity.this.startActivity(intent);
					break;

				case 6:
					intent = new Intent(MainActivity.this,
							ShopListActivity.class);
					intent.putExtra("type", "电脑配件");
					MainActivity.this.startActivity(intent);
					break;

				case 7:
					intent = new Intent(MainActivity.this,
							PersonnalActivity.class);
					startActivity(intent);
					break;
				case 8:

					break;
				default:
					break;
				}
			}
		});
	}

	/**
	 * @param titles
	 *            gridview的文字数组
	 * @param imagesArray
	 *            gridview的图片数组
	 * @return
	 */
	private SimpleAdapter getMenuAdapter(String[] titles, int[] images) {
		ArrayList<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < titles.length; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("itemImage", images[i]);
			map.put("itemText", titles[i]);
			data.add(map);
		}
		SimpleAdapter simperAdapter = new SimpleAdapter(this, data,
				R.layout.trade_girdview_item,
				new String[] { "itemImage", "itemText" }, new int[] {
						R.id.mygv_iv, R.id.mygv_tv });
		return simperAdapter;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
