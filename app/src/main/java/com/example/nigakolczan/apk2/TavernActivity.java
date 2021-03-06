package com.example.nigakolczan.apk2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class TavernActivity extends AppCompatActivity{

    protected void onCreate(Bundle saveInstance){
        super.onCreate(saveInstance);
        setContentView(R.layout.activity_tavern);
        SetSpellNames();
        Equipment equipment = new Equipment();
        equipment.SetBackpack();
        min =1;
        max = 2;
        if(RestActivity.CheckTG.equals("F")){
            Button dungeon = findViewById(R.id.gotoDungeon);
            dungeon.setEnabled(false);
            Button town = findViewById(R.id.gotoTown);
            town.setEnabled(false);
            WriteAnim text = (WriteAnim) findViewById(R.id.text);
            text.setCharacterDelay(30);
            text.animateText("W takim razie witaj "+RestActivity.input+" ,najpierw przejdziemy krotki trening zanim wydam ci sprzet i wypuszcze w podziemia");
            android.support.constraint.ConstraintLayout constraintLayout = (android.support.constraint.ConstraintLayout) findViewById(R.id.Screen);
            constraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent test = new Intent(getApplicationContext(), TestGroundActivity.class);
                    startActivity(test);
                }
            });
        }else if(RestActivity.CheckTG.equals("T") & RestActivity.CheckTG_2.equals("F")){
            final WriteAnim text = (WriteAnim) findViewById(R.id.text);
            text.setCharacterDelay(30);
            text.animateText("Teraz moge ci wydac twoj sprzet");
            final android.support.constraint.ConstraintLayout constraintLayout = (android.support.constraint.ConstraintLayout) findViewById(R.id.Screen);
            constraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    text.animateText("Tak jak kazdy, dostajesz 100 sztuk zlota oraz podstawowy ekwipunek, mocniejszy sprzet znajdziesz w podziemiach, powodzenia");
                    SetComplete_2();
                    constraintLayout.setOnClickListener(null);
                }
            });
        }else if(RestActivity.CheckTG.equals("T") & RestActivity.CheckTG_2.equals("T")){
            WriteAnim text = (WriteAnim) findViewById(R.id.text);
            text.setCharacterDelay(30);
            text.animateText("Hello There "+RestActivity.input+"!");
        }
    }

    protected static int min;
    protected static int max;
    List<String> Spells = new ArrayList<>();

    protected Node getNode(String tagName, NodeList nodes) {
        for (int x = 0; x < nodes.getLength(); x++) {
            Node node = nodes.item(x);
            if (node.getNodeName().equalsIgnoreCase(tagName)) {
                return node;
            }
        }

        return null;
    }

    protected void SetSpellNames(){
        RestActivity restActivity = new RestActivity();
        for(int i = 0; i<8 ; i++){
            restActivity.getSpells(i);
            Spells.add(restActivity.getSpells(i));
        }
        switch(RestActivity.Resource){
            case "Mana":
                Spells.set(1, "Fireball");
                Spells.set(2, "Ice Shield");
                Spells.set(3, "Frost Shock");
                Spells.set(4, "Mage_Skill_5");
                Spells.set(5, "Mage_Skill_6");
                Spells.set(6, "Mage_Skill_7");
                Spells.set(7, "Mage_Skill_8");
                break;
            case "Rage":
                Spells.set(1, "Unleash Fury");
                Spells.set(2, "Heal Wounds");
                Spells.set(3, "Skull Bash");
                Spells.set(4, "Warrior_Skill_5");
                Spells.set(5, "Warrior_Skill_6");
                Spells.set(6, "Warrior_Skill_7");
                Spells.set(7, "Warrior_Skill_8");
                break;
            case "Energy":
                Spells.set(1, "Backstab");
                Spells.set(2, "First aid");
                Spells.set(3, "Cheap Shot");
                Spells.set(4, "Thief_Skill_5");
                Spells.set(5, "Thief_Skill_6");
                Spells.set(6, "Thief_Skill_7");
                Spells.set(7, "Thief_Skill_8");
                break;

        }
    }
    protected void HideInterface(){
        Button tk = findViewById(R.id.tavernKeeper);
        tk.setVisibility(View.GONE);
        Button town = findViewById(R.id.gotoTown);
        town.setVisibility(View.GONE);
        Button map = findViewById(R.id.gotoDungeon);
        map.setVisibility(View.GONE);
    }
    protected void ShowInterface(){
        Button tk = findViewById(R.id.tavernKeeper);
        tk.setVisibility(View.VISIBLE);
        Button town = findViewById(R.id.gotoTown);
        town.setVisibility(View.VISIBLE);
        Button map = findViewById(R.id.gotoDungeon);
        map.setVisibility(View.VISIBLE);
    }
    protected void HideMapArea(View v){
        Button area_1 = findViewById(R.id.area_1);
        area_1.setVisibility(View.GONE);
        Button area_2 = findViewById(R.id.area_2);
        area_2.setVisibility(View.GONE);
        Button area_3 = findViewById(R.id.area_3);
        area_3.setVisibility(View.GONE);
        Button area_4 = findViewById(R.id.area_4);
        area_4.setVisibility(View.GONE);
        Button back = findViewById(R.id.back);
        back.setVisibility(View.GONE);
        ShowInterface();
    }
    protected void SelectMapArea(View v){
        HideInterface();
        Button area_1 = findViewById(R.id.area_1);
        area_1.setVisibility(View.VISIBLE);
        Button area_2 = findViewById(R.id.area_2);
        area_2.setVisibility(View.VISIBLE);
        area_2.setEnabled(false);
        Button area_3 = findViewById(R.id.area_3);
        area_3.setVisibility(View.VISIBLE);
        area_3.setEnabled(false);
        Button area_4 = findViewById(R.id.area_4);
        area_4.setVisibility(View.VISIBLE);
        area_4.setEnabled(false);
        Button back = findViewById(R.id.back);
        back.setVisibility(View.VISIBLE);
        if(Integer.parseInt(RestActivity.Lvl) >= 3){
            area_2.setEnabled(true);
        }
        if(Integer.parseInt(RestActivity.Lvl) >= 5){
            area_3.setEnabled(true);
        }
        if(Integer.parseInt(RestActivity.Lvl) >= 8){
            area_4.setEnabled(true);
        }
    }
    protected void SelectArea_1(View v){
        min=1;
        max=3;
        GotoDungeon();
    }
    protected void SelectArea_2(View v){
        min=3;
        max=5;
        GotoDungeon();
    }
    protected void SelectArea_3(View v){
        min=5;
        max=7;
        GotoDungeon();
    }
    protected void SelectArea_4(View v){
        min=7;
        max=9;
        GotoDungeon();
    }

    protected void GotoTown(View view){
        Intent Town = new Intent(getApplicationContext(), SkillAddActivity.class);
        startActivity(Town);
    }
    protected void GotoDungeon(){
        Intent work = new Intent(getApplicationContext(), WorkActivity.class);
        startActivity(work);
    }

    private void SetComplete_2(){
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

                if (("TestGround_pt2").equals(node.getNodeName())) {
                    node.setTextContent("T");
                }
                if(("First_eq").equals(node.getNodeName())){
                    node.setTextContent("T");
                }
            }
            RestActivity.CheckTG_2 ="T";
            RestActivity restActivity = new RestActivity();
            restActivity.setFirst_eq("T");

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
