package com.item.constants;
/**
 * 缓存ID
 * @author ljqiang
 *
 */
public enum CacheConstant {
	// namespace(cacheModel id)
	Dictionary("Dictionary-cache"), 
	User("User-cache"),
	Role("Role-cache"),
	RoleAuth("RoleAuth-cache")
	;
	
	private String value; 
	
	private CacheConstant(String value){
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
