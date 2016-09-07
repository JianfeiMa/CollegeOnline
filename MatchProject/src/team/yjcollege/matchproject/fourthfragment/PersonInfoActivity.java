package team.yjcollege.matchproject.fourthfragment;

import java.io.File;

import com.example.move.entity.Users;
import team.yjcollege.matchproject.R;
import team.yjcollege.matchproject.customview.CircleImg;
import team.yjcollege.matchproject.customview.SelectPicPopupWindow;
import team.yjcollege.matchproject.login.LoginActivity;
import team.yjcollege.matchproject.myapplication.MyApplication;
import team.yjcollege.matchproject.ui.ModificationActivity;
import team.yjcollege.matchproject.ui.TitleActivity;
import team.yjcollege.matchproject.util.FileUtil;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class PersonInfoActivity extends TitleActivity {
	private CircleImg ivCircleImg=null;
	private SelectPicPopupWindow menuWindow=null;
	private static final String IMAGE_FILE_NAME="avatarImage.jpg";
	private static final int REQUESTCODE_PICK=0;
	private static final int REQUESTCODE_TAKE=1;
	private static final int REQUESTCODE_CUTTING=3;
	private String urlpath;
	private RelativeLayout studentId=null;
	private RelativeLayout nickName=null;
	private Users user;
	private TextView showPersonInformaiton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_person_info);
		setTitle("个人中心");
		showBackwardView(R.string.fourth_fragment_name, true);	
		ivCircleImg=(CircleImg) findViewById(R.id.iv_headPhoto);
		Bitmap b=BitmapFactory.decodeFile("/storage/sdcard0/MatchProject/20160630/temphead.jpg");
		if(b!=null){
			ivCircleImg.setImageBitmap(b);
		}
		ivCircleImg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				menuWindow=new SelectPicPopupWindow(getApplication(), itemsOnClick);
				menuWindow.showAtLocation(findViewById(R.id.activity_person_info), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
			}
		});
		studentId=(RelativeLayout) findViewById(R.id.layout_studentId);
		studentId.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				AlertDialog.Builder builder=new AlertDialog.Builder(PersonInfoActivity.this);
				builder.setTitle("温馨提示!");
				builder.setMessage("你要切换账号吗?");
				builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						SharedPreferences sp=getSharedPreferences("config", Context.MODE_PRIVATE);
						SharedPreferences.Editor editor=sp.edit();
						editor.remove("userName");
						editor.remove("passWord");
						editor.commit();
						Intent intent=new Intent(PersonInfoActivity.this, com.example.move.activity.LoginActivity.class);
						startActivity(intent);
					}
				});
				builder.setNegativeButton("取消", null);
				builder.create();
				builder.show();
			}
		});
		nickName=(RelativeLayout) findViewById(R.id.layout_nickname);
		nickName.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent modificationActivityIntent=new Intent(PersonInfoActivity.this, ModificationActivity.class);
				startActivity(modificationActivityIntent);
			}
		});
		
		/** 下面这个控件是测试展示个人信息的 */
		showPersonInformaiton = (TextView) this.findViewById(R.id.show_person_information_textview);
		MyApplication myApplication = (MyApplication) this.getApplicationContext();
		
		if(myApplication.userMap.size()>0){
			Log.v("showmark", "下面这个控件是测试展示个人信息的");
			user = (Users) myApplication.userMap.get("user");
		}
		String personInformation = null;
		if(user!=null){
			personInformation=user.getUserName();
			personInformation+=user.getSchool();
			personInformation+=user.getProfessional();
			personInformation+=user.getEmail();
		}
		showPersonInformaiton.setText(personInformation);
	}

	
	private OnClickListener itemsOnClick=new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			menuWindow.dismiss();
			switch (arg0.getId()) {
			case R.id.takePhotoBtn:
				Intent takeIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				takeIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(), IMAGE_FILE_NAME)));
				startActivityForResult(takeIntent, REQUESTCODE_TAKE);
				break;

			case R.id.pickPhotoBtn:
				Intent pickIntent=new Intent(Intent.ACTION_PICK, null);
				pickIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
				startActivityForResult(pickIntent, REQUESTCODE_PICK);
				break;
				
			default:
				break;
			}
		}
	};

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case REQUESTCODE_TAKE:
			File temp=new File(Environment.getExternalStorageDirectory()+"/"+IMAGE_FILE_NAME);
			startPhotoZoom(Uri.fromFile(temp));
			break;
		case REQUESTCODE_PICK:
			try {
				startPhotoZoom(data.getData());
			} catch (NullPointerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case REQUESTCODE_CUTTING:
			if(data!=null){
				setPicToView(data);
			}
			break;
		default:
			break;
		}
	}

	public void startPhotoZoom(Uri uri){
		Intent cropIntent=new Intent("com.android.camera.action.CROP");
		cropIntent.setDataAndType(uri, "image/*");
		cropIntent.putExtra("crop", "true");
		cropIntent.putExtra("aspectX", 1);
		cropIntent.putExtra("aspectY", 1);
		cropIntent.putExtra("outputX", 300);
		cropIntent.putExtra("outputY", 300);
		cropIntent.putExtra("return-data", true);
		startActivityForResult(cropIntent, REQUESTCODE_CUTTING);
	}
	
	private void setPicToView(Intent picdata) {
		Bundle extras = picdata.getExtras();
		if (extras != null) {
			// 取得SDCard图片路径做显示
			Bitmap photo = extras.getParcelable("data");
			Drawable drawable = new BitmapDrawable(null, photo);
			urlpath = FileUtil.saveFile(getApplicationContext(), "temphead.jpg", photo);
			Toast.makeText(getApplication(), urlpath, 3).show();
			Log.v("majianfei.debug", urlpath);
			ivCircleImg.setImageDrawable(drawable);

			// 新线程后台上传服务端
//			pd = ProgressDialog.show(mContext, null, "正在上传图片，请稍候...");
//			new Thread(uploadImageRunnable).start();
		}
	}
}
