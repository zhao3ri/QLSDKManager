package com.item.service.authority;

import java.util.ArrayList;
import java.util.List;

import com.item.domain.authority.IdentityData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.item.dao.authority.DataDao;
import com.item.domain.authority.Data;

/**
 * 数据集管理信息处理类
 * 
 * @author Administrator
 * @since 2013-10-11
 */
@Service
@Transactional
public class DataService {

	@Autowired
	private IdentityDataService rds;
	@Autowired
	private DataDao dataDao;
	
	/**
	 * 从数据库获取数据集信息并添加到缓存中
	 * @return
	 */
	public List<Data> getAllDataList(){
		List<Data> dataList = dataDao.find("Data.listAll", null);
		list2map(dataList);
		return dataList;
	}
	
	/**
	 * 将数据集信息采用ID为键添加到map缓存中
	 * @param list
	 */
	public void list2map(List<Data> list){
		if(list != null && !list.isEmpty()){
			for(Data data : list){
				AuthCacheManager.getInstance().putData2Cache(data);
			}
		}
	}
	
	/**
	 * 从缓存中根据模块ID获取数据集信息列表
	 * @param moduleId
	 * @return
	 */
	public List<Data> getDataListByModuleId(Long moduleId){
		List<Data> dataList = new ArrayList<Data>();
		if(moduleId == null){
			return dataList;
		}
		for(Data data : AuthCacheManager.getInstance().getDataCache()){
			if(moduleId.equals(data.getModuleID())){
				dataList.add(data);
			}
		}
		return dataList;
	}
	
	/**
	 * 通过角色ID获取指定数据集的访问级别
	 * @param dataId
	 * @param identityDataList
	 * @return
	 */
	public Long getRoleDataSelectedLevel(Long dataId, List<IdentityData> identityDataList){
		Long level = 4l;
		for(IdentityData rd : identityDataList){
			if(dataId.equals(rd.getDatasetId())){
				level = rd.getLevel();
				break;
			}
		}
		return level;
	}
	
	/**
	 * 获取数据集信息用于角色创建
	 * @param moduleId
	 * @return
	 */
	public String getDataListHtml(Long moduleId){
		int count = 0;
		StringBuffer sb = new StringBuffer();
		List<Data> dataList = this.getDataListByModuleId(moduleId);
		sb.append("<table width=\"60%\">")
			.append("<tr>");
		for(Data data : dataList){
			sb
			.append("<td >")
			.append(data.getDatasetName())
			.append("</td>")
			.append("<td>")
			.append("<select name=\"datasetLevels\" >")
			.append("<option value=\"").append(data.getId()).append("&").append("1").append("\">").append("个人").append("</option>/n")
			.append("<option value=\"").append(data.getId()).append("&").append("2").append("\">").append("小组").append("</option>/n")
			.append("<option value=\"").append(data.getId()).append("&").append("3").append("\">").append("部门").append("</option>/n")
			.append("<option value=\"").append(data.getId()).append("&").append("4").append("\" selected>").append("全部").append("</option>/n")
			.append("</select>")
			.append("</td>")
			;
			count++;
			if(count%3 == 0){
				sb.append("</tr>")
				.append("<tr>");
			}
		}
		sb.append("</tr>")
		.append("</table>");
		return sb.toString();
	}
	
	/**
	 * 获取数据集以及角色拥有的数据集权限信息用于角色更新
	 * @param moduleId
	 * @param roleId
	 * @return
	 */
	public String getUpdateDataListHtml(Long moduleId, Long roleId){
		int count = 0;
		Long level = 4l;
		StringBuffer sb = new StringBuffer();
		List<Data> dataList = this.getDataListByModuleId(moduleId);
		List<IdentityData> identityDataList = rds.getIdentityDataListById(roleId);
		sb.append("<table width=\"60%\">")
			.append("<tr>");
		for(Data data : dataList){
			level = this.getRoleDataSelectedLevel(data.getId(), identityDataList);
			sb
			.append("<td >")
			.append(data.getDatasetName())
			.append("</td>")
			.append("<td>")
			.append("<select name=\"datasetLevels\" >")
			.append("<option value=\"").append(data.getId()).append("&").append("1").append("\" ").append(level.equals(1l) ? "selected" : "").append(" >个人").append("</option>/n")
			.append("<option value=\"").append(data.getId()).append("&").append("2").append("\" ").append(level.equals(2l) ? "selected" : "").append(" >小组").append("</option>/n")
			.append("<option value=\"").append(data.getId()).append("&").append("3").append("\" ").append(level.equals(3l) ? "selected" : "").append(" >部门").append("</option>/n")
			.append("<option value=\"").append(data.getId()).append("&").append("4").append("\" ").append(level.equals(4l) ? "selected" : "").append(" >全部").append("</option>/n")
			.append("</select>")
			.append("</td>")
			;
			count++;
			level = 4l;
			if(count%3 == 0){
				sb.append("</tr>")
				.append("<tr>");
			}
		}
		sb.append("</tr>")
		.append("</table>");
		return sb.toString();
	}
	
}
