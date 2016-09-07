package team.yjcollege.matchproject.login;

import java.util.List;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class GuideAdapter extends PagerAdapter {
	private Context context;
	private List<View> ar;

	public GuideAdapter(Context context, List<View> ar) {
		super();
		this.context = context;
		this.ar = ar;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return ar.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg1==arg0;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		container.removeView(ar.get(position));
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub
		container.addView(ar.get(position));
		return ar.get(position);
	}

}
