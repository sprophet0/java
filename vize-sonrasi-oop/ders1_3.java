import java.util.*;

public class ders1_3 {
    
    // --- 1. Gözlemci (Observer) Arayüzü ---
    static abstract class SensorOlayDinleyici {
        public abstract void veriOlustu(String sensorAdi, double deger);
        public abstract void hataOlustu(String sensorAdi, String mesaj);
    }

    // --- 2. Özne (Subject) Sınıfı ---
    static abstract class Sensor {
        protected String ad;
        protected boolean aktif;
        protected List<SensorOlayDinleyici> dinleyiciler = new ArrayList<>();

        public Sensor(String ad) {
            this.ad = ad;
            this.aktif = true;
        }

        protected abstract double veriOku();
        protected abstract boolean veriGecerliMi(double veri);

        // Şablon Metodu (Template Method)
        public final void okuVeIsle() {
            if (!aktif) {
                bildirimHata("Sensor aktif degil.");
                return;
            }
            try {
                double veri = veriOku();
                if (veriGecerliMi(veri)) {
                    bildirimVeri(veri);
                } else {
                    bildirimHata("Gecersiz veri algilandi: " + String.format("%.2f", veri));
                }
            } catch (Exception e) {
                bildirimHata("okuma hatasi: " + e.getMessage());
            }
        }

        public void DinleyiciEkle(SensorOlayDinleyici dinleyici) {
            dinleyiciler.add(dinleyici);
        }

        protected void bildirimVeri(double veri) {
            for (SensorOlayDinleyici dinleyici : dinleyiciler) {
                dinleyici.veriOlustu(ad, veri);
            }
        }

        public void bildirimHata(String mesaj) {
            for (SensorOlayDinleyici dinleyici : dinleyiciler) {
                dinleyici.hataOlustu(ad, mesaj);
            }
        }
    }
    
    // --- 3. Somut Sınıflar ---
    static class SicaklikSensoru extends Sensor {
        private Random rnd = new Random();

        public SicaklikSensoru(String ad) {
            super(ad);
        }

        @Override
        protected double veriOku() {
            // -20 ile +60 arasında rastgele değer üretir
            return rnd.nextDouble() * 80 - 20; 
        }

        @Override
        protected boolean veriGecerliMi(double veri) {
            // -30 ile +70 arası geçerli kabul edilir
            return veri >= -30 && veri <= 70;
        }
    }

    static class NemSensoru extends Sensor {
        private Random rnd = new Random();

        public NemSensoru(String ad) {
            super(ad);
        }

        @Override
        protected double veriOku() {
            // -20 ile +60 arasında rastgele değer üretir
            // NOT: Nem negatif olamaz, bu yüzden bu formül bazen hata fırlatmamızı sağlayacak (Test amaçlı iyi)
            return rnd.nextDouble() * 80 - 20;
        }

        @Override
        protected boolean veriGecerliMi(double veri) {
            // Nem sadece 0 ile 100 arasında geçerlidir
            return veri >= 0 && veri <= 100;
        }
    }

    // --- 4. Main Metodu (Uygulama Alanı) ---
    public static void main(String[] args) {
        // Sensörlere isim vererek nesneleri oluşturuyoruz
        Sensor sicaklik = new SicaklikSensoru("Salon Termometre");
        Sensor nem = new NemSensoru("Mutfak Nem Ölçer");

        // Dinleyiciyi (Observer) oluşturuyoruz
        SensorOlayDinleyici dinleyici1 = new SensorOlayDinleyici() {
            @Override
            public void veriOlustu(String sensorAdi, double deger) {
                System.out.println("✅ [VERİ] " + sensorAdi + " -> Okunan Değer: " + String.format("%.2f", deger));
            }

            @Override
            public void hataOlustu(String sensorAdi, String mesaj) {
                System.out.println("⚠️ [HATA] " + sensorAdi + " -> " + mesaj);
            }
        };

        // Dinleyiciyi sensörlere abone ediyoruz
        sicaklik.DinleyiciEkle(dinleyici1);
        nem.DinleyiciEkle(dinleyici1);

        // Simülasyon: 5 kez veri okumayı deniyoruz
        System.out.println("--- Sensör Simülasyonu Başlıyor ---\n");
        
        for (int i = 1; i <= 5; i++) {
            System.out.println("Döngü: " + i);
            sicaklik.okuVeIsle();
            nem.okuVeIsle();
            System.out.println("-------------------------");
            
            // Çıktıyı yavaşlatmak için küçük bir bekleme (Opsiyonel)
            try { Thread.sleep(500); } catch (InterruptedException e) { }
        }
    }
}