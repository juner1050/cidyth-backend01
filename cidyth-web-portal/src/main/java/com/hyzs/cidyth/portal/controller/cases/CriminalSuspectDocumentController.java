package com.hyzs.cidyth.portal.controller.cases;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hyzs.cidyth.module.base.service.CriminalSuspectDocumentInfoService;
import com.hyzs.cidyth.module.base.vo.CriminalSuspectDocument;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = { "嫌疑人档案接口" })
@RestController
@RequestMapping("criminalSuspectDocument")
public class CriminalSuspectDocumentController {
	private static final Logger logger = LoggerFactory.getLogger(CriminalSuspectDocumentController.class);
	@Autowired
	private CriminalSuspectDocumentInfoService criminalSuspectDocumentInfoService;

	@PostMapping(value = "baseInfo")
	@ApiOperation(value = "嫌疑人基本信息", httpMethod = "POST", response = Map.class, notes = "嫌疑人基本信息")
	public Map<String, Object> criminalSuspectBaseInfo(@RequestBody CriminalSuspectDocument csd) throws Exception {
		return criminalSuspectDocumentInfoService.loadCriminalSuspectBaseInfo(csd);
	}

	@PostMapping(value = "criminalHistoryInfo")
	@ApiOperation(value = "犯罪前科", httpMethod = "POST", response = List.class, notes = "犯罪前科")
	public List<Map<String, Object>> criminalHistoryInfo(@RequestBody CriminalSuspectDocument csd) throws Exception {
		return criminalSuspectDocumentInfoService.loadCriminalHistoryInfo(csd).getRight();
	}

	@PostMapping(value = "physicalDistributionInfo")
	@ApiOperation(value = "快递（物流）信息", httpMethod = "POST", response = List.class, notes = "快递（物流）信息")
	public List<Map<String, Object>> physicalDistributionInfo(@RequestBody CriminalSuspectDocument csd) throws Exception {
		return criminalSuspectDocumentInfoService.loadPhysicalDistributionInfo(csd).getRight();
	}

	@PostMapping(value = "relatedPerson")
	@ApiOperation(value = "嫌疑人的关系人", httpMethod = "POST", response = List.class, notes = "嫌疑人的关系人")
	public List<Map<String, Object>> relatedPerson(@RequestBody CriminalSuspectDocument csd) throws Exception {
		return criminalSuspectDocumentInfoService.loadRelatedPerson(csd).getRight();
	}

	@PostMapping(value = "rentalsInfo")
	@ApiOperation(value = "出租屋信息", httpMethod = "POST", response = List.class, notes = "出租屋信息")
	public List<Map<String, Object>> rentalsInfo(@RequestBody CriminalSuspectDocument csd) throws Exception {
		return criminalSuspectDocumentInfoService.loadRentalsInfo(csd).getRight();
	}

	@PostMapping(value = "hotelInfo")
	@ApiOperation(value = "旅馆信息", httpMethod = "POST", response = List.class, notes = "旅馆信息")
	public List<Map<String, Object>> hotelInfo(@RequestBody CriminalSuspectDocument csd) throws Exception {
		return criminalSuspectDocumentInfoService.loadHotelInfo(csd).getRight();
	}

	@PostMapping(value = "entryAndExistInfo")
	@ApiOperation(value = "出入境信息", httpMethod = "POST", response = List.class, notes = "出入境信息")
	public List<Map<String, Object>> entryAndExistInfo(@RequestBody CriminalSuspectDocument csd) throws Exception {
		return criminalSuspectDocumentInfoService.loadEntryAndExistInfo(csd).getRight();
	}

	@PostMapping(value = "travelPath")
	@ApiOperation(value = "出行轨迹", httpMethod = "POST", response = List.class, notes = "出行轨迹")
	public List<Map<String, Object>> travelPath(@RequestBody CriminalSuspectDocument csd) throws Exception {
		return criminalSuspectDocumentInfoService.loadTravelPathInfo(csd).getRight();
	}
}
