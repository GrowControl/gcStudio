package growcontrol.studio;

import com.poixson.commonapp.config.xConfigLoader;
import com.poixson.commonjava.Failure;
import com.poixson.commonjava.Utils.Keeper;
import com.poixson.commonjava.xLogger.xLog;

import growcontrol.studio.configs.gcStudioConfig;


public class gcStudioVars {

	private static volatile boolean inited = false;

	// studio config
	private static volatile gcStudioConfig config = null;
	private static final Object configLock = new Object();



	public static void init() {
		if(!inited)
			Keeper.add(new gcStudioVars());
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
