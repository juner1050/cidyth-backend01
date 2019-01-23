package com.hyzs.cidyth.module.reply.vo;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.hyzs.cidyth.common.utils.DateUtil;

/**
 * 抽象的互动内容模型(bbs)
 * 
 * @author jidw
 *
 */
public abstract class InteractionContent implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4463588880301413475L;
	
	/**
	 * 默认互动时间字段名称
	 */
	protected static final String DEFALUT_INTERACTION_TIME_FIELD = "date";
	/**
	 * 默认互动时间格式
	 */
	protected static final String DEFAULT_INTERACTION_TIME_FORMAT = DateUtil.Y_M_D_H_M_S;


	/**
	 * 互动内容列表
	 * 
	 * @return
	 */
	public abstract List<Map<String, Object>> getContentList();

	/**
	 * 互动时间字段
	 * 
	 * @return
	 */
	protected static String getInteractionTimeFieldName() {
		return DEFALUT_INTERACTION_TIME_FIELD;
	}

	/**
	 * 互动时间格式
	 * 
	 * @return
	 */
	protected static String getInteractionTimeFormat() {
		return DEFAULT_INTERACTION_TIME_FORMAT;
	}
	/**
	 * 升序排序
	 * @param map1
	 * @param map2
	 * @return
	 */
	protected int ASC(Map<String, Object> map1, Map<String, Object> map2) {
		if (map1 == null && map2 != null) {
			return -1;
		} else if (map1 != null && map2 == null) {
			return 1;
		} else if (map1 == null && map2 == null) {
			return 0;
		} else {
			Object d1 = map1.get(getInteractionTimeFieldName());
			Object d2 = map2.get(getInteractionTimeFieldName());
			if (d1 == null && d2 != null) {
				return -1;
			} else if (d1 != null && d2 == null) {
				return 1;
			} else if (d1 == null && d2 == null) {
				return 0;
			} else {
				try {
					Date date1 = DateUtil.parseToDate((String) d1, getInteractionTimeFormat());
					Date date2 = DateUtil.parseToDate((String) d2, getInteractionTimeFormat());
					return date1.before(date2) ? -1 : (date1.equals(date2) ? 0 : 1);
				} catch (Exception e) {
					e.printStackTrace();
					return 0;
				}
			}
		}
	}
	/**
	 * 降序排序
	 * @param map1
	 * @param map2
	 * @return
	 */
	protected int DESC(Map<String, Object> map1, Map<String, Object> map2) {
		if (map1 == null && map2 != null) {
			return 1;
		} else if (map1 != null && map2 == null) {
			return -1;
		} else if (map1 == null && map2 == null) {
			return 0;
		} else {
			Object d1 = map1.get(getInteractionTimeFieldName());
			Object d2 = map2.get(getInteractionTimeFieldName());
			if (d1 == null && d2 != null) {
				return 1;
			} else if (d1 != null && d2 == null) {
				return -1;
			} else if (d1 == null && d2 == null) {
				return 0;
			} else {
				try {
					Date date1 = DateUtil.parseToDate((String) d1, getInteractionTimeFormat());
					Date date2 = DateUtil.parseToDate((String) d2, getInteractionTimeFormat());
					return date1.before(date2) ? 1 : (date1.equals(date2) ? 0 : -1);
				} catch (Exception e) {
					e.printStackTrace();
					return 0;
				}
			}
		}
	}
}
