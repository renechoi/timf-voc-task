package timf.voc.task.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import timf.voc.task.entity.DeliveryDriver;
import timf.voc.task.entity.TransportCompany;
import timf.voc.task.fixture.TransportCompanyFixture;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TransportCompanyRepositoryTest {

	@Autowired TransportCompanyRepository transportCompanyRepository;

	@Test
	public void shouldSave_Success(){
		// given
		TransportCompany transportCompany = TransportCompanyFixture.create();

		// when
		transportCompanyRepository.save(transportCompany);

		// then
		assertTrue(transportCompanyRepository.findById(1L).isPresent());
	}

}