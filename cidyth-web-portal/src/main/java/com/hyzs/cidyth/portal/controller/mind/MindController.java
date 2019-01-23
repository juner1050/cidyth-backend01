package com.hyzs.cidyth.portal.controller.mind;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyzs.cidyth.common.helper.ContextUserHelper;
import com.hyzs.cidyth.common.utils.FileUtil;
import com.hyzs.cidyth.common.utils.HttpUtil;
import com.hyzs.cidyth.module.uc.vo.User;
import org.apache.commons.collections.CollectionUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hyzs.cidyth.module.mind.service.MindService;
import com.hyzs.cidyth.module.mind.vo.MindVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.spring.web.json.Json;

/**
 * Created by Administrator on 2018/4/10 0010.
 */
@Api(tags={"思维导图接口"})
@RestController
@RequestMapping("mind")
public class MindController {
	
	private static final Logger logger = LoggerFactory.getLogger(MindController.class);

	@Autowired
	private MindService mindService;

    /**
     * 思维导图获取
     * @author 陈铭
     * @date 2018-05-09 19:58:41
     * @param mindVO 思维导图对象
     * @return java.util.List<com.hyzs.cidyth.module.demand.vo.DemandVO>
     */
    @GetMapping(value = "list")
    @ApiOperation(value = "思维导图获取", httpMethod = "GET", response = MindVO.class, notes = "思维导图获取")
    public Map<String, Object> list(@RequestParam String ajbh){
        return mindService.listMindByAjbh(ajbh);
    }

    /**
     * 思维导图保存
     * @author 陈铭
     * @date 2018-05-09 19:58:41
     * @param mindVO 思维导图对象
     * @return java.util.List<com.hyzs.cidyth.module.demand.vo.DemandVO>
     */
    @PostMapping(value = "insert")
    @ApiOperation(value = "思维导图保存", httpMethod = "POST", response = MindVO.class, notes = "思维导图保存")
    public void insert(MindVO mindVO){
        mindService.saveMind(mindVO);
    }

    @GetMapping(value = "session")
    @ApiOperation(value = "测试session", httpMethod = "GET", notes = "测试session123")
    public void session(){
        String url = "http://api.map.baidu.com/telematics/v3/weather?location=长春&ak=8IoIaU655sQrs95uMWRWPDIa";
        HttpUtil http = new HttpUtil();
        try {
            String result = http.get(url);
            try {
                Document doc = DocumentHelper.parseText(result);
                Element resultsElement = doc.getRootElement().element("results");
                Element weatherElement = resultsElement.element("weather_data");
                /*System.out.println("date = " + weatherElement.element("date").getText());
                System.out.println("date = " + weatherElement.element("dayPictureUrl").getText());
                System.out.println("date = " + weatherElement.element("nightPictureUrl").getText());
                System.out.println("date = " + weatherElement.element("weather").getText());
                System.out.println("date = " + weatherElement.element("wind").getText());
                System.out.println("date = " + weatherElement.element("temperature").getText());*/
                List<Element> lsElementWeather = weatherElement.elements();
                if(CollectionUtils.isNotEmpty(lsElementWeather)){
                    for (Element element : lsElementWeather) {
                        System.out.println(element.getText());
                    }
                }
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
