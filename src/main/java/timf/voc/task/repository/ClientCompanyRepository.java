package timf.voc.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import timf.voc.task.entity.ClientCompany;

public interface ClientCompanyRepository extends JpaRepository<ClientCompany, Long> {
}