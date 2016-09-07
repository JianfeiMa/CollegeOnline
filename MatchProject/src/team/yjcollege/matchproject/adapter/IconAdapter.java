package team.yjcollege.matchproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import team.yjcollege.matchproject.R;
import team.yjcollege.matchproject.util.Constants;

/**
 * 
 * @author Kaming
 *
 */
public class IconAdapter extends BaseAdapter {

	private String[] title = {};
	private LayoutInflater mInflater;
	public IconAdapter(Context context) {
		title = context.getResources().getStringArray(R.array.secondhand_gridview_text);
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return title.length;
	}

	@Override
	public Object getItem(int position) {
		return title[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.icon_item, null);
		}
		TextView tv = (TextView) convertView.findViewById(R.id.title);
		tv.setText(title[position]);
		ImageView img = (ImageView) convertView.findViewById(R.id.icon);
		img.setImageResource(Constants.SECONDHAND_GRIDVIEW_ICON[position]);
		return convertView;
	}
	

}
