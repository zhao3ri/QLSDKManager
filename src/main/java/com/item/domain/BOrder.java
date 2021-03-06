package com.item.domain;
 
public class BOrder extends StatBaseEntity{
	private Long id;
	
	private Long appId;
	
	private String appName;
	
	private Integer platformId;
	
	private String platformName;
	
	private String uid;
	
	private String zoneId;
	
	private String zoneName;
	
	private String roleId;
	
	private String roleName;
	
	private String orderId;
	
	private String cpOrderId;
	
	private String cpExtInfo;
	
	private Integer amount;
	
	private String notifyUrl;
	
	private Integer fixed;
	
	private String deviceId;
	
	private Integer clientType;
	
	private String errorMsg;
	
	private Integer status;
	
	private Integer notifyStatus;
	
	private java.util.Date createTime;
	
	private java.util.Date updateTime;
	
	private Integer gold;
	
	public Long getId(){
		return id;
	}
	public void setId(Long id){
		this.id=id;
	}
	public Long getAppId(){
		return appId;
	}
	public void setAppId(Long appId){
		this.appId=appId;
	}
	public Integer getPlatformId(){
		return platformId;
	}
	public void setPlatformId(Integer platformId){
		this.platformId=platformId;
	}
	public String getUid(){
		return uid;
	}
	public void setUid(String uid){
		this.uid=uid;
	}
	public String getZoneId(){
		return zoneId;
	}
	public void setZoneId(String zoneId){
		this.zoneId=zoneId;
	}
	public String getRoleId(){
		return roleId;
	}
	public void setRoleId(String roleId){
		this.roleId=roleId;
	}
	public String getOrderId(){
		return orderId;
	}
	public void setOrderId(String orderId){
		this.orderId=orderId;
	}
	public String getCpOrderId(){
		return cpOrderId;
	}
	public void setCpOrderId(String cpOrderId){
		this.cpOrderId=cpOrderId;
	}
	public String getCpExtInfo(){
		return cpExtInfo;
	}
	public void setCpExtInfo(String cpExtInfo){
		this.cpExtInfo=cpExtInfo;
	}
	public Integer getAmount(){
		return amount;
	}
	public void setAmount(Integer amount){
		this.amount=amount;
	}
	public String getNotifyUrl(){
		return notifyUrl;
	}
	public void setNotifyUrl(String notifyUrl){
		this.notifyUrl=notifyUrl;
	}
	public Integer getFixed(){
		return fixed;
	}
	public void setFixed(Integer fixed){
		this.fixed=fixed;
	}
	public String getDeviceId(){
		return deviceId;
	}
	public void setDeviceId(String deviceId){
		this.deviceId=deviceId;
	}
	public Integer getClientType(){
		return clientType;
	}
	public void setClientType(Integer clientType){
		this.clientType=clientType;
	}
	public String getErrorMsg(){
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg){
		this.errorMsg=errorMsg;
	}
	public Integer getStatus(){
		return status;
	}
	public void setStatus(Integer status){
		this.status=status;
	}
	
	public Integer getNotifyStatus() {
		return notifyStatus;
	}
	public void setNotifyStatus(Integer notifyStatus) {
		this.notifyStatus = notifyStatus;
	}
	public java.util.Date getCreateTime(){
		return createTime;
	}
	public void setCreateTime(java.util.Date createTime){
		this.createTime=createTime;
	}
	public java.util.Date getUpdateTime(){
		return updateTime;
	}
	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime=updateTime;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getPlatformName() {
		return platformName;
	}
	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}
	public String getZoneName() {
		return zoneName;
	}
	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Integer getGold() {
		return gold;
	}
	public void setGold(Integer gold) {
		this.gold = gold;
	}
}