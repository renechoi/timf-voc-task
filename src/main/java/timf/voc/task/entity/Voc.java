package timf.voc.task.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import timf.voc.task.entity.constant.ClaimResponsibility;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Voc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 2000)
    private String description;

    private boolean claimReceived;

    private boolean compensationRequested;

    @Enumerated(EnumType.STRING)
    private ClaimResponsibility claimResponsibility;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_driver_id")
    private DeliveryDriver deliveryDriver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_company_id")
    private ClientCompany clientCompany;

    @OneToOne(mappedBy = "voc")
    private Compensation compensation;

    @OneToOne(mappedBy = "voc")
    private Penalty penalty;
}
