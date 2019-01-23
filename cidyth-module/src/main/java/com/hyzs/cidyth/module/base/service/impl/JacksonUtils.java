package com.hyzs.cidyth.module.base.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonUtils {
	private final ObjectMapper jsonParser = new ObjectMapper();

	public JacksonUtils() {
		jsonParser.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
		jsonParser.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	/**
	 * 反序列化方法
	 * 
	 * @param nodeList
	 *            json节点列表
	 * @param convertor
	 *            转换器
	 * @return java.util.List<java.util.Map<String,Object>> 列表
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public List<Map<String, Object>> deserailize(JsonNode nodeList, JsonNode2MapConvertor convertor)
			throws JsonParseException, JsonMappingException, IOException {
		if (convertor == null)
			throw new IllegalArgumentException("No JsonNode convertor for jasonParser.");
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		if (null != (nodeList)) {
			Iterator<JsonNode> it = nodeList.elements();
			while (it.hasNext()) {
				JsonNode node = it.next();
				if (node != null) {
					if (convertor != null) {
						Map<String, Object> m = convertor.convert(node);
						if (m != null && !m.isEmpty()) {
							result.add(m);
						}
					}
				}
			}
		}
		return result;
	}

	/**
	 * 反序列化方法
	 * 
	 * @param nodeList
	 *            json节点列表
	 * @param handler
	 *            类型处理器
	 * @return 对象
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public <T> T deserailize(List<JsonNode> nodeList, TypeHandler handler)
			throws JsonParseException, JsonMappingException, IOException {
		if (handler == null)
			throw new IllegalArgumentException("No typeHandler for jasonParser.");
		T result = null;
		if (CollectionUtils.isNotEmpty(nodeList)) {
			result = jsonParser.readValue(nodeList.toString(), handler.process(jsonParser.getTypeFactory()));
		}
		return result;
	}

	/**
	 * 数据转换器
	 * 
	 * @author Administrator
	 *
	 */
	public static interface JsonNode2MapConvertor {
		Map<String, Object> convert(com.fasterxml.jackson.databind.JsonNode node);
	}

	/**
	 * 类型转换器
	 * 
	 * @author Administrator
	 *
	 */
	public static interface TypeHandler {
		JavaType process(com.fasterxml.jackson.databind.type.TypeFactory typeFacory);
	}
}
