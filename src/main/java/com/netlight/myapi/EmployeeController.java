package com.netlight.myapi;


import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
public class EmployeeController {
    private final EmployeeRepository repository;

    EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/employees")
    CollectionModel all() {

        List<Employee> allEmployees = repository.findAll();

        CollectionModel collectedEmployees =  new CollectionModel(allEmployees.stream().map(employee -> {
            EntityModel<Employee> emp = new EntityModel<>(employee);
            emp.add(linkTo(methodOn(EmployeeController.class).one(employee.getId())).withSelfRel());
            emp.add(linkTo(methodOn(EmployeeController.class).all()).withRel("employees"));
            return emp;
        }).collect(Collectors.toList()));

        collectedEmployees.add(linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
        return collectedEmployees;
    }

    @PostMapping("/employees")
    Employee newEmployee(@RequestBody Employee newEmployee) {
        return repository.save(newEmployee);
    }

    @GetMapping("/employees/{id}")
    EntityModel<Employee> one(@PathVariable Long id) {

        Employee employee = repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
        EntityModel<Employee> model = new EntityModel<>(employee);
        model.add(linkTo(methodOn(EmployeeController.class).one(id)).withSelfRel());
        model.add(linkTo(methodOn(EmployeeController.class).all()).withRel("employees"));

        return model;
    }

    @PutMapping("/employees/{id}")
    Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
        return repository.findById(id).
                map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setRole(newEmployee.getRole());
                    return repository.save(employee);
                }).orElseGet(() -> {
            newEmployee.setId(id);
            return repository.save(newEmployee);
        });
    }

    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
    }
}


