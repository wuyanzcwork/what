package com.zc.model.template;

public abstract class AbstractHummer {

	/**
	 * 钩子函数
	 * @return
	 */
	protected abstract boolean isAlarm();

	protected abstract void start();

	protected abstract void stop();

	protected abstract void alarm();

	protected abstract void engineBoom();

	/**
	 * 模板方法
	 */
	public final void run() {

		start();

		engineBoom();

		if( isAlarm() ) {
			alarm();
		}

		stop();
	}
}
