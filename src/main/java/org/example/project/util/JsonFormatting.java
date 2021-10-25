package org.example.project.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.project.service.JsonService;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class JsonFormatting {

    private static final Logger logger = LogManager.getLogger();
    private static final String newLine = "\n";
    private static final String whitespace = "";

    private static final String arrayStart = "<span style='color:blue'>[</span>";
    private static final String arrayEnd = "<span style='color:blue'>]</span>";
    private static final String objectStart = "<span style='color:red'>{</span>";
    private static final String objectEnd = "<span style='color:red'>}</span>";

    private static final String keyStyle = "<span style='color:green'>%s%s%s:</span>";
    private static final String valueStyle = "<span style='color:orange'>%s%s%s</span>";

    private static final String qMarks = "\"";

    private static final StringBuilder sb = new StringBuilder();
    private static int level = 1;

    public static String format(String json) {
        StringBuilder sb = new StringBuilder();
        JSONArray jsonArray = new JSONArray(json);
//        int level = 1;
        sb.append(arrayStart);
        sb.append(newLine);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Set<String> strings = jsonObject.keySet();
            sb.append(getWhitespace(level));
            sb.append(objectStart);
            sb.append(newLine);
            level += 1;
            int count = 0;
            for (String string : strings) {
                Object o = jsonObject.get(string);
                sb.append(getWhitespace(level));
                sb.append(String.format(" %s", String.format(keyStyle, qMarks, string, qMarks)));

                if (o instanceof JSONArray) {
                    sb.append(String.format(" %s", arrayStart));
                    sb.append(newLine);
                    JSONArray innerArr = (JSONArray) o;

                    level += 2;
                    for (int j = 0; j < innerArr.length(); j++) {
                        JSONObject innerOb = innerArr.getJSONObject(j);
                        Set<String> innerStrings = innerOb.keySet();
                        level += 1;
                        sb.append(getWhitespace(level));
                        sb.append(objectStart);
                        sb.append(newLine);
                        level += 2;
                        int count2 = 0;
                        for (String innerString : innerStrings) {
                            Object ob = innerOb.get(innerString);
                            sb.append(getWhitespace(level));
                            String innerKey = String.format(keyStyle, qMarks, innerString, qMarks);
                            String innerValue;
                            if (ob instanceof Number) {
                                innerValue = String.format("<span style='color:blue'>%s</span>", ob);
                            } else {
                                innerValue = String.format(valueStyle, qMarks, ob, qMarks);
                            }
                            sb.append(String.format("%s %s", innerKey, innerValue));
                            if (count2 + 1 < innerString.length()) {
                                sb.append(",");
                            }
                            sb.append(newLine);
                            count2++;
                        }
                        level -= 2;
                        sb.append(getWhitespace(level));
                        sb.append(objectEnd);

                        if (j + 1 < innerArr.length()) {
                            sb.append(",");
                        }
                        sb.append(newLine);
                        level -= 1;
                    }
                    sb.append(getWhitespace(level - 1));
                    sb.append(arrayEnd);
                    if (count + 1 < strings.size()) {
                        sb.append(",");
                    }
                    sb.append(newLine);
                    level -= 2;
                } else {
                    String value;
                    if (o instanceof Number) {
                        value = String.format("<span style='color:blue'>%s</span>", o);
                    } else {
                        value = String.format(valueStyle, qMarks, o, qMarks);
                    }
                    sb.append(String.format(" %s", value));
                    if (count + 1 < strings.size()) {
                        sb.append(",");
                    }
                    sb.append(newLine);
                }
                count++;
            }
            level -= 1;
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

        return sb.toString();
    }

    private static String getWhitespace(int quantity) {
        StringBuilder a = new StringBuilder(whitespace);
        for (int i = 0; i < quantity; i++) {
            a.append(" ");
        }
        return a.toString();
    }
}
