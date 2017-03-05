public class Student implements IStudent
{
  private String Course;
  private String Name;
  private String ID;

  public Student(String Course, String Name, String ID)
  {
    this.Course = Course;
    this.Name = Name;
    this.ID = ID;
  }

  public String getCourse()
  {
    return Course;
  }

  public String getName()
  {
    return Name;
  }

  public String getStudentID()
  {
    return ID;
  }
}
