package sample;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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
public class Main extends Application {
    public int touchX=-1,touchY=-1;
    public void setTouchX(int x)
    {
        touchX=x;
    }
    public void setTouchY(int y)
    {
        touchY=y;
    }
    @Override
    public void start(Stage primaryStage) throws Exception{

        int TurnVariable=0;
        int [][]board=new int[9][9];
        //white -> 0
        //black -> 1
        //Setting up the basic board

        for(int row=1;row<=8;row++)
        {
            for(int col=1;col<=8;col++)
            {
                board[row][col]=-1;
            }
        }
        for(int i=1;i<=8;i++)
        {
            for(int j=1;j<=8;j++)
            {
                if((i+j)%2==1)
                {
                    if(j<=3)
                    {
                        board[i][j]=1;
                    }
                    else if(j>5)
                    {
                        board[i][j]=0;
                    }
                }
            }
        }
        for(int i=1;i<=8;i++)
        {
            for(int j=1;j<=8;j++)
            {
                System.out.print(board[i][j]+" ");

            }
            System.out.print("\n");
        }
        TypeRect [][]rectangle=new TypeRect[9][9];
        AnchorPane g=new AnchorPane();
        g.setStyle(" -fx-background-color: #FFFFFF;");
        Scene scene=new Scene(g,800,800);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setTitle("CheckersGame");
        g.onTouchMovedProperty();

        //===========================This part sets up the initial board Game==========================
        for(int i=1;i<=8;i++) {
            for (int j = 1; j <= 8; j++) {
                if ((i + j) % 2 == 1) {
                    Rectangle rect = new Rectangle();
//                    board[i][j]=1;
                    rect.setX((i - 1) * 100);
                    rect.setY((j - 1) * 100);
                    rect.setHeight(100);
                    rect.setWidth(100);
                    rect.setFill(Color.BLACK);
                    g.getChildren().addAll(rect);
                    rectangle[i][j]=new TypeRect(rect,false);
//                    rectangle[i][j].isKing=false;

                } else {
//                    board[i][j]=0;
                    Rectangle rect = new Rectangle();
                    rect.setX((i - 1) * 100);
                    rect.setY((j - 1) * 100);
                    rect.setHeight(100);
                    rect.setWidth(100);
                    rect.setFill(Color.WHITE);
                    g.getChildren().addAll(rect);
                    rectangle[i][j]=new TypeRect(rect,false);
//                    rectangle[i][j].isKing=false;
                }

            }
        }

        //Open for AI  ==============================
        AIClasses cpuVScpu=new AIClasses();
        cpuVScpu.MinMax(2,0,board);
        cpuVScpu.setUp(g,board);
        cpuVScpu.SETUP(board);
        cpuVScpu.ShowSimulation(g);




//        ArrayList<TypeCircle>BlackTeam=new ArrayList<TypeCircle>();
//        ArrayList<TypeCircle>WhiteTeam=new ArrayList<TypeCircle>();
//        GamePlay game=new GamePlay();
//        game.SetUpInitialConfiguration(g,BlackTeam,WhiteTeam);
//        game.PlayGame(g,rectangle,BlackTeam,WhiteTeam,0,false);
        //==============================================================================================
    }

    public static void main(String[] args) {
        launch(args);
    }
}
