package com.growcontrol.studio.configs;

import java.util.Map;
import java.util.Set;

import com.growcontrol.api.clientapi.apiClientDefines;
import com.growcontrol.api.clientapi.configs.WindowConfig;
import com.growcontrol.common.gcCommonDefines;
import com.poixson.commonapp.config.xConfig;
import com.poixson.commonjava.Utils.utils;
import com.poixson.commonjava.xLogger.xLevel;


public class gcStudioConfig extends xConfig {
	public static final String LOG_NAME = "CONFIG";

	private volatile Map<String, WindowConfig> windowConfigs = null;



	public gcStudioConfig(final Map<String, Object> datamap) {
		super(datamap);
	}



	// version
	public String getVersion() {
		final String value = this.getString(gcCommonDefines.CONFIG_VERSION);
		if(utils.isEmpty(value))
			return null;
		return value;
	}



	// log level
	public xLevel getLogLevel() {
		final String value = this.getString(gcCommonDefines.CONFIG_LOG_LEVEL);
		if(utils.isEmpty(value))
			return null;
		return xLevel.parse(value);
	}



	// debug
	public Boolean getDebug() {
		return this.getBoolean(gcCommonDefines.CONFIG_DEBUG);
	}



	// window configs
	public Map<String, WindowConfig> getWindowConfigs() {
		if(this.windowConfigs == null) {
			final Set<Object> dataset = this.getSet(
					Object.class,
					apiClientDefines.CONFIG_WINDOWS
			);
			this.windowConfigs = WindowConfig.get(dataset);
		}
		return this.windowConfigs;
	}
	public WindowConfig getWindowConfig(final String name) {
		if(this.windowConfigs == null)
			this.getWindowConfigs();
		return this.windowConfigs.get(name);
	}



}
