import java.util.LinkedList;
import java.util.Queue;

class Dugum {
    int veri;
    Dugum sol;
    Dugum sag;

    public Dugum(int veri) {
        this.veri = veri;
        this.sol = null;
        this.sag = null;
    }
}

public class IkiliAramaAgaci {
    Dugum kok;

    public IkiliAramaAgaci() {
        this.kok = null;
    }

    public void ekle(int veri) {
        kok = ekleRec(kok, veri);
    }

    private Dugum ekleRec(Dugum mevcut, int veri) {
        if (mevcut == null) {
            mevcut = new Dugum(veri);
            return mevcut;
        }

        if (veri < mevcut.veri) {
            mevcut.sol = ekleRec(mevcut.sol, veri);
        } else if (veri > mevcut.veri) {
            mevcut.sag = ekleRec(mevcut.sag, veri);
        }

        return mevcut;
    }

    public boolean ara(int veri) {
        return araRec(kok, veri);
    }

    private boolean araRec(Dugum mevcut, int veri) {
        if (mevcut == null) {
            return false;
        }

        if (mevcut.veri == veri) {
            return true;
        }

        if (veri < mevcut.veri) {
            return araRec(mevcut.sol, veri);
        } else {
            return araRec(mevcut.sag, veri);
        }
    }

    public void sil(int veri) {
        kok = silRec(kok, veri);
    }

    private Dugum silRec(Dugum mevcut, int veri) {
        if (mevcut == null) {
            return mevcut;
        }

        if (veri < mevcut.veri) {
            mevcut.sol = silRec(mevcut.sol, veri);
        } else if (veri > mevcut.veri) {
            mevcut.sag = silRec(mevcut.sag, veri);
        } else {
            if (mevcut.sol == null && mevcut.sag == null) {
                return null;
            } else if (mevcut.sol == null) {
                return mevcut.sag;
            } else if (mevcut.sag == null) {
                return mevcut.sol;
            }

            mevcut.veri = enKucukDeger(mevcut.sag);
            mevcut.sag = silRec(mevcut.sag, mevcut.veri);
        }
        return mevcut;
    }

    private int enKucukDeger(Dugum dugum) {
        int minv = dugum.veri;
        while (dugum.sol != null) {
            minv = dugum.sol.veri;
            dugum = dugum.sol;
        }
        return minv;
    }

    public void levelOrder() {
        if (kok == null) {
            System.out.println("Ağaç boş.");
            return;
        }

        Queue<Dugum> kuyruk = new LinkedList<>();
        kuyruk.add(kok);

        System.out.print("Level-Order: ");
        while (!kuyruk.isEmpty()) {
            Dugum mevcut = kuyruk.poll();
            System.out.print(mevcut.veri + " ");

            if (mevcut.sol != null) {
                kuyruk.add(mevcut.sol);
            }

            if (mevcut.sag != null) {
                kuyruk.add(mevcut.sag);
            }
        }
        System.out.println();
    }

    public Dugum bulMinDugum() {
        if (kok == null)
            return null;
        Dugum mevcut = kok;
        while (mevcut.sol != null) {
            mevcut = mevcut.sol;
        }
        return mevcut;
    }

    public Dugum bulMaxDugum() {
        if (kok == null)
            return null;
        Dugum mevcut = kok;
        while (mevcut.sag != null) {
            mevcut = mevcut.sag;
        }
        return mevcut;
    }

    public Dugum bulAta(int veri) {
        if (kok == null || kok.veri == veri) {
            return null;
        }

        Dugum mevcut = kok;
        Dugum ata = null;

        while (mevcut != null) {
            if (veri < mevcut.veri) {
                ata = mevcut;
                mevcut = mevcut.sol;
            } else if (veri > mevcut.veri) {
                ata = mevcut;
                mevcut = mevcut.sag;
            } else {
                return ata;
            }
        }
        return null;
    }

    public Dugum bulKardes(int veri) {
        Dugum ata = bulAta(veri);
        if (ata == null) {
            return null;
        }

        if (ata.sol != null && ata.sol.veri == veri) {
            return ata.sag;
        }
        if (ata.sag != null && ata.sag.veri == veri) {
            return ata.sol;
        }

        return null;
    }

    public void minMaxBilgileriGoster() {
        Dugum minDugum = bulMinDugum();
        Dugum maxDugum = bulMaxDugum();

        if (minDugum != null) {
            Dugum minAta = bulAta(minDugum.veri);
            Dugum minKardes = bulKardes(minDugum.veri);
            System.out.println("Min Düğüm (" + minDugum.veri + "):");
            System.out.println("  Ata: " + (minAta != null ? minAta.veri : "Yok (Kök olabilir veya tek çocuk)"));
            System.out.println("  Kardeş: " + (minKardes != null ? minKardes.veri : "Yok"));
        }

        if (maxDugum != null) {
            Dugum maxAta = bulAta(maxDugum.veri);
            Dugum maxKardes = bulKardes(maxDugum.veri);
            System.out.println("Max Düğüm (" + maxDugum.veri + "):");
            System.out.println("  Ata: " + (maxAta != null ? maxAta.veri : "Yok (Kök olabilir veya tek çocuk)"));
            System.out.println("  Kardeş: " + (maxKardes != null ? maxKardes.veri : "Yok"));
        }
    }

    private int maksDengeFarki = -1;
    private Dugum maksFarkDugum = null;

    private int yukseklik(Dugum dugum) {
        if (dugum == null) {
            return -1;
        }
        return 1 + Math.max(yukseklik(dugum.sol), yukseklik(dugum.sag));
    }

    private int bulMaksDengeHelper(Dugum dugum) {
        if (dugum == null) {
            return -1;
        }

        int solYukseklik = bulMaksDengeHelper(dugum.sol);
        int sagYukseklik = bulMaksDengeHelper(dugum.sag);

        int fark = Math.abs(solYukseklik - sagYukseklik);

        if (fark > maksDengeFarki) {
            maksDengeFarki = fark;
            maksFarkDugum = dugum;
        }

        return 1 + Math.max(solYukseklik, sagYukseklik);
    }

    public Dugum bulMaksDengeFarkiDugum() {
        maksDengeFarki = -1;
        maksFarkDugum = null;

        bulMaksDengeHelper(kok);

        System.out.println("En yüksek denge farkı: " + maksDengeFarki);
        return maksFarkDugum;
    }

    public static void main(String[] args) {
        IkiliAramaAgaci agac = new IkiliAramaAgaci();

        agac.ekle(50);
        agac.ekle(30);
        agac.ekle(70);
        agac.ekle(20);
        agac.ekle(40);
        agac.ekle(60);
        agac.ekle(80);
        agac.ekle(25);
        agac.ekle(28);
        agac.ekle(29);

        agac.levelOrder();

        System.out.println("Ağaçta 40 var mı? " + agac.ara(40));
        System.out.println("Ağaçta 90 var mı? " + agac.ara(90));

        System.out.println("30 siliniyor...");
        agac.sil(30);
        agac.levelOrder();

        System.out.println("---");

        agac.minMaxBilgileriGoster();

        System.out.println("---");

        Dugum enDengesizDugum = agac.bulMaksDengeFarkiDugum();
        if (enDengesizDugum != null) {
            System.out.println("En yüksek denge farkına sahip düğüm: " + enDengesizDugum.veri);
        } else {
            System.out.println("Ağaç boş.");
        }
    }
}