package timf.voc.task.entity.voc.aggregate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import timf.voc.task.entity.voc.Voc;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Compensation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 2000)
	private String description;

	private Long amount;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "voc_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Voc voc;

	public static Compensation of(String compensationDescription, Long compensationAmount) {
		return Compensation.builder().description(compensationDescription).amount(compensationAmount).build();
	}

	public Compensation mappedWith(Voc voc) {
		this.voc = voc;
		return this;
	}
}