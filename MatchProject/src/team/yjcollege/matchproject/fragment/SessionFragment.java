package team.yjcollege.matchproject.fragment;

import com.example.move.activity.MainActivity;

import team.yjcollege.matchproject.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import team.yjcollege.matchproject.ui.BaseFragment;
import team.yjcollege.matchproject.ui.SinvoiceActivity;

public class SessionFragment extends BaseFragment{
	private Button btnCheckOn = null;
	private Button btnTrade = null;
	private View view = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	view = inflater.inflate(R.layout.fragment_session, container, false);
    	btnCheckOn = (Button) view.findViewById(R.id.fragment_session_checkon);
    	btnCheckOn.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getActivity(), SinvoiceActivity.class));
			}
		});
    	btnTrade = (Button) view.findViewById(R.id.fragment_sesion_trade);
    	btnTrade.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(getActivity(), MainActivity.class));
			}
		});
        return view;
    }
    
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}
