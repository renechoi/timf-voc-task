package timf.voc.task.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryDriverPenaltyRequest {

	private Long deliveryDriverId;

	private Long vocId;

	private boolean signed;
	private String objectionContent;

	public static DeliveryDriverPenaltyRequest empty() {
		return new DeliveryDriverPenaltyRequest();
	}
}
