package timf.voc.task.infrastructure.voc.validator;

import timf.voc.task.domain.voc.VocCommand;
import timf.voc.task.domain.voc.aggregate.Compensation;
import timf.voc.task.domain.voc.aggregate.VocSeriesValidator;

public class AmountValidator implements VocSeriesValidator {
	@Override
	public void validate(Compensation compensation, VocCommand.VocRegisterRequest request) {
		// todo: amount validation
	}
}
