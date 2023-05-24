package timf.voc.task.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import timf.voc.task.dto.VocRequest;
import timf.voc.task.entity.ClientCompany;
import timf.voc.task.entity.DeliveryDriver;
import timf.voc.task.entity.voc.Voc;
import timf.voc.task.fixture.ClientCompanyFixture;
import timf.voc.task.fixture.DeliveryDriverFixture;
import timf.voc.task.fixture.TransportCompanyFixture;
import timf.voc.task.fixture.VocFixture;
import timf.voc.task.fixture.VocRequestFixture;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class VocRepositoryTest {

	@Autowired VocRepository vocRepository;

	@Test
	@DirtiesContext
	public void shouldSave_Success() {
		// given
		VocRequest vocRequest = VocRequestFixture.create("voc request1");
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