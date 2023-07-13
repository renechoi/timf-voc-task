package timf.voc.task.fixture;

import timf.voc.task.domain.voc.aggregate.Compensation;
import timf.voc.task.domain.voc.aggregate.Voc;

public class CompensationFixture {

	public static Compensation create(Voc voc) {
		return Compensation.builder()
			.description("penalty content1")
			.amount(1000L)
			.voc(voc)
			.build();
	}

	public static Compensation create(String compensationDescription, Long compensationAmount) {
		return Compensation.builder()
			.description(compensationDescription)
			.amount(compensationAmount)
			.build();
	}
}
