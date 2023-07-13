package timf.voc.task.domain.transportcompany;

import timf.voc.task.domain.transportcompany.aggregate.DeliveryDriver;

public interface DeliveryDriverPersister {
	void save(DeliveryDriver deliveryDriver);
}
