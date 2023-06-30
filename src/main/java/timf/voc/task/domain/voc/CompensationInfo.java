package timf.voc.task.domain.voc;

import lombok.Data;
import timf.voc.task.config.converter.EntityAndDtoConverter;
import timf.voc.task.domain.voc.aggregate.Compensation;

@Data
public class CompensationInfo {
	private Long id;

	private String description;

	private Long amount;
	private VocInfo voc;
	public static CompensationInfo from(Compensation compensation) {
		return EntityAndDtoConverter.convert(compensation, CompensationInfo.class);
	}
}
