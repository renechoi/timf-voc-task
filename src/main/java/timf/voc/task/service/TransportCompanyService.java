package timf.voc.task.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import timf.voc.task.dto.DeliveryDriverDto;
import timf.voc.task.dto.response.DeliveryDriverMyPageResponse;
import timf.voc.task.dto.response.VocResponse;
import timf.voc.task.entity.DeliveryDriver;
import timf.voc.task.exception.DeliveryDriverNotFoundException;
import timf.voc.task.repository.DeliveryDriverRepository;
import timf.voc.task.repository.TransportCompanyRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TransportCompanyService {

	private final TransportCompanyRepository transportCompanyRepository;
	private final DeliveryDriverRepository deliveryDriverRepository;

	public DeliveryDriver searchDeliveryDriverEntity(Long id) {
		return findById(id);
	}

	public DeliveryDriverMyPageResponse getMyPage(Long id) {
		DeliveryDriver deliveryDriver = findById(id);

		return DeliveryDriverMyPageResponse.builder()
			.deliveryDriver(DeliveryDriverDto.from(deliveryDriver))
			.vcos(searchDriverVocs(deliveryDriver))
			.build();
	}

	private List<VocResponse> searchDriverVocs(DeliveryDriver deliveryDriver) {
		return deliveryDriver.getVocList().stream().map(VocResponse::from).collect(Collectors.toList());
	}

	private DeliveryDriver findById(Long id){
		return deliveryDriverRepository.findById(id).orElseThrow(DeliveryDriverNotFoundException::new);
	}
}
