package cn.action.modules.bas.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.action.common.service.CrudService;
import cn.action.modules.bas.dao.WorkOrderDao;
import cn.action.modules.bas.entity.Bom;
import cn.action.modules.bas.entity.BomDetail;
import cn.action.modules.bas.entity.Material;
import cn.action.modules.bas.entity.Order;
import cn.action.modules.bas.entity.WorkOrder;
import javafx.concurrent.Worker;

@Service
@Transactional(readOnly=true)
public class WorkOrderService extends CrudService<WorkOrderDao, WorkOrder>{
	@Autowired
	private BomService bomService;
	@Autowired
	private BomDetailService bomDetailService;
	@Autowired
	private MaterialService materialService;
	@Autowired
	private OrderService orderService;
	
	/**
	 * 保存工单
	 * @param workOrder
	 * @return
	 */
	public boolean saveAll(WorkOrder workOrder) {
		boolean flag=true;
		//判断是修改还是添加的保存
		if(workOrder.getIsNewRecord()) {
			//1.库存判断
			flag=this.stockProcessing(workOrder);
			if(!flag) {
				return flag;
			}
			//2.库存充足时，修改工单对应的订单的状态为workOrder
			Order order=orderService.get(workOrder.getOrder().getId());
			order.setStatus("workOrder");
			orderService.save(order);			
		}
		//3.保存工单
		if(workOrder.getActStartTime().equals("")) {
			workOrder.setActStartTime(null);
		}
		this.save(workOrder);
		return flag;
	}
	//模拟wms系统的库存处理逻辑
	private boolean stockProcessing(WorkOrder workOrder) {
		//1.根据工单中的产品获得对应的Bom对象
		Bom tempBom=new Bom();
		tempBom.setProduct(workOrder.getProduct());
		//传入封装条件的Bom，调用方法获得工单对应的Bom对象
		List<Bom> boms=bomService.findList(tempBom);
		//2.根据bom对象查询bom详情集合
		BomDetail tempDetail=new BomDetail();
		tempDetail.setBom(boms.get(0));
		List<BomDetail> bomdetailList=bomDetailService.findList(tempDetail);
		//3.遍历工单中的产品对应的bom详情集合
		//假设一箱为24袋产品,材料类型以bag为前缀的是包装袋，材料类型以fresh为前缀的是主料
		int allNums=workOrder.getAmount()*24;//工单中生产产品的袋数
		double nums=0;//存放海鲜总重量或袋子总数量
		double curSum=0;//某类型原料总数
		List<Material> curMaterialList=new ArrayList<Material>();//存放某类型的每批次原料的集合
		for(BomDetail detail:bomdetailList) {
			//1.获得物料对应的原料的库存总数和每批次原料的集合
			Material m=new Material();
			m.setmType(detail.getmType());
			curSum=materialService.findSum(m);
			curMaterialList=materialService.findList(m);
			//2.计算出当前工单所需物料的数量
			if(detail.getmType().indexOf("bag")>-1 || detail.getmType().indexOf("fresh")>-1) {
				nums=allNums*detail.getmNum();
			}
			//3.库存数量与所需物料数量对比，小于返回false，如果大于就扣减原料数量
			if(curSum<nums) {
				return false;
			}
			//扣减原料数量
			for(Material temp:curMaterialList) {
				if(temp.getQuantity()>=nums) {
					temp.setQuantity(temp.getQuantity()-nums);
					materialService.save(temp);
					break;
				}
				
				nums=nums-temp.getQuantity();
				temp.setQuantity(0);
				materialService.save(temp);
			}			
		}
		return true;		
	}
	/**
	 * 删除工单
	 * @param workOrder
	 * @return
	 */
	public boolean deleteOrder(WorkOrder workOrder) {
		//状态是“未发布”的工单可以删除
		//判断工单的状态
		if(workOrder.getState().equals("unreleased")) {
			//删除
			this.delete(workOrder);
			//修改对应的订单状态为“新订单”newOrder
			Order tempOrder=orderService.get(workOrder.getOrder());
			tempOrder.setStatus("newOrder");
			orderService.save(tempOrder);
			return true;
		}
		return false;
	}
}
