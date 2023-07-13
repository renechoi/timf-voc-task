package timf.voc.task.infrastructure.voc;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import timf.voc.task.domain.clientcompany.ClientCompany;
import timf.voc.task.domain.transportcompany.aggregate.DeliveryDriver;
import timf.voc.task.domain.voc.VocCommand;
import timf.voc.task.domain.voc.VocSeriesFactory;
import timf.voc.task.domain.voc.aggregate.Compensation;
import timf.voc.task.domain.voc.aggregate.Penalty;
import timf.voc.task.domain.voc.aggregate.Voc;
import timf.voc.task.domain.voc.aggregate.VocSeriesValidator;

@Component
@RequiredArgsConstructor
public class SimpleVocSeriesFactory implements VocSeriesFactory {

	private final VocRepository vocRepository;
	private final List<VocSeriesValidator> vocSeriesValidators;


	@Override
	public void save(VocCommand.VocRegisterRequest request, ClientCompany clientCompany,
		DeliveryDriver deliveryDriver) {

		Compensation compensation = createCompensation(request);
		vocSeriesValidators.forEach(validator -> validator.validate(compensation, request));

		Penalty penalty = createPenalty(request);
		vocRepository.save(Voc.of(request, clientCompany, deliveryDriver, compensation, penalty));
	}


	private Penalty createPenalty(VocCommand.VocRegisterRequest vocRequest) {
		if (vocRequest.isCompensationRequested()) {
			return Penalty.of(vocRequest.getPenaltyDescription(), vocRequest.getPenaltyAmount());
		}
		return null;
	}

	private Compensation createCompensation(VocCommand.VocRegisterRequest vocRequest) {
		if (vocRequest.isCompensationRequested()) {
			return Compensation.of(vocRequest.getCompensationDescription(), vocRequest.getCompensationAmount());
		}

		return null;
	}
}
