package timf.voc.task.fixture;

public class DeliveryDriverPenaltyRequestFixture {
	public static DeliveryDriverPenaltyRequest create_Approved(){
		return new DeliveryDriverPenaltyRequest(1L, 1L, PenaltyApproval.APPROVED, "Objection content");
	}

	public static DeliveryDriverPenaltyRequest create_Denied(String ObjectionContent){
		return new DeliveryDriverPenaltyRequest(1L, 1L, PenaltyApproval.DENIED, ObjectionContent);
	}
}
