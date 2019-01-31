package com.example.nigakolczan.apk2;

import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Created by NigaKolczan on 2018-08-16.
 */

public class StatsCreate extends AppCompatActivity {
    protected static int EnemyLvlVar;
    protected static int EnemyNameVar;
    protected static int money;
    protected static int experience;
    protected static int bossLvlVar;
    protected static int bossNameVar;
    protected static String Input;
    protected static String Rasa;
    protected static String Klasa;
    protected static String Resource;

    public Node getNode(String tagName, NodeList nodes) {
        for (int x = 0; x < nodes.getLength(); x++) {
            Node node = nodes.item(x);
            if (node.getNodeName().equalsIgnoreCase(tagName)) {
                return node;
            }
        }

        return null;
    }

    //pobiera statystyki przeciwnikow
    public static int GetMoney(){
        money = EnemyLvlVar *10;
        return money;
    }
    public static int GetBossMoney(){
        money = bossLvlVar *20;
        return money;
    }
    public static int GetBossExperience(){
        experience = bossLvlVar*10;
        return experience;
    }
    public static int GetExperience(){
        experience = EnemyLvlVar *5;
        return experience;
    }
    public static int GetEnemyLvlVar(){
        EnemyLvlVar = ThreadLocalRandom.current().nextInt(TavernActivity.min, TavernActivity.max);
        return EnemyLvlVar;
    }
    public static int GetEnemyNameVar(){
        EnemyNameVar = ThreadLocalRandom.current().nextInt(1, 6);
        return EnemyNameVar;
    }
    /*public static String GetEnemyLvl(){
        String EnemyLvl;
        switch (EnemyLvlVar) {
            case 1:
                EnemyLvl = "1";
                break;
            case 2:
                EnemyLvl = "2";
                break;
            case 3:
                EnemyLvl = "3";
                break;
            case 4:
                EnemyLvl = "4";
                break;
            case 5:
                EnemyLvl = "5";
                break;
            default:
                EnemyLvl = "100";
                break;
        }
        return EnemyLvl;
    }*/
    public static String GetEnemyName(){
        String EnemyNames;
        switch (EnemyNameVar) {
            case 1:
                EnemyNames = "Test_1";
                break;
            case 2:
                EnemyNames = "Test_2";
                break;
            case 3:
                EnemyNames = "Test_3";
                break;
            case 4:
                EnemyNames = "Test_4";
                break;
            case 5:
                EnemyNames = "Test_5";
                break;
            default:
                EnemyNames = "err";
                break;
        }
        return EnemyNames;
    }
    public static int GetBossLvlVar(){
        bossLvlVar = ThreadLocalRandom.current().nextInt(TavernActivity.bossMin, TavernActivity.bossMax);
        return bossLvlVar;
    }
    public static int GetBossNameVar(){
        bossNameVar = ThreadLocalRandom.current().nextInt(1,6);
        return bossNameVar;
    }
    public static String GetBossName(){
        String bossNames;
        switch (bossNameVar) {
            case 1:
                bossNames = "Boss_1";
                break;
            case 2:
                bossNames = "Boss_2";
                break;
            case 3:
                bossNames = "Boss_3";
                break;
            case 4:
                bossNames = "Boss_4";
                break;
            case 5:
                bossNames = "Boss_5";
                break;
            default:
                bossNames = "err";
                break;
        }
        return bossNames;
    }

    //Pobiera wybrane nick rase i klase
    public String GetInput(){
        Input = RestActivity.input;
        return Input;
    }
    public String GetRace(){
        Rasa = RestActivity.RaceType;
        return Rasa;
    }
    public String GetProf(){
        Klasa = RestActivity.ProfType;
        return Klasa;
    }
    public String GetResource(){
        Resource = RestActivity.Resource;
        return Resource;
    }

    //Tworzy pliki z zapisem
    public void Stats() {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            // root elements
            Document doc = docBuilder.newDocument();
            GetInput();
            GetRace();
            GetProf();
            GetResource();
            Element rootElement = doc.createElement("Stats");
            doc.appendChild(rootElement);

            // Postac
            Element character = doc.createElement("Character");
            rootElement.appendChild(character);

            // Jej poziom
            Attr attr = doc.createAttribute("Lvl");
            attr.setValue("5");
            character.setAttributeNode(attr);


            // Nazwa
            Element nickName = doc.createElement("NickName");
            nickName.appendChild(doc.createTextNode(Input));
            character.appendChild(nickName);


            // Rasa
            Element race = doc.createElement("Race");
            race.appendChild(doc.createTextNode(Rasa));
            character.appendChild(race);


            // Profesja
            Element prof = doc.createElement("Profession");
            prof.appendChild(doc.createTextNode(Klasa));
            character.appendChild(prof);

            // Sprawdza testground
            Element TestGround_pt1 = doc.createElement("TestGround_pt1");
            TestGround_pt1.appendChild(doc.createTextNode("F"));
            character.appendChild(TestGround_pt1);

            Element TestGround_pt2 = doc.createElement("TestGround_pt2");
            TestGround_pt2.appendChild(doc.createTextNode("F"));
            character.appendChild(TestGround_pt2);

            //Sekcja Umiejek
            Element skill_1 = doc.createElement("Skill_1");
            skill_1.appendChild(doc.createTextNode("T"));
            character.appendChild(skill_1);

            Element skill_2 = doc.createElement("Skill_2");
            skill_2.appendChild(doc.createTextNode("F"));
            character.appendChild(skill_2);

            Element skill_3 = doc.createElement("Skill_3");
            skill_3.appendChild(doc.createTextNode("F"));
            character.appendChild(skill_3);

            Element skill_4 = doc.createElement("Skill_4");
            skill_4.appendChild(doc.createTextNode("F"));
            character.appendChild(skill_4);

            Element skill_5 = doc.createElement("Skill_5");
            skill_5.appendChild(doc.createTextNode("F"));
            character.appendChild(skill_5);

            Element skill_6 = doc.createElement("Skill_6");
            skill_6.appendChild(doc.createTextNode("F"));
            character.appendChild(skill_6);

            Element skill_7 = doc.createElement("Skill_7");
            skill_7.appendChild(doc.createTextNode("F"));
            character.appendChild(skill_7);

            Element skill_8 = doc.createElement("Skill_8");
            skill_8.appendChild(doc.createTextNode("F"));
            character.appendChild(skill_8);


            //Eq
            Element helm = doc.createElement("helm");
            helm.appendChild(doc.createTextNode("First"));
            character.appendChild(helm);

            Element chest = doc.createElement("chest");
            chest.appendChild(doc.createTextNode("First"));
            character.appendChild(chest);

            Element legs = doc.createElement("legs");
            legs.appendChild(doc.createTextNode("First"));
            character.appendChild(legs);

            Element boots = doc.createElement("boots");
            boots.appendChild(doc.createTextNode("First"));
            character.appendChild(boots);

            Element weapon = doc.createElement("weapon");
            weapon.appendChild(doc.createTextNode("First"));
            character.appendChild(weapon);

            Element cape = doc.createElement("cape");
            cape.appendChild(doc.createTextNode("First"));
            character.appendChild(cape);

            Element first_eq = doc.createElement("first_eq");
            first_eq.appendChild(doc.createTextNode("F"));
            character.appendChild(first_eq);

            // Zloto
            Element gold = doc.createElement("Gold");
            character.appendChild(gold);

            Attr shekles = doc.createAttribute("Shekles");
            shekles.setValue("1000");
            gold.setAttributeNode(shekles);

            // Ilosc doswiadczenia
            Element Experience = doc.createElement("Experience");
            character.appendChild(Experience);

            Element backpack = doc.createElement("Backpack");
            rootElement.appendChild(backpack);

            Element item_0 = doc.createElement("item_0");
            item_0.appendChild(doc.createTextNode("a"));
            backpack.appendChild(item_0);

            Element item_1 = doc.createElement("item_1");
            item_1.appendChild(doc.createTextNode("a"));
            backpack.appendChild(item_1);

            Element item_2 = doc.createElement("item_2");
            item_2.appendChild(doc.createTextNode("a"));
            backpack.appendChild(item_2);

            Element item_3 = doc.createElement("item_3");
            item_3.appendChild(doc.createTextNode("a"));
            backpack.appendChild(item_3);

            Element item_4 = doc.createElement("item_4");
            item_4.appendChild(doc.createTextNode("a"));
            backpack.appendChild(item_4);

            Element item_5 = doc.createElement("item_5");
            item_5.appendChild(doc.createTextNode("a"));
            backpack.appendChild(item_5);

            Element item_6 = doc.createElement("item_6");
            item_6.appendChild(doc.createTextNode("a"));
            backpack.appendChild(item_6);

            Element item_7 = doc.createElement("item_7");
            item_7.appendChild(doc.createTextNode("a"));
            backpack.appendChild(item_7);

            Element item_8 = doc.createElement("item_8");
            item_8.appendChild(doc.createTextNode("a"));
            backpack.appendChild(item_8);

            Element item_9 = doc.createElement("item_9");
            item_9.appendChild(doc.createTextNode("a"));
            backpack.appendChild(item_9);

            Attr exp = doc.createAttribute("Exp");
            exp.setValue("0");
            Experience.setAttributeNode(exp);

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("data/data/com.example.nigakolczan.apk2/Stats_"+RestActivity.a+".xml"));

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);

            transformer.transform(source, result);

            System.out.println("File saved!");

        } catch (ParserConfigurationException | TransformerException pce) {
            pce.printStackTrace();
        }
    }
    public void CreateResource(){
        try {
            File file = new File ("data/data/com.example.nigakolczan.apk2/Stats_"+RestActivity.a+".xml");
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(file);

            NodeList root = doc.getChildNodes();
            Node Stats = getNode("Stats", root);
            Node Chk = getNode("Character", Stats.getChildNodes());

            Element res = doc.createElement("Resource");
            res.appendChild(doc.createTextNode(RestActivity.Resource));
            Chk.appendChild(res);

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
    //zrobic statystyki dla bossa
    public static void EnemyStats() {
        try {
            GetEnemyNameVar();
            GetEnemyLvlVar();
            String Money = Integer.toString(GetMoney());
            String Exp = Integer.toString(GetExperience());
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();

            Element rootElement = doc.createElement("Stats");
            doc.appendChild(rootElement);

            // Postac
            Element enemyCharacter = doc.createElement("EnemyCharacter");
            rootElement.appendChild(enemyCharacter);

            // Jej poziom
            Attr attr = doc.createAttribute("Lvl");
            attr.setValue(Integer.toString(EnemyLvlVar));
            enemyCharacter.setAttributeNode(attr);


            // shorten way
            // staff.setAttribute("id", "1");

            // Nazwa
            Element nickName = doc.createElement("Enemy");
            nickName.appendChild(doc.createTextNode(Integer.toString(EnemyNameVar)));
            enemyCharacter.appendChild(nickName);


            // Zloto
            Element gold = doc.createElement("Gold");
            enemyCharacter.appendChild(gold);

            Attr shekles = doc.createAttribute("Shekles");
            shekles.setValue(Money);
            gold.setAttributeNode(shekles);

            // Ilosc doswiadczenia
            Element Experience = doc.createElement("Experience");
            enemyCharacter.appendChild(Experience);

            Attr exp = doc.createAttribute("Exp");
            exp.setValue(Exp);
            Experience.setAttributeNode(exp);

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("data/data/com.example.nigakolczan.apk2/EnemyStats.xml"));

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);

            transformer.transform(source, result);

        } catch (ParserConfigurationException | TransformerException pce) {
            pce.printStackTrace();
        }
    }
    public static void BossStats() {
        try {
            GetBossNameVar();
            GetBossLvlVar();
            String Money = Integer.toString(GetBossMoney());
            String Exp = Integer.toString(GetBossExperience());
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();

            Element rootElement = doc.createElement("Stats");
            doc.appendChild(rootElement);

            // Postac
            Element enemyCharacter = doc.createElement("BossCharacter");
            rootElement.appendChild(enemyCharacter);

            // Jej poziom
            Attr attr = doc.createAttribute("Lvl");
            attr.setValue(Integer.toString(bossLvlVar));
            enemyCharacter.setAttributeNode(attr);


            // shorten way
            // staff.setAttribute("id", "1");

            // Nazwa
            Element nickName = doc.createElement("Enemy");
            nickName.appendChild(doc.createTextNode(Integer.toString(bossNameVar)));
            enemyCharacter.appendChild(nickName);


            // Zloto
            Element gold = doc.createElement("Gold");
            enemyCharacter.appendChild(gold);

            Attr shekles = doc.createAttribute("Shekles");
            shekles.setValue(Money);
            gold.setAttributeNode(shekles);

            // Ilosc doswiadczenia
            Element Experience = doc.createElement("Experience");
            enemyCharacter.appendChild(Experience);

            Attr exp = doc.createAttribute("Exp");
            exp.setValue(Exp);
            Experience.setAttributeNode(exp);

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("data/data/com.example.nigakolczan.apk2/BossStats.xml"));

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);

            transformer.transform(source, result);

        } catch (ParserConfigurationException | TransformerException pce) {
            pce.printStackTrace();
        }
    }
}

