package timf.voc.task.infrastructure.transportcompany;

import java.util.Optional;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import timf.voc.task.domain.transportcompany.DeliveryDriverReader;
import timf.voc.task.domain.transportcompany.aggregate.DeliveryDriver;

@Component
@RequiredArgsConstructor
public class SimpleDeliveryDriverReader implements DeliveryDriverReader {

	private final DeliveryDriverRepository deliveryDriverRepository;

	@Override
	public Optional<DeliveryDriver> get(Long id) {
		return deliveryDriverRepository.findById(id);
	}

	@Override
	public Optional<DeliveryDriver> get(String token) {
		return  deliveryDriverRepository.findDeliveryDriverByDeliveryDriverToken(token);
	}
}
