package com.item.utils;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WriteException;


/**
 * Excel样式
 * @author liuxh
 *
 */
public class ExcelStyle {
	
	/**
	 * 设置标题单元格样式
	 * 
	 * @return
	 */    
	public static WritableCellFormat getTitle() {
		WritableFont font = new WritableFont(WritableFont.TIMES, 24,WritableFont.BOLD);// 定义字体

		WritableCellFormat format = new WritableCellFormat(font);
		try {
			format.setAlignment(jxl.format.Alignment.CENTRE);// 左右居中
			format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 上下居中
			// format.setBorder(Border.ALL,BorderLineStyle.THIN,Colour.BLACK);//黑色边框
			// format.setBackground(Colour.YELLOW);//黄色背景
			format.setWrap(true);//设置自动换行
		} catch (WriteException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return format;
	}

	/**
	 * 设置副标题单元格样式
	 * 
	 * @return
	 */
	public static WritableCellFormat getSubTitle() {
		WritableFont font = new WritableFont(WritableFont.TIMES, 14,WritableFont.BOLD);

		WritableCellFormat format = new WritableCellFormat(font);

		try {
			format.setAlignment(jxl.format.Alignment.LEFT);
			format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			// format.setBorder(Border.ALL,BorderLineStyle.THIN,Colour.BLACK);
			format.setWrap(true);
		} catch (WriteException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return format;
	}
	
	/**
	 * 设置表头表尾单元格样式
	 * 
	 * @return
	 */
	public static WritableCellFormat getTableCell() {
		WritableFont font = new WritableFont(WritableFont.TIMES, 12,WritableFont.BOLD);
		
		WritableCellFormat format = new WritableCellFormat(font);
		
		try {
			format.setAlignment(jxl.format.Alignment.CENTRE);
			format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			format.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
			format.setWrap(true);
		} catch (WriteException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return format;
	}

	/**
	 * 设置序号单元格样式
	 * 
	 * @return
	 */
	public static WritableCellFormat getXh() {
		WritableFont font = new WritableFont(WritableFont.TIMES, 12);

		WritableCellFormat format = new WritableCellFormat(font);

		try {
			format.setAlignment(jxl.format.Alignment.CENTRE);
			format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			format.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
			format.setWrap(true);
		} catch (WriteException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return format;
	}

	/**
	 * 设置其他单元格样式
	 * 
	 * @return
	 */
	public static WritableCellFormat getNormolCell() {// 12号字体,上下居中 左右居左,带黑色边框
		WritableFont font = new WritableFont(WritableFont.TIMES, 12);
		WritableCellFormat format = new WritableCellFormat(font);
		try {
			format.setAlignment(jxl.format.Alignment.LEFT);
			format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			format.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
			format.setWrap(true);
		} catch (WriteException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return format;
	}

	/**
	 * 设置数字单元格样式
	 * 
	 * @return
	 */
	public static WritableCellFormat getNumberCell() {
		NumberFormat nf = new NumberFormat("###,##0.00");
		WritableCellFormat format = new jxl.write.WritableCellFormat(nf);
		try {
			format.setAlignment(Alignment.LEFT);
			format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			format.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
			format.setWrap(true);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return format;
	}
	
	/**
	 * 设置数字单元格样式(int)
	 * 
	 * @return
	 */
	public static WritableCellFormat getINumberCell() {
		WritableFont font = new WritableFont(WritableFont.TIMES, 12);
		WritableCellFormat format = new jxl.write.WritableCellFormat(font);
		try {
			format.setAlignment(Alignment.LEFT);
			format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			format.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
			format.setWrap(true);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return format;
	}
	
	/**
	 * 设置百分数单元格样式
	 * 
	 * @return
	 */
	public static WritableCellFormat getPercentCell() {
		NumberFormat nf = new NumberFormat("###,##0.00%");
		WritableCellFormat format = new jxl.write.WritableCellFormat(nf);
		try {
			format.setAlignment(Alignment.LEFT);
			format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			format.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
			format.setWrap(true);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return format;
	}
	
	/**
	 * 设置红色单元格样式
	 * 
	 * @return
	 */
	public static WritableCellFormat getRedCell() {
		WritableFont font = new WritableFont(WritableFont.TIMES, 12);
		WritableCellFormat format = new jxl.write.WritableCellFormat(font);
		try {
			format.setAlignment(Alignment.LEFT);
			format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			format.setBackground(Colour.ROSE);
			format.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
			format.setWrap(true);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return format;
	}
	
	/**
	 * 设置表格标题单元格样式
	 * 
	 * @return
	 */
	public static WritableCellFormat getCellTitle() {		
		WritableFont font = new WritableFont(WritableFont.TIMES, 14,WritableFont.BOLD);

		WritableCellFormat format = new WritableCellFormat(font);

		try {
			format.setAlignment(jxl.format.Alignment.CENTRE);
			
			format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			format.setBorder(Border.ALL,BorderLineStyle.THIN,Colour.BLACK);
			format.setWrap(true);
		} catch (WriteException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return format;
	}
	
	/**
	 * 设置无边框表尾单元格样式
	 * 
	 * @return
	 */
	public static WritableCellFormat getTableFooter() {
		WritableFont font = new WritableFont(WritableFont.TIMES, 12,WritableFont.BOLD);
		
		WritableCellFormat format = new WritableCellFormat(font);
		
		try {
			format.setAlignment(jxl.format.Alignment.LEFT);
			format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			format.setWrap(true);
		} catch (WriteException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return format;
	}
}
