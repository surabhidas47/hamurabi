import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        new Main().playGame();
    }

    Scanner scanner = new Scanner(System.in);
    Random random = new Random();

    public static int year;
    public static int bushelsOwned;
    public static int acresOwned = 1000;
    public static int population = 100;
    public static int landPrice = 19;
    public static int totalDeaths = 0;
    public static int percentDied = 0;
    public static int deaths;
    public static int harvest = 3000;
    public static int yeild = 3;
    public static int immigrants = 0;
    public static int bushels = 2800;
    public static int acres = harvest / yeild;
    public static int eaten = harvest - bushels;
    public static int fullPeople;
    //static int temp;
    public static boolean sanityCheck;
    public boolean gameOn;


    public void playGame() {
        //in while loop
        //call print statement at the end of each year
        newYear();
        finished();


    }

    private void newYear() {
        while (year < 10) {
            if (gameOn==true) {
                break;
            }
            year += 1;

            //new population
            //new land price


            if (askHowManyAcresToBuy(landPrice) > 0) {


            } else {

                askHowManyAcresToSell(acres);
            }
            askHowMuchGrainToFeedPeople(bushels);
            askHowManyAcresToPlant();
            plagueDeaths(population);
            starvationDeaths(population,bushels);
            uprising(population,deaths);
            if (uprising(population,deaths)==true) {
                terminateTheGame();
            }



        }

        //final statement
    }

    public int plagueDeaths(int population) {
        if (random.nextDouble() < 0.15) {
            int deaths = population / 2;

        return deaths;
             } else {
        return 0;
        }

    }



    public int starvationDeaths(int population, int bushels) {

        int howManyMoneyWeNeed = population * 20;

        if (bushels < howManyMoneyWeNeed) {
            int shortfall = howManyMoneyWeNeed - bushels;
            deaths = (int) Math.ceil(shortfall / 20);
            population-=deaths;
        }
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! " +  deaths);

        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!Remaining pop " + population);
        return deaths;

    }
    public boolean uprising(int population, int howManyPeopleStarved) {

        double starvationPercentage = (double) howManyPeopleStarved / population * 100;
        if (starvationPercentage > 45) {
            return true;
        }
        return false;
    }

    public void terminateTheGame() {
        finished();
        gameOn =true;

    }


    public int askHowManyAcresToBuy(int landPrice) {

        sanityCheck = false;

        int playerWantToBuy= getNumber("HOW MANY ACRES DO YOU WISH TO BUY?  "); //print out a prompting question;

        while (sanityCheck != true) {

            if (playerWantToBuy  * landPrice > bushels) {
                System.out.println("HAMURABI:  THINK AGAIN. YOU HAVE ONLY\n" +
                        bushels + " BUSHELS OF GRAIN. NOW THEN,");
                askHowManyAcresToBuy(landPrice);

            }
            else {sanityCheck =true;}
            while (!sanityCheck);
            acresOwned += playerWantToBuy;
            bushels -= playerWantToBuy * landPrice;



            System.out.println("Bushels left:  " + bushels + "  and acres Added   " +acresOwned+ " ");

        }

        return playerWantToBuy;

        }

    int askHowManyAcresToSell(int acresOwned) {
        sanityCheck = false;

        int playerWantToSell = getNumber("HOW MANY ACRES DO YOU WISH TO SELL?  "); //print out a prompting question;


        while (sanityCheck != true) {

            if (playerWantToSell < 0 || playerWantToSell > acresOwned) {

                System.out.println("HAMURABI:  THINK AGAIN. YOU OWN ONLY " + acresOwned + " ACRES. NOW THEN,");
                askHowManyAcresToSell(acresOwned);
            } else {
                sanityCheck = true;

            }

            bushels += playerWantToSell * landPrice;
            this.acresOwned -= playerWantToSell;

            System.out.println("money in wallet " + bushels);
            System.out.println("number of acres " + this.acresOwned);


        }
        return playerWantToSell;
    }

       int askHowMuchGrainToFeedPeople (int bushels){
            sanityCheck = false;

            int playerWantToFeed = getNumber("\nHOW MANY BUSHELS DO YOU WISH TO FEED YOUR PEOPLE?  ");


               while (sanityCheck != true) {
                   if (playerWantToFeed > bushels && playerWantToFeed < 0) {
                       System.out.println("hammurabi.src.main.java.HAMURABI:  THINK AGAIN. YOU HAVE ONLY\n" +
                               bushels + " BUSHELS OF GRAIN. NOW THEN,");
                       askHowMuchGrainToFeedPeople(bushels);
                   } else {
                       sanityCheck = true;
                   }
               }
               bushels -= playerWantToFeed;


               return bushels;
           }


    int askHowManyAcresToPlant() {
        sanityCheck = false;

                int playerWantsToPlant = getNumber("\nHOW MANY ACRES DO YOU WISH TO PLANT WITH SEED?  ");

        while (sanityCheck!= true) {

            if (playerWantsToPlant < 0 || playerWantsToPlant > acresOwned){

                System.out.println("HAMURABI:  THINK AGAIN. YOU OWN ONLY " + acresOwned + " ACRES. NOW THEN,");
                askHowManyAcresToPlant();
            }else if (playerWantsToPlant / 2 > bushels)  {
                System.out.println("hammurabi.src.main.java.HAMURABI:  THINK AGAIN. YOU HAVE ONLY\n" +
                        bushels + " BUSHELS OF GRAIN. NOW THEN,");
                askHowManyAcresToPlant();
            }else if(playerWantsToPlant>population * 10) {
                System.out.println("BUT YOU HAVE ONLY" + population + "PEOPLE TO TEND THE FIELDS. NOW THEN,");
                askHowManyAcresToPlant();
            } else {sanityCheck=true;}

        } while (!sanityCheck);
        bushels += playerWantsToPlant * landPrice;
       acresOwned -= playerWantsToPlant;

        return playerWantsToPlant;

    }




    int getNumber(String message) {
        while (true) {
            System.out.print(message);
            try {
                return scanner.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.println("\"" + scanner.next() + "\" isn't a number!");
            }
        }
    }



    final static String FINK = "DUE TO THIS EXTREME MISMANAGEMENT YOU HAVE NOT ONLY\n" +
            "BEEN IMPEACHED AND THROWN OUT OF OFFICE BUT YOU HAVE\n" +
            "ALSO BEEN DECLARED PERSONA NON GRATA!!\n";

    private void finished() {
        String answer = "IN YOUR 10-YEAR TERM OF OFFICE, " + percentDied + " PERCENT OF THE\n" +
                "POPULATION STARVED PER YEAR ON AVERAGE, I.E., A TOTAL OF\n" +
                totalDeaths + " PEOPLE DIED!!\n" +
                "YOU STARTED WITH 10 ACRES PER PERSON AND ENDED WITH\n" +
                acres / population + " ACRES PER PERSON\n\n";
        if (percentDied > 33 || acres / population < 7)
            answer += FINK;
        else if (percentDied > 10 || acres / population < 9)
            answer += "YOUR HEAVY-HANDED PERFORMANCE SMACKS OF NERO AND IVAN IV.\n" +
                    "THE PEOPLE (REMAINING) FIND YOU AN UNPLEASANT RULER, AND,\n" +
                    "FRANKLY, HATE YOUR GUTS!";
        else if (percentDied > 3 || acres / population < 10)
            answer += "YOUR PERFORMANCE COULD HAVE BEEN SOMEWHAT BETTER, BUT\n" +
                    "REALLY WASN'T TOO BAD AT ALL.\n" +
                    Math.random() * population * .8 + " PEOPLE WOULD" +
                    "DEARLY LIKE TO SEE YOU ASSASSINATED BUT WE ALL HAVE OUR" +
                    "TRIVIAL PROBLEMS";
        else
            answer += "A FANTASTIC PERFORMANCE!!!  CHARLEMANGE, DISRAELI, AND\n" +
                    "JEFFERSON COMBINED COULD NOT HAVE DONE BETTER!";
        answer += "\n\n\n\n\n\n\n\n\n\nSo long for now.";
        System.out.println(answer);
    }










    private int getYear() {
        this.year = year;
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public int getPopulation() {
        return population;
    }
    public void setPopulation(int population) {
        this.population = population;
    }
    public int getAcresOwned() {
        return acresOwned;
    }
    public void setAcresOwned(int acresOwned) {
        this.acresOwned = acresOwned;
    }
    public int getBushelsOwned() {
        return bushelsOwned;
    }
    public void setBushelsOwned(int bushelsOwned) {
        this.bushelsOwned = bushelsOwned;
    }
    public int getLandPrice() {
        return landPrice;
    }
    public void setLandPrice(int landPrice) {
        this.landPrice = landPrice;
    }
    public int getPercentDied() {
        return percentDied;
    }
    public void setPercentDied(int percentDied) {
        this.percentDied = percentDied;
    }
    public int getTotalDeaths() {
        return totalDeaths;
    }
   /* public int getStoresOfGrain() {
        return storesOfGrain;
    }
    public void setStoresOfGrain(int storesOfGrain) {
        this.storesOfGrain = storesOfGrain;
    } */
    public int getImmigrants() {
        return immigrants;
    }
    public void setImmigrants(int immigrants) {
        this.immigrants = immigrants;
    }
    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }
    public int getDeaths() {
        return deaths;
    }
    public int getHarvest() {
        return harvest;
    }
    public void setHarvest(int harvest) {
        this.harvest = harvest;
    }
    public int getYeild() {
        return yeild;
    }
    public void setYeild(int yeild) {
        this.yeild = yeild;
    }
    public int getEaten() {
        return eaten;
    }
    public void setEaten(int eaten) {
        this.eaten = eaten;
    }
    public int getFullPeople() {
        return fullPeople;
    }
    public void setFullPeople(int fullPeople) {
        this.fullPeople = fullPeople;
    }

}



//methods to call

    /*private void askHowManyAcresToBuy( int price, int bushels) {
        System.out.print("HOW MANY ACRES DO YOU WISH TO BUY?  ");
        //while
        //market price of bushels fluctates ... initual i s
        //so if person enters
    }
    private void askHowManyAcresToSell( int price, int bushels) {

    }

    private void askHowMuchGrainToFeedPeople( int price, int bushels) {

    }

    private void askHowManyAcresToPlant (){

    }


    private boolean sanityCheck() {

        return true;
    }

}


/*for (int i=0; i<10;i++){
            do {
                askHowManyAcresToBuy( int price, int bushels);
            } while (sanityCheck=tr);
            do {
                askHowManyAcresToSell( int acresOwned);
            } while (sanityCheck());
            do {
                askHowMuchGrainToFeedPeople( int bushels)
            }while (sanityCheck());
            do {
                askHowManyAcresToPlant (int acresOwned, int population, int bushels)
                while(sanityCheck());


            }
        }*/




