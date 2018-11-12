package com.example.nigakolczan.apk2;



import org.w3c.dom.Document;
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

class Equipment {

    protected static int Hp;
    protected static int Dmg;
    private static int reset = 0;
    protected static int max;
    private int done =0;

    private Node getNode(String tagName, NodeList nodes) {
        for (int x = 0; x < nodes.getLength(); x++) {
            Node node = nodes.item(x);
            if (node.getNodeName().equalsIgnoreCase(tagName)) {
                return node;
            }
        }

        return null;
    }

    protected void SetStats(){
        if(Equipment.reset == 0){
            SetHelmStats();
            SetChestStats();
            SetLegsStats();
            SetBootsStats();
            SetWeaponStats();
            SetCapeStats();
            SetBackpack();
            Equipment.reset++;
        }else if(reset > 0) {

        }
    }
    private void SetHelmStats() {
        RestActivity restActivity = new RestActivity();
        switch (restActivity.getEquipment(0)) {
            case "First":
                Hp += 5;
                break;
            case "Second":
                Hp += 10;
                Dmg += 2;
                break;
            case "Third":
                Hp += 15;
                Dmg += 3;
                break;
        }
        restActivity.UpdateEq();
    }
    private void SetChestStats() {
        RestActivity restActivity = new RestActivity();
        switch (restActivity.getEquipment(1)) {
            case "First":
                Hp += 5;
                break;
            case "Second":
                Hp += 10;
                Dmg += 2;
                break;
            case "Third":
                Hp += 15;
                Dmg += 3;
                break;
        }
        restActivity.UpdateEq();
    }
    private void SetLegsStats() {
        RestActivity restActivity = new RestActivity();
        switch (restActivity.getEquipment(2)) {
            case "First":
                Hp += 5;
                break;
            case "Second":
                Hp += 10;
                Dmg += 2;
                break;
            case "Third":
                Hp += 15;
                Dmg += 3;
                break;
        }
        restActivity.UpdateEq();
    }
    private void SetBootsStats() {
        RestActivity restActivity = new RestActivity();
        switch (restActivity.getEquipment(3)) {
            case "First":
                Hp += 5;
                break;
            case "Second":
                Hp += 10;
                Dmg += 2;
                break;
            case "Third":
                Hp += 15;
                Dmg += 3;
                break;
        }
        restActivity.UpdateEq();
    }
    private void SetWeaponStats() {
        RestActivity restActivity = new RestActivity();
        switch (restActivity.getEquipment(4)) {
            case "First":
                Dmg += 2;
                Hp += 5;
                break;
            case "Second":
                Hp += 10;
                Dmg += 3;
                break;
            case "Third":
                Hp += 15;
                Dmg += 4;
                break;
        }
        restActivity.UpdateEq();
    }
    private void SetCapeStats() {
        RestActivity restActivity = new RestActivity();
        switch (restActivity.getEquipment(5)) {
            case "First":
                Hp += 5;
                break;
            case "Second":
                Hp += 10;
                Dmg += 2;
                break;
            case "Third":
                Hp += 15;
                Dmg += 3;
                break;
        }
        restActivity.UpdateEq();
    }

    List<String>Backpack = new ArrayList<>();
    protected String SetBackpack(){
        RestActivity restActivity = new RestActivity();
        if(restActivity.getFirst_eq().equals("F")){
            if(this.done==0){
                Backpack.add("Mana");
                Backpack.add("Heal");
                Backpack.add("Heal");
                Backpack.add("Mana");
                Backpack.add("Heal");
                Backpack.add("Mana");
                Backpack.add("Heal");
                Backpack.add("Heal");
                Backpack.add("Mana");
                Backpack.add("Heal");
                restActivity.setFirst_eq("T");
                XmlBackpack();
            }
            done = 1;
        }else if(restActivity.getFirst_eq().equals("T")){
            if(done == 0) {
                try {
                    File file = new File("data/data/com.example.nigakolczan.apk2/Stats_" + RestActivity.a + ".xml");
                    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                    Document doc = docBuilder.parse(file);

                    NodeList root = doc.getChildNodes();
                    Node Stats = getNode("Stats", root);
                    Node backpack = getNode("Backpack", Stats.getChildNodes());

                    NodeList list = backpack.getChildNodes();
                    for (int i = 0; i < 10; i++) {
                        Node node = list.item(i);
                        if (("item_" + i).equals(node.getNodeName())) {
                            Backpack.add(node.getTextContent());
                        }
                    }
                    done =1;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if(done ==1){
                XmlBackpack();
            }
        }
        return null;
    }

    List<String> Gear = new ArrayList<>();
    protected int SetGear(){
        RestActivity restActivity = new RestActivity();
        if(restActivity.getFirst_eq().equals("F")){
            Gear.add("First_helm");
            Gear.add("First_chest");
            Gear.add("First_legs");
            Gear.add("First_boots");
            Gear.add("First_Weapon");
            Gear.add("First_cape");
        }else{

        }
        return Gear.size();
    }

    private void XmlBackpack() {
        try {
            File file = new File("data/data/com.example.nigakolczan.apk2/Stats_" + RestActivity.a + ".xml");
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(file);

            NodeList root = doc.getChildNodes();
            Node Stats = getNode("Stats", root);
            Node Chk = getNode("Character", Stats.getChildNodes());
            Node backpack = getNode("Backpack",Stats.getChildNodes());

            NodeList list = backpack.getChildNodes();
            for (int i = 0; i <10; i++) {
                Node node = list.item(i);

                if (("item_"+i).equals(node.getNodeName())) {
                    if(i<Backpack.size()){
                        node.setTextContent(Backpack.get(i));
                    }else{
                        node.setTextContent("empty");
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
    protected void AddToBackpack(String a){
        for(int i = 0; i<Backpack.size(); i++){
            if(Backpack.get(i).equals("empty")){
                Backpack.set(i,a);
                break;
            }
        }
        if(Backpack.size() == 10){
            System.out.println("mozesz miec jedynie 10 itemow na raz");
        }else{
            Backpack.add(a);
        }
    }
    protected int DeleteFromBackpack(int a){
        Backpack.remove(a);
        return Backpack.size();
    }
    protected String GetItemFromBackpack(int a){
        SetBackpack();
        max = (Backpack.size() - 1);
        return Backpack.get(a);
    }

}
