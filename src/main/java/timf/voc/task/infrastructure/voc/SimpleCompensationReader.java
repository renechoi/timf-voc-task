package timf.voc.task.infrastructure.voc;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import timf.voc.task.domain.voc.CompensationReader;
import timf.voc.task.domain.voc.aggregate.Compensation;


@Component
@RequiredArgsConstructor
public class SimpleCompensationReader implements CompensationReader {

	private final CompensationRepository compensationRepository;

	@Override
	public List<Compensation> get() {
		return compensationRepository.findAll();
	}

	@Override
	public Optional<Compensation> get(Long id) {
		return Optional.empty();
	}
}
