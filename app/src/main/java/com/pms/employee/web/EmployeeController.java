package com.pms.employee.web;

import java.io.OutputStream;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.pms.employee.domain.Employee;
import com.pms.employee.service.EmployeeService;

@Controller
@RequestMapping("/employee/")
public class EmployeeController {

  EmployeeService employeeService;

  public EmployeeController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @GetMapping("form")
  public void form() throws Exception {
  }

  @PostMapping("add")
  public String add(Employee e) throws Exception {

    System.out.println(e + "--------");
    employeeService.add(e);
    return "redirect:list";
  }

  @GetMapping("delete")
  public String delete(int no) throws Exception {
    Employee employee = employeeService.get(no);
    if (employee == null) {
      throw new Exception("해당 번호의 회원이 없습니다.");
    }

    // 회원 관리를 관리자가 할 경우 모든 회원의 정보 변경 가능
    //      Employee loginUser = (Employee) request.getSession().getAttribute("loginUser");
    //      if (oldBoard.getWriter().getNo() != loginUser.getNo()) {
    //        throw new Exception("삭제 권한이 없습니다!");
    //      }

    employeeService.delete(no);
    return "redirect:list";
  }

  @GetMapping("detail")
  public void detail(int no, Model model) throws Exception {
    System.out.println(no + "--------");

    Employee m = employeeService.get(no);
    model.addAttribute("employee", m);
  }

  @GetMapping("list") 
  public void list(String keyword, String item, Model model) throws Exception {

    List<Employee> list = null;
    if(item != null && keyword != null && keyword.length() > 0) {
      System.out.println(keyword);
      System.out.println(item);
      list = employeeService.searchByDetail(item, keyword);

      System.out.println(list);

    } else {

      list = employeeService.list(keyword);

      System.out.println(list);
    }
    System.out.println(list);
    model.addAttribute("list", list);
  }

  @PostMapping("update")
  public String update(Employee employee) throws Exception {

    // 회원 관리를 관리자가 할 경우 모든 회원의 정보 변경 가능
    //      Employee loginUser = (Employee) request.getSession().getAttribute("loginUser");
    //      if (oldEmployee.getNo() != loginUser.getNo()) {
    //        throw new Exception("변경 권한이 없습니다!");
    //      }

    if (employeeService.update(employee) == 0) {
      throw new Exception("해당 번호의 회원이 없습니다.");
    } 

    employeeService.update(employee);

    return "redirect:list";
  }

  @PostMapping("savecsv")
  public void saveCsv(String keyword, HttpServletResponse response) throws Exception{

    List<Employee> employeeList = employeeService.list(keyword);
    StringBuffer sb = new StringBuffer();
    sb.append("no, name, email, position, tel");

    for (Employee e : employeeList) {
      String row = e.toCsvString();
      sb.append("\n");
      sb.append(row);
    }

    response.setContentType("text/csv");
    response.setHeader("Content-Disposition", "attachment; filename=\"employees.csv\"");
    response.setHeader("Content-Type", "text/csv; charset=MS949");

    OutputStream outputStream = response.getOutputStream();
    outputStream.write(sb.toString().getBytes("MS949"));
    outputStream.flush();
    outputStream.close();

    sb.setLength(0);
  }
}
