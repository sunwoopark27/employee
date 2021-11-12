package com.pms.employee.domain;

public class Employee {

  private int no;
  private int oldno;
  private String name;
  private String email;
  private String position;
  private String tel;

  public Employee() {}

  public Employee(String csv) {
    String[] fields = csv.split(",");
    this.setNo(Integer.parseInt(fields[0]));
    this.setName(fields[1]);
    this.setEmail(fields[2]);
    this.setPosition(fields[3]);
    this.setTel(fields[5]);
  }

  public String toCsvString() {
    return String.format("%d,%s,%s,%s,%s", 
        this.getNo(),
        this.getName(),
        this.getEmail(),
        this.getPosition(),
        this.getTel());
  }

  public static Employee valueOfCsv(String csv) {
    String[] fields = csv.split(",");
    Employee employee = new Employee();
    employee.setNo(Integer.parseInt(fields[0]));
    employee.setName(fields[1]);
    employee.setEmail(fields[2]);
    employee.setPosition(fields[3]);
    employee.setTel(fields[5]);
    return employee;
  }



  @Override
  public String toString() {
    return "Employee [no=" + no + ", name=" + name + ", email=" + email + ", position=" + position
        + ", tel=" + tel + "]";
  }


  public int getOldno() {
    return oldno;
  }
  public void setOldno(int oldno) {
    this.oldno = oldno;
  }
  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getPosition() {
    return position;
  }
  public void setPosition(String position) {
    this.position = position;
  }
  public String getTel() {
    return tel;
  }
  public void setTel(String tel) {
    this.tel = tel;
  }



}
