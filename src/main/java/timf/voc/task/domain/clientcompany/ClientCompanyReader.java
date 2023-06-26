package timf.voc.task.domain.clientcompany;

import java.util.Optional;

public interface ClientCompanyReader {
	Optional<ClientCompany> get(Long id);
}
