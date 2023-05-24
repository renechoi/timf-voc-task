package timf.voc.task.fixture;

import java.time.LocalDateTime;
import java.util.List;

import timf.voc.task.entity.ClientCompany;
import timf.voc.task.entity.voc.aggregate.Compensation;
import timf.voc.task.entity.DeliveryDriver;
import timf.voc.task.entity.voc.aggregate.Penalty;
import timf.voc.task.entity.voc.Voc;
import timf.voc.task.entity.voc.aggregate.ClaimResponsibility;

public class VocFixture {

	public static Voc create(ClaimResponsibility claimResponsibility, DeliveryDriver deliveryDriver,
		ClientCompany clientCompany, Compensation compensation, Penalty penalty) {
		return Voc.builder()
			.description("voc content1")
			.claimReceived(true)
			.compensationRequested(true)
			.claimResponsibility(claimResponsibility)
			.deliveryDriver(deliveryDriver)
			.clientCompany(clientCompany)
			.compensation(compensation)
			.penalty(penalty)
			.build();
	}

	public static Voc createEmpty() {
		Voc voc = Voc.builder().build();
		voc.setCreatedAt(LocalDateTime.now());
		return voc;
	}

	public static List<Voc> createEmptyAsList() {
		Voc voc1 = Voc.builder().build();
		voc1.setCreatedAt(LocalDateTime.now());

		Voc voc2 = Voc.builder().build();
		voc2.setCreatedAt(LocalDateTime.now());

		return List.of(voc1, voc2);
	}
}
