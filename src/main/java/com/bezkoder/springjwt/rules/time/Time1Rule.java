package com.bezkoder.springjwt.rules.time;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;

import com.bezkoder.springjwt.models.ExtractionRule;
import com.bezkoder.springjwt.constants.TemporalConstants;
import com.bezkoder.springjwt.temporal.entities.Temporal;
import com.bezkoder.springjwt.temporal.entities.Type;
import com.bezkoder.springjwt.utils.TemporalBasicCaseParser;
import com.bezkoder.springjwt.utils.TemporalObjectGenerator;
import com.bezkoder.springjwt.temporal.entities.Time;
import com.bezkoder.springjwt.utils.Utils;

// at 5 pm CET

public class Time1Rule extends ExtractionRule {
    private TemporalBasicCaseParser parser = new TemporalBasicCaseParser();
    protected Locale locale = Locale.US;
    protected double confidence = 0.8;
    private int priority = 3;
    private String rule="("+TemporalConstants.TIME_ZONE+")\\s+((zaman|vakit|saat|vakt)((ıyla|iyle|ı ile|i ile)|(\\sdilimi)((\\sile|yle))))\\s+(saat\\s)?("+TemporalConstants.TIME_OF_DAY+"\\s)(saat\\s)?([01]?[0-9]|2[0-3])(([.|:])([0-5][0-9]))?('|[\\s]*)?(de|te|da)";
    private String ruleEn = "\\b(((at about|at|about)[\\s]*)?((([0-9]|0[0-9]|1[0-9]|2[0-3]))[\\s]*(([p,P][.]?[m,M][.]?)|([a,A][.]?[m,M][.]?)))(?!\\S)[\\s]*" + TemporalConstants.TIME_ZONE + "?)";
    protected String example = " at 5 pm CET";
    protected UUID id = UUID.fromString("27d68b76-15cd-4272-8724-bc442197141b");

    public Time1Rule() {
    }

    @Override
    public Type getType() {
        return Type.TIME;
    }

    @Override
    public List<Temporal> getTemporal(String text) {
        Matcher m = Utils.getMatch(rule, text);
        System.out.println(rule);
        // for (int i = 0; i < 120; i++) {
        //     System.out.println(i+")--------------------------"+m.group(i)+"---------------------------");
        // }
        Time time = new Time();
        int hours = Integer.parseInt(m.group(30));
        if (m.group(19) != null) {
            hours = Utils.convertTimeSpecial(hours, m.group(19));
        }
        if (m.group(33) != null) {
            int minutes = Integer.parseInt(m.group(33));
            time.setMinutes(minutes);
        }
        time.setHours(hours);
        int timezone = 0;
        if (m.group(1) != null) {
            timezone = parser.getTimeZone(m.group(1));
            time.setTimezone(timezone);
        }
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