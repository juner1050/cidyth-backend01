package com.hyzs.cidyth.module.cases.vo;


import com.hyzs.cidyth.module.base.entity.CasesMegerVO;

import java.util.List;

/**
 * Created by Administrator on 2018/4/10 0010.
 */
public class AlarmSituationVO {

	/**
	 * 案件编号
	 */
	private String ajbh;
	/**
	 * 受理接收单位
	 */
	private String sljsdw;
	/**
	 * 受理接收单位名称
	 */
	private String sljsdwCn;
	/**
	 * 警综案件状态
	 */
	private String ajstate;
	/**
	 * 本地案件状态
	 */
	private String bdajstate;
	/**
	 * 本地案件状态名称
	 */
	private String bdajstateCn;
	/**
	 * 案件发生时间
	 */
	private String fasjcz;
	/**
	 * 案件结束时间
	 */
	private String fasjzz;
	/**
	 * 作案状态
	 */
	private String zazt;
	/**
	 * 作案状态名称
	 */
	private String zaztCn;
	/**
	 * 报警受理号
	 */
	private String slBjslh;
	/**
	 * 接警方式
	 */
	private String slJjfs;
	/**
	 * 接警方式
	 */
	private String slJjfsCn;
	/**
	 * 案件类型
	 */
	private String ajlx;
	/**
	 * 案件类型名称
	 */
	private String ajlxCn;
	/**
	 * 案别
	 */
	private String ab;
	/**
	 * 案别名称
	 */
	private String abCn;
	/**
	 * 专案标识
	 */
	private String zabz;
	/**
	 * 专案标识名称
	 */
	private String zabzCn;
	/**
	 * 案件名称
	 */
	private String ajmc;
	/**
	 * 案件危害
	 */
	private String ajwh;
	/**
	 * 受理接警时间
	 */
	private String sljjsj;
	/**
	 * 发案地点区县
	 */
	private String faddQx;
	/**
	 * 发案地点区县
	 */
	private String faddQxCn;
	/**
	 * 发案地点街道
	 */
	private String faddJd;
	/**
	 * 发案地点街道
	 */
	private String faddJdCn;
	/**
	 * 案件所属警区
	 */
	private String ajssjq;
	/**
	 * 案件所属警区
	 */
	private String ajssjqCn;
	/**
	 * 所属社区
	 */
	private String sssq;
	/**
	 * 所属社区
	 */
	private String sssqCn;
	/**
	 * 发案地点
	 */
	private String fadd;
	/**
	 * 发案地域
	 */
	private String fady;
	/**
	 * 发案地域
	 */
	private String fadyCn;
	/**
	 * 发案城市
	 */
	private String slfacs;
	/**
	 * 发现形式
	 */
	private String fxxs;
	/**
	 * 发现形式
	 */
	private String fxxsCn;
	/**
	 * 案件危害程度
	 */
	private String ajwhcd;
	/**
	 * 案件危害程度
	 */
	private String ajwhcdCn;
	/**
	 * 补立原因
	 */
	private String blyy;
	/**
	 * 主要案情
	 */
	private String zyaq;
	/**
	 * 选择时机
	 */
	private String xzsj;
	/**
	 * 选择时机名称
	 */
	private String xzsjCn;
	/**
	 * 选择处所
	 */
	private String xzcs;
	/**
	 * 选择处所名称
	 */
	private String xzcsCn;
	/**
	 * 选择对象
	 */
	private String xzdx;
	/**
	 * 选择对象名称
	 */
	private String xzdxCn;
	/**
	 * 选择物品
	 */
	private String xzwp;
	/**
	 * 选择物品名称
	 */
	private String xzwpCn;
	/**
	 * 作案工具
	 */
	private String zagj;
	/**
	 * 作案工具名称
	 */
	private String zagjCn;
	/**
	 * 选择部位
	 */
	private String xzbw;
	/**
	 * 选择部位名称
	 */
	private String xzbwCn;
	/**
	 * 手段特点
	 */
	private String sdtd;
	/**
	 * 手段特点名称
	 */
	private String sdtdCn;
	/**
	 * 作案人数
	 */
	private String zars;
	/**
	 * 死亡人数
	 */
	private String swrs;
	/**
	 * 受伤人数
	 */
	private String ssrs;
	/**
	 * 损失价值
	 */
	private String ssjz;
	/**
	 * 立案时间
	 */
	private String lasj;
	/**
	 * 挽回损失金额
	 */
	private String whssjz;
	/**
	 * 涉案主体
	 */
	private String sazz;
	/**
	 * 涉案主体名称
	 */
	private String sazzCn;
	/**
	 * 督办级别
	 */
	private String dbjb;
	/**
	 * 督办级别名称
	 */
	private String dbjbCn;
	/**
	 * 保密级别
	 */
	private String securitygrade;
	/**
	 * 保密级别名称
	 */
	private String securitygradeCn;
	/**
	 * 立案单位
	 */
	private String ladw;
	/**
	 * 立案单位名称
	 */
	private String ladwCn;
	/**
	 * 立案人员
	 */
	private String ajlary;
	/**
	 * 立案人员名称
	 */
	private String ajlaryCn;
	/**
	 * 主办单位
	 */
	private String zbdw;
	/**
	 * 主办单位名称
	 */
	private String zbdwCn;
	/**
	 * 案件主办人员（警号）
	 */
	private String ajzbry;
	/**
	 * 案件主办人员名称
	 */
	private String ajzbryCn;
	/**
	 * 案件协办人员（多个警号逗号分隔）
	 */
	private String ajxbry;
	/**
	 * 案件协办人员名称
	 */
	private String ajxbryCn;
	/**
	 * 备注
	 */
	private String bz;
	/**
	 * 犯罪主体类型
	 */
	private String fzztlx;
	/**
	 * 犯罪主体类型名称
	 */
	private String fzztlxCn;
	/**
	 * 是否涉外
	 */
	private String sfsw;
	/**
	 * 是否涉外名称
	 */
	private String sfswCn;
	/**
	 * 涉及国家地区
	 */
	private String sjgjdq;
	/**
	 * 涉及国家地区名称
	 */
	private String sjgjdqCn;
	/**
	 * 案件来源类型（1、从警综提取，2、手动录入案件）
	 */
	private Integer ajFrom;
	/**
	 * 案件来源类型名称（1、从警综提取，2、手动录入案件）
	 */
	private String ajFromCn;
	/**
	 * 案件开始时间
	 */
	//private String beginCreateTime;

	/**
	 * 案件结束时间
	 */
	//private String endCreateTime;

	/**
	 * 时间段：三天内、三十天内、上个月、本年、去年
	 * 根据该字段的值确定如何获取时间段来查询
	 */
	private String timeSection;

	/**
	 * 该案件的需求数量
	 */
	private Integer demandCount;

	/**
	 * 该案件的线索数量
	 */
	private Integer clueCount;

	/**
	 * 该案件的信息数量
	 */
	private Integer infoCount;

	/**
	 * 本地案件状态备注
	 */
	private String bdajstatebz;

	/**
	 * 思维导图上坐标
	 */
	private String mindTop;

	/**
	 * 思维导图左坐标
	 */
	private String mindLeft;

	/**
	 * 案件主办状态（列表查询条件）：我主办、其他、全部
	 */
	private String caseSponsor;

	/**
	 * 是否串并案
	 */
	private Integer sfcba;

	/**
	 * 串并案编号
	 */
	private Integer cbabh;
	
	/**
	 * 串并案名称
	 */
	private List<CasesMegerVO> lsCasesMegerVO;
	
	/**
	 * 登录人的机构
	 */
	private String userDeptCode;

	/**
	 * 立案时间：开始
	 */
	private String beginLasj;

	/**
	 * 立案时间：结束
	 */
	private String endLasj;
	
	public String getUserDeptCode() {
		return userDeptCode;
	}

	public void setUserDeptCode(String userDeptCode) {
		this.userDeptCode = userDeptCode;
	}

	/**
	 * 案件编号
	 * @return
	 */
	public String getAjbh() {
		return ajbh;
	}

	/**
	 * 案件编号
	 * @param ajbh
	 */
	public void setAjbh(String ajbh) {
		this.ajbh = ajbh;
	}

	/**
	 * 受理接收单位
	 * @return
	 */
	public String getSljsdw() {
		return sljsdw;
	}

	/**
	 * 受理接收单位
	 * @param sljsdw
	 */
	public void setSljsdw(String sljsdw) {
		this.sljsdw = sljsdw;
	}

	/**
	 * 受理接收单位名称
	 * @return
	 */
	public String getSljsdwCn() {
		return sljsdwCn;
	}

	/**
	 * 受理接收单位名称
	 * @param sljsdwCn
	 */
	public void setSljsdwCn(String sljsdwCn) {
		this.sljsdwCn = sljsdwCn;
	}

	/**
	 * 本地案件状态
	 * @return
	 */
	public String getBdajstate() {
		return bdajstate;
	}

	/**
	 * 本地案件状态
	 * @param bdajstate
	 */
	public void setBdajstate(String bdajstate) {
		this.bdajstate = bdajstate;
	}

	/**
	 * 本地案件状态名称
	 * @return
	 */
	public String getBdajstateCn() {
		return bdajstateCn;
	}

	/**
	 * 本地案件状态名称
	 * @param bdajstateCn
	 */
	public void setBdajstateCn(String bdajstateCn) {
		this.bdajstateCn = bdajstateCn;
	}

	/**
	 * 作案状态
	 * @return
	 */
	public String getZazt() {
		return zazt;
	}

	/**
	 * 作案状态
	 * @param zazt
	 */
	public void setZazt(String zazt) {
		this.zazt = zazt;
	}

	/**
	 * 作案状态名称
	 * @return
	 */
	public String getZaztCn() {
		return zaztCn;
	}

	/**
	 * 作案状态名称
	 * @param zaztCn
	 */
	public void setZaztCn(String zaztCn) {
		this.zaztCn = zaztCn;
	}

	/**
	 * 受理报警受理号
	 * @return
	 */
	public String getSlBjslh() {
		return slBjslh;
	}

	/**
	 * 受理报警受理号
	 * @param slBjslh
	 */
	public void setSlBjslh(String slBjslh) {
		this.slBjslh = slBjslh;
	}

	/**
	 * 受理接警方式
	 * @return
	 */
	public String getSlJjfs() {
		return slJjfs;
	}

	/**
	 * 受理接警方式
	 * @param slJjfs
	 */
	public void setSlJjfs(String slJjfs) {
		this.slJjfs = slJjfs;
	}

	/**
	 * 受理接警方式名称
	 * @return
	 */
	public String getSlJjfsCn() {
		return slJjfsCn;
	}

	/**
	 * 受理接警方式名称
	 * @param slJjfsCn
	 */
	public void setSlJjfsCn(String slJjfsCn) {
		this.slJjfsCn = slJjfsCn;
	}

	/**
	 * 案件类型
	 * @return
	 */
	public String getAjlx() {
		return ajlx;
	}

	/**
	 * 案件类型
	 * @param ajlx
	 */
	public void setAjlx(String ajlx) {
		this.ajlx = ajlx;
	}

	/**
	 * 案件类型名称
	 * @return
	 */
	public String getAjlxCn() {
		return ajlxCn;
	}

	/**
	 * 案件类型名称
	 * @param ajlxCn
	 */
	public void setAjlxCn(String ajlxCn) {
		this.ajlxCn = ajlxCn;
	}

	/**
	 * 案别
	 * @return
	 */
	public String getAb() {
		return ab;
	}

	/**
	 * 案别
	 * @param ab
	 */
	public void setAb(String ab) {
		this.ab = ab;
	}

	/**
	 * 案别名称
	 * @return
	 */
	public String getAbCn() {
		return abCn;
	}

	/**
	 * 案别名称
	 * @param abCn
	 */
	public void setAbCn(String abCn) {
		this.abCn = abCn;
	}

	/**
	 * 专案标识
	 * @return
	 */
	public String getZabz() {
		return zabz;
	}

	/**
	 * 专案标识
	 * @param zabz
	 */
	public void setZabz(String zabz) {
		this.zabz = zabz;
	}

	/**
	 * 专案标识名称
	 * @return
	 */
	public String getZabzCn() {
		return zabzCn;
	}

	/**
	 * 专案标识名称
	 * @param zabzCn
	 */
	public void setZabzCn(String zabzCn) {
		this.zabzCn = zabzCn;
	}

	/**
	 * 案件名称
	 * @return
	 */
	public String getAjmc() {
		return ajmc;
	}

	/**
	 * 案件名称
	 * @param ajmc
	 */
	public void setAjmc(String ajmc) {
		this.ajmc = ajmc;
	}

	/**
	 * 案件危害
	 * @return
	 */
	public String getAjwh() {
		return ajwh;
	}

	/**
	 * 案件危害
	 * @param ajwh
	 */
	public void setAjwh(String ajwh) {
		this.ajwh = ajwh;
	}

	/**
	 * 受理接警时间
	 * @return
	 */
	public String getSljjsj() {
		return sljjsj;
	}

	/**
	 * 受理接警时间
	 * @param sljjsj
	 */
	public void setSljjsj(String sljjsj) {
		this.sljjsj = sljjsj;
	}

	/**
	 * 发案地点区县
	 * @return
	 */
	public String getFaddQx() {
		return faddQx;
	}

	/**
	 * 发案地点区县
	 * @param faddQx
	 */
	public void setFaddQx(String faddQx) {
		this.faddQx = faddQx;
	}

	/**
	 * 发案地点区县名称
	 * @return
	 */
	public String getFaddQxCn() {
		return faddQxCn;
	}

	/**
	 * 发案地点区县名称
	 * @param faddQxCn
	 */
	public void setFaddQxCn(String faddQxCn) {
		this.faddQxCn = faddQxCn;
	}

	/**
	 * 发案地点街道
	 * @return
	 */
	public String getFaddJd() {
		return faddJd;
	}

	/**
	 * 发案地点街道
	 * @param faddJd
	 */
	public void setFaddJd(String faddJd) {
		this.faddJd = faddJd;
	}

	/**
	 * 发案地点街道名称
	 * @return
	 */
	public String getFaddJdCn() {
		return faddJdCn;
	}

	/**
	 * 发案地点街道名称
	 * @param faddJdCn
	 */
	public void setFaddJdCn(String faddJdCn) {
		this.faddJdCn = faddJdCn;
	}

	/**
	 * 案件所属警区
	 * @return
	 */
	public String getAjssjq() {
		return ajssjq;
	}

	/**
	 * 案件所属警区
	 * @param ajssjq
	 */
	public void setAjssjq(String ajssjq) {
		this.ajssjq = ajssjq;
	}

	/**
	 * 案件所属警区名称
	 * @return
	 */
	public String getAjssjqCn() {
		return ajssjqCn;
	}

	/**
	 * 案件所属警区名称
	 * @param ajssjqCn
	 */
	public void setAjssjqCn(String ajssjqCn) {
		this.ajssjqCn = ajssjqCn;
	}

	/**
	 * 所属社区
	 * @return
	 */
	public String getSssq() {
		return sssq;
	}

	/**
	 * 所属社区
	 * @param sssq
	 */
	public void setSssq(String sssq) {
		this.sssq = sssq;
	}

	/**
	 * 所属社区名称
	 * @return
	 */
	public String getSssqCn() {
		return sssqCn;
	}

	/**
	 * 所属社区名称
	 * @param sssqCn
	 */
	public void setSssqCn(String sssqCn) {
		this.sssqCn = sssqCn;
	}

	/**
	 * 发案地点
	 * @return
	 */
	public String getFadd() {
		return fadd;
	}

	/**
	 * 发案地点
	 * @param fadd
	 */
	public void setFadd(String fadd) {
		this.fadd = fadd;
	}

	/**
	 * 发案地域
	 * @return
	 */
	public String getFady() {
		return fady;
	}

	/**
	 * 发案地域
	 * @param fady
	 */
	public void setFady(String fady) {
		this.fady = fady;
	}

	/**
	 * 发案地域名称
	 * @return
	 */
	public String getFadyCn() {
		return fadyCn;
	}

	/**
	 * 发案地域名称
	 * @param fadyCn
	 */
	public void setFadyCn(String fadyCn) {
		this.fadyCn = fadyCn;
	}

	/**
	 * 受理发案城市
	 * @return
	 */
	public String getSlfacs() {
		return slfacs;
	}

	/**
	 * 受理发案城市
	 * @param slfacs
	 */
	public void setSlfacs(String slfacs) {
		this.slfacs = slfacs;
	}

	/**
	 * 发现形式
	 * @return
	 */
	public String getFxxs() {
		return fxxs;
	}

	/**
	 * 发现形式
	 * @param fxxs
	 */
	public void setFxxs(String fxxs) {
		this.fxxs = fxxs;
	}

	/**
	 * 发现形式名称
	 * @return
	 */
	public String getFxxsCn() {
		return fxxsCn;
	}

	/**
	 * 发现形式名称
	 * @param fxxsCn
	 */
	public void setFxxsCn(String fxxsCn) {
		this.fxxsCn = fxxsCn;
	}

	/**
	 * 案件危害程度
	 * @return
	 */
	public String getAjwhcd() {
		return ajwhcd;
	}

	/**
	 * 案件危害程度
	 * @param ajwhcd
	 */
	public void setAjwhcd(String ajwhcd) {
		this.ajwhcd = ajwhcd;
	}

	/**
	 * 案件危害程度名称
	 * @return
	 */
	public String getAjwhcdCn() {
		return ajwhcdCn;
	}

	/**
	 * 案件危害程度名称
	 * @param ajwhcdCn
	 */
	public void setAjwhcdCn(String ajwhcdCn) {
		this.ajwhcdCn = ajwhcdCn;
	}

	/**
	 * 补立原因
	 * @return
	 */
	public String getBlyy() {
		return blyy;
	}

	/**
	 * 补立原因
	 * @param blyy
	 */
	public void setBlyy(String blyy) {
		this.blyy = blyy;
	}

	/**
	 * 主要案情
	 * @return
	 */
	public String getZyaq() {
		return zyaq;
	}

	/**
	 * 主要案情
	 * @param zyaq
	 */
	public void setZyaq(String zyaq) {
		this.zyaq = zyaq;
	}

	/**
	 * 选择时机
	 * @return
	 */
	public String getXzsj() {
		return xzsj;
	}

	/**
	 * 选择时机
	 * @param xzsj
	 */
	public void setXzsj(String xzsj) {
		this.xzsj = xzsj;
	}

	/**
	 * 选择时机名称
	 * @return
	 */
	public String getXzsjCn() {
		return xzsjCn;
	}

	/**
	 * 选择时机名称
	 * @param xzsjCn
	 */
	public void setXzsjCn(String xzsjCn) {
		this.xzsjCn = xzsjCn;
	}

	/**
	 * 选择处所
	 * @return
	 */
	public String getXzcs() {
		return xzcs;
	}

	/**
	 * 选择处所
	 * @param xzcs
	 */
	public void setXzcs(String xzcs) {
		this.xzcs = xzcs;
	}

	/**
	 * 选择处所名称
	 * @return
	 */
	public String getXzcsCn() {
		return xzcsCn;
	}

	/**
	 * 选择处所名称
	 * @param xzcsCn
	 */
	public void setXzcsCn(String xzcsCn) {
		this.xzcsCn = xzcsCn;
	}

	/**
	 * 选择对象
	 * @return
	 */
	public String getXzdx() {
		return xzdx;
	}

	/**
	 * 选择对象
	 * @param xzdx
	 */
	public void setXzdx(String xzdx) {
		this.xzdx = xzdx;
	}

	/**
	 * 选择对象名称
	 * @return
	 */
	public String getXzdxCn() {
		return xzdxCn;
	}

	/**
	 * 选择对象名称
	 * @param xzdxCn
	 */
	public void setXzdxCn(String xzdxCn) {
		this.xzdxCn = xzdxCn;
	}

	/**
	 * 选择物品
	 * @return
	 */
	public String getXzwp() {
		return xzwp;
	}

	/**
	 * 选择物品
	 * @param xzwp
	 */
	public void setXzwp(String xzwp) {
		this.xzwp = xzwp;
	}

	/**
	 * 选择物品名称
	 * @return
	 */
	public String getXzwpCn() {
		return xzwpCn;
	}

	/**
	 * 选择物品名称
	 * @param xzwpCn
	 */
	public void setXzwpCn(String xzwpCn) {
		this.xzwpCn = xzwpCn;
	}

	/**
	 * 作案工具
	 * @return
	 */
	public String getZagj() {
		return zagj;
	}

	/**
	 * 作案工具
	 * @param zagj
	 */
	public void setZagj(String zagj) {
		this.zagj = zagj;
	}

	/**
	 * 作案工具名称
	 * @return
	 */
	public String getZagjCn() {
		return zagjCn;
	}

	/**
	 * 作案工具名称
	 * @param zagjCn
	 */
	public void setZagjCn(String zagjCn) {
		this.zagjCn = zagjCn;
	}

	/**
	 * 选择部位
	 * @return
	 */
	public String getXzbw() {
		return xzbw;
	}

	/**
	 * 选择部位
	 * @param xzbw
	 */
	public void setXzbw(String xzbw) {
		this.xzbw = xzbw;
	}

	/**
	 * 选择部位名称
	 * @return
	 */
	public String getXzbwCn() {
		return xzbwCn;
	}

	/**
	 * 选择部位名称
	 * @param xzbwCn
	 */
	public void setXzbwCn(String xzbwCn) {
		this.xzbwCn = xzbwCn;
	}

	/**
	 * 手段特点
	 * @return
	 */
	public String getSdtd() {
		return sdtd;
	}

	/**
	 * 手段特点
	 * @param sdtd
	 */
	public void setSdtd(String sdtd) {
		this.sdtd = sdtd;
	}

	/**
	 * 手段特点名称
	 * @return
	 */
	public String getSdtdCn() {
		return sdtdCn;
	}

	/**
	 * 手段特点名称
	 * @param sdtdCn
	 */
	public void setSdtdCn(String sdtdCn) {
		this.sdtdCn = sdtdCn;
	}

	/**
	 * 作案人数
	 * @return
	 */
	public String getZars() {
		return zars;
	}

	/**
	 * 作案人数
	 * @param zars
	 */
	public void setZars(String zars) {
		this.zars = zars;
	}

	/**
	 * 死亡人数
	 * @return
	 */
	public String getSwrs() {
		return swrs;
	}

	/**
	 * 死亡人数
	 * @param swrs
	 */
	public void setSwrs(String swrs) {
		this.swrs = swrs;
	}

	/**
	 * 受伤人数
	 * @return
	 */
	public String getSsrs() {
		return ssrs;
	}

	/**
	 * 受伤人数
	 * @param ssrs
	 */
	public void setSsrs(String ssrs) {
		this.ssrs = ssrs;
	}

	/**
	 * 损失金额
	 * @return
	 */
	public String getSsjz() {
		return ssjz;
	}

	/**
	 * 损失金额
	 * @param ssjz
	 */
	public void setSsjz(String ssjz) {
		this.ssjz = ssjz;
	}

	/**
	 * 挽回损失金额
	 * @return
	 */
	public String getWhssjz() {
		return whssjz;
	}

	/**
	 * 挽回损失金额
	 * @param whssjz
	 */
	public void setWhssjz(String whssjz) {
		this.whssjz = whssjz;
	}

	/**
	 * 涉案主体
	 * @return
	 */
	public String getSazz() {
		return sazz;
	}

	/**
	 * 涉案主体
	 * @param sazz
	 */
	public void setSazz(String sazz) {
		this.sazz = sazz;
	}

	/**
	 * 涉案主体名称
	 * @return
	 */
	public String getSazzCn() {
		return sazzCn;
	}

	/**
	 * 涉案主体名称
	 * @param sazzCn
	 */
	public void setSazzCn(String sazzCn) {
		this.sazzCn = sazzCn;
	}

	/**
	 * 督办级别
	 * @return
	 */
	public String getDbjb() {
		return dbjb;
	}

	/**
	 * 督办级别
	 * @param dbjb
	 */
	public void setDbjb(String dbjb) {
		this.dbjb = dbjb;
	}

	/**
	 * 督办级别名称
	 * @return
	 */
	public String getDbjbCn() {
		return dbjbCn;
	}

	/**
	 * 督办级别名称
	 * @param dbjbCn
	 */
	public void setDbjbCn(String dbjbCn) {
		this.dbjbCn = dbjbCn;
	}

	/**
	 * 立案单位
	 * @return
	 */
	public String getLadw() {
		return ladw;
	}

	/**
	 * 立案单位
	 * @param ladw
	 */
	public void setLadw(String ladw) {
		this.ladw = ladw;
	}

	/**
	 * 立案单位名称
	 * @return
	 */
	public String getLadwCn() {
		return ladwCn;
	}

	/**
	 * 立案单位名称
	 * @param ladwCn
	 */
	public void setLadwCn(String ladwCn) {
		this.ladwCn = ladwCn;
	}

	/**
	 * 案件立案人员
	 * @return
	 */
	public String getAjlary() {
		return ajlary;
	}

	/**
	 * 案件立案人员
	 * @param ajlary
	 */
	public void setAjlary(String ajlary) {
		this.ajlary = ajlary;
	}

	/**
	 * 案件立案人员名称
	 * @return
	 */
	public String getAjlaryCn() {
		return ajlaryCn;
	}

	/**
	 * 案件立案人员名称
	 * @param ajlaryCn
	 */
	public void setAjlaryCn(String ajlaryCn) {
		this.ajlaryCn = ajlaryCn;
	}

	/**
	 * 主办单位
	 * @return
	 */
	public String getZbdw() {
		return zbdw;
	}

	/**
	 * 主办单位
	 * @param zbdw
	 */
	public void setZbdw(String zbdw) {
		this.zbdw = zbdw;
	}

	/**
	 * 主办单位名称
	 * @return
	 */
	public String getZbdwCn() {
		return zbdwCn;
	}

	/**
	 * 主办单位名称
	 * @param zbdwCn
	 */
	public void setZbdwCn(String zbdwCn) {
		this.zbdwCn = zbdwCn;
	}

	/**
	 * 案件主办人员
	 * @return
	 */
	public String getAjzbry() {
		return ajzbry;
	}

	/**
	 * 案件主办人员
	 * @param ajzbry
	 */
	public void setAjzbry(String ajzbry) {
		this.ajzbry = ajzbry;
	}

	/**
	 * 案件主办人员名称
	 * @return
	 */
	public String getAjzbryCn() {
		return ajzbryCn;
	}

	/**
	 * 案件主办人员名称
	 * @param ajzbryCn
	 */
	public void setAjzbryCn(String ajzbryCn) {
		this.ajzbryCn = ajzbryCn;
	}

	/**
	 * 案件协办人员（警号分隔）
	 * @return
	 */
	public String getAjxbry() {
		return ajxbry;
	}

	/**
	 * 案件协办人员（警号分隔）
	 * @param ajxbry
	 */
	public void setAjxbry(String ajxbry) {
		this.ajxbry = ajxbry;
	}

	/**
	 * 案件协办人员名称（多人）
	 * @return
	 */
	public String getAjxbryCn() {
		return ajxbryCn;
	}

	/**
	 * 案件协办人员名称（多人）
	 * @param ajxbryCn
	 */
	public void setAjxbryCn(String ajxbryCn) {
		this.ajxbryCn = ajxbryCn;
	}

	/**
	 * 备注
	 * @return
	 */
	public String getBz() {
		return bz;
	}

	/**
	 * 备注
	 * @param bz
	 */
	public void setBz(String bz) {
		this.bz = bz;
	}

	/**
	 * 犯罪主体类型
	 * @return
	 */
	public String getFzztlx() {
		return fzztlx;
	}

	/**
	 * 犯罪主体类型
	 * @param fzztlx
	 */
	public void setFzztlx(String fzztlx) {
		this.fzztlx = fzztlx;
	}

	/**
	 * 犯罪主体类型名称
	 * @return
	 */
	public String getFzztlxCn() {
		return fzztlxCn;
	}

	/**
	 * 犯罪主体类型名称
	 * @param fzztlxCn
	 */
	public void setFzztlxCn(String fzztlxCn) {
		this.fzztlxCn = fzztlxCn;
	}

	/**
	 * 是否涉外
	 * @return
	 */
	public String getSfsw() {
		return sfsw;
	}

	/**
	 * 是否涉外
	 * @param sfsw
	 */
	public void setSfsw(String sfsw) {
		this.sfsw = sfsw;
	}

	/**
	 * 是否涉外名称
	 * @return
	 */
	public String getSfswCn() {
		return sfswCn;
	}

	/**
	 * 是否涉外名称
	 * @param sfswCn
	 */
	public void setSfswCn(String sfswCn) {
		this.sfswCn = sfswCn;
	}

	/**
	 * 涉及国家地区
	 * @return
	 */
	public String getSjgjdq() {
		return sjgjdq;
	}

	/**
	 * 涉及国家地区
	 * @param sjgjdq
	 */
	public void setSjgjdq(String sjgjdq) {
		this.sjgjdq = sjgjdq;
	}

	/**
	 * 涉及国家地区名称
	 * @return
	 */
	public String getSjgjdqCn() {
		return sjgjdqCn;
	}

	/**
	 * 涉及国家地区名称
	 * @param sjgjdqCn
	 */
	public void setSjgjdqCn(String sjgjdqCn) {
		this.sjgjdqCn = sjgjdqCn;
	}

	/**
	 * 案件来源
	 * @return
	 */
	public Integer getAjFrom() {
		return ajFrom;
	}

	/**
	 * 案件来源
	 * @param ajFrom
	 */
	public void setAjFrom(Integer ajFrom) {
		this.ajFrom = ajFrom;
	}

	/**
	 * 案件来源名称
	 * @return
	 */
	public String getAjFromCn() {
		return ajFromCn;
	}

	/**
	 * 案件来源名称
	 * @param ajFromCn
	 */
	public void setAjFromCn(String ajFromCn) {
		this.ajFromCn = ajFromCn;
	}

	/**
	 * 案件开始时间
	 * @return
	 */
	/*public String getBeginCreateTime() {
		return beginCreateTime;
	}*/

	/**
	 * 案件开始时间
	 * @param beginCreateTime
	 */
	/*public void setBeginCreateTime(String beginCreateTime) {
		this.beginCreateTime = beginCreateTime;
	}*/

	/**
	 * 案件结束时间
	 * @return
	 */
	/*public String getEndCreateTime() {
		return endCreateTime;
	}*/

	/**
	 * 案件结束时间
	 * @param endCreateTime
	 */
	/*public void setEndCreateTime(String endCreateTime) {
		this.endCreateTime = endCreateTime;
	}*/

	/**
	 * 时间段（用于前台提交回来的标识）
	 * @return
	 */
	public String getTimeSection() {
		return timeSection;
	}

	/**
	 * 时间段（用于前台提交回来的标识）
	 * @param timeSection
	 */
	public void setTimeSection(String timeSection) {
		this.timeSection = timeSection;
	}

	/**
	 * 需求数量
	 * @return
	 */
	public Integer getDemandCount() {
		return demandCount;
	}

	/**
	 * 需求数量
	 * @param demandCount
	 */
	public void setDemandCount(Integer demandCount) {
		this.demandCount = demandCount;
	}

	/**
	 * 线索数量
	 * @return
	 */
	public Integer getClueCount() {
		return clueCount;
	}

	/**
	 * 线索数量
	 * @param clueCount
	 */
	public void setClueCount(Integer clueCount) {
		this.clueCount = clueCount;
	}

	/**
	 * 信息数量
	 * @return
	 */
	public Integer getInfoCount() {
		return infoCount;
	}

	/**
	 * 信息数量
	 * @param infoCount
	 */
	public void setInfoCount(Integer infoCount) {
		this.infoCount = infoCount;
	}

	public String getBdajstatebz() {
		return bdajstatebz;
	}

	public void setBdajstatebz(String bdajstatebz) {
		this.bdajstatebz = bdajstatebz;
	}

	public String getMindTop() {
		return mindTop;
	}

	public void setMindTop(String mindTop) {
		this.mindTop = mindTop;
	}

	public String getMindLeft() {
		return mindLeft;
	}

	public void setMindLeft(String mindLeft) {
		this.mindLeft = mindLeft;
	}

	public String getSecuritygrade() {
		return securitygrade;
	}

	public void setSecuritygrade(String securitygrade) {
		this.securitygrade = securitygrade;
	}

	public String getLasj() {
		return lasj;
	}

	public void setLasj(String lasj) {
		this.lasj = lasj;
	}

	public String getCaseSponsor() {
		return caseSponsor;
	}

	public void setCaseSponsor(String caseSponsor) {
		this.caseSponsor = caseSponsor;
	}

	public String getFasjcz() {
		return fasjcz;
	}

	public void setFasjcz(String fasjcz) {
		this.fasjcz = fasjcz;
	}

	public String getFasjzz() {
		return fasjzz;
	}

	public void setFasjzz(String fasjzz) {
		this.fasjzz = fasjzz;
	}

	public Integer getSfcba() {
		return sfcba;
	}

	public void setSfcba(Integer sfcba) {
		this.sfcba = sfcba;
	}

	public Integer getCbabh() {
		return cbabh;
	}

	public void setCbabh(Integer cbabh) {
		this.cbabh = cbabh;
	}

	public List<CasesMegerVO> getLsCasesMegerVO() {
		return lsCasesMegerVO;
	}

	public void setLsCasesMegerVO(List<CasesMegerVO> lsCasesMegerVO) {
		this.lsCasesMegerVO = lsCasesMegerVO;
	}

	public String getAjstate() {
		return ajstate;
	}

	public void setAjstate(String ajstate) {
		this.ajstate = ajstate;
	}

	public String getBeginLasj() {
		return beginLasj;
	}

	public void setBeginLasj(String beginLasj) {
		this.beginLasj = beginLasj;
	}

	public String getEndLasj() {
		return endLasj;
	}

	public void setEndLasj(String endLasj) {
		this.endLasj = endLasj;
	}

	public String getSecuritygradeCn() {
		return securitygradeCn;
	}

	public void setSecuritygradeCn(String securitygradeCn) {
		this.securitygradeCn = securitygradeCn;
	}
}
