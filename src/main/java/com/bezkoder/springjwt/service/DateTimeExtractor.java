package com.bezkoder.springjwt
.service;

import com.bezkoder.springjwt.Business.CsvReader;
import com.bezkoder.springjwt.Business.CsvWriter;
import com.bezkoder.springjwt.models.Settings;
import com.bezkoder.springjwt.models.TemporalExtraction;
import com.bezkoder.springjwt.models
.Tip;
import com.bezkoder.springjwt
.exceptions.ExceptionMessages;

import java.io.IOException;
import java.util.List;
import java.util.TreeSet;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

public class DateTimeExtractor {

    public static TreeSet<TemporalExtraction> extract(String text, Settings settings){
        text = toTurkishLowerCase(text);
        TemporalExtractionService service = new TemporalExtractionService();
        TreeSet<TemporalExtraction> extracted = service.extractDatesAndTimeFromText(text, settings);
        return extracted;
    }

    public static TreeSet<TemporalExtraction> extract(String text){
        text = toTurkishLowerCase(text);
        Settings settings = new Settings();
        TemporalExtractionService service = new TemporalExtractionService();
        TreeSet<TemporalExtraction> extracted = service.extractDatesAndTimeFromText(text, settings);
        return extracted;
    }

    public static String extractJSON(String text){
        text = toTurkishLowerCase(text);
        TreeSet<TemporalExtraction> extracted = extract(text);
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(extracted);
        } catch (JsonProcessingException e) {
            json = e.getMessage();
            e.printStackTrace();
        }
        return json;
    }

    public static String extractJSON(String text, Settings settings){
        text = toTurkishLowerCase(text);
        TreeSet<TemporalExtraction> extracted = extract(text, settings);
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(extracted);
        } catch (JsonProcessingException e) {
            json = e.getMessage();
            e.printStackTrace();
        }
        return json;
    }

    public static TreeSet<TemporalExtraction> extractFromCsv(String csvPath, String separator, String outputPath, Settings settings)
            throws Exception {
        CsvReader reader = new CsvReader();
        CsvWriter writer = new CsvWriter();
        List<Tip> tips;
        TemporalExtractionService service = new TemporalExtractionService();
        ObjectMapper mapper = new ObjectMapper();
        TreeSet<TemporalExtraction> extracted = new TreeSet<>();
        try {
            tips = reader.getTipsFromFile(csvPath, separator);
        }
        catch(IOException e){ throw new Exception(ExceptionMessages.INPUT_FILE_NOT_FOUND);}
        for (Tip tip : tips) {
            String text = tip.getTipText();
            TreeSet<TemporalExtraction> curr_extracted = service.extractDatesAndTimeFromText(text, settings);
            for(TemporalExtraction temp : curr_extracted) {
                extracted.add(temp);
            }
            writer.writeToFile(outputPath, mapper.writeValueAsString(curr_extracted));

        }
        return extracted;
    }

    public static String extractJSONFromCsv(String csvPath, String separator, String outputPath, Settings settings)
            throws Exception {

        TreeSet<TemporalExtraction> extracted = extractFromCsv(csvPath, separator, outputPath, settings);
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(extracted);
        } catch (JsonProcessingException e) {
            json = e.getMessage();
            e.printStackTrace();
        }
        return json;
    }

    public static String toTurkishLowerCase(String text){
        char[] turkishChars = new char[] {'A', 'B', 'C', '??', 'D', 'E', 'F', 'G', '??', 'H', 'I', '??', 'J', 'K', 'L', 'M', 'N', 'O', '??', 'P', 'R', 'S', '??', 'T', 'U', '??', 'V', 'Y', 'Z'};
        char[] turkishLowerChars = new char[] {'a', 'b', 'c', '??', 'd', 'e', 'f', 'g', '??', 'h', '??', 'i', 'j', 'k', 'l', 'm', 'n', 'o', '??', 'p', 'r', 's', '??', 't', 'u', '??', 'v', 'y', 'z'};
        for (int i = 0; i < turkishChars.length; i++) {
            text = text.replaceAll(new String(new char[]{turkishChars[i]}), new String(new char[]{turkishLowerChars[i]}));
        }
        return text;
    }

    public static void main(String[] args) {
        String text = "from summer to winter";
        String result = DateTimeExtractor.extractJSON(text);
        System.out.println(result);
    }
}