package com.growcontrol.studio.configs;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.growcontrol.api.clientapi.apiClientDefines;
import com.growcontrol.api.clientapi.configs.WindowConfig;
import com.growcontrol.common.configs.gcAppConfig;
import com.poixson.commonapp.config.xConfig;
import com.poixson.commonapp.config.xConfigException;


public class gcStudioConfig extends gcAppConfig {

	private volatile Map<String, WindowConfig> windowConfigs = null;
	private final Object windowsLock = new Object();



	public gcStudioConfig(final Map<String, Object> datamap)
			throws xConfigException {
		super(datamap);
	}



	// window configs
	public Map<String, WindowConfig> getWindowConfigs()
			throws xConfigException {
		if(this.windowConfigs == null) {
			synchronized(this.windowsLock) {
				if(this.windowConfigs == null) {
					final List<xConfig> configsList =
						this.getConfigList(
								apiClientDefines.CONFIG_WINDOWS,
								WindowConfig.class
					);
					final LinkedHashMap<String, WindowConfig> windowsMap =
							new LinkedHashMap<String, WindowConfig>();
					for(final xConfig cfg : configsList) {
						final WindowConfig w = (WindowConfig) cfg;
						windowsMap.put(w.getKey(), w);
					}
					this.windowConfigs = Collections.unmodifiableMap(windowsMap);
				}
			}
		}
		return this.windowConfigs;
	}
	public WindowConfig getWindowConfig(final String name) {
		try {
			final Map<String, WindowConfig> windowConfigs = this.getWindowConfigs();
			return windowConfigs.get(name);
		} catch (xConfigException ignore) {}
		return null;
	}



}
