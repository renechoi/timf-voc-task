package timf.voc.task.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
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

	public DeliveryDriver searchDeliveryDriverEntity(Long deliveryDriverId) {
		return deliveryDriverRepository.findById(deliveryDriverId).orElseThrow(DeliveryDriverNotFoundException::new);
	}
}
