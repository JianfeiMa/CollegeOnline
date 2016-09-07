package team.yjcollege.matchproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import team.yjcollege.matchproject.R;

public class SfGridViewAdater extends BaseAdapter {
	private String[] textExplain = {"考勤","二手交易","四六级查询","学院官网","课程表","北区饭堂","南区饭堂","一卡通","兼职","社团","阳职新闻"};
	private int[] iconExpain = {R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher
			,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher
			,R.drawable.ic_launcher,R.drawable.ic_launcher};
	private LayoutInflater inflater;
	
	public SfGridViewAdater(Context context) {
		inflater = LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return textExplain.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return textExplain[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(convertView == null){
			convertView = inflater.inflate(R.layout.icon_item, null);
		}
		ImageView iv = (ImageView) convertView.findViewById(R.id.icon);
		iv.setImageResource(iconExpain[position]);
		TextView tv = (TextView) convertView.findViewById(R.id.title);
		tv.setText(textExplain[position]);
		return convertView;
	}

}
