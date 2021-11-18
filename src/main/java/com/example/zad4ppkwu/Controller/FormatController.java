package com.example.zad4ppkwu.Controller;

import com.example.zad4ppkwu.JSONConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
public class FormatController {


    @GetMapping(value = "/TXT/{from}/{text}", produces = {"text/plain"})
    public String txtConverter(@PathVariable String from, @PathVariable String text) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        if (from.equals("JSON")) {

            Map json = restTemplate.getForObject("http://localhost:8081/" + from + "/" + text, Map.class);

            JSONConverter converter = new JSONConverter();

            return converter.toTXT(json);

        } else if (from.equals("CSV")) {

            String csv = restTemplate.getForObject("http://localhost:8081/" + from + "/" + text, String.class);

            String[] sp = csv.split("\n");

            String[] key = sp[0].split(",");
            String[] value = sp[1].split(",");

            String json = "{";
            for (int i = 0; i < key.length; i++) {
                if (i != 0) {
                    json += ",";
                }
                json += "\"" + key[i] + "\":\"" + value[i] + "\"";
            }
            json += "}";

            JSONConverter converter = new JSONConverter();

            return converter.toTXT(json);

        } else if (from.equals("XML")) {

            String xml = restTemplate.getForObject("http://localhost:8081/" + from + "/" + text, String.class);
            JSONObject xmlJSONObj = XML.toJSONObject(xml);
            String json = xmlJSONObj.get("root").toString();
            JSONConverter converter = new JSONConverter();

            return converter.toTXT(json);
        } else if (from.equals("TXT")) {
            return restTemplate.getForObject("http://localhost:8081/" + from + "/" + text, String.class);
        } else {
            return "BAD FORMAT";
        }
    }

    @GetMapping("/JSON/{from}/{text}")
    public Map jsonConverter(@PathVariable String from, @PathVariable String text) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        if (from.equals("JSON")) {

            Map json = restTemplate.getForObject("http://localhost:8081/" + from + "/" + text, Map.class);

            return json;

        } else if (from.equals("CSV")) {

            String csv = restTemplate.getForObject("http://localhost:8081/" + from + "/" + text, String.class);

            String[] sp = csv.split("\n");

            String[] key = sp[0].split(",");
            String[] value = sp[1].split(",");

            String json = "{";
            for (int i = 0; i < key.length; i++) {
                if (i != 0) {
                    json += ",";
                }
                json += "\"" + key[i] + "\":\"" + value[i] + "\"";
            }
            json += "}";

            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, Map.class);

        } else if (from.equals("XML")) {

            String xml = restTemplate.getForObject("http://localhost:8081/" + from + "/" + text, String.class);
            JSONObject xmlJSONObj = XML.toJSONObject(xml);
            String json = xmlJSONObj.get("root").toString();

            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, Map.class);

        } else if (from.equals("TXT")) {
            String txt = restTemplate.getForObject("http://localhost:8081/" + from + "/" + text, String.class);

            JSONConverter converter = new JSONConverter();

            return converter.toJSON(txt);

        }
        return Map.of();
    }

    @GetMapping(value = "/CSV/{from}/{text}", produces = {"text/plain"})
    public String csvConverter(@PathVariable String from, @PathVariable String text) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        if (from.equals("JSON")) {

            String json = restTemplate.getForObject("http://localhost:8081/" + from + "/" + text, String.class);

            JSONConverter converter = new JSONConverter();


            return converter.toCSV(json);

        } else if (from.equals("CSV")) {

            return restTemplate.getForObject("http://localhost:8081/" + from + "/" + text, String.class);

        } else if (from.equals("XML")) {

            String xml = restTemplate.getForObject("http://localhost:8081/" + from + "/" + text, String.class);
            JSONObject xmlJSONObj = XML.toJSONObject(xml);
            String json = xmlJSONObj.get("root").toString();

            JSONConverter converter = new JSONConverter();

            return converter.toCSV(json);

        } else if (from.equals("TXT")) {
            String txt = restTemplate.getForObject("http://localhost:8081/" + from + "/" + text, String.class);

            JSONConverter converter = new JSONConverter();

            return converter.toCSV(converter.toJSONTXT(txt));

        }
        return "";
    }

    @GetMapping(value = "/XML/{from}/{text}", produces = {"application/xml", "text/xml"})
    public String xmlConverter(@PathVariable String from, @PathVariable String text) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        if (from.equals("JSON")) {

            String json = restTemplate.getForObject("http://localhost:8081/" + from + "/" + text, String.class);

            JSONObject jsonObject = new JSONObject(json);

            String xml = "<?xml version=\"1.0\" encoding=\"ISO-8859-15\"?>\n<" + "root" + ">" + XML.toString(jsonObject) + "</" + "root" + ">";

            return xml;

        } else if (from.equals("CSV")) {

            String csv = restTemplate.getForObject("http://localhost:8081/" + from + "/" + text, String.class);

            String[] sp = csv.split("\n");

            String[] key = sp[0].split(",");
            String[] value = sp[1].split(",");

            String json = "{";
            for (int i = 0; i < key.length; i++) {
                if (i != 0) {
                    json += ",";
                }
                json += "\"" + key[i] + "\":\"" + value[i] + "\"";
            }
            json += "}";

            JSONConverter converter = new JSONConverter();

            return converter.toXML(json);
        } else if (from.equals("XML")) {

            return restTemplate.getForObject("http://localhost:8081/" + from + "/" + text, String.class);

        } else if (from.equals("TXT")) {
            String txt = restTemplate.getForObject("http://localhost:8081/" + from + "/" + text, String.class);

            JSONConverter converter = new JSONConverter();

            return converter.toXML(converter.toJSONTXT(txt));

        }
        return "";
    }


}
