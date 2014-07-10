package com.mingyuanchen.youkumenu;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.mingyuanchen.youkumenu.utils.Utils;

public class MainActivity extends Activity implements OnClickListener {

	private RelativeLayout rlLevel1;//一级菜单Home
	private RelativeLayout rlLevel2;//二级菜单，menu
	private RelativeLayout rlLevel3;//最外层的三击菜单
	
	//是否该级显示菜单，默认显示
	private boolean isDisplayLevel3 = true;
	private boolean isDisplayLevel2 = true;
	private boolean isDisplayLevel1 = true;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		init();
	}

	private void init() {
		ImageButton ibHome = (ImageButton) findViewById(R.id.ib_home);
		ImageButton ibMenu = (ImageButton) findViewById(R.id.ib_menu);
		
		rlLevel1 = (RelativeLayout) findViewById(R.id.rl_Level1);
		rlLevel2 = (RelativeLayout) findViewById(R.id.rl_Level2);
		rlLevel3 = (RelativeLayout) findViewById(R.id.rl_Level3);
		
		ibHome.setOnClickListener(this);
		ibMenu.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		//如果还有动画在执行，直接返回
		if (Utils.runningAnimationCount>0) {
			return;
		}
		switch (v.getId()) {
		case R.id.ib_menu:
			if (isDisplayLevel3) {
				Utils.startOutAnimation(rlLevel3,0);
			}else {
				Utils.startInAnimation(rlLevel3, 0);
			}
			isDisplayLevel3 = !isDisplayLevel3;
			break;
		case R.id.ib_home:

			if (isDisplayLevel1) {
				//隐藏二级和三级菜单
				int startOffset = 0;
				if (isDisplayLevel3) {
					Utils.startOutAnimation(rlLevel3, startOffset);
					startOffset += 200;
					isDisplayLevel3 = !isDisplayLevel3;
				}
				if (isDisplayLevel2) {
					Utils.startOutAnimation(rlLevel2, startOffset);
					startOffset += 200;
					isDisplayLevel2 = !isDisplayLevel2;
				}
			}else {
				Utils.startInAnimation(rlLevel2, 0);
				isDisplayLevel2 = !isDisplayLevel2;
			}
			isDisplayLevel1 = !isDisplayLevel1;
			break;

		default:
			break;
		}
		
	}

	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		//如果还有动画在执行，直接返回
		if (Utils.runningAnimationCount>0) {
			return true;
		}
		switch (keyCode) {
		case KeyEvent.KEYCODE_MENU:

			if (isDisplayLevel1) {
				//隐藏二级和三级菜单
				int startOffset = 0;
				if (isDisplayLevel3) {
					Utils.startOutAnimation(rlLevel3, startOffset);
					startOffset += 200;
					isDisplayLevel3 = !isDisplayLevel3;
				}
				if (isDisplayLevel2) {
					Utils.startOutAnimation(rlLevel2, startOffset);
					startOffset += 200;
					isDisplayLevel2 = !isDisplayLevel2;
				}
				Utils.startOutAnimation(rlLevel1, startOffset);
			}else {
				Utils.startInAnimation(rlLevel1, 0);
				Utils.startInAnimation(rlLevel2, 200);
				Utils.startInAnimation(rlLevel3, 400);
				isDisplayLevel2 = !isDisplayLevel2;
				isDisplayLevel3 = !isDisplayLevel3;
			}
			isDisplayLevel1 = !isDisplayLevel1;
			break;
		default:
			break;
		}
		return super.onKeyDown(keyCode, event);
	}
}
