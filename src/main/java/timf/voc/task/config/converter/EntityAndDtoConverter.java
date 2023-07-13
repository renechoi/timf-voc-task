package timf.voc.task.config.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

public class EntityAndDtoConverter {

	private static final ModelMapper modelMapper = new ModelMapper();

	public static <T, U> U convert(T from, Class<U> to) {
		return modelMapper.map(from, to);
	}

	public static <T, U> List<U> convert(List<T> from, Class<U> to) {
		return from.stream()
			.map(each -> convert(each, to))
			.collect(Collectors.toList());
	}

}
