package com.item.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author liuxh
 * @version 创建时间：2011-6-9 上午01:38:22
 *
 * 类说明：execl导出记录
 *
 */
public class ExcelExport {

	private String head;
	private String foot;
	private List<String> el=new ArrayList<String>();
	private List<List<Excel>> ell=new ArrayList<List<Excel>>();
	protected Logger logger = LoggerFactory.getLogger(getClass());
	private static int amount=60000;
	/**
	 * execl导出记录
	 * <br/>
	 * Date：2011-06-09		
	 * 
	 * @author liuxh
	 */
	public boolean excelExport(){
//		if(null==el||el.size()<=0||null==ell||ell.size()<=0||el.size()!=ell.get(0).size()){
//			logger.error("数据不全");
//			return false;
//		}else if(ell.size()>60000){
//			logger.error("数据过多，超过60000行");
//			return false;
//		}
		
		HttpServletResponse response =ServletActionContext.getResponse();
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String saveAsFileName = head;
		String title=head;
		response.setHeader("Content-Disposition", "attachment;filename="+ Tools.saveFileName(saveAsFileName) + ".xls");
		WritableWorkbook wwb = null;
		try {
			wwb = Workbook.createWorkbook(response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (wwb != null) {
			try {
				for(int l=0;l<=(ell.size()-1)/amount;l++){
					int last=ell.size()-l*amount;
					if(last>amount){
						last=amount;
					}
					WritableSheet ws = wwb.createSheet("sheet"+(l+1), 0);
	
					ws.mergeCells(0, 0, el.size()-1, 2);// 合并单元格(左列，左行，右列，右行)从第1行第1列到第3行第n列
					ws.addCell(new Label(0, 0, title, ExcelStyle.getTitle()));
	
					int lastIndex = 4;
					for(int i=0;i<el.size();i++){
						ws.addCell(new Label(i, 3, el.get(i), ExcelStyle.getTableCell()));
						for(int j=0;j<last;j++){
							ws.setColumnView(i, ell.get(j+l*amount).get(i).getCol_width());// 列宽
							ws.addCell(new Label(i, j + lastIndex, ell.get(j+l*amount).get(i).getContent(), ell.get(j+l*amount).get(i).getSt()));
						}
					}
					if(foot != null && !"".equals(foot)&&(last<amount||ell.size()==amount)){
						lastIndex += last;
						ws.mergeCells(0, lastIndex, el.size()-1, lastIndex+1);
						ws.addCell(new Label(0, lastIndex, foot, ExcelStyle.getTableFooter()));
					}
				}
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			try {
				wwb.write();
				wwb.close();
			} catch (IOException e) {
				e.printStackTrace();
				logger.error(e.getMessage());
				return false;
			} catch (WriteException e) {
				e.printStackTrace();
				logger.error(e.getMessage());
				return false;
			}
		}
		return true;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public String getFoot() {
		return foot;
	}
	public void setFoot(String foot) {
		this.foot = foot;
	}
	public List<String> getEl() {
		return el;
	}
	public void setEl(List<String> el) {
		this.el = el;
	}
	public List<List<Excel>> getEll() {
		return ell;
	}
	public void setEll(List<List<Excel>> ell) {
		this.ell = ell;
	}
}
