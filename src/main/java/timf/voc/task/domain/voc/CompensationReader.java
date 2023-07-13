package timf.voc.task.domain.voc;

import java.util.List;
import java.util.Optional;

import timf.voc.task.domain.voc.aggregate.Compensation;

public interface CompensationReader {
	List<Compensation> get();

	Optional<Compensation> get(Long id);
}
