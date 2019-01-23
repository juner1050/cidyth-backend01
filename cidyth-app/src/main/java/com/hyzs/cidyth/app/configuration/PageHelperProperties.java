package com.hyzs.cidyth.app.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "pagehelper")
public class PageHelperProperties {
	private boolean reasonable = false;
	private boolean supportMethodsArguments = true;
	private String returnPageInfo;
	private String params;

	public boolean isReasonable() {
		return reasonable;
	}

	public void setReasonable(boolean reasonable) {
		this.reasonable = reasonable;
	}

	public boolean isSupportMethodsArguments() {
		return supportMethodsArguments;
	}

	public void setSupportMethodsArguments(boolean supportMethodsArguments) {
		this.supportMethodsArguments = supportMethodsArguments;
	}

	public String getReturnPageInfo() {
		return returnPageInfo;
	}

	public void setReturnPageInfo(String returnPageInfo) {
		this.returnPageInfo = returnPageInfo;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}
}
