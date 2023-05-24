package timf.voc.task.fixture;

import timf.voc.task.entity.Compensation;
import timf.voc.task.entity.Penalty;
import timf.voc.task.entity.Voc;

public class CompensationFixture {

	public static Compensation create(Voc voc) {
		return Compensation.builder()
			.description("penalty content1")
			.amount(1000L)
			.voc(voc)
			.build();
	}
}
