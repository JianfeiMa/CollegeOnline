package team.yjcollege.matchproject.login;

import android.os.Bundle;

import com.example.move.activity.LoginActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Window;

public class IsFirstIn extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//ActivityCollector.addActivity(this);
		IsFirst();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	private void IsFirst(){
		SharedPreferences preferences=this.getSharedPreferences("firstuser", Context.MODE_PRIVATE);
		boolean first=preferences.getBoolean("first", true);
		if(first==true){
			Editor editor=preferences.edit();
			editor.putBoolean("first", false);
			editor.commit();
			startActivity(new Intent(IsFirstIn.this, GuidePageActivity.class));
			IsFirstIn.this.finish();
		}else{
			startActivity(new Intent(this, WelcomeActivity.class));
			IsFirstIn.this.finish();
		}
	}
}
