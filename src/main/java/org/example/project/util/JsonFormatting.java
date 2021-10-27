package org.example.project.util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Set;

public class JsonFormatting {

    private static final String newLine = "\n";
    private static final String qMarks = "\"";
    private static final String arrayStart = "<span style='color:black'>[</span>";
    private static final String arrayEnd = "<span style='color:black'>]</span>";
    private static final String objectStart = "<span style='color:black'>{</span>";
    private static final String objectEnd = "<span style='color:black'>}</span>";
    private static final String keyStyle = "<span style='color:purple'>%s%s%s:</span>";
    private static final String valueStyle = "<span style='color:green'>%s%s%s</span>";
    private static final String numberOrBooleanValueStyle = "<span style='color:blue'>%s</span>";

    private int depthLevel = 0;

    public String format(String json) {
        StringBuilder sb = new StringBuilder();
        if (json.startsWith("[")) {
            JSONArray jsonArray = new JSONArray(json);
            printArray(sb, jsonArray, false);
        } else {
            JSONObject jsonObject = new JSONObject(json);
            printObject(sb, jsonObject, false, false);
        }
        return sb.toString();
    }

    private void printArray(StringBuilder sb, JSONArray jsonArray, boolean hasMoreElements) {
        sb.append(arrayStart);
        sb.append(newLine);
        increaseDepthLevel(2);
        for (int i = 0; i < jsonArray.length(); i++) {
            Object o = jsonArray.get(i);
            if (o instanceof JSONObject) {
                JSONObject jsonObject = (JSONObject) o;
                printObject(sb, jsonObject, i + 1 < jsonArray.length(), false);
            } else if (o instanceof JSONArray) {
                JSONArray arr = (JSONArray) o;
                printArray(sb, arr, i + 1 < jsonArray.length());
            } else {
                printList(sb, o, i + 1 < jsonArray.length());
            }
        }
        decreaseDepthLevel(2);
        sb.append(getWhitespace(depthLevel));
        sb.append(arrayEnd);
        if (hasMoreElements) {
            sb.append(",");
        }
        sb.append(newLine);
    }

    private void printObject(StringBuilder sb, JSONObject jsonObject, boolean hasMoreElement, boolean isRecursive) {
        Set<String> strings = jsonObject.keySet();
        if (!isRecursive) {
            sb.append(getWhitespace(depthLevel));
        }
        sb.append(objectStart);
        sb.append(newLine);
        increaseDepthLevel(1);
        int count = 0;

        for (String string : strings) {
            Object o = jsonObject.get(string);
            sb.append(getWhitespace(depthLevel));
            sb.append(String.format(" %s", String.format(keyStyle, qMarks, string, qMarks)));
            increaseDepthLevel(2);
            if (o instanceof JSONArray) {
                decreaseDepthLevel(1);
                JSONArray arr = (JSONArray) o;
                printArray(sb, arr, count + 1 < strings.size());
                increaseDepthLevel(1);
            } else if (o instanceof JSONObject) {
                decreaseDepthLevel(1);
                JSONObject jObj = (JSONObject) o;
                printObject(sb, jObj, count + 1 < strings.size(), true);
                increaseDepthLevel(1);
            } else {
                if (checkIsNumberOrBoolean(o)) {
                    sb.append(String.format(numberOrBooleanValueStyle, o));
                } else {
                    sb.append(String.format(valueStyle, qMarks, o, qMarks));
                }
                if (count + 1 < strings.size()) {
                    sb.append(",");
                }
                sb.append(newLine);
            }
            decreaseDepthLevel(2);
            count++;
        }

        decreaseDepthLevel(1);
        sb.append(getWhitespace(depthLevel));
        sb.append(objectEnd);

        if (hasMoreElement) {
            sb.append(",");
        }
        sb.append(newLine);
    }

    private void printList(StringBuilder sb, Object o, boolean hasMoreElements) {
        sb.append(getWhitespace(depthLevel));
        if (checkIsNumberOrBoolean(o)) {
            sb.append(String.format(numberOrBooleanValueStyle, o));
        } else {
            sb.append(String.format(valueStyle, qMarks, o, qMarks));
        }

        if (hasMoreElements) {
            sb.append(",");
        }
        sb.append(newLine);
    }

    private boolean checkIsNumberOrBoolean(Object o) {
        return o instanceof Number || o instanceof Boolean;
    }

    private String getWhitespace(int quantity) {
        StringBuilder a = new StringBuilder("");
        for (int i = 0; i < quantity; i++) {
            a.append(" ");
        }
        return a.toString();
    }

    private void increaseDepthLevel(int quantity) {
        depthLevel += quantity;
    }

    private void decreaseDepthLevel(int quantity) {
        depthLevel -= quantity;
    }
}
