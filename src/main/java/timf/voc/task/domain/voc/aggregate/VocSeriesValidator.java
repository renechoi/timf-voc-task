package timf.voc.task.domain.voc.aggregate;

import timf.voc.task.domain.voc.VocCommand;

public interface VocSeriesValidator {
	void validate(Compensation compensation, VocCommand.VocRegisterRequest request);
}