package ait.cohort34.person.service;

import ait.cohort34.person.dao.PersonRepository;
import ait.cohort34.person.dto.AddressDto;
import ait.cohort34.person.dto.CityDto;
import ait.cohort34.person.dto.PersonDto;
import ait.cohort34.person.dto.exceptions.PersonNotFoundException;
import ait.cohort34.person.model.Person;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    final PersonRepository personRepository;
    final ModelMapper modelMapper;

    @Override
    public Boolean addPerson(PersonDto personDto) {

        if (personRepository.existsById(personDto.getId())) {
            return false;
        }

        personRepository.save(modelMapper.map(personDto, Person.class));
        return true;
    }

    @Override
    public PersonDto findPersonById(Integer id) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
        return modelMapper.map(person, PersonDto.class);
    }

    @Override
    public PersonDto[] findPersonsByCity(String city) {
        return new PersonDto[0];
    }

    @Override
    public PersonDto[] findPersonsByAges(Integer ageFrom, Integer ageTo) {
        return new PersonDto[0];
    }

    @Override
    public PersonDto updatePersonName(Integer id, String name) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
        person.setName(name);
        personRepository.save(person);

        return modelMapper.map(person, PersonDto.class);

    }

    @Override
    public PersonDto[] findPersonsByName(String name) {
        return new PersonDto[0];
    }

    @Override
    public CityDto[] getCitiesPopulation() {
        return new CityDto[0];
    }

    @Override
    public PersonDto updatePersonAddress(Integer id, AddressDto addressDto) {
        return null;
    }

    @Override
    public PersonDto deletePersonById(Integer id) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
        personRepository.delete(person);
        return modelMapper.map(person, PersonDto.class);
    }
}
