package uo.ri.cws.application.service.mechanic.crud.command;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.MechanicRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.mechanic.MechanicDto;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Mechanic;

public class FindMechanicById implements Command<Optional<MechanicDto>>{
	private MechanicRepository repo = Factory.repository.forMechanic();
	private String id;

	public FindMechanicById(String id) {
		this.id = id;
	}

	public Optional<MechanicDto> execute() throws BusinessException {
		Optional<Mechanic> om = repo.findById(id);
		BusinessCheck.isTrue(om.isPresent(), "Mechanic does not exist");
		return om.isPresent() ? Optional.of(DtoAssembler.toDto(om.get())) : Optional.empty();
	}

}
