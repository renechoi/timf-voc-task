package timf.voc.task.fixture;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import timf.voc.task.dto.VocRequest;
import timf.voc.task.entity.ClientCompany;
import timf.voc.task.entity.DeliveryDriver;
import timf.voc.task.entity.voc.Voc;

public class VocFixture {

	public static Voc create(VocRequest vocRequest, DeliveryDriver deliveryDriver, ClientCompany clientCompany) {

		Voc voc = Voc.createVoc(vocRequest, clientCompany, deliveryDriver);
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
}
