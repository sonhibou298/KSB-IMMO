package sn.ksb.immo.ksbimmo.application.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import sn.ksb.immo.ksbimmo.application.models.Employee;
import sn.ksb.immo.ksbimmo.application.repositories.EmployeeRepo;

@Service
@Slf4j
@Transactional
public class EmployeeService {
    
    private final EmployeeRepo employeeRepo;

    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    //récupérer les employées d'une agence
    public List<Employee> getEmployeesByAgence(String agenceId) {
        //log entrée dans la méthode getEmployeesByAgence du service EmployeeService
        log.info("Entrée dans la méthode getEmployeesByAgence du service EmployeeService");
        //initialisation de la liste des employées
        List<Employee> employees = new ArrayList<>();
        //try catch pour récupérer les employées
        try {
            //récupération des employées
            employees = employeeRepo.findByAgence_Id(UUID.fromString(agenceId));
            //log récupération des employées
            log.info("Récupération des employées");
        } catch (Exception e) {
            //log erreur récupération des employées
            log.error("Erreur lors de la récupération des employées");
        }
        //si la liste est vide
        if (employees.isEmpty()) {
            //log aucun employé trouvé dans la base de données
            log.error("Aucun employé trouvé dans la base de données pour l'agence d'id : " + agenceId);
        }
        //log sortie de la méthode getEmployeesByAgence du service EmployeeService
        log.info("Sortie de la méthode getEmployeesByAgence du service EmployeeService");
        //retourner la liste des employées
        return employees;
    }

    //récupérer un employé par son matricule
    public Employee getEmployeeByMatricule(String matricule) {
        //log entrée dans la méthode getEmployeeByMatricule du service EmployeeService
        log.info("Entrée dans la méthode getEmployeeByMatricule du service EmployeeService");
        //initialisation de l'employé
        Employee employee = null;
        //log matricule
        log.info("Paramètre matricule : " + matricule);
        //try catch pour récupérer l'employé
        try {
            //récupération de l'employé
            employee = employeeRepo.findByMatricule(matricule);
            //log récupération de l'employé
            log.info("Récupération de l'employé");
        } catch (Exception e) {
            //log erreur récupération de l'employé
            log.error("Erreur lors de la récupération de l'employé");
        }
        //si l'employé est null
        if (employee == null) {
            //log aucun employé trouvé dans la base de données
            log.error("Aucun employé trouvé dans la base de données pour le matricule : " + matricule);
        }
        //log sortie de la méthode getEmployeeByMatricule du service EmployeeService
        log.info("Sortie de la méthode getEmployeeByMatricule du service EmployeeService");
        //retourner l'employé
        return employee;
    }

    //ajouter un employé
    public Employee addEmployee(Employee employee) {
        //log entrée dans la méthode addEmployee du service EmployeeService
        log.info("Entrée dans la méthode addEmployee du service EmployeeService");
        //try catch pour ajouter l'employé
        try {
            //ajout de l'employé
            employee = employeeRepo.save(employee);
            //log ajout de l'employé
            log.info("Ajout de l'employé");
        } catch (Exception e) {
            //log erreur ajout de l'employé
            log.error("Erreur lors de l'ajout de l'employé");
        }
        //log sortie de la méthode addEmployee du service EmployeeService
        log.info("Sortie de la méthode addEmployee du service EmployeeService");
        //retourner l'employé
        return employee;
    }

    //modifier un employé

}