package uo.ri.cws.application.service.mechanic.crud.command;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.MechanicRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Mechanic;

public class DeleteMechanic implements Command<Void>{
	private MechanicRepository repo = Factory.repository.forMechanic();
	private String mechanicId;

	public DeleteMechanic(String idMecanico) {
		this.mechanicId = idMecanico;
	}

	public Void execute() throws BusinessException {
		Optional<Mechanic> om = repo.findById(mechanicId);

		BusinessCheck.isTrue(om.isPresent(), "Mechanic does not exist");
		Mechanic m = om.get();
		BusinessCheck.isTrue(m.getInterventions().size() == 0, "Mechanic can't be removed: it has interventions");
		
		repo.remove(m);
		return null;
	}

}
