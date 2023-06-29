package timf.voc.task.config.converter;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import timf.voc.task.domain.voc.VocCommand.VocReigsterRequest;
import timf.voc.task.domain.voc.VocCommand.VocRegisterRequest;
import timf.voc.task.interfaces.transportcompany.DeliveryDriverDto.DeliveryDriverVocProcessRequest;
import timf.voc.task.interfaces.voc.VocDto.VocRequest;

@Mapper(
	componentModel = "spring",
	injectionStrategy = InjectionStrategy.CONSTRUCTOR,
	unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface VocDtoConverter {

	VocRegisterRequest convertToCommand(VocRequest request);

	VocReigsterRequest convertToCommand(DeliveryDriverVocProcessRequest request);
}
