package com.bezkoder.springjwt.rules.timeinterval;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;

import com.bezkoder.springjwt.constants.TemporalConstants;
import com.bezkoder.springjwt.models.ExtractionRule;
import com.bezkoder.springjwt.temporal.entities.Temporal;
import com.bezkoder.springjwt.temporal.entities.Time;
import com.bezkoder.springjwt.temporal.entities.TimeDate;
import com.bezkoder.springjwt.temporal.entities.TimeTag;
import com.bezkoder.springjwt.temporal.entities.Type;
import com.bezkoder.springjwt.utils.TemporalBasicCaseParser;
import com.bezkoder.springjwt.utils.TemporalObjectGenerator;
import com.bezkoder.springjwt.utils.Utils;

// until 5.33 am, after 5pm 

public class TimeIntervalRule5 extends ExtractionRule {

    protected Locale locale = Locale.US;
    protected double confidence = 0.8;
    private int priority = 5;
    private String rule = "\\b("+TemporalConstants.TIME_OF_DAY+")[\\s](saat[\\s])?([0-9]|0[0-9]|1[0-9]|2[0-3])((:|.)([0-5][0-9]))?([\\s]?|')[\\s]?(((a|e|ye|ya|ya)|(den|dan|ten|tan))([\\s](sonra|önce|kadar)))";
    private String ruleEn = "((after|before|until|till|til|by|past)[\\s]*([01]?[0-9]|2[0-3])(([.|:])([0-5][0-9]))?[\\s]*(([p,P][.]?[m,M][.]?)|([a,A][.]?[m,M][.]?)))";
    protected String example="öğlen 1'den önce";
    protected String exampleEn = "after 19pm, until 5.33 am";
    protected UUID id = UUID.fromString("1a7522ee-7abe-4595-af9e-8cff79d8d88a");

    public TimeIntervalRule5() {
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
        Time time = new Time();
        Temporal temporal = null;
        // for (int i = 1; i < 60; i++) {
        //    System.out.println(i+")-------------------------------"+m.group(i)+"------------------------");
        //  }
        

        if (m.group(13) != null) {
            int hours = Integer.parseInt(m.group(13));
            hours = Utils.convertTimeSpecial(hours, m.group(1));
            time.setHours(hours);
        }
        if (m.group(16) != null) {
            time.setMinutes(Integer.parseInt(m.group(16)));
        }
        TimeTag tag = TemporalBasicCaseParser.getTimeTag(m.group(23));
        if (tag == TimeTag.AFTER) {
            start.setTime(time);
            temporal = TemporalObjectGenerator.generateTemporalTime(Type.TIME_INTERVAL, start, null); //akşam 7'ye kadar
        } else {
            end.setTime(time);
            temporal = TemporalObjectGenerator.generateTemporalTime(Type.TIME_INTERVAL, null, end); //sabah 
        }
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
