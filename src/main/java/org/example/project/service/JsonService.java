package org.example.project.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

import java.io.*;
import java.util.stream.Collectors;

public class JsonService {

    private final String fileName;

    public JsonService(String fileName) {
        this.fileName = fileName;
    }

    public String getJson() {
        Gson gs = new GsonBuilder().setPrettyPrinting().create();
        return gs.toJson(new JsonParser().parse(readJsonFile()));
    }

    private String readJsonFile() {
        String result = "";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            result = br.lines().collect(Collectors.joining());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
