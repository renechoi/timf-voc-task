package timf.voc.task.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import timf.voc.task.config.converter.EntityAndDtoConverter;
import timf.voc.task.entity.DeliveryDriver;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryDriverDto {

	private Long id;
	private String Name;
	private Long salary;
	private boolean isPenaltyDeducted;
	private TransportCompanyDto transportCompany;
	private List<VocDto> vocs;

	public static DeliveryDriverDto from(DeliveryDriver deliveryDriver) {
		return EntityAndDtoConverter.convert(deliveryDriver, DeliveryDriverDto.class);
	}
}
