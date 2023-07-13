package timf.voc.task.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import timf.voc.task.domain.clientcompany.ClientCompany;
import timf.voc.task.fixture.ClientCompanyFixture;
import timf.voc.task.fixture.VocFixture;
import timf.voc.task.infrastructure.clientcompany.ClientCompanyRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ClientCompanyRepositoryTest {

	@Autowired
	ClientCompanyRepository clientCompanyRepository;

	@Test
	@DirtiesContext
	public void shouldSave_Success() {
		// given
		ClientCompany clientCompany = ClientCompanyFixture.create(VocFixture.createEmptyAsList(), 1000L, true);

		// when
		clientCompanyRepository.save(clientCompany);

		// then
		assertTrue(clientCompanyRepository.findById(1L).isPresent());
	}
}