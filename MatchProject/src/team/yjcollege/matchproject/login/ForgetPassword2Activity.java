package team.yjcollege.matchproject.login;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import team.yjcollege.matchproject.R;
import team.yjcollege.matchproject.myapplication.MyApplication;
import team.yjcollege.matchproject.ui.TitleActivity;

public class ForgetPassword2Activity extends TitleActivity {
	private ImageView forget2Back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(R.string.forgetword2_name);
		showBackwardView(R.string.forgetword_name, true);
		setContentView(R.layout.activity_forget_password2);
		MyApplication.getInstance().addActivity(this);
//		forget2Back=(ImageView) this.findViewById(R.id.forget2_back);
//		forget2Back.setOnClickListener(new OnClickListener() {		
//			@Override
//			public void onClick(View arg0) {
//				startActivity(new Intent(ForgetPassword2Activity.this, ForgetPasswordActivity.class));
//				overridePendingTransition(R.anim.backleft, R.anim.backright);
//				ForgetPassword2Activity.this.finish();
//			}
//		});
	}

}
