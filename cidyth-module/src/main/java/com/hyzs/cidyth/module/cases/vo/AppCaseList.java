package com.hyzs.cidyth.module.cases.vo;

import java.text.SimpleDateFormat;

import org.apache.commons.lang3.StringUtils;

import com.hyzs.cidyth.common.enums.CaseStateEnum;
import com.hyzs.cidyth.common.module.CommonParameter;
import com.hyzs.cidyth.module.base.entity.Cases;

/**
 * 案件列表
 * 
 * @author derrick
 *
 */
public class AppCaseList implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5086646275010861877L;

	private Cases cases;

	public AppCaseList() {

	}

	public AppCaseList(Cases cases) {
		this.cases = cases;
	}

	/**
	 * id
	 * 
	 * @return
	 */
	public Integer getId() {
		return cases == null ? null : cases.getId();
	}

	/**
	 * ajbh:案件编号
	 * 
	 * @return
	 */
	public String getAjbh() {
		return cases == null ? "" : StringUtils.isNotBlank(cases.getAjbh()) ? cases.getAjbh() : "";
	}

	/**
	 * ajmc:案件名称
	 * 
	 * @return
	 */
	public String getAjmc() {
		return cases == null ? "" : StringUtils.isNotBlank(cases.getAjmc()) ? cases.getAjmc() : "";
	}

	/**
	 * fasjcz:发案时间
	 * 
	 * @return
	 */
	public String getFasjcz() {
		SimpleDateFormat formmater = new SimpleDateFormat("yyyy-MM-dd");
		return cases == null ? "" : cases.getFasjcz() == null ? "" : formmater.format(cases.getFasjcz());
	}

	/**
	 * ab:案件类别
	 * 
	 * @return
	 */
	public String getAb() {
		return cases == null ? "" : StringUtils.isNotBlank(cases.getAb()) ? cases.getAb() : "";
	}

	/**
	 * abCn:案件类别中文值
	 * 
	 * @return
	 */
	public String getAbCn() {
		return paramHolder.getAbCn();
	}

	/**
	 * ajstate:案件状态代码
	 * 
	 * @return
	 */
	public String getAjstate() {
		return cases == null ? "" : StringUtils.isNotBlank(cases.getBdajstate()) ? cases.getBdajstate() : "";
	}

	/**
	 * ajstateCn:案件状态中文
	 * 
	 * @return
	 */
	public String getAjstateCn() {
		return (null == CaseStateEnum.ofCode(getAjstate()) ? "" : CaseStateEnum.ofCode(getAjstate()).descp());
	}

	/**
	 * zyaq:主要案情
	 * 
	 * @return
	 */
	public String getZyaq() {
		return cases == null ? "" : StringUtils.isNotBlank(cases.getZyaq()) ? cases.getZyaq() : "";
	}

	/**
	 * zdtd:作案手段特点
	 * 
	 * @return
	 */
	public String getSdtd() {
		return paramHolder.getSdtdCn();
	}

	/**
	 * zbdw:主办单位编号
	 * 
	 * @return
	 */
	public String getZbdw() {
		return cases == null ? "" : StringUtils.isNotBlank(cases.getZbdw()) ? cases.getZbdw() : "";
	}

	/**
	 * zbdwMc:主办单位名称
	 * 
	 * @return
	 */
	public String getZbdwMc() {
		return paramHolder.getZbdwCn();
	}

	/**
	 * ajzbry:案件主办人员警号
	 * 
	 * @return
	 */
	public String getAjzbry() {
		return cases == null ? "" : StringUtils.isNotBlank(cases.getAjzbry()) ? cases.getAjzbry() : "";
	}

	/**
	 * ajzbryXm:案件主办人员姓名
	 * 
	 * @return
	 */
	public String getAjzbryXm() {
		return paramHolder.getAjzbryCn();
	}

	public static Parameter parameter() {
		return paramHolder;
	}

	public static class Parameter extends CommonParameter {
		public void setAbCn(String ab) {
			super.put("abCn", ab);
		}

		/**
		 * abCn:案件类别中文值
		 * 
		 * @return
		 */
		public String getAbCn() {
			Object value = super.get("abCn");
			return value == null ? "" : value.toString();
		}

		/**
		 * zbdwCn:主办单位名称
		 * 
		 * @param zbdwCn
		 */
		public void setZbdwCn(String zbdwCn) {
			super.put("zbdwCn", zbdwCn);
		}

		/**
		 * 主办单位名称
		 * 
		 * @return
		 */
		public String getZbdwCn() {
			Object value = super.get("zbdwCn");
			return value == null ? "" : value.toString();
		}

		/**
		 * ajzbryCn:主办人员姓名
		 * 
		 * @param ajzbryCn
		 */
		public void setAjzbryCn(String ajzbryCn) {
			super.put("ajzbryCn", ajzbryCn);
		}

		/**
		 * ajzbryCn: 主办人员姓名
		 * 
		 * @return
		 */
		public String getAjzbryCn() {
			Object value = super.get("ajzbryCn");
			return value == null ? "" : value.toString();
		}

		/**
		 * 作案手段特点中文值
		 * 
		 * @param value
		 */
		public void setSdtdCn(String value) {
			super.put("sdtdCn", value);
		}

		/**
		 * sdtdCn:作案手段特点中文值
		 * 
		 * @param value
		 */
		public String getSdtdCn() {
			Object value = super.get("sdtdCn");
			return value == null ? "" : value.toString();
		}
	}

	private static Parameter paramHolder = new Parameter();
}
