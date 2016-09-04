package team.yjcollege.matchproject.fragment;

import team.yjcollege.matchproject.R;
import team.yjcollege.matchproject.customview.CircleImg;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import team.yjcollege.matchproject.fourthfragment.PersonInfoActivity;
import team.yjcollege.matchproject.myapplication.MyApplication;
import team.yjcollege.matchproject.ui.BaseFragment;
import team.yjcollege.matchproject.ui.AboutActivity;

public class SettingFragment extends BaseFragment implements OnClickListener {
	private View view=null;
	private CircleImg previewHeadPhoto=null;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view=inflater.inflate(R.layout.fragment_setting, container, false);
		previewHeadPhoto=(CircleImg) view.findViewById(R.id.fourthfragment_previewHeadPhoto);
		Bitmap b=BitmapFactory.decodeFile("/storage/sdcard0/MatchProject/20160630/temphead.jpg");
		if(b!=null){
			previewHeadPhoto.setImageBitmap(b);
		}
		return view;
	}


	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		view.findViewById(R.id.layout_about).setOnClickListener(this);
		view.findViewById(R.id.layout_account).setOnClickListener(this);
		view.findViewById(R.id.layout_exit).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.layout_about:
			startActivity(new Intent(getActivity(), AboutActivity.class));
			break;
		case R.id.layout_account:
			startActivity(new Intent(getActivity(), PersonInfoActivity.class));
			break;
		case R.id.layout_exit:

			View dialogView = getActivity().getLayoutInflater().inflate(R.layout.trade_my_dialog,
					null, false);
			TextView title = (TextView) dialogView.findViewById(R.id.title);
			title.setText("温馨提示");
			TextView message = (TextView) dialogView.findViewById(R.id.message);
			message.setVisibility(View.VISIBLE);
			message.setText("你确定要退出吗?");
			TextView gallery = (TextView) dialogView.findViewById(R.id.gallery);
			gallery.setVisibility(View.GONE);
			TextView camera = (TextView) dialogView.findViewById(R.id.camera);
			camera.setVisibility(View.GONE);
			final Dialog dialog = new Dialog(getActivity(),
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
				}
			});
			Button ok = (Button) dialogView.findViewById(R.id.button_ok);
			ok.setVisibility(View.VISIBLE);
			ok.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					dialog.dismiss();
					MyApplication.getInstance().exit();
					System.gc();
				}
			});
		
			break;
		default:
			break;
		}
	}
}