package com.hyzs.cidyth.common.utils;

import java.util.List;
import org.apache.commons.collections4.CollectionUtils;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
/**
 * sql批处理参数分派工具
 * @author derrick
 *
 */
public class BatchSqlParamListBuilder {
	/**
	 * 分批
	 * 
	 * @param fullDataList
	 *            原始全量列表
	 * @param batchSize
	 *            每批数量
	 * @return 分批列表
	 */
	public static <F, T> List<T> getBatchList(List<F> fullDataList, int batchSize,
			BatchSqlParamListBuilder.ElementResolver<F, T> function) {
		if (CollectionUtils.isEmpty(fullDataList)) {
			throw new IllegalArgumentException("参数'fullDataList'不能为空！");
		}
		if (function == null) {
			throw new IllegalArgumentException(
					"参数'function'不能为空！建议使用 getBatchList(List<F> fullDataList,int batchSize)方法.");
		}
		List<T> result = Lists.newArrayList();
		int len = fullDataList.size();
		int size = len % batchSize;
		if (size == 0) {
			size = len / batchSize;
		} else {
			size = (len / batchSize) + 1;
		}
		for (int j = 0; j < size; j++) {
			int fromIdx = j * batchSize;
			int toIdx = Math.min(fromIdx + batchSize, len);
			List<F> subList = fullDataList.subList(fromIdx, toIdx);
			result.add(function.apply(subList));

		}
		return result;
	}

	/**
	 * 分批
	 * 
	 * @param fullDataList
	 *            原始全量列表
	 * @param batchSize
	 *            每批数量
	 * @return 分批列表
	 */
	public static <F> List<List<F>> getBatchList(List<F> fullDataList, int batchSize) {
		if (CollectionUtils.isEmpty(fullDataList)) {
			throw new IllegalArgumentException("参数'fullDataList'不能为空！");
		}
		List<List<F>> result = Lists.newArrayList();
		int len = fullDataList.size();
		int size = len % batchSize;
		if (size == 0) {
			size = len / batchSize;
		} else {
			size = (len / batchSize) + 1;
		}
		for (int j = 0; j < size; j++) {
			int fromIdx = j * batchSize;
			int toIdx = Math.min(fromIdx + batchSize, len);
			List<F> subList = fullDataList.subList(fromIdx, toIdx);
			result.add(subList);
		}
		return result;
	}

	public static interface ElementResolver<F, T> {
		public T apply(List<F> input);
	}

	public static void main(String[] args) {
		List<String> list = Lists.newArrayList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13",
				"14");		
		List<String> result = BatchSqlParamListBuilder.getBatchList(list, 3,
				new BatchSqlParamListBuilder.ElementResolver<String, String>() {
					@Override
					public String apply(List<String> input) {
						StringBuilder result = new StringBuilder("'");
						Joiner.on("','").appendTo(result, input);
						result.append("'");
						return result.toString();
					}
				});
		for (String ele : result) {
			System.out.println(ele);
		}
	}
}
