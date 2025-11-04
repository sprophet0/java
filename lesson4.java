public class lesson4
{
    public static void main(String[] args)
    {

    }

    public static class Employee
    {
        protected String name;
        protected int wage;

        public Employee(String name, int wage)
        {
            this.name=name;
            this.wage=wage;
        }

        public void showInfo()
        {
            System.out.println("Name: "+name);
            System.out.println("Wage: "+wage);
        }
    }
}