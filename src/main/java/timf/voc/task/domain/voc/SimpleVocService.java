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
import timf.voc.task.domain.voc.VocCommand.VocProcessRequest;
import timf.voc.task.domain.voc.VocCommand.VocRegisterRequest;
import timf.voc.task.domain.clientcompany.ClientCompany;
import timf.voc.task.domain.transportcompany.aggregate.DeliveryDriver;
import timf.voc.task.domain.voc.aggregate.Voc;
import timf.voc.task.config.exception.VocNotFoundException;
import timf.voc.task.infrastructure.voc.VocRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SimpleVocService {

	private final VocRepository vocRepository;

	private final ClientCompanyReader clientCompanyReader;
	private final DeliveryDriverReader deliveryDriverReader;
	private final VocReader vocReader;
	private final CompensationReader compensationReader;

	@Transactional
	public void registerVoc(VocRegisterRequest request) {
		ClientCompany clientCompany = clientCompanyReader.get(request.getClientCompanyId()).orElseThrow(
			ClientCompanyNotFoundException::new);

		DeliveryDriver deliveryDriver =deliveryDriverReader.get(request.getDeliveryDriverId()).orElseThrow(
			DeliveryDriverNotFoundException::new);

		vocRepository.save(Voc.createVoc(request, clientCompany, deliveryDriver));
	}

	public List<VocInfo> retrieveVocs() {
		return vocReader.get().stream().map(VocInfo::from).collect(Collectors.toList());
	}

	public List<CompensationInfo> retrieveCompensations() {
		return compensationReader.get().stream().map(CompensationInfo::from).collect(Collectors.toList());
	}

	@Transactional
	public void handleDriverApproval(VocProcessRequest request) {
		Voc voc = vocReader.get(request.getVocId()).orElseThrow(VocNotFoundException::new);
		voc.updatePenaltyStatusApproved();
		voc.imposePenalty();
		voc.compensate();
		voc.updateStatusEnd();
	}
}
