package timf.voc.task.interfaces.transportcompany;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import timf.voc.task.config.converter.EntityAndDtoConverter;
import timf.voc.task.domain.transportcompany.aggregate.DeliveryDriver;
import timf.voc.task.domain.voc.aggregate.Penalty.PenaltyApproval;
import timf.voc.task.interfaces.voc.VocDto;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryDriverDto {

	private Long id;
	private String name;
	private Long salary;
	private Long pendingPenaltyAmount;
	private boolean isPenaltyDeducted;
	private TransportCompanyDto transportCompany;
	private List<VocDto> vocs;

	public static DeliveryDriverDto from(DeliveryDriver deliveryDriver) {
		return EntityAndDtoConverter.convert(deliveryDriver, DeliveryDriverDto.class);
	}


	@Data
	@Builder
	public static class Main{
		private Long id;
		private String name;
		private Long salary;
		private Long pendingPenaltyAmount;
		private boolean isPenaltyDeducted;
		private TransportCompanyDto transportCompany;
		private List<VocDto> vocs;

		public static DeliveryDriverDto from(DeliveryDriver deliveryDriver) {
			return EntityAndDtoConverter.convert(deliveryDriver, DeliveryDriverDto.class);
		}
	}

	@Data
	@Builder
	public static class DeliveryDriverMyPageResponse{
		private DeliveryDriverDto deliveryDriver;

		private List<VocDto.VocResponse> vcos;
	}

	@Data
	@Builder
	@NoArgsConstructor
	public static class DeliveryDriverVocProcessRequest {
		private Long deliveryDriverId;
		private Long vocId;
		private PenaltyApproval penaltyApproval;
		private String objectionContent;

		public static DeliveryDriverVocProcessRequest empty() {
			return new DeliveryDriverVocProcessRequest();
		}

	}
}
