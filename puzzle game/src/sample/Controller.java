package sample;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.*;

import javafx.event.ActionEvent;
import javafx.animation.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;
import javafx.scene.text.Text;
import javafx.scene.transform.Translate;
import javafx.util.Duration;


import java.awt.*;
import java.sql.SQLOutput;
import java.util.*;
import java.util.List;

public class Controller {

    public Button rollButton;
    public TextArea processText;
    public String record = "";
    private static int nowBlue = 0;
    private static int nowRed = 2;
    private static int nowYellow = 3;
    private static int nowGreen = 1;
    private static int[] nowPos = {-1, -1, -1, -1};
    private static int[] test = {0};
    private static int posTest = 0;

    public Text numFinal;
    public Circle p1, p2, p3, p4, p5, p6, p7, p8, p9, p10,
            p11, p12, p13, p14, p15, p16, p17, p18, p19, p20,
            p21, p22, p23, p24, p25, p26, p27, p28, p29, p30,
            p31, p32, p33, p34, p35, p36, p37, p38, p39, p40,
            p41, p42, p43, p44, p45, p46, p47, p48, p49, p50,
            p51, p52, p53, p54, p55, p56, p57, p58, p59, p60,
            p61, p62, p63, p64, p65, p66, p67, p68, p69, p70,
            p71, p72, p73, p74, p75, p76;
    private boolean rollDone = false; // to record whether the dice has been rolled
    private boolean flyDone = false; // to record whether the piece has flied
    public Circle blueP;
    public Circle yellowP;
    public Circle greenP;
    public Circle redP;
    public Circle spB;
    public Circle spG;
    public Circle spR;
    public Circle spY;
    private Circle[] routeBlue;
    private Circle[] routeRed;
    private Circle[] routeGreen;
    private Circle[] routeYellow;
    private Color[] colorOrder;

    private int numRoll = 0; // keep the number after the rolling

    // the HashMap is to keep the positions of each piece on the map
    // in order to run the crash part(there should be just one piece on one position, so if the other piece stop on the
    // position which has already had a piece on it, the previous piece will be crashed and go back to the start position)
    HashMap<Node, double[]> posMap = new HashMap<Node, double[]>();
    HashMap<Node, Circle> totalMap = new HashMap<Node, Circle>();


    //String[] order = new String[] {"Blue", "Red", "Yellow", "Green"};
    String[] order = new String[] {"Blue", "Green", "Red", "Yellow"};
    int orderPosition = 0;

    // test use keep the position of each color
    double[] originYellow = {320, 320};
    double[] originRed = {320, 80};
    double[] originBlue = {80, 320};
    double[] originGreen = {80, 80};

    double[] startYellow = {370, 260};
    double[] startRed = {260, 30};
    double[] startBlue = {140, 370};
    double[] startGreen = {30, 140};

    //double[][] originPos = {originBlue, originRed, originYellow, originGreen};
    double[][] originPos = {originBlue, originGreen, originRed, originYellow};
    //double[][] startPos = {startBlue, startRed, startYellow, startGreen};
    double[][] startPos = {startBlue, startGreen, startRed, startYellow};


    // initialize the route of each color
    public void initialize(){
        //colorOrder = new Color[] {Color.BLUE, Color.RED, Color.YELLOW, Color.GREEN};
        colorOrder = new Color[] {Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW};

        routeBlue = new Circle[] {
                spB, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50,  //0-11
                p51, p52, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10,           //12-23
                p11, p12, p13, p14, p15, p16, p17, p18, p19, p20,            //24-33
                p21, p22, p23, p24, p25, p26, p27, p28, p29, p30,            //34-43
                p31, p32, p33, p34, p35, p36, p37, p53, p54, p55,            //44-53
                p56, p57, p58};                                              //54-56

        routeRed = new Circle[] {
                spR, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24,   //0-11
                p25, p26, p27, p28, p29, p30, p31, p32, p33, p34, p35, p36,   //12-23
                p37, p38, p39, p40, p41, p42, p43, p44, p45, p46,             //24-33
                p47, p48, p49, p50, p51, p52, p1, p2, p3, p4,                 //34-43
                p5, p6, p7, p8, p9, p10, p11, p65, p66, p67,                  //44-53
                p68, p69, p70};                                               //54-56

        routeGreen = new Circle[] {
                spG, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11,            //0-11
                p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23,   //12-23
                p24, p25, p26, p27, p28, p29, p30, p31, p32, p33,             //24-33
                p34, p35, p36, p37, p38, p39, p40, p41, p42, p43,             //34-43
                p44, p45, p46, p47, p48, p49, p50, p59, p60, p61,             //44-53
                p62, p63, p64};                                               //54-56

        routeYellow = new Circle[] {
                spY, p27, p28, p29, p30, p31, p32, p33, p34, p35, p36, p37,   //0-11
                p38, p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49,   //12-23
                p50, p51, p52, p1, p2, p3, p4, p5, p6, p7,                    //24-33
                p8, p9, p10, p11, p12, p13, p14, p15, p16, p17,               //34-43
                p18, p19, p20, p21, p22, p23, p24, p71, p72, p73,             //44-53
                p74, p75, p76};                                               //54-56
    }
    //test


    //set up a sequence for the game
    private void nextPlayer(){
        orderPosition++;
        if(orderPosition > 3){
            orderPosition = 0;
        }
        //chooseWordColor();
    }

    private void chooseWordColor(){
        String res = "";
        if(orderPosition == 0){
            res = "-fx-text-fill: blue";
        }
        else if(orderPosition == 1){
            res = "-fx-text-fill: green";
        }
        else if(orderPosition == 2){
            res = "-fx-text-fill: red";
        }
        else{
            res = "-fx-text-fill: yellow";
        }
        processText.setStyle(res);
    }

    // compare the positions of two pieces, the array will be [x, y]
    private boolean posSame(double[] a, double[] b){
        if(a.length != b.length){
            return false;
        }
        for(int i = 0; i < a.length; i++){
            if(a[i] != b[i]){
                return false;
            }
        }
        return true;
    }

    // roll the dice to get the random number
    private int rollDice(){
        Random roll = new Random();
        return (roll.nextInt(6) + 1);
    }

    // press the roll button to get the number of movement with the animation of 1-6 rolling
    public void pressButton(ActionEvent a) {
        if(numRoll == 0){
            rollDone = true;
            final String content = "123456";
            final Animation animation = new Transition() {
                {
                    setCycleDuration(Duration.seconds(0.05));
                    setCycleCount(10);
                    setInterpolator(Interpolator.LINEAR);
                    setOnFinished((e) -> {
                        numRoll = rollDice();
                        numFinal.setText("" + numRoll);
                        //record += order[orderPosition] + " have a " + numRoll + "\n";
                        //processText.setText(record);
                        processText.appendText(order[orderPosition] + " have a " + numRoll + "\n");
                        if(!checkPlaneOnMap() && numRoll != 6){
                            processText.appendText("you have to roll a 6 to start\n");
                            numRoll = 0;
                            nextPlayer();
                            rollDone = false;
                        }
                        if(numRoll == 6){
                            //record = record + "you can roll again\n";
                            //processText.setText(record);
                            processText.appendText("Choose a " + order[orderPosition] + " plane\nAnd you can roll again\n");
                        }
                    });
                }
                @Override
                protected void interpolate(double v) {
                    final int length = content.length();
                    final int n = Math.round(length * (float) v);
                    if(n < length) {
                        numFinal.setText(content.substring(n, n + 1));
                    }
                }
            };
            animation.play();
        }
        else{
            //record += "you have roll the dice\nplease choose your plane.\n";
            //processText.setText(record);
            processText.appendText("you have roll the dice\nplease choose your plane.\n");
        }
    }

    // blue pieces movement, nowBlue is the current position of the blue pieces, the number represent the position in
    // routeBlue, if it is 1, it means it is now on the position of routeBlue[1] which is the circle of the map
    public void goBlue(MouseEvent a){
        if(!rollDone){
            processText.appendText(order[orderPosition] + " please roll the dice.\n");
        }
        else{
            if(order[orderPosition].equals("Blue")){
                int step = numRoll;
                if(step != 6){
                    nextPlayer();
                }
                //int step = 6;
                if(nowPos[nowBlue] == -1){
                    moveToStart(a.getPickResult().getIntersectedNode(), startPos, nowBlue);
                }
                else{
                    goSBS(a.getPickResult().getIntersectedNode(), step, nowPos[nowBlue], nowBlue, routeBlue);
                    System.out.println("Blue is now on " + nowPos[nowBlue]);
                }
            }
            else{
                //record = record + "it's not your turn.\n" + "Choose a " + order[orderPosition] + " one.\n";
                //processText.setText(record);
                processText.appendText("it's not your turn.\n" + "Choose a " + order[orderPosition] + " one.\n");
            }
        }

        /*
        int step = numRoll;
        if(step != 6){
            nextPlayer();
        }
        //int step = 6;
        goSBS(a.getPickResult().getIntersectedNode(), step, nowPos[nowBlue], nowBlue, routeBlue);
        System.out.println("Blue is now on " + nowPos[nowBlue]);

         */
    }

    public void goRed(MouseEvent a){
        if(!rollDone){
            processText.appendText(order[orderPosition] + " please roll the dice.\n");
        }
        else{
            if(order[orderPosition].equals("Red")){
                int step = numRoll;
                if(step != 6){
                    nextPlayer();
                }
                //int step = 6;
                if(nowPos[nowRed] == -1){
                    moveToStart(a.getPickResult().getIntersectedNode(), startPos, nowRed);
                }
                else{
                    goSBS(a.getPickResult().getIntersectedNode(), step, nowPos[nowRed], nowRed, routeRed);
                    System.out.println("Red is now on " + nowPos[nowRed]);
                }
            }
            else{
                //record = record + "it's not your turn.\n" + "Choose a " + order[orderPosition] + " one.\n";
                //processText.setText(record);
                processText.appendText("it's not your turn.\n" + "Choose a " + order[orderPosition] + " one.\n");
            }
        }

         /*
        int step = numRoll;
        if(step != 6){
            nextPlayer();
        }
        //int step = 6;
        goSBS(a.getPickResult().getIntersectedNode(), step, nowPos[nowRed], nowRed, routeRed);
        System.out.println("Red is now on " + nowPos[nowRed]);

          */

    }

    public void goGreen(MouseEvent a){
        if(!rollDone){
            processText.appendText(order[orderPosition] + " please roll the dice.\n");
        }
        else{
            if(order[orderPosition].equals("Green")){
                int step = numRoll;
                if(step != 6){
                    nextPlayer();
                }
                //int step = 6;
                if(nowPos[nowGreen] == -1){
                    moveToStart(a.getPickResult().getIntersectedNode(), startPos, nowGreen);
                }
                else{
                    goSBS(a.getPickResult().getIntersectedNode(), step, nowPos[nowGreen], nowGreen, routeGreen);
                    System.out.println("Green is now on " + nowPos[nowGreen]);
                }
            }
            else{
                //record = record + "it's not your turn.\n" + "Choose a " + order[orderPosition] + " one.\n";
                //processText.setText(record);
                processText.appendText("it's not your turn.\n" + "Choose a " + order[orderPosition] + " one.\n");
            }
        }

        /*
        int step = numRoll;
        if(step != 6){
            nextPlayer();
        }
        //int step = 6;
        goSBS(a.getPickResult().getIntersectedNode(), step, nowPos[nowBlue], nowBlue, routeBlue);
        System.out.println("Blue is now on " + nowPos[nowBlue]);

         */
    }

    public void goYellow(MouseEvent a){
        if(!rollDone){
            processText.appendText(order[orderPosition] + " please roll the dice.\n");
        }
        else{
            if(order[orderPosition].equals("Yellow")){
                int step = numRoll;
                if(step != 6){
                    nextPlayer();
                }
                //int step = 6;
                if(nowPos[nowYellow] == -1){
                    moveToStart(a.getPickResult().getIntersectedNode(), startPos, nowYellow);
                }
                else{
                    goSBS(a.getPickResult().getIntersectedNode(), step, nowPos[nowYellow], nowYellow, routeYellow);
                    System.out.println("Yellow is now on " + nowPos[nowYellow]);
                }
            }
            else{
                //record = record + "it's not your turn.\n" + "Choose a " + order[orderPosition] + " one.\n";
                //processText.setText(record);
                processText.appendText("it's not your turn.\n" + "Choose a " + order[orderPosition] + " one.\n");
            }
        }

        /*
        int step = numRoll;
        if(step != 6){
            nextPlayer();
        }
        //int step = 6;
        goSBS(a.getPickResult().getIntersectedNode(), step, nowPos[nowBlue], nowBlue, routeBlue);
        System.out.println("Blue is now on " + nowPos[nowBlue]);

         */
    }

    // you can choose a plane in the airport to the start point if you roll a 6
    // (you can not choose the plane which has finished the route)
    public void moveToStart(Node n, double[][] p, int nowColor){
        TranslateTransition tt = new TranslateTransition();
        tt.setNode(n);
        tt.setDuration(Duration.seconds(0.5));
        double xdiff = p[nowColor][0] - n.getLayoutX();
        double ydiff = p[nowColor][1] - n.getLayoutY();
        tt.setToX(xdiff);
        tt.setToY(ydiff);
        tt.play();
        System.out.println("Move to Start Point");
        nowPos[nowColor] = 0;
        numRoll = 0;
        rollDone = false;
    }

    // go back to the start position
    public void moveToAirport(Node n, double[][] p, int nowColor){
        TranslateTransition tt = new TranslateTransition();
        tt.setNode(n);
        tt.setDuration(Duration.seconds(0.5));
        double xdiff = p[nowColor][0] - n.getLayoutX();
        double ydiff = p[nowColor][1] - n.getLayoutY();
        tt.setToX(xdiff);
        tt.setToY(ydiff);
        tt.play();
        nowPos[nowColor] = -1;
    }

    // go step by step with animation, s is the position the piece stops on, a is the roll number
    public void goSBS(Node n, int a, int s, int nowColor, Circle[] route){
        if(a == 0){
            numRoll = 0;
            if(s >= 57){

                nowPos[nowColor] = 2 * 57 - s - 2;
            }
            else{
                nowPos[nowColor] = s;
            }
            if(nowPos[nowColor] == 57 - 1){
                moveToAirport(n, originPos, nowColor);
                System.out.println("You win");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Congratulations! You Win!");
                alert.setOnHidden(evt -> Platform.exit());
                alert.show();
                return;
            }
            System.out.println("Finish");
            //System.out.println(routeBlue[nowBlue].getFill());
            if(s == 18){
                flyDone = true;
                cross(n, s, nowColor, route);
            }
            else if(nowPos[nowColor] < 57 - 8 && route[nowPos[nowColor]].getFill() == colorOrder[nowColor] && nowPos[nowColor] != 0 && rollDone){
                System.out.println("fly");
                flyDone = true;
                fly(n, 4, s, nowColor, route);
            }
            else{
                if(s < 57){
                    checkCrash(n, s, route);
                }
                else{
                    checkCrash(n, 2 * 57 - s - 2, route);
                }
            }
            rollDone = false;
            return;
        }
        if(s + 1 >= 57){
            TranslateTransition tt = new TranslateTransition();
            tt.setNode(n);
            tt.setDuration(Duration.seconds(0.1));
            double xdiff = route[2 * 57 - s - 3].getLayoutX() - n.getLayoutX();
            double ydiff = route[2 * 57 - s - 3].getLayoutY() - n.getLayoutY();
            tt.setToX(xdiff);
            tt.setToY(ydiff);
            //System.out.println(a);
            tt.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    goSBS(n, a - 1, s + 1, nowColor, route);
                }
            });
            tt.play();
        }
        else{
            TranslateTransition tt1 = new TranslateTransition();
            tt1.setNode(n);
            tt1.setDuration(Duration.seconds(0.1));
            double xdiff = route[s + 1].getLayoutX() - n.getLayoutX();
            double ydiff = route[s + 1].getLayoutY() - n.getLayoutY();
            tt1.setToX(xdiff);
            tt1.setToY(ydiff);
            //System.out.println(a);
            tt1.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    goSBS(n, a - 1, s + 1, nowColor, route);
                }
            });
            tt1.play();
        }
    }

    // fly part, if the piece stops on the position which has the same color as the piece, it can go to the next position
    // with the same color(which is going 4 steps more) except the cross part(the 19th position of each route (route[18])).
    public void fly(Node n, int a, int p, int nowColor, Circle[] route){
        if(a == 0 && p != 18){
            nowPos[nowColor] = p;
            flyDone = false;
            System.out.println("Fly done");
            checkCrash(n, p, route);
            return;
        }
        else if(a == 0){
            flyDone = false;
            cross(n, p, nowColor, route);
            System.out.println("Fly done");
            return;
        }
        TranslateTransition tt = new TranslateTransition();
        tt.setNode(n);
        tt.setDuration(Duration.seconds(0.1));
        double xdiff = route[p + 1].getLayoutX() - n.getLayoutX();
        double ydiff = route[p + 1].getLayoutY() - n.getLayoutY();
        tt.setToX(xdiff);
        tt.setToY(ydiff);
        tt.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                fly(n, a - 1, p + 1, nowColor, route);
            }
        });
        tt.play();
    }

    // cross part, it the piece stop on the route[18] of each route, it can go directly to the route[30] position
    // no matter stops on it after going steps to or flying to.
    // (if you going steps by steps to the position, you can go directly to route[30] and fly once more after crossing
    //  if you fly to the position, you can not fly once more after crossing)
    public void cross(Node n, int s, int nowColor, Circle[] route){
        TranslateTransition tt = new TranslateTransition();
        tt.setNode(n);
        tt.setDuration(Duration.seconds(0.5));
        double xdiff = route[s + 12].getLayoutX() - n.getLayoutX();
        double ydiff = route[s + 12].getLayoutY() - n.getLayoutY();
        tt.setToX(xdiff);
        tt.setToY(ydiff);
        tt.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(flyDone){
                    fly(n, 4, s + 12, nowColor, route);
                }
                else{
                    nowPos[nowColor] = 30;
                    checkCrash(n, s + 12, route);
                }
            }
        });
        tt.play();
    }

    // crash part, checking if there is an another piece on the same position
    // if it does, crash it (making the previous one go back to the start)
    public void checkCrash(Node n, int s, Circle[] route){
        if(totalMap.size() == 0){
            System.out.println("no plane on the Map");
        }
        else{
            System.out.println("has plane on the map and scan");
            System.out.println(totalMap);

            for (Node nd: totalMap.keySet()){
                if(totalMap.get(nd) == route[s] && !nd.getId().equals(n.getId())){
                    System.out.println(nd.getId() + " is on the place, crash it!");
                    moveToAirport(nd, originPos, scanNode(nd));
                    totalMap.remove(nd);
                    break;
                }
            }
            System.out.println("no other plane on the position");
        }
        if(s != 0){
            totalMap.put(n, route[s]);
        }
    }

    //scan the node to get the color of it
    public int scanNode(Node n){
        int i = -1;
        switch (n.getId()){
            case "blueP": i = 0; break;
            case "redP": i = 1; break;
            case "yellowP": i = 2; break;
            case "greenP": i = 3; break;
        }
        return i;
    }

    //check if there is a plane on the map, you have to roll a 6 to start the plane if you have no planes on the map
    public boolean checkPlaneOnMap(){
        if(nowPos[orderPosition] != -1){
            //record += "choose your " + order[orderPosition] + " plane\n";
            //processText.setText(record);
            processText.appendText("choose your " + order[orderPosition] + " plane\n");
            return true;
        }
        else{
            //record += "you have no " + order[orderPosition] + " plane on the field\nyou have to roll a 6 to start\n";
            //processText.setText(record);
            processText.appendText("no " + order[orderPosition] + " plane on the field\n");
            return false;
        }
    }




    // test
    public void testScanNode(MouseEvent a){
        int i = scanNode(a.getPickResult().getIntersectedNode());
        System.out.println(i);
    }


}
