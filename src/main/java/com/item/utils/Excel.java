package com.item.utils;

import jxl.format.CellFormat;

/**
 * @author liuxh
 * @version 创建时间：2011-6-9 上午06:48:53
 *
 * 类说明：excel自定义实体
 *
 */
public class Excel {

	private String title;
	private int col_width=13;
	private  CellFormat st=ExcelStyle.getXh();
	private String content="";
	
	public Excel(Object content,int col_width){
		this.col_width=col_width;
		if(content!=null){
			this.content=content.toString();
		}else{
			this.content="";
		}
	}
	
	public Excel(Object content){
		if(content!=null){
			this.content=content.toString();
		}else{
			this.content="";
		}
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getCol_width() {
		return col_width;
	}
	public void setCol_width(int colWidth) {
		col_width = colWidth;
	}
	public CellFormat getSt() {
		return st;
	}
	public void setSt(CellFormat st) {
		this.st = st;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
