/**
 * 
 */
package com.example.move.activity;

import java.util.ArrayList;

import com.viewpagerindicator.LinePageIndicator;
import com.viewpagerindicator.PageIndicator;
import team.yjcollege.matchproject.R;
import team.yjcollege.matchproject.myapplication.MyApplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

/**
 * @author YHX
 *2014-3-3上午10:00:19
 */
public class PicturePreview extends Activity{
	private MyApplication myApplication;
	private ViewPager vp;
	private PageIndicator indicator;
	private ArrayList<View> pageViews;
    private View view1,view2,view3;// 图片的布局
	private ImageView image1,image2,image3;// 图片显示
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		myApplication = (MyApplication) this
				.getApplicationContext();
		myApplication.addActivity(this);
		setContentView(R.layout.trade_activity_guide);
		initData();
		vp = (ViewPager)this.findViewById(R.id.vp);
		vp.setPageMargin(2); //页间距
		vp.setAdapter(new GuidePageAdapter());

		indicator = (LinePageIndicator) this.findViewById(R.id.indicator);
		indicator.setViewPager(vp);
	}
	private void initData(){
		// 加载引导页
		LayoutInflater inflater = LayoutInflater.from(PicturePreview.this);
		pageViews = new ArrayList<View>();
		pageViews = new ArrayList<View>();
		view1 = inflater.inflate(R.layout.trade_guide_item_01, null);
		image1 = (ImageView) view1.findViewById(R.id.image_pieview1);
		view2 = inflater.inflate(R.layout.trade_guide_item_02, null);
		image2 = (ImageView) view2.findViewById(R.id.image_pieview2);
		view3 = inflater.inflate(R.layout.trade_guide_item_03, null);
		image3 = (ImageView) view3.findViewById(R.id.image_pieview3);
		if (myApplication.getListBitmap()!=null) {
		for(int i = 0;i<myApplication.getListBitmap().size();i++){
			if(i == 0){
				pageViews.add(view1);
				image1.setImageBitmap(myApplication.getListBitmap().get(i));
			}else if(i == 1){
				pageViews.add(view2);
				image2.setImageBitmap(myApplication.getListBitmap().get(i));
			}else {
				pageViews.add(view3);
				image3.setImageBitmap(myApplication.getListBitmap().get(i));
			}
		}
		} else {
		}
	}

	// 引导页面数据适配器
	class GuidePageAdapter extends PagerAdapter {
		@Override
		public int getCount() {
			return pageViews == null ? 0 : pageViews.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView(pageViews.get(arg1));
		}

		@Override
		public Object instantiateItem(View arg0, int arg1) {
			((ViewPager) arg0).addView(pageViews.get(arg1));
			return pageViews.get(arg1);
		}
	}

}
