package com.example.nigakolczan.apk2;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.graphics.drawable.AnimationDrawable;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.StackView;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import  java.util.concurrent.ThreadLocalRandom;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class WorkActivity extends AppCompatActivity implements Runnable {
    Thread test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Equipment equipment = new Equipment();
        equipment.SetStats();
        setContentView(R.layout.activity_work);
        ImageView img = (ImageView) findViewById(R.id.testanim);
        img.setBackgroundResource(R.drawable.testanim_1);
        AnimationDrawable frameAnimation = (AnimationDrawable) img.getBackground();
        frameAnimation.start();

        ImageView img_2 = (ImageView) findViewById(R.id.testanim_2);
        img_2.setBackgroundResource(R.drawable.testanim_2);
        AnimationDrawable frameAnimation_2 = (AnimationDrawable) img_2.getBackground();
        frameAnimation_2.start();

        ImageView dot = (ImageView) findViewById(R.id.dot);
        dot.setImageResource(R.drawable.dot);
        getSpells();
        xArray = new int[36];

        test = new Thread(this, "test");
        test.start();
        System.out.println(Thread.activeCount());
        }
    protected Node getNode(String tagName, NodeList nodes) {
        for (int x = 0; x < nodes.getLength(); x++) {
            Node node = nodes.item(x);
            if (node.getNodeName().equalsIgnoreCase(tagName)) {
                return node;
            }
        }

        return null;
    }

    public void run(){
        SetHp();
        SetDmg();
        setDmgTemp();
        GetArea();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                SetBackground();
            }
        });
        StartSeen();
        setMap();
        SetResource();
        ShowStats();
    }

    //Statystyki bohatera
    protected static int lvl = Integer.parseInt(RestActivity.Lvl);
    protected static int shekles = Integer.parseInt(RestActivity.Shekles);
    protected int exp = Integer.parseInt(RestActivity.Experience);
    protected int SetHp(){
        int reset = 0;
        if(reset == 0){
            hp = ((lvl*2) + Equipment.Hp)/2;
            reset++;
            return hp;
        }else if(reset > 0){
        }
        return hp;
    }
    protected int SetDmg(){
        int reset = 0;
        if(reset == 0){
            dmg = Equipment.Dmg;
            reset++;
            return dmg;
        }else if(reset > 0){
        }
        return  dmg;
    }
    protected int hp = 0;
    protected int dmg = 0;
    List<String> Spells = new ArrayList<>();
    protected String resourceName = RestActivity.Resource;
    protected int resource = 0;
    protected int resourceMax = 0;
    protected int SetResource(){
        switch(resourceName){
            case "Mana":
                resourceMax = lvl *10;
                resource = resourceMax;
            case "Rage":
                resourceMax = lvl * 5;
            case "Energy":
                resourceMax = lvl * 15;
        }
        return resource;
    }
    protected int AddResourceFight(){
        switch(resourceName){
            case "Mana":
                return resource;
            case "Rage":
                resource +=10;
                if(resource > resourceMax){
                    resource = resourceMax;
                }
            case "Energy":
                resource +=5;
                if(resource > resourceMax){
                    resource = resourceMax;
                }
        }
        return  resource;
    }
    protected int AddResourceMove(){
        switch(resourceName){
            case "Energy":
                resource +=5;
                if(resource > resourceMax){
                    resource = resourceMax;
                }
        }
        return  resource;
    }
    protected void getSpells(){
        RestActivity restActivity = new RestActivity();
        for(int i = 0; i < 8; i++){
            restActivity.getSpells(i);
            Spells.add(restActivity.getSpells(i));
        }
    }
    private void SetEq(){
        RestActivity restActivity = new RestActivity();
        ImageButton Helm = findViewById(R.id.Helm);
        Helm.setScaleType(ImageButton.ScaleType.FIT_XY);
        /*String helm = null;
        String chest = null;
        String legs = null;
        String boots = null;
        String weapon = null;
        String cape = null;
        helm.valueOf(eq.get(0));
        chest.valueOf(eq.get(1));
        legs.valueOf(eq.get(2));
        boots.valueOf(eq.get(3));
        weapon.valueOf(eq.get(4));
        cape.valueOf(eq.get(5));*/
        switch(restActivity.getEquipment(0)){
            case "First":
                Helm.setImageResource(R.drawable.helm_1);
                break;
            case "Second":
                Helm.setImageResource(R.drawable.helm_2);
                break;
            case "Third":
                Helm.setImageResource(R.drawable.helm_3);
                break;
        }

        ImageButton Chest = findViewById(R.id.Chest);
        Chest.setScaleType(ImageButton.ScaleType.FIT_XY);
        switch(restActivity.getEquipment(1)){
            case "First":
                Chest.setImageResource(R.drawable.chest_1);
                break;
            case "Second":
                Chest.setImageResource(R.drawable.chest_2);
                break;
            case "Third":
                Chest.setImageResource(R.drawable.chest_3);
                break;
        }

        ImageButton Legs = findViewById(R.id.Legs);
        Legs.setScaleType(ImageButton.ScaleType.FIT_XY);
        switch(restActivity.getEquipment(2)){
            case "First":
                Legs.setImageResource(R.drawable.legs_1);
                break;
            case "Second":
                Legs.setImageResource(R.drawable.legs_2);
                break;
            case "Third":
                Legs.setImageResource(R.drawable.legs_3);
                break;
        }

        ImageButton Boots = findViewById(R.id.Boots);
        Boots.setScaleType(ImageButton.ScaleType.FIT_XY);
        switch(restActivity.getEquipment(3)){
            case "First":
                Boots.setImageResource(R.drawable.boots_1);
                break;
            case "Second":
                Boots.setImageResource(R.drawable.boots_2);
                break;
            case "Third":
                Boots.setImageResource(R.drawable.boots_3);
                break;
        }

        ImageButton Weapon = findViewById(R.id.Weapon);
        Weapon.setScaleType(ImageButton.ScaleType.FIT_XY);
        switch(restActivity.getEquipment(4)){
            case "First":
                Weapon.setImageResource(R.drawable.weapon_1);
                break;
            case "Second":
                Weapon.setImageResource(R.drawable.weapon_2);
                break;
            case "Third":
                Weapon.setImageResource(R.drawable.weapon_3);
                break;
        }

        ImageButton Cape = findViewById(R.id.Cape);
        Cape.setScaleType(ImageButton.ScaleType.FIT_XY);
        switch(restActivity.getEquipment(5)){
            case "First":
                Cape.setImageResource(R.drawable.cape_1);
                break;
            case "Second":
                Cape.setImageResource(R.drawable.cape_2);
                break;
            case "Third":
                Cape.setImageResource(R.drawable.cape_3);
                break;
        }
    }
    protected void ShowStats(){
        TextView hpBar = findViewById(R.id.hpBar);
        hpBar.setText("Hp:"+Integer.toString(hp));
        TextView exp = findViewById(R.id.expBar);
        exp.setText(resourceName+" : "+resource);
    }
    protected void setDmgTemp(){
        dmgTemp = dmg;
    }

    final Equipment equipment = new Equipment();

    //Statystyki Wroga
    int enemyHp;
    int enemyDmg;
    int bossHp;
    int bossDmg;

    //Liczniki do walki
    private int a = 0;
    private int stunFor = 0;
    private int ablazeFor = 0;
    private int bleedFor = 0;
    private int dmgTemp = 0;
    private int enemyDmgTemp = 0;
    private int bossDmgTemp = 0;
    private Boolean bleed = false;
    private Boolean ablaze = false;
    private Boolean stun = false;
    int tura = 0;
    int click = 1;
    private int deep = 0;
    private Boolean stateCheck = true;
    private Boolean stateList = true;
    private Boolean end = false;
    private int tempGold=0;
    private int tempExp=0;
    private Boolean bossFight = false;

    String newLine = System.getProperty("line.separator");

    //Współrzędne na mapie
    int x = 0;
    int y = 0;
    int arrayX=0;

    //Współrzędne dla tworzenia mapy
    int setY=96;
    int setX=1584;

    //Zmiene do tworzenia mapy
    private int[] xArray;
    private int[] seen;
    private int testCheck = 0;
    private int kTemp=0;
    private int k=0;
    private int id = 0;
    private int finish = 0;


    private void ResetMap(){
        setY=96;
        setX=1584;
        arrayX=0;
        x=0;
        y=0;
        testCheck = 0;
        k=0;
        kTemp=0;
        id=0;
        finish=0;
        deep++;
        ImageView iv = findViewById(R.id.dot);
        iv.setImageResource(R.drawable.dot);
        iv.setTranslationX(x);
        iv.setTranslationY(y);
        if(deep == 2){
            bossFight = true;
        }
        if(deep==3){
            System.out.println("To ostatni Lvl");

            Finished();
        }
    }
    protected void BlackoutMap(){
        id=0;
        setY=96;
        setX=1584;
        for(int i = 0; i<36; i++){
            ImageView imageView = findViewById(id);
            if(i>0 & i%6==0){
                setX=1584;
                setY+=50;
            }
            if(seen[i]==0){
                setX+=50;
            }
            if(seen[i]!=0){
                android.support.constraint.ConstraintLayout constraintLayout = findViewById(R.id.Screen);
                imageView.setX(setX);
                imageView.setY(setY);
                imageView.setImageResource(0);
                id++;
                setX+=50;
            }}
    }

    protected void GetArea(){
        xArray = new int[36];
        xArray[0] = 1;
        for (int i = 0; i < 11; i++) {
            k = ThreadLocalRandom.current().nextInt(1, 4);
            if(k==1 && kTemp ==3){
                //System.out.println("jestem w 1 if");
                k = ThreadLocalRandom.current().nextInt(1, 4);
            }else if(k==2 && kTemp==4){
                //System.out.println("jestem w 2 if");
                k = ThreadLocalRandom.current().nextInt(1, 4);
            }else if(k==3 && kTemp==1){
                //System.out.println("jestem w 3 if");
                k = ThreadLocalRandom.current().nextInt(1, 4);
            }else if(k==4 && kTemp==2) {
               // System.out.println("jestem w 4 if");
                k = ThreadLocalRandom.current().nextInt(1, 4);
            }

            if (k == 1) {
                //System.out.println("jestem w 5 if");
                kTemp = k;
                testCheck -= 6;
                if (testCheck < 1) {
                  //  System.out.println("jestem w 6 if");
                    testCheck += 6;
                }
                if(xArray[testCheck]==1){
                   // System.out.println("jestem w 7 if");
                    i--;
                }else if(xArray[testCheck]!=1){
                    xArray[testCheck] = 1;
                }
            } else if (k == 2) {
                kTemp = k;
                testCheck += 1;
                if (testCheck > 35 || testCheck%6==0) {
                   // System.out.println("jestem w 8 if");
                    if(testCheck>35){
                        testCheck = 2;
                    }else{
                        testCheck -= 1;
                    }
                }
                if(xArray[testCheck]==1){
                   //System.out.println("jestem w 9 if");
                    i--;
                }else if(xArray[testCheck]!=1){
                    xArray[testCheck] = 1;
                }
            }else if (k == 3) {
                kTemp = k;
                testCheck += 6;
                if (testCheck > 35) {
                    //System.out.println("jestem w 10 if");
                    testCheck -= 6;
                }
                if(xArray[testCheck]==1){
                    //System.out.println("jestem w 11 if");
                    i--;
                }else if(xArray[testCheck]!=1){
                    xArray[testCheck] = 1;
                }
            } else if (k == 4 ) {
                kTemp = k;
                testCheck -= 1;
                if (testCheck < 1 || (testCheck+1)%6==0) {
                    //System.out.println("jestem w 12 if");
                    testCheck += 1;
                }
                if(xArray[testCheck]==1){
                    //System.out.println("jestem w 13 if");
                    i--;
                }else if(xArray[testCheck]!=1){
                    xArray[testCheck] = 1;
                }
                System.out.println(i);
            }
        }
    }
    protected void setMap(){
        id=0;
        setY=96;
        setX=1584;
       for(int i = 0; i<36; i++){
           if(i>0 & i%6==0){
               setX=1584;
               setY+=50;
           }
           if(xArray[i]==0){
               setX+=50;
           }
           if(xArray[i]!=0){
               android.support.constraint.ConstraintLayout constraintLayout = findViewById(R.id.Screen);
               ImageView imageView = new ImageView(this);
               //imageView.setImageResource(R.drawable.bgdot);
               imageView.setLayoutParams(new LinearLayout.LayoutParams(62,62));
               imageView.setPadding(8,8,8,8);
               imageView.setId(id);
               imageView.setX(setX);
               imageView.setY(setY);
               id++;
               setX+=50;
               constraintLayout.addView(imageView);
               }}}
    protected void HideMap(){
        id=0;
        setY=96;
        setX=1584;
        for(int i = 0; i<36; i++){
            if(i>0 & i%6==0){
                setX=1584;
                setY+=50;
            }
            if(xArray[i]==0){
                setX+=50;
            }
            if(xArray[i]!=0){
                android.support.constraint.ConstraintLayout constraintLayout = findViewById(R.id.Screen);
                ImageView imageView = findViewById(id);
                imageView.setX(setX);
                imageView.setY(setY);
                imageView.setImageResource(0);
                id++;
                setX+=50;
            }}}
    protected void ShowMap(){
        id=0;
        setY=96;
        setX=1584;
        for(int i = 0; i<36; i++){
            ImageView imageView = findViewById(id);
            if(i>0 & i%6==0){
                setX=1584;
                setY+=50;
            }
            if(seen[i]==0){
                setX+=50;
            }
            if(seen[i]!=0){
                android.support.constraint.ConstraintLayout constraintLayout = findViewById(R.id.Screen);
                imageView.setX(setX);
                imageView.setY(setY);
                imageView.setImageResource(R.drawable.bgdot);
                id++;
                setX+=50;
            }}
    }
    protected void SetBackground(){
        android.support.constraint.ConstraintLayout constraintLayout = (android.support.constraint.ConstraintLayout) findViewById(R.id.Screen);
        constraintLayout.setBackgroundResource(0);
        if(GetArray(arrayX+1)==1 && GetArray(arrayX+6)==1 && GetArray(arrayX-1)==1 && GetArray(arrayX-6)==1 ){
            constraintLayout.setBackgroundResource(R.drawable.test_map_9);
        }
        if(GetArray(arrayX+1)==1 && GetArray(arrayX+6)==1 && GetArray(arrayX-6)==1 && GetArray(arrayX-1)==0){
            constraintLayout.setBackgroundResource(R.drawable.test_map_1);
        }
        if(GetArray(arrayX+1)==1 && GetArray(arrayX+6)==1 && GetArray(arrayX-6)==0 && GetArray(arrayX-1)==1){
            constraintLayout.setBackgroundResource(R.drawable.test_map_11);
        }
        if(GetArray(arrayX+1)==1 && GetArray(arrayX+6)==0 && GetArray(arrayX-6)==1 && GetArray(arrayX-1)==1){
            constraintLayout.setBackgroundResource(R.drawable.test_map_10);
        }
        if(GetArray(arrayX+1)==0 && GetArray(arrayX+6)==1 && GetArray(arrayX-6)==1 && GetArray(arrayX-1)==1){
            constraintLayout.setBackgroundResource(R.drawable.test_map_12);
        }
        if(GetArray(arrayX+1)==1 && GetArray(arrayX+6)==1 && GetArray(arrayX-6)==0 && GetArray(arrayX-1)==0){
            constraintLayout.setBackgroundResource(R.drawable.test_map_3);
        }
        if(GetArray(arrayX+1)==1 && GetArray(arrayX+6)==0 && GetArray(arrayX-6)==1 && GetArray(arrayX-1)==0){
            constraintLayout.setBackgroundResource(R.drawable.test_map_2);
        }
        if(GetArray(arrayX+1)==0 && GetArray(arrayX+6)==1 && GetArray(arrayX-6)==1 && GetArray(arrayX-1)==0){
            constraintLayout.setBackgroundResource(R.drawable.test_map_4);
        }
        if(GetArray(arrayX+1)==1 && GetArray(arrayX+6)==0 && GetArray(arrayX-1)==0 && GetArray(arrayX-6)==0 ){
            constraintLayout.setBackgroundResource(R.drawable.test_map_5);
        }
        if(GetArray(arrayX+1)==0 && GetArray(arrayX+6)==0 && GetArray(arrayX-6)==1 && GetArray(arrayX-1)==0){
            constraintLayout.setBackgroundResource(R.drawable.test_map_7);
        }
        if(GetArray(arrayX+1)==0 && GetArray(arrayX+6)==1 && GetArray(arrayX-1)==0 && GetArray(arrayX-6)==0 ){
            constraintLayout.setBackgroundResource(R.drawable.test_map_6);
        }
        if(GetArray(arrayX+1)==0 && GetArray(arrayX+6)==0 && GetArray(arrayX-6)==0 && GetArray(arrayX-1)==1){
            constraintLayout.setBackgroundResource(R.drawable.test_map_8);
        }
        if(GetArray(arrayX+1)==1 && GetArray(arrayX+6)==0 && GetArray(arrayX-6)==0 && GetArray(arrayX-1)==1){
            constraintLayout.setBackgroundResource(R.drawable.test_map_13);
        }
        if(GetArray(arrayX+1)==0 && GetArray(arrayX+6)==1 && GetArray(arrayX-6)==0 && GetArray(arrayX-1)==1){
            constraintLayout.setBackgroundResource(R.drawable.test_map_14);
        }
        if(GetArray(arrayX+1)==0 && GetArray(arrayX+6)==0 && GetArray(arrayX-6)==1 && GetArray(arrayX-1)==1){
            constraintLayout.setBackgroundResource(R.drawable.test_map_15);
        }
    }
    protected int SetArray(int a){
        a = arrayX;
        if(arrayX>35){
            a-=1;
        }
        return xArray[a];
    }

    protected void StartSeen(){
        seen = new int[36];
        seen[0] = 1;
        for(int i = 1; i < 36; i++){
            seen[i] = 0;
        }
    }
    protected int SetSeen(int a){
        seen[a] = 1;
        return seen[a];
    }
    protected void CheckEnd(){
        if(seen[arrayX] == 0){
            SetSeen(arrayX);
                finish++;
                System.out.println("finish to: "+finish);
        }else if(seen[arrayX] == 1){
        }
            if(finish == 11){
                BlackoutMap();
                ResetMap();
                GetArea();
                StartSeen();
                setMap();
                ShowMap();
                SetBackground();
            }
        }
    protected int GetArray(int a){
        xArray[0]=1;
        if(a<0){
            a=0;
            xArray[0]=0;
        }
        if(a>35){
            a=0;
            xArray[0]=0;
        }
        return xArray[a];
    }
    protected void Finished(){
        final WriteAnim writeAnim = (WriteAnim) findViewById(R.id.battleText);
        writeAnim.setVisibility(View.VISIBLE);
        writeAnim.setCharacterDelay(30);
        HideMovement();
        writeAnim.animateText("brawo");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                writeAnim.animateText("zebrales: "+tempGold+" zlota i: "+tempExp+" doswiadczenia");
            }
        },250);
        final ConstraintLayout constraintLayout = findViewById(R.id.Screen);
        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                constraintLayout.setOnClickListener(null);
                LostExit();
            }
        });
    }


    //losowo generowane potyczki
    int rng = 3;
    protected int getRng() {
        end = false;
        rng = ThreadLocalRandom.current().nextInt(0, 4);
        return rng;
    }

    //Sekcja ruchu na mapie
    protected void Right(View v) {
        arrayX += 1;
        if (arrayX>35 | arrayX % 6== 0 | SetArray(arrayX) == 0 | SetArray(arrayX)%6 == 0) {
            arrayX -= 1;
        } else {
            SetArray(arrayX);
            ImageView img = findViewById(R.id.dot);
            x+=50;
            img.setTranslationX(x);
            CheckEnd();
            ShowMap();
            SetBackground();
            AddResourceMove();
            ShowStats();
            //getRng();
            //Fight();
            BossFight();
        }
    }
    protected void Left(View v) {
        arrayX -= 1;
        // tak na wszelki wypadek  | SetArray(arrayX)%6 == 0 | (arrayX > 0 & arrayX % 6 == 0)
        if ( arrayX < 0 || SetArray(arrayX) == 0 || (arrayX > 4 && (arrayX+1)%6==0) ) {
            arrayX += 1;
        } else {
            SetArray(arrayX);
            ImageView img = findViewById(R.id.dot);
            x-=50;
            img.setTranslationX(x);
            CheckEnd();
            ShowMap();
            SetBackground();
            AddResourceMove();
            ShowStats();
            //getRng();
            //Fight();
            BossFight();
        }
    }
    protected void Up(View v) {
        arrayX -= 6;
        if (arrayX < 0 || SetArray(arrayX) == 0) {
            arrayX += 6;
        } else {
            SetArray(arrayX);
            ImageView img = findViewById(R.id.dot);
            y-=50;
            img.setTranslationY(y);
            CheckEnd();
            ShowMap();
            SetBackground();
            AddResourceMove();
            ShowStats();
            //getRng();
           // Fight();
            BossFight();
        }
    }
    protected void Down(View v) {
        arrayX += 6;
        if (arrayX > 36 || SetArray(arrayX) == 0) {
            arrayX -= 6;
        } else {
            SetArray(arrayX);
            ImageView img = findViewById(R.id.dot);
            y+=50;
            img.setTranslationY(y);
            CheckEnd();
            ShowMap();
            SetBackground();
            AddResourceMove();
            ShowStats();
            //getRng();
            //Fight();
            BossFight();
        }
    }

    //Interfejs w trakcie walki
    protected void Exit(View v) {
        LostExit();
    }
    protected void LostExit(){
        Intent tavern = new Intent(getApplicationContext(),TavernActivity.class);
        startActivity(tavern);
    }
    protected void move(){
        final WriteAnim battleText = (WriteAnim) findViewById(R.id.writeAnim);
        battleText.animateText("");
        battleText.setVisibility(View.VISIBLE);
        if(click % 2 == 0){
            CheckforIlments();
            if(bossFight){
                EndBossFight();
            }else{
                EndFight();
            }
            if(!end){
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (!stun) {
                            CpuTurn();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if(bossFight){
                                        battleText.setCharacterDelay(30);
                                        battleText.animateText("Wrog zadal: " + bossDmg + " obrazen!" + newLine + "Zostalo ci: " + hp + " punktow zycia!");
                                        System.out.println("Koniec tury: " + tura);
                                    }else{
                                        battleText.setCharacterDelay(30);
                                        battleText.animateText("Wrog zadal: " + enemyDmg + " obrazen!" + newLine + "Zostalo ci: " + hp + " punktow zycia!");
                                        System.out.println("Koniec tury: " + tura);
                                    }
                                }
                            });
                        }
                    }
                },1000);
                click++;
                tura++;
                final ConstraintLayout constraintLayout = findViewById(R.id.Screen);
                constraintLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        battleText.setVisibility(View.GONE);
                        constraintLayout.setOnClickListener(null);
                        EnableButtons();
                    }
                });
            }
        } else {
            MoveList();
        }
        ShowStats();
    }
    protected void Move(View v) {
        move();
    }
    protected void CpuTurn() {
        int cpurng = ThreadLocalRandom.current().nextInt(0,5);
        ImageView img = findViewById(R.id.testanim_2);
        img.setX(700);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ImageView img = findViewById(R.id.testanim_2);
                img.setX(1200);
            }
        },500);
        if(cpurng == 5){
            bleedFor = 2;
            Bleed();
            }
        ShowStats();
        if(bossFight){
            hp = hp - bossDmg;
            EndBossFight();
        }else{
            hp = hp - enemyDmg;
            EndFight();
        }
    }
    protected void Check(View v) {
        WriteAnim battleText = (WriteAnim) findViewById(R.id.battleText);
        if (stateCheck == true) {
            if(!bossFight){
                battleText.setVisibility(View.VISIBLE);
                battleText.setCharacterDelay(30);
                battleText.animateText("Przeciwnik to: "+RestActivity.EnemyName+ newLine+"HP: "+enemyHp+ newLine+"Zadaje: "+enemyDmg+" obrazen"+newLine+"Lvl: "+StatsCreate.EnemyLvlVar);
                HideDuringCheck();
                stateCheck = false;
            }else{
                battleText.setVisibility(View.VISIBLE);
                battleText.setCharacterDelay(30);
                battleText.animateText("Przeciwnik to: "+RestActivity.bossName+ newLine+"HP: "+bossHp+ newLine+"Zadaje: "+bossDmg+" obrazen"+newLine+"Lvl: "+StatsCreate.bossLvlVar);
                HideDuringCheck();
                stateCheck = false;
            }
        } else if (stateCheck == false) {
            stateCheck = true;
            ShowAfterCheck();
        }
    }
    protected void List(View v) {
        ImageView lv = findViewById(R.id.it_list);
        if (stateList == true) {
            lv.setVisibility(View.VISIBLE);
            ShowEq();
            SetEq();
            stateList = false;
        } else if (stateList == false) {
            lv.setVisibility(View.GONE);
            HideEq();
            stateList = true;
        }
    }

    //ruchy w walce
    protected void FightAnimMelee(){
        ImageView img = findViewById(R.id.testanim);
        img.setX(900);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ImageView img = findViewById(R.id.testanim);
                img.setX(400);
            }
        },500);
        MoveList_hide();
        BlockButtons();
        if(bossFight){
            EndBossFight();
        }else{
            EndFight();
        }
        if(!end){
            final ConstraintLayout constraintLayout = findViewById(R.id.Screen);
            constraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    constraintLayout.setOnClickListener(null);
                    WriteAnim b = findViewById(R.id.battleText);
                    b.setVisibility(View.GONE);
                    move();
                }
            });
        }
    }
    protected void Stunned(){
        if(a<=stunFor){
            stun = true;
        }else{
            a = 0;
            stun = false;
        }
    }
    protected void Ablaze(){
        if(a<=ablazeFor){
            ablaze = true;
        }else{
            a = 0;
            ablaze = false;
            dmg = dmgTemp;
            enemyDmg = enemyDmgTemp;
            if(bossFight){
                bossDmg = bossDmgTemp;
            }
        }
    }
    protected void Bleed(){
        if(a<=bleedFor){
            bleed = true;
        }else{
            a = 0;
            bleed = false;
            dmg = dmgTemp;
            enemyDmg = enemyDmgTemp;
            if(bossFight){
                bossDmg = bossDmgTemp;
            }
        }
    }
    protected void CheckforIlments(){
        if(stun){
            Stunned();
            a++;
        }
        if(ablaze){
            click++;
            if(click%2==1){
                System.out.print("Ploniesz");
                dmg -= 2;
                hp -= 3;
            }else if(click%2==0){
                if(bossFight){
                    System.out.print("Boss plonie i dostaje: "+3+" obrazen");
                    bossDmg -=2;
                    bossHp -=3;
                }else{
                    System.out.print("Wrog plonie i dostaje: "+3+" obrazen");
                    enemyDmg -= 2;
                    enemyHp -= 3;
                }
            }
            Ablaze();
            click--;
            a++;
        }
        if(bleed){
            click++;
            if(click%2==1){
                System.out.print("Krwawisz");
                dmg -= 1;
                hp -= 5;
            }else if(click%2==0){
                if(bossFight){
                    System.out.print("Boss Krwawi i dostaje: "+5+" obrazen");
                    bossDmg -=1;
                    bossHp -=5;
                }else{
                    System.out.print("Wrog Krwawi i dostaje: "+5+" obrazen");
                    enemyDmg -= 1;
                    enemyHp -= 5;
                }
            }
            click--;
            a++;
            Bleed();
        }
    }

    //Umiejetnosci
    protected void Test_at1(final View v){
        CheckforIlments();
        WriteAnim battleText = (WriteAnim) findViewById(R.id.battleText);
        battleText.setVisibility(View.VISIBLE);
        battleText.setCharacterDelay(30);
        click++;
        AddResourceFight();
        if(bossFight){
            bossHp = bossHp - dmg;
            battleText.animateText("zadales: "+dmg+" obrazen!"+newLine+ "Wrogowi zostalo: "+bossHp+" punktow zycia!");
        }else{
            enemyHp = enemyHp - dmg;
            battleText.animateText("zadales: "+dmg+" obrazen!"+newLine+ "Wrogowi zostalo: "+enemyHp+" punktow zycia!");
        }
        FightAnimMelee();
    }
    protected void Test_at2(final View v){
        Boolean done = false;
        final WriteAnim battleText = (WriteAnim) findViewById(R.id.battleText);
        battleText.setVisibility(View.VISIBLE);
        battleText.setCharacterDelay(30);
        switch(resourceName){
            case "Mana":
                if(resource >= 10){
                    if(bossFight){
                        bossHp -= (dmg*3);
                        battleText.animateText("zadales: "+dmg*3+" obrazen!"+newLine+ "Wrogowi zostalo: "+bossHp+" punktow zycia!");
                    }else{
                        enemyHp -= (dmg*3);
                        battleText.animateText("zadales: "+dmg*3+" obrazen!"+newLine+ "Wrogowi zostalo: "+enemyHp+" punktow zycia!");
                    }
                    resource -= 10;
                    Ablaze();
                    ablazeFor = 1;
                    done = true;
                }else{
                    System.out.println("Za malo many");
                }
                break;
            case "Rage":
                if(resource != 0){
                    if(bossFight){
                        bossHp -= resource/2;
                        battleText.animateText("zadales: "+resource/2+" obrazen!"+newLine+ "Wrogowi zostalo: "+bossHp+" punktow zycia!");
                    }else{
                        enemyHp -= resource/2;
                        battleText.animateText("zadales: "+resource/2+" obrazen!"+newLine+ "Wrogowi zostalo: "+enemyHp+" punktow zycia!");
                    }
                    resource = 0;
                    done = true;
                }else{
                    System.out.println("Za malo furii");
                }
                break;
            case "Energy":
                if(resource >= 5){
                    if(bossFight){
                        bossHp -= (dmg*2);
                        battleText.animateText("zadales: "+dmg*2+" obrazen!"+newLine+ "Wrogowi zostalo: "+bossHp+" punktow zycia!");
                    }else{
                        enemyHp -= (dmg*2);
                        battleText.animateText("zadales: "+dmg*2+" obrazen!"+newLine+ "Wrogowi zostalo: "+enemyHp+" punktow zycia!");
                    }
                    resource -=5;
                    Bleed();
                    done = true;
                }else{
                    System.out.println("Za malo energii");
                }
                break;
        }
        if(!done){
            Back(v);
        }else{
            CheckforIlments();
            click++;
            FightAnimMelee();
        }
    }
    protected void Test_at3(final View v){
        Boolean done = false;
        final WriteAnim battleText = (WriteAnim) findViewById(R.id.battleText);
        battleText.setVisibility(View.VISIBLE);
        battleText.setCharacterDelay(30);
        switch(resourceName){
            case "Mana":
                if(resource >= 15){
                    battleText.animateText("Dodales sobie bariere o mocy: "+hp/2+" !"+newLine+ "Zostalo ci: "+(hp+(hp/2))+" punktow zycia!");
                    hp += (hp/2);
                    resource -= 15;
                    done = true;
                }else{
                    System.out.println("Za malo many");
                }
                break;
            case "Rage":
                if(resource >= (resource/2)){
                    battleText.animateText("Przywrociles sobie: "+resource/4+" Hp!"+newLine+ "Zostalo ci: "+(hp+(resource/4))+" punktow zycia!");
                    hp += (resource/4);
                    resource -= (resource/2);
                    done = true;
                }else{
                    System.out.println("Za malo furii");
                }
                break;
            case "Energy":
                if(resource >= 15){
                    battleText.animateText("Przywrociles sobie: "+hp/4+" Hp!"+newLine+ "Zostalo ci: "+(hp+(hp/4))+" punktow zycia!");
                    hp += (hp/4);
                    resource -=15;
                    done = true;
                }else{
                    System.out.println("Za malo energii");
                }
                break;
        }
        if(!done){
            Back(v);
        }else{
            CheckforIlments();
            FightAnimMelee();
            click++;
        }
    }
    protected void Test_at4(final View v){
        Boolean done = false;
        final WriteAnim battleText = (WriteAnim) findViewById(R.id.battleText);
        battleText.setVisibility(View.VISIBLE);
        battleText.setCharacterDelay(30);
        switch(resourceName){
            case "Mana":
                if(resource >= 25){
                    if(bossFight){
                        bossHp -=dmg/2;
                        battleText.animateText("Zadales: "+dmg+" obrazen!"+newLine+ "Zostalo wrogowi: "+bossHp+" punktow zycia!");
                    }else{
                        enemyHp -= dmg/2;
                        battleText.animateText("Zadales: "+dmg+" obrazen!"+newLine+ "Zostalo wrogowi: "+enemyHp+" punktow zycia!");
                    }
                    resource -= 25;
                    stunFor=2;
                    Stunned();
                    done = true;
                }else{
                    System.out.println("Za malo many");
                }
                break;
            case "Rage":
                if(resource >= 15){
                    if(bossFight){
                        bossHp -= dmg;
                        battleText.animateText("Zadales: "+dmg+" obrazen!"+newLine+ "Zostalo wrogowi: "+bossHp+" punktow zycia!");
                    }else{
                        enemyHp -= dmg;
                        battleText.animateText("Zadales: "+dmg+" obrazen!"+newLine+ "Zostalo wrogowi: "+enemyHp+" punktow zycia!");
                    }
                    resource -= 15;
                    stunFor=1;
                    Stunned();
                    done = true;
                }else{
                    System.out.println("Za malo furii");
                }
                break;
            case "Energy":
                if(resource >= 10){
                    if(bossFight){
                        bossHp -= (dmg*2);
                        battleText.animateText("Zadales: "+dmg*2+" obrazen!"+newLine+ "Zostalo wrogowi: "+bossHp+" punktow zycia!");
                    }else{
                        enemyHp -= (dmg*2);
                        battleText.animateText("Zadales: "+dmg*2+" obrazen!"+newLine+ "Zostalo wrogowi: "+enemyHp+" punktow zycia!");
                    }
                    resource -=10;
                    stunFor=1;
                    Stunned();
                    done = true;
                }else{
                    System.out.println("Za malo energii");
                }
                break;
        }
        if(!done){
            Back(v);
        }else{
            CheckforIlments();
            FightAnimMelee();
            EnableButtons();
            Back(v);
        }
    }
    protected void Test_at5(final View v){}
    protected void Test_at6(final View v){}
    protected void Test_at7(final View v){}
    protected void Test_at8(final View v){}

    //pokazuje lub ukrywa liste ruchow w walce
    protected void CheckSkillsFirstRow(){
        getSpells();
        TavernActivity tavernActivity = new TavernActivity();
        tavernActivity.SetSpellNames();
        Boolean skill_1 = Spells.get(0).equals("T");
        if(skill_1){
            Button test_at1 = findViewById(R.id.test_At1);
            test_at1.setText("Melee Attack");
            test_at1.setVisibility(View.VISIBLE);
        }

        Boolean skill_2 = Spells.get(1).equals("T");
        if(skill_2){
            Button test_at2 = findViewById(R.id.test_At2);
            test_at2.setText(tavernActivity.Spells.get(1));
            test_at2.setVisibility(View.VISIBLE);
        }

        Boolean skill_3 = Spells.get(2).equals("T");
        if(skill_3){
            Button test_at3 = findViewById(R.id.test_At3);
            test_at3.setText(tavernActivity.Spells.get(2));
            test_at3.setVisibility(View.VISIBLE);
        }

        Boolean skill_4 = Spells.get(3).equals("T");
        if(skill_4){
            Button test_at4 = findViewById(R.id.test_At4);
            test_at4.setText(tavernActivity.Spells.get(3));
            test_at4.setVisibility(View.VISIBLE);
        }

        Boolean skill_5 = Spells.get(4).equals("T");
        Boolean skill_6 = Spells.get(5).equals("T");
        Boolean skill_7 = Spells.get(6).equals("T");
        Boolean skill_8 = Spells.get(7).equals("T");
        if(skill_5 | skill_6 | skill_7 | skill_8){
            Button nextPage = findViewById(R.id.n_Page);
            nextPage.setVisibility(View.VISIBLE);
            Button previousPage = findViewById(R.id.p_Page);
            previousPage.setVisibility(View.VISIBLE);
        }else if(!skill_5 & !skill_6 & !skill_7 & !skill_8){
            Button nextPage = findViewById(R.id.n_Page);
            nextPage.setVisibility(View.GONE);
            Button previousPage = findViewById(R.id.p_Page);
            previousPage.setVisibility(View.GONE);
        }
    }
    protected void CheckSkillsSecRow(){
        TavernActivity tavernActivity = new TavernActivity();
        Boolean skill_5 = Spells.get(4).equals("T");
        if(skill_5){
            Button test_at5 = findViewById(R.id.test_At5);
            test_at5.setText(tavernActivity.Spells.get(4));
            test_at5.setVisibility(View.VISIBLE);
        }

        Boolean skill_6 = Spells.get(5).equals("T");
        if(skill_6){
            Button test_at6 = findViewById(R.id.test_At6);
            test_at6.setText(tavernActivity.Spells.get(5));
            test_at6.setVisibility(View.VISIBLE);
        }

        Boolean skill_7 = Spells.get(6).equals("T");
        if(skill_7){
            Button test_at7 = findViewById(R.id.test_At7);
            test_at7.setText(tavernActivity.Spells.get(6));
            test_at7.setVisibility(View.VISIBLE);
        }

        Boolean skill_8 = Spells.get(7).equals("T");
        if(skill_8){
            Button test_at8 = findViewById(R.id.test_At8);
            test_at8.setText(tavernActivity.Spells.get(7));
            test_at8.setVisibility(View.VISIBLE);
        }
    }

    protected void MoveList(){
        Button move = findViewById(R.id.Move);
        move.setVisibility(View.GONE);
        Button check = findViewById(R.id.Check);
        check.setVisibility(View.GONE);
        Button list = findViewById(R.id.List);
        list.setVisibility(View.GONE);
        Button dunno = findViewById(R.id.dunno);
        dunno.setVisibility(View.GONE);

        CheckSkillsFirstRow();

        Button back = findViewById(R.id.back);
        back.setVisibility(View.VISIBLE);
    }
    protected void MoveList_next(View v){
        Button test_at1 = findViewById(R.id.test_At1);
        test_at1.setVisibility(View.GONE);
        Button test_at2 = findViewById(R.id.test_At2);
        test_at2.setVisibility(View.GONE);
        Button test_at3 = findViewById(R.id.test_At3);
        test_at3.setVisibility(View.GONE);
        Button test_at4 = findViewById(R.id.test_At4);
        test_at4.setVisibility(View.GONE);

        CheckSkillsSecRow();
    }
    protected void MoveList_previous(View v){
        CheckSkillsFirstRow();

        Button test_at5 = findViewById(R.id.test_At5);
        test_at5.setVisibility(View.GONE);
        Button test_at6 = findViewById(R.id.test_At6);
        test_at6.setVisibility(View.GONE);
        Button test_at7 = findViewById(R.id.test_At7);
        test_at7.setVisibility(View.GONE);
        Button test_at8 = findViewById(R.id.test_At8);
        test_at8.setVisibility(View.GONE);
    }
    protected void MoveList_hide(){
        Button move = findViewById(R.id.Move);
        move.setVisibility(View.VISIBLE);
        Button check = findViewById(R.id.Check);
        check.setVisibility(View.VISIBLE);
        Button list = findViewById(R.id.List);
        list.setVisibility(View.VISIBLE);
        Button dunno = findViewById(R.id.dunno);
        dunno.setVisibility(View.VISIBLE);
        Button test_at1 = findViewById(R.id.test_At1);
        test_at1.setVisibility(View.GONE);
        Button test_at2 = findViewById(R.id.test_At2);
        test_at2.setVisibility(View.GONE);
        Button test_at3 = findViewById(R.id.test_At3);
        test_at3.setVisibility(View.GONE);
        Button test_at4 = findViewById(R.id.test_At4);
        test_at4.setVisibility(View.GONE);
        Button test_at5 = findViewById(R.id.test_At5);
        test_at5.setVisibility(View.GONE);
        Button test_at6 = findViewById(R.id.test_At6);
        test_at6.setVisibility(View.GONE);
        Button test_at7 = findViewById(R.id.test_At7);
        test_at7.setVisibility(View.GONE);
        Button test_at8 = findViewById(R.id.test_At8);
        test_at8.setVisibility(View.GONE);

        Button back = findViewById(R.id.back);
        back.setVisibility(View.GONE);
        Button nextPage = findViewById(R.id.n_Page);
        nextPage.setVisibility(View.GONE);
        Button previousPage = findViewById(R.id.p_Page);
        previousPage.setVisibility(View.GONE);
    }
    protected void Back(View v){
        MoveList_hide();
    }
    protected void BlockButtons(){
        Button move = findViewById(R.id.Move);
        move.setEnabled(false);
        Button check = findViewById(R.id.Check);
        check.setEnabled(false);
        Button list = findViewById(R.id.List);
        list.setEnabled(false);
        Button dunno = findViewById(R.id.dunno);
        dunno.setEnabled(false);
    }
    protected void EnableButtons(){
        Button move = findViewById(R.id.Move);
        move.setEnabled(true);
        Button check = findViewById(R.id.Check);
        check.setEnabled(true);
        Button list = findViewById(R.id.List);
        list.setEnabled(true);
        Button dunno = findViewById(R.id.dunno);
        dunno.setEnabled(true);
    }

    //pokazuje lub ukrywa strzalki ruchu
    protected void HideMovement(){
        Button up = findViewById(R.id.Up);
        up.setVisibility(View.GONE);
        Button down = findViewById(R.id.Down);
        down.setVisibility(View.GONE);
        Button left = findViewById(R.id.Left);
        left.setVisibility(View.GONE);
        Button right = findViewById(R.id.Right);
        right.setVisibility(View.GONE);
    }
    protected void ShowMovement(){
        Button up = findViewById(R.id.Up);
        up.setVisibility(View.VISIBLE);
        Button down = findViewById(R.id.Down);
        down.setVisibility(View.VISIBLE);
        Button left = findViewById(R.id.Left);
        left.setVisibility(View.VISIBLE);
        Button right = findViewById(R.id.Right);
        right.setVisibility(View.VISIBLE);
    }

    //pokazuje lub ukrywa elementy interface'u w trakcie check'u
    protected void HideDuringCheck(){
        Button move = findViewById(R.id.Move);
        move.setVisibility(View.GONE);
        Button list = findViewById(R.id.List);
        list.setVisibility(View.GONE);
        Button dunno = findViewById(R.id.dunno);
        dunno.setVisibility(View.GONE);
    }
    protected void ShowAfterCheck(){
        Button move = findViewById(R.id.Move);
        move.setVisibility(View.VISIBLE);
        Button list = findViewById(R.id.List);
        list.setVisibility(View.VISIBLE);
        Button dunno = findViewById(R.id.dunno);
        dunno.setVisibility(View.VISIBLE);
    }

    //pokazuje lub ukrywa elementy interface'u w trakcie patrzenia do eq
    protected void HideEq(){
        ImageButton helm = findViewById(R.id.Helm);
        helm.setVisibility(View.GONE);
        ImageButton chest = findViewById(R.id.Chest);
        chest.setVisibility(View.GONE);
        ImageButton legs = findViewById(R.id.Legs);
        legs.setVisibility(View.GONE);
        ImageButton boots = findViewById(R.id.Boots);
        boots.setVisibility(View.GONE);
        ImageButton weapon = findViewById(R.id.Weapon);
        weapon.setVisibility(View.GONE);
        ImageButton cape = findViewById(R.id.Cape);
        cape.setVisibility(View.GONE);

        Button move = findViewById(R.id.Move);
        move.setVisibility(View.VISIBLE);
        Button check = findViewById(R.id.Check);
        check.setVisibility(View.VISIBLE);
        Button dunno = findViewById(R.id.dunno);
        dunno.setVisibility(View.VISIBLE);

        ImageButton up_page = findViewById(R.id.up_page);
        up_page.setVisibility(View.GONE);
        ImageButton item_1 = findViewById(R.id.item_1);
        item_1.setVisibility(View.GONE);
        ImageButton item_2 = findViewById(R.id.item_2);
        item_2.setVisibility(View.GONE);
        ImageButton item_3 = findViewById(R.id.item_3);
        item_3.setVisibility(View.GONE);
        ImageButton down_page = findViewById(R.id.down_page);
        down_page.setVisibility(View.GONE);

    }
    protected void ShowEq(){
        ImageButton helm = findViewById(R.id.Helm);
        helm.setVisibility(View.VISIBLE);
        ImageButton chest = findViewById(R.id.Chest);
        chest.setVisibility(View.VISIBLE);
        ImageButton legs = findViewById(R.id.Legs);
        legs.setVisibility(View.VISIBLE);
        ImageButton boots = findViewById(R.id.Boots);
        boots.setVisibility(View.VISIBLE);
        ImageButton weapon = findViewById(R.id.Weapon);
        weapon.setVisibility(View.VISIBLE);
        ImageButton cape = findViewById(R.id.Cape);
        cape.setVisibility(View.VISIBLE);
        ImageView item_1 = findViewById(R.id.item_1);
        item_1.setVisibility(View.VISIBLE);
        ImageView item_2 = findViewById(R.id.item_2);
        item_2.setVisibility(View.VISIBLE);
        ImageView item_3 = findViewById(R.id.item_3);
        item_3.setVisibility(View.VISIBLE);
        ImageView item_up = findViewById(R.id.up_page);
        item_up.setVisibility(View.VISIBLE);
        ImageView item_down = findViewById(R.id.down_page);
        item_down.setVisibility(View.VISIBLE);
        Set_item();
/*
        Button move = findViewById(R.id.Move);
        move.setVisibility(View.GONE);
        Button check = findViewById(R.id.Check);
        check.setVisibility(View.GONE);
        Button dunno = findViewById(R.id.dunno);
        dunno.setVisibility(View.GONE);

        ImageButton up_page = findViewById(R.id.up_page);
        up_page.setVisibility(View.VISIBLE);
        ImageButton item_1 = findViewById(R.id.item_1);
        item_1.setVisibility(View.VISIBLE);
        ImageButton item_2 = findViewById(R.id.item_2);
        item_2.setVisibility(View.VISIBLE);
        ImageButton item_3 = findViewById(R.id.item_3);
        item_3.setVisibility(View.VISIBLE);
        ImageButton down_page = findViewById(R.id.down_page);
        down_page.setVisibility(View.VISIBLE);*/
    }

    //Poczatek i koniec walki
    protected void Fight() {
        if (rng==3) {

            final WriteAnim battleText = (WriteAnim) findViewById(R.id.battleText);
            battleText.setVisibility(View.VISIBLE);
            battleText.setCharacterDelay(30);
            battleText.animateText("Start Walki");
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            battleText.animateText("");
                        }
                    });
                    }
            },1000);
            StatsCreate.EnemyStats();
            RestActivity set = new RestActivity();
            set.EnemyStats();
            enemyHp = StatsCreate.EnemyLvlVar*4;
            enemyDmg = StatsCreate.EnemyLvlVar;
            enemyDmgTemp = enemyDmg;
            Button move = findViewById(R.id.Move);
            move.setVisibility(View.VISIBLE);
            Button check = findViewById(R.id.Check);
            check.setVisibility(View.VISIBLE);
            Button list = findViewById(R.id.List);
            list.setVisibility(View.VISIBLE);
            Button dunno = findViewById(R.id.dunno);
            dunno.setVisibility(View.VISIBLE);
            ImageView map = findViewById(R.id.testbg);
            map.setVisibility(View.GONE);
            ImageView dot = findViewById(R.id.dot);
            dot.setVisibility(View.GONE);
            ImageView player = findViewById(R.id.testanim);
            player.setVisibility(View.VISIBLE);
            ImageView enemy = findViewById(R.id.testanim_2);
            enemy.setVisibility(View.VISIBLE);
            HideMap();

            HideMovement();
        }
    }
    protected void BossFight(){
        if(bossFight){
            final WriteAnim battleText = (WriteAnim) findViewById(R.id.battleText);
            battleText.setVisibility(View.VISIBLE);
            battleText.setCharacterDelay(30);
            battleText.animateText("Start Walki");
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            battleText.animateText("");
                        }
                    });
                }
            },1000);
            StatsCreate.BossStats();
            RestActivity set = new RestActivity();
            set.BossStats();
            bossHp = StatsCreate.bossLvlVar *4;
            bossDmg = StatsCreate.bossLvlVar;
            bossDmgTemp = bossDmg;
            Button move = findViewById(R.id.Move);
            move.setVisibility(View.VISIBLE);
            Button check = findViewById(R.id.Check);
            check.setVisibility(View.VISIBLE);
            Button list = findViewById(R.id.List);
            list.setVisibility(View.VISIBLE);
            Button dunno = findViewById(R.id.dunno);
            dunno.setVisibility(View.VISIBLE);
            ImageView map = findViewById(R.id.testbg);
            map.setVisibility(View.GONE);
            ImageView dot = findViewById(R.id.dot);
            dot.setVisibility(View.GONE);
            ImageView player = findViewById(R.id.testanim);
            player.setVisibility(View.VISIBLE);
            ImageView enemy = findViewById(R.id.testanim_2);
            enemy.setVisibility(View.VISIBLE);
            HideMap();

            HideMovement();
        }
    }
    protected void EndBossFight() {
        if (bossHp <= 0) {
            MoveList_hide();
            click = 1;
            ablaze = false;
            stun = false;
            bleed = false;
            end = true;
            EnableButtons();
            Button move = findViewById(R.id.Move);
            move.setVisibility(View.GONE);
            Button check = findViewById(R.id.Check);
            check.setVisibility(View.GONE);
            Button list = findViewById(R.id.List);
            list.setVisibility(View.GONE);
            Button dunno = findViewById(R.id.dunno);
            dunno.setVisibility(View.GONE);
            ImageView map = findViewById(R.id.testbg);
            map.setVisibility(View.VISIBLE);
            ImageView dot = findViewById(R.id.dot);
            dot.setVisibility(View.VISIBLE);
            ImageView player = findViewById(R.id.testanim);
            player.setVisibility(View.GONE);
            ImageView enemy = findViewById(R.id.testanim_2);
            enemy.setVisibility(View.GONE);
            shekles += StatsCreate.GetBossMoney();
            exp += StatsCreate.GetBossExperience();
            tempGold += StatsCreate.GetBossMoney();
            tempExp += StatsCreate.GetBossExperience();

            id = 0;
            ShowMap();
            BattleWriter();
            if (exp >= lvl * 2) {
                exp = exp - lvl * 2;
                lvl += 1;
            }
        }
        if (hp < 1) {
            ablaze = false;
            stun = false;
            bleed = false;
            BlockButtons();
            System.out.println("PRZEGRYWASZ");
            SetHp();
            LostFight();
        }
    }
    protected void EndFight() {
        if (enemyHp <= 0) {
            MoveList_hide();
            click = 1;
            ablaze = false;
            stun = false;
            bleed = false;
            end = true;
            EnableButtons();
            Button move = findViewById(R.id.Move);
            move.setVisibility(View.GONE);
            Button check = findViewById(R.id.Check);
            check.setVisibility(View.GONE);
            Button list = findViewById(R.id.List);
            list.setVisibility(View.GONE);
            Button dunno = findViewById(R.id.dunno);
            dunno.setVisibility(View.GONE);
            ImageView map = findViewById(R.id.testbg);
            map.setVisibility(View.VISIBLE);
            ImageView dot = findViewById(R.id.dot);
            dot.setVisibility(View.VISIBLE);
            ImageView player = findViewById(R.id.testanim);
            player.setVisibility(View.GONE);
            ImageView enemy = findViewById(R.id.testanim_2);
            enemy.setVisibility(View.GONE);
            shekles += StatsCreate.GetMoney();
            exp += StatsCreate.GetExperience();
            tempGold+=StatsCreate.GetMoney();
            tempExp+=StatsCreate.GetExperience();

            id=0;
            ShowMap();
            BattleWriter();
            if (exp >= lvl * 2) {
                exp = exp - lvl * 2;
                lvl += 1;
            }
        }
        if (hp < 1) {
            ablaze = false;
            stun = false;
            bleed = false;
            BlockButtons();
            System.out.println("PRZEGRYWASZ");
            SetHp();
            LostFight();
        }
    }
    protected void LostFight(){
        final WriteAnim writeAnim = (WriteAnim) findViewById(R.id.battleText);
        writeAnim.setVisibility(View.VISIBLE);
        writeAnim.setCharacterDelay(30);
        writeAnim.animateText("Przegrywasz");
        final ConstraintLayout constraintLayout = findViewById(R.id.Screen);
        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeAnim.animateText("Tracisz czesc zlota i caly ekwipunek ktory tutaj zdobyles");
                constraintLayout.setOnClickListener(null);
            }
        });
        LostExit();
    }

    //Komunikaty na ekranie
    protected void Writer(){
        final WriteAnim writer = (WriteAnim) findViewById(R.id.writeAnim);
        writer.setVisibility(View.VISIBLE);
        writer.setCharacterDelay(30);
        if(bossFight){
            writer.animateText("Otrzymujesz: " + StatsCreate.GetBossExperience() + " doswiadczenia i: " + StatsCreate.GetBossMoney() + " Shekli!");
            bossFight = false;
        }else{
            writer.animateText("Otrzymujesz: " + StatsCreate.GetExperience() + " doswiadczenia i: " + StatsCreate.GetMoney() + " Shekli!");
        }
        final android.support.constraint.ConstraintLayout constraintLayout = (android.support.constraint.ConstraintLayout) findViewById(R.id.Screen);
        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    writer.animateText("");
                    writer.setVisibility(View.GONE);
                    ShowMovement();
                    constraintLayout.setOnClickListener(null);
            }
        });

    }
    protected void BattleWriter(){
        final WriteAnim battleText = (WriteAnim) findViewById(R.id.battleText);
        battleText.setCharacterDelay(30);
        battleText.setVisibility(View.VISIBLE);
        battleText.animateText("Wrog pokonany!");
        final android.support.constraint.ConstraintLayout constraintLayout = (android.support.constraint.ConstraintLayout) findViewById(R.id.Screen);
        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                battleText.animateText("");
                battleText.setVisibility(View.GONE);
                Writer();
            }
        });
    }

    //Zapis statystyk
    protected void Ck(View v){
        try {
            File file = new File ("data/data/com.example.nigakolczan.apk2/Stats_"+RestActivity.a+".xml");
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(file);

            String pz = Integer.toString(lvl);
            String mo = Integer.toString(shekles);
            String ex = Integer.toString(exp);

            NodeList root = doc.getChildNodes();
            Node Stats = getNode("Stats", root);

            Node Chk = getNode("Character", Stats.getChildNodes());
            NamedNodeMap lvl = Chk.getAttributes();
            Node nodeLvl = lvl.getNamedItem("Lvl");
            nodeLvl.setTextContent(pz);

            Node Gold = getNode("Gold", Chk.getChildNodes());
            NamedNodeMap gold = Gold.getAttributes();
            Node nodeGold = gold.getNamedItem("Shekles");
            nodeGold.setTextContent(mo);

            Node Exp = getNode("Experience", Chk.getChildNodes());
            NamedNodeMap exp = Exp.getAttributes();
            Node nodeExp = exp.getNamedItem("Exp");
            nodeExp.setTextContent(ex);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);

            System.out.println("Done");
            System.out.println(lvl);
            System.out.println(shekles);
        } catch (ParserConfigurationException | TransformerException | IOException | SAXException pce) {
            pce.printStackTrace();
        }
    }

    //Odpowiedzialne za stan przedmiotow do uzycia
    private int b = 0;
    protected void item_first(){
        final ImageButton item = findViewById(R.id.item_1);
        if(b <= Equipment.max){
            switch(equipment.GetItemFromBackpack(b)){
                case "Heal":
                    item.setImageResource(R.drawable.hp);
                    item.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            hp+=20;
                            equipment.DeleteFromBackpack(b);
                            Set_item();
                            HideEq();
                            click++;
                            item.setOnClickListener(null);
                        }
                    });
                    break;
                case "Mana":
                    item.setImageResource(R.drawable.mana);
                    item.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            resource+=20;
                            equipment.DeleteFromBackpack(b);
                            Set_item();
                            HideEq();
                            click++;
                            item.setOnClickListener(null);
                        }
                    });

                    break;
                default:
                    item.setImageResource(0);
            }
            item.setScaleType(ImageButton.ScaleType.FIT_XY);
        }else{
            item.setImageResource(0);
        }
    }
    protected void item_second(){
        final ImageButton item = findViewById(R.id.item_2);
        if (b+1 <= Equipment.max) {
            switch (equipment.GetItemFromBackpack(b + 1)) {
                case "Heal":
                    item.setImageResource(R.drawable.hp);
                    item.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            hp+=20;
                            equipment.DeleteFromBackpack(b+1);
                            Set_item();
                            HideEq();
                            click++;
                            item.setOnClickListener(null);
                        }
                    });
                    break;
                case "Mana":
                    item.setImageResource(R.drawable.mana);
                    item.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            resource+=20;
                            equipment.DeleteFromBackpack(b+1);
                            Set_item();
                            HideEq();
                            click++;
                            item.setOnClickListener(null);
                        }
                    });
                    break;
                default:
                    item.setImageResource(0);
            }
            item.setScaleType(ImageButton.ScaleType.FIT_XY);
        }else{
            item.setImageResource(0);
        }
    }
    protected void item_third(){
        final ImageButton item = findViewById(R.id.item_3);
        if (b+2 <= Equipment.max) {
            switch (equipment.GetItemFromBackpack(b + 2)) {
                case "Heal":
                    item.setImageResource(R.drawable.hp);
                    item.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            hp+=20;
                            equipment.DeleteFromBackpack(b+2);
                            Set_item();
                            HideEq();
                            click++;
                            item.setOnClickListener(null);
                        }
                    });
                    break;
                case "Mana":
                    item.setImageResource(R.drawable.mana);
                    item.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            resource+=20;
                            boolean used = true;
                            equipment.DeleteFromBackpack(b+2);
                            Set_item();
                            HideEq();
                            click++;
                            item.setOnClickListener(null);
                        }
                    });
                    break;
                default:
                    item.setImageResource(0);
            }
            item.setScaleType(ImageButton.ScaleType.FIT_XY);
        }else{
            item.setImageResource(0);
        }
    }

    protected void item_1(View v){
        item_first();
    }
    protected void item_2(View v) {
        item_second();
    }
    protected void item_3(View v) {
        item_third();
    }

    protected void item_up(View v){
        b++;
        if(b+1 >= Equipment.max){
            b--;
        }
        item_first();
        item_second();
        item_third();
    }
    protected void item_down(View v){
        b--;
        if(b < 0){
            b++;
        }
        item_first();
        item_second();
        item_third();
    }

    private void Set_item(){
        item_first();
        item_second();
        item_third();
    }
}
