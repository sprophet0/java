public class ders2_2{
    public static class BankaSistemi{ 
        private static int genelIslemSayaci = 0; 
        private static double bankaGenelKasasi = 1000000.0; 

        public static class ParaTransferiModulu{
            public static class TransferTalebi{
                private String gonderenH;
                private String aliciH;
                private double miktar;
                private String aciklama;

                public TransferTalebi(String gonderenH, String aliciH, double miktar, String aciklama){
                    this.gonderenH = gonderenH;
                    this.aliciH = aliciH;
                    this.miktar = miktar;
                    this.aciklama = aciklama;
                }
                public void IslemiGerceklestir(){
                    BankaSistemi.genelIslemSayaci++;
                    System.out.println("Transfer işlemi:--------------------------");
                    System.out.println("Gönderen: "+ gonderenH);
                    System.out.println("Alıcı: "+ aliciH);
                    System.out.println("Miktar: "+ miktar);
                    
                    BankaSistemi.bankaGenelKasasi-=miktar;
                    System.out.println("Açıklama: "+ aciklama);
                    System.out.println("Banka genel kasa güncel bakiye: "+BankaSistemi.bankaGenelKasasi);
                }
            }
        }

        public static class KrediAnalizi{
            public static class MusteriVerisi{
                private String adSoyad;
                private int krediSkoru;
                private double aylikGelir;
                private double mevcutBorc;
                
                public MusteriVerisi(String adSoyad,int krediSkoru,double aylikGelir,double mevcutBorc){
                    this.adSoyad = adSoyad;
                    this.krediSkoru = krediSkoru;
                    this.aylikGelir = aylikGelir;
                    this.mevcutBorc = mevcutBorc;
                }

                public void RiskAnaliziYap(){
                    BankaSistemi.genelIslemSayaci++;
                    double borcOrani = mevcutBorc / aylikGelir;
                    System.out.println("\nKredi Risk Analizi-------------------------");
                    System.out.println("Müşteri: "+ adSoyad);
                    System.out.println("Kredi Skoru: "+krediSkoru);
                    System.out.println("Borç/Gelir Oranı: "+ borcOrani);
                    if (krediSkoru > 700 && borcOrani < 0.3) {
                        System.out.println("Kredi onaylandı. :)");
                    }
                    else{
                        System.out.println("Kredi reddedildi. :(");
                    }
                }

                public static int getGenelIslemSayaci()
                {
                    return BankaSistemi.genelIslemSayaci;
                }
            }
        }


    }

            public static void main(String[] args) {
            BankaSistemi.ParaTransferiModulu.TransferTalebi transfer =
                new BankaSistemi.ParaTransferiModulu.TransferTalebi("TR12 1234 5678 3212", "TR34 2314 5433 916", 15000, "Kira");
            
            transfer.IslemiGerceklestir();
            
            BankaSistemi.KrediAnalizi.MusteriVerisi musteri = new BankaSistemi.KrediAnalizi.MusteriVerisi("Ali Veli", 
                720, 25000, 3000
            );
            musteri.RiskAnaliziYap();

            System.out.println("\nToplam işlem: "+ BankaSistemi.KrediAnalizi.MusteriVerisi.getGenelIslemSayaci());
        }
}