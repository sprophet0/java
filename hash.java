import java.util.LinkedList;
import java.util.Random;


public class hash {

    public static void main(String[] args) {
        final int TABLO_BOYUTU = 100;
        final int ANAHTAR_SAYISI = 100;

        int[] anahtarlar = new int[ANAHTAR_SAYISI];
        Random rand = new Random();
        
        System.out.println(ANAHTAR_SAYISI + " adet rastgele anahtar üretiliyor (1-100 arası):");
        for (int i = 0; i < ANAHTAR_SAYISI; i++) {
            anahtarlar[i] = rand.nextInt(100) + 1;
            System.out.print(anahtarlar[i] + " ");
        }
        System.out.println("\n" + "-------------------------------------------------" + "\n");


        HashTableChaining chainingTable = new HashTableChaining(TABLO_BOYUTU);
        for (int anahtar : anahtarlar) {
            chainingTable.insert(anahtar);
        }
        System.out.println("✅ 1. YÖNTEM: AYRI ZİNCİRLEME (CHAINING)");
        System.out.println("Her dizi indeksi, o indekse hashlenen anahtarların bir listesini tutar.");
        chainingTable.printTable();
        System.out.println("-------------------------------------------------" + "\n");


        HashTableLinearProbing linearTable = new HashTableLinearProbing(TABLO_BOYUTU);
        for (int anahtar : anahtarlar) {
            linearTable.insert(anahtar);
        }
        System.out.println("✅ 2. YÖNTEM: DOĞRUSAL SONDALAMA (LINEAR PROBING)");
        System.out.println("Çakışma olursa, bir sonraki boş yuvaya (index + 1, index + 2, ...) bakılır.");
        linearTable.printTable();
        System.out.println("-------------------------------------------------" + "\n");

        
        HashTableQuadraticProbing quadraticTable = new HashTableQuadraticProbing(TABLO_BOYUTU);
        for (int anahtar : anahtarlar) {
            quadraticTable.insert(anahtar);
        }
        System.out.println("✅ 3. YÖNTEM: KARESEL SONDALAMA (QUADRATIC PROBING)");
        System.out.println("Çakışma olursa, karesel adımlarla (index + 1^2, index + 2^2, ...) boş yuvaya bakılır.");
        quadraticTable.printTable();
        System.out.println("-------------------------------------------------" + "\n");
    }
}


class HashTableChaining {
    private LinkedList<Integer>[] table;
    private int TABLE_SIZE;

    private int hash(int key) {
        return key % TABLE_SIZE;
    }

    @SuppressWarnings("unchecked")
    public HashTableChaining(int size) {
        this.TABLE_SIZE = size;
        table = new LinkedList[TABLE_SIZE];
        for (int i = 0; i < TABLE_SIZE; i++) {
            table[i] = new LinkedList<>();
        }
    }

    public void insert(int key) {
        int index = hash(key);
        table[index].add(key); 
    }

    public void printTable() {
        for (int i = 0; i < TABLE_SIZE; i++) {
            System.out.print("Index " + i + ": ");
            if (table[i].isEmpty()) {
                System.out.println("[]");
            } else {
                System.out.println(table[i]);
            }
        }
    }
}

class HashTableLinearProbing {
    private Integer[] table;
    private int TABLE_SIZE;

    private int hash(int key) {
        return key % TABLE_SIZE;
    }

    public HashTableLinearProbing(int size) {
        this.TABLE_SIZE = size;
        table = new Integer[TABLE_SIZE]; 
    }

    public void insert(int key) {
        int hash = hash(key);
        int i = 0;
        int index = (hash + i) % TABLE_SIZE; 

        while (table[index] != null) {
            i++; 
            index = (hash + i) % TABLE_SIZE;

            if (i == TABLE_SIZE) {
                System.out.println("Tablo dolu! Anahtar " + key + " eklenemedi.");
                return;
            }
        }
        
        table[index] = key;
    }

    public void printTable() {
        for (int i = 0; i < TABLE_SIZE; i++) {
            System.out.println("Index " + i + ": " + (table[i] == null ? "NULL (Boş)" : table[i]));
        }
    }
}


class HashTableQuadraticProbing {
    private Integer[] table;
    private int TABLE_SIZE;

    private int hash(int key) {
        return key % TABLE_SIZE;
    }

    public HashTableQuadraticProbing(int size) {
        this.TABLE_SIZE = size;
        table = new Integer[TABLE_SIZE]; 
    }

    public void insert(int key) {
        int hash = hash(key);
        int i = 0;
        int index = (hash + i * i) % TABLE_SIZE; 

        while (table[index] != null) {
            i++; 
            index = (hash + i * i) % TABLE_SIZE; 

            if (i == TABLE_SIZE) {
                System.out.println("Döngü tespit edildi veya tablo dolu! Anahtar " + key + " eklenemedi.");
                return;
            }
        }
        
        table[index] = key;
    }

    public void printTable() {
        for (int i = 0; i < TABLE_SIZE; i++) {
            System.out.println("Index " + i + ": " + (table[i] == null ? "NULL (Boş)" : table[i]));
        }
    }
}