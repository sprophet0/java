public class Test{
    int a;
    public int b;
    public int c;

    void setC(int a){
        c = a;
    }

    int getC()
    {
        return c;
    }

    void printC(){
        System.out.println(c);
    }

    public static void main(String[] args) {
        Test test = new Test();

        test.setC(25);
        test.getC();
        test.printC();
    }
}
