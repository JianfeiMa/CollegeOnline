/**
 * 
 */
package com.example.move.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.http.HttpStatus;
import org.w3c.dom.ls.LSInput;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import team.yjcollege.matchproject.R;
import com.example.move.adapter.ShopListAdapter;
import com.example.move.common.AppException;
import com.example.move.common.Constants;
import com.example.move.common.HttpHelper;
import com.example.move.common.HttpHelper.Callback;
import com.example.move.common.PageModel;
import com.example.move.entity.ItemList;
import com.example.move.entity.Users;
import team.yjcollege.matchproject.myapplication.MyApplication;
import team.yjcollege.matchproject.ui.TitleActivity;

import com.example.move.customview.PullDownView;
import com.example.move.customview.PullDownView.UpdateHandler;

/**
 * @author YHX 2014-2-27下午2:47:25 我发布的二手信息
 */
public class MyReleaseActivity extends TitleActivity implements UpdateHandler {
	private PullDownView pdv;
	private ListView lv;
	private LinearLayout footer;// listview的底部
	private ProgressBar listview_foot_progress;// listview的底部进度条
	private TextView listview_foot_more, title, tv_edit, tv_release;
	private LinearLayout list_all, list_school;
	private ShopListAdapter adapter;
	private List<ItemList> listDatas = new ArrayList<ItemList>();
	private Button btn_back, btn_right;
	private int pageNo = 1;
	private String string;
	private String condition = "username";// 查询条件是根据账户查询还是根据类型查询
	private boolean hasMore = false;
	private int lastItem;
	private MyApplication myApplication;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.trade_activity_myrelease);
		super.setTitle("我的发布");
		super.showBackwardView(R.string.button_backward, true);
		myApplication = (MyApplication) this.getApplicationContext();
		myApplication.addActivity(this);
		Users users = (Users) myApplication.userMap.get("user");
		if (users != null) {
			string = users.getUserName();
		}
		initPullDownView();
		loadData(pageNo);
//		title = (TextView) this.findViewById(R.id.title_tv);
//		title.setText("我的发布");
//		// 返回按钮监听
//		btn_back = (Button) this.findViewById(R.id.button_back);
//		btn_back.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				Intent intent =  new Intent(MyReleaseActivity.this,MainActivity.class);
//				MyReleaseActivity.this.startActivity(intent);
//				MyReleaseActivity.this.finish();
//			}
//		});
//		// 手动刷新
//		btn_right = (Button) this.findViewById(R.id.button_right);
//		btn_right.setText("刷新");
//		btn_right.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				pdv.startUpdate();
//				onUpdate();
//			}
//		});
		//删除按钮
		list_all = (LinearLayout) this.findViewById(R.id.list_all);
		tv_edit = (TextView) this.findViewById(R.id.tv_all);
		tv_edit.setText("删除");
		list_all.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				View view = getLayoutInflater().inflate(R.layout.trade_my_dialog,
						null, false);
				TextView title = (TextView) view.findViewById(R.id.title);
				title.setText("温馨提示");
				TextView message = (TextView) view
						.findViewById(R.id.message);
				message.setVisibility(View.VISIBLE);
				message.setText("取消：长按选项，删除指定选项，确定：删除全部选项");
				TextView gallery = (TextView) view
						.findViewById(R.id.gallery);
				gallery.setVisibility(View.GONE);
				TextView camera = (TextView) view.findViewById(R.id.camera);
				camera.setVisibility(View.GONE);
				final Dialog dialog = new Dialog(MyReleaseActivity.this,
						R.style.myDialogTheme);
				dialog.setContentView(view);
				dialog.show();
				Button cancel = (Button) view.findViewById(R.id.button_cancel);
				cancel.setVisibility(View.VISIBLE);
				cancel.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						dialog.dismiss();
					}
				});
				Button ok = (Button) view.findViewById(R.id.button_ok);
				ok.setVisibility(View.VISIBLE);
				ok.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						dialog.dismiss();
						adapter.setItemList(null);
						adapter.notifyDataSetChanged();
						HashMap<String, Object> params = new HashMap<String, Object>();
						params.put("username", string);
						params.put("shopoid", 0);
						HttpHelper.asyncPost(Constants.URL + "/second-hand/shop_delete.do", params, new Callback() {
							
							@Override
							public void dataLoaded(Message msg) {
								if(HttpStatus.SC_OK != msg.what){
									AppException.http(msg.what).makeToast(
											MyReleaseActivity.this);
									return;
								}else {
									Toast.makeText(MyReleaseActivity.this,
											msg.obj.toString(),
											Toast.LENGTH_SHORT).show();
								}
							}
						});
					}
				});

			}
		});
		list_school = (LinearLayout) this.findViewById(R.id.list_school);
		tv_release = (TextView) this.findViewById(R.id.tv_school);
		tv_release.setText("发布");
		list_school.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MyReleaseActivity.this,
						ReleaseActivity.class);
				startActivity(intent);
			}
		});
	}

	// 初始化下拉刷新
	private void initPullDownView() {
		pdv = (PullDownView) this.findViewById(R.id.pdv);
		pdv.setUpdateHandler(this);// 设置下拉刷新处理器
		lv = (ListView) pdv.findViewById(R.id.lv);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (position == lv.getCount()-1) {
				} else {
					ItemList itemList = listDatas.get(position);
					Bundle data = new Bundle();
					data.putSerializable("shopinfo", itemList);
					Intent intent = new Intent(MyReleaseActivity.this,
							ShopInfoActivity.class);
					intent.putExtras(data);
					MyReleaseActivity.this.startActivity(intent);
				}
			}
		});
		//长按监听 ，长按删除对应项
		lv.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(final AdapterView<?> parent, final View view,
					 int position, long id) {
				if(position == lv.getCount()-1){
					return false;
				}
				final int index = position;
				View dialogView = getLayoutInflater().inflate(R.layout.trade_my_dialog,
						null, false);
				TextView title = (TextView) dialogView.findViewById(R.id.title);
				title.setText("温馨提示");
				TextView message = (TextView) dialogView
						.findViewById(R.id.message);
				message.setVisibility(View.VISIBLE);
				message.setText("删除不能恢复，你确定删除该项");
				TextView gallery = (TextView) dialogView
						.findViewById(R.id.gallery);
				gallery.setVisibility(View.GONE);
				TextView camera = (TextView) dialogView.findViewById(R.id.camera);
				camera.setVisibility(View.GONE);
				final Dialog dialog = new Dialog(MyReleaseActivity.this,
						R.style.myDialogTheme);
				dialog.setContentView(dialogView);
				dialog.show();
				Button cancel = (Button) dialogView.findViewById(R.id.button_cancel);
				cancel.setVisibility(View.VISIBLE);
				cancel.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						dialog.dismiss();
					}
				});
				Button ok = (Button) dialogView.findViewById(R.id.button_ok);
				ok.setVisibility(View.VISIBLE);
				ok.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						dialog.dismiss();
						int shopid = listDatas.get(index).getShopId();
						listDatas.remove(index);
						adapter.notifyDataSetChanged();
						HashMap<String, Object> params = new HashMap<String, Object>();
						params.put("username", "");
						params.put("shopoid", shopid);
						HttpHelper.asyncPost(Constants.URL + "/second-hand/shop_delete.do", params, new Callback() {
							
							@Override
							public void dataLoaded(Message msg) {
								if(HttpStatus.SC_OK != msg.what){
									AppException.http(msg.what).makeToast(
											MyReleaseActivity.this);
									return;
								}else {
									Toast.makeText(MyReleaseActivity.this,
											msg.obj.toString(),
											Toast.LENGTH_SHORT).show();
								}
							}
						});
					}
				});
				return false;
			}
		});
		// 初始化底部视图
		this.footer = ((LinearLayout) LayoutInflater.from(this).inflate(
				R.layout.trade_listview_footer, null));
		listview_foot_progress = (ProgressBar) footer
				.findViewById(R.id.listview_foot_progress);
		listview_foot_more = (TextView) footer
				.findViewById(R.id.listview_foot_more);

		lv.addFooterView(footer);// 添加底部视图 必须在setAdapter前
		lv.setFooterDividersEnabled(false);

		// /////////////////////////////
		adapter = new ShopListAdapter(this);
		lv.setAdapter(adapter);
		lv.setOnScrollListener(new AbsListView.OnScrollListener() {
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// 数据为空
				if (listDatas.isEmpty()) {
					return;
				}

				// 判断是否滚动到底部
				if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE
						&& lastItem == adapter.getCount()) {
					if (hasMore) {
						listview_foot_progress.setVisibility(View.VISIBLE);
						listview_foot_more.setText("加载中...");
						loadData(++pageNo);
					}
				}
			}

			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				lastItem = firstVisibleItem + visibleItemCount - 1;
			}
		});
	}

	// 回调方法
	public void onUpdate() {
		pageNo = 1;
		lv.setSelection(0);
		loadData(pageNo);
	}

	// 加载数据
	public void loadData( int pn) {
		getLayoutInflater().inflate(R.layout.trade_progress_dialog, null,
				false);
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("pageNo", pn);
		params.put("value", string);
		params.put("condition", condition);
		params.put("school", "");
		if (pageNo == 1) {
			listDatas.clear();
			footer.setVisibility(View.GONE);
			lv.setFooterDividersEnabled(false);
		} else {
			footer.setVisibility(View.VISIBLE);
			lv.setFooterDividersEnabled(true);
		}
		HttpHelper.asyncPost(Constants.URL + "/second-hand/shop_info.do",
				params, new Callback() {
					@Override
					public void dataLoaded(Message msg) {
						footer.setVisibility(View.GONE);
						if (HttpStatus.SC_OK != msg.what) {
							AppException.http(msg.what).makeToast(
									MyReleaseActivity.this);
							pdv.endUpdate();
							return;
						}
						String json = (String) msg.obj;
						PageModel<ItemList> pm = PageModel.jsonConvert(json);
						listDatas.addAll(pm.getData());
						adapter.setItemList(listDatas);
						adapter.notifyDataSetChanged();
						pdv.endUpdate();

						if (pageNo < pm.getPageCount()) {
							hasMore = true;
						} else {
							hasMore = false;
							footer.setVisibility(View.VISIBLE);
							listview_foot_progress
									.setVisibility(View.INVISIBLE);
							listview_foot_more.setText("已加载全部");
						}
					}
				});
	}
}
