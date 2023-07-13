package timf.voc.task.domain.voc;

import timf.voc.task.domain.clientcompany.ClientCompany;
import timf.voc.task.domain.transportcompany.aggregate.DeliveryDriver;
import timf.voc.task.domain.voc.VocCommand.VocRegisterRequest;

public interface VocSeriesFactory {
	void save(VocRegisterRequest request, ClientCompany clientCompany, DeliveryDriver deliveryDriver);
}
