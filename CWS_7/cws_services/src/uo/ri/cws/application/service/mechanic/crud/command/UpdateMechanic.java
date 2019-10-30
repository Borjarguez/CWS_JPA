package uo.ri.cws.application.service.mechanic.crud.command;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.MechanicRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.mechanic.MechanicDto;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Mechanic;

public class UpdateMechanic implements Command<Void>{
	private MechanicRepository repo = Factory.repository.forMechanic();
	private MechanicDto dto;

	public UpdateMechanic(MechanicDto dto) {
		this.dto = dto;
	}

	public Void execute() throws BusinessException {
		Optional<Mechanic> om = repo.findById(dto.id);
		
		BusinessCheck.isTrue(om.isPresent(), "Mechanic does not exist");
		Mechanic m = om.get();
		
		m.setName(dto.name);
		m.setSurname(dto.surname);
		return null;
	}

}
