package com.bezkoder.springjwt.rules.date.dayofweek;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;

import com.bezkoder.springjwt.models.ExtractionRule;
import com.bezkoder.springjwt.constants.TemporalConstants;
import com.bezkoder.springjwt.temporal.entities.Date;
import com.bezkoder.springjwt.temporal.entities.DayOfWeek;
import com.bezkoder.springjwt.temporal.entities.Temporal;
import com.bezkoder.springjwt.temporal.entities.Type;
import com.bezkoder.springjwt.utils.TemporalBasicCaseParser;
import com.bezkoder.springjwt.utils.TemporalObjectGenerator;
import com.bezkoder.springjwt.utils.Utils;

// Sunday 16 2014
public class DayOfWeekRule3 extends ExtractionRule {

    protected Locale locale = Locale.US;
    protected double confidence = 0.8;
    private int priority = 3;

    private String rule = "(" + TemporalConstants.DAY_OF_WEEK + "|" + TemporalConstants.DAY_OF_WEEK_EASY + ")[\\s]*([1-2][0-9]|[3][0-1]|[1-9])[.]?[,]?[\\s]*(([12][0-9])?(\\d\\d))";
    private String ruleEn = "((" + TemporalConstants.DAY_OF_WEEK + "|" + TemporalConstants.DAY_OF_WEEK_EASY + ")[s]?[.]?[,]?[\\s]*([1-2][0-9]|[3][0-1]|[1-9])[,]?[\\s]*([12][0-9]\\d\\d))";

    protected String example = "Pazar 16 2014";
    protected String exampleEn = "Sunday 16 2014";
    protected UUID id = UUID.fromString("c099f256-a339-40f2-a562-b43c1f767c70");

    public DayOfWeekRule3() {
    }

    public Type getType() {
        return Type.DATE;
    }

    @Override
    public List<Temporal> getTemporal(String text) {
        Matcher m = Utils.getMatch(rule, text);

        /*
        for (int i = 1; i < 60; i++) {
        System.out.println(i+")-------------------------------"+m.group(i)+"------------------------");
        }*/

        DayOfWeek dayOfWeek = null;
        int dayOfMonth = 0;
        int year = 0;

        dayOfWeek = TemporalBasicCaseParser.getDayOfWeek(m.group(1));
        dayOfMonth = Integer.parseInt(m.group(4));
        year = Integer.parseInt(m.group(5));

        Date date = new Date();
        date.setDayOfWeek(dayOfWeek);
        date.setDay(dayOfMonth);
        date.setYear(year);

        Temporal temporal = TemporalObjectGenerator.generateTemporalDate(type, date);

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

    public void setId(UUID id) {
        this.id = id;
    }

}
