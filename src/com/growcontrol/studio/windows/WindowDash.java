/*
package com.growcontrol.studio.windows;

import com.growcontrol.studio.gcStudioDefines;
import com.growcontrol.studio.guiManager;
import com.poixson.commonapp.gui.guiUtils;
import com.poixson.commonapp.gui.xWindow;
import com.poixson.commonapp.gui.annotations.xWindowProperties;


@xWindowProperties(
		resizable = true
)
public class WindowDash extends xWindow {
	private static final long serialVersionUID = 1L;
//	private final boolean DEBUG;



	public WindowDash() {
		super();
//		this.DEBUG = xVars.debug();
		this.setTitle(gcStudioDefines.WINDOW_TITLE);
		// resize and prepare

		// show window
		this.Show();
		this.registerCloseHook();
	}



	// close window
	@Override
	public void close() {
		if(guiUtils.forceDispatchThread(this, "close")) return;
		if(!this.closing.compareAndSet(false, true)) return;
		this.closing.set(false);
		super.close();
		guiManager.get()
			.doDashWindowClosed();
	}



}
*/
