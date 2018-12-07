package com.item.domain;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnore;

/*
 *联运平台 实体类
 */
public class BChannel {

    private Long id;

    private String channelName;

    private Integer balance;
    private float amount;

    private Integer amountType = 0;
    private Integer version;
    private Integer newversion;
    private String business;
    private String phone;
    private String verifyUrl;

    @JsonIgnore
    private Date createTime;

    @JsonIgnore
    private String channelCallbackUrl;

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getNewversion() {
        return newversion;
    }

    public void setNewversion(Integer newversion) {
        this.newversion = newversion;
    }

    public Integer getAmountType() {
        return amountType;
    }

    public void setAmountType(Integer amountType) {
        this.amountType = amountType;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    @JsonIgnore
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @JsonIgnore
    public String getChannelCallbackUrl() {
        return channelCallbackUrl;
    }

    public void setChannelCallbackUrl(String channelCallbackUrl) {
        this.channelCallbackUrl = channelCallbackUrl;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getVerifyUrl() {
        return verifyUrl;
    }

    public void setVerifyUrl(String verifyUrl) {
        this.verifyUrl = verifyUrl;
    }

    @Override
    public boolean equals(Object obj) {
        return this.id.equals(((BChannel)obj).id);
    }
}
