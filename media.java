public class media
{
    static class Media
    {
        String title;
        int ReleaseYear;

        public Media(String title,int ReleaseYear){
            this.title=title;
            this.ReleaseYear=ReleaseYear;
        }

        void play()
        {
            System.out.println("Media is being played.");
        }

        void showInfo()
        {
            System.out.println("Title: "+title+ " Release Year: "+ReleaseYear);
        }
    }

    static class Movie extends Media
    {
        String Director;

        public Movie(String title, String Director, int ReleaseYear)
        {   super(title, ReleaseYear);
            this.Director=Director;
        }

        @Override

        void play()
        {
            System.out.println("A movie is being played : "+title+" Director: "+Director);
        }

        @Override
        void showInfo()
        {
            super.showInfo();
            System.out.println("Type: Movie");
        }
    }
    
    static class Podcast extends Media
    {
        String guest;

        public Podcast(String title,int ReleaseYear, String guest)
        {
            super(title, ReleaseYear);
            this.guest=guest;
        }

        @Override

        void play()
        {
            System.out.println("A Podcast is being played : "+title+" Guest: "+guest);
        }

        @Override
        void showInfo()
        {
            super.showInfo();
            System.out.println("Type: Podcast");
        }
    }

    public static void main(String[] args) {
        Media[] mediaList={new Movie("Paprika ", " Satoshi Kon ", 2007),
         new Podcast("Talk Tuah", 2024, "Cookie King")};

        for (Media m : mediaList) {
            m.showInfo();
            m.play();
            System.out.println("-----------------");
        }
    }
}