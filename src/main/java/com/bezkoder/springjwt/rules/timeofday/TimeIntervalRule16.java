package com.bezkoder.springjwt.rules.timeofday;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;

import com.bezkoder.springjwt.models.ExtractionRule;
import com.bezkoder.springjwt.constants.TemporalConstants;
import com.bezkoder.springjwt.temporal.entities.Temporal;
import com.bezkoder.springjwt.temporal.entities.Type;
import com.bezkoder.springjwt.utils.TemporalParser;
import com.bezkoder.springjwt.utils.Utils;

// between noon and 3pm

public class TimeIntervalRule16 extends ExtractionRule {

    private TemporalParser parser;
    protected Locale locale = Locale.US;
    protected double confidence = 0.8;
    private int priority = 6;
    private String rule="\\b("+TemporalConstants.TIME_OF_DAY+")('|\\s*)((den|ten|dan|tan)\\s)((saat)\\s)?(("+TemporalConstants.TIME_OF_DAY+")\\s)?([0-9]|0[0-9]|1[0-9]|2[0-3])((:|.)([0-5][0-9]))?('|\\s*)(e|ye|ya|a)\\s(kadar)?";
    private String ruleEn = "\\b(from|between)[\\s]*" + TemporalConstants.TIME_OF_DAY + "[\\s]*(to|and)[\\s]*([01]?[0-9]|2[0-3])[\\s]*(([p,P][.]?[m,M][.]?)|([a,A][.]?[m,M]\\.?))(?!,\\S)";
    protected String example = "from morning to 14pm";
    protected UUID id = UUID.fromString("ae135d69-9fcc-4014-9c1c-f02754be012a");

    public TimeIntervalRule16() {
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
        

        Temporal temporal = parser.getTimeOfDay(m.group(3));
        int hours = Integer.parseInt(m.group(29));
        if (m.group(26) != null) {
            hours = Utils.convertTimeSpecial(hours, m.group(26));
        }
        
        temporal.getEndDate().getTime().setHours(hours);
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
