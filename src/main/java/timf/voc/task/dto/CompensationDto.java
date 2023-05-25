package timf.voc.task.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import timf.voc.task.config.converter.EntityAndDtoConverter;
import timf.voc.task.entity.voc.aggregate.Compensation;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompensationDto {

	private Long id;
	private String description;
	private Long amount;
	private VocDto voc;

	public static CompensationDto from(Compensation compensation) {
		return EntityAndDtoConverter.convert(compensation, CompensationDto.class);
	}
}
