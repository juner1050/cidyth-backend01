package com.hyzs.cidyth.module.interaction.vo;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.hyzs.cidyth.common.enums.CaseStateEnum;
import com.hyzs.cidyth.common.module.CommonParameter;
import com.hyzs.cidyth.module.base.entity.Cases;
import com.hyzs.cidyth.module.reply.vo.InteractionContent;
import com.hyzs.cidyth.module.websocket.data.WebSocketMessage;

/**
 * 抽象的需求详情
 * 
 * @author derrick
 *
 */
public abstract class AbstractDemandDetail implements java.io.Serializable, WebSocketMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5177909064841440541L;

	private Cases cases;// 案件

	private InteractionContent interactionContent;// 互动内容（bbs）

	private List<Map<String, Object>> attacments;// 附件

	public AbstractDemandDetail setCases(Cases cases) {
		this.cases = cases;
		return this;
	}

	public AbstractDemandDetail setInteractionContent(InteractionContent interactionContent) {
		this.interactionContent = interactionContent;
		return this;
	}

	/**
	 * id
	 * 
	 * @return
	 */
	public abstract Object getId();

	/**
	 * 案件编号
	 * 
	 * @return
	 */
	public String getAjbh() {
		return cases == null ? null : cases.getAjbh();
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
		return cases == null ? "" : StringUtils.isNotBlank(cases.getAjstate()) ? cases.getAjstate() : "";
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

	public static CaseParameter parameter() {
		return paramHolder;
	}

	private static CaseParameter paramHolder = new CaseParameter();

	public static class CaseParameter extends CommonParameter {
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
	}

	/**
	 * 名称
	 * 
	 * @return
	 */
	public abstract String getXqmc();

	/**
	 * 内容
	 * 
	 * @return
	 */
	public abstract String getXqnr();

	/**
	 * 主办人警号
	 * 
	 * @return
	 */
	public abstract String getQqry();

	/**
	 * 发起方人员姓名
	 * 
	 * @return
	 */
	public abstract String getLrrymc();

	/**
	 * 录入时间
	 * 
	 * @return
	 */
	public abstract String getLrsj();

	/**
	 * 内容类型
	 * 
	 * @return
	 */
	public abstract String getContentType();

	/**
	 * 互动内容列表
	 * 
	 * @return
	 */
	public List getContentList() {
		return interactionContent == null ? null : interactionContent.getContentList();
	}

	/**
	 * 附件列表
	 * 
	 * @return
	 */
	public List<Map<String, Object>> getAttacments() {
		return this.attacments;
	}

	public AbstractDemandDetail setAttacments(List<Map<String, Object>> attacments) {
		this.attacments = attacments;
		return this;
	}

	@Override
	public String fromUser() {
		return null;
	}
}
