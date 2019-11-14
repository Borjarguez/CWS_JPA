package uo.ri.cws.application.service.training.crud.certificate.command;

import java.util.List;
import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.CertificateRepository;
import uo.ri.cws.application.repository.MechanicRepository;
import uo.ri.cws.application.repository.VehicleTypeRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Certificate;
import uo.ri.cws.domain.Mechanic;
import uo.ri.cws.domain.VehicleType;

public class GenerateCertificates implements Command<Integer>{
	private CertificateRepository repo = Factory.repository.forCertificate();
	private VehicleTypeRepository repoType = Factory.repository.forVehicleType();
	private MechanicRepository repoMechanic = Factory.repository.forMechanic();
	
	@Override
	public Integer execute() throws BusinessException {
		int numCertificates = 0;
		int totalHours = 0;
		List<VehicleType> types = repoType.findAll();
		List<Mechanic> mchanics = repoMechanic.findAll();
		
		for(VehicleType v : types) {
			for(Mechanic m : mchanics) {
				Optional<Certificate> cer = repo.findCertificateByMechanicVehicleTypeIDs(m,v);
				if(!cer.isPresent()) {
					totalHours = repo.findTotalHoursCertificate(m, v).get().intValue();
					
					if(totalHours >= v.getMinTrainingHours()) {
						Certificate c = new Certificate(m,v);
						repo.add(c);
						numCertificates++;
					}
					
				}
			}
		}
		return numCertificates;
	}

}
