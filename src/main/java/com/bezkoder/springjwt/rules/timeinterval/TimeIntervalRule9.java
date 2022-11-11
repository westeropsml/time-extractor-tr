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

// 9am till 6pm

public class TimeIntervalRule9 extends ExtractionRule {

    protected Locale locale = Locale.US;
    protected double confidence = 0.8;
    private int priority = 5;
    private String rule="\\b(((ö\\.?(s|ö)\\.?)|(Ö\\.?(S|Ö)\\.?))\\s)(saat\\s)?(([0-9]|0[0-9]|1[0-9]|2[0-3]))('|\\s*)((den|ten|dan|tan)\\s)(((ö\\.?(s|ö)\\.?)|(Ö\\.?(S|Ö)\\.?))\\s)(([0-9]|0[0-9]|1[0-9]|2[0-3]))('|\\s*)(e|ye|ya|a)\\s(kadar)?";
    private String ruleEn = "\\b((from|between)[\\s]*)?(([0-9]|0[0-9]|1[0-9]|2[0-3]))[\\s]?(([p,P][.]?[m,M]?)|([a,A][.]?[m,M]?))?[\\s]*(to|till|until|before|-)[\\s]*(([0-9]|0[0-9]|1[0-9]|2[0-3]))[\\s]?(([p,P][.]?[m,M]?)|([a,A][.]?[m,M]?))\\b";
    protected String example = "9am till 6pm";
    protected String exampleTr = "öö 9'dan ös 6'ya kadar";
    protected UUID id = UUID.fromString("730197fe-92c5-4e12-bfba-b13b6335a2a9");

    public TimeIntervalRule9() {

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

        // for (int i = 1; i < 60; i++) {
        //  System.out.println(i+")-------------------------------"+m.group(i)+"------------------------");
        //  }

 


		 if (m.group(8) != null) {
			 timeFrom.setHours(Integer.parseInt(m.group(8)));
			 if (m.group(2) != null) {
				 timeFrom.setHours(Utils.convertTimeSpecial(timeFrom.getHours(), m.group(2)));
			 }
			 if (m.group(2) == null && m.group(14) != null) {
				 timeFrom.setHours(Utils.convertTimeSpecial(timeFrom.getHours(), m.group(14)));
			 }
		 }
 
		 if (m.group(19) != null) {
			 timeTo.setHours(Integer.parseInt(m.group(19)));
			 if (m.group(14) != null) {
				 timeTo.setHours(Utils.convertTimeSpecial(timeTo.getHours(), m.group(14)));
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
