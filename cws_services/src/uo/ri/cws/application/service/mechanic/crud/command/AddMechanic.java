package uo.ri.cws.application.service.mechanic.crud.command;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.MechanicRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.mechanic.MechanicDto;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Mechanic;

public class AddMechanic implements Command<MechanicDto> {
	private MechanicRepository repo = Factory.repository.forMechanic();
	private MechanicDto dto;

	public AddMechanic(MechanicDto mecanico) {
		this.dto = mecanico;
	}

	public MechanicDto execute() throws BusinessException {
		BusinessCheck.isNull(dto, "Dto is missing");
		Mechanic m = new Mechanic(dto.dni, dto.name, dto.surname);

		repo.add(m);

		dto.id = m.getId();
		return dto;
	}

}
