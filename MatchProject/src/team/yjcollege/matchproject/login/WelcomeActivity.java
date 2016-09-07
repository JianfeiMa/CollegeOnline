package team.yjcollege.matchproject.login;

import android.os.Bundle;
import android.os.Handler;

import com.example.move.activity.LoginActivity;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.Window;
import team.yjcollege.matchproject.R;

public class WelcomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_welcome);
		Runnable runnable=new Runnable() {
			public void run() {
				
			}
		};
		Handler hd=new Handler();
		hd.postDelayed(runnable, 2000);
		hd.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
				WelcomeActivity.this.finish();
			}
		}, 2000);
	}

}
