package timf.voc.task.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import timf.voc.task.entity.ClientCompany;
import timf.voc.task.exception.ClientCompanyNotFoundException;
import timf.voc.task.repository.ClientCompanyRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ClientCompanyService {

	private final ClientCompanyRepository clientCompanyRepository;

	public ClientCompany searchClientCompanyEntity(Long id){
		return clientCompanyRepository.findById(id)
			.orElseThrow(ClientCompanyNotFoundException::new);
	}
}
