package ait.cohort34.person.service;

import ait.cohort34.person.dto.AddressDto;
import ait.cohort34.person.dto.CityDto;
import ait.cohort34.person.dto.PersonDto;

public interface PersonService {
    Boolean addPerson(PersonDto personDto);

    PersonDto findPersonById(Integer id);

    PersonDto[] findPersonsByCity(String city);

    PersonDto[] findPersonsByAges(Integer ageFrom, Integer ageTo);

    PersonDto updatePersonName(Integer id, String name);

    PersonDto[] findPersonsByName(String name);

    CityDto[] getCitiesPopulation();

    PersonDto updatePersonAddress(Integer id, AddressDto addressDto);

    PersonDto deletePersonById(Integer id);

}
