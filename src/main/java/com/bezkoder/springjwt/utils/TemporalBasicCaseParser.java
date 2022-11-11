package com.bezkoder.springjwt.utils;

import com.bezkoder.springjwt.temporal.entities.WeekOfMonth;
import com.bezkoder.springjwt.temporal.entities.DayOfWeek;
import com.bezkoder.springjwt.temporal.entities.MonthOfYear;
import com.bezkoder.springjwt.temporal.entities.TimeTag;

public class TemporalBasicCaseParser {

    public static DayOfWeek getDayOfWeek(String text) {

        if (text.equalsIgnoreCase("pazartesi") || text.equalsIgnoreCase("pte") || text.equalsIgnoreCase("pazartesiler") || text.equalsIgnoreCase("pazartesisi")) {
            return DayOfWeek.MO;
        }
        if (text.equalsIgnoreCase("salı") || text.equalsIgnoreCase("sal") || text.equalsIgnoreCase("salılar") || text.equalsIgnoreCase("salısı")) {
            return DayOfWeek.TU;
        }
        if (text.equalsIgnoreCase("çarşamba") || text.equalsIgnoreCase("çar") || text.equalsIgnoreCase("çarşambalar") || text.equalsIgnoreCase("çarşambası")) {
            return DayOfWeek.WE;
        }
        if (text.equalsIgnoreCase("perşembe") || text.equalsIgnoreCase("per") || text.equalsIgnoreCase("perşembeler") || text.equalsIgnoreCase("perşembesi")) {
            return DayOfWeek.TH;
        }
        if (text.equalsIgnoreCase("cuma") || text.equalsIgnoreCase("cum") || text.equalsIgnoreCase("cumalar") || text.equalsIgnoreCase("cuması")) {
            return DayOfWeek.FR;
        }
        if (text.equalsIgnoreCase("cumartesi") || text.equalsIgnoreCase("cte") || text.equalsIgnoreCase("cumartesiler") || text.equalsIgnoreCase("cumartesisi")) {
            return DayOfWeek.SA;
        }
        if (text.equalsIgnoreCase("pazar") || text.equalsIgnoreCase("paz") || text.equalsIgnoreCase("pazarlar") || text.equalsIgnoreCase("pazarı")) {
            return DayOfWeek.SU;
        }
        return null;
    }

    public static MonthOfYear getMonthOfYear(String text) {
        if (text == null) {
            return null;
        }
        if (text.equalsIgnoreCase("ocak") || text.equalsIgnoreCase("oca") || text.equalsIgnoreCase("ocağ")) {
            return MonthOfYear.JANUARY;
        }
        if (text.equalsIgnoreCase("şubat") || text.equalsIgnoreCase("şub")) {
            return MonthOfYear.FEBRUARY;
        }
        if (text.equalsIgnoreCase("mart") || text.equalsIgnoreCase("mar")) {
            return MonthOfYear.MARCH;
        }
        if (text.equalsIgnoreCase("nisan") || text.equalsIgnoreCase("nis")) {
            return MonthOfYear.APRIL;
        }
        if (text.equalsIgnoreCase("mayıs") || text.equalsIgnoreCase("may")) {
            return MonthOfYear.MAY;
        }
        if (text.equalsIgnoreCase("haziran") || text.equalsIgnoreCase("haz")) {
            return MonthOfYear.JUNE;
        }
        if (text.equalsIgnoreCase("temmuz") || text.equalsIgnoreCase("tem")) {
            return MonthOfYear.JULY;
        }
        if (text.equalsIgnoreCase("ağustos") || text.equalsIgnoreCase("ağu")) {
            return MonthOfYear.AUGUST;
        }
        if (text.equalsIgnoreCase("eylül") || text.equalsIgnoreCase("eyl")) {
            return MonthOfYear.SEPTEMBER;
        }
        if (text.equalsIgnoreCase("ekim") || text.equalsIgnoreCase("eki")) {
            return MonthOfYear.OCTOBER;
        }
        if (text.equalsIgnoreCase("kasım") || text.equalsIgnoreCase("kas")) {
            return MonthOfYear.NOVEMBER;
        }
        if (text.equalsIgnoreCase("aralık") || text.equalsIgnoreCase("ara") || text.equalsIgnoreCase("aralığ")) {
            return MonthOfYear.DECEMBER;
        }
        return null;
    }

    public static WeekOfMonth getWeekOfMonth(String text) {

        if (text.equalsIgnoreCase("birinci") || text.equalsIgnoreCase("1") || text.equalsIgnoreCase("ilk") ) {
            return WeekOfMonth.FIRST;
        }
        if (text.equalsIgnoreCase("ikinci") || text.equalsIgnoreCase("2")) {
            return WeekOfMonth.SECOND;
        }
        if (text.equalsIgnoreCase("üçüncü") || text.equalsIgnoreCase("3")) {
            return WeekOfMonth.THIRD;
        }
        if (text.equalsIgnoreCase("dördüncü") || text.equalsIgnoreCase("4")) {
            return WeekOfMonth.FOURTH;
        }
        if (text.equalsIgnoreCase("beşinci") || text.equalsIgnoreCase("5")) {
            return WeekOfMonth.FIFTH;
        }

        if (text.equalsIgnoreCase("sonuncu")|| text.equalsIgnoreCase("son")) {
            return WeekOfMonth.LAST;
        }

        return null;
    }

    public static int getDayOfWeekFromOrder(String text) {

        if (text.equalsIgnoreCase("birinci") || text.equalsIgnoreCase("ilk") || text.equalsIgnoreCase("1")) {
            return 1;
        }
        if (text.equalsIgnoreCase("ikinci") || text.equalsIgnoreCase("2")) {
            return 2;
        }
        if (text.equalsIgnoreCase("üçüncü") || text.equalsIgnoreCase("3")) {
            return 3;
        }
        if (text.equalsIgnoreCase("dördüncü") || text.equalsIgnoreCase("4")) {
            return 4;
        }
        if (text.equalsIgnoreCase("beşinci") || text.equalsIgnoreCase("5")) {
            return 5;
        }

        if (text.equalsIgnoreCase("altıncı") || text.equalsIgnoreCase("6")) {
            return 6;
        }

        if (text.equalsIgnoreCase("yedinci") || text.equalsIgnoreCase("7")) {
            return 7;
        }

        if (text.equalsIgnoreCase("sekizinci") || text.equalsIgnoreCase("8")) {
            return 8;
        }
        if (text.equalsIgnoreCase("dokuzuncu") || text.equalsIgnoreCase("9")) {
            return 9;
        }
        if (text.equalsIgnoreCase("onuncu") || text.equalsIgnoreCase("10")) {
            return 10;
        }
        if (text.equalsIgnoreCase("on birinci") || text.equalsIgnoreCase("11")) {
            return 11;
        }
        if (text.equalsIgnoreCase("on ikinci") || text.equalsIgnoreCase("12")) {
            return 12;
        }
        if (text.equalsIgnoreCase("on üçüncü") || text.equalsIgnoreCase("13")) {
            return 13;
        }
        if (text.equalsIgnoreCase("on dördüncü") || text.equalsIgnoreCase("14")) {
            return 14;
        }
        if (text.equalsIgnoreCase("ön beşinci") || text.equalsIgnoreCase("15")) {
            return 15;
        }
        if (text.equalsIgnoreCase("on altıncı") || text.equalsIgnoreCase("16")) {
            return 16;
        }
        if (text.equalsIgnoreCase("on yedinci") || text.equalsIgnoreCase("17")) {
            return 17;
        }
        if (text.equalsIgnoreCase("on sekizinci") || text.equalsIgnoreCase("18")) {
            return 18;
        }
        if (text.equalsIgnoreCase("on dokuzuncu") || text.equalsIgnoreCase("19")) {
            return 19;
        }

        if (text.equalsIgnoreCase("yirminci") || text.equalsIgnoreCase("20")) {
            return 20;
        }

        if (text.equalsIgnoreCase("yirmi birinci") || text.equalsIgnoreCase("21")) {
            return 21;
        }

        if (text.equalsIgnoreCase("yirmi ikinci") || text.equalsIgnoreCase("22")) {
            return 22;
        }

        if (text.equalsIgnoreCase("yirmi üçüncü") || text.equalsIgnoreCase("23")) {
            return 23;
        }

        if (text.equalsIgnoreCase("yirmi dördüncü") || text.equalsIgnoreCase("24")) {
            return 24;
        }

        if (text.equalsIgnoreCase("yirmi beşinci") || text.equalsIgnoreCase("25")) {
            return 25;
        }
        if (text.equalsIgnoreCase("yirmi altıncı") || text.equalsIgnoreCase("26")) {
            return 26;
        }
        if (text.equalsIgnoreCase("yirmi yedinci") || text.equalsIgnoreCase("27")) {
            return 27;
        }
        if (text.equalsIgnoreCase("yirmi sekizinci") || text.equalsIgnoreCase("28")) {
            return 28;
        }
        if (text.equalsIgnoreCase("yirmi dokuzuncu") || text.equalsIgnoreCase("29")) {
            return 29;
        }
        if (text.equalsIgnoreCase("otuzuncu") || text.equalsIgnoreCase("30")) {
            return 30;
        }
        if (text.equalsIgnoreCase("otuz birinci") || text.equalsIgnoreCase("31")) {
            return 31;
        }

        return 0;
    }

    public static int getIntFromBasicTerm(String text) {

        if (text.equalsIgnoreCase("bir")) {
            return 1;
        }
        if (text.equalsIgnoreCase("iki")) {
            return 2;
        }
        if (text.equalsIgnoreCase("üç")) {
            return 3;
        }
        if (text.equalsIgnoreCase("dört")) {
            return 4;
        }
        if (text.equalsIgnoreCase("beş")) {
            return 5;
        }

        if (text.equalsIgnoreCase("altı")) {
            return 6;
        }

        if (text.equalsIgnoreCase("yedi")) {
            return 7;
        }

        if (text.equalsIgnoreCase("sekiz")) {
            return 8;
        }
        if (text.equalsIgnoreCase("dokuz")) {
            return 9;
        }
        if (text.equalsIgnoreCase("on")) {
            return 10;
        }
        if (text.equalsIgnoreCase("on bir")) {
            return 11;
        }
        if (text.equalsIgnoreCase("on iki")) {
            return 12;
        }
        if (text.equalsIgnoreCase("on üç")) {
            return 13;
        }
        if (text.equalsIgnoreCase("on dört")) {
            return 14;
        }
        if (text.equalsIgnoreCase("on beş")) {
            return 15;
        }
        if (text.equalsIgnoreCase("on altı")) {
            return 16;
        }
        if (text.equalsIgnoreCase("on yedi")) {
            return 17;
        }
        if (text.equalsIgnoreCase("on sekiz")) {
            return 18;
        }
        if (text.equalsIgnoreCase("on dokuz")) {
            return 19;
        }

        if (text.equalsIgnoreCase("yirmi")) {
            return 20;
        }

        if (text.equalsIgnoreCase("yirmi bir")) {
            return 21;
        }

        if (text.equalsIgnoreCase("yirmi iki")) {
            return 22;
        }

        if (text.equalsIgnoreCase("yirmi üç")) {
            return 23;
        }

        if (text.equalsIgnoreCase("yirmi dört")) {
            return 24;
        }

        if (text.equalsIgnoreCase("yirmi beş")) {
            return 25;
        }
        if (text.equalsIgnoreCase("yirmi altı")) {
            return 26;
        }
        if (text.equalsIgnoreCase("yirmi yedi")) {
            return 27;
        }
        if (text.equalsIgnoreCase("yirmi sekiz")) {
            return 28;
        }
        if (text.equalsIgnoreCase("yirmi dokuz")) {
            return 29;
        }
        if (text.equalsIgnoreCase("otuz")) {
            return 30;
        }
        if (text.equalsIgnoreCase("kırk")) {
            return 40;
        }
        if (text.equalsIgnoreCase("elli")) {
            return 50;
        }
        if (text.equalsIgnoreCase("altmış")) {
            return 60;
        }
        if (text.equalsIgnoreCase("yetmiş")) {
            return 70;
        }
        if (text.equalsIgnoreCase("seksen")) {
            return 80;
        }
        if (text.equalsIgnoreCase("doksan")) {
            return 90;
        }
        if (text.equalsIgnoreCase("yüz")) {
            return 100;
        }

        return 0;
    }

    public int getTimeZone(String zone) {
        if (zone.equalsIgnoreCase("pst") || zone.equalsIgnoreCase("pacific time")) {
            return +480;
        }

        else if (zone.equalsIgnoreCase("pdt") || zone.equalsIgnoreCase("pt")) {
            return +420;
        }

        else if (zone.equalsIgnoreCase("est")) {
            return +300;
        }

        else if (zone.equalsIgnoreCase("edt") || zone.equalsIgnoreCase("et") || zone.equalsIgnoreCase("eastern daylight time")) {
            return +240;
        }

        else if (zone.equalsIgnoreCase("cst")) {
            return +360;
        } else if (zone.equalsIgnoreCase("cet") || zone.equalsIgnoreCase("central european time")) {
            return -60;
        } else if (zone.equalsIgnoreCase("cest") || zone.equalsIgnoreCase("eet")) {
            return -120;
        }

        else if (zone.equalsIgnoreCase("bst")) {
            return -60;
        }

        else if (zone.equalsIgnoreCase("eest") || zone.equalsIgnoreCase("eastern europe summer time")) {
            return -180;
        }

        else if (zone.equalsIgnoreCase("gmt")) {
            return 0;
        } else if (zone.equalsIgnoreCase("ist")) {
            return -60;
        } else if (zone.equalsIgnoreCase("msd")) {
            return -240;
        } else if (zone.equalsIgnoreCase("msk")) {
            return -240;
        } else if (zone.equalsIgnoreCase("west")) {
            return -60;
        } else if (zone.equalsIgnoreCase("wet")) {
            return 0;
        } else if (zone.equalsIgnoreCase("cst")) {
            return -480;
        } else if (zone.equalsIgnoreCase("hkt")) {
            return -480;
        } else if (zone.equalsIgnoreCase("idt")) {
            return -180;
        } else if (zone.equalsIgnoreCase("jst")) {
            return -540;
        } else if (zone.equalsIgnoreCase("cst")) {
            return +360;
        } else if (zone.equalsIgnoreCase("est")) {
            return +300;
        }
        return 0;

    }


    public static int getStringtoInt(String text){
        return Integer.parseInt(text);
    }

    public static TimeTag getTimeTag(String tag) {
        tag=tag.toLowerCase();
        if (tag.equalsIgnoreCase("sonra") || tag.equalsIgnoreCase("geçe")) {
            return TimeTag.AFTER;
        }
        return TimeTag.BEFORE;
    }

}
