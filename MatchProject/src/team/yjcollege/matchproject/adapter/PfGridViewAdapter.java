package team.yjcollege.matchproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import team.yjcollege.matchproject.R;

public class PfGridViewAdapter extends BaseAdapter {
	private int[] sighticon = {R.drawable.yjcollege1,R.drawable.yjcollege2,R.drawable.yjcollege3,
			R.drawable.yjcollege4,R.drawable.yjcollege5,R.drawable.yjcollege6,};
	private LayoutInflater inflater;
	
	public PfGridViewAdapter(Context context) {
		inflater = LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return sighticon.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return sighticon[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(convertView==null) {
			convertView = inflater.inflate(R.layout.pf_gridview_icon_item, null);
		}
		ImageView iv = (ImageView) convertView.findViewById(R.id.pf_gridview_icon_item_imageView);
		iv.setImageResource(sighticon[position]);
		return convertView;
	}

}
