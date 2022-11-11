package com.bezkoder.springjwt.rules.time;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;

import com.bezkoder.springjwt.models.ExtractionRule;
import com.bezkoder.springjwt.temporal.entities.Temporal;
import com.bezkoder.springjwt.temporal.entities.Time;
import com.bezkoder.springjwt.temporal.entities.Type;
import com.bezkoder.springjwt.utils.TemporalObjectGenerator;
import com.bezkoder.springjwt.utils.Utils;
import com.bezkoder.springjwt.constants.TemporalConstants;

// at 5:30 pm
public class Time3Rule extends ExtractionRule {
    protected Locale locale = Locale.US;
    protected double confidence = 0.8;
    private int priority = 4;
    private String rule="("+TemporalConstants.TIME_OF_DAY+")\\s([0-9]|0[0-9]|1[0-9]|2[0-3])([:.]([0-5][0-9]))?(\\s?|')(de|da|te|ta)";
    private String ruleEn = "\\b(at[\\s]*|about[\\s]*|at about[\\s]*|around[\\s]*)?(([01]?[0-9]|2[0-3])[\\s]*(([:.,]?)([0-5][0-9]))?)[\\s]*(([p,P][.]?[m,M][.]?)|([a,A][.]?[m,M][.]?))(?!\\w)";
    protected String example = "at 5:30 pm";
    protected UUID id = UUID.fromString("e1f227da-71c0-42eb-988e-ce29ccd4c7f8");

    public Time3Rule() {
    }

    @Override
    public Type getType() {
        return Type.TIME;
    }

    @Override
    public List<Temporal> getTemporal(String text) {
        Matcher m = Utils.getMatch(rule, text);
        System.out.println(rule);
        // for (int i = 0; i < 64; i++) {
        //     System.out.println(i+")-------------------------------"+m.group(i)+"---------------------");
        // }
        Time time = new Time();
        int hours = Integer.parseInt(m.group(12));
        hours = Utils.convertTimeSpecial(hours, m.group(1));
        if (m.group(14) != null) {
            int minutes = Integer.parseInt(m.group(14));
            time.setMinutes(minutes);
        }
        time.setHours(hours);
        Temporal temporal = TemporalObjectGenerator.generateTemporalTime(type, time);
        List<Temporal> temporalList = new ArrayList<Temporal>();
        temporalList.add(temporal);
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

    public UUID getId() {
        return id;
    }
}