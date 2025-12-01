public class ders2_1 {

    public static class EmlakYonetimSistemi{
        private String sirketAdi = "Güven Emlak";
        private String il="Trabzon";

        public class Emlak{
            private String emlakTuru;
            private double kiraBedeli;
            private String adres;

            public Emlak(String emlakTuru, double kiraBedeli, String adres){
                this.emlakTuru = emlakTuru;
                this.kiraBedeli = kiraBedeli;
                this.adres = adres;
            }
            public void EmlakBilgisiGoster(){
                System.out.println("Emlak Bilgisi:");
                System.out.println("Şirket: "+sirketAdi+" , "+il);
                System.out.println("Emlak Türü: "+emlakTuru);
                System.out.println("Kira: "+kiraBedeli+" TL");
                System.out.println("Adres: "+adres);
            }
        public class kiraci{
            private String adSoyad;
            private int kiraSuresi;
            private String tc;

            public kiraci(String adSoyad, int kiraSuresi, String tc){
                this.adSoyad = adSoyad;
                this.kiraSuresi = kiraSuresi;
                this.tc = tc;
            }
            public void kiraSozlesmesiYazdir(){
                System.out.println("Kira Sözleşmesi");
                System.out.println("Kiracı: "+adSoyad+","+tc);
                System.out.println("Emlak Türü: "+emlakTuru);
                System.out.println("Aylık kira: "+kiraBedeli);
                System.out.println("Süre: "+kiraSuresi);
                System.out.println("Tutar: "+kiraBedeli*kiraSuresi);
            }
    }
        }

    }
    public static void main(String[] args) {
        EmlakYonetimSistemi sistem = new EmlakYonetimSistemi();
        EmlakYonetimSistemi.Emlak emlak = sistem.new Emlak("3+1 Daire", 18000, "Yalıncak");

        emlak.EmlakBilgisiGoster();
        EmlakYonetimSistemi.Emlak.kiraci kiraci = 
           emlak.new kiraci("Ali Veli", 12, "10234512345");
        kiraci.kiraSozlesmesiYazdir();
    }
}