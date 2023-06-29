package timf.voc.task.domain.voc;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import timf.voc.task.config.exception.ClientCompanyNotFoundException;
import timf.voc.task.config.exception.DeliveryDriverNotFoundException;
import timf.voc.task.domain.clientcompany.ClientCompanyReader;
import timf.voc.task.domain.transportcompany.DeliveryDriverReader;
import timf.voc.task.domain.voc.VocCommand.VocReigsterRequest;
import timf.voc.task.domain.voc.VocCommand.VocRegisterRequest;
import timf.voc.task.domain.clientcompany.ClientCompany;
import timf.voc.task.domain.transportcompany.aggregate.DeliveryDriver;
import timf.voc.task.domain.voc.aggregate.Voc;
import timf.voc.task.config.exception.VocNotFoundException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SimpleVocService implements VocService {

	private final ClientCompanyReader clientCompanyReader;
	private final DeliveryDriverReader deliveryDriverReader;
	private final VocReader vocReader;
	private final CompensationReader compensationReader;
	private final VocSeriesFactory vocSeriesFactory;

	@Override
	@Transactional
	public void registerVoc(VocRegisterRequest request) {
		ClientCompany clientCompany = clientCompanyReader.get(request.getClientCompanyId()).orElseThrow(
			ClientCompanyNotFoundException::new);

		DeliveryDriver deliveryDriver =deliveryDriverReader.get(request.getDeliveryDriverId()).orElseThrow(
			DeliveryDriverNotFoundException::new);

		vocSeriesFactory.save(request, clientCompany, deliveryDriver);
	}

	@Override
	public List<VocInfo> retrieveVocs() {
		return vocReader.get().stream().map(VocInfo::from).collect(Collectors.toList());
	}

	@Override
	public List<CompensationInfo> retrieveCompensations() {
		return compensationReader.get().stream().map(CompensationInfo::from).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public void handleDriverApproval(VocReigsterRequest request) {
		Voc voc = vocReader.get(request.getVocId()).orElseThrow(VocNotFoundException::new);
		voc.updatePenaltyStatusApproved();
		voc.imposePenalty();
		voc.compensate();
		voc.updateStatusEnd();
	}
}
