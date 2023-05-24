package timf.voc.task.fixture;

import timf.voc.task.entity.voc.aggregate.Penalty;
import timf.voc.task.entity.voc.Voc;

public class PenaltyFixture {

	public static Penalty create(boolean approved, String objectionContent, Voc voc) {
		return Penalty.builder()
			.description("penalty content1")
			.amount(1000L)
			.voc(voc)
			.approved(approved)
			.objectionContent(objectionContent)
			.build();
	}
}
