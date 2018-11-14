package com.example.nigakolczan.apk2;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

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

public class SkillAddActivity extends AppCompatActivity implements View.OnClickListener{

    protected void onCreate(Bundle saveInstance){
        super.onCreate(saveInstance);
        setContentView(R.layout.activity_skilladd);

        WriteAnim text = (WriteAnim) findViewById(R.id.text);
        text.setCharacterDelay(30);
        text.animateText("Henlo, What can i do for you?");
        ShowMoney();
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
    Equipment equipment = new Equipment();

    private int a;
    private int needmoney;
    private int needlvl;

    private String tempName;
    private String tempType;
    private String item;
    private int b;
    private Boolean buyed = false;
    private Boolean sold = false;
    private Boolean choise = false;

    //Skille
    protected void HideSkillButtons(){
        Button addSkill_2 = findViewById(R.id.addSkill_2);
        addSkill_2.setVisibility(View.GONE);
        Button addSkill_3 = findViewById(R.id.addSkill_3);
        addSkill_3.setVisibility(View.GONE);
        Button addSkill_4 = findViewById(R.id.addSkill_4);
        addSkill_4.setVisibility(View.GONE);
        Button addSkill_5 = findViewById(R.id.addSkill_5);
        addSkill_5.setVisibility(View.GONE);
        Button addSkill_6 = findViewById(R.id.addSkill_6);
        addSkill_6.setVisibility(View.GONE);
        Button addSkill_7 = findViewById(R.id.addSkill_7);
        addSkill_7.setVisibility(View.GONE);
    }
    protected void ShowMoney(){
        TextView m = findViewById(R.id.money);
        m.setText("Szekle: "+WorkActivity.Shekles);
    }

    protected void ShowYesNoButtons(){
        Button yes = findViewById(R.id.yes);
        yes.setVisibility(View.VISIBLE);
        Button no = findViewById(R.id.no);
        no.setVisibility(View.VISIBLE);
    }
    protected void HideYesNoButtons(){
        Button yes = findViewById(R.id.yes);
        yes.setVisibility(View.GONE);
        Button no = findViewById(R.id.no);
        no.setVisibility(View.GONE);
    }

    protected void ShowSellButtons(){
        ImageButton item_up = findViewById(R.id.up_page);
        item_up.setVisibility(View.VISIBLE);
        ImageButton item_3 = findViewById(R.id.item_3);
        item_3.setVisibility(View.VISIBLE);
        ImageButton item_2 = findViewById(R.id.item_2);
        item_2.setVisibility(View.VISIBLE);
        ImageButton item_1 = findViewById(R.id.item_1);
        item_1.setVisibility(View.VISIBLE);
        ImageButton item_down = findViewById(R.id.down_page);
        item_down.setVisibility(View.VISIBLE);
    }
    protected void HideSellButtons(){
        ImageButton item_up = findViewById(R.id.up_page);
        item_up.setVisibility(View.GONE);
        ImageButton item_3 = findViewById(R.id.item_3);
        item_3.setVisibility(View.GONE);
        ImageButton item_2 = findViewById(R.id.item_2);
        item_2.setVisibility(View.GONE);
        ImageButton item_1 = findViewById(R.id.item_1);
        item_1.setVisibility(View.GONE);
        ImageButton item_down = findViewById(R.id.down_page);
        item_down.setVisibility(View.GONE);
    }

    protected void HideBuyButtons(){
        Button buyHelm = findViewById(R.id.buyHelm);
        buyHelm.setVisibility(View.GONE);
        Button buyChest = findViewById(R.id.buyChest);
        buyChest.setVisibility(View.GONE);
        Button buyLegs = findViewById(R.id.buyLegs);
        buyLegs.setVisibility(View.GONE);
        Button buyBoots = findViewById(R.id.buyBoots);
        buyBoots.setVisibility(View.GONE);
        Button buyWeapon = findViewById(R.id.buyWeapon);
        buyWeapon.setVisibility(View.GONE);
        Button buyCape = findViewById(R.id.buyCape);
        buyCape.setVisibility(View.GONE);
    }
    protected void ShowBuyButtons(){
        Button buyHelm = findViewById(R.id.buyHelm);
        buyHelm.setVisibility(View.VISIBLE);
        buyHelm.setOnClickListener(this);
        Button buyChest = findViewById(R.id.buyChest);
        buyChest.setVisibility(View.VISIBLE);
        buyChest.setOnClickListener(this);
        Button buyLegs = findViewById(R.id.buyLegs);
        buyLegs.setVisibility(View.VISIBLE);
        buyLegs.setOnClickListener(this);
        Button buyBoots = findViewById(R.id.buyBoots);
        buyBoots.setVisibility(View.VISIBLE);
        buyBoots.setOnClickListener(this);
        Button buyWeapon = findViewById(R.id.buyWeapon);
        buyWeapon.setVisibility(View.VISIBLE);
        buyWeapon.setOnClickListener(this);
        Button buyCape = findViewById(R.id.buyCape);
        buyCape.setVisibility(View.VISIBLE);
        buyCape.setOnClickListener(this);
    }

    protected void ShowWhichButtons(){
        Button buyFirst = findViewById(R.id.buyFirst);
        buyFirst.setVisibility(View.VISIBLE);
        Button buySecond = findViewById(R.id.buySecond);
        buySecond.setVisibility(View.VISIBLE);
        Button buyThird = findViewById(R.id.buyThird);
        buyThird.setVisibility(View.VISIBLE);
    }
    protected void HideWhichButtons(){
        Button buyFirst = findViewById(R.id.buyFirst);
        buyFirst.setVisibility(View.GONE);
        Button buySecond = findViewById(R.id.buySecond);
        buySecond.setVisibility(View.GONE);
        Button buyThird = findViewById(R.id.buyThird);
        buyThird.setVisibility(View.GONE);
    }

    protected void HideItemButtons(){
        Button buyHp = findViewById(R.id.buyHp);
        buyHp.setVisibility(View.GONE);
        Button buyRes = findViewById(R.id.buyResource);
        buyRes.setVisibility(View.GONE);
    }

    protected void BuySkill(View v){
        WriteAnim text = (WriteAnim) findViewById(R.id.text);
        text.setCharacterDelay(30);
        text.animateText("Ktore spelle chcesz?");
        Button addSkill_2 = findViewById(R.id.addSkill_2);
        addSkill_2.setVisibility(View.VISIBLE);
        addSkill_2.setOnClickListener(this);
        Button addSkill_3 = findViewById(R.id.addSkill_3);
        addSkill_3.setVisibility(View.VISIBLE);
        addSkill_3.setOnClickListener(this);
        Button addSkill_4 = findViewById(R.id.addSkill_4);
        addSkill_4.setVisibility(View.VISIBLE);
        addSkill_4.setOnClickListener(this);
        Button addSkill_5 = findViewById(R.id.addSkill_5);
        addSkill_5.setVisibility(View.VISIBLE);
        addSkill_5.setOnClickListener(this);
        Button addSkill_6 = findViewById(R.id.addSkill_6);
        addSkill_6.setVisibility(View.VISIBLE);
        addSkill_6.setOnClickListener(this);
        Button addSkill_7 = findViewById(R.id.addSkill_7);
        addSkill_7.setVisibility(View.VISIBLE);
        addSkill_7.setOnClickListener(this);
    }
    protected void AddSkill(){
        final WriteAnim text = (WriteAnim) findViewById(R.id.text);
        text.setCharacterDelay(30);
        text.animateText("czy masz lvl "+needlvl+" i "+needmoney+" g?");
        HideSkillButtons();
        ShowYesNoButtons();
        final Button yes = findViewById(R.id.yes);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(WorkActivity.Shekles >= needmoney & WorkActivity.Lvl >= needlvl){
                    WorkActivity.Shekles -=needmoney;
                    UpdateStats();
                    text.animateText("prosze bardzo, cos jeszcze?");
                    HideSkillButtons();
                    HideYesNoButtons();
                    ShowMoney();
                    yes.setOnClickListener(null);
                }else{
                    text.animateText("nie ladnie tak klamac");
                    HideSkillButtons();
                    HideYesNoButtons();
                    yes.setOnClickListener(null);
                }
            }
        });
        final Button no = findViewById(R.id.no);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text.animateText("W takim razie odejdz...");
                HideSkillButtons();
                HideYesNoButtons();
                no.setOnClickListener(null);
            }
        });
    }

    protected void BuyEq(View v){
        WriteAnim text = (WriteAnim) findViewById(R.id.text);
        text.setCharacterDelay(30);
        text.animateText("Co chcialbys kupic?");
        ShowBuyButtons();
    }
    protected void AddEq(){
        final WriteAnim text = (WriteAnim) findViewById(R.id.text);
        text.setCharacterDelay(30);
        text.animateText("czy masz lvl "+needlvl+" i "+needmoney+" g?");
        HideWhichButtons();
        ShowYesNoButtons();
        final Button yes = findViewById(R.id.yes);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(WorkActivity.Shekles >= needmoney & WorkActivity.Lvl >= needlvl){
                    WorkActivity.Shekles -=needmoney;
                    UpdateEq();
                    text.animateText("prosze bardzo, cos jeszcze?");
                    HideWhichButtons();
                    HideYesNoButtons();
                    ShowMoney();
                    yes.setOnClickListener(null);
                }else{
                    text.animateText("nie ladnie tak klamac");
                    HideWhichButtons();
                    HideYesNoButtons();
                    yes.setOnClickListener(null);
                }
            }
        });
        final Button no = findViewById(R.id.no);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text.animateText("W takim razie odejdz...");
                HideWhichButtons();
                HideYesNoButtons();
                no.setOnClickListener(null);
            }
        });
    }

    protected void BuyItems(View v){
        WriteAnim text = (WriteAnim) findViewById(R.id.text);
        text.setCharacterDelay(30);
        text.animateText("Co chcialbys kupic?");
        Button buyHp = findViewById(R.id.buyHp);
        buyHp.setVisibility(View.VISIBLE);
        buyHp.setOnClickListener(this);
        Button buyRes = findViewById(R.id.buyResource);
        buyRes.setVisibility(View.VISIBLE);
        buyRes.setOnClickListener(this);

    }
    protected void Purchase(){
        final WriteAnim text = findViewById(R.id.text);
        text.setCharacterDelay(30);
        text.animateText("Czy masz "+needmoney+" g?");
        final Button yes = findViewById(R.id.yes);
        yes.setVisibility(View.VISIBLE);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(WorkActivity.Shekles >= needmoney){
                    WorkActivity.Shekles -=needmoney;
                    UpdateStats();
                    equipment.AddToBackpack(item);
                    text.animateText("prosze bardzo, cos jeszcze?");
                    HideYesNoButtons();
                    HideItemButtons();
                    buyed = true;
                    ShowMoney();
                    UpdateBackpack();
                    equipment.SetBackpack();
                    yes.setOnClickListener(null);
                }else{
                    text.animateText("nie ladnie tak klamac");
                    HideYesNoButtons();
                    HideItemButtons();
                    yes.setOnClickListener(null);
                }
            }
        });
        final Button no = findViewById(R.id.no);
        no.setVisibility(View.VISIBLE);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text.animateText("W takim razie odejdz...");
                HideYesNoButtons();
                HideItemButtons();
                no.setOnClickListener(null);
            }
        });
    }


    protected void SellEq(View v){
        ShowSellButtons();
        item_first();
        item_second();
        item_third();
        WriteAnim text = findViewById(R.id.text);
        text.setCharacterDelay(30);
        text.animateText("co chcesz sprzedac");
    }
    protected void Sell(){
        final WriteAnim text = findViewById(R.id.text);
        text.setCharacterDelay(30);
        text.animateText("Czy chcesz za to "+needmoney+" g?");
        final Button yes = findViewById(R.id.yes);
        yes.setVisibility(View.VISIBLE);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choise = true;
                if(choise){
                    WorkActivity.Shekles +=needmoney;
                    UpdateStats();
                    equipment.DeleteFromBackpack(b);
                    text.animateText("prosze bardzo, cos jeszcze?");
                    HideYesNoButtons();
                    HideSellButtons();
                    ShowMoney();
                    sold = true;
                    UpdateBackpack();
                    equipment.SetBackpack();
                    b=0;
                    yes.setOnClickListener(null);
                }
            }
        });
        final Button no = findViewById(R.id.no);
        no.setVisibility(View.VISIBLE);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choise = false;
                text.animateText("W takim razie odejdz...");
                HideYesNoButtons();
                HideSellButtons();
                b = 0;
                no.setOnClickListener(null);
            }
        });
    }

    protected void item_1(View v){
        item_first();
    }
    protected void item_2(View v){
        item_second();
    }
    protected void item_3(View v){
        item_third();
    }
    protected void item_first(){
        final ImageButton item = findViewById(R.id.item_1);
        if(b <= Equipment.max){
            switch(equipment.GetItemFromBackpack(b)){
                case "Heal":
                    item.setImageResource(R.drawable.hp);
                    item.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            needmoney = 30;
                            Sell();
                            item.setOnClickListener(null);
                        }
                    });
                    break;
                case "Mana":
                    item.setImageResource(R.drawable.mana);
                    item.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            needmoney = 30;
                            Sell();
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
                            needmoney = 30;
                            b = b+1;
                            Sell();
                            item.setOnClickListener(null);
                        }
                    });
                    break;
                case "Mana":
                    item.setImageResource(R.drawable.mana);
                    item.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            needmoney = 30;
                            b = b+1;
                            Sell();
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
                            needmoney = 30;
                            b = b+2;
                            Sell();
                            item.setOnClickListener(null);
                        }
                    });
                    break;
                case "Mana":
                    item.setImageResource(R.drawable.mana);
                    item.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            needmoney = 30;
                            b = b+2;
                            Sell();
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
    protected void item_up(View v){
        b++;
        if(b >= 8){
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

    protected void Back(View view){
        Intent Tavern = new Intent(getApplicationContext(), TavernActivity.class);
        startActivity(Tavern);
    }

    @Override
    public void onClick(View view) {
        WriteAnim text = (WriteAnim) findViewById(R.id.text);
        text.setCharacterDelay(30);
        switch(view.getId()){
            case R.id.buyHelm:
                HideBuyButtons();
                ShowWhichButtons();
                text.animateText("ktory helm bys chcial?");
                tempName = "helm";
                break;
            case R.id.buyChest:
                HideBuyButtons();
                ShowWhichButtons();
                text.animateText("ktory klate bys chcial?");
                tempName = "chest";
                break;
            case R.id.buyLegs:
                HideBuyButtons();
                ShowWhichButtons();
                text.animateText("ktore spodnie bys chcial?");
                tempName = "legs";
                break;
            case R.id.buyBoots:
                HideBuyButtons();
                ShowWhichButtons();
                text.animateText("ktore buty bys chcial?");
                tempName = "boots";
                break;
            case R.id.buyWeapon:
                HideBuyButtons();
                ShowWhichButtons();
                text.animateText("ktora bron bys chcial?");
                tempName = "weapon";
                break;
            case R.id.buyCape:
                HideBuyButtons();
                ShowWhichButtons();
                text.animateText("ktory plaszcz bys chcial?");
                tempName = "cape";
                break;
            case R.id.addSkill_2:
                a=2;
                needlvl=3;
                needmoney=100;
                AddSkill();
                break;
            case R.id.addSkill_3:
                a=3;
                needlvl=4;
                needmoney=200;
                AddSkill();
                break;
            case R.id.addSkill_4:
                a=4;
                needlvl=5;
                needmoney=300;
                AddSkill();
                break;
            case R.id.addSkill_5:
                a=5;
                needlvl=5;
                needmoney=300;
                AddSkill();
                break;
            case R.id.addSkill_6:
                a=6;
                needlvl=5;
                needmoney=300;
                AddSkill();
                break;
            case R.id.addSkill_7:
                a=7;
                needlvl=5;
                needmoney=300;
                AddSkill();
                break;
            case R.id.buyHp:
                needmoney = 50;
                item = "Heal";
                Purchase();
                break;
            case R.id.buyResource:
                needmoney = 50;
                item = "Mana";
                Purchase();
        }
    }

    protected void BuyFirst(View v){
        tempType = "First";
        needlvl=3;
        needmoney=100;
        AddEq();
    }
    protected void BuySecond(View v){
        tempType = "Second";
        needlvl=5;
        needmoney=300;
        AddEq();
    }
    protected void BuyThird(View v){
        tempType = "Third";
        needlvl=5;
        needmoney=300;
        AddEq();
    }

    protected void UpdateStats(){
        try {
            File file = new File ("data/data/com.example.nigakolczan.apk2/Stats_"+RestActivity.a+".xml");
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(file);

            String mo = Integer.toString(WorkActivity.Shekles);

            NodeList root = doc.getChildNodes();
            Node Stats = getNode("Stats", root);
            Node Chk = getNode("Character", Stats.getChildNodes());

            Node Gold = getNode("Gold", Chk.getChildNodes());
            NamedNodeMap gold = Gold.getAttributes();
            Node nodeGold = gold.getNamedItem("Shekles");
            nodeGold.setTextContent(mo);


            NodeList list = Chk.getChildNodes();
            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);

                if (("Skill_"+a).equals(node.getNodeName())) {
                    node.setTextContent("T");
                }
            }

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
    protected void UpdateEq(){
        try {
            File file = new File ("data/data/com.example.nigakolczan.apk2/Stats_"+RestActivity.a+".xml");
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(file);

            String mo = Integer.toString(WorkActivity.Shekles);

            NodeList root = doc.getChildNodes();
            Node Stats = getNode("Stats", root);
            Node Chk = getNode("Character", Stats.getChildNodes());

            Node Gold = getNode("Gold", Chk.getChildNodes());
            NamedNodeMap gold = Gold.getAttributes();
            Node nodeGold = gold.getNamedItem("Shekles");
            nodeGold.setTextContent(mo);


            NodeList list = Chk.getChildNodes();
            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);

                if ((tempName).equals(node.getNodeName())) {
                    node.setTextContent(tempType);
                }
            }

            RestActivity restActivity = new RestActivity();
            restActivity.UpdateEq();

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

    protected void UpdateBackpack() {
        try {
            File file = new File("data/data/com.example.nigakolczan.apk2/Stats_" + RestActivity.a + ".xml");
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(file);

            NodeList root = doc.getChildNodes();
            Node Stats = getNode("Stats", root);
            Node Chk = getNode("Character", Stats.getChildNodes());
            Node backpack = getNode("Backpack", Stats.getChildNodes());

            NodeList list = backpack.getChildNodes();
            for (int i = 0; i < 10; i++) {
                Node node = list.item(i);

                if (("item_"+i).equals(node.getNodeName())) {
                    if(sold){
                        if(i<Equipment.max){
                            node.setTextContent(equipment.GetItemFromBackpack(i));
                            sold = false;
                        }else{
                            node.setTextContent("empty");
                            sold = false;
                        }
                    }
                    if(buyed){
                        if(node.getNodeName().equals("empty")){
                            node.setTextContent(item);
                            buyed = false;
                            break;
                        }
                    }
                }
            }
            Node First = getNode("first_eq", Chk.getChildNodes());
            First.setTextContent("T");

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(file);
            transformer.transform(source,result);

        } catch (ParserConfigurationException | TransformerException | IOException | SAXException pce) {
            pce.printStackTrace();
        }
    }
}
   /* protected void AddSkill_8(View v){
        a=8;
        needlvl=5;
        needShekles=300;
        AddSkill();
    }
*/