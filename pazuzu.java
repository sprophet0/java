public class pazuzu{
    public static class Character{
        protected String name;
        protected  int hp;
        protected int strength;

        public Character(String name, int hp, int strength){
            this.name=name;
            this.hp=hp;
            this.strength=strength;
        }

        public void ShowInfo(){
            System.out.println("Name: "+name);
            System.out.println("HP: "+hp);
            System.out.println("Strength: "+strength);
        }

        interface Attack{
            void Attack();
        }

        interface ChangeHP{
            void heal(int hp);
            void takeDmg(int hp);
        }

    }
    static class Player extends Character implements Character.Attack, Character.ChangeHP{
        private int lvl;

        public Player(String name, int hp, int strength, int lvl){
            super(name, hp, strength);
            this.lvl=lvl;
            System.out.println("Player constructor started");
        }

        @Override
        public void takeDmg(int damage) {
            this.hp-=damage;
        }
        @Override
        public void heal(int healAmount) {
            this.hp+=healAmount;
        }
        @Override
        public void Attack(){
            System.out.println(name+" dealt strong attack to enemy. Level: "+lvl);
        }
        public void LevelUp(){
            lvl++;
            strength+=10;
            System.out.println(name+" leveled up to level "+lvl);
        }
    }

    public static class Enemy extends Character implements Character.Attack{
        private String type;
        public Enemy(String name, int hp, int strength, String type){
            super(name, hp, strength);
            this.type = type;
            System.out.println("Enemy constructor started");
        }
        @Override
        public void Attack(){
            System.out.println(name+"("+type+") enemy attacks!");
        }
    }
    public static void main(String[] args) {
        Player player= new Player("Lion", 100, 20, 1);
    }
}
