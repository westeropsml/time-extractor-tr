package com.bezkoder.springjwt.rules.timeinterval;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;

import com.bezkoder.springjwt.models.ExtractionRule;
import com.bezkoder.springjwt.temporal.entities.Temporal;
import com.bezkoder.springjwt.temporal.entities.Time;
import com.bezkoder.springjwt.temporal.entities.TimeDate;
import com.bezkoder.springjwt.temporal.entities.Type;
import com.bezkoder.springjwt.utils.TemporalObjectGenerator;
import com.bezkoder.springjwt.utils.Utils;

// from 10 to 11:00

public class TimeIntervalRule15 extends ExtractionRule {

    protected Locale locale = Locale.US;
    protected double confidence = 0.8;
    private int priority = 5;
    private String rule = "\\b(saat\\s)?([01]?[0-9]|2[0-3])('|\\s*)(den|dan|ten|tan)(\\s)(saat\\s)?([01]?[0-9]|2[0-3])(\\s)?(\\.|,|:)([0-5][0-9])('|\\s*)(e|a|ye|ya)((\\s)kadar)?\\b";
    private String ruleEn = "\\b((from|between)[\\s]*)([01]?[0-9]|2[0-3])[\\s]*(to|and)[\\s]*([01]?[0-9]|2[0-3])[\\s]*[.|:]([0-5][0-9])";
    protected String example = "20'den 21.30'a, saat 20'den 21:30'a, saat 20'den saat 21.30'a kadar";
    //saat 20'den saat 21.30'a kadar hatalı
    protected String exampleEn = "from 20 to 21.30";
    protected UUID id = UUID.fromString("ee72d702-edee-4431-80f5-15fa34b108bc");

    public TimeIntervalRule15() {

    }

    @Override
    public Type getType() {
        return Type.TIME_INTERVAL;

    }

    @Override
    public List<Temporal> getTemporal(String text) {
        Matcher m = Utils.getMatch(rule, text);

        // for (int i = 1; i < 60; i++) {
        //    System.out.println(i+")-------------------------------"+m.group(i)+"------------------------");
        //  }

        TimeDate start = new TimeDate();
        TimeDate end = new TimeDate();

        Time timeFrom = new Time();
        Time timeTo = new Time();

        Temporal temporal = null;

        if (m.group(2) != null) {
            timeFrom.setHours(Integer.parseInt(m.group(2)));
        }

        if (m.group(7) != null) {
            timeTo.setHours(Integer.parseInt(m.group(7)));

        }

        if (m.group(10) != null) {
            timeTo.setMinutes(Integer.parseInt(m.group(10)));
        }

        start.setTime(timeFrom);
        end.setTime(timeTo);

        temporal = TemporalObjectGenerator.generateTemporalTime(Type.TIME_INTERVAL, start, end);
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
