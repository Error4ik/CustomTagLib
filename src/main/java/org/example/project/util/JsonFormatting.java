package org.example.project.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.project.service.JsonService;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Objects;
import java.util.Set;

public class JsonFormatting {

    private static final Logger logger = LogManager.getLogger();
    private static final String newLine = "\n";
    private static final String whitespace = " ";

    private static final String arrayStart = "<span style='color:blue'>[</span>";
    private static final String arrayEnd = "<span style='color:blue'>]</span>";
    private static final String objectStart = "<span style='color:red'>{</span>";
    private static final String objectEnd = "<span style='color:red'>}</span>";

    private static final String keyStyle = "<span style='color:green'>%s%s%s:</span>";
    private static final String valueStyle = "<span style='color:orange'>%s%s%s</span>";

    private static final String qMarks = "\"";

    public static String format(String json) {
        StringBuilder sb = new StringBuilder();
        JSONArray jsonArray = new JSONArray(json);

        int level = 1;

        sb.append(arrayStart);
        sb.append(newLine);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Set<String> strings = jsonObject.keySet();

            sb.append(getWhitespace(level));
            sb.append(objectStart);
            sb.append(newLine);

            for (String string : strings) {
                Object o = jsonObject.get(string);
//                sb.append(String.format("%s%s%s%s%s%s%s%s", keyStyle, qMarks, string, qMarks, valueStyle, qMarks, o, qMarks));
                String key = String.format(keyStyle, qMarks, string, qMarks);
                String value = "";
                if (o instanceof Number) {
                    value = String.format("<span style='color:blue'>%s</span>", o);
                } else {
                    value = String.format(valueStyle, qMarks, o, qMarks);
                }
                sb.append(getWhitespace(level + 2));
                sb.append(String.format("%s %s", key, value));
                sb.append(newLine);
            }
            sb.append(getWhitespace(level));
            sb.append(objectEnd);

            if (i + 1 < jsonArray.length()) {
                sb.append(",");
                sb.append(newLine);
            }
        }

        sb.append(newLine);
        sb.append(arrayEnd);
        sb.append(newLine);


////        sb.append("<div>");
//        sb.append(String.format("<span style='color:blue'>%s</span>", "{"));
//        sb.append("\n");
//        sb.append(String.format("<span style='color:green'>%s:</span> <span style='color:orange'>%s</span>", "\"id\"", 132131));
//        sb.append("\n");
//        sb.append(String.format("<span style='color:green'>%s:</span> <span style='color:orange'>%s</span>", "\"name\"", "\"John\""));
//        sb.append("\n");
//        sb.append(String.format("<span style='color:green'>%s:</span> <span style='color:orange'>%s</span>", "\"surname\"", "\"Doe\""));
//        sb.append("\n");
//        sb.append(String.format("<span style='color:blue'>%s</span>", "}"));
////        sb.append("</div>");
        return sb.toString();
    }

//    public static void main(String[] args) {
//        Settings settings = new Settings();
//        String fileName =
//                Objects.requireNonNull(
//                        settings.getClass().getClassLoader().getResource(settings.getValue("fileName"))).getPath();
//        JsonService jsonService = new JsonService(fileName);
//        String json = jsonService.getJson();
//
//        String result = format(json);
//
//        logger.info(result);
//    }

    private static String getWhitespace(int quantity) {
        StringBuilder a = new StringBuilder(whitespace);
        for (int i = 0; i < quantity; i++) {
            a.append(whitespace);
        }
        return a.toString();
    }
}
