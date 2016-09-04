package team.yjcollege.matchproject.ui;

import team.yjcollege.matchproject.R;
import team.yjcollege.matchproject.myapplication.MyApplication;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * @author majianfei
 *
 */
public class AboutActivity extends TitleActivity {

    private static final String TAG = AboutActivity.class.getSimpleName();

    /* (non-Javadoc)
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupViews();
    }


    private void setupViews() {
        setContentView(R.layout.activity_about);
        setTitle(R.string.text_about);
        showBackwardView(R.string.button_backward, true);
        MyApplication.getInstance().addActivity(this);
        final StringBuffer buffer = new StringBuffer(getString(R.string.app_name));
        PackageManager packageManager = getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
            buffer.append(" V");
            buffer.append(packageInfo.versionName);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }

        final TextView appNameTextView = (TextView) findViewById(R.id.text_app_name);
        appNameTextView.setText(buffer.toString());
    }

    /* (non-Javadoc)
     * @see app.ui.TitleActivity#onClick(android.view.View)
     */
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
           /* case R.id.layout_official_website:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.kuaidian.mobi")));
                break;

            case R.id.layout_rate_app:
                final Intent marketIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=" + getPackageName()));
                try {
                    startActivity(marketIntent);
                } catch (ActivityNotFoundException e) {
                    Log.e(TAG, e.getMessage());
                }
                break;

            case R.id.layout_help:
                startActivity(new Intent(this, HelpActivity.class));
                break;

            case R.id.layout_feedback:
                Intent feedbackIntent = new Intent(Intent.ACTION_SEND);
                final String[] tos={getString(R.string.text_email_address)};
                feedbackIntent.putExtra(Intent.EXTRA_EMAIL, tos);
                feedbackIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.text_email_title));
                feedbackIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.text_email_content));
                feedbackIntent.setType("message/rfc822");
                try {
                    startActivity(Intent.createChooser(feedbackIntent, getString(R.string.text_email_dialog_title)));
                } catch (ActivityNotFoundException e) {
                    Log.e(TAG, e.getMessage());
                }
                break;

            case R.id.layout_update:
                break;
*/
            default:
                break;
        }
    }
    /* (non-Javadoc)
     * @see app.ui.TitleActivity#onBackward(android.view.View)
     */
    @Override
    protected void onBackward(View backwardView) {
        super.onBackward(backwardView);
    }

}
