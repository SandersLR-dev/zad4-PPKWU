package com.example.zad4ppkwu;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

import java.util.Map;

public class JSONConverter {

    public String toTXT(String JSON) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        Map json = mapper.readValue(JSON, Map.class);


        String txtformat = "givenString: %s\nlenght: %d\nletter: %d\nlowerCase: %d\nupperCase: %d\nnumber: %d\nwhiteSpace: %d\nspecialChar: %d\n";

        String txt = String.format(txtformat
                , json.get("givenString")
                , Integer.parseInt(String.valueOf(json.get("length")))
                , Integer.parseInt(String.valueOf(json.get("letter")))
                , Integer.parseInt(String.valueOf(json.get("lowerCase")))
                , Integer.parseInt(String.valueOf(json.get("upperCase")))
                , Integer.parseInt(String.valueOf(json.get("number")))
                , Integer.parseInt(String.valueOf(json.get("whiteSpace")))
                , Integer.parseInt(String.valueOf(json.get("specialChar"))));

        return txt;
    }

    public String toTXT(Map JSON) throws JsonProcessingException {

        String txtformat = "givenString: %s\nlenght: %d\nletter: %d\nlowerCase: %d\nupperCase: %d\nnumber: %d\nwhiteSpace: %d\nspecialChar: %d\n";

        String txt = String.format(txtformat, JSON.get("givenString")
                , JSON.get("length")
                , JSON.get("letter")
                , JSON.get("lowerCase")
                , JSON.get("upperCase")
                , JSON.get("number")
                , JSON.get("whiteSpace")
                , JSON.get("specialChar"));

        return txt;
    }

    public String toXML(String JSON) {

        JSONObject jsonObject = new JSONObject(JSON);

        String xml = "<?xml version=\"1.0\" encoding=\"ISO-8859-15\"?>\n<" + "root" + ">" + XML.toString(jsonObject) + "</" + "root" + ">";

        return xml;
    }

    public String toCSV(String JSON){

        String modifiedResponse = "{\"infile\": [" + JSON + "]}";

        JSONObject jsonObject = new JSONObject(modifiedResponse);

        JSONArray jsonArray = jsonObject.getJSONArray("infile");

        String csv = CDL.toString(jsonArray);

        return csv;
    }

    public Map toJSON(String txt) throws JsonProcessingException {

        String json="{";
        String[] test = txt.split("\n");

        for(int i=0;i<test.length;i++){
            if(i!=0)
            {
                json+=",";
            }
            String[] poz = test[i].split(":");

            json+="\""+poz[0]+"\":";

            if(i==0)
            {
                json+="\""+poz[1].substring(1)+"\"";
            }else{
                json+=Integer.parseInt(String.valueOf(poz[1]).substring(1));
            }

        }
        json+="}";

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, Map.class);
    }


    public String toJSONTXT(String txt) throws JsonProcessingException {

        String json="{";
        String[] test = txt.split("\n");

        for(int i=0;i<test.length;i++){
            if(i!=0)
            {
                json+=",";
            }
            String[] poz = test[i].split(":");

            json+="\""+poz[0]+"\":";

            if(i==0)
            {
                json+="\""+poz[1].substring(1)+"\"";
            }else{
                json+=Integer.parseInt(String.valueOf(poz[1]).substring(1));
            }

        }
        json+="}";

        return json;
    }





}
