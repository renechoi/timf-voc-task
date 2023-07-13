package timf.voc.task.domain.voc;

import java.util.List;
import java.util.Optional;

import timf.voc.task.domain.voc.aggregate.Voc;

public interface VocReader {
	List<Voc> get();

	Optional<Voc> get(Long vocId);
}
