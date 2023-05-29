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

import timf.voc.task.dto.request.VocRequest;
import timf.voc.task.entity.ClientCompany;
import timf.voc.task.exception.ClientCompanyNotFoundException;
import timf.voc.task.fixture.ClientCompanyFixture;
import timf.voc.task.fixture.VocFixture;
import timf.voc.task.fixture.VocRequestFixture;
import timf.voc.task.repository.ClientCompanyRepository;

@ExtendWith(MockitoExtension.class)
class ClientCompanyServiceTest {

	@InjectMocks
	ClientCompanyService clientCompanyService;

	@Mock
	ClientCompanyRepository clientCompanyRepository;

	@Test
	void shouldSearchClientCompanyEntity_Success() {
		// given
		Long companyId = 1L;
		ClientCompany clientCompany = createClientCompany(createVocRequest());
		when(clientCompanyRepository.findById(companyId)).thenReturn(Optional.of(clientCompany));

		// when
		ClientCompany result = clientCompanyService.searchClientCompanyEntity(companyId);

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
			() -> clientCompanyService.searchClientCompanyEntity(companyId));
		verify(clientCompanyRepository).findById(companyId);
	}

	@NotNull
	private ClientCompany createClientCompany(VocRequest vocRequest) {
		return ClientCompanyFixture.create(vocRequest.getClientCompanyId(),
			VocFixture.createEmptyAsList(), 1000L, false);
	}

	@NotNull
	private VocRequest createVocRequest() {
		return VocRequestFixture.create("voc request1");
	}
}

