/*  This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

 */
package Hernandez_5_javafx;


import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.input.DataFormat.URL;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.text.Text;
//import javafx.event.ActionEvent;
//import javafx.scene.shape.Shape;
//import javafx.event.Event;
//import javafx.scene.Node;
//import javafx.scene.control.Button;
import javafx.scene.input.KeyCombination;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
//import static javafx.scene.input.KeyCombination.SHIFT_DOWN;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
//import javafx.scene.input.DataFormat.URL;
import javafx.scene.media.*;
import static javafx.scene.input.KeyCode.SPACE;
import static javafx.scene.paint.Color.YELLOW;



/**
 * For more information see:
 * http://stackoverflow.com/questions/21331519/how-to-get-smooth-animation-with-keypress-event-in-javafx
 * http://docs.oracle.com/javafx/2/scenegraph/jfxpub-scenegraph.htm
 * http://stackoverflow.com/questions/15013913/checking-collision-of-shapes-with-javafx
 * https://gamedevelopment.tutsplus.com/tutorials/introduction-to-javafx-for-game-development--cms-23835
 */
public class Hernandz_J_5th_JavaGame extends Application {

    Text sttx;
    public ArrayList<Wall> badblockz = new ArrayList();
    public ArrayList<Enemy> enemiez = new ArrayList();
    public Image error = new Image("file:pics/error.png");
    Rectangle forceuser;
    Rectangle jedi;
    Rectangle wallz;
    Rectangle swall;
    Hero Luke;
    Badperson Vader;
    publicB b1;
    Enemy stormtrooper1;
    Enemy stormtrooper2;
    Enemy stormtrooper3;
    Enemy Force;
    Rectangle barrier;
    Rectangle wall1;
    Rectangle wall2;
    Rectangle wall3;
    Rectangle wall4;
    boolean BEGIN = true;
    private Media media;

    @Override
    public void start(Stage primaryStage) {

        sttx = new Text(500, 500, " ");
//      sttx.setFont(value);
        Group root = new Group();
        swall = new Rectangle(0, 0, 600, 600);

        Scene scene = new Scene(root, 600, 600, YELLOW);
        primaryStage.setTitle("Duel of the fates");
        primaryStage.setScene(scene);

        Canvas canvas = new Canvas();
        //Notice gc is not being used yet 
        GraphicsContext gc = canvas.getGraphicsContext2D();

        b1 = new publicB();
        Luke = new Hero(36,42);
       Vader = new Badperson(6, 142);
       stormtrooper1 = new Enemy(850, 150, 40, 80, 1, "down");
        stormtrooper1.setFill(Color.DARKTURQUOISE);
        Force = new Enemy(500, 100, 40, 80, 1, "up");
        Force.setFill(Color.DARKTURQUOISE);
        stormtrooper2 = new Enemy(350, 450, 100, 40, 1, "right");
        stormtrooper2.setFill(Color.DARKTURQUOISE);
        stormtrooper3 = new Enemy(1050, 650, 80, 40, 1, "left");
        stormtrooper3.setFill(Color.DARKTURQUOISE);
        enemiez.add(stormtrooper1);
        enemiez.add(Force);
        enemiez.add(stormtrooper2);
        enemiez.add(stormtrooper3);
        barrier = new Wall(100, 100);



        media = new Media("file:///home/joshua02o/NetBeansProjects/Hernandez_J_5th_Java/src/Battleoftheheroes.wav");
        // beat = new MediaPlayer(media);
        // beat.play();
        //final URL resource = getClass().getResources("Battleoftheheroes.mp3");
        System.out.println("Now Playing: THE BEST SADDEST MUSIC IN THE WORLD!");

        /*
       Luke.picture.setX(Luke.getX());
       Luke.picture.setY(Luke.getY());

       Vader.photo.setX(Vader.getX());
       Vader.photo.setY(Vader.getY());
        //  Group root = new Group(Luke.picture);
        //notice we are creating shape objects 
       
         */
        sttx = new Text(750, 100, "");
        sttx.setFont(new Font(50));
        sttx.setText("Welcome\nto the\nJedi Academy!" + "\n\n\nPress '0' to start!");
        
        sttx.setTextAlignment(TextAlignment.CENTER);
        jedi = new Rectangle(300, 300, 23, 23);
        jedi.setFill(Color.GRAY);

        forceuser = new Rectangle(150, 50, 25, 25);
        forceuser.setFill(Color.BLUE);

        // notice the difference in how an Ahow to add images in javafxrrayList adds items 
        badblockz.add((Wall) forceuser);

        //we have created an animation timer --- the class MUST be overwritten - look below 
        AnimationTimer timer = new MyTimer();

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            
            public void handle(KeyEvent event) {
                checkBounds(jedi);
                if (!b1.BEGIN()) {
                    if (event.getCode() == KeyCode.DIGIT0) {
                        swall.setHeight(0);
                        swall.setWidth(0);
                        sttx.setText(" ");
                        b1.setBegin(true);
                        BEGIN = false;
                    }
                }

                if (!BEGIN) {
                    if (null!=event.getCode()) switch (event.getCode()) {
                        case D:
                        case RIGHT:
                            // don't use toString here!!!
                            Vader.setRight();
                            jedi.setX(jedi.getX() + 5);
                            checkBounds(jedi);
                            break;
                        case LEFT:
                        case A:
                            Vader.setLeft();
                            jedi.setX(jedi.getX() - 5);
                            checkBounds(jedi);
                            break;
                        case UP:
                        case W:
                            Vader.setUp();
                            jedi.setY(jedi.getY() - 5);
                            jedi.setFill(Color.GREEN);
                            checkBounds(jedi);
                            break;
                        case DOWN:
                        case S:
                            Vader.setDown();
                            jedi.setY(jedi.getY() + 5);
                            checkBounds(jedi);
                            break;
                        default:
                            break;
                    }
                }
            }

                private void checkBounds(Rectangle Luke) {
        Color light = Color.GOLDENROD;
        Color dark = Color.RED;
        Color grey = Color.DARKTURQUOISE;
        Color black = Color.RED;
        // checkBounds is called in two different locations --- it's really only necessary in the animation dohandle
        // experiment - check the differences

        boolean collisionDetected = false;

        // notice the difference in how an ArrayList itestormtrooperes through items
        for (Rectangle badblock : badblockz) {
            if (Luke.getBoundsInParent().intersects(badblock.getBoundsInParent())) {
                collisionDetected = true;
                badblock.setFill(black);
            } else {
                badblock.setFill(grey);
            }
        }
        if (collisionDetected) {
            Luke.setFill(dark);
        } else {
            Luke.setFill(light);
        }
    }
        });

//        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent event) {
//                if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.LEFT) {
//                    // forceuserangleVelocity.set(0);
//                }
//            }
//        });
        //try disabling canvas --- notice the difference 
        root.getChildren().add(canvas);
        //notice we are manually adding the COLOR.BLUE shape objects to the "root" window
        root.getChildren().add(forceuser);
        root.getChildren().add(jedi);
        root.getChildren().add(Luke);
        root.getChildren().add(Luke.picture);
        root.getChildren().add(Vader.photo);
        root.getChildren().add(swall);
        root.getChildren().add(wall1);
        root.getChildren().add(wall2);
        root.getChildren().add(wall3);
        root.getChildren().add(wall4);

        timer.start();
        primaryStage.show();
    }


    /**
     * @param args the command line arguments
     *
     * The same as before main just calls the argus described above
     */
    //// ^^^^^^^^^^^  MAIN ^^^^^^^^^^^^^
    // we create our time here --- to animate 
    public static void main(String[] args) {
        launch(args);

       
    }
    private abstract class MyTimer extends AnimationTimer {

        boolean movedown = true;
        boolean moveright = true;
        
        private void checkBounds(Rectangle Luke) {
        Color light = Color.GOLDENROD;
        Color dark = Color.RED;
        Color grey = Color.DARKTURQUOISE;
        Color black = Color.RED;
        // checkBounds is called in two different locations --- it's really only necessary in the animation dohandle
        // experiment - check the differences

        boolean collisionDetected = false;

        // notice the difference in how an ArrayList itestormtrooperes through items
        for (Rectangle badblock : badblockz) {
            if (Luke.getBoundsInParent().intersects(badblock.getBoundsInParent())) {
                collisionDetected = true;
                badblock.setFill(black);
            } else {
                badblock.setFill(grey);
            }
        }
        if (collisionDetected) {
            Luke.setFill(dark);
        } else {
            Luke.setFill(light);
        }
    }
    

    }


        /// handle is defined by the abstract parent class -- it must be redined
        /// this is what happens again and again until stop()
        public void handle(long now) {
            
            
          

            doHandle();

            ///i1.inty +=1;
            /// notice doHandle()  is what happens again and again it's defined below
        }
        

        
        
class Hero extends Rectangle {

    int x, y;
    int life;
    boolean isgoingUp, isgoingDown, isgoingLeft, isgoingRight;

    Image knight = new Image("file:pictures/knight.png");
    public ImageView picture = new ImageView(knight);

    public Hero(double x, double y) {

        super(x, y, 30, 30);
        this.setFill(Color.TRANSPARENT);

        System.out.println("Made a thing");

        this.picture.setX(this.getX());
        this.picture.setY(this.getY());
        this.picture.setFitHeight(70);
        this.picture.setFitWidth(70);

        this.life = 20;
    }

    void move() {
        if (this.isgoingUp) {
            this.moveUp();
        } else if (this.isgoingDown) {
            this.moveDown();
        } else if (this.isgoingLeft) {
            this.moveLeft();
        } else if (this.isgoingRight) {
            this.moveRight();
        }
        this.picture.setX(this.getX());
        this.picture.setY(this.getY());
    }

    public void moveDown() {
        if (this.getY() < 200) {
            this.setY(this.getY() + 15);
        }
    }

    public void moveUp() {
        if (this.getY() > 200) {
            this.setY(this.getY() - 15);
        }
    }

    public void moveRight() {
        if (this.getX() < 800) {
            this.setY(this.getX() - 15);
        }
    }

    public void moveLeft() {
        if (this.getX() > 800) {
            this.setY(this.getX() + 15);
        }
    }

    void setUp() {
        this.isgoingUp = true;
        this.isgoingDown = false;
        this.isgoingLeft = false;
        this.isgoingRight = false;
    }

    void setDown() {
        this.isgoingUp = false;
        this.isgoingDown = true;
        this.isgoingLeft = false;
        this.isgoingRight = false;
    }

    void setRight() {
        this.isgoingUp = true;
        this.isgoingDown = false;
        this.isgoingLeft = false;
        this.isgoingRight = true;
    }

    void setLeft() {
        this.isgoingUp = false;
        this.isgoingDown = false;
        this.isgoingLeft = false;
        this.isgoingRight = true;
    }
    
    
    public void useTransport() {
        Random randomx = new Random();
        Random randomy = new Random();
        int rx = (randomx.nextInt(1500) + 20);
        int ry = (randomy.nextInt(1000) + 80);
        this.picture.setX(rx);
        this.picture.setY(ry);
    }
        public void useTrap() {
        Random randy = new Random();
        int rh = (randy.nextInt(4)+1);
        int rh2 = rh*10;
        if ((this.life + rh2)>=100){
            this.life = 100;
        } else{
            this.life +=rh2;
        } System.out.println(this.life);
    }

    public void hit() {
        this.life = this.life - 10;
    }
        public void resetHealth(){
            this.life =100;
        }
        public boolean isDead(){
        return this.life <= 0;
        }

}

class Badperson extends Rectangle {

    boolean isgoingUp, isgoingDown, isgoingLeft, isgoingRight;

    int x, y;

    Image Sith = new Image("file:pictures/darthvader.png");

    public ImageView photo = new ImageView(Sith);

    public Badperson(double x, double y) {
        super(x, y, 50, 50);
        this.setFill(Color.TRANSPARENT);
        System.out.println("Another thing");
        this.photo.setX(this.getX());
        this.photo.setY(this.getY());
        this.photo.setFitHeight(30);
        this.photo.setFitWidth(30);
    }

    void move() {
        if (this.isgoingUp) {
            this.moveUp();
        } else if (this.isgoingDown) {
            this.moveDown();
        } else if (this.isgoingLeft) {
            this.moveLeft();
        } else if (this.isgoingRight) {
            this.moveRight();
        }
        this.photo.setX(this.getX());
        this.photo.setY(this.getY());
    }

    private void moveUp() {
        if (this.getY() > 5) {
            this.setY(this.getY() - 1);
        } else {
            this.setDown();
        }
    }

    private void moveDown() {
        if (this.getY() < 600) {
            this.setY(this.getY() + 1);
        } else {
            this.setUp();
        }
    }

    private void moveLeft() {
        if (this.getX() > 5) {
            this.setX(this.getY() - 1);
        } else {
            this.setRight();
        }
    }

    private void moveRight() {
        if (this.getX() > 5) {
            this.setX(this.getY() + 1);
        } else {
            this.setLeft();
        }
    }

    void setUp() {
        this.isgoingUp = true;
        this.isgoingDown = false;
        this.isgoingLeft = false;
        this.isgoingRight = false;
    }

    void setDown() {
        this.isgoingUp = false;
        this.isgoingDown = true;
        this.isgoingLeft = false;
        this.isgoingRight = false;
    }

    void setRight() {
        this.isgoingUp = true;
        this.isgoingDown = false;
        this.isgoingLeft = false;
        this.isgoingRight = true;
    }

    void setLeft() {
        this.isgoingUp = false;
        this.isgoingDown = false;
        this.isgoingLeft = false;
        this.isgoingRight = true;
    }
}




//My trap is PreAp CS 2018-19
class Wall extends Rectangle {

    public Wall(double x, double y) {
        super(x, y, 20, 20);
        this.setFill(Color.DARKTURQUOISE);
    }

}

/* public Wall(double x, double y, double width, double height) {
        super(x, y, width, height);
        this.setFill(Color.DARKTURQUOISE);
    } */
class publicB {

    boolean begin;

    public publicB() {
        begin = false;
    }

    public boolean BEGIN() {
        return begin;
    }

    public void setBegin(boolean begin) {
        this.begin = begin;
    }

}

class Enemy extends Rectangle {

    int x, y, width, height, speed, move;
    Image stormtrooperpic1 = new Image("file:pictures/stormtroopers.png");
    Image stormtrooperpic2 = new Image("file:pictures/stormtroopers.png");
    Image stormtrooperpic3 = new Image("file:pictures/stormtroopers.png");
    Image stormtrooperpic4 = new Image("file:pictures/stormtroopers.png");
    public ImageView picture = new ImageView(stormtrooperpic1);

    public Enemy(double x, double y, double width, double height, int speed, String spos) {
        super(x, y, width, height);
        this.setFill(Color.TRANSPARENT);
        this.width = (int) width;
        this.height = (int) height;
        this.speed = speed;
        this.move = 10;
        this.picture.setX(x);
        this.picture.setY(y);
        this.picture.setFitWidth(width);
        this.picture.setFitHeight(height);
        if (spos == "up") {
            this.picture.setImage(stormtrooperpic1);
        } else if (spos == "down") {
            this.picture.setImage(stormtrooperpic2);
        } else if (spos == "right") {
            this.picture.setImage(stormtrooperpic3);
        } else if (spos == "left") {
            this.picture.setImage(stormtrooperpic4);
        }
    }

    public void changePicture(String changeimage) {
        if (changeimage == "down") {
            this.picture.setFitWidth(40);
            this.picture.setFitHeight(80);
            picture.setImage(stormtrooperpic2);
        } else if (changeimage == "left") {
            this.picture.setFitWidth(80);
            this.picture.setFitHeight(40);
            picture.setImage(stormtrooperpic3);
        } else if (changeimage == "right") {
            picture.setImage(stormtrooperpic1);
            this.picture.setFitWidth(80);
            this.picture.setFitHeight(40);
        } else if (changeimage == "up") {
            this.picture.setFitWidth(40);
            this.picture.setFitHeight(80);
            picture.setImage(stormtrooperpic4);
        }

    }

    public void chase(double x, double y) {
        if (this.picture.getX() + 60 < x) {
            this.move("right");
            this.changePicture("right");
        } else if (this.picture.getX() - 35 > x) {
            this.move("left");
            this.changePicture("left");
        } else if (this.picture.getY() + 75 < y) {
            this.move("down");
            this.changePicture("down");
        } else if (this.picture.getY() - 45 > y) {
            this.move("up");
            this.changePicture("up");
        }
    }

    public void move(String m) {
        if (m == "right") {
            this.picture.setX(this.picture.getX() + 15);
        } else if (m == "left") {
            this.picture.setX(this.picture.getX() - 15);
        } else if (m == "up") {
            this.picture.setY(this.picture.getY() - 15);
        } else if (m == "down") {
            this.picture.setY(this.picture.getY() + 15);  
        }
    }

    public boolean hitPlayer(int x, int y) {
        boolean hit = false;
        boolean xtwo = false;
        boolean ytwo = false;
        int xSearch = 0;
        int ySearch = 0;
        if ((this.picture.getImage() == stormtrooperpic3) || (this.picture.getImage() == stormtrooperpic1)) {
            xSearch = 40;
            ySearch = 60;
        } else if ((this.picture.getImage() == stormtrooperpic2) || (this.picture.getImage() == stormtrooperpic4)) {
            ySearch = 40;
            xSearch = 60;
        }

        for (int i = -5; i < xSearch+1; i++) {
            if (this.picture.getX() == x + i) {
                xtwo = true;
            }
        }

        for (int j = -5; j < ySearch+1; j++) {
            if (this.picture.getY() == y + j) {
                ytwo = true;
       
            }
        }
        if ((xtwo) && (ytwo)) {
            hit = true;
        }
        return hit;

    }
    public boolean doMove() {
        Random chase = new Random();
        int rc = (chase.nextInt(this.move));
        if (rc == 1) {
            return true;
        } else {
            return false;
        }

    }
        public boolean doHit(){
        Random hit = new Random();
        int rc = (hit.nextInt(7));
        if (rc == 1){
            return true;
        }else{
            return false;
        }
        }
    }
    }
