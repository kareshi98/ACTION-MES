package cn.action.modules.bas.entity;

import cn.action.common.persistence.DataEntity;
/**
 * BOM详情类
 * @author Administrator
 *
 */
public class BomDetail extends DataEntity<BomDetail>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Bom bom;//Bom外键对象
	private String mType;//材料类型
	private double mNum;//数量
	private String unit;//单位
	public Bom getBom() {
		return bom;
	}
	public void setBom(Bom bom) {
		this.bom = bom;
	}
	public String getmType() {
		return mType;
	}
	public void setmType(String mType) {
		this.mType = mType;
	}
	public double getmNum() {
		return mNum;
	}
	public void setmNum(double mNum) {
		this.mNum = mNum;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}



}
