package uo.ri.cws.infrastructure.persistence.jpa.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.repository.CertificateRepository;
import uo.ri.cws.domain.Certificate;
import uo.ri.cws.domain.Mechanic;
import uo.ri.cws.domain.VehicleType;
import uo.ri.cws.infrastructure.persistence.jpa.util.BaseJpaRepository;
import uo.ri.cws.infrastructure.persistence.jpa.util.Jpa;

public class CertificateJpaRepository extends BaseJpaRepository<Certificate> implements CertificateRepository{

	@Override
	public Optional<BigDecimal> findTotalHoursCertificate(Mechanic m, VehicleType v) {
		return Jpa.getManager()
				.createNamedQuery("Certificate.findTotalHours", BigDecimal.class)
				.setParameter(1, m)
				.setParameter(2, v)
				.getResultStream().findFirst();
	}

	@Override
	public Optional<Certificate> findCertificateByMechanicVehicleTypeIDs(Mechanic m, VehicleType v) {
		return Jpa.getManager()
				.createNamedQuery("Certificate.findCertificateByMechanicVehicleTypeIDs", Certificate.class)
				.setParameter(1, m)
				.setParameter(2, v)
				.getResultStream()
				.findFirst();
	}

	@Override
	public List<Certificate> findCertificatesByVehicleTypeId(String vehicleTypeId) {
		return Jpa.getManager()
				.createNamedQuery("Certificate.findByVehicleTypeId", Certificate.class)
				.setParameter(1, vehicleTypeId)
				.getResultList();
	}

}
