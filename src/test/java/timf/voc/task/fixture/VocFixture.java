package timf.voc.task.fixture;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import timf.voc.task.domain.clientcompany.ClientCompany;
import timf.voc.task.domain.transportcompany.aggregate.DeliveryDriver;
import timf.voc.task.domain.voc.VocCommand.VocRegisterRequest;
import timf.voc.task.domain.voc.aggregate.Compensation;
import timf.voc.task.domain.voc.aggregate.Penalty;
import timf.voc.task.domain.voc.aggregate.Voc;

public class VocFixture {

	public static Voc create(VocRegisterRequest vocRequest, DeliveryDriver deliveryDriver, ClientCompany clientCompany) {
		Compensation compensation = CompensationFixture.create(vocRequest.getCompensationDescription(),
			vocRequest.getCompensationAmount());
		Penalty penalty = PenaltyFixture.create();

		Voc voc = Voc.of(vocRequest, clientCompany, deliveryDriver, compensation, penalty);
		voc.setCreatedAt(LocalDateTime.now());
		return voc;
	}

	public static Voc createEmpty() {
		Voc voc = Voc.builder().build();
		voc.setCreatedAt(LocalDateTime.now());
		return voc;
	}

	public static List<Voc> createEmptyAsList() {
		return Stream.of(createEmpty(), createEmpty())
			.collect(Collectors.toList());
	}

	public static List<Voc> createList(){
		return Stream.of(
			create(VocRequestFixture.createRegisterRequest("request1"), DeliveryDriverFixture.create(createEmptyAsList(), false, null), ClientCompanyFixture.create(createEmptyAsList(),0L, false)),
			create(VocRequestFixture.createRegisterRequest("request2"), DeliveryDriverFixture.create(createEmptyAsList(), false, null), ClientCompanyFixture.create(createEmptyAsList(),0L, false))
			).collect(Collectors.toList());
	}
}
