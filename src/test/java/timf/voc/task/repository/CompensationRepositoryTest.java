package timf.voc.task.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import timf.voc.task.entity.voc.aggregate.Compensation;
import timf.voc.task.fixture.CompensationFixture;
import timf.voc.task.fixture.VocFixture;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CompensationRepositoryTest {

	@Autowired
	CompensationRepository compensationRepository;

	@Test
	public void shouldSave_Success(){
		// given
		Compensation compensation = CompensationFixture.create(VocFixture.createEmpty());

		// when
		compensationRepository.save(compensation);

		// then
		assertTrue(compensationRepository.findById(1L).isPresent());
	}


}