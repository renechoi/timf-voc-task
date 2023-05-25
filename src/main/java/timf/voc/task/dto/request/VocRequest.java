package timf.voc.task.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import timf.voc.task.entity.voc.aggregate.ClaimResponsibility;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VocRequest {

	private Long claimId;
	private String description;
	private boolean claimReceived;
	private boolean compensationRequested;
	private ClaimResponsibility claimResponsibility;
	private Long deliveryDriverId;
	private Long clientCompanyId;
	private Long compensationAmount;
	private Long penaltyAmount;
	private String compensationDescription;
	private String penaltyDescription;
}
