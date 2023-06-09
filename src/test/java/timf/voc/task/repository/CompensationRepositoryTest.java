package timf.voc.task.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import timf.voc.task.domain.voc.aggregate.Compensation;
import timf.voc.task.fixture.CompensationFixture;
import timf.voc.task.fixture.VocFixture;
import timf.voc.task.infrastructure.voc.CompensationRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CompensationRepositoryTest {

	@Autowired
	CompensationRepository compensationRepository;

	@Test
	@DirtiesContext
	public void shouldSave_Success() {
		// given
		Compensation compensation = CompensationFixture.create(VocFixture.createEmpty());

		// when
		compensationRepository.save(compensation);

		// then
		assertTrue(compensationRepository.findById(1L).isPresent());
	}
}