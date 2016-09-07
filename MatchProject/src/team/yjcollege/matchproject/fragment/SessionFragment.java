package team.yjcollege.matchproject.fragment;

import com.example.move.activity.TradeMainActivity;

import team.yjcollege.matchproject.R;
import team.yjcollege.matchproject.adapter.SfGridViewAdater;
import team.yjcollege.matchproject.firstfragment.CollegeAuthorityWeb;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import team.yjcollege.matchproject.ui.BaseFragment;
import team.yjcollege.matchproject.ui.SinvoiceActivity;

public class SessionFragment extends BaseFragment implements OnItemClickListener{
	private View view = null;
	private GridView sfGridView;

    @Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	view = inflater.inflate(R.layout.fragment_session, container, false);
    	sfGridView = (GridView) view.findViewById(R.id.fragment_session_gridView);
        SfGridViewAdater sfGridviewAdapter = new SfGridViewAdater(getActivity());
        sfGridView.setAdapter(sfGridviewAdapter);
        sfGridView.setOnItemClickListener(this);
    	return view;
    }
    
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		switch (position) {
		case 0:
			startActivity(new Intent(getActivity(), SinvoiceActivity.class));
			break;
			
		case 1:
			startActivity(new Intent(getActivity(), TradeMainActivity.class));
			break;
		case 3:
			startActivity(new Intent(getActivity(), CollegeAuthorityWeb.class));
			break;
		default:
			break;
		}
	}

}
