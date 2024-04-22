package ait.cohort34.person.service;

import ait.cohort34.person.dao.PersonRepository;
import ait.cohort34.person.dto.*;
import ait.cohort34.person.dto.exceptions.PersonNotFoundException;
import ait.cohort34.person.model.Address;
import ait.cohort34.person.model.Child;
import ait.cohort34.person.model.Employee;
import ait.cohort34.person.model.Person;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService, CommandLineRunner {

    final PersonRepository personRepository;
    final ModelMapper modelMapper;

    @Transactional
    @Override
    public Boolean addPerson(PersonDto personDto) {
        if (personRepository.existsById(personDto.getId())) {
            return false;
        }
        personRepository.save(modelMapper.map(personDto, Person.class));
        return true;
    }

    @Override
    public Boolean addEmployee(EmployeeDto employeeDto) {
        if (personRepository.existsById(employeeDto.getId())) {
            return false;
        }
        personRepository.save(modelMapper.map(employeeDto, Employee.class));
        return true;
    }

    @Override
    public Boolean addChild(ChildDto childDto) {
        if (personRepository.existsById(childDto.getId())) {
            return false;
        }
        personRepository.save(modelMapper.map(childDto, Child.class));
        return true;
    }

    @Override
    public PersonDto findPersonById(Integer id) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
        return modelMapper.map(person, PersonDto.class);
    }

    @Override
    public List<ChildDto> findAllChildren() {

//        return personRepository.stream()
//                .filter(p -> p.type == "child")
//                .map(p-> modelMapper.map(p, ChildDto.class))
//                .toList();

        return List.of();
    }

    @Override
    public List<EmployeeDto> findEmployeesBySalary(Integer salaryMin, Integer salaryMax) {
        return List.of();
    }

    @Transactional
    @Override
    public PersonDto removePerson(Integer id) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
        personRepository.delete(person);
        return modelMapper.map(person, PersonDto.class);
    }

    @Transactional
    @Override
    public PersonDto updatePersonName(Integer id, String name) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
        person.setName(name);
//        personRepository.save(person);    // избыточное действие при @Transactional
        return modelMapper.map(person, PersonDto.class);
    }

    @Transactional
    @Override
    public PersonDto updatePersonAddress(Integer id, AddressDto addressDto) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
        person.setAddress(modelMapper.map(addressDto, Address.class));
//        personRepository.save(person);    // избыточное действие при @Transactional
        return modelMapper.map(person, PersonDto.class);
    }

    @Transactional(readOnly = true)
    @Override
    public PersonDto[] findPersonsByCity(String city) {
        return personRepository.findByAddressCityIgnoreCase(city)
                .map(p -> modelMapper.map(p, PersonDto.class))
                .toArray(PersonDto[]::new);
    }

    @Transactional(readOnly = true) // чтение - паралельно, запись - последовательно
    @Override
    public PersonDto[] findPersonsByName(String name) {
        return personRepository.findByNameIgnoreCase(name)
                .map(p -> modelMapper.map(p, PersonDto.class))
                .toArray(PersonDto[]::new);
    }

    @Transactional(readOnly = true)
    @Override
    public PersonDto[] findPersonsBetweenAge(Integer ageFrom, Integer ageTo) {
        LocalDate to = LocalDate.now().minusYears(ageTo);
        LocalDate from = LocalDate.now().minusYears(ageFrom);

        return personRepository.findByBirthDateBetween(from, to)
                .map(p -> modelMapper.map(p, PersonDto.class))
                .toArray(PersonDto[]::new);
    }

    @Override
    public Iterable<CityPopulationDto> getCitiesPopulation() {
        return personRepository.getCitiesPopulation();
    }

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        if (personRepository.count() == 0) {
            Person person = new Person(1000, "John", LocalDate.of(1985, 3, 11),
                    new Address("Berlin", "Purim", 18));

            Child child = new Child(2000, "Karl", LocalDate.of(2018, 3, 11),
                    new Address("Hamburg", "HauptStrasse", 5), "Sunny");

            Employee employee = new Employee(3000, "Mary", LocalDate.of(1995, 11, 23),
                    new Address("Bremen", "PappelStrasse", 28), "Motorolla", 4500);
            personRepository.save(person);
            personRepository.save(child);
            personRepository.save(employee);
        }

    }
}
