package sprint02ex02.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sprint02ex02.entities.Filme;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Utils {

    public static List<Filme> readFile(String fileName) throws IOException, ParseException {
        ObjectMapper objectMapper = new ObjectMapper();
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(fileName));
        JSONArray json = (JSONArray) jsonObject.get("filmes");
        var jsonString = json.toJSONString();
        return objectMapper.readValue(jsonString, new TypeReference<>(){});
    }
}
