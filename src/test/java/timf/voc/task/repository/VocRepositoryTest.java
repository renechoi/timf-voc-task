package timf.voc.task.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import timf.voc.task.domain.clientcompany.ClientCompany;
import timf.voc.task.domain.transportcompany.aggregate.DeliveryDriver;
import timf.voc.task.domain.voc.VocCommand.VocRegisterRequest;
import timf.voc.task.domain.voc.aggregate.Voc;
import timf.voc.task.fixture.ClientCompanyFixture;
import timf.voc.task.fixture.DeliveryDriverFixture;
import timf.voc.task.fixture.TransportCompanyFixture;
import timf.voc.task.fixture.VocFixture;
import timf.voc.task.fixture.VocRequestFixture;
import timf.voc.task.infrastructure.voc.VocRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class VocRepositoryTest {

	@Autowired
	VocRepository vocRepository;

	@Test
	@DirtiesContext
	public void shouldSave_Success() {
		// given
		VocRegisterRequest vocRequest = VocRequestFixture.createRegisterRequest("voc request1");
		DeliveryDriver deliveryDriver = DeliveryDriverFixture.create(VocFixture.createEmptyAsList(), false,
			TransportCompanyFixture.create());
		ClientCompany clientCompany = ClientCompanyFixture.create(VocFixture.createEmptyAsList(), 1000L, false);

		Voc voc = VocFixture.create(vocRequest, deliveryDriver, clientCompany);

		// when
		vocRepository.save(voc);

		// then
		assertTrue(vocRepository.findById(1L).isPresent());
	}

}