package com.bezkoder.springjwt.rules.timeofday;

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
import com.bezkoder.springjwt.utils.TemporalParser;

// between 7pm to midnight

public class TimeIntervalRule17 extends ExtractionRule {

	private TemporalParser parser;
	protected Locale locale = Locale.US;
	protected double confidence = 0.8;
	private int priority = 6;
	private String rule="("+TemporalConstants.TIME_OF_DAY+")\\s(saat\\s)?([0-9]|0[0-9]|1[0-9]|2[0-3])('|\\s*)((den|ten|dan|tan)\\s)((((b)((u|ugün)))\\s)?(("+TemporalConstants.TIME_OF_DAY+"))\\s)(saat\\s)?([0-9]|0[0-9]|1[0-9]|2[0-3])((:|.)([0-5][0-9]))?('|\\s*)(e|ye|ya|a)\\s(kadar)?";
	private String ruleEn = "\\b(from|between)[\\s]*"
			+ "([01]?[0-9]|2[0-3])[\\s]*(([p,P][.]?[m,M][.]?)|([a,A][.]?[m,M]\\.?))[\\s]*(to|and)[\\s]*"
			+ TemporalConstants.TIME_OF_DAY + "\\b";
	protected String example = "sabah 8'ten öğlen 14.00'a kadar";
	protected UUID id = UUID.fromString("ae135d69-9fcc-4014-9c1c-f02754be012a");

	public TimeIntervalRule17() {
		parser = new TemporalParser();
	}

	@Override
	public Type getType() {
		return Type.TIME_INTERVAL;

	}

	@Override
	public List<Temporal> getTemporal(String text) {
		Matcher m = Utils.getMatch(rule, text);
		Temporal temporal = null;

        // for (int i = 1; i < 60; i++) {
        //  System.out.println(i+")-------------------------------"+m.group(i)+"------------------------");
        //  }

		 TimeDate start = new TimeDate();
		 TimeDate end = new TimeDate();
 
		 Time timeFrom = new Time();
		 Time timeTo = new Time();
 


		 if (m.group(13) != null) {
			 timeFrom.setHours(Integer.parseInt(m.group(13)));
			 if (m.group(1) != null) {
				 timeFrom.setHours(Utils.convertTimeSpecial(timeFrom.getHours(), m.group(1)));
			 }
			 if (m.group(1) == null && m.group(23) != null) {
				 timeFrom.setHours(Utils.convertTimeSpecial(timeFrom.getHours(), m.group(23)));
			 }
		 }
 
		 if (m.group(36) != null) {
			 timeTo.setHours(Integer.parseInt(m.group(36)));
			 if (m.group(23) != null) {
				 timeTo.setHours(Utils.convertTimeSpecial(timeTo.getHours(), m.group(23)));
			 }
		 }
 
		 if (m.group(39) != null) {
			 timeTo.setMinutes(Integer.parseInt(m.group(39)));
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
