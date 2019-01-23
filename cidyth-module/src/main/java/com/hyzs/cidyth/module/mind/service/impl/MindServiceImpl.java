package com.hyzs.cidyth.module.mind.service.impl;

import com.hyzs.cidyth.common.enums.ComparisonFromEnum;
import com.hyzs.cidyth.common.enums.TimeNodeEnum;
import com.hyzs.cidyth.module.base.dao.CasesComparisonMapper;
import com.hyzs.cidyth.module.base.dao.CasesMapper;
import com.hyzs.cidyth.module.base.entity.Cases;
import com.hyzs.cidyth.module.base.entity.CasesComparison;
import com.hyzs.cidyth.module.clue.dao.ClueMapper;
import com.hyzs.cidyth.module.clue.entity.Clue;
import com.hyzs.cidyth.module.demand.dao.DemandMapper;
import com.hyzs.cidyth.module.demand.entity.Demand;
import com.hyzs.cidyth.module.mind.dao.MindMapper;
import com.hyzs.cidyth.module.mind.entity.Mind;
import com.hyzs.cidyth.module.mind.service.MindService;
import com.hyzs.cidyth.module.mind.vo.MindVO;
import com.hyzs.cidyth.module.msg.dao.InfoMapper;
import com.hyzs.cidyth.module.msg.entity.Info;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.tools.ant.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("MindService")
public class MindServiceImpl implements MindService {

	private static final Logger logger = LoggerFactory.getLogger(MindServiceImpl.class);

	@Autowired
	private MindMapper mindMapper;
	@Autowired
	private CasesMapper casesMapper;
	@Autowired
	private DemandMapper demandMapper;
	@Autowired
	private ClueMapper clueMapper;
	@Autowired
	private InfoMapper infoMapper;
	@Autowired
	private CasesComparisonMapper casesComparisonMapper;
	
	/**
     * 插入思维导图表
     * @author 陈铭
     * @date 2018-04-16 17:21:53
     * @param ajbh 案件编号
     * @param sourceId 源id
     * @param targetId 目标id
     * @param sourceType 源类型
     * @param targetType 目标类型
     * @return 
     */
	public void insert(String ajbh, int sourceId, int targetId, String sourceType, String targetType) {
		Mind mind = new Mind();
		mind.setAjbh(ajbh);
		mind.setSourceType(sourceType);
		mind.setSourceId(sourceId);
		mind.setTargetType(targetType);
		mind.setTargetId(targetId);
		mindMapper.insertSelective(mind);
	}

	@Override
	public Map<String, Object> listMindByAjbh(String ajbh) {

		// 存储nodes对象
		List<MindVO> lsNodes = new ArrayList<>();
		// 存储edges对象
		List<Map<String, String>> lsEdges = new ArrayList<>();
	
		// 获取案件
		Cases casesParam = new Cases();
		casesParam.setAjbh(ajbh);
		Cases cases = casesMapper.selectOne(casesParam);
		// 获取需求
		Demand demandParam = new Demand();
		demandParam.setAjbh(ajbh);
		List<Demand> lsDemand = demandMapper.select(demandParam);
		// 获取反馈线索
		Clue returnClueParam = new Clue();
		returnClueParam.setAjbh(ajbh);
		List<Clue> lsReturnClue = clueMapper.select(returnClueParam);
		// 获取信息
		Info infoParam = new Info();
		infoParam.setAjbh(ajbh);
		List<Info> lsInfo = infoMapper.select(infoParam);

		CasesComparison paramCasesComparison = new CasesComparison();
		paramCasesComparison.setAjbh(ajbh);
		List<CasesComparison> lsCasesComparison = casesComparisonMapper.select(paramCasesComparison);

		String datePattern = "yyyy-MM-dd HH:mm:ss";
		
		// id=枚举名+横杠+主键
		MindVO mindVO = null;
		// 添加案件
		mindVO = new MindVO();
		mindVO.setId(TimeNodeEnum.CASE.name() + "-" + cases.getId());
		mindVO.setTitle(cases.getAjmc());
		mindVO.setContent(cases.getZyaq());
		mindVO.setType(TimeNodeEnum.CASE.name());
		mindVO.setTime(DateUtils.format(cases.getLrsj(), datePattern));
		mindVO.setTop(cases.getMindTop());
		mindVO.setLeft(cases.getMindLeft());
		lsNodes.add(mindVO);
		// 添加需求
		if(CollectionUtils.isNotEmpty(lsDemand)){
			for(Demand demand : lsDemand){
				mindVO = new MindVO();
				mindVO.setId(TimeNodeEnum.DEMAND.name() + "-" + demand.getId());
				mindVO.setTitle(demand.getXqmc());
				mindVO.setContent(demand.getXqnr());
				mindVO.setType(TimeNodeEnum.DEMAND.name());
				mindVO.setTime(DateUtils.format(demand.getLrsj(), datePattern));
				mindVO.setTop(demand.getMindTop());
				mindVO.setLeft(demand.getMindLeft());
				lsNodes.add(mindVO);
			}
		}
		// 添加反馈线索
		if(CollectionUtils.isNotEmpty(lsReturnClue)){
			for(Clue returnClue : lsReturnClue){
				mindVO = new MindVO();
				mindVO.setId(TimeNodeEnum.CLUE.name() + "-" + returnClue.getId());
				mindVO.setTitle(returnClue.getTheme());
				mindVO.setContent(returnClue.getXsnr());
				mindVO.setType(TimeNodeEnum.CLUE.name());
				mindVO.setTime(DateUtils.format(returnClue.getLrsj(), datePattern));
				mindVO.setTop(returnClue.getMindTop());
				mindVO.setLeft(returnClue.getMindLeft());
				lsNodes.add(mindVO);
			}
		}
		// 添加信息
		if(CollectionUtils.isNotEmpty(lsInfo)){
			for(Info info : lsInfo){
				mindVO = new MindVO();
				mindVO.setId(TimeNodeEnum.INFO.name() + "-" + info.getId());
				mindVO.setTitle(info.getXxzt());
				mindVO.setContent(info.getFbnr());
				mindVO.setType(TimeNodeEnum.INFO.name());
				mindVO.setTime(DateUtils.format(info.getLrsj(), datePattern));
				mindVO.setTop(info.getMindTop());
				mindVO.setLeft(info.getMindLeft());
				lsNodes.add(mindVO);
			}
		}
		// 添加比中信息
		if(CollectionUtils.isNotEmpty(lsCasesComparison)){
			for(CasesComparison casesComparison : lsCasesComparison){
				mindVO = new MindVO();
				mindVO.setId(TimeNodeEnum.COMPARISON_INFO.name() + "-" + casesComparison.getId());
				mindVO.setTitle(ComparisonFromEnum.ofCode(casesComparison.getBzly()).descp());
				mindVO.setContent(casesComparison.getXm() + "：" + casesComparison.getZjhm());
				mindVO.setType(TimeNodeEnum.COMPARISON_INFO.name());
				mindVO.setTime(casesComparison.getBzsj());
				lsNodes.add(mindVO);
			}
		}
		
		// 根据案件编号获取思维导图的edges的关系
		Mind mind = new Mind();
		mind.setAjbh(ajbh);
		List<Mind> lsMind = mindMapper.select(mind);
		
		// 构造edges对象
		if(CollectionUtils.isNotEmpty(lsMind)){
			for(Mind edge : lsMind){
				Map<String, String> mapEdge = new HashMap<>();
				// id=枚举名+横杠+主键
				String source = edge.getSourceType() + "-" + edge.getSourceId();
				String target = edge.getTargetType() + "-" + edge.getTargetId();
				mapEdge.put("source", source);
				mapEdge.put("target", target);
				lsEdges.add(mapEdge);
			}
		}
		
		Map<String, Object> mapResult = new HashMap<>();
		mapResult.put("nodes", lsNodes);
		mapResult.put("edges", lsEdges);
		
		return mapResult;
	}

	@Override
	public void saveMind(MindVO mindVO) {
		if(StringUtils.isNotEmpty(mindVO.getId())){
			String[] mindId = mindVO.getId().split("-");
			String enumName = mindId[0];
			int actualId = Integer.parseInt(mindId[1]);
			if(TimeNodeEnum.CASE.name().equals(enumName)){// 修改案件的思维导图坐标
				Cases cases = new Cases();
				cases.setId(actualId);
				cases.setMindTop(mindVO.getTop());
				cases.setMindLeft(mindVO.getLeft());
				casesMapper.updateByPrimaryKeySelective(cases);
			}else if(TimeNodeEnum.DEMAND.name().equals(enumName)){// 修改需求的思维导图坐标
				Demand demand = new Demand();
				demand.setId(actualId);
				demand.setMindTop(mindVO.getTop());
				demand.setMindLeft(mindVO.getLeft());
				demandMapper.updateByPrimaryKeySelective(demand);
			}else if(TimeNodeEnum.CLUE.name().equals(enumName)){// 修改上传线索的思维导图坐标
				Clue clue = new Clue();
				clue.setId(actualId);
				clue.setMindTop(mindVO.getTop());
				clue.setMindLeft(mindVO.getLeft());
				clueMapper.updateByPrimaryKeySelective(clue);
			}else if(TimeNodeEnum.INFO.name().equals(enumName)){// 修改信息的思维导图坐标
				Info info = new Info();
				info.setId(actualId);
				info.setMindTop(mindVO.getTop());
				info.setMindLeft(mindVO.getLeft());
				infoMapper.updateByPrimaryKeySelective(info);
			}else{
				
			}
		}
	}
	
}
