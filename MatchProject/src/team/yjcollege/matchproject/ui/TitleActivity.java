package team.yjcollege.matchproject.ui;
import team.yjcollege.matchproject.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

public class TitleActivity extends Activity implements OnClickListener {
    private TextView mTitleTextView;
    private Button mBackwardbButton;
    private Button mForwardButton;
    private FrameLayout mContentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupViews();
    }

    private void setupViews() {
        super.setContentView(R.layout.activity_title);
        mTitleTextView = (TextView) findViewById(R.id.text_title);
        mContentLayout = (FrameLayout) findViewById(R.id.layout_content);
        mBackwardbButton = (Button) findViewById(R.id.button_backward);
        mForwardButton = (Button) findViewById(R.id.button_forward);
    }
    
    protected Button showBackwardView(int backwardResid, boolean show) {
        if (mBackwardbButton != null) {
            if (show) {
                mBackwardbButton.setText(backwardResid);
                mBackwardbButton.setVisibility(View.VISIBLE);
            } else {
                mBackwardbButton.setVisibility(View.INVISIBLE);
            }
        } // else ignored
        return mBackwardbButton;
    }

    protected Button showForwardView(int forwardResId, boolean show) {
        if (mForwardButton != null) {
            if (show) {
                mForwardButton.setVisibility(View.VISIBLE);
                mForwardButton.setText(forwardResId);
                
            } else {
                mForwardButton.setVisibility(View.INVISIBLE);
            }
        } // else ignored
        return mForwardButton;
    }

    protected void onBackward(View backwardView) {
        TitleActivity.this.finish();
    }

    protected void onForward(View forwardView) {
    	
    }
 
    @Override
    public void setTitle(int titleId) {
        mTitleTextView.setText(titleId);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitleTextView.setText(title);
    }

    @Override
    public void setTitleColor(int textColor) {
        mTitleTextView.setTextColor(textColor);
    }
    
    @Override
    public void setContentView(int layoutResID) {
        mContentLayout.removeAllViews();
        View.inflate(this, layoutResID, mContentLayout);
        onContentChanged();
    }

    @Override
    public void setContentView(View view) {
        mContentLayout.removeAllViews();
        mContentLayout.addView(view);
        onContentChanged();
    }

    @Override
    public void setContentView(View view, LayoutParams params) {
        mContentLayout.removeAllViews();
        mContentLayout.addView(view, params);
        onContentChanged();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button_backward:
                onBackward(v);
                break;

            case R.id.button_forward:
               // onForward(v);
                break;

            default:
                break;
        }
    }

}
