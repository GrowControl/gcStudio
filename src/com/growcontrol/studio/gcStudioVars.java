package com.growcontrol.studio;

import com.growcontrol.api.clientapi.apiClientDefines;
import com.growcontrol.api.clientapi.configs.ProfilesConfig;
import com.growcontrol.studio.configs.gcStudioConfig;
import com.poixson.commonapp.config.xConfigLoader;
import com.poixson.commonjava.Failure;
import com.poixson.commonjava.Utils.Keeper;
import com.poixson.commonjava.xLogger.xLog;


public class gcStudioVars {

	private static volatile gcStudioVars instance = null;
	private static final Object lock = new Object();

	// studio config
	private static volatile gcStudioConfig config = null;
	private static final Object configLock = new Object();
	private static ProfilesConfig profilesConfig = null;



	public static void init() {
		if(instance == null) {
			synchronized (lock) {
				if(instance == null) {
					instance = new gcStudioVars();
					Keeper.add(instance);
				}
			}
		}
	}
	private gcStudioVars() {
	}



	// studio config
	public static gcStudioConfig getConfig() {
		if(config == null) {
			synchronized(configLock) {
				if(config == null) {
					config = (gcStudioConfig) xConfigLoader.Load(
							gcStudioDefines.CONFIG_FILE,
							gcStudioConfig.class,
							gcStudio.class
					);
					if(config == null) {
						Failure.fail("Failed to load "+gcStudioDefines.CONFIG_FILE);
						return null;
					}
					if(config.isFromResource())
						xLog.getRoot(gcStudioConfig.LOG_NAME)
							.warning("Created default "+gcStudioDefines.CONFIG_FILE);
				}
			}
		}
		return config;
	}



}
