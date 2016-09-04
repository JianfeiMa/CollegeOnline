package team.yjcollege.matchproject.login;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import team.yjcollege.matchproject.R;

public class GuidePageActivity extends Activity implements OnClickListener, OnPageChangeListener {
	private ViewPager mGuidePage;
	private Button mBtinJoin;
	
	private List<View> ar;
	
	private ImageView[] mImages;
	private ImageView mImage;

	private AtomicInteger atomicInteger=new AtomicInteger();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_guide_page);
		initView();
	}

	private void initView(){
		mGuidePage=(ViewPager) this.findViewById(R.id.viewpager);	
		ViewGroup viewGroup=(ViewGroup) this.findViewById(R.id.rounddot);
		ar=new ArrayList<View>();
		
		View v0=this.getLayoutInflater().inflate(R.layout.page_item, null);
		LinearLayout one=(LinearLayout) v0.findViewById(R.id.pageitem);
		one.setBackgroundResource(R.drawable.guide_page_1);
		Button btn1=(Button) v0.findViewById(R.id.btn_join);
		btn1.setVisibility(View.INVISIBLE);
		ar.add(one);
		
		View v1=this.getLayoutInflater().inflate(R.layout.page_item, null);
		LinearLayout two=(LinearLayout) v1.findViewById(R.id.pageitem);
		two.setBackgroundResource(R.drawable.guide_page_2);
		Button btn2=(Button) v1.findViewById(R.id.btn_join);
		btn2.setVisibility(View.INVISIBLE);
		ar.add(two);
		
		View v2=this.getLayoutInflater().inflate(R.layout.page_item, null);
		LinearLayout three=(LinearLayout) v2.findViewById(R.id.pageitem);
		three.setBackgroundResource(R.drawable.guide_page_3);
		Button btn3=(Button) v2.findViewById(R.id.btn_join);
		btn3.setVisibility(View.INVISIBLE);
		ar.add(three);
		
		View v3=this.getLayoutInflater().inflate(R.layout.page_item, null);
		LinearLayout four=(LinearLayout) v3.findViewById(R.id.pageitem);
		four.setBackgroundResource(R.drawable.guide_page_4);
		mBtinJoin=(Button) v3.findViewById(R.id.btn_join);
		mBtinJoin.setVisibility(View.VISIBLE);
		mBtinJoin.setOnClickListener(this);
		ar.add(four);
		
		PagerAdapter adapter=new GuideAdapter(this, ar);
		mGuidePage.setAdapter(adapter);
		
		mImages = new ImageView[ar.size()];
		for (int i = 0; i < ar.size(); i++) {
			mImage = new ImageView(GuidePageActivity.this);
			//FrameLayout.LayoutParams layoutParams=new FrameLayout.LayoutParams(9, 9);
			LayoutParams layoutParams=new LayoutParams(15,15);
			layoutParams.setMargins(50,10,50,10);
			mImage.setLayoutParams(layoutParams);

			mImages[i] = mImage;
			if (i == 0) {
				mImages[i].setBackgroundResource(R.drawable.small_bg);
			} else {
				mImages[i].setBackgroundResource(R.drawable.small_bg1);
			}

			viewGroup.addView(mImages[i]);
		}
		mGuidePage.setOnPageChangeListener(this);
		
		Timer timer=new Timer();
		TimerTask timerTask=new TimerTask() {		
			@Override
			public void run() {
				handler.sendEmptyMessage(atomicInteger.incrementAndGet()-1);
			}
		};
		timer.schedule(timerTask, 2000, 2000);
	}

	Handler handler=new Handler(){
		@Override
		public void handleMessage(android.os.Message msg) {
			mGuidePage.setCurrentItem(msg.what);
			if(atomicInteger.get()==ar.size()){
				atomicInteger.set(0);
			}
		}
		
	};
	
	@Override
	public void onClick(View arg0) {
		if(arg0.getId()==R.id.btn_join){
			startActivity(new Intent(GuidePageActivity.this, WelcomeActivity.class));
		}
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		
	}

	@Override
	public void onPageSelected(int arg0) {
		atomicInteger.getAndSet(arg0);
		for(int j=0;j<mImages.length;j++){
			mImages[j].setBackgroundResource(R.drawable.small_bg1);
			if (arg0 != j) {
				mImages[j].setBackgroundResource(R.drawable.small_bg);
			}
		}
	}
}
