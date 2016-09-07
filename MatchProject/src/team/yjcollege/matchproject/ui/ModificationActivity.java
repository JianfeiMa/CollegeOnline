package team.yjcollege.matchproject.ui;

import android.os.Bundle;
import team.yjcollege.matchproject.R;

public class ModificationActivity extends TitleActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modification);
		showBackwardView(R.id.button_back, true);
		showForwardView(R.string.modification_activity_completed, true);
	}

}
