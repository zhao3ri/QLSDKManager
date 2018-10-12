package core.module.annotation;
/**
 * @author liuxh
 * @version 创建时间：2013-1-22 下午03:24:06
 *
 * 类说明：操作属性类
 *
 */
public class AttributeLog {

	private String opkey;
	private String opname;
	private String state;
	private String rentity;
	private Class<?> rserviceClass;
	private String rname;
	
	public String getOpkey() {
		return opkey;
	}
	public void setOpkey(String opkey) {
		this.opkey = opkey;
	}
	public String getOpname() {
		return opname;
	}
	public void setOpname(String opname) {
		this.opname = opname;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getRentity() {
		return rentity;
	}
	public void setRentity(String rentity) {
		this.rentity = rentity;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	public Class<?> getRserviceClass() {
		return rserviceClass;
	}
	public void setRserviceClass(Class<?> rserviceClass) {
		this.rserviceClass = rserviceClass;
	}
}
