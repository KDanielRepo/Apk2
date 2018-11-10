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

public class WorkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Equipment equipment = new Equipment();
        equipment.SetStats();
        SetHp();
        SetDmg();
        setDmgTemp();
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
        GetArea();
        startSeen();
        setMap();
        SetBackground();
        SetResource();
        ShowStats();
        getSpells();
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

    //Statystyki bohatera
    protected static int Poziom = Integer.parseInt(RestActivity.Lvl);
    protected static int Money = Integer.parseInt(RestActivity.Shekles);
    protected int Exp = Integer.parseInt(RestActivity.Experience);
    protected int SetHp(){
        int reset = 0;
        if(reset == 0){
            Hp = ((Poziom*2) + Equipment.Hp)/2;
            reset++;
            return Hp;
        }else if(reset > 0){
        }
        return Hp;
    }
    protected int SetDmg(){
        int reset = 0;
        if(reset == 0){
            Dmg = Equipment.Dmg;
            reset++;
            return Dmg;
        }else if(reset > 0){
        }
        return  Dmg;
    }
    protected int Hp = 0;
    protected int Dmg = 0;
    List<String> Spells = new ArrayList<>();
    protected String ResourceName = RestActivity.Resource;
    protected int Resource = 0;
    protected int Resource_Max = 0;
    protected int SetResource(){
        switch(ResourceName){
            case "Mana":
                Resource_Max = Poziom *10;
                Resource = Resource_Max;
            case "Rage":
                Resource_Max = Poziom * 5;
            case "Energy":
                Resource_Max = Poziom * 15;
        }
        return Resource;
    }
    protected int AddResourceFight(){
        switch(ResourceName){
            case "Mana":
                return Resource;
            case "Rage":
                Resource +=10;
                if(Resource > Resource_Max){
                    Resource = Resource_Max;
                }
            case "Energy":
                Resource +=5;
                if(Resource > Resource_Max){
                    Resource = Resource_Max;
                }
        }
        return  Resource;
    }
    protected int AddResourceMove(){
        switch(ResourceName){
            case "Energy":
                Resource +=5;
                if(Resource > Resource_Max){
                    Resource = Resource_Max;
                }
        }
        return  Resource;
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
        TextView hp = findViewById(R.id.hpBar);
        hp.setText("Hp:"+Integer.toString(Hp));
        TextView exp = findViewById(R.id.expBar);
        exp.setText(ResourceName+" : "+Resource);
    }
    protected void setDmgTemp(){
        dmg_temp = Dmg;
    }

    final Equipment equipment = new Equipment();

    //Statystyki Wroga
    int EnemyHp;
    int EnemyDmg;

    //Liczniki do walki
    private int a = 0;
    private int stun_for = 0;
    private int ablaze_for = 0;
    private int bleed_for = 0;
    private int dmg_temp = 0;
    private int enemy_dmg_temp = 0;
    private Boolean bleed = false;
    private Boolean ablaze = false;
    private Boolean stun = false;
    int tura = 0;
    int click = 1;
    private int deep = 0;
    Boolean stateCheck = true;
    Boolean stateList = true;
    Boolean end = false;
    private int temp_gold=0;
    private int temp_exp=0;

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
    private int testcheck = 0;
    private int k_temp;
    private int k;
    private int id = 0;
    private int finish = 0;

    private void ResetMap(){
        setY=96;
        setX=1584;
        arrayX=0;
        x=0;
        y=0;
        testcheck = 0;
        k=0;
        k_temp=0;
        id=0;
        finish=0;
        deep++;
        ImageView iv = findViewById(R.id.dot);
        iv.setImageResource(R.drawable.dot);
        iv.setTranslationX(x);
        iv.setTranslationY(y);
        if(deep==3){
            System.out.println("To ostatni poziom");
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
            if(k==1 & k_temp ==3){
                k = ThreadLocalRandom.current().nextInt(1, 4);
            }else if(k==2 & k_temp==4){
                k = ThreadLocalRandom.current().nextInt(1, 4);
            }else if(k==3 & k_temp==1){
                k = ThreadLocalRandom.current().nextInt(1, 4);
            }else if(k==4 & k_temp==2) {
                k = ThreadLocalRandom.current().nextInt(1, 4);
            }

            if (k == 1) {
                k_temp = k;
                testcheck -= 6;
                if (testcheck < 1) {
                    testcheck += 6;
                }
                if(xArray[testcheck]==1){
                    i--;
                }else if(xArray[testcheck]!=1){
                    xArray[testcheck] = 1;
                }
            } else if (k == 2) {
                k_temp = k;
                testcheck += 1;
                if (testcheck > 35 | testcheck%6==0) {
                    testcheck -= 1;
                }
                if(xArray[testcheck]==1){
                    i--;
                }else if(xArray[testcheck]!=1){
                    xArray[testcheck] = 1;
                }
            }else if (k == 3) {
                k_temp = k;
                testcheck += 6;
                if (testcheck > 35) {
                    testcheck -= 6;
                }
                if(xArray[testcheck]==1){
                    i--;
                }else if(xArray[testcheck]!=1){
                    xArray[testcheck] = 1;
                }
            } else if (k == 4 ) {
                k_temp = k;
                testcheck -= 1;
                if (testcheck < 1 | testcheck%6==0) {
                    testcheck += 1;
                }
                if(xArray[testcheck]==1){
                    i--;
                }else if(xArray[testcheck]!=1){
                    xArray[testcheck] = 1;
                }
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
        if(getArray(arrayX+1)==1 && getArray(arrayX+6)==1 && getArray(arrayX-1)==1 && getArray(arrayX-6)==1 ){
            constraintLayout.setBackgroundResource(R.drawable.test_map_9);
        }
        if(getArray(arrayX+1)==1 && getArray(arrayX+6)==1 && getArray(arrayX-6)==1 && getArray(arrayX-1)==0){
            constraintLayout.setBackgroundResource(R.drawable.test_map_1);
        }
        if(getArray(arrayX+1)==1 && getArray(arrayX+6)==1 && getArray(arrayX-6)==0 && getArray(arrayX-1)==1){
            constraintLayout.setBackgroundResource(R.drawable.test_map_11);
        }
        if(getArray(arrayX+1)==1 && getArray(arrayX+6)==0 && getArray(arrayX-6)==1 && getArray(arrayX-1)==1){
            constraintLayout.setBackgroundResource(R.drawable.test_map_10);
        }
        if(getArray(arrayX+1)==0 && getArray(arrayX+6)==1 && getArray(arrayX-6)==1 && getArray(arrayX-1)==1){
            constraintLayout.setBackgroundResource(R.drawable.test_map_12);
        }
        if(getArray(arrayX+1)==1 && getArray(arrayX+6)==1 && getArray(arrayX-6)==0 && getArray(arrayX-1)==0){
            constraintLayout.setBackgroundResource(R.drawable.test_map_3);
        }
        if(getArray(arrayX+1)==1 && getArray(arrayX+6)==0 && getArray(arrayX-6)==1 && getArray(arrayX-1)==0){
            constraintLayout.setBackgroundResource(R.drawable.test_map_2);
        }
        if(getArray(arrayX+1)==0 && getArray(arrayX+6)==1 && getArray(arrayX-6)==1 && getArray(arrayX-1)==0){
            constraintLayout.setBackgroundResource(R.drawable.test_map_4);
        }
        if(getArray(arrayX+1)==1 && getArray(arrayX+6)==0 && getArray(arrayX-1)==0 && getArray(arrayX-6)==0 ){
            constraintLayout.setBackgroundResource(R.drawable.test_map_5);
        }
        if(getArray(arrayX+1)==0 && getArray(arrayX+6)==0 && getArray(arrayX-6)==1 && getArray(arrayX-1)==0){
            constraintLayout.setBackgroundResource(R.drawable.test_map_7);
        }
        if(getArray(arrayX+1)==0 && getArray(arrayX+6)==1 && getArray(arrayX-1)==0 && getArray(arrayX-6)==0 ){
            constraintLayout.setBackgroundResource(R.drawable.test_map_6);
        }
        if(getArray(arrayX+1)==0 && getArray(arrayX+6)==0 && getArray(arrayX-6)==0 && getArray(arrayX-1)==1){
            constraintLayout.setBackgroundResource(R.drawable.test_map_8);
        }
        if(getArray(arrayX+1)==1 && getArray(arrayX+6)==0 && getArray(arrayX-6)==0 && getArray(arrayX-1)==1){
            constraintLayout.setBackgroundResource(R.drawable.test_map_13);
        }
        if(getArray(arrayX+1)==0 && getArray(arrayX+6)==1 && getArray(arrayX-6)==0 && getArray(arrayX-1)==1){
            constraintLayout.setBackgroundResource(R.drawable.test_map_14);
        }
        if(getArray(arrayX+1)==0 && getArray(arrayX+6)==0 && getArray(arrayX-6)==1 && getArray(arrayX-1)==1){
            constraintLayout.setBackgroundResource(R.drawable.test_map_15);
        }
    }
    protected int setArray(int a){
        a = arrayX;
        return xArray[a];
    }
    protected void startSeen(){
        seen = new int[36];
        seen[0] = 1;
        for(int i = 1; i < 36; i++){
            seen[i] = 0;
        }
    }
    protected int setSeen(int a){
        seen[a] = 1;
        return seen[a];
    }
    protected void CheckEnd(){
        if(seen[arrayX] == 0){
            setSeen(arrayX);
                finish++;
                System.out.println("finish to: "+finish);
        }else if(seen[arrayX] == 1){
        }
            if(finish == 11){
                System.out.println("Brawo");
                BlackoutMap();
                ResetMap();
                GetArea();
                startSeen();
                setMap();
                ShowMap();
                SetBackground();
            }
        }
    protected int getArray(int a){
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
        if ( arrayX % 6== 0 | setArray(arrayX) == 0 | setArray(arrayX)%6 == 0) {
            arrayX -= 1;
        } else {
            setArray(arrayX);
            ImageView img = findViewById(R.id.dot);
            x+=50;
            img.setTranslationX(x);
            CheckEnd();
            ShowMap();
            SetBackground();
            AddResourceMove();
            ShowStats();
            getRng();
            Fight();
        }
    }
    protected void Left(View v) {
        arrayX -= 1;
        // tak na wszelki wypadek  | setArray(arrayX)%6 == 0 | (arrayX > 0 & arrayX % 6 == 0)
        if ( arrayX < 0 || setArray(arrayX) == 0 || (arrayX > 4 && (arrayX+1)%6==0) ) {
            arrayX += 1;
        } else {
            setArray(arrayX);
            ImageView img = findViewById(R.id.dot);
            x-=50;
            img.setTranslationX(x);
            CheckEnd();
            ShowMap();
            SetBackground();
            AddResourceMove();
            ShowStats();
            getRng();
            Fight();
        }
    }
    protected void Up(View v) {
        arrayX -= 6;
        if (arrayX < 0 || setArray(arrayX) == 0) {
            arrayX += 6;
        } else {
            setArray(arrayX);
            ImageView img = findViewById(R.id.dot);
            y-=50;
            img.setTranslationY(y);
            CheckEnd();
            ShowMap();
            SetBackground();
            AddResourceMove();
            ShowStats();
            getRng();
            Fight();
        }
    }
    protected void Down(View v) {
        arrayX += 6;
        if (arrayX > 36 || setArray(arrayX) == 0) {
            arrayX -= 6;
        } else {
            setArray(arrayX);
            ImageView img = findViewById(R.id.dot);
            y+=50;
            img.setTranslationY(y);
            CheckEnd();
            ShowMap();
            SetBackground();
            AddResourceMove();
            ShowStats();
            getRng();
            Fight();
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
                Dmg -= 2;
                Hp -= 3;
            }else if(click%2==0){
                System.out.print("Wrog plonie i dostaje: "+3+" obrazen");
                EnemyDmg -= 2;
                EnemyHp -= 3;
            }
            Ablaze();
            click--;
            a++;
        }
        if(bleed){
            click++;
            if(click%2==1){
                System.out.print("Krwawisz");
                Dmg -= 1;
                Hp -= 5;
            }else if(click%2==0){
                System.out.print("Wrog Krwawi i dostaje: "+5+" obrazen");
                EnemyDmg -= 1;
                EnemyHp -= 5;
            }
            click--;
            a++;
            Bleed();
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
            EndFight();
            if(!end){
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (!stun) {
                            CpuTurn();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    battleText.setCharacterDelay(30);
                                    battleText.animateText("Wrog zadal: " + EnemyDmg + " obrazen!" + newLine + "Zostalo ci: " + Hp + " punktow zycia!");
                                    System.out.println("Koniec tury: " + tura);
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
            bleed_for = 2;
            Bleed();
            }
            Hp = Hp - EnemyDmg;
        ShowStats();
        EndFight();
    }
    protected void Check(View v) {
        WriteAnim battleText = (WriteAnim) findViewById(R.id.battleText);
        if (stateCheck == true) {
            battleText.setVisibility(View.VISIBLE);
            battleText.setCharacterDelay(30);
            battleText.animateText("Przeciwnik to: "+RestActivity.EnemyName+ newLine+"HP: "+EnemyHp+ newLine+"Zadaje: "+EnemyDmg+" obrazen"+newLine+"Poziom: "+StatsCreate.EnemyLvlVar);
            HideDuringCheck();
            stateCheck = false;
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
        EndFight();
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
        if(a<=stun_for){
            stun = true;
        }else{
            a = 0;
            stun = false;
        }
    }
    protected void Ablaze(){
        if(a<=ablaze_for){
            ablaze = true;
        }else{
            a = 0;
            ablaze = false;
            Dmg = dmg_temp;
            EnemyDmg = enemy_dmg_temp;
        }
    }
    protected void Bleed(){
        if(a<=bleed_for){
            bleed = true;
        }else{
            a = 0;
            bleed = false;
            Dmg = dmg_temp;
            EnemyDmg = enemy_dmg_temp;
        }
    }
    protected void Test_at1(final View v){
        CheckforIlments();
        WriteAnim battleText = (WriteAnim) findViewById(R.id.battleText);
        battleText.setVisibility(View.VISIBLE);
        battleText.setCharacterDelay(30);
        click++;
        AddResourceFight();
        EnemyHp = EnemyHp - Dmg;
        battleText.animateText("zadales: "+Dmg+" obrazen!"+newLine+ "Wrogowi zostalo: "+EnemyHp+" punktow zycia!");
        FightAnimMelee();
    }
    //tutaj gotowy szablon do umiejek
    protected void Test_at2(final View v){
        Boolean done = false;
        final WriteAnim battleText = (WriteAnim) findViewById(R.id.battleText);
        battleText.setVisibility(View.VISIBLE);
        battleText.setCharacterDelay(30);
        switch(ResourceName){
            case "Mana":
                if(Resource >= 10){
                    EnemyHp -= (Dmg*3);
                    Resource -= 10;
                    Ablaze();
                    ablaze_for = 1;
                    battleText.animateText("zadales: "+Dmg*3+" obrazen!"+newLine+ "Wrogowi zostalo: "+EnemyHp+" punktow zycia!");
                    done = true;
                }else{
                    System.out.println("Za malo many");
                }
                break;
            case "Rage":
                if(Resource != 0){
                    EnemyHp -= Resource/2;
                    Resource = 0;
                    battleText.animateText("zadales: "+Resource/2+" obrazen!"+newLine+ "Wrogowi zostalo: "+EnemyHp+" punktow zycia!");
                    done = true;
                }else{
                    System.out.println("Za malo furii");
                }
                break;
            case "Energy":
                if(Resource >= 5){
                    EnemyHp -= (Dmg*2);
                    Resource -=5;
                    Bleed();
                    battleText.animateText("zadales: "+Dmg*2+" obrazen!"+newLine+ "Wrogowi zostalo: "+EnemyHp+" punktow zycia!");
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
        switch(ResourceName){
            case "Mana":
                if(Resource >= 15){
                    battleText.animateText("Dodales sobie bariere o mocy: "+Hp/2+" !"+newLine+ "Zostalo ci: "+(Hp+(Hp/2))+" punktow zycia!");
                    Hp += (Hp/2);
                    Resource -= 15;
                    done = true;
                }else{
                    System.out.println("Za malo many");
                }
                break;
            case "Rage":
                if(Resource >= (Resource/2)){
                    battleText.animateText("Przywrociles sobie: "+Resource/4+" Hp!"+newLine+ "Zostalo ci: "+(Hp+(Resource/4))+" punktow zycia!");
                    Hp += (Resource/4);
                    Resource -= (Resource/2);
                    done = true;
                }else{
                    System.out.println("Za malo furii");
                }
                break;
            case "Energy":
                if(Resource >= 15){
                    battleText.animateText("Przywrociles sobie: "+Hp/4+" Hp!"+newLine+ "Zostalo ci: "+(Hp+(Hp/4))+" punktow zycia!");
                    Hp += (Hp/4);
                    Resource -=15;
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
        switch(ResourceName){
            case "Mana":
                if(Resource >= 25){
                    EnemyHp -= Dmg/2;
                    Resource -= 25;
                    stun_for=2;
                    Stunned();
                    battleText.animateText("Zadales: "+Dmg+" obrazen!"+newLine+ "Zostalo wrogowi: "+EnemyHp+" punktow zycia!");
                    done = true;
                }else{
                    System.out.println("Za malo many");
                }
                break;
            case "Rage":
                if(Resource >= 15){
                    EnemyHp -= Dmg;
                    Resource -= 15;
                    stun_for=1;
                    Stunned();
                    battleText.animateText("Zadales: "+Dmg+" obrazen!"+newLine+ "Zostalo wrogowi: "+EnemyHp+" punktow zycia!");
                    done = true;
                }else{
                    System.out.println("Za malo furii");
                }
                break;
            case "Energy":
                if(Resource >= 10){
                    EnemyHp -= (Dmg*2);
                    Resource -=10;
                    stun_for=1;
                    Stunned();
                    battleText.animateText("Zadales: "+Dmg*2+" obrazen!"+newLine+ "Zostalo wrogowi: "+EnemyHp+" punktow zycia!");
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
    protected void getSpells(){
        RestActivity restActivity = new RestActivity();
        for(int i = 0; i < 8; i++){
            restActivity.getSpells(i);
            Spells.add(restActivity.getSpells(i));
        }
    }

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
            EnemyHp = StatsCreate.EnemyLvlVar *4;
            EnemyDmg = StatsCreate.EnemyLvlVar;
            enemy_dmg_temp = EnemyDmg;
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
    protected void EndFight() {
        if (EnemyHp <= 0) {
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
            Money += StatsCreate.GetMoney();
            Exp += StatsCreate.GetExperience();
            temp_gold+=StatsCreate.GetMoney();
            temp_exp+=StatsCreate.GetExperience();

            id=0;
            ShowMap();
            BattleWriter();
            if (Exp >= Poziom * 2) {
                Exp = Exp - Poziom * 2;
                Poziom += 1;
            }
        }
        if (Hp < 1) {
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
    protected void Finished(){
        final WriteAnim writeAnim = (WriteAnim) findViewById(R.id.battleText);
        writeAnim.setVisibility(View.VISIBLE);
        writeAnim.setCharacterDelay(30);
        HideMovement();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                writeAnim.animateText("brawo");
            }
        },250);
        writeAnim.animateText("zebrales: "+temp_gold+" zlota i: "+temp_exp+" doswiadczenia");
        final ConstraintLayout constraintLayout = findViewById(R.id.Screen);
        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                constraintLayout.setOnClickListener(null);
                LostExit();
            }
        });
    }

    //Komunikaty na ekranie
    protected void Writer(){
        final WriteAnim writer = (WriteAnim) findViewById(R.id.writeAnim);
        writer.setVisibility(View.VISIBLE);
        //Add a character every
        writer.setCharacterDelay(30);
        writer.animateText("Otrzymujesz: " + StatsCreate.GetExperience() + " doswiadczenia i: " + StatsCreate.GetMoney() + " Shekli!");
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

            String pz = Integer.toString(Poziom);
            String mo = Integer.toString(Money);
            String ex = Integer.toString(Exp);

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
            System.out.println(Poziom);
            System.out.println(Money);
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
                            Hp+=20;
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
                            Resource+=20;
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
                            Hp+=20;
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
                            Resource+=20;
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
                            Hp+=20;
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
                            Resource+=20;
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
