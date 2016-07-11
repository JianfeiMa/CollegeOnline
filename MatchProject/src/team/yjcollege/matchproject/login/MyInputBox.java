package team.yjcollege.matchproject.login;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import team.yjcollege.matchproject.R;

public class MyInputBox extends LinearLayout {
    private TextView txtTitle=null;
    private EditText etInput=null;
    private Button btnClear=null;
    
	public MyInputBox(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		View view=LayoutInflater.from(getContext()).inflate(R.layout.inputbox, this);
		txtTitle=(TextView) view.findViewById(R.id.txtTitle);
		etInput=(EditText) view.findViewById(R.id.etInput);
		btnClear=(Button) view.findViewById(R.id.btnClear);
		
		if(isInEditMode()){
	    	return;
	    }
		String attTitle=attrs.getAttributeValue(null, "title");
		int mode=attrs.getAttributeIntValue(null, "textMode", 1);
		
		txtTitle.setText(attTitle);
		etInput.setInputType(mode);
		
		etInput.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean focused) {
				if(focused==true){
					btnClear.setVisibility(VISIBLE);
				}
				else{
					btnClear.setVisibility(INVISIBLE);
				}
			}
		});
		
		etInput.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence str, int start, int before, int count) {
				if(str.length()>0){
					btnClear.setVisibility(VISIBLE);
				}
				else{
					btnClear.setVisibility(INVISIBLE);
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {		
			}
			
			@Override
			public void afterTextChanged(Editable arg0) {	
			}
		});
		
		btnClear.setVisibility(INVISIBLE);
		btnClear.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v) {
				if(etInput.getText().length()>0){
					etInput.setText("");
				}
			}
		});
	}
	
	public Editable getText(){
		return etInput.getText();
	}

	public void setText(String s){
		etInput.setText(s);
	}
}
