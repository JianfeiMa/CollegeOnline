package team.yjcollege.matchproject.firstfragment;

import com.example.move.activity.BaseActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import team.yjcollege.matchproject.R;

public class CollegeAuthorityWeb extends BaseActivity {
	private WebView webview;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_collegeauthorityweb);
		webview = (WebView) findViewById(R.id.activity_collegeauthorityweb_webView);
		WebSettings webSettings = webview.getSettings();  
        //设置WebView属性，能够执行Javascript脚本    
        webSettings.setJavaScriptEnabled(true);    
        //设置可以访问文件  
        webSettings.setAllowFileAccess(true);  
         //设置支持缩放  
        webSettings.setBuiltInZoomControls(true);  
        //加载需要显示的网页    
        webview.loadUrl("http://www.cnyjpt.cn");    
        //设置Web视图    
        webview.setWebViewClient(new webViewClient ()); 
		
	}
	
	private class webViewClient extends WebViewClient {    
        public boolean shouldOverrideUrlLoading(WebView view, String url) {    
            view.loadUrl(url);    
            return true;    
        }    
    }    

}
