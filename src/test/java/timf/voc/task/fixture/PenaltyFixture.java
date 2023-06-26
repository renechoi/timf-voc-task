package timf.voc.task.fixture;

import timf.voc.task.domain.voc.aggregate.Penalty;
import timf.voc.task.domain.voc.aggregate.Voc;

public class PenaltyFixture {

	public static Penalty create(boolean approved, String objectionContent, Voc voc) {
		return Penalty.builder()
			.description("penalty content1")
			.amount(1000L)
			.voc(voc)
			.penaltyApproval(PenaltyApproval.APPROVED)
			.objectionContent(objectionContent)
			.build();
	}
}
