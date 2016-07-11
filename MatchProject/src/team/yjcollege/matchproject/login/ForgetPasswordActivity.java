package team.yjcollege.matchproject.login;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import team.yjcollege.matchproject.R;
import team.yjcollege.matchproject.myapplication.MyApplication;
import team.yjcollege.matchproject.ui.TitleActivity;

public class ForgetPasswordActivity extends TitleActivity {
	private Button btnNext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(R.string.forgetword_name);
		showBackwardView(R.string.login_activity_name, true);
		setContentView(R.layout.activity_forget_password);
		MyApplication.getInstance().addActivity(this);
		btnNext=(Button) this.findViewById(R.id.register_btnRegist);
		btnNext.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(ForgetPasswordActivity.this, ForgetPassword2Activity.class));
				overridePendingTransition(R.anim.left, R.anim.right);
			}
		});
	}

}
