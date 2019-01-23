package com.hyzs.cidyth.common.utils;

public final class Constant {
	//上下文中登录用户的key
	public static final String CONTEXT_LOGIN_USER="CURRENT^LOGIN^USER";
	//服务器上传文件路径
	public static final String SERVER_UPLOAD_PATH="E:\\Java\\Upload\\";
	//本地上传文件路径
	public static final String LCOAL_UPLOAD_PATH="Z:\\";
	
	public static final String[] SESSION_INTERCEPTOR_EXCLUDED_URL_PATERNS = new String[] { "/druid2/*", "/swagger/**",
			"/swagger-resources/**", "/v2/api-docs" };	
	
	public static class SystemInfo {
		public static SystemInfo APP = new SystemInfo("cidyth_app");//手机端
		public static SystemInfo WEB_PORTAL = new SystemInfo("cidyth_web_portal");//web版用户端
		public static SystemInfo WEB_ADMIN = new SystemInfo("cidyth_web_admin");//web版管理端
		
		public static final String DICTIONARY_GROUP_NAME="system_info";
		private String name;// 系统名称,对应字典表中system_info字典组的'name'字典项,该值一定要和注册到用户中心中的系统名称保持一致
		private String version = "0.0.1";// 系统版本,对应字典表中system_info字典组的'version'字典项

		public SystemInfo() {
		}

		public SystemInfo(String systemName) {
			this.name = systemName;
		}

		public SystemInfo(String systemName, String systemVersion) {
			this.name = systemName;
			this.version = systemVersion;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getVersion() {
			return version;
		}

		public void setVersion(String version) {
			this.version = version;
		}
	}
}
