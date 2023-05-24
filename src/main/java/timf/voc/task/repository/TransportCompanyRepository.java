package timf.voc.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import timf.voc.task.entity.TransportCompany;

public interface TransportCompanyRepository extends JpaRepository<TransportCompany, Long> {
}
