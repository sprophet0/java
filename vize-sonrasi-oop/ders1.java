
public class ders1_1 {

    public static void main(String[] args) 
    {
    Odeme odeme1 = new Odeme.NakitOdeme(100);
        odeme1.odemeYap();
        odeme1.OdemeBilgisiGoster();
        
    Odeme odeme2 = new Odeme.KrediKartiOdeme(200, "1234567890123456");
        odeme2.odemeYap();
        odeme2.OdemeBilgisiGoster();
    }

    public static abstract class Odeme{
        
        public double miktar;

        public Odeme(double miktar)
        {
            this.miktar = miktar;
        } 

        public abstract void odemeYap();

        public void OdemeBilgisiGoster()
        {
            System.out.println("Odenecek tutar: "+miktar+" TL");
        }

        public static class KrediKartiOdeme extends Odeme
        {   private String KartNumarasi;
            public KrediKartiOdeme(double miktar, String kartNumarasi)
            {
                super(miktar);
                this.KartNumarasi = kartNumarasi;
            }

            @Override
            public void odemeYap() {
                System.out.println("Kredi karti ("+KartNumarasi+") ile "+miktar+" TL odeme yapildi.");
            }
        }

        public static class NakitOdeme extends Odeme
        {
            public NakitOdeme(double miktar)
            {
                super(miktar);
            }

            @Override
            public void odemeYap() {
                System.out.println(miktar+" TL nakit odeme yapildi.");
            }
        }

}


}
