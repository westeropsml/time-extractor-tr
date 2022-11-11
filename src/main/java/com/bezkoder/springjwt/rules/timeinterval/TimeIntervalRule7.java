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
import com.bezkoder.springjwt.temporal.entities.Type;
import com.bezkoder.springjwt.utils.TemporalObjectGenerator;
import com.bezkoder.springjwt.utils.Utils;

//11.30 am-12.30 pm

public class TimeIntervalRule7 extends ExtractionRule {
    protected Locale locale = Locale.US;
    protected double confidence = 0.8;
    private int priority = 6;
    private String rule="\\b(("+TemporalConstants.TIME_OF_DAY+")\\s)(saat\\s)?([01]?[0-9]|2[0-3])(([.|:])([0-5][0-9]))?\\s?(\\s|-)\\s?(("+TemporalConstants.TIME_OF_DAY+")\\s)([01]?[0-9]|2[0-3])(([.|:])([0-5][0-9]))?\\s(arası)?";
    private String ruleEn = "\\b((from|between)[\\s]*)?(([01]?[0-9]|2[0-3]|1[0-9])([:.,]([0-5][0-9]))?)[\\s]*(([p,P][.]?[m,M]?[.]?)|([a,A][.]?[m,M]?[.]?))?[\\s]*(�|-|to|until|till|til|up to|through|thru)[\\s]*(\\b([01]?[0-9]|2[0-3]|1[0-9])([:.,]([0-5][0-9]))?)[\\s]*(([p,P][.]?[m,M][.]?)|([a,A][.]?[m,M][.]?))";
    protected String example = "11.30 am-12.30 pm";
    protected UUID id = UUID.fromString("3ab0f1b9-c85d-415b-8f62-0c355bf8de70");

    public TimeIntervalRule7() {

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
        //  for (int i = 1; i < 60; i++) {
        //    System.out.println(i+")-------------------------------"+m.group(i)+"------------------------");
        //   }

        if (m.group(14) != null) {
            timeFrom.setHours(Integer.parseInt(m.group(14)));
            if (m.group(4) != null) {
                timeFrom.setHours(Utils.convertTimeSpecial(timeFrom.getHours(), m.group(4)));
            }
            if (m.group(4) == null && m.group(20) != null) {
                timeFrom.setHours(Utils.convertTimeSpecial(timeFrom.getHours(), m.group(20)));
            }
        }
        if (m.group(17) != null) {
            timeFrom.setMinutes(Integer.parseInt(m.group(17)));
        }

        if (m.group(31) != null) {
            timeTo.setHours(Integer.parseInt(m.group(31)));
            if (m.group(20) != null) {
                timeTo.setHours(Utils.convertTimeSpecial(timeTo.getHours(), m.group(20)));
            }
        }

        if (m.group(34) != null) {
            timeTo.setMinutes(Integer.parseInt(m.group(34)));
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
