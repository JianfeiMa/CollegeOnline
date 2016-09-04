/**
 * 
 */
package com.example.move.adapter;

import java.text.SimpleDateFormat;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.move.activity.LeaveMessageActivity;
import team.yjcollege.matchproject.R;
import com.example.move.entity.Message;

/**
 * @author YHX
 *2014-2-28上午8:57:08
 *listview 的适配器
 */
public class MessageAdapter extends BaseAdapter{
	private List<Message> msgList;
	
	public List<Message> getMsgList() {
		return msgList;
	}
	public void setMsgList(List<Message> msgList) {
		this.msgList = msgList;
	}
	private Context context;
	private LayoutInflater inflater;
	public MessageAdapter(Context context){
		this.context = context;
		inflater = LayoutInflater.from(context);
	}
	@Override
	public int getCount() {
		return msgList == null ? 0 : msgList.size();
	}

	@Override
	public Object getItem(int position) {
		return msgList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder vh = null;
		if(convertView == null){
			convertView  = inflater.inflate(R.layout.trade_message_list_item, null);
			vh = new ViewHolder();
			vh.tv_username = (TextView) convertView.findViewById(R.id.tv_username);
			vh.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
			vh.tv_reply = (TextView) convertView.findViewById(R.id.tv_reply);
			vh.tv_message = (TextView) convertView.findViewById(R.id.tv_message);
			convertView.setTag(vh);
		}else {
			vh = (ViewHolder) convertView.getTag();
		}
		final Message item = msgList.get(position);
		vh.tv_username.setText(item.getUsername());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		vh.tv_time.setText(df.format(item.getLeave_time()));
		vh.tv_message.setText("  "+item.getContent());
		vh.tv_reply.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context,LeaveMessageActivity.class);
				intent.putExtra("username", item.getUsername());
                context.startActivity(intent);
			}
		});
		return convertView;
	}
	private class ViewHolder {
		TextView tv_username,tv_time,tv_reply,tv_message;
	}
}
