package timf.voc.task.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import timf.voc.task.entity.auditEntity.BaseEntity;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TransportCompany extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String companyName;

}
