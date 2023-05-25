package timf.voc.task.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import timf.voc.task.config.converter.EntityAndDtoConverter;
import timf.voc.task.entity.ClientCompany;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientCompanyDto {

	private Long id;
	private String companyName;
	private String contacts;
	private String description;
	private Long compensationPayment;
	private boolean isCompensationPaid;
	private List<VocDto> vocs;

	public static ClientCompanyDto from(ClientCompany clientCompany) {
		return EntityAndDtoConverter.convert(clientCompany, ClientCompanyDto.class);
	}
}
