package com.hyzs.cidyth.module.uc.vo;

/**
 * 行政级别
 * 
 * @author jidw
 *
 */
public class PolityLevel implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2091783845510479151L;

	private Integer id;// 级别代码
	private String name;// 级别名称
	private Integer pid;// 父代码

	public PolityLevel() {

	}

	public PolityLevel(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		PolityLevel other = (PolityLevel) obj;
		if (id != other.getId())
			return false;
		return true;
	}

}
