import java.util.Base64;

public class ders1_2 {

    public static abstract class sifreleyici
    {
        protected String anahtar;
        public sifreleyici(String anahtar)
        {
            this.anahtar=anahtar;
        }

        public abstract String sifrele(String metin);
        public abstract String coz(String sifreliMetin);

        public void AnahtarGoster()
        {
            System.out.println("Kullanilan anahtar: "+anahtar);
        }

        public final void Log(String mesaj)
        {
            System.out.println("[LOG]: "+mesaj);
        }

        public static void bilgi()
        {
            System.out.println("Bu sinif cesitli sifreleme islemleri " + " icin temel soyut siniftir.");
        }
    }

    // Class must be static to be used easily from a static main method.
    public static class BasitBase64Encoder extends sifreleyici
    {
        public BasitBase64Encoder(String anahtar)
        {
            super(anahtar);
        }
        
        @Override
        public String sifrele(String metin)
        {
            // FIX 1: Removed invalid 'mesaj:' argument label
            Log("Metin sifreleniyor: " + metin); 
            
            // Appending the key to the plaintext
            String BirlesikMetin = metin + anahtar; 

            return Base64.getEncoder().encodeToString(BirlesikMetin.getBytes());
        }

        @Override
        public String coz(String sifreliMetin)
        {
            // FIX 2: Removed invalid 'mesaj:' argument label and added missing semicolon
            Log("Sifreli metin cozuluyor: " + sifreliMetin);
            
            // Decode the Base64 string into bytes, then convert to string
            byte[] cozulmusBytes = Base64.getDecoder().decode(sifreliMetin);
            String cozulmusMetin = new String(cozulmusBytes);

            if (cozulmusMetin.endsWith(anahtar)) {
                // Return the part of the string BEFORE the key
                return cozulmusMetin.substring(0, cozulmusMetin.length() - anahtar.length());
            } else {
                Log("HATA: Anahtar eslesmedi veya metin bozuk.");
                return cozulmusMetin; // Return full decoded text if key removal fails
            }
        }
    }

    public static void main(String[] args) {
        String key = "gizlianahtar";
        String originalMessage = "Merhaba Dunya!";

        // 1. Create the encoder/decoder
        sifreleyici encoder = new BasitBase64Encoder(key);
        encoder.AnahtarGoster();
        sifreleyici.bilgi();
        
        System.out.println("\n--- ISLEM ---");

        // 2. Encryption
        String encrypted = encoder.sifrele(originalMessage);
        System.out.println("Orijinal Metin: " + originalMessage);
        System.out.println("Sifreli Metin:  " + encrypted);

        System.out.println("\n--- ISLEM ---");
        
        // 3. Decryption
        String decrypted = encoder.coz(encrypted);
        System.out.println("Cozulen Metin:  " + decrypted);
        
        // Verification
        if (originalMessage.equals(decrypted)) {
            System.out.println("[SONUC]: Sifreleme ve cozme islemi basarili!");
        } else {
            System.out.println("[SONUC]: HATA! Cozulen metin orijinal metinle eslesmiyor.");
        }
    }
}