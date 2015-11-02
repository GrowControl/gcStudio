package com.growcontrol.studio;

import com.growcontrol.studio.windows.WindowDash;
import com.growcontrol.studio.windows.WindowLogin;
import com.poixson.commonapp.gui.guiUtils;
import com.poixson.commonjava.Utils.Keeper;
import com.poixson.commonjava.Utils.utils;
import com.poixson.commonjava.Utils.exceptions.RequiredArgumentException;
import com.poixson.commonjava.xLogger.xLog;


public class guiManager {

	private static volatile guiManager manager = null;
	private static final Object instanceLock = new Object();

	// display mode
	public enum GUI_MODE {
		LOGIN,
		DASH
	}
	protected volatile GUI_MODE guiMode = null;
	protected volatile GUI_MODE guiLast = null;

	// window instances
	protected volatile WindowLogin windowLogin = null;
	protected volatile WindowDash  windowDash  = null;



	public static guiManager get() {
		if(manager == null) {
			synchronized(instanceLock) {
				if(manager == null)
					manager = new guiManager();
			}
		}
		return manager;
	}
	private guiManager() {
		// just to prevent gc
		Keeper.add(this);
	}



//	public void updateConnectState(final ConnectState connectState, final ConnectState connectLast) {
//	}
//	public static void UpdateConnectState(final ConnectState connectState, final ConnectState connectLast) {
//		get().updateConnectState(connectState, connectLast);
//	}



	// show window
	public void Show(final GUI_MODE guiMode) {
		if(guiMode == null) throw new RequiredArgumentException("mode");
		// run in event dispatch thread
		if(guiUtils.forceDispatchThread(this, "Show", guiMode)) return;
		switch(guiMode) {
		// login window
		case LOGIN: {
			if(this.windowLogin == null)
				this.windowLogin = new WindowLogin();
			this.windowLogin.Show();
			break;
		}
		// dashboard window
		case DASH: {
			if(this.windowDash == null)
				this.windowDash = new WindowDash();
			break;
		}
		}
		// mode has changed
		if(!guiMode.equals(this.guiMode)) {
			xLog.getRoot("GUI").info("Displaying mode: "+guiMode.toString());
			this.guiLast = this.guiMode;
			this.guiMode = guiMode;
		}
	}



	// close windows
	public void shutdown() {
		// run in event dispatch thread
		if(guiUtils.forceDispatchThread(this, "shutdown")) return;
		utils.safeClose(this.windowLogin);
		utils.safeClose(this.windowDash);
		this.windowLogin = null;
		this.windowDash  = null;
	}



	// hook window close events
	public void doLoginWindowClosed() {
//		// exit if dash not loaded
//		if(this.windowDash == null)
//			gcClient.get().Stop();
	}
	public void doDashWindowClosed() {
//		gcClient.get().Stop();
	}



}
