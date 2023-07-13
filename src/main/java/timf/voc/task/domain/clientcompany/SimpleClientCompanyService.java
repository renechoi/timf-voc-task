package timf.voc.task.domain.clientcompany;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import timf.voc.task.config.exception.ClientCompanyNotFoundException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SimpleClientCompanyService implements ClientCompanyService{

	private final ClientCompanyReader clientCompanyReader;

	@Override
	public ClientCompanyInfo retrieveClientCompany(Long id){
		return clientCompanyReader.get(id).map(ClientCompanyInfo::from).orElseThrow(ClientCompanyNotFoundException::new);
	}
}
