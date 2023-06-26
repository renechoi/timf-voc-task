package timf.voc.task.domain.voc;

import timf.voc.task.domain.clientcompany.ClientCompany;
import timf.voc.task.domain.transportcompany.aggregate.DeliveryDriver;

public interface VocSeriesFactory {
	void save(VocCommand.VocRegisterRequest request, ClientCompany clientCompany, DeliveryDriver deliveryDriver);
}
