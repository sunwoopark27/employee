package com.pms.employee.dao;

import java.util.List;
import com.pms.employee.domain.Employee;

public interface EmployeeDao {

  int insert(Employee employee) throws Exception;

  List<Employee> findByKeyword(String keyword) throws Exception;

  Employee findByNo(int no) throws Exception;

  //Employee findByEmailPassword(Map<String,Object> params) throws Exception;

  int update(Employee employee) throws Exception;

  int delete(int no) throws Exception;

  //Employee findByName(String name) throws Exception;

}
