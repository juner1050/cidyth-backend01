package com.hyzs.cidyth.module.notice.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hyzs.cidyth.module.notice.entity.Notice;
import com.hyzs.psd.gafa.daf.mybatis.tex.DataSourceName;
import com.hyzs.psd.gafa.daf.mybatis.tk.common.Mapper;
@DataSourceName(name = "masterSqlSessionFactory")
public interface NoticeMapper extends Mapper<Notice> {
	public int insertBatch(@Param("noticeList") List<Notice> notices);
	public List<Map<String, Object>> selectNotRead(@Param("jsrbh") String userId);
	public int readBatch(@Param("idList") List<String> idList,@Param("jsrbh") String userId);
}
