package com.spring.myapp.commons.paging;

public class SearchCriteria extends Criteria{
	
	private String condition;	//검색조건
	private String keyword;		//검색어
	
	public SearchCriteria() {
		super();
		this.keyword = "";
		this.condition = "";
	}
	
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	
	
}
