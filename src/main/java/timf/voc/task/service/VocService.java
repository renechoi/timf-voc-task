package timf.voc.task.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import timf.voc.task.dto.request.DeliveryDriverPenaltyRequest;
import timf.voc.task.dto.request.VocRequest;
import timf.voc.task.dto.response.CompensationResponse;
import timf.voc.task.dto.response.VocResponse;
import timf.voc.task.entity.ClientCompany;
import timf.voc.task.entity.DeliveryDriver;
import timf.voc.task.entity.voc.Voc;
import timf.voc.task.entity.voc.aggregate.PenaltyApproval;
import timf.voc.task.entity.voc.aggregate.VocStatus;
import timf.voc.task.exception.VocNotFoundException;
import timf.voc.task.repository.CompensationRepository;
import timf.voc.task.repository.VocRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class VocService {

	private final ClientCompanyService clientCompanyService;
	private final TransportCompanyService transportCompanyService;
	private final ClaimService claimService;

	private final VocRepository vocRepository;
	private final CompensationRepository compensationRepository;

	@Transactional
	public void registerVoc(VocRequest vocRequest) {
		ClientCompany clientCompany = getClientCompany(vocRequest);
		DeliveryDriver deliveryDriver = getDeliveryDriver(vocRequest);

		vocRepository.save(Voc.createVoc(vocRequest, clientCompany, deliveryDriver));

		/**
		 * claim status를 현재 메서드에서 처리해주는 이유는 같은 트랜잭션 내의 동작이라고 보았기 때문이다.
		 */
		claimService.handleStatus(vocRequest, true);

		// todo 구독하는 page에 새로운 voc가 등록되었으니 화면을 refresh 해야 한다는 알림 서비스 구현
	}

	public List<VocResponse> getVocs() {
		return vocRepository.findAll().stream().map(VocResponse::from).collect(Collectors.toList());
	}

	public List<CompensationResponse> getCompensations() {
		return compensationRepository.findAll().stream().map(CompensationResponse::from).collect(Collectors.toList());
	}

	@Transactional
	public void handleDriverPenalty(DeliveryDriverPenaltyRequest deliveryDriverPenaltyRequest) {

		Voc voc = findById(deliveryDriverPenaltyRequest.getVocId());
		voc.updatePenaltyStatus(deliveryDriverPenaltyRequest.getPenaltyApproval(),
			deliveryDriverPenaltyRequest.getObjectionContent());

		if (deliveryDriverPenaltyRequest.getPenaltyApproval() == PenaltyApproval.APPROVED) {
			voc.imposePenalty();
			voc.compensate();
			voc.updateStatus(VocStatus.END);
			return;

			/**
			 * 아래의 방식처럼 각각의 service layer에 역할을 위임할 수도 있지만
			 * voc 객체가 행위를 처리하도록 페널티 부과와 보상 처리 로직을 위임했다.
			 *
			 * transportCompanyService.imposePenalty(voc.getPenalty().getAmount());
			 * clientCompanyService.compensate(voc.getCompensation().getAmount());
			 */
		}
	}

	private DeliveryDriver getDeliveryDriver(VocRequest vocRequest) {
		return transportCompanyService.searchDeliveryDriverEntity(vocRequest.getDeliveryDriverId());
	}

	private ClientCompany getClientCompany(VocRequest vocRequest) {
		return clientCompanyService.searchClientCompanyEntity(vocRequest.getClientCompanyId());
	}

	private Voc findById(Long id) {
		return vocRepository.findById(id).orElseThrow(VocNotFoundException::new);
	}
}
