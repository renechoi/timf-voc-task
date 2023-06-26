package timf.voc.task.infrastructure.clientcompany;

import org.springframework.data.jpa.repository.JpaRepository;

import timf.voc.task.domain.clientcompany.ClientCompany;

public interface ClientCompanyRepository extends JpaRepository<ClientCompany, Long> {
}