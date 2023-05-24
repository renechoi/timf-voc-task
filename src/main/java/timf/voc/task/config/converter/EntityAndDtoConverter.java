package timf.voc.task.config.converter;

import org.modelmapper.ModelMapper;

public class EntityAndDtoConverter {

	private static final ModelMapper modelMapper = new ModelMapper();

	public static <T, U> U convert(T from, Class<U> to) {
		return modelMapper.map(from, to);
	}
}
