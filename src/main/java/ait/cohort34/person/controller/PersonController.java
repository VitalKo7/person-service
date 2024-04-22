package ait.cohort34.person.controller;

import ait.cohort34.person.dto.*;
import ait.cohort34.person.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {
    final PersonService personService;

    @PostMapping
    public Boolean addPerson(@RequestBody PersonDto personDto) {
        return personService.addPerson(personDto);
    }

    @PostMapping
    public Boolean addEmployee(EmployeeDto employeeDto) {
        return personService.addEmployee(employeeDto);
    }

    @PostMapping
    public Boolean addChild(ChildDto childDto) {
        return personService.addChild(childDto);
    }


    @GetMapping("/{id}")
    public PersonDto findPersonById(@PathVariable Integer id) {
        return personService.findPersonById(id);
    }

    @GetMapping("/children")
    public List<ChildDto> findAllChildren() {
        return List.of();
    }

    @GetMapping("/salary/{salaryMin}/{salaryMax}")
    public List<EmployeeDto> findEmployeesBySalary(@PathVariable Integer salaryMin, @PathVariable Integer salaryMax) {
        return List.of();
    }


    @GetMapping("/city/{city}")
    public PersonDto[] findPersonsByCity(@PathVariable String city) {
        return personService.findPersonsByCity(city);
    }

    @GetMapping("/ages/{ageFrom}/{ageTo}")
    public PersonDto[] findPersonsBetweenAge(@PathVariable Integer ageFrom, @PathVariable Integer ageTo) {
        return personService.findPersonsBetweenAge(ageFrom, ageTo);
    }


    @PutMapping("/{id}/name/{name}")
    public PersonDto updatePersonName(@PathVariable Integer id, @PathVariable String name) {
        return personService.updatePersonName(id, name);
    }

    @PutMapping("/{id}/address")
    public PersonDto updatePersonAddress(@PathVariable Integer id, @RequestBody AddressDto addressDto) {
        return personService.updatePersonAddress(id, addressDto);
    }


    @GetMapping("/name/{name}")
    public PersonDto[] findPersonsByName(@PathVariable String name) {
        return personService.findPersonsByName(name);
    }


    @GetMapping("/population/city")
    public Iterable<CityPopulationDto> getCitiesPopulation() {
        return personService.getCitiesPopulation();
    }


    @DeleteMapping("/{id}")
    public PersonDto removePerson(@PathVariable Integer id) {
        return personService.removePerson(id);
    }

}
