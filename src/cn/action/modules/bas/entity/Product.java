package cn.action.modules.bas.entity;

import cn.action.common.persistence.DataEntity;
import cn.action.modules.tec.entity.Flow;

public class Product extends DataEntity<Product>{

	/**
	 * 产品类
	 */
	private static final long serialVersionUID = 1L;
	
	private String productName;
	private String productAbbr;
	private String productDesc;
	private String firstCheck;
	private String qrCode;
	private String manageWay;
	private String lotNumber;
	private String state;
	private String productUnit;
	private String productProp;
	private Flow flow;//工艺流程外键对象
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductAbbr() {
		return productAbbr;
	}
	public void setProductAbbr(String productAbbr) {
		this.productAbbr = productAbbr;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public String getFirstCheck() {
		return firstCheck;
	}
	public void setFirstCheck(String firstCheck) {
		this.firstCheck = firstCheck;
	}
	public String getQrCode() {
		return qrCode;
	}
	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}
	public String getManageWay() {
		return manageWay;
	}
	public void setManageWay(String manageWay) {
		this.manageWay = manageWay;
	}
	public String getLotNumber() {
		return lotNumber;
	}
	public void setLotNumber(String lotNumber) {
		this.lotNumber = lotNumber;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getProductUnit() {
		return productUnit;
	}
	public void setProductUnit(String productUnit) {
		this.productUnit = productUnit;
	}
	public String getProductProp() {
		return productProp;
	}
	public void setProductProp(String productProp) {
		this.productProp = productProp;
	}
	public Flow getFlow() {
		return flow;
	}
	public void setFlow(Flow flow) {
		this.flow = flow;
	}

}
