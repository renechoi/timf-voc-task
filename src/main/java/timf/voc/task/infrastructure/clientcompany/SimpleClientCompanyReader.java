package timf.voc.task.infrastructure.clientcompany;

import java.util.Optional;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import timf.voc.task.domain.clientcompany.ClientCompany;
import timf.voc.task.domain.clientcompany.ClientCompanyReader;

@Component
@RequiredArgsConstructor
public class SimpleClientCompanyReader implements ClientCompanyReader {

	private final ClientCompanyRepository clientCompanyRepository;

	@Override
	public Optional<ClientCompany> get(Long id) {
		return clientCompanyRepository.findById(id);
	}
}
