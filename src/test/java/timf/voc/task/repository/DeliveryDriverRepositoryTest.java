package timf.voc.task.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import timf.voc.task.entity.ClientCompany;
import timf.voc.task.entity.DeliveryDriver;
import timf.voc.task.fixture.ClientCompanyFixture;
import timf.voc.task.fixture.DeliverDriverFixture;
import timf.voc.task.fixture.TransportCompanyFixture;
import timf.voc.task.fixture.VocFixture;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DeliveryDriverRepositoryTest {

	@Autowired
	DeliveryDriverRepository deliveryDriverRepository;

	@Test
	public void shouldSave_Success(){
		// given
		DeliveryDriver deliveryDriver = DeliverDriverFixture.create(VocFixture.createEmptyAsList(), false,
			TransportCompanyFixture.create());

		// when
		deliveryDriverRepository.save(deliveryDriver);

		// then
		assertTrue(deliveryDriverRepository.findById(1L).isPresent());
	}


}