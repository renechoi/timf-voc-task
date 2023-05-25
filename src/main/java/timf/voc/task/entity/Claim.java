package timf.voc.task.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import timf.voc.task.config.converter.EntityAndDtoConverter;
import timf.voc.task.dto.request.ClaimRequest;
import timf.voc.task.entity.auditEntity.BaseEntity;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Claim extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String personName;

	private String content;

	private boolean handled;

	public static Claim from(ClaimRequest claimRequest) {
		return EntityAndDtoConverter.convert(claimRequest, Claim.class);
	}

	public void updateStatus(boolean status) {
		this.handled = status;
	}
}
