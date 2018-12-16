package com.example.nigakolczan.apk2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
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

/**
 * Created by NigaKolczan on 2018-08-12.
 */

public class RestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest);
    }

    protected static int raceType = 1;
    protected static int profType = 1;
    protected static String RaceType;
    protected static String ProfType;
    protected static String NickName;
    protected static String Rasa;
    protected static String Klasa;
    protected static String Resource;
    protected static String Lvl;
    protected static String Experience;
    protected static String Shekles;
    private static String First_eq;
    protected static String CheckTG;
    protected static String CheckTG_2;
    private static String Helm;
    private static String Chest;
    private static String Legs;
    private static String Boots;
    private static String Weapon;
    private static String Cape;
    protected static String EnemyName;
    protected static String EnemyLvl;
    protected static String bossName;
    protected static String bossLvl;
    protected static String input;
    protected static int a;

    List<String> spells = new ArrayList<>();
    protected String getFirst_eq(){
        return First_eq;
    }
    protected String setFirst_eq(String a){
        First_eq = a;
        return First_eq;
    }

    protected String getSpells(int i){
        try{
            File file = new File("data/data/com.example.nigakolczan.apk2/Stats_"+a+".xml");
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(file);
            NodeList root = doc.getChildNodes();

            //Wszystkie znaczniki w XML
            Node Stats = getNode("Stats", root);
            Node Chk = getNode("Character", Stats.getChildNodes());

            NodeList list = Chk.getChildNodes();
            int test = 1;
            for(int j = 5; j <= 13; j++){
                Node node = list.item(j);
                if(("Skill_"+test).equals(node.getNodeName())){
                    test++;
                    spells.add(node.getTextContent());
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return spells.get(i);
    }

    protected String getEquipment(int i){
        List<String> eq = new ArrayList<>();
        eq.add(Helm);
        eq.add(Chest);
        eq.add(Legs);
        eq.add(Boots);
        eq.add(Weapon);
        eq.add(Cape);
        return eq.get(i);
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
    protected String getNodeValue(String tagName, NodeList nodes) {
        for (int x = 0; x < nodes.getLength(); x++) {
            Node node = nodes.item(x);
            if (node.getNodeName().equalsIgnoreCase(tagName)) {
                NodeList childNodes = node.getChildNodes();
                for (int y = 0; y < childNodes.getLength(); y++) {
                    Node data = childNodes.item(y);
                    if (data.getNodeType() == Node.TEXT_NODE)
                        return data.getNodeValue();
                }
            }
        }
        return "";
    }
    protected String getNodeAttr(String attrName, Node node) {
        NamedNodeMap attrs = node.getAttributes();
        for (int y = 0; y < attrs.getLength(); y++) {
            Node attr = attrs.item(y);
            if (attr.getNodeName().equalsIgnoreCase(attrName)) {
                return attr.getNodeValue();
            }
        }
        return "";
    }

    protected String GetName(){
        EditText edit = (EditText) findViewById(R.id.editText);
        input = edit.getText().toString();
        return input;
    }
    protected void CheckExists(){
        try {
            File file = new File("data/data/com.example.nigakolczan.apk2/Stats_"+a+".xml");
            GetName();
            Boolean exists = file.exists();
            if(!exists){
                ShowAttr();
            }
    }catch (Exception e) {
            e.printStackTrace();
        }}

    protected void HideAttr(){
        TextView tvp = findViewById(R.id.textViewProf);
        tvp.setVisibility(View.GONE);
        TextView tvr = findViewById(R.id.textViewRace);
        tvr.setVisibility(View.GONE);
        Button spl = findViewById(R.id.selectProfLeft);
        spl.setVisibility(View.GONE);
        Button spr = findViewById(R.id.selectProfRight);
        spr.setVisibility(View.GONE);
        Button srl = findViewById(R.id.selectRaceLeft);
        srl.setVisibility(View.GONE);
        Button srp = findViewById(R.id.selectRaceRight);
        srp.setVisibility(View.GONE);
    }
    protected void ShowAttr(){
        TextView tvp = findViewById(R.id.textViewProf);
        tvp.setVisibility(View.VISIBLE);
        TextView tvr = findViewById(R.id.textViewRace);
        tvr.setVisibility(View.VISIBLE);
        Button spl = findViewById(R.id.selectProfLeft);
        spl.setVisibility(View.VISIBLE);
        Button spr = findViewById(R.id.selectProfRight);
        spr.setVisibility(View.VISIBLE);
        Button srl = findViewById(R.id.selectRaceLeft);
        srl.setVisibility(View.VISIBLE);
        Button srp = findViewById(R.id.selectRaceRight);
        srp.setVisibility(View.VISIBLE);
    }
    protected void HideInterface(){
        HideAttr();
        EditText editText = findViewById(R.id.editText);
        editText.setVisibility(View.GONE);
        com.example.nigakolczan.apk2.WriteAnim writeAnim = findViewById(R.id.writeStats);
        writeAnim.setVisibility(View.GONE);
        Button start = findViewById(R.id.gotoWork);
        start.setVisibility(View.GONE);
        Button getStats = findViewById(R.id.Gstats);
        getStats.setVisibility(View.GONE);
        Button delete = findViewById(R.id.Dstats);
        delete.setVisibility(View.GONE);
    }
    protected void ShowInterface(){
        EditText editText = findViewById(R.id.editText);
        editText.setVisibility(View.VISIBLE);
        com.example.nigakolczan.apk2.WriteAnim writeAnim = findViewById(R.id.writeStats);
        writeAnim.setVisibility(View.VISIBLE);
        Button start = findViewById(R.id.gotoWork);
        start.setVisibility(View.VISIBLE);
        Button getStats = findViewById(R.id.Gstats);
        getStats.setVisibility(View.VISIBLE);
    }
    protected void HideSlots(){
        Button slot1 = findViewById(R.id.saveSlot_1);
        slot1.setVisibility(View.GONE);
        Button slot2 = findViewById(R.id.saveSlot_2);
        slot2.setVisibility(View.GONE);
        Button slot3 = findViewById(R.id.saveSlot_3);
        slot3.setVisibility(View.GONE);
    }

    protected void SelectSlot1(View v){
        HideSlots();
        a=1;
        CheckExists();
        ShowInterface();
    }
    protected void SelectSlot2(View v){
        HideSlots();
        a=2;
        CheckExists();
        ShowInterface();
    }
    protected void SelectSlot3(View v){
        HideSlots();
        a=3;
        CheckExists();
        ShowInterface();
    }
    protected void CreateSave(){
        try {
            File file = new File("data/data/com.example.nigakolczan.apk2/Stats_"+a+".xml");
            GetName();
            Boolean exists = file.exists();
            Boolean equals = input.equals(NickName);
            if (!exists) {
                StatsCreate statsCreate = new StatsCreate();
                statsCreate.Stats();
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                Document doc = docBuilder.parse(file);
                NodeList root = doc.getChildNodes();

                Node Stats = getNode("Stats", root);
                Node Chk = getNode("Character", Stats.getChildNodes());
                NodeList nodes = Chk.getChildNodes();
                NickName = getNodeValue("NickName", nodes);
                statsCreate.CreateResource();
                HideAttr();
            } else if (exists & equals) {
                Button delete = findViewById(R.id.Dstats);
                delete.setVisibility(View.VISIBLE);
                final Button start = findViewById(R.id.gotoWork);
                start.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent Tavern = new Intent(getApplicationContext(), TavernActivity.class);
                        startActivity(Tavern);
                        start.setOnClickListener(null);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void Start(View v) {
        CreateSave();
        GetStats(v);

    }
    protected void Delete(View v) {
        File file = new File("data/data/com.example.nigakolczan.apk2/Stats_"+a+".xml");
        file.delete();
        ShowAttr();
    }
    protected void GetStats(View v) {
        try {
            File file = new File("data/data/com.example.nigakolczan.apk2/Stats_"+a+".xml");
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(file);
            NodeList root = doc.getChildNodes();
            GetName();

            //Wszystkie znaczniki w XML
            Node Stats = getNode("Stats", root);
            Node Chk = getNode("Character", Stats.getChildNodes());
            Node Gold = getNode("Gold", Chk.getChildNodes());
            Node Exp = getNode("Experience", Chk.getChildNodes());

            NodeList nodes = Chk.getChildNodes();

            //Wszystkie atrybuty znaczników w XML
            Rasa = getNodeValue("Race",nodes);
            Klasa = getNodeValue("Profession",nodes);
            Resource = getNodeValue("Resource",nodes);
            NickName = getNodeValue("NickName", nodes);
            Lvl = getNodeAttr("Lvl", Chk);
            Shekles = getNodeAttr("Shekles", Gold);
            Experience = getNodeAttr("Exp", Exp);

            CheckTG = getNodeValue("TestGround_pt1",nodes);
            CheckTG_2 = getNodeValue("TestGround_pt2",nodes);
            Helm = getNodeValue("Helm",nodes);
            Chest = getNodeValue("Chest",nodes);
            Legs = getNodeValue("Legs",nodes);
            Boots = getNodeValue("Boots",nodes);
            Weapon = getNodeValue("Weapon",nodes);
            Cape = getNodeValue("Cape",nodes);
            First_eq = getNodeValue("first_eq",nodes);
            Boolean equals = input.equals(NickName);
            if (equals) {
                String newLine = System.getProperty("line.separator");
                WriteAnim writeStats = (WriteAnim) findViewById(R.id.writeStats);
                writeStats.setCharacterDelay(30);
                writeStats.animateText("Nickname: "+NickName +newLine+ "Lvl: "+Lvl +newLine+ "Race: "+Rasa +newLine+ "Profession: "+Klasa +newLine+ "Gold: "+Shekles +newLine+ "Exp: "+Experience);
            } else {
                System.out.println("No such acc found");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected void EnemyStats(){
        try {
            File file = new File("data/data/com.example.nigakolczan.apk2/EnemyStats.xml");
            Boolean exists = file.exists();
            if(exists){
                file.delete();
                File filetemp = new File("data/data/com.example.nigakolczan.apk2/EnemyStats.xml");
            }
            EnemyName = StatsCreate.GetEnemyName();
            EnemyLvl =Integer.toString(StatsCreate.GetEnemyLvlVar());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected void BossStats(){
        try {
            File file = new File("data/data/com.example.nigakolczan.apk2/BossStats.xml");
            Boolean exists = file.exists();
            if(exists){
                file.delete();
                File filetemp = new File("data/data/com.example.nigakolczan.apk2/BossStats.xml");
            }
            bossName = StatsCreate.GetBossName();
            bossLvl =Integer.toString(StatsCreate.GetBossLvlVar());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void UpdateEq(){
        try {
            File file = new File("data/data/com.example.nigakolczan.apk2/Stats_"+RestActivity.a+".xml");
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(file);
            NodeList root = doc.getChildNodes();

            //Wszystkie znaczniki w XML
            Node Stats = getNode("Stats", root);
            Node Chk = getNode("Character", Stats.getChildNodes());
            NodeList nodes = Chk.getChildNodes();

            //Wszystkie atrybuty znaczników w XML
            RestActivity.Helm = getNodeValue("Helm",nodes);
            RestActivity.Chest = getNodeValue("Chest",nodes);
            RestActivity.Legs = getNodeValue("Legs",nodes);
            RestActivity.Boots = getNodeValue("Boots",nodes);
            RestActivity.Weapon = getNodeValue("Weapon",nodes);
            RestActivity.Cape = getNodeValue("Cape",nodes);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //interface do wyboru rasy i klasy w menu
    protected void SelectRaceLeft(View v){
        raceType -= 1;
        if(raceType < 1){
            raceType=4;
        }
        switch (raceType) {
            case 1:
                RaceType = "Husky";
                break;
            case 2:
                RaceType = "Corgi";
                break;
            case 3:
                RaceType = "G_Sheperd";
                break;
            case 4:
                RaceType = "Bulldog";
                break;
            default:
                RaceType = "err";
                break;
        }
        TextView tv = findViewById(R.id.textViewRace);
        tv.setText(RaceType);
    }
    protected void SelectRaceRight(View v){
        raceType += 1;
        if(raceType > 4){
            raceType=1;
        }
        switch (raceType) {
            case 1:
                RaceType = "Husky";
                break;
            case 2:
                RaceType = "Corgi";
                break;
            case 3:
                RaceType = "G_Sheperd";
                break;
            case 4:
                RaceType = "Bulldog";
                break;
            default:
                RaceType = "err";
                break;
        }
        TextView tv = findViewById(R.id.textViewRace);
        tv.setText(RaceType);
    }
    protected void SelectProfLeft(View v){
        profType -= 1;
        if(profType < 1){
            profType =3;
        }
        switch (profType) {
            case 1:
                ProfType = "Mage";
                Resource = "Mana";
                break;
            case 2:
                ProfType = "Warrior";
                Resource = "Rage";
                break;
            case 3:
                ProfType = "Thief";
                Resource = "Energy";
                break;
            default:
                ProfType = "err";
                break;
        }
        TextView tv = findViewById(R.id.textViewProf);
        tv.setText(ProfType);
    }
    protected void SelectProfRight(View v){
        profType += 1;
        if(profType > 3){
            profType=1;
        }
        switch (profType) {
            case 1:
                ProfType = "Mage";
                Resource = "Mana";
                break;
            case 2:
                ProfType = "Warrior";
                Resource = "Rage";
                break;
            case 3:
                ProfType = "Thief";
                Resource = "Energy";
                break;
            default:
                ProfType = "err";
                break;
        }
        TextView tv = findViewById(R.id.textViewProf);
        tv.setText(ProfType);
    }

}
