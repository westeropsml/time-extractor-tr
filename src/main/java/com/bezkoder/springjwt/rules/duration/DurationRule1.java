package com.bezkoder.springjwt.rules.duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;

import com.bezkoder.springjwt.constants.TemporalConstants;
import com.bezkoder.springjwt.models.ExtractionRule;
import com.bezkoder.springjwt.utils.TemporalParser;
import com.bezkoder.springjwt.utils.Utils;
import com.bezkoder.springjwt.temporal.entities.Temporal;
import com.bezkoder.springjwt.temporal.entities.Type;

public class DurationRule1 extends ExtractionRule {
    private TemporalParser parser;
    private double confidence = 0.8;
    private int priority = 4;
    private String rule = "((en az(ından|ı)?|en fazla)[\\s]*(bir[\\s]*)?)?(\\d)+([\\s]*)("+TemporalConstants.DURATION+")(([\\s]*)(([1-9][0-9])|([1-9][0-9][0-9]))([\\s]*)("+TemporalConstants.DURATION+"))?(den|ten|dan)?([\\s]*)(sürer|fazla)";
    private String ruleEn = "\\b((lasts|about|past|at least|up to|more than|less than|last|after)[\\s]*)?(([1-9])|([1-9][0-9])|([1-9][0-9][0-9]))" + "([\\s]*[-]?" + TemporalConstants.DURATION + "\\b)";
    
    protected String example = "4 gün sürer, 3 saatten fazla";
    protected String exampleEn = "lasts n days, more than 3 hours, etc.";
    protected UUID id = UUID.fromString("a8578915-03f3-4297-b9cd-6486974e1feb");

    public DurationRule1() {
        parser = new TemporalParser();
    }

    @Override
    public Type getType() {
        return Type.DURATION;
    }

    @Override
    public List<Temporal> getTemporal(String text) {
        Matcher m = Utils.getMatch(rule, text);
        // for (int i = 0; i < 120; i++) {
        //     System.out.println(i+")--------------------------"+m.group(i)+"---------------------------");
        // }
        int duration1 = 0;
        if (m.group(5) != null) {
            duration1 = Integer.parseInt(m.group(5));
        }
        int duration2 = 0;
        if (m.group(11) != null) {
            duration2 = Integer.parseInt(m.group(11));
        }
        
        Temporal temporal = parser.getDuration(m.group(7), duration1);
        Temporal temporal2 = parser.getDuration(m.group(15),duration2);
        List<Temporal> temporalList = new ArrayList<Temporal>();
        temporalList.add(temporal);
        temporalList.add(temporal2);
        return temporalList;
    }

    @Override
    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    @Override
    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }

    @Override
    public int compareTo(ExtractionRule o) {
        return super.compare(this, o);
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public UUID getId() {
        return id;
    }

}
