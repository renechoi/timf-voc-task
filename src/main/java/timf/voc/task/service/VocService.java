package timf.voc.task.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import timf.voc.task.dto.request.VocRequest;
import timf.voc.task.dto.response.VocResponse;
import timf.voc.task.entity.ClientCompany;
import timf.voc.task.entity.DeliveryDriver;
import timf.voc.task.entity.voc.Voc;
import timf.voc.task.repository.VocRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class VocService {

	private final ClientCompanyService clientCompanyService;
	private final TransportCompanyService transportCompanyService;
	private final ClaimService claimService;

	private final VocRepository vocRepository;

	@Transactional
	public void registerVoc(VocRequest vocRequest){
		ClientCompany clientCompany = getClientCompany(vocRequest);
		DeliveryDriver deliveryDriver = getDeliveryDriver(vocRequest);

		vocRepository.save(Voc.createVoc(vocRequest, clientCompany, deliveryDriver));

		/**
		 * claim status를 현재 메서드에서 처리해주는 이유는 같은 트랜잭션 내의 동작이라고 보았기 때문이다.
		 */
		claimService.handleStatus(vocRequest, true);
	}

	public List<VocResponse> getVocs() {
		return vocRepository.findAll()
			.stream()
			.map(VocResponse::from)
			.collect(Collectors.toList());
	}

	private DeliveryDriver getDeliveryDriver(VocRequest vocRequest) {
		return transportCompanyService.searchDeliveryDriverEntity(
			vocRequest.getDeliveryDriverId());
	}

	private ClientCompany getClientCompany(VocRequest vocRequest) {
		return clientCompanyService.searchClientCompanyEntity(vocRequest.getClientCompanyId());
	}
}
