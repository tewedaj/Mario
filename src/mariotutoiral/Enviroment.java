package mariotutoiral;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
/*AudioInputStream audio=AudioSYstem.getAudioInputStream(new File("store/record/his.wav").getAbsoluteFile());
Clip clip=AudioSYstem.getClip();
clip.open(audio);
clip.start();
*/
public class Enviroment extends JPanel implements ActionListener,KeyListener{
    private int road_x=0; // this controlles the illusion of mario walking by moving the background in the oposit direction
    private boolean game; //checks if the game started
    private Timer time; // controles time 
    private int length=15; //the length of the background image
    private int delay=100; // the amount of time the timer class uses's
    private Image sky; // the image file that is for the background image
    private Avater mario; // the class i created for the avater mario..
    private boolean falling = false; // tells if the avater is falling
    private boolean jump = false; // tells if the avater is jumping
    private boolean direction[]={true,false}; //right , left | it tells direction 0 means right and 1 means left both can't be true at the same time
    private boolean ablity[] = {true,true}; // if there is an obstacle from left or right of the avater the ablity will be false| 0 for right 1 for left
    private boolean pipes[]={true,false,false,false,false,false,false};//if the pipe is out of the screen it will be turned of so that it won't affect the booleans
    private Image pipd;//image for the pip
    private Image boxI;//image for the box
    private Image priceBox[]=new Image[1000]; //an array for price box 
    private int object[]={0,0};//creates a filed on the air to stand on if there is an object detected
    private int box[] = {0,0};//creates a fild or a limit on the air to stand on if there is a box
    private boolean boxAblity[]={true,true};// it controlls ablity to move right or left depending on if there is a box or not
    private int box1[] = {0,0};
    private boolean box1Ablity[]={true,true};
    private int jumpStart;
    private int pipes_count=0;
    private String animatePriceBox[]={"files/priceBox.png","files/priceBox2.png"};
    private String priceBoxImage=animatePriceBox[0];
    private int Ypositions[]={320,320,320,320,320,320,200,320};
    private int CoinPositions[]={1,1,1,1,1,1,1,1};
    private int coinX,coinY;//this will be asinend when the mistry box collides with the avater
    private boolean coinThrow;
    private int Yposhead[]=new int[1000];
    private int YpositionNow[]=new int[1000];
    private int jumpstart=360;
    private String unknownBox[] =new String[1000];
    private ImageIcon emptyPriceb;
    private ImageIcon priceBoxI[]=new ImageIcon[1000];    
    private String Coin[]={"files/coin1.png","files/coin2.png"};
    private String CoinNow=Coin[0];
    private int CoinCollacted=0;
    private int marioScore=0;
    private int timeR=400;
    private int timeSlowDown=1000;
    private int timeSlowDownStarter=1000; 
    private int boxNo =0;
    private int boxXX[] = new int[1000];
    private int boxYY[] = new int[1000];
    private int lengthl[]=new int[1000];
    private Enemy enemy[]=new Enemy[1000];
    
    public Enviroment(){
        enemy[0] =new Enemy(road_x,1200,368,30,"eli",true,false);
        
        for(int x=0;x<Ypositions.length;x++){ //this is for the block to jump up
            Yposhead[x]=Ypositions[x]-20;
        }
        for(int x=0;x<Ypositions.length;x++){//this is for the block to get back to it's regional position
            YpositionNow[x]=Ypositions[x];
        }
        for(int x=0;x<10;x++){ // this is for the ligting on the mistry box
            unknownBox[x] =  animatePriceBox[0];
        }
        
        pipes[0] = true;
        mario=new Avater(road_x);
        emptyPriceb=new ImageIcon("files/emptyp.png");
        ImageIcon boxImg=new ImageIcon("files/singlebox.png");
        ImageIcon ig=new ImageIcon("files/background.jpg");
        ImageIcon pipes=new ImageIcon("files/pipe.png");
        pipd=pipes.getImage();
        boxI=boxImg.getImage();
        sky = ig.getImage();
        addKeyListener(this);
        setFocusable(true);
        time=new Timer(delay,this);
        time.start();
        
    }
    public void paint(Graphics g){
        g.drawImage(sky,0,0,700,500,this);
        for(int i=0;i<length;i++){
            g.drawImage(sky,road_x+i*700,0,700,500,this);
        }
       g.drawImage(pipd,road_x+450,380,40,40,this);
       g.drawImage(pipd,road_x+700, 300,40,120,this);
       g.drawImage(pipd,road_x+900,270,40,150,this);
       g.setColor(Color.blue);
       g.fillRect(road_x+1200,420,100,100);
       mario.drawAvater(g,this);
        for(int x=0;x<10;x++){
             priceBoxI[x]=new ImageIcon(unknownBox[x]);
             priceBox[x] = priceBoxI[x].getImage();
       
       }    
       enemy[0].drawEnemy(g,this);
       g.drawImage(priceBox[0],road_x+130,this.YpositionNow[0],35,30,this);
       
       g.drawImage(boxI,road_x+210,this.YpositionNow[1],35, 30,this);
       g.drawImage(priceBox[1],road_x+210+34,this.YpositionNow[2],37,30,this);
       g.drawImage(boxI,road_x+210+34+34,this.YpositionNow[3],35,30,this);
       g.drawImage(priceBox[2],road_x+210+34+34+34,this.YpositionNow[4],37,30,this);
       g.drawImage(boxI,road_x+210+34+34+34+34,this.YpositionNow[5],35,30,this);
       g.drawImage(priceBox[3],road_x+210+34+34,this.YpositionNow[6],35,30,this);
       Font d=new Font("hope",Font.BOLD,25);
       g.setColor(Color.red);
       g.setFont(d);
       g.drawString("X"+this.CoinCollacted,290,40);
       g.drawString("MARIO SCORE",40,40);
       g.drawString(""+this.marioScore,45,60);
       g.drawString("WORLD",360,40);
       g.drawString("1-1", 390, 60);
       g.drawString("TIME",500,40);
       g.drawString(""+this.timeR,510,60);
       
       this.CountDown();
       this.CoinTose(g);
       
    }
    public void CountDown(){
          this.timeSlowDown--;
       if(this.timeSlowDown+10 == this.timeSlowDownStarter){
           this.timeSlowDownStarter = this.timeSlowDown;
           this.timeR--;  
       }
    }
    public void CoinTose(Graphics g){
       if(this.coinThrow){           
           ImageIcon co=new ImageIcon(this.CoinNow);
           Image coin = co.getImage();
           g.drawImage(coin,coinX,coinY, 30,30,this);
           g.setColor(Color.orange);
           Font f=new Font("hope",Font.ITALIC,23);
           g.setFont(f);
           int z=coinY+50;
           g.drawString("200",coinX,z);
           this.coinY-=50;
            
               if(this.CoinNow == this.Coin[0]){
                   this.CoinNow = this.Coin[1];
               }else if(this.CoinNow == this.Coin[1]){
                   this.CoinNow = this.Coin[0];
               }
       } 
    }
    public void CheckBoxAndDesied(){
       for(int x=0;x<this.boxNo;x++){
        if(road_x-mario.getAvaterX() <= -boxXX[x]+23 && road_x-mario.getAvaterX() >= -boxXX[x]-length && mario.getAvatery() <= boxYY[x]+30 && mario.getAvatery() >= boxYY[x]-40){
             if(this.direction[0] == true){
                      this.boxAblity[0] =false;
                      this.boxAblity[1] =true;
                  }else if(this.direction[1] == true){
                      this.boxAblity[1] = false;
                      this.boxAblity[0] = true;
                  } 
             
           this.box[0] = 0;
           this.box[1] = boxYY[x]+30;
           System.out.println("box");
           break;
       }else if(road_x-mario.getAvaterX() <= -boxXX[x]+23 && road_x-mario.getAvaterX() >=-boxXX[x]-length && mario.getAvatery() < boxYY[x]-40){
          
           this.box[0] =1;
           this.box[1] =boxYY[x]-40;
            this.boxAblity[0] =true;
           this.boxAblity[1] =true;
           System.out.println("ontop");
                break;
       
        }
       }
       this.box[0] =0;
       this.box[1] =0;
    }
    public boolean isRegistered(int x,int y,int len){
        for(int i=0;i<this.boxNo;i++){
            if(x == this.boxXX[i] && y==this.boxYY[i] && len == this.lengthl[i]){
                return true;
            }
        }
         return false;
    }
    public void getEnemyCollision(int x,int y,int w,int h,int x2,int y2,int w2,int h2){
        if(new Rectangle(road_x+x,y,w,h).intersects(new Rectangle(x2,y2,w2,h2))){
        
    }
    }
    public void collisionde(int x,int y,int w,int h,String type,int priceBoxloc){
        if(!type.equals("enemy")){
           // for(int xlop=0;xlop<1;x++){
                if(new Rectangle(road_x+x,this.YpositionNow[y],w,h).intersects(new Rectangle(enemy[0].getEnemy_x(),enemy[0].getEnemy_y(),50,50))){
                    System.out.println("turn around");
                }
           // }
        }
        if(type.equals("enemy")){
            if(new Rectangle(road_x+x,y,w,h).intersects(new Rectangle(mario.getAvaterX(),mario.getAvatery(),20,60))){
                if(mario.getAvatery()<=y-30){
                    System.out.println("crashed");
                }else{
                    System.out.println("Game over");
                }
            }
           //if(new Rectangle(road_x+x,y,w,h).intersects(new Rectangle()))
        }else{
        if(new Rectangle(road_x+x,this.YpositionNow[y],w,h).intersects(new Rectangle(mario.getAvaterX(),mario.getAvatery(),20,60))){
        
            if(mario.getAvatery()>=YpositionNow[y]){
            if(type.equals("Box?")){//check if it is mistry box if it is check if it is empty if it is not play the coine flip and then make it empyt
             if(this.CoinPositions[y] == 1){
                 this.coinThrow = true;
                 this.CoinPositions[y] =0;
                 this.unknownBox[priceBoxloc] ="files/emptyp.png";
                 this.coinX =road_x+x;
                 this.coinY =YpositionNow[y];
                 this.CoinCollacted++;
                 this.marioScore+=200;
            if(mario.getAvatery()>=YpositionNow[y]){
                 this.YpositionNow[y] = this.Yposhead[y];
           
            }else{
                
            }
                     
             }else{
                 
             }   
            }else if(type.equals("Food")){
                if(this.CoinPositions[y] == 1){
                    
                }
            }else if(type.equals("Box")){
            this.YpositionNow[y] = this.Yposhead[y];
         
            
            }
        }
        }
       }
    }
   public void createBox(int boxX,int boxY,int length){
             if(!this.isRegistered(boxX,boxY,length)){
             boxXX[boxNo] = boxX;
             boxYY[boxNo] = boxY;
             lengthl[boxNo] =length;
             boxNo++;
             }
       if(road_x-mario.getAvaterX() <= -boxX+23 && road_x-mario.getAvaterX() >= -boxX-length && mario.getAvatery() <= boxY+30 && mario.getAvatery() >= boxY-40){
             if(this.direction[0] == true){                     
                      this.boxAblity[0] =false;
                      this.boxAblity[1] =true;
                  }else if(this.direction[1] == true){
                      this.boxAblity[1] = false;
                      this.boxAblity[0] = true;
                  } 
             
           this.box[0] = 0;
           this.box[1] = boxY+60;
           
       }else if(road_x-mario.getAvaterX() <= -boxX+23 && road_x-mario.getAvaterX() >=-boxX-length && mario.getAvatery() < boxY-40){
          
           this.box[0] =1;
           this.box[1] =boxY-40;
            this.boxAblity[0] =true;
           this.boxAblity[1] =true;
           
       }else if(road_x-mario.getAvaterX() <= -boxX+23 && road_x-mario.getAvaterX() >= -boxX-length && mario.getAvatery() > boxY+30){
        this.boxAblity[0] = true;
        this.boxAblity[1] = true;
   }
   }

      public void creatPip(int pipX,int y,int pipnumb){
          if(pipes[pipnumb]){
              if(road_x-mario.getAvaterX() <= -pipX+23 && road_x-mario.getAvaterX() >= -pipX-40 && mario.getAvatery() > y-40){
                  if(this.direction[0] == true){
                      mario.setAvaterX(mario.getAvaterX()-20);
                      this.ablity[0] =false;
                      this.ablity[1] =true;
                  }else if(this.direction[1] == true){
                      mario.setAvaterX(mario.getAvaterX()+20);
                      this.ablity[1] = false;
                      this.ablity[0] = true;
                  }
              }else if(road_x-mario.getAvaterX() <= -pipX+23 && road_x-mario.getAvaterX() >= -pipX-40 && road_x-mario.getAvaterX() <y-40 ){
                  this.object[0] = 1;
                  this.object[1] = y-40;
              }else if(road_x-mario.getAvaterX() >=-pipX+23 || road_x-mario.getAvaterX() <= -pipX-40){
                 this.ablity[1] =true;
                 this.ablity[0]=true;
                 this.object[0] = 0;
                 this.object[1] = 0;
                
              }
              if(road_x-mario.getAvaterX() <= -pipX-40-50){
                  pipes[pipnumb+1]=true;
                  pipes[pipnumb]=false;
              }
          }
      }
     public void lightTurnONandoff(){// this function makes the lights turn on and off in the 
             for(int x=0;x<10;x++){
          if(this.unknownBox[x].equals(this.animatePriceBox[0])){
              this.unknownBox[x] = this.animatePriceBox[1];
          }else if(this.unknownBox[x].equals(this.animatePriceBox[1])){
              this.unknownBox[x] = this.animatePriceBox[0];
          }
          }
     }
     public void BoxPutbackToplace(){//this puts the box back to place after it is hitted by mario
          for(int x=0;x<Ypositions.length;x++){
          if(this.YpositionNow[x] < this.Ypositions[x]){
              this.YpositionNow[x]+=5;
             }
         }
          
     }
     public void Jumping(){
                if(jump && !falling){
           mario.Jump();
         
          if(this.box[0] == 0 && mario.getAvatery() <= this.box[1]-15){
              falling = true;
              jump = false;
              this.boxAblity[0] = true;
              this.boxAblity[1] = true;
               try {
                    AudioInputStream audio=AudioSystem.getAudioInputStream(new File("files/jump.wav"));
                    Clip clip;
                    clip = AudioSystem.getClip();
                    clip.open(audio);
                    clip.start();
                } catch (LineUnavailableException ex) {
                    Logger.getLogger(Enviroment.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Enviroment.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedAudioFileException ex) {
                    Logger.getLogger(Enviroment.class.getName()).log(Level.SEVERE, null, ex);
                }
          }
           
          if(mario.getAvatery() <= this.jumpStart-150){
              falling=true;
              jump=false;
          }
        if(mario.getAvatery() <= 100){
              falling = true;
              jump = false;
          }
      }
     }
     public void NotJumpingButFalling(){
            if(!jump){
          mario.gravity();
          if(this.object[0] == 1 && mario.getAvatery() >this.object[1]-5){
              falling=false;
              mario.setAvaterY(this.object[1]-15);
          }
         if(this.box[0] == 1 && mario.getAvatery() > this.box[1]-15){
             
             falling=false;
             mario.setAvaterY(this.box[1]-15);
            
            }
         if(this.box1[0] == 1 && mario.getAvatery() > this.box1[1]-15){
             falling = false;
             mario.setAvaterY(this.box1[1]-15);
         }
          if(mario.getAvatery() > 360){
              mario.setAvaterY(360);
              falling=false;
          }
      }
     }
        @Override
      public void actionPerformed(ActionEvent e) {

          this.creatPip(450,380,0);
          this.creatPip(700,300,1);
          this.creatPip(900,270,2);
          this.createBox(130,this.Ypositions[0],30);
          this.createBox(210,this.Ypositions[1],30);
          this.createBox(210+34,this.Ypositions[2],30);
          this.createBox(210+34+34,this.Ypositions[3],30);
          this.createBox(210+34+34+34,this.Ypositions[4],30);
          this.createBox(210+34+34+34+34,this.Ypositions[5],30);
          this.createBox(210+34+34,200,30);
          //enemy start
          this.collisionde(enemy[0].getEnemy_x(),enemy[0].getEnemy_y(),50,50,"enemy",0);
          
          //enemy end
          this.collisionde(130,0,35,30,"Box?",0);
          this.collisionde(210,1,35,30,"Box",0);
          this.collisionde(210+34,2,35,30,"Food",1);
          this.collisionde(210+34+34,3,35,30,"Box",0);
          this.collisionde(210+34+34+34,4,35,30,"Box?",2);
          this.collisionde(210+34+34+34+34,5,35,30,"Box",0);
          this.collisionde(210+34+34,6,35,30,"Box?",3);
          this.enemy[0].move();
          
          this.lightTurnONandoff();
          this.BoxPutbackToplace(); 
          this.Jumping();
          this.NotJumpingButFalling();
          this.CheckBoxAndDesied();

      repaint();
    }   
    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        int kcode = e.getKeyCode();
        if(kcode == KeyEvent.VK_RIGHT){
            if(this.ablity[0] && this.boxAblity[0] && this.box1Ablity[0]){
                this.direction[0] = true;
                this.direction[1] = false;
            road_x = mario.moveRight();
            enemy[0].updateRoad_x(road_x);
            repaint();
            }
        }
        if(kcode == KeyEvent.VK_LEFT){
            if(this.ablity[1] && this.boxAblity[1] && this.box1Ablity[1]){ 
                this.direction[1] = true;
                this.direction[0] = false;
            mario.moveBack();
             repaint();
            }
        }
        if(kcode == KeyEvent.VK_SPACE){
            if(!this.falling){
                this.jump = true;
                this.jumpStart = mario.getAvatery();
                    
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
  
    
    
}