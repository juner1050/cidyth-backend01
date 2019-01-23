package com.hyzs.cidyth.module.uc.vo;

import org.apache.commons.lang3.StringUtils;

public class Premission implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9002241884295620457L;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	private Integer id;//id
	private String code;//代码
	private String name;//名称
	private String url;//url
	private String pcodes;//所有父级代码
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getPcodes() {
		return pcodes;
	}
	public void setPcodes(String pcodes) {
		this.pcodes = pcodes;
	}
	public final String identifier(){
		StringBuilder strb = new StringBuilder();
		strb.append(id==null?"":id);
		strb.append(StringUtils.isNotBlank(code)?code:"");
		strb.append(StringUtils.isNotBlank(url)?url:"");
		return strb.toString();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((identifier() == null) ? 0 : identifier().hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Premission other = (Premission) obj;
		if (identifier() == null) {
			if (other.identifier() != null)
				return false;
		} else if (!identifier().equals(other.identifier()))
			return false;
		return true;
	}
	
}
