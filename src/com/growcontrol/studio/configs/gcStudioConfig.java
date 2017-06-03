/*
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

	private final Map<String, WindowConfig> windowConfigs;



	public gcStudioConfig(final Map<String, Object> datamap)
			throws xConfigException {
		super(datamap);
		this.windowConfigs = this.loadWindowConfigs();
	}
	private Map<String, WindowConfig> loadWindowConfigs()
			throws xConfigException {
		final List<xConfig> configsList = this.getConfigList(
				apiClientDefines.CONFIG_WINDOWS,
				WindowConfig.class
		);
		final LinkedHashMap<String, WindowConfig> windowsMap =
				new LinkedHashMap<String, WindowConfig>();
		for(final xConfig cfg : configsList) {
			final WindowConfig w = (WindowConfig) cfg;
			windowsMap.put(w.getKey(), w);
		}
		return Collections.unmodifiableMap(windowsMap);
	}



	// window configs
	public Map<String, WindowConfig> getWindowConfigs() {
		return this.windowConfigs;
	}
	public WindowConfig getWindowConfig(final String name) {
		return this.windowConfigs.get(name);
	}



}
*/
