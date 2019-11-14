package uo.ri.cws.application.repository;

import java.util.List;

import uo.ri.cws.domain.WorkOrder;

public interface WorkOrderRepository extends Repository<WorkOrder> {

	/**
	 * Method which finds the work orders by its id
	 * 
	 * @param idsAveria, the id's list
	 * @return the work order's list if there's any work order
	 */
	List<WorkOrder> findByIds(List<String> workOrderIds);
}