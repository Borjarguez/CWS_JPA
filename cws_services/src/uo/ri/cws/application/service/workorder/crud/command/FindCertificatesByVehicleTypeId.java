package uo.ri.cws.application.service.workorder.crud.command;

import java.util.List;
import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.CertificateRepository;
import uo.ri.cws.application.repository.VehicleTypeRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.CertificateDto;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Certificate;
import uo.ri.cws.domain.VehicleType;

public class FindCertificatesByVehicleTypeId implements Command<List<CertificateDto>> {
	private CertificateRepository repo = Factory.repository.forCertificate();
	private VehicleTypeRepository repoType = Factory.repository.forVehicleType();
	private String vehicleTypeId;
	
	public FindCertificatesByVehicleTypeId(String id) {
		this.vehicleTypeId = id;
	}
	
	@Override
	public List<CertificateDto> execute() throws BusinessException {
		Optional<VehicleType> ov = repoType.findById(vehicleTypeId);
		BusinessCheck.isTrue(ov.isPresent(), "Vehicle type does not exist");
		
		List<Certificate> certificates = repo.findCertificatesByVehicleTypeId(vehicleTypeId);
		return  DtoAssembler.toCertificateDtoList(certificates);
	}

}
