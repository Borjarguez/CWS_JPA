package uo.ri.cws.application.service.training.crud.courseReport.command;

import java.util.List;
import java.util.stream.Collectors;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.CertificateRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.CertificateDto;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Certificate;

public class FindCertificatedByVehicleType implements Command<List<CertificateDto>> {
	private CertificateRepository repo = Factory.repository.forCertificate();

	@Override
	public List<CertificateDto> execute() throws BusinessException {
		List<Certificate> list = repo.findAll().stream().sorted((o1, o2) -> o1.getVehicleType().getName().compareTo(o2.getVehicleType().getName()))
				.collect(Collectors.toList());
		return DtoAssembler.toCertificateDtoList(list);
	}

}
