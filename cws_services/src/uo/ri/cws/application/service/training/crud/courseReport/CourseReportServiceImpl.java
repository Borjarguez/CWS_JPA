package uo.ri.cws.application.service.training.crud.courseReport;

import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.CertificateDto;
import uo.ri.cws.application.service.training.CourseReportService;
import uo.ri.cws.application.service.training.TrainingForMechanicRow;
import uo.ri.cws.application.service.training.TrainingHoursRow;
import uo.ri.cws.application.service.training.crud.courseReport.command.FindCertificatedByVehicleType;
import uo.ri.cws.application.service.training.crud.courseReport.command.FindTrainingByMechanicId;
import uo.ri.cws.application.service.training.crud.courseReport.command.FindTrainingByVehicleTypeAndMechanic;
import uo.ri.cws.application.util.command.CommandExecutor;

public class CourseReportServiceImpl implements CourseReportService {
	
	private CommandExecutor executor = Factory.executor.forExecutor();

	@Override
	public List<TrainingForMechanicRow> findTrainigByMechanicId(String mechanicId) throws BusinessException {
		return executor.execute(new FindTrainingByMechanicId(mechanicId));
	}

	@Override
	public List<TrainingHoursRow> findTrainingByVehicleTypeAndMechanic() throws BusinessException {
		return executor.execute(new FindTrainingByVehicleTypeAndMechanic());
	}

	@Override
	public List<CertificateDto> findCertificatedByVehicleType() throws BusinessException {
		return executor.execute(new FindCertificatedByVehicleType());
	}

}
