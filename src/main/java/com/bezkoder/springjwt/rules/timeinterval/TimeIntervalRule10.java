package com.bezkoder.springjwt.rules.timeinterval;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;

import com.bezkoder.springjwt.models.ExtractionRule;
import com.bezkoder.springjwt.constants.TemporalConstants;
import com.bezkoder.springjwt.temporal.entities.Temporal;
import com.bezkoder.springjwt.temporal.entities.TimeDate;
import com.bezkoder.springjwt.temporal.entities.Type;
import com.bezkoder.springjwt.utils.TemporalBasicCaseParser;
import com.bezkoder.springjwt.utils.TemporalObjectGenerator;
import com.bezkoder.springjwt.utils.TemporalParser;
import com.bezkoder.springjwt.utils.Utils;
import com.bezkoder.springjwt.temporal.entities.Time;

// from 10:00 to 11:00
//from 10.00 to 11.00

public class TimeIntervalRule10 extends ExtractionRule {
    private TemporalParser parser;

    protected Locale locale = Locale.US;
    protected double confidence = 0.8;
    private int priority = 5;
    private String rule="\\b((saat)\\s)?(("+TemporalConstants.TIME_OF_DAY+")\\s)?((saat)\\s)?([0-9]|0[0-9]|1[0-9]|2[0-3])((:|.)([0-5][0-9]))?('|\\s*)((den|ten|dan|tan)\\s)("+TemporalConstants.TIME_OF_DAY+")(e|ye|ya|a)\\s(kadar)?";
    private String ruleEn = "\\b((from|between)[\\s]*)([01]?[0-9]|2[0-3])[\\s]*[.|:]([0-5][0-9])[\\s]*(to|and)[\\s]*([01]?[0-9]|2[0-3])[\\s]*[.|:]([0-5][0-9])" + "([\\s]*" + TemporalConstants.TIME_ZONE
            + ")?";

    protected String example = "from 10:00 to 11:00";
    protected UUID id = UUID.fromString("11e289d6-9421-40bb-b799-cd7f96c32913");

    public TimeIntervalRule10() {
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
        //  System.out.println(i+")-------------------------------"+m.group(i)+"------------------------");
        //  }

         Temporal temporal = parser.getTimeOfDay(m.group(24));
         int hours = Integer.parseInt(m.group(17));
         if (m.group(4) != null) {
             hours = Utils.convertTimeSpecial(hours, m.group(4));
         }
         
         temporal.getStartDate().getTime().setHours(hours);
         List<Temporal> temporalList = new ArrayList<Temporal>();
         temporalList.add(temporal);
         return temporalList;

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

    @Override
    public int compareTo(ExtractionRule o) {
        return super.compare(this, o);
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    @Override
    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }

    public UUID getId() {
        return id;
    }
}
