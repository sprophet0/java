public class ders33
{
    static class vehicle
    {
        String model;
        int speed;

        public vehicle(String model, int speed)
        {
            this.model=model;
            this.speed=speed;
        }

        void Move()
        {
            System.out.println("vehicle with model "+model+" is moving");
        }

        // Add a maintenance method to the base class
        void performMaintenance() {
            System.out.println("Generic maintenance started for " + model);
        }
    }

    static class Bicycle extends vehicle
    {
        boolean isElectric;

        public Bicycle(String model, int speed, boolean isElectric)
        {
            super(model, speed);
            this.isElectric=isElectric;
        }

        void BicycleMove()
        {
            System.out.println("Bicycle with model "+model+" is moving");
        }

        void EBikeMove()
        {   if(isElectric==true)
            {
            System.out.println("Bicycle with model "+model+" is moving in Electric mode");
            }
            else
            System.out.println("Bicycle with model "+model+" is moving in Normal mode");
        }

        @Override
        void performMaintenance() {
            System.out.println("Bike maintenance started for " + model);
            Move();
            EBikeMove();
            System.out.println("Brake maintenance finished");
        }


    }

    static class Scooter extends vehicle
    {   int battery;
        public Scooter(String model, int speed, int battery)
        {
            super(model, speed);
            this.battery=battery;
        }
        void EScooterMove()
        {
            if(battery>10)
            {
                System.out.println("Scooter with model "+model+" is moving in Electric mode");
            }
            else
            System.out.println("Scooter with model "+model+" has low battery, pls cahrge :)");
        }

        @Override
        void performMaintenance() {
            System.out.println("Scooter maintenance started for " + model);
            EScooterMove();
        }
    }

    static class vehicleMaintenance
    {   
        void doMaintenance(vehicle Vehicle)
        {
            System.out.println("Maintenance started for vehicle with model "+Vehicle.model);
            if(Vehicle instanceof Bicycle)
            {
                Bicycle bike = (Bicycle)Vehicle;
                System.out.println("Bike maintenance started");
                bike.Move();
                bike.EBikeMove();
                System.out.println("Brake maintenance finished");
            }
            else if(Vehicle instanceof Scooter)
            {
                Scooter scooter = (Scooter)Vehicle;
                System.out.println("Scooter maintenance started");
                scooter.EScooterMove();
            }
            else
            {
                System.out.println("Generic maintenance started");
            }
            Vehicle.performMaintenance();
        }
    }

    public static void main(String[] args)
    {
        vehicle[] vehicles= {
            new Bicycle("BMX", 30, false),
            new Scooter("Xiaomi", 20, 50),
            new Scooter("Honda", 40, 5)
        };

        vehicleMaintenance maintenance = new vehicleMaintenance();
        for (vehicle v : vehicles) {
            maintenance.doMaintenance(v);
            System.out.println("-----------------");
        }
    }
}