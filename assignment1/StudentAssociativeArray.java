public class StudentAssociativeArray implements IStudentAssociativeArray
{
  private Node arrStudent[];
  private int capacity;
  private int size;

  public StudentAssociativeArray(int capacity)
  {
    arrStudent = new Node[capacity];
    this.capacity = capacity;
    size = 0;
  }

  public void clear()
  {
    size = 0;
    for(int i = 0; i < capacity; ++i)
      arrStudent[i] = null;
  }

  private int hash(String key)
  {
    return Math.abs(key.hashCode());
  }

  public boolean containsStudentID(String studentID)
  {
    int h = hash(studentID);
    int index = h % capacity;

    Node p = arrStudent[index];

    while(p != null && h != p.hkey && studentID != p.data.getStudentID() )
      p = p.next;

    return p != null;
  }

  public boolean containsValue(Student student)
  {
    int index = hash(student.getName() ) % capacity;

    Node pair = arrStudent[index];

    while(pair != null  && student != pair.data )
      pair = pair.next;

    return pair != null;
  }

  public Student getStudent(String studentID)
  {
    int h = hash(studentID);
    int index = h % capacity;

    Node p = arrStudent[index];

    while(p != null && h != p.hkey && studentID != p.data.getStudentID())
      p = p.next;

    return p.data;
  }

  public boolean isEmpty()
  {
    return size == 0;
  }

/*  public Collection<String> keySet()
  {
   String[] array = new String[size];
    
    int j = 0;
    for(int i = 0; i < capacity; ++i)
    {
      Node p = arrStudent[i];
      while(p != null)
      {
        array[j] = p.data.getStudentID();
        p = p.next;
        ++j;
      }
    }
    return array;
  }
*/
  public void put(Student student)
  {
    int h, index;
    Node p = null, pair = null, ins = null;

    if((float)size / (float)capacity > 0.75)
    {
      StudentAssociativeArray htab = new StudentAssociativeArray(2 * capacity);
      for(int i = 0; i < capacity; ++i)
      {
        p = arrStudent[i];
        while(p != null)
        {
          index = p.hkey % capacity;
          ins = new Node();
          ins.data = p.data;
          ins.hkey = p.hkey;
          ins.next = null;

          pair = htab.arrStudent[index];
          if(pair == null)
            htab.arrStudent[index] = ins;
          else
          {
            while(pair.next != null)
              pair = pair.next;
            pair.next = ins;
          }
          p = p.next;
        }
      }
      capacity = htab.capacity;
      arrStudent = htab.arrStudent;
    }
    h = hash(student.getStudentID());
    index = h % capacity;
    p = arrStudent[index];

    if(p == null)
      arrStudent[index] = ins;
    else
    {
      while(p.next != null)
      {
        if(p.hkey == h && p.data.getStudentID() == student.getStudentID() )
        {
          p.data = student;
          return;
        }
        p = p.next;
      }
      ins.data = student;
      ins.hkey = h;
      ins.next = null;
      ++size;
      p.next = ins;
    }
  }

  public Student remove(String studentID)
  {
    int h = hash(studentID);
    int index = h % capacity;
    Node p = arrStudent[index], prev = null;
    Student student = null;
    while(p != null && p.hkey != h && p.data.getStudentID() != studentID)
    {
      prev = p;
      p = p.next;
    }
    if(prev == null)
    {
      if(p != null)
      {
        student = arrStudent[index].data;
        arrStudent[index] = null;
        --size;
        return student;
      }
    }
    if(p != null)
    {
      student = p.data;
      prev.next = p.next;
      --size;
    }
    return student;
  }
  public int size()
  {
    return size;
  }

  public void printHash()
  {
    for(int i = 0; i < capacity; ++i)
    {
      Node p = arrStudent[i];
      while(p != null)
      {
        System.out.println(p.data.getStudentID() + p.data.getName() + "\n");
        p = p.next;
      }
    }

  }
}








