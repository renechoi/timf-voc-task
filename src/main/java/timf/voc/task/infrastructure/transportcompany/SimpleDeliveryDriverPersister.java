package timf.voc.task.infrastructure.transportcompany;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import timf.voc.task.domain.transportcompany.DeliveryDriverPersister;
import timf.voc.task.domain.transportcompany.aggregate.DeliveryDriver;

@Component
@RequiredArgsConstructor
public class SimpleDeliveryDriverPersister implements DeliveryDriverPersister {

	private final DeliveryDriverRepository deliveryDriverRepository;

	@Override
	public void save(DeliveryDriver deliveryDriver) {
		deliveryDriverRepository.save(deliveryDriver);
	}
}
