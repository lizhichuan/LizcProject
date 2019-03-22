/*
    ShengDao Android Client, DownTimerListener
    Copyright (c) 2014 ShengDao Tech Company Limited
 */
package com.njcool.lzccommon.view.downtime;

/**
 * [倒计时监听类]
 * 
 * @author lizhichuan
 * 
 **/
public interface CoolDownTimerListener {

	/**
	 * [倒计时每秒方法]<BR>
	 * @param millisUntilFinished
	 */
	public void onTick(long millisUntilFinished);
	
	/**
	 * [倒计时完成方法]<BR>
	 */
	public void onFinish();
}

