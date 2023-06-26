package timf.voc.task.infrastructure.voc;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import timf.voc.task.domain.voc.VocReader;
import timf.voc.task.domain.voc.aggregate.Voc;

@Component
@RequiredArgsConstructor
public class SimpleVocReader implements VocReader {

	private final VocRepository vocRepository;

	@Override
	public List<Voc> get() {
		return vocRepository.findAll();
	}

	@Override
	public Optional<Voc> get(Long id) {
		return vocRepository.findById(id);
	}
}
