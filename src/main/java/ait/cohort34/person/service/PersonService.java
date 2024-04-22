package ait.cohort34.person.service;

import ait.cohort34.person.dto.*;

import java.util.List;

public interface PersonService {
    Boolean addPerson(PersonDto personDto);

    Boolean addEmployee(EmployeeDto employeeDto);

    Boolean addChild(ChildDto childDto);


    PersonDto findPersonById(Integer id);

    List<ChildDto> findAllChildren();

    List<EmployeeDto> findEmployeesBySalary(Integer salaryMin, Integer salaryMax);

    PersonDto removePerson(Integer id);


    PersonDto updatePersonName(Integer id, String name);

    PersonDto updatePersonAddress(Integer id, AddressDto addressDto);


    PersonDto[] findPersonsByCity(String city);

    PersonDto[] findPersonsByName(String name);

    PersonDto[] findPersonsBetweenAge(Integer ageFrom, Integer ageTo);

    Iterable<CityPopulationDto> getCitiesPopulation();


}
