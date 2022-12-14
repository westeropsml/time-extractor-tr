package com.bezkoder.springjwt.rules.timeinterval;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;

import com.bezkoder.springjwt.constants.TemporalConstants;
import com.bezkoder.springjwt.models.ExtractionRule;
import com.bezkoder.springjwt.temporal.entities.Temporal;
import com.bezkoder.springjwt.temporal.entities.TimeDate;
import com.bezkoder.springjwt.temporal.entities.Type;
import com.bezkoder.springjwt.utils.TemporalObjectGenerator;
import com.bezkoder.springjwt.temporal.entities.Time;
import com.bezkoder.springjwt.utils.Utils;

//5-9pm

public class TimeIntervalRule6 extends ExtractionRule {

    protected Locale locale = Locale.US;
    protected double confidence = 0.8;
    private int priority = 5;
    private String rule="\\b("+TemporalConstants.TIME_OF_DAY+"\\s)([0-9]|0[0-9]|1[0-9]|2[0-3])((\\sile|'(le|la))|('(ten|tan|den|dan)))\\s([0-9]|0[0-9]|1[0-9]|2[0-3])(\\s(arası)|('(e|a|ye|ya))\\s(kadar))";
    private String ruleEn = "\\b(from[\\s]*)?(([01]?[0-9]|2[0-3]|1[0-9])[-]([01]?[0-9]|2[0-3]|1[0-9])(([p,P][.]?[m,M][.]?)|([a,A][.]?[m,M][.]?)\\b))";
    protected String example = "from 5-9pm, 7-10am";
    protected UUID id = UUID.fromString("b2360b5c-59cc-4689-91f1-9325ac219e06");

    public TimeIntervalRule6() {

    }

    @Override
    public Type getType() {
        return Type.TIME_INTERVAL;

    }

    @Override
    public List<Temporal> getTemporal(String text) {
        Matcher m = Utils.getMatch(rule, text);
        TimeDate start = new TimeDate();
        TimeDate end = new TimeDate();

        Time timeFrom = new Time();
        Time timeTo = new Time();

        Temporal temporal = null;

         //for (int i = 1; i < 24; i++) {
         //  System.out.println(i+")-------------------------------"+m.group(i)+"------------------------");
         // }
        

        if (m.group(12) != null) {
            timeFrom.setHours(Integer.parseInt(m.group(12)));
            if (m.group(4) != null) {
                timeFrom.setHours(Utils.convertTimeSpecial(timeFrom.getHours(), m.group(4)));
            }
        }
        if (m.group(18) != null) {
            timeTo.setHours(Integer.parseInt(m.group(18)));
            if (m.group(4) != null) {
                timeTo.setHours(Utils.convertTimeSpecial(timeTo.getHours(), m.group(4)));
            }
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
