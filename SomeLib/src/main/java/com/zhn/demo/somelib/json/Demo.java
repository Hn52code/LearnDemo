package com.zhn.demo.somelib.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Demo {

    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();

        ArrayNode array = node.putArray("list");
        array.add("pa1");
        array.add("pa2");
        array.add("pa3");

        System.out.println(node.toString());    // {"list":["pa1","pa2","pa3"]}
        String json = "{\"list\":[\"pa1\",\"pa2\",\"pa3\"]}";
        JsonNode tree = mapper.readTree(json);
        System.out.println(tree);               // {"list":["pa1","pa2","pa3"]}
        System.out.println(tree.findValuesAsText("list")); // []
        System.out.println(tree.get("list").toString()); //  ["pa1","pa2","pa3"]
        System.out.println(tree.get("list").isArray());  // true
        System.out.println(tree.findValue("list").toString());  // ["pa1","pa2","pa3"]
        System.out.println(tree.findValue("list").toString());  // ["pa1","pa2","pa3"]

    }

}
