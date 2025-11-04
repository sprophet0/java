public class test{
    public class Person {
  private String name; // private = restricted access

  // Getter
  public String getName() {
    return name;
  }

  // Setter
  public void setName(String newName) {
    this.name = newName;
  }
}

    public void main(String[] args) {
        Person person = new Person();
        person.setName("John");
        String name = person.getName();
        System.out.println(name); // Output: John
    }
}