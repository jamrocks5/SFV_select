public class MainClass {

    static final int NUM_FIGHTERS = 18;


    public static void main(String[] args){

        String[] fightNames = { "Alex",
                "Birdie", "Bison","Cammy","Chun-li","Dhalsim","Fang", "Guile",
                "Karin","Ken","Laura","Mika","Nash","Necalli","Rashid",
                "Ryu","Vega","Zangief"};

        IconData[] fighters = new IconData[NUM_FIGHTERS];

        for(int i = 0; i < fighters.length; i++){
            fighters[i] = new IconData(fightNames[i]);
        }
        GUI myGUI  = new GUI(fighters);
        myGUI.setVisible(true);
    }
}
