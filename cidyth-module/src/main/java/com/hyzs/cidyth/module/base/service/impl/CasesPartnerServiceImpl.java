package com.hyzs.cidyth.module.base.service.impl;

import java.util.List;

import com.hyzs.cidyth.module.uc.common.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyzs.cidyth.common.helper.ContextUserHelper;
import com.hyzs.cidyth.common.page.Page;
import com.hyzs.cidyth.core.exception.ServiceException;
import com.hyzs.cidyth.module.base.dao.CasesPartnerMapper;
import com.hyzs.cidyth.module.base.entity.CasesPartner;
import com.hyzs.cidyth.module.base.service.CasesPartnerService;
import com.hyzs.cidyth.module.uc.vo.User;


/**
 * Created by Administrator on 2018/4/10 0010.
 */
@Service("CasesPartnerService")
public class CasesPartnerServiceImpl implements CasesPartnerService {

    private static final Logger logger = LoggerFactory.getLogger(CasesPartnerServiceImpl.class);

    @Autowired
    private CasesPartnerMapper casesPartnerMapper;

    @Override
    public List<CasesPartner> listCasesPartnerByMonth(Page page) {
        User loginUser = UserUtil.getUser();
        ;
        if (loginUser == null) {
            throw new ServiceException("无法获取登录用户");
        }
        return null;
    }

    @Override
    public int countCasesJoinPerson(String ajbh) {
        CasesPartner casesPartner = new CasesPartner();
        casesPartner.setAjbh(ajbh);
        return casesPartnerMapper.selectCount(casesPartner);
    }

}
