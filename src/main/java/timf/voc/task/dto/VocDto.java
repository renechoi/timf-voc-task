package timf.voc.task.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import timf.voc.task.entity.voc.Voc;
import timf.voc.task.entity.voc.aggregate.ClaimResponsibility;
import timf.voc.task.entity.voc.aggregate.VocStatus;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VocDto {

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

	/**
	 * DTO 매핑시 voc를 기준으로 단순 참조용이므로 관련 엔티티들의 DTO 생성은
	 * 구현한 컨버터를 기반으로 기본형들에 대한 변환만 수행하도록 구현하였다. (순환 참조 발생시엔 set 방식으로 사후 처리를 해주어야 한다)
	 */
	public static VocDto from(Voc voc) {
		return VocDto.builder()
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