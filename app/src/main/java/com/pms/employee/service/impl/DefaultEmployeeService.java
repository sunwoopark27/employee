package com.pms.employee.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.pms.employee.dao.EmployeeDao;
import com.pms.employee.domain.Employee;
import com.pms.employee.service.EmployeeService;

@Service
public class DefaultEmployeeService implements EmployeeService {

  EmployeeDao employeeDao;

  public DefaultEmployeeService(EmployeeDao employeeDao) {
    this.employeeDao = employeeDao;
  }  

  // 등록 업무
  @Override
  public int add(Employee employee) throws Exception {
    return employeeDao.insert(employee);
  }

  // 조회 업무
  @Override
  public List<Employee> list(String keyword) throws Exception {
    return employeeDao.findByKeyword(keyword);
  }

  // 상세 조회 업무
  @Override
  public Employee get(int no) throws Exception {
    return employeeDao.findByNo(no);
  }

  //  // 사용자 조회 업무
  //  @Override
  //  public Employee get(String email, String password) throws Exception {
  //    Map<String,Object> params = new HashMap<>();
  //    params.put("email", email);
  //    params.put("password", password);
  //
  //    return employeeDao.findByEmailPassword(params);
  //  }

  // 변경 업무
  @Override
  public int update(Employee employee) throws Exception {
    return employeeDao.update(employee);
  }

  // 삭제 업무
  @Override
  public int delete(int no) throws Exception {
    return employeeDao.delete(no);
  }

  //  // 이름으로 찾기
  //  @Override
  //  public Employee search(String name) throws Exception {
  //    return employeeDao.findByName(name);
  //  }

}
