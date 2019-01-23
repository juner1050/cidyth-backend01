package com.hyzs.cidyth.module.dic.service.impl;

import java.util.List;

import com.hyzs.cidyth.common.utils.Constant;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.hyzs.cidyth.module.dic.dao.DictionaryMapper;
import com.hyzs.cidyth.module.dic.entity.Dictionary;
import com.hyzs.cidyth.module.dic.service.DicService;

@Service("DicService")
public class DicServiceImpl implements DicService {

	private static final Logger logger = LoggerFactory.getLogger(DicServiceImpl.class);

	@Autowired
	private DictionaryMapper dictionaryMapper;
	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@Override
	public List<Dictionary> listByLxbh(String lxbh) {
		if(StringUtils.isEmpty(lxbh)){
			return null;
		}
		Dictionary dictionary = new Dictionary();
		dictionary.setLxbh(lxbh);
		return dictionaryMapper.select(dictionary);
	}

	@Override
	public List<Dictionary> listByLxbh(String lxbh, String fjzdj) {
		if(StringUtils.isEmpty(fjzdj)){
			return listByLxbh(lxbh);
		}
		Dictionary dictionary = new Dictionary();
		dictionary.setLxbh(lxbh);
		dictionary.setFjzdj(fjzdj);
		return dictionaryMapper.select(dictionary);
	}

	@Override
	public String getValueByKey(String lxbh, String zdj) {
		String result = "";
		if(StringUtils.isEmpty(lxbh) || StringUtils.isEmpty(zdj)){
			return result;
		}
		String[] arr = {};
		if(StringUtils.isNotEmpty(zdj)){
			arr = zdj.split(",");
		}
		if(arr.length > 0){
			StringBuilder stringBuilder = new StringBuilder();
			for(String item : arr){
				String cacheValue = redisTemplate.opsForValue().get(Constant.SystemInfo.WEB_PORTAL.getName() + ":dict:" + lxbh + ":" + zdj);
				if(StringUtils.isEmpty(cacheValue)){
					Dictionary dictionary = new Dictionary();
					dictionary.setLxbh(lxbh);
					dictionary.setZdj(item);
					dictionary = dictionaryMapper.selectOne(dictionary);
					cacheValue = dictionary == null ? "" : dictionary.getZdz();
					if(StringUtils.isNotBlank(cacheValue)){
						redisTemplate.opsForValue().set(Constant.SystemInfo.WEB_PORTAL.getName() + ":dict:" + lxbh + ":" + zdj, cacheValue);
					}
				}
				if(StringUtils.isNotEmpty(cacheValue)){
					stringBuilder.append(cacheValue).append("、");
				}
			}
			if(stringBuilder.length() > 0){
				result = stringBuilder.deleteCharAt(stringBuilder.length() - 1).toString();
			}
		}
		return result;
	}
}
