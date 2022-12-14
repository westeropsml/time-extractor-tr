package com.bezkoder.springjwt.rules.timeinterval;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;

import com.bezkoder.springjwt.models.ExtractionRule;
import com.bezkoder.springjwt.constants.TemporalConstants;
import com.bezkoder.springjwt.temporal.entities.Temporal;
import com.bezkoder.springjwt.temporal.entities.Type;
import com.bezkoder.springjwt.utils.TemporalParser;
import com.bezkoder.springjwt.utils.Utils;

public class TimeIntervalRule2 extends ExtractionRule {

    private TemporalParser parser;
    private int priority = 2;
    private String ruleEn = "\\b(between)[\\s]*" + TemporalConstants.TIME_OF_DAY + "[\\s]*((and|to|until|til)|[-])[\\s]*" + TemporalConstants.TIME_OF_DAY + "\\b";

    private String rule = "\\b(" + TemporalConstants.TIME_OF_DAY + ")[\\s]?(la|le|ile|ve)?\\s(" + TemporalConstants.TIME_OF_DAY + ")(arası)?\\b";
    protected String exampleEn = "between morning and evening";
    protected String example = "sabah ile akşam arası, öğlenle akşam arası, sabah akşam arası, akşam ve gece arası, akşam ile sabah arası";

    protected UUID id = UUID.fromString("db01b0a4-8ef9-49b7-bf17-509533ef7e84");

    private double confidence = 0.9;

    public TimeIntervalRule2() {
        parser = new TemporalParser();
    }

    @Override
    public Type getType() {
        return Type.TIME_INTERVAL;
    }

    @Override
    public List<Temporal> getTemporal(String text) {
        Matcher m = Utils.getMatch(rule, text);

        // for (int i = 1; i < 60; i++) {
        //     System.out.println(i+")-------------------------------"+m.group(i)+"------------------------");
        //    }

        Temporal temporal = parser.getTimeOfDay(m.group(1));
        Temporal temporal2 = parser.getTimeOfDay(m.group(13));
        temporal.setEndDate(temporal2.getEndDate());
        List<Temporal> temporalList = new ArrayList<Temporal>();
        temporalList.add(temporal);
        return temporalList;
    }

    @Override
    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
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

    @Override
    public int compareTo(ExtractionRule o) {
        return super.compare(this, o);
    }

    public UUID getId() {
        return id;
    }

}