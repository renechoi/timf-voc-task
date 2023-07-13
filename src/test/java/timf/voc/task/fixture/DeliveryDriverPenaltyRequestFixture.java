package timf.voc.task.fixture;

import timf.voc.task.domain.voc.VocCommand;
import timf.voc.task.domain.voc.aggregate.Penalty.PenaltyApproval;
import timf.voc.task.interfaces.transportcompany.DeliveryDriverDto.DeliveryDriverVocProcessRequest;

public class DeliveryDriverPenaltyRequestFixture {
	public static VocCommand.VocProcessRequest create_Approved() {
		return new VocCommand.VocProcessRequest (1L, 1L, PenaltyApproval.APPROVED, "Objection content");
	}

	public static DeliveryDriverVocProcessRequest create_Denied(String ObjectionContent) {
		return new DeliveryDriverVocProcessRequest(1L, 1L, PenaltyApproval.DENIED, ObjectionContent);
	}
}
