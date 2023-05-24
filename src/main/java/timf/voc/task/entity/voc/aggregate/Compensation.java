package timf.voc.task.entity.voc.aggregate;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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

    @OneToOne
    @JoinColumn(name = "voc_id")
    private Voc voc;

    public static Compensation of(String compensationDescription, Long compensationAmount) {
        return Compensation.builder().description(compensationDescription).amount(compensationAmount).build();
    }
}