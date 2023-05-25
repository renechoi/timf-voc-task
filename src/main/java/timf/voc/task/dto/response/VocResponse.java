package timf.voc.task.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import timf.voc.task.dto.ClientCompanyDto;
import timf.voc.task.dto.CompensationDto;
import timf.voc.task.dto.DeliveryDriverDto;
import timf.voc.task.dto.PenaltyDto;
import timf.voc.task.entity.voc.Voc;
import timf.voc.task.entity.voc.aggregate.ClaimResponsibility;
import timf.voc.task.entity.voc.aggregate.VocStatus;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VocResponse {

	private Long id;
	private String description;
	private boolean claimReceived;
	private boolean compensationRequested;
	private ClaimResponsibility claimResponsibility;
	private DeliveryDriverDto deliveryDriver;
	private ClientCompanyDto clientCompany;
	private CompensationDto compensation;
	private PenaltyDto penalty;
	private VocStatus status;

	public static VocResponse from(Voc voc) {
		return VocResponse.builder()
			.id(voc.getId())
			.description(voc.getDescription())
			.claimReceived(voc.isClaimReceived())
			.compensationRequested(voc.isCompensationRequested())
			.claimResponsibility(voc.getClaimResponsibility())
			.deliveryDriver(DeliveryDriverDto.from(voc.getDeliveryDriver()))
			.clientCompany(ClientCompanyDto.from(voc.getClientCompany()))
			.compensation(CompensationDto.from(voc.getCompensation()))
			.penalty(PenaltyDto.from(voc.getPenalty()))
			.status(voc.getStatus())
			.build();
	}
}
