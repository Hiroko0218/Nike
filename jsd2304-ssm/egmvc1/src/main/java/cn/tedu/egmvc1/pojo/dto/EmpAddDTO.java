package cn.tedu.egmvc1.pojo.dto;

public class EmpAddDTO {
    private String title;
    private Double salary;
    private String job;

    @Override
    public String toString() {
        return "EmployeeAddDTO{" +
                "title='" + title + '\'' +
                ", salary=" + salary +
                ", job='" + job + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
