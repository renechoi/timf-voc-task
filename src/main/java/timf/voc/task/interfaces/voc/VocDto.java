package timf.voc.task.interfaces.voc;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import timf.voc.task.config.converter.EntityAndDtoConverter;
import timf.voc.task.domain.voc.aggregate.Compensation;
import timf.voc.task.domain.voc.aggregate.Penalty;
import timf.voc.task.domain.voc.aggregate.Penalty.PenaltyApproval;
import timf.voc.task.domain.voc.aggregate.Voc.ClaimResponsibility;
import timf.voc.task.domain.voc.aggregate.Voc.VocStatus;
import timf.voc.task.interfaces.clientcompany.ClientCompanyDto;
import timf.voc.task.interfaces.transportcompany.DeliveryDriverDto;
import timf.voc.task.domain.voc.aggregate.Voc;

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

	@Data
	@Builder
	public static class Main{
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

	@Data
	@Builder
	@AllArgsConstructor
	public static class VocRequest{
		private Long claimId;
		private String description;
		private boolean claimReceived;
		private boolean compensationRequested;
		private ClaimResponsibility claimResponsibility;
		private Long deliveryDriverId;
		private String deliveryDriverToken;
		private Long clientCompanyId;
		private String clientCompanyToken;
		private Long compensationAmount;
		private Long penaltyAmount;
		private String compensationDescription;
		private String penaltyDescription;
	}

	@Data
	@Builder
	public static class VocResponse{
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
				.compensation( voc.getCompensation() == null ? null : CompensationDto.from(voc.getCompensation()))
				.penalty(voc.getPenalty() == null ? null : PenaltyDto.from(voc.getPenalty()))
				.status(voc.getStatus())
				.build();
		}
	}

	@Data
	@Builder
	public static class CompensationDto {
		private Long id;
		private String description;
		private Long amount;
		private VocDto voc;

		public static CompensationDto from(Compensation compensation) {
			return EntityAndDtoConverter.convert(compensation, CompensationDto.class);
		}
	}

	@Data
	@Builder
	public static class PenaltyDto {
		private Long id;
		private String description;
		private Long amount;
		private VocDto voc;
		private PenaltyApproval penaltyApproval;
		private String objectionContent;

		public static PenaltyDto from(Penalty penalty) {
			return EntityAndDtoConverter.convert(penalty, PenaltyDto.class);
		}
	}

	@Data
	@Builder
	public static class CompensationResponse {
		private Long id;
		private String description;
		private Long amount;
		private VocDto voc;

		public static CompensationResponse from(Compensation compensation) {
			return CompensationResponse.builder()
				.id(compensation.getId())
				.description(compensation.getDescription())
				.amount(compensation.getAmount())
				.voc(VocDto.from(compensation.getVoc()))
				.build();
		}
	}

}