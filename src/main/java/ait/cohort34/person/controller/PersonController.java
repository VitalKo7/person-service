package ait.cohort34.person.controller;

import ait.cohort34.person.dto.AddressDto;
import ait.cohort34.person.dto.CityDto;
import ait.cohort34.person.dto.PersonDto;
import ait.cohort34.person.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {
    final PersonService personService;

    @PostMapping
    public Boolean addPerson(@RequestBody PersonDto personDto) {
        return personService.addPerson(personDto);
    }

    @GetMapping("/{id}")
    public PersonDto findPersonById(@PathVariable Integer id) {
        return personService.findPersonById(id);
    }

    @GetMapping("/city/{city}")
    public PersonDto[] findPersonsByCity(@PathVariable String city) {
        return personService.findPersonsByCity(city);
    }

    @GetMapping("/ages/{ageFrom}/{ageTo}")
    public PersonDto[] findPersonsByAges(@PathVariable Integer ageFrom, @PathVariable Integer ageTo) {
        return personService.findPersonsByAges(ageFrom, ageTo);
    }

    @PutMapping("/{id}/name/{name}")
    public PersonDto updatePersonName(@PathVariable Integer id, @PathVariable String name) {
        return personService.updatePersonName(id, name);
    }

    @GetMapping("/name/{name}")
    public PersonDto[] findPersonsByName(@PathVariable String name) {
        return personService.findPersonsByName(name);
    }

    @GetMapping("/population/city")
    public CityDto[] getCitiesPopulation() {
        return personService.getCitiesPopulation();
    }

    @PutMapping("/{id}/address")
    public PersonDto updatePersonAddress(@PathVariable Integer id, @RequestBody AddressDto addressDto) {
        return personService.updatePersonAddress(id, addressDto);
    }

    @DeleteMapping("/{id}")
    public PersonDto deletePersonById(@PathVariable Integer id) {
        return personService.deletePersonById(id);
    }

}
