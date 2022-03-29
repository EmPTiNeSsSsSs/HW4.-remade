package com.company;

import java.util.Random;

public class Main{




    public static int[] heroesHealth = {270,280,260,230};
    public static int[] heroesDamage = {20,15,25,0};
    public static String[] heroesAttackType = {"Physical","Magical","Kinetic","Medic"};

    public static int bossHealth = 700;
    public static  int bossDamage = 50;
    public static String bossDefenceType = "";
    public static int roundNumber = 0;

    public static void chooseBossDefenceType(){
        Random random = new Random();
        int randNum = random.nextInt(heroesAttackType.length);  // 0 1 2
        bossDefenceType = heroesAttackType[randNum];
        System.out.println("Boss choose: "+ bossDefenceType);
    }

    public static void heroesHit(){
        for (int i = 0; i < heroesDamage.length; i++) {
            if(heroesHealth[i]>0 && bossHealth>0){
                if(heroesAttackType[i] == bossDefenceType){
                    Random r = new Random();
                    int coefficient = r.nextInt(8)+2; // 0 1 2 3 4 5 6 7

                    if(bossHealth < heroesDamage[i]*coefficient){
                        bossHealth = 0;
                    } else {
                        bossHealth =  bossHealth - heroesDamage[i]*coefficient;
                    }
                    System.out.println("Critical Damage: "+ heroesDamage[i]*coefficient);
                } else {
                    if(bossHealth < heroesDamage[i]){
                        bossHealth = 0;
                    } else {
                        bossHealth =  bossHealth - heroesDamage[i];
                    }
                }
            }
        }
    }
    public static void bossHits(){
        for (int i = 0; i < heroesHealth.length; i++) {
            if(heroesHealth[i] < bossDamage){
                heroesHealth[i] = 0;
            } else{
                heroesHealth[i] =  heroesHealth[i] - bossDamage;
            }
        }
    }
    public static void main(String[] args) {
        printDStatistics();
        isGameFinished();
        while (!isGameFinished()){
            round();
        }
        HealerHeal();


    }
    public static Boolean isGameFinished(){
        if(bossHealth <= 0){
            System.out.println("Heroes won!");
            return true;
        }

        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if(heroesHealth[i]>0){
                allHeroesDead = false;
                break;
            }
        }

        if (allHeroesDead){
            System.out.println("BOSS WON!");
        }
        return allHeroesDead;
    }

    private static void round() {
        roundNumber++;
        chooseBossDefenceType();
        bossHits();
        heroesHit();
        printDStatistics();
    }


    public static void printDStatistics() {
        System.out.println(roundNumber+" ROUND **************");
        System.out.println("Boss health: "+ bossHealth + " ["+ bossDamage+ "]" );
        for (int i = 0; i < heroesHealth.length ; i++) {
            System.out.println(heroesAttackType[i]+" health: "+ heroesHealth[i]+" ["+ heroesDamage[i]+ "]" );
        }
        System.out.println();

    }

    public static void HealerHeal() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (1 == 3) {
                continue;
            }
            if (heroesHealth[i] > 0 && heroesHealth[i] < 100 && heroesHealth[i] > 0) {
                heroesHealth[i] += 40;
                System.out.println("Medic help: " + heroesAttackType[i]);
                break;
            }
        }
    }




}
