package com.study.vo;

public class CustomVO {

	private String item;
	
	private String subject;
	
	private String subSubject;
	
	private String ref;
	
	private String remarks;
	
	private String category;

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSubSubject() {
		return subSubject;
	}

	public void setSubSubject(String subSubject) {
		this.subSubject = subSubject;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "CustomVO [item=" + item + ", subject=" + subject + ", subSubject=" + subSubject + ", ref=" + ref
				+ ", remarks=" + remarks + ", category=" + category + "]";
	}
	
}
