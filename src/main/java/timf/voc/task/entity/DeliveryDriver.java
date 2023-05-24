package timf.voc.task.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import timf.voc.task.entity.auditEntity.BaseEntity;
import timf.voc.task.entity.voc.Voc;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryDriver extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String Name;

	private Long salary;

	private boolean isPenaltyDeducted;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "transport_company_id")
	private TransportCompany transportCompany;

	@OneToMany(mappedBy = "deliveryDriver", cascade = CascadeType.ALL)
	private List<Voc> vocList;
}
