package timf.voc.task.fixture;

import java.time.LocalDateTime;
import java.util.List;

import timf.voc.task.entity.DeliveryDriver;
import timf.voc.task.entity.TransportCompany;
import timf.voc.task.entity.voc.Voc;

public class DeliveryDriverFixture {

	public static DeliveryDriver create(List<Voc> vocList, boolean isPenaltyDeducted, TransportCompany transportCompany) {
		DeliveryDriver deliveryDriver = DeliveryDriver.builder()
			.Name("delivery driver1")
			.salary(1000L)
			.isPenaltyDeducted(isPenaltyDeducted)
			.transportCompany(transportCompany)
			.pendingPenaltyAmount(0L)
			.vocList(vocList)
			.build();

		deliveryDriver.setCreatedAt(LocalDateTime.now());
		return deliveryDriver;
	}

	public static DeliveryDriver create(Long id, List<Voc> vocList, boolean isPenaltyDeducted, TransportCompany transportCompany) {
		DeliveryDriver deliveryDriver = DeliveryDriver.builder()
			.id(id)
			.Name("delivery driver1")
			.salary(1000L)
			.isPenaltyDeducted(isPenaltyDeducted)
			.pendingPenaltyAmount(0L)
			.transportCompany(transportCompany)
			.vocList(vocList)
			.build();

		deliveryDriver.setCreatedAt(LocalDateTime.now());
		return deliveryDriver;
	}
}
