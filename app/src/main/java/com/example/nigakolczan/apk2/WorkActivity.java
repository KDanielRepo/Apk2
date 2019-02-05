package com.example.nigakolczan.apk2;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Environment;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.graphics.drawable.AnimationDrawable;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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
import android.graphics.Bitmap;

public class WorkActivity extends AppCompatActivity implements Runnable {
    Thread test;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Equipment equipment = new Equipment();
        equipment.SetStats();
        setContentView(R.layout.activity_work);
        playerSprite();

        ImageView dot = (ImageView) findViewById(R.id.dot);
        dot.setBackgroundResource(R.drawable.dot);
        getSpells();
        xArray = new int[36];

        test = new Thread(this, "test");
        test.start();
        System.out.println(Thread.activeCount());
        }
    public Node getNode(String tagName, NodeList nodes) {
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
        StartSeen();
        setMap();
        SetResource();
        ShowStats();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                SetBackground();
                SetSecondBackground();
            }
        });
        StartSeen();
        setMap();
        SetResource();
        ShowStats();
    }
    public void playerSprite(){
        ImageView img = (ImageView) findViewById(R.id.testanim);
        System.out.println(RestActivity.Rasa+" "+RestActivity.Klasa);
        if(RestActivity.Rasa.equals("Czlowiek") & RestActivity.Klasa.equals("Mag")){
            System.out.println("jestem 1 1");
            img.setBackgroundResource(R.drawable.mage_h);
        }
        if(RestActivity.Rasa.equals("Czlowiek") & RestActivity.Klasa.equals("Wojownik")){
            System.out.println("jestem 1 2");
            img.setBackgroundResource(R.drawable.warrior_h);
        }
        if(RestActivity.Rasa.equals("Czlowiek") & RestActivity.Klasa.equals("Lotr")){
            System.out.println("jestem 1 3");
            img.setBackgroundResource(R.drawable.rogue_h);
        }
        if(RestActivity.Rasa.equals("Krasnolud") & RestActivity.Klasa.equals("Mag")){
            System.out.println("jestem 2 1");
            img.setBackgroundResource(R.drawable.mage_k);
        }
        if(RestActivity.Rasa.equals("Krasnolud") & RestActivity.Klasa.equals("Wojownik")){
            System.out.println("jestem 2 2 ");
            img.setBackgroundResource(R.drawable.warrior_k);
        }
        if(RestActivity.Rasa.equals("Krasnolud") & RestActivity.Klasa.equals("Lotr")){
            System.out.println("jestem 2 3");
            img.setBackgroundResource(R.drawable.rogue_k);
        }
        if(RestActivity.Rasa.equals("Elf") & RestActivity.Klasa.equals("Mag")){
            System.out.println("jestem 3 1");
            img.setBackgroundResource(R.drawable.mage_e);
        }
        if(RestActivity.Rasa.equals("Elf") & RestActivity.Klasa.equals("Wojownik")){
            System.out.println("jestem 3 2");
            img.setBackgroundResource(R.drawable.warrior_e);
        }
        if(RestActivity.Rasa.equals("Elf") & RestActivity.Klasa.equals("Lotr")){
            System.out.println("jestem 3 3");
            img.setBackgroundResource(R.drawable.rogue_e);
        }
        AnimationDrawable frameAnimation = (AnimationDrawable) img.getBackground();
        frameAnimation.start();

    }
    public void playerAttackSprite(){
        ImageView img = (ImageView) findViewById(R.id.testanim);
        if(RestActivity.Rasa.equals("Czlowiek") & RestActivity.Klasa.equals("Mag")){
            System.out.println("jestem 1 1");
            img.setBackgroundResource(R.drawable.mage_h_attack);
        }
        if(RestActivity.Rasa.equals("Czlowiek") & RestActivity.Klasa.equals("Wojownik")){
            System.out.println("jestem 1 2");
            img.setBackgroundResource(R.drawable.warrior_h_attack);
        }
        if(RestActivity.Rasa.equals("Czlowiek") & RestActivity.Klasa.equals("Lotr")){
            System.out.println("jestem 1 3");
            img.setBackgroundResource(R.drawable.rogue_h_attack);
        }
        if(RestActivity.Rasa.equals("Krasnolud") & RestActivity.Klasa.equals("Mag")){
            System.out.println("jestem 2 1");
            img.setBackgroundResource(R.drawable.mage_k_attack);
        }
        if(RestActivity.Rasa.equals("Krasnolud") & RestActivity.Klasa.equals("Wojownik")){
            System.out.println("jestem 2 2 ");
            img.setBackgroundResource(R.drawable.warrior_k_attack);
        }
        if(RestActivity.Rasa.equals("Krasnolud") & RestActivity.Klasa.equals("Lotr")){
            System.out.println("jestem 2 3");
            img.setBackgroundResource(R.drawable.rogue_k_attack);
        }
        if(RestActivity.Rasa.equals("Elf") & RestActivity.Klasa.equals("Mag")){
            System.out.println("jestem 3 1");
            img.setBackgroundResource(R.drawable.mage_e_attack);
        }
        if(RestActivity.Rasa.equals("Elf") & RestActivity.Klasa.equals("Wojownik")){
            System.out.println("jestem 3 2");
            img.setBackgroundResource(R.drawable.warrior_e_attack);
        }
        if(RestActivity.Rasa.equals("Elf") & RestActivity.Klasa.equals("Lotr")){
            System.out.println("jestem 3 3");
            img.setBackgroundResource(R.drawable.rogue_e_attack);
        }
    }

    //Statystyki bohatera
    protected static int lvl = Integer.parseInt(RestActivity.Lvl);
    protected static int shekles = Integer.parseInt(RestActivity.Shekles);
    protected int exp = Integer.parseInt(RestActivity.Experience);
    public int SetHp(){
        int reset = 0;
        if(reset == 0){
            hp = ((lvl*2) + Equipment.Hp)/2;
            reset++;
            return hp;
        }else if(reset > 0){
        }
        return hp;
    }
    public int SetDmg(){
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
    public int SetResource(){
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
    public int AddResourceFight(){
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
    public int AddResourceMove(){
        switch(resourceName){
            case "Energy":
                resource +=5;
                if(resource > resourceMax){
                    resource = resourceMax;
                }
        }
        return  resource;
    }
    public void getSpells(){
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
    public void ShowStats(){
        TextView hpBar = findViewById(R.id.hpBar);
        hpBar.setText("Hp:"+Integer.toString(hp));
        TextView exp = findViewById(R.id.expBar);
        exp.setText(resourceName+" : "+resource);
    }
    public void setDmgTemp(){
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

    //Wspólrzędne na mapie
    float x = 0;
    float y = 0;
    int arrayX=0;

    //Zmiene do tworzenia mapy
    private int[] xArray;
    private int[] seen;
    private int testCheck = 0;
    private int kTemp=0;
    private int k=0;
    private int id = 0;
    private int finish = 0;

    private void ResetMap(){
        arrayX=0;
        x=0;
        y=0;
        testCheck = 0;
        k=0;
        kTemp=0;
        id=0;
        finish=0;
        deep++;
        SetBackground();
        SetSecondBackground();
        ImageView iv = findViewById(R.id.dot);
        iv.setBackgroundResource(R.drawable.dot);
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
        ImageView imageView1 = findViewById(R.id.dotHelper);
        float setY=imageView1.getY();
        float setX=imageView1.getX();
        for(int i = 0; i<36; i++){
            ImageView imageView = findViewById(id);
            if(i>0 & i%6==0){
                setX=imageView1.getX();
                setY+=imageView1.getLayoutParams().height;
            }
            if(seen[i]==0){
                setX+=imageView1.getLayoutParams().width;
            }
            if(seen[i]!=0){
                imageView.setTranslationX(setX);
                imageView.setTranslationY(setY);
                imageView.setBackgroundResource(0);
                id++;
                setX+=imageView1.getLayoutParams().width;
            }}
    }

    public void GetArea(){
        xArray = new int[36];
        xArray[0] = 1;
        for (int i = 0; i < 12; i++) {
            k = ThreadLocalRandom.current().nextInt(1, 4);
            if(k==1 && kTemp ==3){
                k = ThreadLocalRandom.current().nextInt(1, 4);
            }else if(k==2 && kTemp==4){
                k = ThreadLocalRandom.current().nextInt(1, 4);
            }else if(k==3 && kTemp==1){
                k = ThreadLocalRandom.current().nextInt(1, 4);
            }else if(k==4 && kTemp==2) {
                k = ThreadLocalRandom.current().nextInt(1, 4);
            }

            if (k == 1) {
                kTemp = k;
                testCheck -= 6;
                if (testCheck < 1) {
                    testCheck += 6;
                }
                if(xArray[testCheck]==1){
                    i--;
                }else if(xArray[testCheck]!=1){
                    xArray[testCheck] = 1;
                }
            } else if (k == 2) {
                kTemp = k;
                testCheck += 1;
                if (testCheck > 35 || testCheck%6==0) {
                    if(testCheck>35){
                        testCheck = 2;
                    }else{
                        testCheck -= 1;
                    }
                }
                if(xArray[testCheck]==1){
                    i--;
                }else if(xArray[testCheck]!=1){
                    xArray[testCheck] = 1;
                }
            }else if (k == 3) {
                kTemp = k;
                testCheck += 6;
                if (testCheck > 35) {
                    testCheck -= 6;
                }
                if(xArray[testCheck]==1){
                    i--;
                }else if(xArray[testCheck]!=1){
                    xArray[testCheck] = 1;
                }
            } else if (k == 4 ) {
                kTemp = k;
                testCheck -= 1;
                if (testCheck < 1 || (testCheck+1)%6==0) {
                    testCheck += 1;
                }
                if(xArray[testCheck]==1){
                    i--;
                }else if(xArray[testCheck]!=1){
                    xArray[testCheck] = 1;
                }
            }
        }
    }
    protected void setMap(){
        id=0;
        ImageView imageView1 = findViewById(R.id.dotHelper);
        imageView1.getLayoutParams();
        float setY=imageView1.getY();
        float setX=imageView1.getX();
        for(int i = 0; i<36; i++){
            if(i>0 & i%6==0){
                setX=imageView1.getX();
                setY+=imageView1.getLayoutParams().height;
            }
            if(xArray[i]==0){
                setX+=imageView1.getLayoutParams().width;
            }
            if(xArray[i]!=0){
                android.support.constraint.ConstraintLayout constraintLayout = findViewById(R.id.Screen);
                ImageView imageView = new ImageView(this);
                //imageView.setImageResource(R.drawable.bgdot);
                imageView.setLayoutParams(new LinearLayout.LayoutParams(imageView1.getLayoutParams().width,imageView1.getLayoutParams().height));
                imageView.setPadding(8,8,8,8);
                imageView.setId(id);
                imageView.setTranslationX(setX);
                imageView.setTranslationY(setY);
                id++;
                setX+=imageView1.getLayoutParams().width;
                constraintLayout.addView(imageView);
            }
        }
    }
    protected void HideMap(){
        id=0;
        ImageView imageView1 = findViewById(R.id.dotHelper);
        float setY=imageView1.getY();
        float setX=imageView1.getX();
        for(int i = 0; i<36; i++){
            if(i>0 & i%6==0){
                setX=imageView1.getX();
                setY+=imageView1.getLayoutParams().height;
            }
            if(xArray[i]==0){
                setX+=imageView1.getLayoutParams().width;
            }
            if(xArray[i]!=0){
                ImageView imageView = findViewById(id);
                imageView.setTranslationX(setX);
                imageView.setTranslationY(setY);
                imageView.setBackgroundResource(0);
                id++;
                setX+=imageView1.getLayoutParams().width;
            }}}
    protected void ShowMap(){
        id=0;
        ImageView imageView1 = findViewById(R.id.dotHelper);
        float setY=imageView1.getY();
        float setX=imageView1.getX();
        for(int i = 0; i<36; i++){
            ImageView imageView = findViewById(id);
            if(i>0 & i%6==0){
                setX=imageView1.getX();
                setY+=imageView1.getLayoutParams().height;
            }
            if(seen[i]==0){
                setX+=imageView1.getLayoutParams().width;
            }
            if(seen[i]!=0){
                imageView.setTranslationX(setX);
                imageView.setTranslationY(setY);
                imageView.setBackgroundResource(R.drawable.bgdot);
                id++;
                setX+=imageView1.getLayoutParams().width;
            }}
    }

    public void SetBackground(){
        ImageView imageView = findViewById(R.id.bg);
        if(GetArray(arrayX+1)==1 && GetArray(arrayX+6)==1 && GetArray(arrayX-1)==1 && GetArray(arrayX-6)==1 ){
            if((arrayX) % 6== 0){
                imageView.setBackgroundResource(R.drawable.test_map_1);
            }else {
                imageView.setBackgroundResource(R.drawable.test_map_9);
            }
        }
        if(GetArray(arrayX+1)==1 && GetArray(arrayX+6)==1 && GetArray(arrayX-6)==1 && GetArray(arrayX-1)==0){
            imageView.setBackgroundResource(R.drawable.test_map_1);
        }
        if(GetArray(arrayX+1)==1 && GetArray(arrayX+6)==1 && GetArray(arrayX-6)==0 && GetArray(arrayX-1)==1){
            if((arrayX) % 6== 0){
                imageView.setBackgroundResource(R.drawable.test_map_3);
            }else {
                imageView.setBackgroundResource(R.drawable.test_map_11);
            }
        }
        if(GetArray(arrayX+1)==1 && GetArray(arrayX+6)==0 && GetArray(arrayX-6)==1 && GetArray(arrayX-1)==1){
            if((arrayX) % 6== 0){
                imageView.setBackgroundResource(R.drawable.test_map_2);
            }else {
                imageView.setBackgroundResource(R.drawable.test_map_10);
            }
        }
        if(GetArray(arrayX+1)==0 && GetArray(arrayX+6)==1 && GetArray(arrayX-6)==1 && GetArray(arrayX-1)==1){
            if((arrayX) % 6== 0){
                imageView.setBackgroundResource(R.drawable.test_map_4);
            }else {
                imageView.setBackgroundResource(R.drawable.test_map_12);
            }
        }
        if(GetArray(arrayX+1)==1 && GetArray(arrayX+6)==1 && GetArray(arrayX-6)==0 && GetArray(arrayX-1)==0){
            imageView.setBackgroundResource(R.drawable.test_map_3);
        }
        if(GetArray(arrayX+1)==1 && GetArray(arrayX+6)==0 && GetArray(arrayX-6)==1 && GetArray(arrayX-1)==0){
            imageView.setBackgroundResource(R.drawable.test_map_2);
        }
        if(GetArray(arrayX+1)==0 && GetArray(arrayX+6)==1 && GetArray(arrayX-6)==1 && GetArray(arrayX-1)==0){
            imageView.setBackgroundResource(R.drawable.test_map_4);
        }
        if(GetArray(arrayX+1)==1 && GetArray(arrayX+6)==0 && GetArray(arrayX-1)==0 && GetArray(arrayX-6)==0 ){
            imageView.setBackgroundResource(R.drawable.test_map_5);
        }
        if(GetArray(arrayX+1)==0 && GetArray(arrayX+6)==0 && GetArray(arrayX-6)==1 && GetArray(arrayX-1)==0){
            imageView.setBackgroundResource(R.drawable.test_map_7);
        }
        if(GetArray(arrayX+1)==0 && GetArray(arrayX+6)==1 && GetArray(arrayX-1)==0 && GetArray(arrayX-6)==0 ){
            imageView.setBackgroundResource(R.drawable.test_map_6);
        }
        if(GetArray(arrayX+1)==0 && GetArray(arrayX+6)==0 && GetArray(arrayX-6)==0 && GetArray(arrayX-1)==1){
            imageView.setBackgroundResource(R.drawable.test_map_8);
        }
        if(GetArray(arrayX+1)==1 && GetArray(arrayX+6)==0 && GetArray(arrayX-6)==0 && GetArray(arrayX-1)==1){
            if((arrayX) % 6== 0){
                imageView.setBackgroundResource(R.drawable.test_map_5);
            }else {
                imageView.setBackgroundResource(R.drawable.test_map_13);
            }
        }
        if(GetArray(arrayX+1)==0 && GetArray(arrayX+6)==1 && GetArray(arrayX-6)==0 && GetArray(arrayX-1)==1){
            if((arrayX) % 6== 0){
                imageView.setBackgroundResource(R.drawable.test_map_6);
            }else {
                imageView.setBackgroundResource(R.drawable.test_map_14);
            }
        }
        if(GetArray(arrayX+1)==0 && GetArray(arrayX+6)==0 && GetArray(arrayX-6)==1 && GetArray(arrayX-1)==1){
            if((arrayX) % 6== 0){
                imageView.setBackgroundResource(R.drawable.test_map_7);
            }else {
                imageView.setBackgroundResource(R.drawable.test_map_15);
            }
        }
    }
    public void SetSecondBackground(){
        ImageView imageView = findViewById(R.id.bgSecond);
        if(GetArray(arrayX+1)==1 && GetArray(arrayX+6)==1 && GetArray(arrayX-1)==1 && GetArray(arrayX-6)==1 ){
            if((arrayX) % 6== 0){
                imageView.setBackgroundResource(R.drawable.test_map_1);
            }else {
                imageView.setBackgroundResource(R.drawable.test_map_9);
            }
        }
        if(GetArray(arrayX+1)==1 && GetArray(arrayX+6)==1 && GetArray(arrayX-6)==1 && GetArray(arrayX-1)==0){
            imageView.setBackgroundResource(R.drawable.test_map_1);
        }
        if(GetArray(arrayX+1)==1 && GetArray(arrayX+6)==1 && GetArray(arrayX-6)==0 && GetArray(arrayX-1)==1){
            if((arrayX) % 6== 0){
                imageView.setBackgroundResource(R.drawable.test_map_3);
            }else {
                imageView.setBackgroundResource(R.drawable.test_map_11);
            }
        }
        if(GetArray(arrayX+1)==1 && GetArray(arrayX+6)==0 && GetArray(arrayX-6)==1 && GetArray(arrayX-1)==1){
            if((arrayX) % 6== 0){
                imageView.setBackgroundResource(R.drawable.test_map_2);
            }else {
                imageView.setBackgroundResource(R.drawable.test_map_10);
            }
        }
        if(GetArray(arrayX+1)==0 && GetArray(arrayX+6)==1 && GetArray(arrayX-6)==1 && GetArray(arrayX-1)==1){
            if((arrayX) % 6== 0){
                imageView.setBackgroundResource(R.drawable.test_map_4);
            }else {
                imageView.setBackgroundResource(R.drawable.test_map_12);
            }
        }
        if(GetArray(arrayX+1)==1 && GetArray(arrayX+6)==1 && GetArray(arrayX-6)==0 && GetArray(arrayX-1)==0){
            imageView.setBackgroundResource(R.drawable.test_map_3);
        }
        if(GetArray(arrayX+1)==1 && GetArray(arrayX+6)==0 && GetArray(arrayX-6)==1 && GetArray(arrayX-1)==0){
            imageView.setBackgroundResource(R.drawable.test_map_2);
        }
        if(GetArray(arrayX+1)==0 && GetArray(arrayX+6)==1 && GetArray(arrayX-6)==1 && GetArray(arrayX-1)==0){
            imageView.setBackgroundResource(R.drawable.test_map_4);
        }
        if(GetArray(arrayX+1)==1 && GetArray(arrayX+6)==0 && GetArray(arrayX-1)==0 && GetArray(arrayX-6)==0 ){
            imageView.setBackgroundResource(R.drawable.test_map_5);
        }
        if(GetArray(arrayX+1)==0 && GetArray(arrayX+6)==0 && GetArray(arrayX-6)==1 && GetArray(arrayX-1)==0){
            imageView.setBackgroundResource(R.drawable.test_map_7);
        }
        if(GetArray(arrayX+1)==0 && GetArray(arrayX+6)==1 && GetArray(arrayX-1)==0 && GetArray(arrayX-6)==0 ){
            imageView.setBackgroundResource(R.drawable.test_map_6);
        }
        if(GetArray(arrayX+1)==0 && GetArray(arrayX+6)==0 && GetArray(arrayX-6)==0 && GetArray(arrayX-1)==1){
            imageView.setBackgroundResource(R.drawable.test_map_8);
        }
        if(GetArray(arrayX+1)==1 && GetArray(arrayX+6)==0 && GetArray(arrayX-6)==0 && GetArray(arrayX-1)==1){
            if((arrayX) % 6== 0){
                imageView.setBackgroundResource(R.drawable.test_map_5);
            }else {
                imageView.setBackgroundResource(R.drawable.test_map_13);
            }
        }
        if(GetArray(arrayX+1)==0 && GetArray(arrayX+6)==1 && GetArray(arrayX-6)==0 && GetArray(arrayX-1)==1){
            if((arrayX) % 6== 0){
                imageView.setBackgroundResource(R.drawable.test_map_6);
            }else {
                imageView.setBackgroundResource(R.drawable.test_map_14);
            }
        }
        if(GetArray(arrayX+1)==0 && GetArray(arrayX+6)==0 && GetArray(arrayX-6)==1 && GetArray(arrayX-1)==1){
            if((arrayX) % 6== 0){
                imageView.setBackgroundResource(R.drawable.test_map_7);
            }else {
                imageView.setBackgroundResource(R.drawable.test_map_15);
            }
        }
    }
    public int SetArray(int a){
        a = arrayX;
        if(arrayX>35){
            a-=1;
        }
        return xArray[a];
    }
    public void animBgLeft(){
        final ImageView iv = findViewById(R.id.bg);
        final ImageView imageView = findViewById(R.id.bgSecond);
        iv.setX(0);
        iv.setY(0);
        imageView.setX(0);
        imageView.setY(0);
        final ValueAnimator animator = ValueAnimator.ofFloat(0.0f,1.0f);
        animator.setRepeatCount(0);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(1000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                final float progress = (float) valueAnimator.getAnimatedValue();
                final float width = iv.getWidth();
                final float translationX = width * progress;
                imageView.setTranslationX(translationX);
                iv.setTranslationX(translationX-width);
            }
        });
        animator.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SetBackground();
            }
        },500);
    }
    public void animBgRight(){
        final ImageView iv = findViewById(R.id.bg);
        final ImageView imageView = findViewById(R.id.bgSecond);
        final ValueAnimator animator = ValueAnimator.ofFloat(0.0f,-1.0f);
        imageView.setX(0);
        imageView.setY(0);
        iv.setX(0);
        iv.setY(0);
        animator.setRepeatCount(0);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(1000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                final float progress = (float) valueAnimator.getAnimatedValue();
                final float width = iv.getWidth();
                final float translationX = width * progress;
                imageView.setTranslationX(translationX);
                iv.setTranslationX(translationX+width);
            }
        });
        animator.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SetBackground();
            }
        },500);
    }
    public void animBgUp(){
        final ImageView iv = findViewById(R.id.bg);
        final ImageView imageView = findViewById(R.id.bgSecond);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoomin);
        imageView.startAnimation(animation);
        iv.startAnimation(animation);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoomout);
                iv.startAnimation(animation1);
                imageView.startAnimation(animation1);
                SetBackground();
                SetSecondBackground();
            }
        },500);

    }
    public void animBgDown(){
        final ImageView iv = findViewById(R.id.bg);
        final ImageView imageView = findViewById(R.id.bgSecond);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoomin);
        imageView.startAnimation(animation);
        iv.startAnimation(animation);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoomout);
                iv.startAnimation(animation1);
                imageView.startAnimation(animation1);
                SetBackground();
                SetSecondBackground();
            }
        },500);
    }
    public void StartSeen(){
        seen = new int[36];
        seen[0] = 1;
        for(int i = 1; i < 36; i++){
            seen[i] = 0;
        }
    }
    public int SetSeen(int a){
        seen[a] = 1;
        return seen[a];
    }
    public void CheckEnd(){
        if(seen[arrayX] == 0){
            SetSeen(arrayX);
                finish++;
                System.out.println("finish to: "+finish);
        }else if(seen[arrayX] == 1){
        }
            if(finish == 12){
                BlackoutMap();
                ResetMap();
                GetArea();
                StartSeen();
                setMap();
                ShowMap();
                SetBackground();
                SetSecondBackground();
            }
        }
    public int GetArray(int a){
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
    public void Finished(){
        final WriteAnim writeAnim = (WriteAnim) findViewById(R.id.battleText);
        writeAnim.setVisibility(View.VISIBLE);
        writeAnim.setCharacterDelay(30);
        HideMovement();
        writeAnim.animateText("brawo");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                writeAnim.animateText("na tej wyprawie udalo ci sie zebrac: "+tempGold+" zlota i: "+tempExp+" punktow doswiadczenia");
                saveStats();
            }
        },1000);
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
    public int getRng() {
        end = false;
        rng = ThreadLocalRandom.current().nextInt(0, 4);
        return rng;
    }

    //Sekcja ruchu na mapie
    public void Right(View v) {
        arrayX += 1;
        if (arrayX>35 | arrayX % 6== 0 | SetArray(arrayX) == 0 | SetArray(arrayX)%6 == 0) {
            arrayX -= 1;
        } else {
            SetArray(arrayX);
            ImageView img = findViewById(R.id.dot);
            x+=img.getLayoutParams().width;
            img.setTranslationX(x);
            CheckEnd();
            ShowMap();
            animBgRight();
            AddResourceMove();
            ShowStats();
            blockMovement();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    getRng();
                    Fight();
                    enableMovement();
                }
            },600);
            BossFight();
        }
    }
    public void Left(View v) {
        arrayX -= 1;
        // tak na wszelki wypadek  | SetArray(arrayX)%6 == 0 | (arrayX > 0 & arrayX % 6 == 0)
        if ( arrayX < 0 || SetArray(arrayX) == 0 || (arrayX > 4 && (arrayX+1)%6==0) ) {
            arrayX += 1;
        } else {
            SetArray(arrayX);
            ImageView img = findViewById(R.id.dot);
            animBgLeft();
            x-=img.getLayoutParams().width;
            img.setTranslationX(x);
            CheckEnd();
            ShowMap();
            AddResourceMove();
            ShowStats();
            blockMovement();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    getRng();
                    Fight();
                    enableMovement();
                }
            },600);
            BossFight();
        }
    }
    public void Up(View v) {
        arrayX -= 6;
        if (arrayX < 0 || SetArray(arrayX) == 0) {
            arrayX += 6;
        } else {
            SetArray(arrayX);
            ImageView img = findViewById(R.id.dot);
            animBgUp();
            y-=img.getLayoutParams().height;
            img.setTranslationY(y);
            CheckEnd();
            ShowMap();
            AddResourceMove();
            ShowStats();
            blockMovement();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    getRng();
                    Fight();
                    enableMovement();
                }
            },600);
            BossFight();
        }
    }
    public void Down(View v) {
        arrayX += 6;
        if (arrayX > 36 || SetArray(arrayX) == 0) {
            arrayX -= 6;
        } else {
            SetArray(arrayX);
            ImageView img = findViewById(R.id.dot);
            animBgDown();
            y+=img.getLayoutParams().height;
            img.setTranslationY(y);
            CheckEnd();
            ShowMap();
            AddResourceMove();
            ShowStats();
            blockMovement();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    getRng();
                    /*Fight();*/
                    enableMovement();
                }
            },600);
            BossFight();
        }
    }
    public void blockMovement(){
        ImageButton up = findViewById(R.id.Up);
        ImageButton down = findViewById(R.id.Down);
        ImageButton left = findViewById(R.id.Left);
        ImageButton right = findViewById(R.id.Right);
        up.setEnabled(false);
        down.setEnabled(false);
        left.setEnabled(false);
        right.setEnabled(false);
    }
    public void enableMovement(){
        ImageButton up = findViewById(R.id.Up);
        ImageButton down = findViewById(R.id.Down);
        ImageButton left = findViewById(R.id.Left);
        ImageButton right = findViewById(R.id.Right);
        up.setEnabled(true);
        down.setEnabled(true);
        left.setEnabled(true);
        right.setEnabled(true);
    }

    //Interfejs w trakcie walki
    /*public void Exit(View v) {
        LostExit();
    }*/
    public void LostExit(){
        Intent tavern = new Intent(getApplicationContext(),TavernActivity.class);
        startActivity(tavern);
    }
    public void move(){
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
    public void Move(View v) {
        move();
    }
    public void CpuTurn() {
        int cpurng = ThreadLocalRandom.current().nextInt(0,5);
        ImageView img = findViewById(R.id.testanim_2);
        img.setTranslationX(-540);
        enemyAttackSprite();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ImageView img = findViewById(R.id.testanim_2);
                img.setTranslationX(0);
                enemySprite();
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
    public void Check(View v) {
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
    public void List(View v) {
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
    public void FightAnimMelee(){
        ImageView img = findViewById(R.id.testanim);
        playerAttackSprite();
        img.setTranslationX(540);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ImageView img = findViewById(R.id.testanim);
                img.setTranslationX(0);
                playerSprite();
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
    public void Stunned(){
        if(a<=stunFor){
            stun = true;
        }else{
            a = 0;
            stun = false;
        }
    }
    public void Ablaze(){
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
    public void Bleed(){
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
    public void CheckforIlments(){
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
    public void Test_at1(final View v){
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
    public void Test_at2(final View v){
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
    public void Test_at3(final View v){
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
    public void Test_at4(final View v){
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
    public void Test_at5(final View v){}
    public void Test_at6(final View v){}
    public void Test_at7(final View v){}
    public void Test_at8(final View v){}

    //pokazuje lub ukrywa liste ruchow w walce
    public void CheckSkillsFirstRow(){
        getSpells();
        TavernActivity tavernActivity = new TavernActivity();
        tavernActivity.SetSpellNames();
        Boolean skill_1 = Spells.get(0).equals("T");
        if(skill_1){
            Button test_at1 = findViewById(R.id.test_At1);
            test_at1.setText("Cios Fizyczny");
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
    public void CheckSkillsSecRow(){
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

    public void MoveList(){
        Button move = findViewById(R.id.Move);
        move.setVisibility(View.GONE);
        Button check = findViewById(R.id.Check);
        check.setVisibility(View.GONE);
        Button list = findViewById(R.id.List);
        list.setVisibility(View.GONE);
        /*Button dunno = findViewById(R.id.dunno);
        dunno.setVisibility(View.GONE);*/

        CheckSkillsFirstRow();

        Button back = findViewById(R.id.back);
        back.setVisibility(View.VISIBLE);
    }
    public void MoveList_next(View v){
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
    public void MoveList_previous(View v){
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
    public void MoveList_hide(){
        Button move = findViewById(R.id.Move);
        move.setVisibility(View.VISIBLE);
        Button check = findViewById(R.id.Check);
        check.setVisibility(View.VISIBLE);
        Button list = findViewById(R.id.List);
        list.setVisibility(View.VISIBLE);
        /*Button dunno = findViewById(R.id.dunno);
        dunno.setVisibility(View.VISIBLE);*/
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
    public void Back(View v){
        MoveList_hide();
    }
    public void BlockButtons(){
        Button move = findViewById(R.id.Move);
        move.setEnabled(false);
        Button check = findViewById(R.id.Check);
        check.setEnabled(false);
        Button list = findViewById(R.id.List);
        list.setEnabled(false);
        /*Button dunno = findViewById(R.id.dunno);
        dunno.setEnabled(false);*/
    }
    public void EnableButtons(){
        Button move = findViewById(R.id.Move);
        move.setEnabled(true);
        Button check = findViewById(R.id.Check);
        check.setEnabled(true);
        Button list = findViewById(R.id.List);
        list.setEnabled(true);
        /*Button dunno = findViewById(R.id.dunno);
        dunno.setEnabled(true);*/
    }

    //pokazuje lub ukrywa strzalki ruchu
    public void HideMovement(){
        ImageButton up = findViewById(R.id.Up);
        up.setVisibility(View.GONE);
        ImageButton down = findViewById(R.id.Down);
        down.setVisibility(View.GONE);
        ImageButton left = findViewById(R.id.Left);
        left.setVisibility(View.GONE);
        ImageButton right = findViewById(R.id.Right);
        right.setVisibility(View.GONE);
    }
    public void ShowMovement(){
        ImageButton up = findViewById(R.id.Up);
        up.setVisibility(View.VISIBLE);
        ImageButton down = findViewById(R.id.Down);
        down.setVisibility(View.VISIBLE);
        ImageButton left = findViewById(R.id.Left);
        left.setVisibility(View.VISIBLE);
        ImageButton right = findViewById(R.id.Right);
        right.setVisibility(View.VISIBLE);
    }

    //pokazuje lub ukrywa elementy interface'u w trakcie check'u
    public void HideDuringCheck(){
        Button move = findViewById(R.id.Move);
        move.setVisibility(View.GONE);
        Button list = findViewById(R.id.List);
        list.setVisibility(View.GONE);
        /*Button dunno = findViewById(R.id.dunno);
        dunno.setVisibility(View.GONE);*/
    }
    public void ShowAfterCheck(){
        Button move = findViewById(R.id.Move);
        move.setVisibility(View.VISIBLE);
        Button list = findViewById(R.id.List);
        list.setVisibility(View.VISIBLE);
       /* Button dunno = findViewById(R.id.dunno);
        dunno.setVisibility(View.VISIBLE);*/
    }

    //pokazuje lub ukrywa elementy interface'u w trakcie patrzenia do eq
    public void HideEq(){
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
        /*Button dunno = findViewById(R.id.dunno);
        dunno.setVisibility(View.VISIBLE);*/

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
    public void ShowEq(){
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
        item_up.setImageResource(R.drawable.up);
        item_down.setImageResource(R.drawable.down);
        Set_item();
    }
    public void enemySprite(){
        ImageView enemy = findViewById(R.id.testanim_2);
        enemy.setVisibility(View.VISIBLE);
        switch(StatsCreate.EnemyNameVar){
            case 1:
                enemy.setBackgroundResource(R.drawable.green);
                break;
            case 2:
                enemy.setBackgroundResource(R.drawable.red);
                break;
            case 3:
                enemy.setBackgroundResource(R.drawable.blue);
                break;
            default:
                enemy.setBackgroundResource(R.drawable.testanim_2);
                AnimationDrawable frameAnimation_2 = (AnimationDrawable) enemy.getBackground();
                frameAnimation_2.start();
                break;
        }
    }
    public void enemyAttackSprite(){
        ImageView enemy = findViewById(R.id.testanim_2);
        enemy.setVisibility(View.VISIBLE);
        switch(StatsCreate.EnemyNameVar){
            case 1:
                enemy.setBackgroundResource(R.drawable.green_attack);
                break;
            case 2:
                enemy.setBackgroundResource(R.drawable.red_attack);
                break;
            case 3:
                enemy.setBackgroundResource(R.drawable.blue_attack);
                break;
            default:
                enemy.setBackgroundResource(R.drawable.testanim_2);
                AnimationDrawable frameAnimation_2 = (AnimationDrawable) enemy.getBackground();
                frameAnimation_2.start();
                break;
        }
    }

    //Poczatek i koniec walki
    public void Fight() {
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
            enemySprite();
            /*Button dunno = findViewById(R.id.dunno);
            dunno.setVisibility(View.VISIBLE);*/
            ImageView map = findViewById(R.id.testbg);
            map.setVisibility(View.GONE);
            ImageView dot = findViewById(R.id.dot);
            dot.setVisibility(View.GONE);
            ImageView player = findViewById(R.id.testanim);
            player.setVisibility(View.VISIBLE);
            HideMap();

            HideMovement();
        }
    }
    public void BossFight(){
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
            /*Button dunno = findViewById(R.id.dunno);
            dunno.setVisibility(View.VISIBLE);*/
            ImageView map = findViewById(R.id.testbg);
            map.setVisibility(View.GONE);
            ImageView dot = findViewById(R.id.dot);
            dot.setVisibility(View.GONE);
            ImageView player = findViewById(R.id.testanim);
            player.setVisibility(View.VISIBLE);
            enemySprite();
            HideMap();

            HideMovement();
        }
    }
    public void EndBossFight() {
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
            /*Button dunno = findViewById(R.id.dunno);
            dunno.setVisibility(View.GONE);*/
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
    public void EndFight() {
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
            /*Button dunno = findViewById(R.id.dunno);
            dunno.setVisibility(View.GONE);*/
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
    public void LostFight(){
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
    public void Writer(){
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
    public void BattleWriter(){
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
    public void saveStats(){
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
    public void item_first(){
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
    public void item_second(){
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
    public void item_third(){
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

    public void item_1(View v){
        item_first();
    }
    public void item_2(View v) {
        item_second();
    }
    public void item_3(View v) {
        item_third();
    }

    public void item_up(View v){
        b++;
        if(b+1 >= Equipment.max){
            b--;
        }
        item_first();
        item_second();
        item_third();
    }
    public void item_down(View v){

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
