package com.example.nigakolczan.apk2;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

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
    public String getFirst_eq(){
        return First_eq;
    }
    public String setFirst_eq(String a){
        First_eq = a;
        return First_eq;
    }

    public String getSpells(int i){
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

    public String getEquipment(int i){
        try {
            File file = new File("data/data/com.example.nigakolczan.apk2/Stats_"+a+".xml");
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(file);
            NodeList root = doc.getChildNodes();

            //Wszystkie znaczniki w XML
            Node Stats = getNode("Stats", root);
            Node Chk = getNode("Character", Stats.getChildNodes());
            NodeList nodes = Chk.getChildNodes();

            //Wszystkie atrybuty znaczników w XML

            Helm = getNodeValue("Helm",nodes);
            Chest = getNodeValue("Chest",nodes);
            Legs = getNodeValue("Legs",nodes);
            Boots = getNodeValue("Boots",nodes);
            Weapon = getNodeValue("Weapon",nodes);
            Cape = getNodeValue("Cape",nodes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String> eq = new ArrayList<>();
        eq.add(Helm);
        eq.add(Chest);
        eq.add(Legs);
        eq.add(Boots);
        eq.add(Weapon);
        eq.add(Cape);
        return eq.get(i);
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
    public String getNodeValue(String tagName, NodeList nodes) {
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
    public String getNodeAttr(String attrName, Node node) {
        NamedNodeMap attrs = node.getAttributes();
        for (int y = 0; y < attrs.getLength(); y++) {
            Node attr = attrs.item(y);
            if (attr.getNodeName().equalsIgnoreCase(attrName)) {
                return attr.getNodeValue();
            }
        }
        return "";
    }

    public String GetName(){
        EditText edit = (EditText) findViewById(R.id.editText);
        input = edit.getText().toString();
        return input;
    }
    public void CheckExists(){
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

    public void HideAttr(){
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
    public void ShowAttr(){
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
    public void HideInterface(){
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
    public void ShowInterface(){
        EditText editText = findViewById(R.id.editText);
        editText.setVisibility(View.VISIBLE);
        com.example.nigakolczan.apk2.WriteAnim writeAnim = findViewById(R.id.writeStats);
        writeAnim.setVisibility(View.VISIBLE);
        Button start = findViewById(R.id.gotoWork);
        start.setVisibility(View.VISIBLE);
        Button getStats = findViewById(R.id.Gstats);
        getStats.setVisibility(View.VISIBLE);
    }
    public void HideSlots(){
        Button slot1 = findViewById(R.id.saveSlot_1);
        slot1.setVisibility(View.GONE);
        Button slot2 = findViewById(R.id.saveSlot_2);
        slot2.setVisibility(View.GONE);
        Button slot3 = findViewById(R.id.saveSlot_3);
        slot3.setVisibility(View.GONE);
    }
    public void showSlots(){
        Button slot1 = findViewById(R.id.saveSlot_1);
        slot1.setVisibility(View.VISIBLE);
        Button slot2 = findViewById(R.id.saveSlot_2);
        slot2.setVisibility(View.VISIBLE);
        Button slot3 = findViewById(R.id.saveSlot_3);
        slot3.setVisibility(View.VISIBLE);
    }

    public void SelectSlot1(View v){
        HideSlots();
        a=1;
        CheckExists();
        ShowInterface();
    }
    public void SelectSlot2(View v){
        HideSlots();
        a=2;
        CheckExists();
        ShowInterface();
    }
    public void SelectSlot3(View v){
        HideSlots();
        a=3;
        CheckExists();
        ShowInterface();
    }
    public void CreateSave(){
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

    public void Start(View v) {
        CreateSave();
        GetStats(v);
    }
    public void Delete(View v) {
        File file = new File("data/data/com.example.nigakolczan.apk2/Stats_"+a+".xml");
        file.delete();
        ShowAttr();
    }
    public void GetStats(View view) {
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
                playerSpriteStart();
            } else {
                System.out.println("No such acc found");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void backToSelect(View view){
        ImageView imageView = findViewById(R.id.sprite);
        imageView.setVisibility(View.GONE);
        HideInterface();

        showSlots();
    }
    public void EnemyStats(){
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
    public void BossStats(){
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
    public void playerSprite(){
        ImageView img = (ImageView) findViewById(R.id.sprite);
        if(RestActivity.raceType == 1 & RestActivity.profType == 1){
            System.out.println("jestem 1 1");
            img.setBackgroundResource(R.drawable.mage_h);
        }
        if(RestActivity.raceType == 1 & RestActivity.profType == 2){
            System.out.println("jestem 1 2");
            img.setBackgroundResource(R.drawable.warrior_h);
        }
        if(RestActivity.raceType == 1 & RestActivity.profType == 3){
            System.out.println("jestem 1 3");
            img.setBackgroundResource(R.drawable.rogue_h);
        }
        if(RestActivity.raceType == 2 & RestActivity.profType == 1){
            System.out.println("jestem 2 1");
            img.setBackgroundResource(R.drawable.mage_k);
        }
        if(RestActivity.raceType == 2 & RestActivity.profType == 2){
            System.out.println("jestem 2 2 ");
            img.setBackgroundResource(R.drawable.warrior_k);
        }
        if(RestActivity.raceType == 2 & RestActivity.profType == 3){
            System.out.println("jestem 2 3");
            img.setBackgroundResource(R.drawable.rogue_k);
        }
        if(RestActivity.raceType == 3 & RestActivity.profType == 1){
            System.out.println("jestem 3 1");
            img.setBackgroundResource(R.drawable.mage_e);
        }
        if(RestActivity.raceType == 3 & RestActivity.profType == 2){
            System.out.println("jestem 3 2");
            img.setBackgroundResource(R.drawable.warrior_e);
        }
        if(RestActivity.raceType == 3 & RestActivity.profType == 3){
            System.out.println("jestem 3 3");
            img.setBackgroundResource(R.drawable.rogue_e);
        }
        AnimationDrawable frameAnimation = (AnimationDrawable) img.getBackground();
        img.setVisibility(View.VISIBLE);
        frameAnimation.start();

    }
    public void playerSpriteStart(){
        ImageView img = (ImageView) findViewById(R.id.sprite);
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
        img.setVisibility(View.VISIBLE);

    }
    public void UpdateEq(){
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
    public void SelectRaceLeft(View v){
        raceType -= 1;
        if(raceType < 1){
            raceType=3;
        }
        switch (raceType) {
            case 1:
                RaceType = "Czlowiek";
                break;
            case 2:
                RaceType = "Krasnolud";
                break;
            case 3:
                RaceType = "Elf";
                break;
            default:
                RaceType = "err";
                break;
        }
        TextView tv = findViewById(R.id.textViewRace);
        tv.setText(RaceType);
        playerSprite();
    }
    public void SelectRaceRight(View v){
        raceType += 1;
        if(raceType > 3){
            raceType=1;
        }
        switch (raceType) {
            case 1:
                RaceType = "Czlowiek";
                break;
            case 2:
                RaceType = "Krasnolud";
                break;
            case 3:
                RaceType = "Elf";
                break;
            default:
                RaceType = "err";
                break;
        }
        TextView tv = findViewById(R.id.textViewRace);
        tv.setText(RaceType);
        playerSprite();
    }
    public void SelectProfLeft(View v){
        profType -= 1;
        if(profType < 1){
            profType =3;
        }
        switch (profType) {
            case 1:
                ProfType = "Mag";
                Resource = "Mana";
                break;
            case 2:
                ProfType = "Wojownik";
                Resource = "Rage";
                break;
            case 3:
                ProfType = "Lotr";
                Resource = "Energy";
                break;
            default:
                ProfType = "err";
                break;
        }
        TextView tv = findViewById(R.id.textViewProf);
        tv.setText(ProfType);
        playerSprite();
    }
    public void SelectProfRight(View v){
        profType += 1;
        if(profType > 3){
            profType=1;
        }
        switch (profType) {
            case 1:
                ProfType = "Mag";
                Resource = "Mana";
                break;
            case 2:
                ProfType = "Wojownik";
                Resource = "Rage";
                break;
            case 3:
                ProfType = "Lotr";
                Resource = "Energy";
                break;
            default:
                ProfType = "err";
                break;
        }
        TextView tv = findViewById(R.id.textViewProf);
        tv.setText(ProfType);
        playerSprite();
    }


}
