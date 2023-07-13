package timf.voc.task.domain.transportcompany;

import static timf.voc.task.config.converter.EntityAndDtoConverter.*;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import timf.voc.task.domain.transportcompany.aggregate.DeliveryDriver;
import timf.voc.task.domain.voc.VocInfo;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryDriverInfo {
	private Long id;
	private String deliveryDriverToken;
	private String name;
	private Long salary;
	private Long pendingPenaltyAmount;
	private boolean isPenaltyDeducted;
	private TransportCompanyInfo transportCompany;
	private List<VocInfo> vocs;

	public static DeliveryDriverInfo from(DeliveryDriver deliveryDriver){
		DeliveryDriverInfo info = convert(deliveryDriver, DeliveryDriverInfo.class);
		info.setVocs(convert(deliveryDriver.getVocList(), VocInfo.class));
		return info;
	}

}
