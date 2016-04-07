package com.henli.data;

public class Knowledge {
     
	private int _id;
	private String title;
	private String detail;
	
	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Knowledge(String title){
		
		this.title=title;
		
	}
	
	@Override
	public String toString() {
		return  title ;
	}
	

}