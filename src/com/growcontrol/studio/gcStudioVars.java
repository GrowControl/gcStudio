package com.growcontrol.studio;

import com.growcontrol.api.clientapi.apiClientDefines;
import com.growcontrol.api.clientapi.configs.ProfilesConfig;
import com.growcontrol.studio.configs.gcStudioConfig;
import com.poixson.commonapp.config.xConfig;
import com.poixson.commonapp.config.xConfigException;
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
					try {
						config = (gcStudioConfig) xConfig.Load(
								xLog.getRoot(),
								null,
								gcStudioDefines.CONFIG_FILE,
								gcStudioConfig.class,
								gcStudio.class
						);
					} catch (xConfigException e) {
						xLog.getRoot().trace(e);
						config = null;
					}
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



	// profiles
	public static ProfilesConfig getProfilesConfig() {
		if(profilesConfig == null) {
			synchronized(configLock) {
				if(profilesConfig == null) {
					try {
						profilesConfig = (ProfilesConfig) xConfig.Load(
								xLog.getRoot(),
								null,
								apiClientDefines.PROFILES_FILE,
								ProfilesConfig.class,
								gcStudio.class
						);
					} catch (xConfigException e) {
						xLog.getRoot().trace(e);
						profilesConfig = null;
					}
					if(profilesConfig == null) {
						Failure.fail("Failed to load "+apiClientDefines.PROFILES_FILE);
						return null;
					}
					if(profilesConfig.isFromResource())
						xLog.getRoot(gcStudioConfig.LOG_NAME)
							.warning("Created default "+apiClientDefines.PROFILES_FILE);
				}
			}
		}
		return profilesConfig;
	}



//	// connect state
//	private static volatile ConnectState connectState = ConnectState.CLOSED;
//	private static volatile ConnectState connectLast  = null;
//	public static ConnectState getConnectState() {
//		if(connectState == null)
//			return ConnectState.CLOSED;
//		return connectState;
//	}
//	public static ConnectState getLastConnectState() {
//		return connectLast;
//	}
//	public static boolean setConnectState(final ConnectState expected, final ConnectState state) {
//		if(state == null) throw new NullPointerException("connectState argument is required!");
//		if(expected == null || expected.equals(connectState)) {
//			synchronized(connectState) {
//				if(expected == null || expected.equals(connectState)) {
//					connectLast = connectState;
//					connectState = state;
//					// do change
////					doChangedState(connectState, connectLast);
//					final guiManager manager = guiManager.get();
//					manager.updateConnectState(connectState, connectLast);
//					return true;
//				}
//			}
//		}
//		return false;
//	}
////	protected abstract void doChangedState(State lastState);



}
