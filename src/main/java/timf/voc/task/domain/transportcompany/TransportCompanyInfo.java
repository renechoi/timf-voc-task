package timf.voc.task.domain.transportcompany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TransportCompanyInfo {
	private Long id;
	private String transportCompanyToken;
	private String companyName;
}
