package team.yjcollege.matchproject.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import team.yjcollege.matchproject.R;
import team.yjcollege.matchproject.adapter.PfGridViewAdapter;
import team.yjcollege.matchproject.thirfragment.Grallery3DActivity;
import team.yjcollege.matchproject.ui.BaseFragment;

public class ProfileFragment extends BaseFragment implements OnItemClickListener {
	private View view;
	private GridView pfGridView;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        pfGridView =(GridView) view.findViewById(R.id.fragment_profile_gridView);
        PfGridViewAdapter pfGridViewAdapter = new PfGridViewAdapter(getActivity());
        pfGridView.setAdapter(pfGridViewAdapter);
        pfGridView.setOnItemClickListener(this);
    	return view;
    }

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		startActivity(new Intent(getActivity(), Grallery3DActivity.class));
	}

}

