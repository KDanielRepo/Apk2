package com.example.nigakolczan.apk2;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class TestGroundActivity extends AppCompatActivity {
    WorkActivity workActivity = new WorkActivity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testground);

        ImageView img = (ImageView) findViewById(R.id.testanim);
        img.setBackgroundResource(R.drawable.testanim_1);
        AnimationDrawable frameAnimation = (AnimationDrawable) img.getBackground();
        frameAnimation.start();

        ImageView img_2 = (ImageView) findViewById(R.id.testanim_2);
        img_2.setBackgroundResource(R.drawable.testanim_2);
        AnimationDrawable frameAnimation_2 = (AnimationDrawable) img_2.getBackground();
        frameAnimation_2.start();

        Fight();
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
    private static int Poziom = Integer.parseInt(RestActivity.Lvl);
    private int Hp = Poziom * 20;
    private int Dmg = Poziom;
    String newLine = System.getProperty("line.separator");
    private int steps = 0;

    //Statystyki Wroga
    private int EnemyHp;
    private int EnemyDmg;

    //Liczniki do walki
    private int tura = 0;
    private int click = 1;
    private Boolean stateCheck = true;
    private Boolean stateList = true;
    private Boolean end = false;

    private void cpuTurn(){
        ImageView img = findViewById(R.id.testanim_2);
        img.setX(700);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ImageView img = findViewById(R.id.testanim_2);
                img.setX(1200);
            }
        },500);
        EndFight();
    }
    private void HideDuringCheck(){
        Button move = findViewById(R.id.Move);
        move.setVisibility(View.GONE);
        Button list = findViewById(R.id.List);
        list.setVisibility(View.GONE);
        Button dunno = findViewById(R.id.dunno);
        dunno.setVisibility(View.GONE);
    }
    private void ShowAfterCheck(){
        Button move = findViewById(R.id.Move);
        move.setVisibility(View.VISIBLE);
        Button list = findViewById(R.id.List);
        list.setVisibility(View.VISIBLE);
        Button dunno = findViewById(R.id.dunno);
        dunno.setVisibility(View.VISIBLE);
    }

    protected void Check(View view){
        WriteAnim battleText = (WriteAnim) findViewById(R.id.battleText);
        if (stateCheck == true) {
            battleText.setVisibility(View.VISIBLE);
            battleText.setCharacterDelay(30);
            battleText.animateText("Przeciwnik to: Kukla"+newLine+"HP: "+EnemyHp+ newLine+"Zadaje: "+EnemyDmg+" obrazen"+newLine+"Poziom: 1");
            HideDuringCheck();
            stateCheck = false;
        } else if (stateCheck == false) {
            stateCheck = true;
            ShowAfterCheck();
        }
    }

    protected void List(View v){
        workActivity.List(v);
    }


    //Interfejs w trakcie walki
    protected void Exit(View v) {
        Intent tavern = new Intent(getApplicationContext(),TavernActivity.class);
        startActivity(tavern);
    }
    protected void move(){
        final WriteAnim battleText = (WriteAnim) findViewById(R.id.writeAnim);
        battleText.animateText("");
        battleText.setVisibility(View.VISIBLE);
        if(click % 2 == 0){
            EndFight();
            if(!end){
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                            cpuTurn();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    battleText.setCharacterDelay(30);
                                    battleText.animateText("Wrog zadal: " + EnemyDmg + " obrazen!" + newLine + "Zostalo ci: " + Hp + " punktow zycia!");
                                    System.out.println("Koniec tury: " + tura);
                                }
                            });
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
    }
    protected void Move(View v) {
        move();
    }

    //ruchy w walce
    protected void Test_at1(View v){
        WriteAnim battleText = (WriteAnim) findViewById(R.id.battleText);
        battleText.setVisibility(View.VISIBLE);
        battleText.setCharacterDelay(30);
        click++;
        EnemyHp = EnemyHp - Dmg;
        battleText.animateText("zadales: "+Dmg+" obrazen!"+newLine+ "Wrogowi zostalo: "+EnemyHp+" punktow zycia!");
        FightAnimMelee();
    }

    //pokazuje lub ukrywa liste ruchow w walce
    private void CheckSkillsFirstRow(){
            Button test_at1 = findViewById(R.id.test_At1);
            test_at1.setText("Melee Attack");
            test_at1.setVisibility(View.VISIBLE);
        }

    private void MoveList(){
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
    private void MoveList_hide(){
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

        Button back = findViewById(R.id.back);
        back.setVisibility(View.GONE);
    }
    protected void Back(View v){
        MoveList_hide();
    }

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

    private void Fight(){
        final Button move = findViewById(R.id.Move);
        move.setEnabled(false);
        Button list = findViewById(R.id.List);
        list.setEnabled(false);
        Button dunno = findViewById(R.id.dunno);
        dunno.setEnabled(false);
        final Button check = findViewById(R.id.Check);
        check.setEnabled(false);
        ImageView map = findViewById(R.id.testbg);
        map.setVisibility(View.GONE);
        ImageView dot = findViewById(R.id.dot);
        dot.setVisibility(View.GONE);

        final WriteAnim battleText = (WriteAnim) findViewById(R.id.battleText);
        battleText.setVisibility(View.VISIBLE);
        battleText.setCharacterDelay(30);
        battleText.animateText("Dobra, najpierw podstawy, sprawdz jego statystyki");
        final android.support.constraint.ConstraintLayout constraintLayout = (android.support.constraint.ConstraintLayout) findViewById(R.id.Screen);
        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                battleText.animateText("Kliknij przycisk check aby zobaczyc szczegoly o danym przeciwniku");
                check.setEnabled(true);
                check.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Check(view);
                        steps++;
                        if(steps > 1){
                            move.setEnabled(true);
                            check.setEnabled(false);
                            battleText.animateText("teraz gdy wiemy o nim wiecej, mozemy go spokojnie zaatakowac, uzyj przycisku attack a nastepnie wybierz cios fizyczny");
                            check.setOnClickListener(null);
                        }
                    }
                });
                constraintLayout.setOnClickListener(null);
            }
        });

        StatsCreate.EnemyStats();
        RestActivity set = new RestActivity();
        set.EnemyStats();
        EnemyHp = 6;
        EnemyDmg = 0;

    }
    private void EndFight() {
        if (EnemyHp <= 0) {
            MoveList_hide();
            click = 1;
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
            BattleWriter();
            SetComplete();
        }
    }

    private void Writer(){
        final WriteAnim writer = (WriteAnim) findViewById(R.id.writeAnim);
        writer.setVisibility(View.VISIBLE);
        //Add a character every
        writer.setCharacterDelay(30);
        writer.animateText("No coz, kukly nie oddaja, zobaczymy co z ciebie bedzie pozniej, a poki co wracajmy");
        final android.support.constraint.ConstraintLayout constraintLayout = (android.support.constraint.ConstraintLayout) findViewById(R.id.Screen);
        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writer.setVisibility(View.GONE);
                constraintLayout.setOnClickListener(null);
                Intent tavern = new Intent(getApplicationContext(), TavernActivity.class);
                startActivity(tavern);
            }
        });
    }
    private void BattleWriter(){
        final WriteAnim battleText = (WriteAnim) findViewById(R.id.battleText);
        battleText.setCharacterDelay(30);
        battleText.animateText("Wrog pokonany!");
        final android.support.constraint.ConstraintLayout constraintLayout = (android.support.constraint.ConstraintLayout) findViewById(R.id.Screen);
        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                battleText.setVisibility(View.GONE);
                Writer();
            }
        });
    }

    private void SetComplete(){
        try {
            File file = new File ("data/data/com.example.nigakolczan.apk2/Stats_"+RestActivity.a+".xml");
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(file);


            NodeList root = doc.getChildNodes();
            Node Stats = getNode("Stats", root);
            Node Chk = getNode("Character", Stats.getChildNodes());

            NodeList list = Chk.getChildNodes();
            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);

                if (("TestGround_pt1").equals(node.getNodeName())) {
                    node.setTextContent("T");
                }
            }
            RestActivity.CheckTG ="T";

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);

            System.out.println("Done");
        } catch (ParserConfigurationException | TransformerException | IOException | SAXException pce) {
            pce.printStackTrace();
        }
    }

}
