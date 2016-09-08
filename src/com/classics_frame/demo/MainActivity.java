package com.classics_frame.demo;

import java.util.ArrayList;
import java.util.List;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.Toast;









import com.classics_frame.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class MainActivity extends SlidingFragmentActivity
{
    
	private ViewPager mViewPager;
	private FragmentPagerAdapter mAdapter;
	private List<Fragment> mFragments = new ArrayList<Fragment>();


	MainTab01 tab01;
	MainTab02 tab02;
	MainTab03 tab03;
	MainTab04 tab04;
	MainTab05 tab05;
	
	
	private ImageButton stepButton;
	private ImageButton campassButton;
	private ImageButton weatherButton;
	private ImageButton heartButton;
	private ImageButton mapButton;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		
		// 初始化下方导航按钮,方便后面的使用
		this.stepButton = (ImageButton)findViewById(R.id.id_step);
		this.campassButton = (ImageButton)findViewById(R.id.id_campass);
		this.weatherButton = (ImageButton)findViewById(R.id.id_weather);
		this.heartButton = (ImageButton)findViewById(R.id.id_heart);
		this.mapButton = (ImageButton)findViewById(R.id.id_map);
		
		// 初始化SlideMenu
		initRightMenu();
		// 初始化ViewPager
		initViewPager();
		
	

	}

	private void initViewPager()
	{
		mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
		tab01 = new MainTab01();
	    tab02 = new MainTab02();
		tab03 = new MainTab03();
		tab04 = new MainTab04();
		tab05 = new MainTab05();
		
		
		mFragments.add(tab01);
		mFragments.add(tab02);
		mFragments.add(tab03);
		mFragments.add(tab04);
		mFragments.add(tab05);
		/**
		 * 初始化Adapter
		 */
		mAdapter = new FragmentPagerAdapter(getSupportFragmentManager())
		{
			@Override
			public int getCount()
			{
				return mFragments.size();
			}

			@Override
			public Fragment getItem(int arg0)
			{
				return mFragments.get(arg0);
			}
		};
		mViewPager.setAdapter(mAdapter);
		stepButton.setEnabled(false);//已进入软件，设置当前第一个page的导航的底色
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			

			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				resetImageButton();
				switch(arg0){
				  case 0:
					  stepButton.setEnabled(false);
					  break;
				  case 1:
					   campassButton.setEnabled(false);
					  break;
				  case 2:
					  weatherButton.setEnabled(false);
					  break;
				  case 3:
					  heartButton.setEnabled(false);
					  break;
				  case 4:
					  mapButton.setEnabled(false);		      
					  break;
			
				}
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
	} 
	
	
	protected void resetImageButton() {
		  mapButton.setEnabled(true);
		  stepButton.setEnabled(true);
	      campassButton.setEnabled(true);
	      weatherButton.setEnabled(true);
	      heartButton.setEnabled(true);			
	}

	private void initRightMenu()
	{
		
		Fragment leftMenuFragment = new MenuLeftFragment();
		setBehindContentView(R.layout.left_menu_frame);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.id_left_menu_frame, leftMenuFragment).commit();
		SlidingMenu menu = getSlidingMenu();
		menu.setMode(SlidingMenu.LEFT_RIGHT);
		// 设置触摸屏幕的模式
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		menu.setShadowWidthRes(R.dimen.shadow_width);
		menu.setShadowDrawable(R.drawable.shadow);
		// 设置滑动菜单视图的宽度
		menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
//		menu.setBehindWidth()
		// 设置渐入渐出效果的值
		menu.setFadeDegree(0.35f);
		// menu.setBehindScrollScale(1.0f);
		menu.setSecondaryShadowDrawable(R.drawable.shadow);
		
		//设置右边（二级）侧滑菜单
		menu.setSecondaryMenu(R.layout.right_menu_frame);
		Fragment rightMenuFragment = new MenuRightFragment();
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.id_right_menu_frame, rightMenuFragment).commit();
	}

	@Override
	protected void onResume() {
		//强制竖屏，不得横屏
		if(getRequestedOrientation()!=ActivityInfo.SCREEN_ORIENTATION_PORTRAIT){
			  setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
			 }
		super.onResume(); 
	}

	//上方两个图标的点击监听事件（显示左菜单，显示右菜单）
	public void showLeftMenu(View view)
	{
		getSlidingMenu().showMenu();
	}

	public void showRightMenu(View view)
	{
		getSlidingMenu().showSecondaryMenu();
	}
	
	public void StepCounts(View view){
		resetImageButton();
		this.stepButton.setEnabled(false);
	    changeView(0);	
	}
	public void ShowCampass(View view){
		resetImageButton();
        this.campassButton.setEnabled(false);
        changeView(1);
	}
	public void ShowWeather(View view){
		resetImageButton();
        this.weatherButton.setEnabled(false);   
	    changeView(2);
	}
	public void ShowHeartCounts(View view){
		resetImageButton();
		this.heartButton.setEnabled(false);
	    changeView(3);
	}
	public void ShowMap(View view){
		resetImageButton();
		this.mapButton.setEnabled(false);
   	    changeView(4);
	}
	
	//改变当前viewpager的item:即fragement
	private void changeView(int desTab){
		mViewPager.setCurrentItem(desTab);
	}
	
	
	//程序退出提示
	private long exitTime = 0;
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			if ((System.currentTimeMillis() - exitTime) > 2000) {
				Toast.makeText(getApplicationContext(), "再按一次退出程序",
						Toast.LENGTH_SHORT).show();
				exitTime = System.currentTimeMillis();
			} else {
				finish();
				System.exit(0);
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
