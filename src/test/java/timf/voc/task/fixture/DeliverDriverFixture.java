package timf.voc.task.fixture;

import java.util.List;

import timf.voc.task.entity.ClientCompany;
import timf.voc.task.entity.DeliveryDriver;
import timf.voc.task.entity.TransportCompany;
import timf.voc.task.entity.Voc;

public class DeliverDriverFixture {

	public static DeliveryDriver create(List<Voc> vocList, boolean isPenaltyDeducted, TransportCompany transportCompany) {
		return DeliveryDriver.builder()
			.Name("delivery driver1")
			.salary(1000L)
			.isPenaltyDeducted(isPenaltyDeducted)
			.transportCompany(transportCompany)
			.vocList(vocList)
			.build();
	}
}
