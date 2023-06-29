package timf.voc.task.domain.voc;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import timf.voc.task.domain.voc.aggregate.Penalty.PenaltyApproval;
import timf.voc.task.domain.voc.aggregate.Voc.ClaimResponsibility;
import timf.voc.task.interfaces.transportcompany.DeliveryDriverDto;

public class VocCommand {

	@Data
	@Builder
	public static class VocRegisterRequest{
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

	@Data
	@Builder
	@NoArgsConstructor
	public static class VocReigsterRequest {
		private Long deliveryDriverId;
		private Long vocId;
		private PenaltyApproval penaltyApproval;
		private String objectionContent;

		public static DeliveryDriverDto.DeliveryDriverVocProcessRequest empty() {
			return new DeliveryDriverDto.DeliveryDriverVocProcessRequest();
		}

		public boolean isApproved(){
			return this.penaltyApproval == PenaltyApproval.APPROVED;
		}
	}
}
