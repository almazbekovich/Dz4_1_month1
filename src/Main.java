import java.util.Random;

public class Main {
    public static int bossHealth = 600;
    public static int bossDamage = 50;
    public static String bossDefence;
    public static int[] heroesHealth = {250, 260, 270, 265};
    public static int[] heroesDamage = {20, 15, 10, 0};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Heal"};
    public static int roundNumber = 0;

    public static void main(String[] args) {
        printStatistics();
        while (!isGameOver()) {
            playRound();
        }
    }

    public static void playRound() {
        roundNumber++;
        chooseBossDefence();
        bossAttack();
        heroesAttack();
        medicHeal();
        printStatistics();


        if (isGameOver()) {
            if (bossHealth <= 0) {
                System.out.println("Heroes won!!!");
            } else {
                System.out.println("Boss won!!!");
            }
        }
    }

    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length - 1);
        bossDefence = heroesAttackType[randomIndex];
    }

    public static void bossAttack() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] -= bossDamage;
                }
            }
        }
    }

    public static void heroesAttack() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesAttackType[i].equals("Heal")) {

                } else {
                    int damage = heroesDamage[i];
                    if (heroesAttackType[i] == bossDefence) {
                        Random random = new Random();
                        int coeff = random.nextInt(9) + 2;
                        damage = heroesDamage[i] * coeff;
                        System.out.println("Critical damage: " + damage);
                    }
                    if (bossHealth - damage < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth -= damage;
                    }
                }
            }
        }
    }

    public static void medicHeal() {
        Random random = new Random();
        int targetIndex = random.nextInt(heroesHealth.length - 1);


        if (heroesHealth[heroesHealth.length - 1] > 0 && heroesHealth[heroesHealth.length - 1] < 100) {

            if (heroesHealth[targetIndex] < 100 && heroesHealth[targetIndex] > 0) {
                int healAmount = 50;
                heroesHealth[targetIndex] += healAmount;
                System.out.println("Medic healed hero " + (targetIndex + 1) + " for " + healAmount + " health.");
            }
        }
    }

    public static void printStatistics() {
        System.out.println("ROUND " + roundNumber + " ---------------");
        System.out.println("Boss health: " + bossHealth + " damage: " + bossDamage + " defence: " +
                (bossDefence == null ? "No defence" : bossDefence));
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health: " + heroesHealth[i] + " damage: " + heroesDamage[i]);
        }
    }

    public static boolean isGameOver() {
        if (bossHealth <= 0) {
            return true;
        }

        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (i != heroesHealth.length - 1 && heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }

        return allHeroesDead;
    }
}
