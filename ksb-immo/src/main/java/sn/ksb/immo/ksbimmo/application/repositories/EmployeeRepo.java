package sn.ksb.immo.ksbimmo.application.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sn.ksb.immo.ksbimmo.application.models.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, UUID> {
    List<Employee> findByAgence_Id(UUID agenceId);

    Employee findByMatricule(String matricule);
}