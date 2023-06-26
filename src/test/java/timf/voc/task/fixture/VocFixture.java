package timf.voc.task.fixture;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import timf.voc.task.domain.clientcompany.ClientCompany;
import timf.voc.task.domain.transportcompany.aggregate.DeliveryDriver;
import timf.voc.task.domain.voc.aggregate.Voc;

public class VocFixture {

	public static Voc create(VocRequest vocRequest, DeliveryDriver deliveryDriver, ClientCompany clientCompany) {

		Voc voc = Voc.of(vocRequest, clientCompany, deliveryDriver);
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
			create(VocRequestFixture.create("request1"), DeliveryDriverFixture.create(createEmptyAsList(), false, null), ClientCompanyFixture.create(createEmptyAsList(),0L, false)),
			create(VocRequestFixture.create("request2"), DeliveryDriverFixture.create(createEmptyAsList(), false, null), ClientCompanyFixture.create(createEmptyAsList(),0L, false))
			).collect(Collectors.toList());
	}
}
