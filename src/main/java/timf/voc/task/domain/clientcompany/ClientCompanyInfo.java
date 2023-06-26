package timf.voc.task.domain.clientcompany;

import static timf.voc.task.config.converter.EntityAndDtoConverter.*;

import java.util.List;

import lombok.Data;
import timf.voc.task.domain.voc.VocInfo;

@Data
public class ClientCompanyInfo {
	private Long id;
	private String clientCompanyToken;
	private String companyName;
	private String contacts;
	private String description;
	private Long compensationPayment;
	private boolean isCompensationPaid;
	private List<VocInfo> vocs;

	public static ClientCompanyInfo from(ClientCompany clientCompany) {
		ClientCompanyInfo info = convert(clientCompany, ClientCompanyInfo.class);
		info.setVocs(convert(clientCompany.getVocs(), VocInfo.class));
		return info;
	}
}
