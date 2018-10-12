package core.module.annotation;
/**
 * @author liuxh
 * @version 创建时间：2013-1-21 下午05:48:08
 *
 * 类说明：记录操作Action类
 *
 */
public class ActionHandle {

	private String className;
	private String operName;
	private String entityKey;
	
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getOperName() {
		return operName;
	}
	public void setOperName(String operName) {
		this.operName = operName;
	}
	public String getEntityKey() {
		return entityKey;
	}
	public void setEntityKey(String entityKey) {
		this.entityKey = entityKey;
	}
}
