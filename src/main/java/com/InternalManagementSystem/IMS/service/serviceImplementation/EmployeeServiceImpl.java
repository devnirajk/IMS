package com.InternalManagementSystem.IMS.service.serviceImplementation;
import com.InternalManagementSystem.IMS.dto.EmployeeDtos.EmployeeRequestDto;
import com.InternalManagementSystem.IMS.dto.EmployeeDtos.EmployeeResponseDto;
import com.InternalManagementSystem.IMS.entity.Employee;
import com.InternalManagementSystem.IMS.repository.EmployeeRepository;
import com.InternalManagementSystem.IMS.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    ModelMapper modelMapper;

    public EmployeeResponseDto createEmployee(EmployeeRequestDto employeeRequestDto) {
        try {
            Employee employee = modelMapper.map(employeeRequestDto, Employee.class);

            employee.setCreatedAt(new Date());
            employee.setUpdatedAt(new Date());

            // Save the organisation entity to the database
            Employee savedEmployee = employeeRepository.save(employee);


            // Map the saved entity to the response DTO
            return modelMapper.map(savedEmployee, EmployeeResponseDto.class);
        } catch (Exception e) {
            throw new RuntimeException("Error creating employee", e);
        }
    }

    public EmployeeResponseDto getEmployeeById(Long id) {
        try {
            Optional<Employee> employeeOptional = employeeRepository.findById(id);
            return modelMapper.map(employeeOptional.get(), EmployeeResponseDto.class);
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving employee", e);
        }
    }

    public EmployeeResponseDto updateEmployee(Long id, EmployeeRequestDto employeeRequestDto) {
        try {
            Optional<Employee> employeeOptional = employeeRepository.findById(id);

            // Get the employee record saved in table
            Employee employee = employeeOptional.get();

            // set the password in the incoming request DTO
            employeeRequestDto.setPassword(employee.getPassword());

            // now convert the employeeDTO to employee
            Employee newEmployee = modelMapper.map(employeeRequestDto, Employee.class);

            // set updatedAt
            newEmployee.setUpdatedAt(new Date());

            // save the updated employee
            Employee updatedEmployee = employeeRepository.save(employee);
            return modelMapper.map(updatedEmployee, EmployeeResponseDto.class);

        } catch (Exception e) {
            throw new RuntimeException("Error updating employee", e);
        }
    }

    public boolean deleteEmployee(Long id) {
        try {
            employeeRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Error deleting employee", e);
        }
    }
}
