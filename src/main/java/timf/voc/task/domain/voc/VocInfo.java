package timf.voc.task.domain.voc;

import lombok.Data;
import timf.voc.task.config.converter.EntityAndDtoConverter;
import timf.voc.task.domain.clientcompany.ClientCompanyInfo;
import timf.voc.task.domain.transportcompany.DeliveryDriverInfo;
import timf.voc.task.domain.voc.aggregate.Voc;
import timf.voc.task.domain.voc.aggregate.Voc.ClaimResponsibility;
import timf.voc.task.domain.voc.aggregate.Voc.VocStatus;

@Data
public class VocInfo {
	private Long id;
	private String token;
	private String description;
	private boolean claimReceived;
	private boolean compensationRequested;
	private ClaimResponsibility claimResponsibility;
	private DeliveryDriverInfo deliveryDriver;
	private ClientCompanyInfo clientCompany;
	private CompensationInfo compensation;
	private PenaltyInfo penalty;
	private VocStatus status;

	public static VocInfo from(Voc voc) {
		VocInfo info = EntityAndDtoConverter.convert(voc, VocInfo.class);
		return info;
	}
}



