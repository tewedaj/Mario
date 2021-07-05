/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mariotutoiral;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import javax.swing.ImageIcon;

/**
 *
 * @author Tewedaj
 */
public class Enemy {
    private int enemy_x;
    private int enemy_y;
    private String ImgState;
    private Image enemy_look;
    private int img_height;
    private String ImgN;
    private int road_x;
    private boolean avalableDir[] = new boolean[2]; // this desieds the direction 
    public Enemy(int road_x,int x,int y,int img_height,String enemy_type,boolean right,boolean left){
        this.avalableDir[0] = right;
        this.avalableDir[1] = left;
        this.enemy_x = x;
        this.enemy_y = y;
        this.road_x  = road_x;
        this.img_height = img_height;
        if(enemy_type.equals("eli")){
           ImgN="files/enemy_1.png"; 
        }else if(enemy_type.equals("mashrume")){
            
        }
        
    }
       public void drawEnemy(Graphics g,ImageObserver imgo){
        ImageIcon ene=new ImageIcon("files/enemy_1.png");
        enemy_look = ene.getImage();
      
        g.drawImage(enemy_look,road_x+enemy_x,enemy_y,50,50,imgo);
    }
    public void setEnemy_x(int x){
        this.enemy_x = x;
    }
    public int getEnemy_x(){
        return this.enemy_x;
    }
    public void setEnemy_y(int y){
        this.enemy_y = y;
    }
    public int getEnemy_y(){
        return this.enemy_y;
    }
    public void setImgHeight(int h){
        this.img_height = h;
    }
    public int getImgHeight(){
        return this.img_height;
    }
    public void updateRoad_x(int newRoad_x){
        this.road_x = newRoad_x;
    }
    public void move(){
        if(avalableDir[0]){
        this.setEnemy_x(this.getEnemy_x()-10);
            this.avalableDir[1] = false;
        }else if(avalableDir[1]){
            this.setEnemy_x(this.getEnemy_x()+10);
        }
     }
    
}
