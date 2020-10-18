package com.javatechie.redis;

import com.javatechie.redis.entity.Employee;
import com.javatechie.redis.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/employee")
public class RedisExampleApplication {

    @Autowired
    private EmployeeRepository repository;

    @PostMapping
    public Employee save(@RequestBody Employee employee) {
        return repository.save(employee);
    }

    @GetMapping
    public ResponseEntity<?> getEmployees() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Employee getEmployeesByDept(@PathVariable int id) {
        return repository.findEmployeeById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable int id) {
        return repository.deleteEmployee(id);
    }

    public static void main(String[] args) {
        SpringApplication.run(RedisExampleApplication.class, args);
    }

}
