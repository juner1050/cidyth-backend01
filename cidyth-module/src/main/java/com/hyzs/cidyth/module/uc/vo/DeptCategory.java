package com.hyzs.cidyth.module.uc.vo;

/**
 * 机构分类(警种)
 * 
 * @author jidw
 *
 */
public class DeptCategory implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4555591373192552495L;

	private Integer id;// 级别代码
	private String name;// 级别名称
	private Integer pid;// 父代码

	public DeptCategory() {
		super();
	}

	public DeptCategory(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
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
		DeptCategory other = (DeptCategory) obj;
		if (id != other.getId())
			return false;
		return true;
	}
}
