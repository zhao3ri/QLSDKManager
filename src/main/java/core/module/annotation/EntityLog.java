package core.module.annotation;

import java.util.HashMap;
import java.util.Map;


/**
 * @author liuxh
 * @version 创建时间：2013-1-22 上午09:48:55
 *
 * 类说明：操作日志
 *
 */
public class EntityLog {

	private Class<?> entityClass;
	private Class<?> serviceClass;
	private String omkey;
	private String omname;
	private Map<String, AttributeLog> attributeLogMap=new HashMap<String, AttributeLog>();
	private String primarykeyName;
	
	public Class<?> getEntityClass() {
		return entityClass;
	}
	public void setEntityClass(Class<?> entityClass) {
		this.entityClass = entityClass;
	}
	public Class<?> getServiceClass() {
		return serviceClass;
	}
	public void setServiceClass(Class<?> serviceClass) {
		this.serviceClass = serviceClass;
	}
	public String getOmkey() {
		return omkey;
	}
	public void setOmkey(String omkey) {
		this.omkey = omkey;
	}
	public String getOmname() {
		return omname;
	}
	public void setOmname(String omname) {
		this.omname = omname;
	}
	public Map<String, AttributeLog> getAttributeLogMap() {
		return attributeLogMap;
	}
	public void setAttributeLogMap(Map<String, AttributeLog> attributeLogMap) {
		this.attributeLogMap = attributeLogMap;
	}
	public String getPrimarykeyName() {
		return primarykeyName;
	}
	public void setPrimarykeyName(String primarykeyName) {
		this.primarykeyName = primarykeyName;
	}
}
