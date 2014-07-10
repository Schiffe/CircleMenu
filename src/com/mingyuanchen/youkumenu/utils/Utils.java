package com.mingyuanchen.youkumenu.utils;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;

public class Utils {
	/**
	 * 执行出动画
	 * @param view
	 * @param startOffset
	 */
	public static void startOutAnimation(RelativeLayout view, int startOffset) {
		//设置出去后不可被点击
		int childCount = view.getChildCount();
		for (int i = 0; i < childCount; i++) {
			view.getChildAt(i).setEnabled(false);
		}
		RotateAnimation ra = new RotateAnimation(0, -180,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 1);
		ra.setDuration(500);
		ra.setFillAfter(true);//执行完成后停留在最后位置
		ra.setStartOffset(startOffset);//启动延时
		
		view.startAnimation(ra);
		ra.setAnimationListener(new MyAnimationListener());
	}

	public static void startInAnimation(RelativeLayout view, int startOffset) {
		//设置进来后可被点击
		int childCount = view.getChildCount();
		for (int i = 0; i < childCount; i++) {
			view.getChildAt(i).setEnabled(true);
		}
		
		RotateAnimation ra = new RotateAnimation( -180,0,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 1);
		ra.setDuration(500);
		ra.setFillAfter(true);//执行完成后停留在最后位置
		ra.setStartOffset(startOffset);
		
		view.startAnimation(ra);
	}
	public static int runningAnimationCount = 0;
	static class MyAnimationListener implements AnimationListener{

		@Override
		public void onAnimationStart(Animation animation) {
			runningAnimationCount++;
		}

		@Override
		public void onAnimationEnd(Animation animation) {
			runningAnimationCount--;
		}

		@Override
		public void onAnimationRepeat(Animation animation) {
			
		}
		
	}
	
}
