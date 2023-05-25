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
import lombok.Getter;
import lombok.NoArgsConstructor;
import timf.voc.task.entity.auditEntity.BaseEntity;
import timf.voc.task.entity.voc.Voc;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryDriver extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String Name;

	private Long salary;

	private Long pendingPenaltyAmount;

	private boolean isPenaltyDeducted;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "transport_company_id")
	private TransportCompany transportCompany;

	@OneToMany(mappedBy = "deliveryDriver", cascade = CascadeType.ALL)
	private List<Voc> vocList;

	public DeliveryDriver mappedWith(Voc voc) {
		this.vocList.add(voc);
		return this;
	}

	public void updatePenaltyAmount(Long amount) {
		this.pendingPenaltyAmount += amount;
	}

	/**
	 * 페널티 금액은 다음 달 월급에 1회성으로 차감되어야 한다. 즉, 기본 salary를 영구히 낮춰서는 안 된다.
	 * 이러한 이유로 salary 에서 차감하는 것이 아니라 penalty 금액을 누적하였다가 월급을 지급하는 시점에 누적된 페널티 금액을 차감하고
	 * 다시 누적 페널티 값을 0으로 초기화해주는 시나리오를 설정하였다.
	 */
	public Long paySalary() {
		this.salary -= this.pendingPenaltyAmount;
		this.pendingPenaltyAmount = 0L;
		return salary;
	}
}



