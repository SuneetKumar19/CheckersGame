package sample;
import com.sun.javafx.geom.AreaOp;
import com.sun.prism.shader.Solid_ImagePattern_AlphaTest_Loader;
import javafx.animation.*;
import javafx.scene.input.PickResult;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import com.sun.source.tree.Tree;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.util.Random;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.w3c.dom.*;
import javafx.scene.text.Text;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileWriter;
import java.lang.*;
import java.net.SocketTimeoutException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.*;
import javafx.scene.layout.GridPane;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;

import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;

import javafx.scene.shape.Box;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GamePlay {

    //Method to set up the initial screen
    public void SetUpInitialConfiguration(AnchorPane g, ArrayList<TypeCircle> black, ArrayList<TypeCircle> white) {
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                if ((i + j) % 2 == 1) {
                    if (j != 4 && j != 5) {
                        Circle c = new Circle();
                        c.setCenterX((i - 1) * 100 + 50);
                        c.setCenterY((j - 1) * 100 + 50);

                        if (j > 5) {
                            //for white team
                            c.setRadius(31);
                        } else {
                            c.setRadius(30);
                        }

                        if (j >= 6) {
                            c.setFill(Color.rgb(217, 217, 217));
                        } else {
                            c.setFill(Color.rgb(64, 64, 64));
                        }
                        if (j >= 6) {
                            TypeCircle cx = new TypeCircle(c, false);
                            white.add(cx);
                        } else {
                            TypeCircle cx = new TypeCircle(c, false);
                            black.add(cx);
                        }

                        g.getChildren().addAll(c);
                    }
                }
            }
        }
    }

    boolean isWhitePresent(ArrayList<TypeCircle> white, int x, int y) {
        for (int i = 0; i < white.size(); i++) {
            Circle c = white.get(i).circle;
            int x1 = (int) Math.ceil(c.getCenterX() / 100);
            int y1 = (int) Math.ceil(c.getCenterY() / 100);
            if (x1 == x && y1 == y) {
                return true;
            }
        }
        return false;
    }

    boolean isBlackPresent(ArrayList<TypeCircle> black, int x, int y) {
        for (int i = 0; i < black.size(); i++) {
            Circle c = black.get(i).circle;
            int x1 = (int) Math.ceil(c.getCenterX() / 100);
            int y1 = (int) Math.ceil(c.getCenterY() / 100);
            if (x1 == x && y1 == y) {
                return true;
            }
        }
        return false;
    }

    public void PlayGame(AnchorPane g, TypeRect[][] board, ArrayList<TypeCircle> BlackTeam, ArrayList<TypeCircle> WhiteTeam, int TurnVariable, boolean playAs) {
//                while(BlackTeam.size()!=0&&WhiteTeam.size()!=0)
//                {
        if (TurnVariable % 2 == 0) {
            blackMove(g, board, BlackTeam, WhiteTeam, TurnVariable, playAs);
        } else {
            blackMove(g, board, BlackTeam, WhiteTeam, TurnVariable, playAs);
        }
//                    TurnVariable++;
//                }
    }
    //set default Player 1

    public void blackMove(AnchorPane g, TypeRect[][] board, ArrayList<TypeCircle> black, ArrayList<TypeCircle> white, int turn, boolean playAs) {
        EventHandler<javafx.scene.input.MouseEvent> eventHandler =
                new EventHandler<javafx.scene.input.MouseEvent>() {
                    @Override
                    public void handle(javafx.scene.input.MouseEvent e) {
                        int xcrdn = (int) Math.ceil(e.getX() / 100);
                        int ycrdn = (int) Math.ceil(e.getY() / 100);
                        int idx = -1;
                        for (int i = 0; i < black.size(); i++) {
                            Circle c = black.get(i).circle;
                            int x = (int) Math.ceil(c.getCenterX() / 100);
                            int y = (int) Math.ceil(c.getCenterY() / 100);
                            if (x == xcrdn && y == ycrdn) {
                                idx = i;
                                break;
                            }
                        }
                        int tempIdx = idx;
                        if (idx != -1) {
                            System.out.println("Inside BLACK");
                            black.get(idx).circle.setFill(Color.ORANGE);
                            if (white.size() > 0) {
                                for (int i = 0; i < black.size(); i++) {
                                    black.get(i).circle.removeEventHandler(MouseEvent.MOUSE_CLICKED, this);
                                }
//                                 ===========================================================================
                                EventHandler<javafx.scene.input.MouseEvent> eventHandler2 =
                                        new EventHandler<javafx.scene.input.MouseEvent>() {
                                            @Override
                                            public void handle(javafx.scene.input.MouseEvent e) {
                                                int xx = (int) Math.ceil(e.getX() / 100);
                                                int yy = (int) Math.ceil(e.getY() / 100);
                                                boolean isvalid = false;
                                                int z1 = Math.abs(xcrdn - xx);
                                                int z2 = Math.abs(ycrdn - yy);
                                                if (black.get(tempIdx).isitking) {
                                                    isvalid = true;
                                                } else {
                                                    if ((yy - ycrdn) > 0) {
                                                        isvalid = true;
                                                    }
                                                }
//                                                 System.out.println("-->" + xx+"->"+yy+"->"+xcrdn+"-?"+ycrdn);

                                                if (isvalid) {
                                                    int nop = 0;
                                                    for (int i = 0; i < white.size(); i++) {
                                                        Circle c = white.get(i).circle;
                                                        int x1 = (int) Math.ceil(c.getCenterX() / 100);
                                                        int y1 = (int) Math.ceil(c.getCenterY() / 100);
                                                        if (x1 == xx && y1 == yy) {
                                                            nop = 1;
                                                            break;
                                                        }
                                                    }
                                                    for (int i = 0; i < black.size(); i++) {
                                                        Circle c = black.get(i).circle;
                                                        int x1 = (int) Math.ceil(c.getCenterX() / 100);
                                                        int y1 = (int) Math.ceil(c.getCenterY() / 100);
                                                        if (x1 == xx && y1 == yy) {
                                                            nop = 2;
                                                            break;
                                                        }
                                                    }
//                                                 System.out.println(z1+"---"+z2+"----"+nop);

                                                    if (z1 == 1 && z2 == 1 && nop == 0) {
                                                        int flag = 0;
                                                        for (int i = 0; i < black.size(); i++) {
                                                            Circle c = black.get(i).circle;
                                                            int x = (int) Math.ceil(c.getCenterX() / 100);
                                                            int y = (int) Math.ceil(c.getCenterY() / 100);
                                                            if (x == xcrdn && y == ycrdn) {
                                                                flag = 1;
                                                                black.get(i).circle.setFill(Color.rgb(64, 64, 64));
                                                                black.get(i).circle.setCenterX((xx - 1) * 100 + 50);
                                                                black.get(i).circle.setCenterY((yy - 1) * 100 + 50);
//                                                             xcrdn=10100101;
                                                                break;
                                                            }
                                                        }
                                                        if (flag == 1) {
                                                            for (int i = 1; i <= 8; i++) {
                                                                for (int j = 1; j <= 8; j++) {
                                                                    board[i][j].Rect.removeEventHandler(MouseEvent.MOUSE_CLICKED, this);
                                                                }
                                                            }
                                                            if (yy == 8) {
                                                                black.get(tempIdx).circle.setFill(Color.rgb(100, 100, 100));
                                                                black.get(tempIdx).isitking = true;
                                                            }
                                                            System.out.println("BlackMove 1");
                                                            whiteMove(g, board, black, white, turn, playAs);
                                                            return;
                                                        }
                                                    } else if (z1 == 2 && z2 == 2 && nop == 0) {
                                                        int flag1 = 0;
                                                        int xc = (xcrdn + xx) / 2;
                                                        int yc = (ycrdn + yy) / 2;
                                                        for (int i = 0; i < white.size(); i++) {
                                                            Circle c = white.get(i).circle;
                                                            int x = (int) Math.ceil(c.getCenterX() / 100);
                                                            int y = (int) Math.ceil(c.getCenterY() / 100);
                                                            if (x == xc && y == yc) {
                                                                flag1 = 1;
                                                                g.getChildren().remove(c);
//                                                             white.get(i).setFill(Color.GREEN);
                                                                white.remove(i);
                                                                break;
                                                            }
                                                        }
                                                        if (flag1 == 1) {
                                                            int flag = 0;
                                                            for (int i = 0; i < black.size(); i++) {
                                                                Circle c = black.get(i).circle;
                                                                int x = (int) Math.ceil(c.getCenterX() / 100);
                                                                int y = (int) Math.ceil(c.getCenterY() / 100);
                                                                if (x == xcrdn && y == ycrdn) {
                                                                    flag = 1;
                                                                    black.get(i).circle.setFill(Color.rgb(64, 64, 64));
                                                                    black.get(i).circle.setCenterX((xx - 1) * 100 + 50);
                                                                    black.get(i).circle.setCenterY((yy - 1) * 100 + 50);
                                                                    break;
                                                                }
                                                            }
                                                            if (flag == 1) {
                                                                for (int i = 1; i <= 8; i++) {
                                                                    for (int j = 1; j <= 8; j++) {
                                                                        board[i][j].Rect.removeEventHandler(MouseEvent.MOUSE_CLICKED, this);
                                                                    }
                                                                }
                                                                if (yy == 8) {
                                                                    black.get(tempIdx).circle.setFill(Color.rgb(100, 100, 100));
                                                                    black.get(tempIdx).isitking = true;
                                                                }
                                                                int newPosx = xx, newPosy = yy;
                                                                if (black.get(tempIdx).isitking) {
                                                                    int nx1 = xx + 1, ny1 = yy + 1;
                                                                    int nx2 = xx - 1, ny2 = yy - 1;
                                                                    int nx3 = xx + 1, ny3 = yy - 1;
                                                                    int nx4 = xx - 1, ny4 = yy + 1;
                                                                    if ((nx1 < 8 && ny1 < 8) && (isWhitePresent(white, nx1, ny1)) && (!(isWhitePresent(white, nx1 + 1, ny1 + 1) || isBlackPresent(black, nx1 + 1, ny1 + 1)))) {
                                                                        blackMove(g, board, black, white, turn, playAs);
                                                                    } else if ((nx2 > 1 && ny2 > 1) && (isWhitePresent(white, nx2, ny2)) && (!(isWhitePresent(white, nx2 - 1, ny2 - 1) || isBlackPresent(black, nx2 - 1, ny2 - 1)))) {
                                                                        blackMove(g, board, black, white, turn, playAs);
                                                                    } else if ((nx3 < 8 && ny3 > 1) && (isWhitePresent(white, nx3, ny3)) && (!(isWhitePresent(white, nx3 + 1, ny3 - 1) || isBlackPresent(black, nx3 + 1, ny3 - 1)))) {
                                                                        blackMove(g, board, black, white, turn, playAs);
                                                                    } else if ((nx4 > 1 && ny4 < 8) && (isWhitePresent(white, nx4, ny4)) && (!(isWhitePresent(white, nx4 - 1, ny4 + 1) || isBlackPresent(black, nx4 - 1, ny4 + 1)))) {
                                                                        blackMove(g, board, black, white, turn, playAs);
                                                                    } else {
                                                                        whiteMove(g, board, black, white, turn, playAs);
                                                                    }
                                                                } else {
                                                                    int nx1 = xx + 1, ny1 = yy + 1;
//                                                                 int nx2=xx-1,ny2=yy-1;
//                                                                 int nx3=xx+1,ny3=yy-1;
                                                                    int nx4 = xx - 1, ny4 = yy + 1;
                                                                    if ((nx1 < 8 && ny1 < 8) && (isWhitePresent(white, nx1, ny1)) && (!(isWhitePresent(white, nx1 + 1, ny1 + 1) || isBlackPresent(black, nx1 + 1, ny1 + 1)))) {
                                                                        blackMove(g, board, black, white, turn, playAs);
                                                                    } else if ((nx4 > 1 && ny4 < 8) && (isWhitePresent(white, nx4, ny4)) && (!(isWhitePresent(white, nx4 - 1, ny4 + 1) || isBlackPresent(black, nx4 - 1, ny4 + 1)))) {
                                                                        blackMove(g, board, black, white, turn, playAs);
                                                                    } else {
                                                                        whiteMove(g, board, black, white, turn, playAs);
                                                                    }

                                                                }


                                                                System.out.println("Blackmove2");
                                                                whiteMove(g, board, black, white, turn, playAs);
                                                                return;
                                                            }

                                                        }
                                                    }
                                                }

                                            }
                                        };

                                for (int i = 1; i <= 8; i++) {
                                    for (int j = 1; j <= 8; j++) {
                                        board[i][j].Rect.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler2);
                                    }
                                }

                            }
                        }
                    }
                };

        for (int i = 0; i < black.size(); i++) {
            black.get(i).circle.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
        }
    }

    //selected as Computer Player
    //playAs->true for computer
    //playAs->false for manualPlay
    public void whiteMove(AnchorPane g, TypeRect[][] board, ArrayList<TypeCircle> black, ArrayList<TypeCircle> white, int turn, boolean playAs) {
        if (!playAs) {
            EventHandler<javafx.scene.input.MouseEvent> eventHandler =
                    new EventHandler<javafx.scene.input.MouseEvent>() {
                        @Override
                        public void handle(javafx.scene.input.MouseEvent e) {

                            int xcrdn = (int) Math.ceil(e.getX() / 100);
                            int ycrdn = (int) Math.ceil(e.getY() / 100);
                            int idx = -1;
                            for (int i = 0; i < white.size(); i++) {
                                Circle c = white.get(i).circle;
                                int x = (int) Math.ceil(c.getCenterX() / 100);
                                int y = (int) Math.ceil(c.getCenterY() / 100);
                                if (x == xcrdn && y == ycrdn) {
                                    idx = i;
                                    break;
                                }
                            }
                            int tempid = idx;
                            if (idx != -1) {
                                System.out.println("Inside WHITE");
                                white.get(idx).circle.setFill(Color.BLUE);
                                if (black.size() > 0) {

                                    for (int i = 0; i < white.size(); i++) {
                                        white.get(i).circle.removeEventHandler(MouseEvent.MOUSE_CLICKED, this);
                                    }
//                                 ===========================================================================
                                    EventHandler<javafx.scene.input.MouseEvent> eventHandler2 =
                                            new EventHandler<javafx.scene.input.MouseEvent>() {
                                                @Override

                                                public void handle(javafx.scene.input.MouseEvent e) {
                                                    int xx = (int) Math.ceil(e.getX() / 100);
                                                    int yy = (int) Math.ceil(e.getY() / 100);
                                                    boolean isValid = false;
//                                                System.out.println("-->" + xx+"->"+yy+"->"+xcrdn+"-?"+ycrdn);
                                                    int z1 = Math.abs(xcrdn - xx);
                                                    int z2 = Math.abs(ycrdn - yy);
                                                    if (white.get(tempid).isitking) {
                                                        isValid = true;
                                                    } else {
                                                        if (ycrdn - yy > 0) {
                                                            isValid = true;
                                                        }
                                                    }
                                                    if (isValid) {


                                                        int nop = 0;
                                                        for (int i = 0; i < white.size(); i++) {
                                                            Circle c = white.get(i).circle;
                                                            int x1 = (int) Math.ceil(c.getCenterX() / 100);
                                                            int y1 = (int) Math.ceil(c.getCenterY() / 100);
                                                            if (x1 == xx && y1 == yy) {
                                                                nop = 1;
                                                                break;
                                                            }
                                                        }
                                                        for (int i = 0; i < black.size(); i++) {
                                                            Circle c = black.get(i).circle;
                                                            int x = (int) Math.ceil(c.getCenterX() / 100);
                                                            int y = (int) Math.ceil(c.getCenterY() / 100);
                                                            if (x == xx && y == yy) {
                                                                nop = 2;
                                                                break;
                                                            }
                                                        }
//                                                System.out.println(z1+"->>"+z2+"->"+nop);
                                                        if (nop == 0 && z1 == 1 && z2 == 1) {
                                                            int flag = 0;
                                                            for (int i = 0; i < white.size(); i++) {
                                                                Circle c = white.get(i).circle;
                                                                int x = (int) Math.ceil(c.getCenterX() / 100);
                                                                int y = (int) Math.ceil(c.getCenterY() / 100);
                                                                if (x == xcrdn && y == ycrdn) {
                                                                    flag = 1;
                                                                    white.get(i).circle.setFill(Color.rgb(217, 217, 217));
                                                                    white.get(i).circle.setCenterX((xx - 1) * 100 + 50);
                                                                    white.get(i).circle.setCenterY((yy - 1) * 100 + 50);
                                                                    break;
                                                                }
                                                            }
                                                            if (flag == 1) {
                                                                if (yy == 1) {
                                                                    white.get(tempid).circle.setFill(Color.rgb(191, 191, 191));
                                                                    white.get(tempid).isitking = true;
                                                                }
                                                                for (int i = 1; i <= 8; i++) {
                                                                    for (int j = 1; j <= 8; j++) {
                                                                        board[i][j].Rect.removeEventHandler(MouseEvent.MOUSE_CLICKED, this);
                                                                    }
                                                                }
                                                                System.out.println("WhiteMove1");
                                                                blackMove(g, board, black, white, turn, playAs);
                                                                return;
                                                            }
                                                        } else if (z1 == 2 && z2 == 2 && nop == 0) {
                                                            int flag1 = 0;
                                                            int xc = (xcrdn + xx) / 2;
                                                            int yc = (ycrdn + yy) / 2;
                                                            for (int i = 0; i < black.size(); i++) {
                                                                Circle c = black.get(i).circle;
                                                                int x = (int) Math.ceil(c.getCenterX() / 100);
                                                                int y = (int) Math.ceil(c.getCenterY() / 100);
                                                                if (x == xc && y == yc) {
                                                                    flag1 = 1;
                                                                    g.getChildren().remove(c);
                                                                    black.get(i).circle.setFill(Color.GREEN);
                                                                    black.remove(i);
                                                                    break;
                                                                }
                                                            }

                                                            //new Coordinates are xx and yy

                                                            if (flag1 == 1) {


                                                                int flag = 0;
                                                                for (int i = 0; i < white.size(); i++) {
                                                                    Circle c = white.get(i).circle;
                                                                    int x = (int) Math.ceil(c.getCenterX() / 100);
                                                                    int y = (int) Math.ceil(c.getCenterY() / 100);
                                                                    if (x == xcrdn && y == ycrdn) {
                                                                        flag = 1;
                                                                        white.get(i).circle.setFill(Color.rgb(217, 217, 217));
                                                                        white.get(i).circle.setCenterX((xx - 1) * 100 + 50);
                                                                        white.get(i).circle.setCenterY((yy - 1) * 100 + 50);
                                                                        break;
                                                                    }
                                                                }
                                                                if (flag == 1) {
                                                                    if (yy == 1) {
                                                                        white.get(tempid).circle.setFill(Color.rgb(191, 191, 191));
                                                                        white.get(tempid).isitking = true;
                                                                    }

                                                                    for (int i = 1; i <= 8; i++) {
                                                                        for (int j = 1; j <= 8; j++) {
                                                                            board[i][j].Rect.removeEventHandler(MouseEvent.MOUSE_CLICKED, this);
                                                                        }
                                                                    }
                                                                    System.out.println("whiteMove 2");
                                                                    if (white.get(tempid).isitking) {
                                                                        int nx1 = xx + 1, ny1 = yy + 1;
                                                                        int nx2 = xx - 1, ny2 = yy - 1;
                                                                        int nx3 = xx + 1, ny3 = yy - 1;
                                                                        int nx4 = xx - 1, ny4 = yy + 1;
                                                                        if ((nx1 < 8 && ny1 < 8) && (isWhitePresent(white, nx1, ny1)) && (!(isWhitePresent(white, nx1 + 1, ny1 + 1) || isBlackPresent(black, nx1 + 1, ny1 + 1)))) {
                                                                            whiteMove(g, board, black, white, turn, playAs);
                                                                        } else if ((nx2 > 1 && ny2 > 1) && (isWhitePresent(white, nx2, ny2)) && (!(isWhitePresent(white, nx2 - 1, ny2 - 1) || isBlackPresent(black, nx2 - 1, ny2 - 1)))) {
                                                                            whiteMove(g, board, black, white, turn, playAs);
                                                                        } else if ((nx3 < 8 && ny3 > 1) && (isWhitePresent(white, nx3, ny3)) && (!(isWhitePresent(white, nx3 + 1, ny3 - 1) || isBlackPresent(black, nx3 + 1, ny3 - 1)))) {
                                                                            whiteMove(g, board, black, white, turn, playAs);
                                                                        } else if ((nx4 > 1 && ny4 < 8) && (isWhitePresent(white, nx4, ny4)) && (!(isWhitePresent(white, nx4 - 1, ny4 + 1) || isBlackPresent(black, nx4 - 1, ny4 + 1)))) {
                                                                            whiteMove(g, board, black, white, turn, playAs);
                                                                        } else {
                                                                            blackMove(g, board, black, white, turn, playAs);
                                                                        }
                                                                    } else {
//                                                                int nx1=xx+1,ny1=yy+1;
                                                                        int nx2 = xx - 1, ny2 = yy - 1;
                                                                        int nx3 = xx + 1, ny3 = yy - 1;
//                                                                int nx4=xx-1,ny4=yy+1;
//                                                                if((nx1<8&&ny1<8)&&(isWhitePresent(white,nx1,ny1))&&(!(isWhitePresent(white,nx1+1,ny1+1)||isBlackPresent(black,nx1+1,ny1+1))))
//                                                                {
//                                                                    whiteMove(g,board,black,white,turn);
//                                                                }
                                                                        if ((nx2 > 1 && ny2 > 1) && (isWhitePresent(white, nx2, ny2)) && (!(isWhitePresent(white, nx2 - 1, ny2 - 1) || isBlackPresent(black, nx2 - 1, ny2 - 1)))) {
                                                                            whiteMove(g, board, black, white, turn, playAs);
                                                                        } else if ((nx3 < 8 && ny3 > 1) && (isWhitePresent(white, nx3, ny3)) && (!(isWhitePresent(white, nx3 + 1, ny3 - 1) || isBlackPresent(black, nx3 + 1, ny3 - 1)))) {
                                                                            whiteMove(g, board, black, white, turn, playAs);
                                                                        }
//                                                                else if((nx4>1&&ny4<8)&&(isWhitePresent(white,nx4,ny4))&&(!(isWhitePresent(white,nx4-1,ny4+1)||isBlackPresent(black,nx4-1,ny4+1))))
//                                                                {
//                                                                    whiteMove(g,board,black,white,turn);
//                                                                }
                                                                        else {
                                                                            blackMove(g, board, black, white, turn, playAs);
                                                                        }
                                                                    }
                                                                    return;
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            };

                                    for (int i = 1; i <= 8; i++) {
                                        for (int j = 1; j <= 8; j++) {
                                            board[i][j].Rect.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler2);
                                        }
                                    }

                                }
                            }
                        }
                    };

            for (int i = 0; i < white.size(); i++) {
                white.get(i).circle.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
            }
        } else {

            //for a computer player
            for (int i = 0; i < white.size(); i++) {
                Circle c = white.get(i).circle;
                int xx = (int) Math.ceil(c.getCenterX() / 100);
                int yy = (int) Math.ceil(c.getCenterY() / 100);
                int nx1 = xx + 1, ny1 = yy + 1;
                int nx2 = xx - 1, ny2 = yy - 1;
                int nx3 = xx + 1, ny3 = yy - 1;
                int nx4 = xx - 1, ny4 = yy + 1;
                if (nx2 > 1 && ny2 > 1 && (isBlackPresent(black, nx2, ny2)) && (!(isWhitePresent(white, nx2 - 1, ny2 - 1) || isBlackPresent(black, nx2 - 1, ny2 - 1)))) {
                    if (ny2 == 2) {
                        white.get(i).isitking = true;
                    }
                    //deleting black at nx2 ny2
                    for (int j = 0; j < black.size(); j++) {
                        Circle cx = black.get(j).circle;
                        int xx1 = (int) Math.ceil(cx.getCenterX() / 100);
                        int yy1 = (int) Math.ceil(cx.getCenterY() / 100);
                        if (xx1 == nx2 && yy1 == ny2) {
                            black.get(j).circle.setFill(Color.BLACK);
                            g.getChildren().remove(cx);
                            black.remove(j);
                            break;
                        }
                    }

                    white.get(i).circle.setCenterX((nx2 - 2) * 100 + 50);
                    white.get(i).circle.setCenterY((ny2 - 2) * 100 + 50);
                    blackMove(g, board, black, white, turn, playAs);
                    break;
                }
                if (nx3 < 1 && ny3 > 1 && (isBlackPresent(black, nx3, ny3)) && (!(isWhitePresent(white, nx3 + 1, ny3 - 1) || isBlackPresent(black, nx3 + 1, ny3 - 1)))) {
                    if (ny3 == 2) {
                        white.get(i).isitking = true;
                    }
                    //deleting black at nx3 ny3
                    for (int j = 0; j < black.size(); j++) {
                        Circle cx = black.get(j).circle;
                        int xx1 = (int) Math.ceil(cx.getCenterX() / 100);
                        int yy1 = (int) Math.ceil(cx.getCenterY() / 100);
                        if (xx1 == nx3 && yy1 == ny3) {
                            black.get(i).circle.setFill(Color.BLACK);
                            g.getChildren().remove(cx);
                            black.remove(j);
                            break;
                        }
                    }

                    white.get(i).circle.setCenterX((nx3) * 100 + 50);
                    white.get(i).circle.setCenterY((ny3 - 2) * 100 + 50);
                    blackMove(g, board, black, white, turn, playAs);
                    break;
                }
                if ((white.get(i).isitking) && nx1 <= 8 && ny1 <= 8 && (!(isWhitePresent(white, nx1, ny1) || isBlackPresent(black, nx1, ny1)))) {
                    if (ny1 == 1) {
                        white.get(i).isitking = true;
                    }
                    white.get(i).circle.setCenterX((nx1 - 1) * 100 + 50);
                    white.get(i).circle.setCenterY((ny1 - 1) * 100 + 50);
                    blackMove(g, board, black, white, turn, playAs);
                    break;
                }
                if (nx2 >= 1 && ny2 >= 1 && (!(isWhitePresent(white, nx2, ny2) || isBlackPresent(black, nx2, ny2)))) {
                    if (ny2 == 1) {
                        white.get(i).isitking = true;
                    }
                    white.get(i).circle.setCenterX((nx2 - 1) * 100 + 50);
                    white.get(i).circle.setCenterY((ny2 - 1) * 100 + 50);
                    blackMove(g, board, black, white, turn, playAs);
                    break;
                }
                if (nx3 <= 8 && ny3 >= 1 && (!(isWhitePresent(white, nx3, ny3) || isBlackPresent(black, nx3, ny3)))) {
                    if (ny3 == 1) {
                        white.get(i).isitking = true;
                    }
                    white.get(i).circle.setCenterX((nx3 - 1) * 100 + 50);
                    white.get(i).circle.setCenterY((ny3 - 1) * 100 + 50);
                    blackMove(g, board, black, white, turn, playAs);
                    break;
                }


            }
        }
    }
}


