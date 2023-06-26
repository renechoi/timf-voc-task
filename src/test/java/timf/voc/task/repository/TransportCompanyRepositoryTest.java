package timf.voc.task.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import timf.voc.task.domain.transportcompany.aggregate.TransportCompany;
import timf.voc.task.fixture.TransportCompanyFixture;
import timf.voc.task.infrastructure.transportcompany.TransportCompanyRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TransportCompanyRepositoryTest {

	@Autowired
	TransportCompanyRepository transportCompanyRepository;

	@Test
	@DirtiesContext
	public void shouldSave_Success(){
		// given
		TransportCompany transportCompany = TransportCompanyFixture.create();

		// when
		transportCompanyRepository.save(transportCompany);

		// then
		assertTrue(transportCompanyRepository.findById(1L).isPresent());
	}

}