package timf.voc.task.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import timf.voc.task.domain.voc.aggregate.Penalty;
import timf.voc.task.fixture.PenaltyFixture;
import timf.voc.task.fixture.VocFixture;
import timf.voc.task.infrastructure.voc.PenaltyRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PenaltyRepositoryTest {

	@Autowired
	PenaltyRepository penaltyRepository;

	@Test
	@DirtiesContext
	public void shouldSave_Success(){
		// given
		Penalty penalty = PenaltyFixture.create(true, null, VocFixture.createEmpty());

		// when
		penaltyRepository.save(penalty);

		// then
		assertTrue(penaltyRepository.findById(1L).isPresent());
	}
}