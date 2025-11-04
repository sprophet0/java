public class dynamic
{   
    class Tool
    {
        void use()
        {
            System.out.println("The tool is being used");
        }

        static class screwdriver extends Tool
        {
            @Override
            void use()
            {
                System.out.println("The screwdriver is being used");
            }
        }

        static class hammer extends Tool
        {
            @Override
            void use()
            {
                System.out.println("The hammer is being used");
            }
        }
        }
    }

    public static void main(String[] args) {
        Tool[] tools={new screwdriver(), new hammer()};

        for (Tool tool : tools) {
            tool.use();
        }
    }
