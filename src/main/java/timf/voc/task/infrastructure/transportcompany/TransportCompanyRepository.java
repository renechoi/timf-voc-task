package timf.voc.task.infrastructure.transportcompany;

import org.springframework.data.jpa.repository.JpaRepository;

import timf.voc.task.domain.transportcompany.aggregate.TransportCompany;

public interface TransportCompanyRepository extends JpaRepository<TransportCompany, Long> {
}
