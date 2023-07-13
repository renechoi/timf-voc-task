package timf.voc.task.config.converter;

import java.util.List;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import timf.voc.task.domain.claim.ClaimInfo;
import timf.voc.task.interfaces.claim.ClaimDto;

@Mapper(
	componentModel = "spring",
	injectionStrategy = InjectionStrategy.CONSTRUCTOR,
	unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface ClaimDtoConverter {

	List<ClaimDto.ClaimResponse> convertToResponse(List<ClaimInfo> cliamInfoList);
}
