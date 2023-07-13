package timf.voc.task.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.Optional;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import timf.voc.task.domain.clientcompany.ClientCompany;
import timf.voc.task.config.exception.ClientCompanyNotFoundException;
import timf.voc.task.domain.clientcompany.ClientCompanyInfo;
import timf.voc.task.domain.clientcompany.SimpleClientCompanyService;
import timf.voc.task.domain.voc.VocCommand;
import timf.voc.task.fixture.ClientCompanyFixture;
import timf.voc.task.fixture.VocFixture;
import timf.voc.task.fixture.VocRequestFixture;
import timf.voc.task.infrastructure.clientcompany.ClientCompanyRepository;

@ExtendWith(MockitoExtension.class)
class SimpleClientCompanyServiceTest {

	@InjectMocks
	SimpleClientCompanyService simpleClientCompanyService;

	@Mock
	ClientCompanyRepository clientCompanyRepository;

	@Test
	void shouldSearchClientCompanyEntity_Success() {
		// given
		Long companyId = 1L;
		ClientCompany clientCompany = createClientCompany(createVocRequest());
		when(clientCompanyRepository.findById(companyId)).thenReturn(Optional.of(clientCompany));

		// when
		ClientCompanyInfo result = simpleClientCompanyService.retrieveClientCompany(companyId);

		// then
		verify(clientCompanyRepository).findById(companyId);
		assertEquals(clientCompany, result);
	}

	@Test
	void shouldThrowException_WhenClientCompanyNotFound() {
		// given
		Long companyId = 1L;
		when(clientCompanyRepository.findById(companyId)).thenReturn(Optional.empty());

		// when, then
		assertThrows(ClientCompanyNotFoundException.class,
			() -> simpleClientCompanyService.retrieveClientCompany(companyId));
		verify(clientCompanyRepository).findById(companyId);
	}

	@NotNull
	private ClientCompany createClientCompany(VocCommand.VocRegisterRequest vocRequest) {
		return ClientCompanyFixture.create(vocRequest.getClientCompanyId(),
			VocFixture.createEmptyAsList(), 1000L, false);
	}

	@NotNull
	private VocCommand.VocRegisterRequest createVocRequest() {
		return VocRequestFixture.createRegisterRequest("voc request1");
	}
}

