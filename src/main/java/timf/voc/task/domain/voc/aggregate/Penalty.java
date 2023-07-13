package timf.voc.task.domain.voc.aggregate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Penalty {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 2000)
	private String description;

	private Long amount;

	@OneToOne
	@JoinColumn(name = "voc_id")
	private Voc voc;

	@Enumerated(EnumType.STRING)
	private PenaltyApproval penaltyApproval;

	@Getter
	@RequiredArgsConstructor
	public enum PenaltyApproval {

		UNCHECKED("미확인"),
		APPROVED("승인"),
		DENIED("이의제기");

		private final String description;
	}

	@Column(length = 2000)
	private String objectionContent;

	public static Penalty of(String penaltyDescription, Long penaltyAmount) {
		return Penalty.builder()
			.description(penaltyDescription)
			.amount(penaltyAmount)
			.penaltyApproval(PenaltyApproval.UNCHECKED).build();
	}

	public Penalty mappedWith(Voc voc) {
		this.voc = voc;
		return this;
	}

	public void updateApproval() {
		this.penaltyApproval = PenaltyApproval.APPROVED;
	}
}
