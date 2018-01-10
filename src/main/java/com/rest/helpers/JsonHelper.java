package com.rest.helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class JsonHelper {
    public static JSONObject getJSONObjectFromList(List<?> list, String listName){
        JSONObject result = new JSONObject();
        JSONArray arrayJSON = new JSONArray();
        ObjectMapper mapper = new ObjectMapper();

        try {
            for(int i=0; i<list.size(); i++){
                String objectString = mapper.writeValueAsString(list.get(i));
                JSONObject objectJSON = new JSONObject(objectString);
                arrayJSON.put(objectJSON);
            }
            result.put(listName, arrayJSON);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return result;
    }
}
