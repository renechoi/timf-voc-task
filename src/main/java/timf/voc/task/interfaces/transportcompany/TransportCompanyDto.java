package timf.voc.task.interfaces.transportcompany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import timf.voc.task.config.converter.EntityAndDtoConverter;
import timf.voc.task.domain.transportcompany.aggregate.TransportCompany;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransportCompanyDto {

	private Long id;
	private String companyName;

	public static TransportCompanyDto from(TransportCompany transportCompany) {
		return EntityAndDtoConverter.convert(transportCompany, TransportCompanyDto.class);
	}
}
