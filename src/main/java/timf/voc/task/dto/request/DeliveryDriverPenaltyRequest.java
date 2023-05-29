package timf.voc.task.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import timf.voc.task.entity.voc.aggregate.PenaltyApproval;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryDriverPenaltyRequest {

	private Long deliveryDriverId;
	private Long vocId;
	private PenaltyApproval penaltyApproval;
	private String objectionContent;

	public static DeliveryDriverPenaltyRequest empty() {
		return new DeliveryDriverPenaltyRequest();
	}

	public boolean isApproved(){
		return this.penaltyApproval == PenaltyApproval.APPROVED;
	}
}
