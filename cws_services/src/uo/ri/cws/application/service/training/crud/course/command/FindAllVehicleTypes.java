package uo.ri.cws.application.service.training.crud.course.command;

import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.VehicleTypeRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.vehicletype.VehicleTypeDto;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;

public class FindAllVehicleTypes implements Command<List<VehicleTypeDto>>{
	private VehicleTypeRepository repo = Factory.repository.forVehicleType();
	@Override
	public List<VehicleTypeDto> execute() throws BusinessException {
		return DtoAssembler.toVehicleTypeDtoList(repo.findAll());
	}

}
