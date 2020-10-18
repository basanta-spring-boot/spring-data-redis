package com.javatechie.redis.repository;

import com.javatechie.redis.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class EmployeeRepository {

    public static final String HASH_KEY = "EMPLOYEE";
    @Autowired
    private RedisTemplate redisTemplate;


    public Employee save(Employee employee) {
        redisTemplate.opsForHash().put(HASH_KEY, employee.getId(), employee);
        return employee;
    }

    public List<Employee> findAll() {
        return redisTemplate.opsForHash().values(HASH_KEY);
    }

    public Employee findEmployeeById(int id) {
        return (Employee) redisTemplate.opsForHash().get(HASH_KEY, id);
    }

    public String deleteEmployee(int id) {
        redisTemplate.opsForHash().delete(HASH_KEY, id);
        return "Employee removed !!";
    }


}
