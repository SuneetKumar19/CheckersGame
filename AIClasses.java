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
//mport com.sun.javafx.geom.AreaOp;
//import com.sun.prism.shader.Solid_ImagePattern_AlphaTest_Loader;
import javafx.animation.*;
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
import java.util.ArrayList;

public class AIClasses {
    public int [][] copyArray(int [][]table)
    {
        int [][] board=new int [9][9];
        for(int i=1;i<=8;i++)
        {
            for(int j=1;j<=8;j++)
            {
                board[i][j]=table[i][j];
            }
        }
        return board;
//        for
    }
    public int heurestic(int [][]table)
    {
        //black -white
        int cb=0,cw=0,sum=0;
        for(int i=1;i<=8;i++)
        {
            for(int j=1;j<=8;j++)
            {
                if(table[i][j]==1)
                {
                    sum+=(j);
                    cb+=3;
                }
                else if(table[i][j]==3)
                {
                    cb+=3;
                }
                else if(table[i][j]==0)
                {
                    sum-=(9-j);
                    cw+=3;
                }
                else if(table[i][j]==2)
                {
                    cw+=3;
                }
            }
        }
        return cb-cw+sum;
    }
    public ArrayList<int [][]>get_child(int [][]tablex,int turn)
    {
//        System.out.println("Valler\n");
        ArrayList<int [][]> children=new ArrayList<int [][]>();
        int [][]table=copyArray(tablex);
        if(turn%2==0)//black turn--> represent black as 1 white as 0 and empty as -1
        {
            for(int i=1;i<=8;i++)
            {
                for(int j=1;j<=8;j++)
                {
                    if(table[i][j]==1)
                    {
                        if(i+1<=8&&j+1<=8)
                        {
                            if(table[i+1][j+1]==-1)
                            {
                                table[i][j]=-1;
                                table[i+1][j+1]=1;
                                children.add(copyArray(table));
                                table[i+1][j+1]=-1;
                                table[i][j]=1;
                            }
                            if(table[i+1][j+1]==0&&(i+2<=8&&j+2<=8)&&(table[i+2][j+2]==-1))
                            {
                                table[i][j]=-1;
                                table[i+1][j+1]=-1;
                                table[i+2][j+2]=1;
                                children.add(copyArray(table));
                                table[i][j]=1;
                                table[i+1][j+1]=0;
                                table[i+2][j+2]=-1;
                            }
                        }
                        if(i-1>=1&&j+1<=8)
                        {
                            if(table[i-1][j+1]==-1)
                            {
                                table[i][j]=-1;
                                table[i-1][j+1]=1;
                                children.add(copyArray(table));
                                table[i-1][j+1]=-1;
                                table[i][j]=1;
                            }
                            if(table[i-1][j+1]==0&&(i-2>=1&&j+2<=8)&&(table[i-2][j+2]==-1))
                            {
                                table[i][j]=-1;
                                table[i-1][j+1]=-1;
                                table[i-2][j+2]=1;
                                children.add(copyArray(table));
                                table[i][j]=1;
                                table[i-1][j+1]=0;
                                table[i-2][j+2]=-1;
                            }
                        }
                    }

                    if(table[i][j]==3)
                    {
                        //King conditions
                        if(i+1<=8&&j+1<=8)
                        {
                            if(table[i+1][j+1]==-1)
                            {
                                table[i][j]=-1;
                                table[i+1][j+1]=3;
                                children.add(copyArray(table));
                                table[i+1][j+1]=-1;
                                table[i][j]=3;
                            }
                            if((table[i+1][j+1]==0||table[i+1][j+1]==2)&&(i+2<=8&&j+2<=8)&&(table[i+2][j+2]==-1))
                            {
                                int x=table[i+1][j+1];
                                table[i][j]=-1;
                                table[i+1][j+1]=-1;
                                table[i+2][j+2]=3;
                                children.add(copyArray(table));
                                table[i][j]=3;
                                table[i+1][j+1]=x;
                                table[i+2][j+2]=-1;
                            }
                        }
                        if(i-1>=1&&j+1<=8)
                        {
                            if(table[i-1][j+1]==-1)
                            {
                                table[i][j]=-1;
                                table[i-1][j+1]=3;
                                children.add(copyArray(table));
                                table[i-1][j+1]=-1;
                                table[i][j]=3;
                            }
                            if((table[i-1][j+1]==0||table[i-1][j+1]==2)&&(i-2>=1&&j+2<=8)&&(table[i-2][j+2]==-1))
                            {
                                int x=table[i-1][j+1];
                                table[i][j]=-1;
                                table[i-1][j+1]=-1;
                                table[i-2][j+2]=3;
                                children.add(copyArray(table));
                                table[i][j]=3;
                                table[i-1][j+1]=x;
                                table[i-2][j+2]=-1;
                            }
                        }
                        //King Conditions
                        if(i+1<=8&&j-1>=1)
                        {
                            if(table[i+1][j-1]==-1)
                            {
                                table[i][j]=-1;
                                table[i+1][j-1]=3;
                                children.add(copyArray(table));
                                table[i+1][j-1]=-1;
                                table[i][j]=3;
                            }
                            if((table[i+1][j-1]==0||table[i+1][j-1]==2)&&(i+2<=8&&j-2>=1)&&(table[i+2][j-2]==-1))
                            {
                                int x=table[i+1][j-1];
                                table[i][j]=-1;
                                table[i+1][j-1]=-1;
                                table[i+2][j-2]=3;
                                children.add(copyArray(table));
                                table[i][j]=3;
                                table[i+1][j-1]=x;
                                table[i+2][j-2]=-1;
                            }
                        }
                        if(i-1>=1&&j-1>=1)
                        {
                            if(table[i-1][j-1]==-1)
                            {
                                table[i][j]=-1;
                                table[i-1][j-1]=3;
                                children.add(copyArray(table));
                                table[i-1][j-1]=-1;
                                table[i][j]=3;
                            }
                            if((table[i-1][j-1]==0||table[i-1][j-1]==2)&&(i-2>=1&&j-2>=1)&&(table[i-2][j-2]==-1))
                            {
                                int x=table[i-1][j-1];
                                table[i][j]=-1;
                                table[i-1][j-1]=-1;
                                table[i-2][j-2]=3;
                                children.add(copyArray(table));
                                table[i][j]=3;
                                table[i-1][j-1]=x;
                                table[i-2][j-2]=-1;
                            }
                        }

                    }
                }
            }
        }
        else
        {
            for(int i=1;i<=8;i++)
            {
                for(int j=1;j<=8;j++)
                {
                    if(table[i][j]==0)
                    {
                        if(i+1<=8&&j-1>=1)
                        {
                            if(table[i+1][j-1]==-1)
                            {
                                table[i][j]=-1;
                                table[i+1][j-1]=0;
                                children.add(copyArray(table));
                                table[i+1][j-1]=-1;
                                table[i][j]=0;
                            }
                            if(table[i+1][j-1]==1&&(i+2<=8&&j-2>=1)&&(table[i+2][j-2]==-1))
                            {
                                table[i][j]=-1;
                                table[i+1][j-1]=-1;
                                table[i+2][j-2]=0;
                                children.add(copyArray(table));
                                table[i][j]=0;
                                table[i+1][j-1]=1;
                                table[i+2][j-2]=-1;
                            }
                        }
                        if(i-1>=1&&j-1>=1)
                        {
                            if(table[i-1][j-1]==-1)
                            {
                                table[i][j]=-1;
                                table[i-1][j-1]=0;
                                children.add(copyArray(table));
                                table[i-1][j-1]=-1;
                                table[i][j]=0;
                            }
                            if(table[i-1][j-1]==1&&(i-2>=1&&j-2>=1)&&(table[i-2][j-2]==-1))
                            {
                                table[i][j]=-1;
                                table[i-1][j-1]=-1;
                                table[i-2][j-2]=0;
                                children.add(copyArray(table));
                                table[i][j]=0;
                                table[i-1][j-1]=1;
                                table[i-2][j-2]=-1;
                            }
                        }
                    }
                    //for white king
                    if(table[i][j]==2)
                    {
                        //King conditions
                        if(i+1<=8&&j+1<=8)
                        {
                            if(table[i+1][j+1]==-1)
                            {
                                table[i][j]=-1;
                                table[i+1][j+1]=2;
                                children.add(copyArray(table));
                                table[i+1][j+1]=-1;
                                table[i][j]=2;
                            }
                            if((table[i+1][j+1]==1||table[i+1][j+1]==3)&&(i+2<=8&&j+2<=8)&&(table[i+2][j+2]==-1))
                            {
                                int x=table[i+1][j+1];
                                table[i][j]=-1;
                                table[i+1][j+1]=-1;
                                table[i+2][j+2]=2;
                                children.add(copyArray(table));
                                table[i][j]=2;
                                table[i+1][j+1]=x;
                                table[i+2][j+2]=-1;
                            }
                        }
                        //////////
                        if(i-1>=1&&j+1<=8)
                        {
                            if(table[i-1][j+1]==-1)
                            {
                                table[i][j]=-1;
                                table[i-1][j+1]=2;
                                children.add(copyArray(table));
                                table[i-1][j+1]=-1;
                                table[i][j]=2;
                            }
                            if((table[i-1][j+1]==1||table[i-1][j+1]==3)&&(i-2>=1&&j+2<=8)&&(table[i-2][j+2]==-1))
                            {
                                int x=table[i-1][j+1];
                                table[i][j]=-1;
                                table[i-1][j+1]=-1;
                                table[i-2][j+2]=2;
                                children.add(copyArray(table));
                                table[i][j]=2;
                                table[i-1][j+1]=x;
                                table[i-2][j+2]=-1;
                            }
                        }
                        //King Conditions
                        if(i+1<=8&&j-1>=1)
                        {
                            if(table[i+1][j-1]==-1)
                            {
                                table[i][j]=-1;
                                table[i+1][j-1]=2;
                                children.add(copyArray(table));
                                table[i+1][j-1]=-1;
                                table[i][j]=2;
                            }
                            if((table[i+1][j-1]==1||table[i+1][j-1]==3)&&(i+2<=8&&j-2>=1)&&(table[i+2][j-2]==-1))
                            {
                                int x=table[i+1][j-1];
                                table[i][j]=-1;
                                table[i+1][j-1]=-1;
                                table[i+2][j-2]=2;
                                children.add(copyArray(table));
                                table[i][j]=2;
                                table[i+1][j-1]=x;
                                table[i+2][j-2]=-1;
                            }
                        }
                        if(i-1>=1&&j-1>=1)
                        {
                            if(table[i-1][j-1]==-1)
                            {
                                table[i][j]=-1;
                                table[i-1][j-1]=2;
                                children.add(copyArray(table));
                                table[i-1][j-1]=-1;
                                table[i][j]=2;
                            }
                            if((table[i-1][j-1]==1||table[i-1][j-1]==3)&&(i-2>=1&&j-2>=1)&&(table[i-2][j-2]==-1))
                            {
                                int x=table[i-1][j-1];
                                table[i][j]=-1;
                                table[i-1][j-1]=-1;
                                table[i-2][j-2]=2;
                                children.add(copyArray(table));
                                table[i][j]=2;
                                table[i-1][j-1]=x;
                                table[i-2][j-2]=-1;
                            }
                        }
                    }
                }
            }
        }
        return children;
    }

    public int MinMax(int depth,int turn,int [][] table)
    {
//        System.out.println("Called\n");
        ArrayList<int [][]>child=get_child(table,turn);
//        cout<<"->"<
        int fans;
//        System.out.println("->"+child.size());

        if(turn%2==0)
        {
            fans=-64;
        }
        else
        {
            fans=64;
        }
        if(child.size()==0)
        {
            if(turn%2==0)
            {
                return 64;
            }
            else
            {
                return -64;
            }
        }
        if(depth==0)
        {
            //call the heurestic function here
            return heurestic(table);
        }
        if(turn%2==0)//maximizer
        {
            for(int i=0;i<child.size();i++)
            {
                int temp=MinMax(depth-1,turn+1,child.get(i));
                if(fans<=temp)
                {
                    fans=temp;
                }
            }
//            System.out.println("-->"+fans);
            return fans;
        }
        else//minimizer
        {
            for (int i = 0; i < child.size(); i++) {
                int temp = MinMax(depth - 1, turn + 1, child.get(i));
                if (fans >= temp) {
                    fans = temp;
                }
//                fans=Math.min(fans,);
            }
//            System.out.println("-->" + fans);
            return fans;
        }
    }

    public void setUp(AnchorPane g,int [][] table)
    {
        g.getChildren().removeAll();
        for(int i=1;i<=8;i++)
        {
            for(int j=1;j<=8;j++)
            {
                if((i+j)%2==1)
                {
                    Rectangle rect = new Rectangle();
                    rect.setX((i - 1) * 100);
                    rect.setY((j - 1) * 100);
                    rect.setHeight(100);
                    rect.setWidth(100);
                    rect.setFill(Color.BLACK);
                    g.getChildren().addAll(rect);
                }
            }
        }
        for(int i=1;i<=8;i++)
        {
            for(int j=1;j<=8;j++)
            {
                if((i+j)%2==1) {
                    if (table[i][j] == 0) {
                        Circle c = new Circle();
                        c.setCenterX((i - 1) * 100 + 50);
                        c.setCenterY((j - 1) * 100 + 50);
                        c.setRadius(30);
                        c.setFill(Color.rgb(217, 217, 217));
                        g.getChildren().addAll(c);
                    } else if (table[i][j] == 1) {
                        Circle c = new Circle();
                        c.setCenterX((i - 1) * 100 + 50);
                        c.setCenterY((j - 1) * 100 + 50);
                        c.setRadius(30);
                        c.setFill(Color.rgb(64, 64, 64));
                        g.getChildren().addAll(c);
                    } else if (table[i][j] == 3) {
                        Circle c = new Circle();
                        c.setCenterX((i - 1) * 100 + 50);
                        c.setCenterY((j - 1) * 100 + 50);
                        c.setRadius(30);
                        c.setFill(Color.rgb(100, 100, 100));
                        g.getChildren().addAll(c);

                    } else if (table[i][j] == 2) {
                        Circle c = new Circle();
                        c.setCenterX((i - 1) * 100 + 50);
                        c.setCenterY((j - 1) * 100 + 50);
                        c.setRadius(30);
                        c.setFill(Color.rgb(250, 250, 250));
                        g.getChildren().addAll(c);

                    }

                }
            }
        }
    }
    int [][] table= new int[9][9];
    int turn;
    public  void SETUP(int [][]initial)
    {
        turn=0;
        table=initial;
    }
    //white King ->2
    // Black King ->3

    public void ShowSimulation(AnchorPane g) {
        Timeline tl = new Timeline(new KeyFrame(Duration.millis(700),
                event -> {
//                    table=new int[9][9];
                    int[][] nextMove = new int[9][9];
//                    table=tablex;
//                    table=nextMove;
                    int cw=0,cb=0;
                    for(int i=1;i<=8;i++)
                    {
                        for(int j=1;j<=8;j++)
                        {
                            if(table[i][j]==0||table[i][j]==2)
                            {
                                cw++;
                            }
                            else if(table[i][j]==1||table[i][j]==3)
                            {
                                cb++;
                            }
                        }
                    }
                    for(int i=1;i<=8;i++)
                    {
                        for(int j=1;j<=8;j++)
                        {
                            if(table[i][j]==1&&j==8)
                            {
                                table[i][j]=3;
                            }
                            else if(table[i][j]==0&&j==1)
                            {
                                table[i][j]=2;
                            }
                        }
                    }
                    if(cw>0&&cb>0) {
//                    tl.pause();
                        ArrayList<int[][]> child = get_child(table, turn);

                        int val;
                        if (turn % 2 == 0) {
                            val = -64;
                            for (int i = 0; i < child.size(); i++) {
                                int k = MinMax(4, turn + 1, child.get(i));
                                if (k > val) {
                                    val = k;
                                    nextMove = child.get(i);
                                }
                            }
                        } else {
                            val = 64;
                            for (int i = 0; i < child.size(); i++) {
                                int k = MinMax(1, turn + 1, child.get(i));
                                if (k < val) {
                                    val = k;
                                    nextMove = child.get(i);
                                }
                            }
                        }
                        turn++;
                        setUp(g, nextMove);
                        int colorw=0,colorb=0;
                        for(int i=1;i<=8;i++)
                        {
                            for(int j=1;j<=8;j++)
                            {
                                if(nextMove[i][j]==0||nextMove[i][j]==2)
                                {
                                    colorw++;
                                }
                                else if(nextMove[i][j]==1||nextMove[i][j]==3)
                                {
                                    colorb++;
                                }
                            }
                        }
                        if(colorw>=0&&colorb>=0&&colorb<=12&&colorw<=12) {
//                                System.out.println(colorb+"->"+colorw);
                            table = nextMove;
//                            System.out.print("-----------------------------------\n");
//                            for(int i=1;i<=8;i++)
//                            {
//                                for(int j=1;j<=8;j++)
//                                {
//                                    System.out.print(table[i][j]+" ");
//                                }
//                                System.out.print("\n");
//                            }
                        }
                        else
                        {
//                            System.out.println(colorb+"->"+colorw);
//                            for(int i=1;i<=8;i++)
//                            {
//                                for(int j=1;j<=8;j++)
//                                {
//                                    System.out.print(table[i][j]+" ");
//                                }
//                                System.out.print("\n");
//                            }
//                            if(col)

//                            break;

                        }
                    }
                    else
                    {
                        for(int i=1;i<=8;i++)
                        {
                            for(int j=1;j<=8;j++)
                            {
                                System.out.print(table[i][j]+" ");
                            }
                            System.out.print("\n");
                        }
                    }
                    setUp(g, table);
                }));
        tl.setCycleCount(Timeline.INDEFINITE);
        tl.play();
        int cw=0,cb=0;
        for(int i=1;i<=8;i++)
        {
            for(int j=1;j<=8;j++)
            {
                if(table[i][j]==0||table[i][j]==2)
                {
                    cw++;
                }
                else if(table[i][j]==1||table[i][j]==3)
                {
                    cb++;
                }
            }
        }
        if(cw==0||cb==0)
        {
            tl.pause();
        }
    }
}
