package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Employee;
import com.example.demo.Repo.EmployeeRepo;

@Service
public class EmployeeService {
	
	@Autowired
    private EmployeeRepo repo;

    public Page<Employee> listAll(Pageable pageable) {
        return repo.findAll(pageable);
    }

    public void save(Employee emp) {
        repo.save(emp);
    }

    public Employee get(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

}
