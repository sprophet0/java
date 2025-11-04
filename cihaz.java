
public class cihaz {

    static class Cihaz {

        String marka;
        String model;

        public Cihaz(String marka, String model) {
            this.marka = marka;
            this.model = model;
        }

        void ac() {
            System.out.println("Cihaz açlıyor");
        }

        void CihazBilgileriGoster() {
            System.out.println("Marka: " + marka  + " Model: " + model);
        }
    }

    static class Phone extends Cihaz {

        public Phone(String marka, String model) {
            super(marka, model);
        }

        @Override
        void ac() {
            System.out.println(marka + " " + model + " telefon açılıyor ve ana ekran yükleniyor");
        }
    }

    static class laptop extends Cihaz {

        public laptop(String marka, String model) {
            super(marka, model);
        }

        @Override
        void ac() {
            System.out.println(marka + " " + model + " laptop açılıyor ve işletim sistemi yükleniyor");
        }
    }

    public static void main(String[] args) 
    {
        Cihaz[] cihazlar={new laptop("Lenovo", "Loq 15"), new Phone("Xiaomi", "17")};

        for(Cihaz cihaz:cihazlar)
        {
            cihaz.CihazBilgileriGoster();
            cihaz.ac();
            System.out.println("-----------------");
        }
    }
}
