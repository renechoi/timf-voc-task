package timf.voc.task.domain.transportcompany;

import java.util.Optional;

import timf.voc.task.domain.transportcompany.aggregate.DeliveryDriver;

public interface DeliveryDriverReader {
	Optional<DeliveryDriver> get(Long id);

	Optional<DeliveryDriver> get(String token);
}
