/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mariotutoiral;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;

/**
 *
 * @author Tewedaj
 */
public class Avater {
     private int size =1;// 1 normal 2 medium 3 large
     private int avater_x = 10;
     private int avater_y = 361;
     private int road_x;
     private String walking[]={"human1.png","human2.png","human3.png","human4.png","human5.png","human6.png"};
     public String walkingNow = walking[0];
     private Image img;
    
     public Avater(int road_x){
         
         
         this.road_x = road_x;
     }
     public void drawAvater(Graphics g,ImageObserver observ){
         ImageIcon human=new ImageIcon("files/"+walkingNow);
         img =human.getImage();
     
         g.drawImage(img,avater_x,avater_y,30,60,observ);
     }
     public int getAvatery(){
         return avater_y;
     }
     public int getAvaterX(){
         return avater_x;
     }
     public void setAvaterX(int x){
         this.avater_x = x;
     }
     public void setAvaterY(int y){
         this.avater_y = y;
     }
     public int moveRight(){
         if(walkingNow == walking[0]){
             
               walkingNow = walking[1];
         }else if(walkingNow == walking[1]){
              walkingNow = walking[0];
         }else if(walkingNow == walking[2]){
             walkingNow = walking[0];
         }else if(walkingNow == walking[3]){
             walkingNow = walking[0];
         }else if(walkingNow == walking[4]){
             walkingNow = walking[0];
         }else if(walkingNow == walking[5]){
             walkingNow = walking[0];
         }
         
         if(avater_x <200){
         avater_x+=5;
         }else{
             road_x-=10;
            return road_x;
         }
         return road_x;
     }
     public void moveBack(){
         if(walkingNow == walking[0] || walkingNow == walking[1] || walkingNow == walking[4] || walkingNow == walking[5]){
             walkingNow = walking[2];
         }else if(walkingNow == walking[2]){
             walkingNow = walking[3];
         }else if(walkingNow == walking[3]){
             walkingNow = walking[2];
         }
         
         if(avater_x > 0){
             avater_x-=5;
         }
     }
     public void gravity(){
            if(avater_y<361){
             avater_y+=20;
     }
     }
     public void Jump(){
         avater_y-=25;   
     }
}
