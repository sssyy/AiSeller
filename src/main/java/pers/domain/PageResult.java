package pers.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**********************************
 * @company 智慧互通科技有线公司
 * @author wenli.zhao
 * @date 2017年9月19日
 * @desc
 ****************************/
public class PageResult<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7438599270466894003L;

	private int pageNum;
	private int pageSize;
	private long total;
	private List<T> list;

	public PageResult(){}
	
	public PageResult(int pageNum, int pageSize, long total, List<T> list){
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.total = total;
		this.list = list;
	}
	
	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getList() {
		if(list==null) {
			return new ArrayList<>();
		}
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{");
		builder.append("\"total\":").append(total).append(",");
		builder.append("\"list\":");
		builder.append("[");
		List<T> dataList = getList();
		Integer size = dataList.size();
		for (int i = 0; i < size; i++) {
			if (i == size - 1) {
				builder.append(dataList.get(i));
			} else {
				builder.append(dataList.get(i));
				builder.append(",");
			}
		}
		builder.append("]");
		builder.append("}");
		return builder.toString();
	}

}
