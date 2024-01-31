package util;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class DateTimeUtils {

	public static final String FORMAT_300 = "yyyyMMddHHmmssS";
	public static final String FORMAT_303 = "yyyyMMddHHmmss";
	public static final String FORMAT_306 = "yyyyMMddHHmm";
	public static final String FORMAT_309 = "yyyyMMdd";
	
	public static final String FORMAT_200 = "yyyy-MM-dd HH:mm:ss.S";
	public static final String FORMAT_203 = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT_206 = "yyyy-MM-dd HH:mm";
	public static final String FORMAT_209 = "yyyy-MM-dd";
	
	public static final String FORMAT_006 = "MMM dd, yyyy @ HH:mm:ss.SSS";
	
	
	public static final String FORMAT_100 = "dd/MM/yyyy HH:mm:ss.S";
	public static final String FORMAT_103 = "dd/MM/yyyy HH:mm:ss";
	public static final String FORMAT_106 = "dd/MM/yyyy HH:mm";
	public static final String FORMAT_109 = "dd/MM/yyyy";
	
	
	private static final String A = " a ";
	private static final String DE = " de ";
	private static final String NO_PERIODO = "no periodo de ";
	private static final String NO_DIA = "no dia de ";

	
	
	public static String dateLogs(Date date){
		 DateFormat dateFormat = new SimpleDateFormat(DateTimeUtils.FORMAT_200);
		 return dateFormat.format(date);  
	}
	
	public static String dateyyyyMMddhhmmssS(Date date){
		 DateFormat dateFormat = new SimpleDateFormat(DateTimeUtils.FORMAT_300);
		 return dateFormat.format(date);  
	}
	
	public static String dateyyyyMMddhhmmss(Date date){
		 DateFormat dateFormat = new SimpleDateFormat(DateTimeUtils.FORMAT_303);
		 return dateFormat.format(date);  
	}

	public static String dateToString(Date date,String format){
		DateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}
	public static String dateToStringUS(Date date,String format){
		DateFormat dateFormat = new SimpleDateFormat(format, Locale.ENGLISH);
		return dateFormat.format(date);
	}



	public static Date getLastHourOfDate(Date date) {
		if (date == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return DateTimeUtils.getLastHourOfDate(cal);
	}

	public static Date getLastHourOfDate(Calendar cal) {
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}


	public static Date getFirstHourOfDate(Date date) {
		if (date == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return DateTimeUtils.getFirstHourOfDate(cal);
	}


	public static Date getFirstHourOfDate(Calendar cal) {
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * Retorna a data atual
	 * 
	 * @return
	 */
	public static Date getCurrentDate() {

//			GregorianCalendar calendar = (GregorianCalendar) new GregorianCalendar().getInstance();
//			
//			calendar.set(Calendar.HOUR_OF_DAY, 0);
//			calendar.set(Calendar.MINUTE, 0);
//			calendar.set(Calendar.SECOND, 0);
//			calendar.set(Calendar.MILLISECOND, 0);
		//
//			return calendar.getTime();
		return Calendar.getInstance().getTime();
	}

	/**
	 * Verifica se o hor�rio do primeiro par�metro est� dentro do intervalo de
	 * hor�rios dos outros dois par�metros.
	 * 
	 * @param hour
	 * @param interval1
	 * @param interval2
	 * @return
	 */
	public static boolean isOnlyTimeIntoInterval(Date hour, Date interval1, Date interval2) {

		Calendar int1 = Calendar.getInstance();
		int1.setTime(interval1);
		long vlInterval1 = (int1.get(Calendar.HOUR_OF_DAY) * 60) + int1.get(Calendar.MINUTE);

		Calendar int2 = Calendar.getInstance();
		int2.setTime(interval2);
		long vlInterval2 = (int2.get(Calendar.HOUR_OF_DAY) * 60) + int2.get(Calendar.MINUTE);

		Calendar cal = Calendar.getInstance();
		cal.setTime(hour);
		long vlCal = (cal.get(Calendar.HOUR_OF_DAY) * 60) + cal.get(Calendar.MINUTE);

		return ((vlCal >= vlInterval1) && (vlCal <= vlInterval2));
	}

	/**
	 * Semelhante � date.after(dateCompare), por�m nullsafe.
	 * 
	 * @param date
	 * @param dateCompare
	 * @return
	 */
	public static boolean isAfterThan(Date date, Date dateCompare) {
		if (date == null) {
			return false;
		}
		if (dateCompare == null) {
			return true;
		}
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(dateCompare);
		return c1.after(c2);
	}

	/**
	 * Semelhante � date.after(dateCompare), por�m nullsafe.
	 * 
	 * @param date
	 * @param dateCompare
	 * @return
	 */
	public static boolean isBeforeThan(Date date, Date dateCompare) {
		if (date == null) {
			return false;
		} else if (dateCompare == null) {
			return true;
		}
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(dateCompare);
		return c1.before(c2);
	}

	/**
	 * Verifica se o periodo est� contido em um intervalo de datas
	 * 
	 * @param dtIni
	 * @param dtFim
	 * @param dtIni1
	 * @param dtFim1
	 * @return
	 */
	public static boolean isInInterval(Date dtIni, Date dtFim, Date dtIni1, Date dtFim1) {
		return DateTimeUtils.isInInterval(dtIni, dtFim, dtIni1) || DateTimeUtils.isInInterval(dtIni, dtFim, dtFim1)
				|| DateTimeUtils.isInInterval(dtIni1, dtFim1, dtIni)
				|| DateTimeUtils.isInInterval(dtIni1, dtFim1, dtFim);
	}

	/**
	 * Verifica se o periodo est� contido em um intervalo de datas
	 * 
	 * @param dtIni
	 * @param dtFim
	 * @param dtIni1
	 * @param dtFim1
	 * @return
	 */
	public static boolean isInInterval(Calendar dtIni, Calendar dtFim, Calendar dtIni1, Calendar dtFim1) {
		return DateTimeUtils.isInInterval(dtIni, dtFim, dtIni1) || DateTimeUtils.isInInterval(dtIni, dtFim, dtFim1)
				|| DateTimeUtils.isInInterval(dtIni1, dtFim1, dtIni)
				|| DateTimeUtils.isInInterval(dtIni1, dtFim1, dtFim);
	}

	/**
	 * Verifica se uma data est� contido em um intervalo de datas.
	 * 
	 * @param dtIni
	 * @param dtFim
	 * @param dtCompare
	 * @return
	 */
	public static boolean isInInterval(Calendar dtIni, Calendar dtFim, Calendar dtCompare) {
		if ((dtIni != null) && (dtFim != null) && (dtCompare != null)) {
			return (dtCompare.compareTo(dtIni) >= 0) && (dtCompare.compareTo(dtFim) <= 0);
		}
		return false;
	}

	/**
	 * Verifica se uma data est� dentro de um intervalo de datas.
	 * 
	 * @param dtIni
	 * @param dtFim
	 * @param dtCompare
	 * @return
	 */
	public static boolean isInInterval(Date dtIni, Date dtFim, Date dtCompare) {
		if ((dtIni != null) && (dtFim != null) && (dtCompare != null)) {
			return (dtCompare.compareTo(dtIni) >= 0) && (dtCompare.compareTo(dtFim) <= 0);
		}
		return false;
	}

	/**
	 * @param dtIni
	 * @param dtFim
	 * @param hrIni
	 * @param hrFim
	 * @param dtCompare
	 * @param hrCompare
	 * @return
	 */
	public static boolean isInInterval(Calendar dtIni, Calendar dtFim, Calendar hrIni, Calendar hrFim,
			Calendar dtCompare, Calendar hrCompare) {

		if ((dtIni != null) && (dtFim != null) && (dtCompare != null)) {

			if (hrIni != null) {
				dtIni.set(Calendar.HOUR_OF_DAY, hrIni.get(Calendar.HOUR_OF_DAY));
				dtIni.set(Calendar.MINUTE, hrIni.get(Calendar.MINUTE));
				dtIni.set(Calendar.SECOND, hrIni.get(Calendar.SECOND));
			}

			if (hrFim != null) {
				dtFim.set(Calendar.HOUR_OF_DAY, hrFim.get(Calendar.HOUR_OF_DAY));
				dtFim.set(Calendar.MINUTE, hrFim.get(Calendar.MINUTE));
				dtFim.set(Calendar.SECOND, hrFim.get(Calendar.SECOND));
			}

			if (hrCompare != null) {
				dtCompare.set(Calendar.HOUR_OF_DAY, hrCompare.get(Calendar.HOUR_OF_DAY));
				dtCompare.set(Calendar.MINUTE, hrCompare.get(Calendar.MINUTE));
				dtCompare.set(Calendar.SECOND, hrCompare.get(Calendar.SECOND));
			}

			return (dtCompare.compareTo(dtIni) >= 0) && (dtCompare.compareTo(dtFim) <= 0);
		}

		return false;
	}

	/**
	 * @param dtIni
	 * @param dtFim
	 * @param hrIni
	 * @param hrFim
	 * @param dtCompare
	 * @param hrCompare
	 * @return
	 */
	public static boolean isInInterval(Date dtIni, Date dtFim, Date hrIni, Date hrFim, Date dtCompare, Date hrCompare) {

		if ((dtIni != null) && (dtFim != null) && (dtCompare != null)) {

			Calendar dtIni1 = Calendar.getInstance();
			dtIni1.setTime(dtIni);

			Calendar dtFim1 = Calendar.getInstance();
			dtFim1.setTime(dtFim);

			Calendar dtCompare1 = Calendar.getInstance();
			dtCompare1.setTime(dtCompare);

			if ((hrIni != null) && (hrFim != null) && (hrCompare != null)) {

				Calendar hrIni1 = Calendar.getInstance();
				hrIni1.setTime(hrIni);

				Calendar hrFim1 = Calendar.getInstance();
				hrFim1.setTime(hrFim);

				Calendar hrCompare1 = Calendar.getInstance();
				hrCompare1.setTime(hrCompare);

				return DateTimeUtils.isInInterval(dtIni1, dtFim1, hrIni1, hrFim1, dtCompare1, hrCompare1);
			}

			return DateTimeUtils.isInInterval(dtIni1, dtFim1, dtCompare1);
		}

		return false;
	}

	/**
	 * Verifica se os periodos se cruzam;
	 * 
	 * @param dtIni
	 * @param dtFim
	 * @param dtIni1
	 * @param dtFim1
	 * @return
	 */
	public static boolean isHourInInterval(Date dtIni, Date dtFim, Date dtIni1, Date dtFim1) {
		return DateTimeUtils.isHourInInterval(dtIni, dtFim, dtIni1)
				|| DateTimeUtils.isHourInInterval(dtIni, dtFim, dtFim1)
				|| DateTimeUtils.isHourInInterval(dtIni1, dtFim1, dtIni)
				|| DateTimeUtils.isHourInInterval(dtIni1, dtFim1, dtFim);
	}

	/**
	 * @param dtIni
	 * @param dtFim
	 * @param dtCompare
	 * @return
	 */
	private static boolean isHourInInterval(Date dtIni, Date dtFim, Date dtCompare) {

		if ((dtIni != null) && (dtFim != null) && (dtCompare != null)) {
			Calendar hrIni = Calendar.getInstance();
			hrIni.setTime(dtIni);

			Calendar hrFim = Calendar.getInstance();
			hrFim.setTime(dtFim);

			Calendar hrCompare = Calendar.getInstance();
			hrCompare.setTime(dtCompare);

			return DateTimeUtils.isHourInInterval(hrIni, hrFim, hrCompare);
		}

		return false;
	}

	/**
	 * @param dtIni
	 * @param dtFim
	 * @param dtCompare
	 * @return
	 */
	private static boolean isHourInInterval(Calendar dtIni, Calendar dtFim, Calendar dtCompare) {

		if ((dtIni != null) && (dtFim != null) && (dtCompare != null)) {

			Calendar hrIni = Calendar.getInstance();
			hrIni.setTime(DateTimeUtils.getCurrentDate());

			if (dtIni != null) {
				hrIni.set(Calendar.HOUR_OF_DAY, dtIni.get(Calendar.HOUR_OF_DAY));
				hrIni.set(Calendar.MINUTE, dtIni.get(Calendar.MINUTE));
				hrIni.set(Calendar.SECOND, dtIni.get(Calendar.SECOND));
			}

			Calendar hrFim = Calendar.getInstance();
			hrFim.setTime(DateTimeUtils.getCurrentDate());

			if (dtFim != null) {
				hrFim.set(Calendar.HOUR_OF_DAY, dtFim.get(Calendar.HOUR_OF_DAY));
				hrFim.set(Calendar.MINUTE, dtFim.get(Calendar.MINUTE));
				hrFim.set(Calendar.SECOND, dtFim.get(Calendar.SECOND));
			}

			Calendar hrCompare = Calendar.getInstance();
			hrCompare.setTime(DateTimeUtils.getCurrentDate());

			if (dtCompare != null) {
				hrCompare.set(Calendar.HOUR_OF_DAY, dtCompare.get(Calendar.HOUR_OF_DAY));
				hrCompare.set(Calendar.MINUTE, dtCompare.get(Calendar.MINUTE));
				hrCompare.set(Calendar.SECOND, dtCompare.get(Calendar.SECOND));
			}

			return (hrCompare.compareTo(hrIni) >= 0) && (hrCompare.compareTo(hrFim) <= 0);
		}

		return false;
	}


	public static long getDaysBetween(Date dtInicial, Date dtFinal) {

		long diasCorridos = 0;

		if ((dtInicial != null) && (dtFinal != null)) {

			Calendar dtIni = Calendar.getInstance();
			dtIni.setTime(dtInicial);

			Calendar dtFim = Calendar.getInstance();
			dtFim.setTime(dtFinal);

			diasCorridos = DateTimeUtils.getDaysBetween(dtIni, dtFim);
//				
//				diasCorridos = (diasCorridos + 1);
		}

		return diasCorridos;
	}

		public static long getDaysBetween(Calendar dtInicial, Calendar dtFinal) {

		long diasCorridos = 0;

		if ((dtInicial != null) && (dtFinal != null)) {

			long diferenca = dtFinal.getTimeInMillis() - dtInicial.getTimeInMillis();

			long tempoDia = 1000 * 60 * 60 * 24;

			diasCorridos = diferenca / tempoDia;

		}

		return diasCorridos;
	}
	//		public static int getDaysBetween(Date dtInicial, Date dtFinal, DiasSemana diasSemana) {
//			
//			int diasCorridos = 0;
//			
//			if (dtInicial != null && dtFinal != null) {
//				
//				Calendar dtIni = Calendar.getInstance();
//				dtIni.setTime(dtInicial);
//				
//				Calendar dtFim = Calendar.getInstance();
//				dtFim.setTime(dtFinal);
//				
//				diasCorridos = getDaysBetween(dtIni, dtFim, diasSemana);
//			}
//			
//			return diasCorridos;
//		}


//		public static int getDaysBetween(Calendar dtInicial, Calendar dtFinal, DiasSemana diasSemana) {
//			
//			int diasCorridos = 0;
//			
//			while (!dtInicial.after(dtFinal)) {
//				
//				if (isInTheDaysOfWeek(dtInicial, diasSemana)) {
//					diasCorridos += 1;
//				}
//				
//				dtInicial.add(Calendar.DATE, 1);
//			}
//			
//			return diasCorridos;
//		}


	public static long getSecondsBetween(Calendar dtInicial, Calendar dtFinal) {

		long segundosCorridos = 0;

		if ((dtInicial != null) && (dtFinal != null)) {

			long diferenca = dtFinal.getTimeInMillis() - dtInicial.getTimeInMillis();

			segundosCorridos = diferenca / 1000;

		}

		return segundosCorridos;
	}

	public static long getMinutesBetween(Calendar dtInicial, Calendar dtFinal) {

		long minutosCorridos = 0;

		if ((dtInicial != null) && (dtFinal != null)) {

			long diferenca = dtFinal.getTimeInMillis() - dtInicial.getTimeInMillis();

			long tempoMinutos = 1000 * 60;

			minutosCorridos = diferenca / tempoMinutos;

		}

		return minutosCorridos;
	}


	public static long getMinutesBetween(Date dtInicial, Date dtFinal) {

		long minutosCorridos = 0;

		if ((dtInicial != null) && (dtFinal != null)) {

			Calendar dtIni = Calendar.getInstance();
			dtIni.setTime(dtInicial);

			Calendar dtFim = Calendar.getInstance();
			dtFim.setTime(dtFinal);

			minutosCorridos = DateTimeUtils.getMinutesBetween(dtIni, dtFim);
		}

		return minutosCorridos;
	}

		public static Double getHoursBetween(Date dtInicial, Date dtFinal) {

		Double horasCorridos = null;

		if ((dtInicial != null) && (dtFinal != null)) {

			Calendar dtIni = Calendar.getInstance();
			dtIni.setTime(dtInicial);

			Calendar dtFim = Calendar.getInstance();
			dtFim.setTime(dtFinal);

			horasCorridos = DateTimeUtils.getHoursBetween(dtIni, dtFim);
		}

		return horasCorridos;
	}

		public static Double getHoursBetween(Calendar dtInicial, Calendar dtFinal) {

		Double horasCorridos = null;

		if ((dtInicial != null) && (dtFinal != null)) {

			double diferenca = dtFinal.getTimeInMillis() - dtInicial.getTimeInMillis();

			double tempoHora = 1000d * 60d * 60d;

			horasCorridos = diferenca / tempoHora;

		}

		return horasCorridos;
	}

	public static Double getMonthBetween(Date dtInicial, Date dtFinal) {

		Double mesesCorridos = null;

		if ((dtInicial != null) && (dtFinal != null)) {

			Calendar dtIni = Calendar.getInstance();
			dtIni.setTime(dtInicial);

			Calendar dtFim = Calendar.getInstance();
			dtFim.setTime(dtFinal);

			mesesCorridos = DateTimeUtils.getMonthBetween(dtIni, dtFim);
		}

		return mesesCorridos;

	}

	public static boolean validateTime(Date data1, Date data2, double intervalo) {

		if ((data1 == null) || (data2 == null))
			return false;

		double range = DateTimeUtils.getParcialMonthBetween(data1, data2);

		int diferenca = (int) (range * 10) - (int) (intervalo * 10);

		if ((diferenca > 1) || (diferenca < -1)) {
			return false;
		}
		return true;
	}

	public static Date getDateByMonths(Date date, double meses) {

		int mes = (int) meses;
		int parcial = (int) ((meses - mes) * 10);

		Calendar d1 = Calendar.getInstance();
		d1.setTime(date);
		d1.add(Calendar.MONTH, mes);

		int size = DateTimeUtils.monthSize(d1);
		int dias = parcial * (100 / size);
		d1.add(Calendar.DAY_OF_MONTH, dias);
		d1.add(Calendar.DAY_OF_MONTH, -1);

		return d1.getTime();
	}

	public static Date getDateByMonthsFinal(Date date, double meses) {

		int mes = (int) meses;
		int parcial = (int) ((meses - mes) * 10);

		Calendar d1 = Calendar.getInstance();
		d1.setTime(date);
		d1.add(Calendar.MONTH, mes);

		int size = DateTimeUtils.monthSize(d1);
		int dias = parcial * (100 / size);
		d1.add(Calendar.DAY_OF_MONTH, dias);
		d1.add(Calendar.DAY_OF_MONTH, +1);

		return d1.getTime();
	}

	public static Integer getPeriodo(Date initdate, Date finaldate) {

		Calendar d1 = Calendar.getInstance();
		d1.setTime(initdate);
		Calendar d2 = Calendar.getInstance();
		d2.setTime(finaldate);

		if (initdate.compareTo(finaldate) == 0) {
			return 1;
		} else if (initdate.compareTo(finaldate) > 0) {
			d1.setTime(finaldate);
			d2.setTime(initdate);
		}

		int anos = d2.get(Calendar.YEAR) - d1.get(Calendar.YEAR);

		int meses = d2.get(Calendar.MONTH) - d1.get(Calendar.MONTH);

		meses = meses + (anos * 12);

		// if(negativo)
		// meses = meses * -1;

		return meses + 1;

		// return (getParcialMonthBetween(initdate, date, true).intValue() + 1) *
		// (getFirstHourOfDate(initdate).compareTo(getFirstHourOfDate(date)) <= 0 ? 1 :
		// -1);
	}

	public static Double getParcialMonthBetween(Date date1, Date date2) {
		return DateTimeUtils.getParcialMonthBetween(date1, date2, false);
	}

	public static Double getParcialMonthBetween(Date date1, Date date2, boolean periodo) {

		boolean negativo = false;

		Calendar d1 = Calendar.getInstance();
		d1.setTime(date1);
		Calendar d2 = Calendar.getInstance();
		d2.setTime(date2);

		if (date1.compareTo(date2) == 0) {
			return new Double(0);
		} else if (date1.compareTo(date2) > 0) {
			d1.setTime(date2);
			d2.setTime(date1);
			negativo = true;
		}

		int meses = 0;
		int dias = 0;

		if (d2.get(Calendar.MONTH) != d1.get(Calendar.MONTH)) {
			if (d2.get(Calendar.YEAR) == d1.get(Calendar.YEAR)) {
				meses = d2.get(Calendar.MONTH) - d1.get(Calendar.MONTH);
			} else if (d2.get(Calendar.YEAR) > d1.get(Calendar.YEAR)) {
				meses = (d2.get(Calendar.MONTH) - d1.get(Calendar.MONTH)) + 12;
				meses += (d2.get(Calendar.YEAR) - d1.get(Calendar.YEAR) - 1) * 12;
			}

			if (d2.get(Calendar.DAY_OF_MONTH) >= d1.get(Calendar.DAY_OF_MONTH)) {
				dias = (d2.get(Calendar.DAY_OF_MONTH) + 1) - d1.get(Calendar.DAY_OF_MONTH);
			} else {
				dias = DateTimeUtils.monthSize(d2) - (d1.get(Calendar.DAY_OF_MONTH) - d2.get(Calendar.DAY_OF_MONTH));
				meses--;
			}
			if (periodo && DateTimeUtils.fechaMes(d1, d2)) {
				meses--;
			}
		} else {
			if (DateTimeUtils.fechaMes(d1, d2) && !periodo) {
				meses++;
			} else {
				dias = d2.get(Calendar.DAY_OF_MONTH) - d1.get(Calendar.DAY_OF_MONTH);
			}
			if (d2.get(Calendar.YEAR) != d1.get(Calendar.YEAR)) {
				meses += (d2.get(Calendar.YEAR) - d1.get(Calendar.YEAR)) * 12;
			}
		}

		double ret = 0;
		if (periodo) {
			ret = meses + (((int) ((1D / DateTimeUtils.monthSize(d2)) * dias)));
		} else {
			ret = meses + (((int) ((10D / DateTimeUtils.monthSize(d2)) * dias)) / 10D);
		}

		if (negativo) {
			ret = ret * -1;
		}

		return ret;

	}

	private static boolean fechaMes(Calendar d1, Calendar d2) {
		if (d1.get(Calendar.DAY_OF_MONTH) == 1) {
			Calendar temp = (Calendar) d2.clone();
			temp.add(Calendar.DAY_OF_MONTH, 1);
			if (temp.get(Calendar.DAY_OF_MONTH) == 1) {
				return true;
			}
		}
		if (d1.get(Calendar.DAY_OF_MONTH) == (d2.get(Calendar.DAY_OF_MONTH) + 1)) {
			return true;
		}
		return false;
	}

	private static int monthSize(Calendar c) {

		Calendar temp = (Calendar) c.clone();

		int size = temp.get(Calendar.DAY_OF_MONTH) - 1;

		if (size == 0) {
			temp.add(Calendar.DAY_OF_MONTH, 1);
			size++;
		}

		while (temp.get(Calendar.DAY_OF_MONTH) != 1) {
			temp.add(Calendar.DAY_OF_MONTH, 1);
			size++;
		}
		return size;

	}

	public static Double getMonthBetween(Calendar dtInicial, Calendar dtFinal) {

		Double mesesCorridos = null;

		if ((dtInicial != null) && (dtFinal != null)) {

//				dtFinal.add(Calendar.HOUR_OF_DAY, 23);
//				dtFinal.add(Calendar.MINUTE, 59);
//				dtFinal.add(Calendar.SECOND, 59);
			double diferenca = dtFinal.getTimeInMillis() - dtInicial.getTimeInMillis();

			double tempoMes = 1000d * 60d * 60d * 24d * 30d;

			mesesCorridos = diferenca / tempoMes;

		}

		return mesesCorridos;
	}

	/**
	 * Retorna o semestre (1 ou 2) da data passada por par�metro.
	 * 
	 * @param date
	 * @return
	 */
	public static int getSemestre(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, Calendar.JUNE);
		cal.set(Calendar.DAY_OF_MONTH, 30);
		return date.after(DateTimeUtils.getLastHourOfDate(cal)) ? 2 : 1;
	}

	/**
	 * Semestre acad�mico: a partir de 31 de julho considera como segundo semestre.
	 * 
	 * @param date
	 * @return
	 */
	public static int getSemestreAcademico(Date date) {
		if (date == null) {
			return 1;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.MONTH, Calendar.JULY);
		cal.set(Calendar.DAY_OF_MONTH, 31);

		return date.after(DateTimeUtils.getLastHourOfDate(cal)) ? 2 : 1;
	}

	public static Calendar adjustWeekendtoFriday(Calendar calendar) {
		if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			calendar.add(Calendar.DATE, -2);
		} else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
			calendar.add(Calendar.DATE, -1);
		}
		return calendar;
	}

	public static ArrayList<Date> getDatesCollisions(Date dtIni, Date dtFim, Date dtIni1, Date dtFim1) {

		ArrayList<Date> dates = new ArrayList<Date>();

		Date dataInicial = dtIni.after(dtIni1) ? dtIni : dtIni1;
		Date dataFinal = dtFim.before(dtFim1) ? dtFim : dtFim1;

		dates.add(dataInicial);
		dates.add(dataFinal);

		return dates;
	}

	public static ArrayList<Calendar> getDatesCollisions(Calendar dtIni, Calendar dtFim, Calendar dtIni1,
			Calendar dtFim1) {

		ArrayList<Calendar> dates = new ArrayList<Calendar>();

		Calendar dataInicial = null;
		Calendar dataFinal = null;

		if (DateTimeUtils.isInInterval(dtIni, dtFim, dtIni1)) {
			dataInicial = dtIni1;
		}

		if (DateTimeUtils.isInInterval(dtIni, dtFim, dtFim1)) {
			dataFinal = dtFim;
		}

		if (DateTimeUtils.isInInterval(dtIni1, dtFim1, dtIni)) {
			dataInicial = dtIni;
		}

		if (DateTimeUtils.isInInterval(dtIni1, dtFim1, dtFim)) {
			dataFinal = dtFim;
		}

		dates.add(dataInicial);
		dates.add(dataFinal);

		return dates;
	}

//		public static boolean isInTheDaysOfWeek(Date dtCompare, DiasSemana diasSemana) {
//			
//			Calendar dateCompare = Calendar.getInstance();
//			dateCompare.setTime(dtCompare);
//			
//			return isInTheDaysOfWeek(dateCompare, diasSemana);
//		}

//		public static boolean isInTheDaysOfWeek(Calendar dtCompare, DiasSemana diasSemana) {
//			
//			if (dtCompare != null && diasSemana != null) {
//				if (dtCompare.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY && diasSemana.getDomingo()) {
//					return true;
//				}
	//
//				if (dtCompare.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY && diasSemana.getSegunda()) {
//					return true;
//				}
	//
//				if (dtCompare.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY && diasSemana.getTerca()) {
//					return true;
//				}
	//
//				if (dtCompare.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY && diasSemana.getQuarta()) {
//					return true;
//				}
	//
//				if (dtCompare.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY && diasSemana.getQuinta()) {
//					return true;
//				}
	//
//				if (dtCompare.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY && diasSemana.getSexta()) {
//					return true;
//				}
	//
//				if (dtCompare.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY && diasSemana.getSabado()) {
//					return true;
//				}
//			}
//			
//			return false;
//		}

	public static String getStringDia(Date date, boolean showNoDia) {
		return DateTimeUtils.getStringPeriodo(date, date, false, showNoDia);
	}

	/**
	 * 
	 * @param dateInicio
	 * @param dateFim
	 * @param showNoPeriodo
	 * @param showNoDia
	 * @return periodo em formato string, utilizando o locale default
	 */
	public static String getStringPeriodo(Date dateInicio, Date dateFim, boolean showNoPeriodo, boolean showNoDia) {
		Calendar calendarInicio = Calendar.getInstance();
		Calendar calendarFim = Calendar.getInstance();

		calendarInicio.setTime(dateInicio);
		calendarFim.setTime(dateFim);

		if (calendarInicio.get(Calendar.YEAR) != calendarFim.get(Calendar.YEAR)) {
			if (showNoPeriodo) {
				return DateTimeUtils.NO_PERIODO + DateTimeUtils.getDataPorExtenso(calendarInicio) + DateTimeUtils.A
						+ DateTimeUtils.getDataPorExtenso(calendarFim);
			} else {
				return DateTimeUtils.getDataPorExtenso(calendarInicio) + DateTimeUtils.A
						+ DateTimeUtils.getDataPorExtenso(calendarFim);
			}
		} else if (calendarInicio.get(Calendar.MONTH) != calendarFim.get(Calendar.MONTH)) {
			if (showNoPeriodo) {
				return DateTimeUtils.NO_PERIODO + DateTimeUtils.getDay(calendarInicio) + DateTimeUtils.DE
						+ DateTimeUtils.getMesPorExtenso(calendarInicio) + DateTimeUtils.A
						+ DateTimeUtils.getDataPorExtenso(calendarFim);
			} else {
				return DateTimeUtils.getDay(calendarInicio) + DateTimeUtils.DE
						+ DateTimeUtils.getMesPorExtenso(calendarInicio) + DateTimeUtils.A
						+ DateTimeUtils.getDataPorExtenso(calendarFim);
			}
		} else if (calendarInicio.get(Calendar.DAY_OF_MONTH) != calendarFim.get(Calendar.DAY_OF_MONTH)) {
			if (showNoPeriodo) {
				return DateTimeUtils.NO_PERIODO + DateTimeUtils.getDay(calendarInicio) + DateTimeUtils.A
						+ DateTimeUtils.getDataPorExtenso(calendarFim);
			} else {
				return DateTimeUtils.getDay(calendarInicio) + DateTimeUtils.A
						+ DateTimeUtils.getDataPorExtenso(calendarFim);
			}
		} else {
			if (showNoDia) {
				return DateTimeUtils.NO_DIA + DateTimeUtils.getDataPorExtenso(calendarInicio);
			} else {
				return DateTimeUtils.getDataPorExtenso(calendarInicio);
			}
		}
	}

	private static String getDataPorExtenso(Calendar calendar) {
		return DateTimeUtils.getDay(calendar) + DateTimeUtils.DE + DateTimeUtils.getMesPorExtenso(calendar)
				+ DateTimeUtils.DE + calendar.get(Calendar.YEAR);
	}

	private static String getDay(Calendar calendar) {
		return calendar.get(Calendar.DAY_OF_MONTH) < 10 ? ("0" + calendar.get(Calendar.DAY_OF_MONTH))
				: String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
	}

	/**
	 * Retorna uma String a partir de uma lista de datas. Formato de retorno: 12 e
	 * 14 de outubro de 2009 17, 22 e 26 de novembro de 2009 1 de dezembro de 2009
	 * Formato de retorno: Dias 12 e 14 de outubro de 2009 Dias 17, 22 e 26 de
	 * novembro de 2009 Dia 1 de dezembro de 2009
	 * 
	 * @param datas
	 * @param htmlLineBreak Se true, as quebras de linha s�o com <br>
	 * @param prefixoDias   Se true, mostra a palavra "Dia" ou "Dias"
	 * @return
	 */
	public static String getStringDatas(List<Date> datas, boolean htmlLineBreak, boolean prefixoDias, boolean mostraMes,
			boolean mostraAno) {
		Collections.sort(datas);

		/* Monta um Map com List contendo datas de um mesmo ano/m�s */
		Map<String, List<Date>> mapAnoMes = new LinkedHashMap<String, List<Date>>();
		for (Date data : datas) {
			data = DateTimeUtils.getFirstHourOfDate(data);

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(data);
			String anoMes = Integer.toString(calendar.get(Calendar.YEAR)) + "#"
					+ Integer.toString(calendar.get(Calendar.MONTH));
			if (!mapAnoMes.containsKey(anoMes)) {
				mapAnoMes.put(anoMes, new ArrayList<Date>());
			}
			List<Date> listDatas = mapAnoMes.get(anoMes);
			if (!listDatas.contains(data)) {
				listDatas.add(data);
			}
		}

		/* Monta a String resultante */
		StringBuilder str = new StringBuilder();
		Calendar calendar = Calendar.getInstance();

		for (Map.Entry<String, List<Date>> entry : mapAnoMes.entrySet()) {
			boolean isFirst = true;
			List<Date> d = mapAnoMes.get(entry.getKey());
			for (Iterator<Date> iterator = d.iterator(); iterator.hasNext();) {
				calendar.setTime(iterator.next());
				if (!isFirst) {
					str.append(iterator.hasNext() ? ", " : " e ");
				} else if (prefixoDias) {
					str.append(iterator.hasNext() ? "Dias " : "Dia ");
				}
				str.append(calendar.get(Calendar.DAY_OF_MONTH));
				isFirst = false;
			}
			if (mostraMes) {
				str.append(DateTimeUtils.DE);
				str.append(DateTimeUtils.getMesPorExtenso(calendar));
			}
			if (mostraAno) {
				str.append(DateTimeUtils.DE);
				str.append(calendar.get(Calendar.YEAR));
			}
			if (htmlLineBreak) {
				str.append("<br/>");
			} else {
				str.append("\n");
			}
		}

		return str.toString();
	}

	/**
	 * Retorna o mes por extenso no Locale default
	 * 
	 * @param calendar
	 * @return nome do mes por extenso
	 */
	public static String getMesPorExtenso(Calendar calendar) {
		return new SimpleDateFormat("MMMM", new Locale("pt", "BR")).format(calendar.getTime()).toLowerCase();
	}

	public static String getMesPorExtenso(Date date) {
		return new SimpleDateFormat("MMMM", new Locale("pt", "BR")).format(date).toLowerCase();
	}

	public static String getMesExtensoAno(Date date) {
		if (date == null)
			return "";
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return DateTimeUtils.getMesPorExtenso(date).substring(0, 3) + "/" + c.get(Calendar.YEAR);
	}

	public static String mesAnoToMesExtensoAno(String mesAno) {
		return DateTimeUtils.getMesExtensoAno(DateTimeUtils.firstDayByMesAno(mesAno));
	}

	public static Date firstDayByMesAno(String mesAno) {
		Calendar c = Calendar.getInstance();
		String[] s = mesAno.split("/");
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.MONTH, new Integer(s[0]) - 1);
		c.set(Calendar.YEAR, new Integer(s[1]));
		return c.getTime();
	}

	public int getMesByName(String mes) {
		return 0;
	}

	public static Date lastDayByMesAno(String mesAno) {
		Calendar c = Calendar.getInstance();
		String[] s = mesAno.split("/");
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.MONTH, new Integer(s[0]) - 1);
		c.set(Calendar.YEAR, new Integer(s[1]));
		c.set(Calendar.DAY_OF_MONTH, DateTimeUtils.monthSize(c));

		return c.getTime();
	}

	/**
	 * Calcula hora somando a hora mais a quantida de horas
	 * 
	 * @param hour
	 * @param qtdHoras
	 * @return
	 */
	public static Date somaHora(Date hour, Double qtdHoras) {
		if (qtdHoras != null) {
			long nrHoras = (long) (3600000 * qtdHoras.doubleValue());
			hour.setTime(hour.getTime() + nrHoras);
		}
		return hour;
	}

	public static Date somaHoraIntervalo(Date hour, Double qtdHoras) {

		if (qtdHoras != null) {
			Date dt = DateTimeUtils.somaHora((Date) hour.clone(), qtdHoras);
			dt.setTime(dt.getTime() - 1);
			return dt;
		}

		return null;
	}

	public static int getYear() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.YEAR);
	}


//		public static boolean hasSelectedDaysOfWeekInDays(Date dtInicio, Date dtFim, DiasSemana diasSemana) {
//			if (dtInicio == null || dtFim == null || diasSemana == null) {
//				return false;
//			}
//			
//			Calendar dataInicio = Calendar.getInstance();
//			dataInicio.setTime(dtInicio);
//			
//			Calendar dataFim = Calendar.getInstance();
//			dataFim.setTime(dtFim);
//			
//			return hasSelectedDaysOfWeekInDays(dataInicio, dataFim, diasSemana);
//		}


//		public static boolean hasSelectedDaysOfWeekInDays(Calendar dtInicio, Calendar dtFim, DiasSemana diasSemana) {
//			
//			boolean isInTheDaysOfWeek = getDaysBetween(dtInicio, dtFim) >= 7;
	//
//			if (isInTheDaysOfWeek) {
//				return true;
//			}
//			
//			while (DateTimeUtils.isInInterval(dtInicio, dtFim, dtInicio)) {
//				
//				isInTheDaysOfWeek = isInTheDaysOfWeek(dtInicio, diasSemana);
//				
//				if (isInTheDaysOfWeek) {
//					return isInTheDaysOfWeek;
//				}
//				
//				dtInicio.add(Calendar.DATE, 1);
//			}
//			
//			return isInTheDaysOfWeek;
//		}
	//

//		public static boolean hasSelectedDaysOfWeekOutOfDays(Date dtInicio, Date dtFim, DiasSemana diasSemana) {
	//
//			if (dtInicio == null || dtFim == null || diasSemana == null) {
//				return false;
//			}
//			
//			Calendar dataInicio = Calendar.getInstance();
//			dataInicio.setTime(dtInicio);
//			
//			Calendar dataFim = Calendar.getInstance();
//			dataFim.setTime(dtFim);
//			
//			return hasSelectedDaysOfWeekOutOfDays(dataInicio, dataFim, diasSemana);
//		}

//		public static boolean hasSelectedDaysOfWeekOutOfDays(Calendar dtInicio, Calendar dtFim, DiasSemana diasSemana) {
	//
//			boolean isInTheDaysOfWeek = getDaysBetween(dtInicio, dtFim) >= 7;
	//
//			if (isInTheDaysOfWeek) {
//				return false;
//			} else {
//				
//				dtInicio.add(Calendar.DATE, 6);
//				
//				dtInicio.add(Calendar.HOUR_OF_DAY, -1);
//				while (dtFim.before(dtInicio)) {
//				
//					dtInicio.add(Calendar.HOUR_OF_DAY, 1);
//					isInTheDaysOfWeek = isInTheDaysOfWeek(dtInicio, diasSemana);
//					dtInicio.add(Calendar.HOUR_OF_DAY, -1);
//					
//					if (isInTheDaysOfWeek) {
//						return isInTheDaysOfWeek;
//					}
//					
//					dtInicio.add(Calendar.DATE, -1);
//				}
//			}
	//
//			return isInTheDaysOfWeek;
//		}

	public static Date addDay(Date date, int qtdDias) {

		Calendar dataInicio = Calendar.getInstance();
		dataInicio.setTime(date);

		dataInicio.add(Calendar.DATE, qtdDias);

		return dataInicio.getTime();

	}

	public static Date addMonth(Date date, int qtdMeses) {

		Calendar dataInicio = Calendar.getInstance();
		dataInicio.setTime(date);

		dataInicio.add(Calendar.MONTH, qtdMeses);
		return dataInicio.getTime();
	}

	public static String getDataAtualAsStringFormatoDefault() {
		return new SimpleDateFormat(DateTimeUtils.FORMAT_109).format(new Date());
	}

	public static String toStringDefault(Date date) {
		return DateTimeUtils.toStringDefault(date, DateTimeUtils.FORMAT_109);
	}

	public static String toStringDefault(Date date, String pattern) {
		return new SimpleDateFormat(pattern).format(date);
	}

	public static Date getFirstDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public static Date getLastDayOfMonth(Date date) {
		date = DateTimeUtils.getFirstDayOfMonth(date);
		date = DateTimeUtils.addMonth(date, 1);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 0);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public static Date toDateDefault(String date) {
		try {
			return new SimpleDateFormat(DateTimeUtils.FORMAT_109).parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date stringToDate(String data) {
		return stringToDate(data,DateTimeUtils.whatDateFormat(data));
	}
	
	public static Date stringToDate(String date,String format) {
		try {
			return new SimpleDateFormat(format).parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<Date> removeRange(List<Date> list, Date date1, Date date2) {
		date1 = DateTimeUtils.toDateDefault(DateTimeUtils.toStringDefault(date1));
		date2 = DateTimeUtils.toDateDefault(DateTimeUtils.toStringDefault(date2));
		List<Date> listAux = new ArrayList<Date>();
		while (date1.before(DateTimeUtils.addDay(date2, 1))) {
			listAux.add(date1);
			date1 = DateTimeUtils.addDay(date1, 1);
		}
		list.removeAll(listAux);
		return list;
	}

	@SuppressWarnings("unused")
//		public static void main(String[] args) {
//			
//			Date date1 = DateTimeUtils.toDateDefault("01/01/2017");
//			Date date2 = DateTimeUtils.toDateDefault("31/05/2017");
////			Date date1 = DateTimeUtils.toDateDefault("19/12/2016");
////			Date date2 = DateTimeUtils.toDateDefault("18/05/2017");
////			DateRange dr = new DateRange(date1,date2);
	//
////			System.out.println("Percentual: " + dr.getPercentual());
////			System.out.println("Qt de dias: " + dr.getQtDays());
//			System.out.println("MonthBet: " + getMonthBetween(date1, date2));
//			System.out.println(" qtMeses: " + getQtMeses(date1, date2));
//			System.out.println("  qrdays: " + getDaysBetween(date1, date2));
//			System.out.println();
//		}

	public static Integer getMonthOfDate(Date date) {
		return Integer.valueOf(DateTimeUtils.toStringDefault(date, "MM"));
	}

	public static Integer getYearMonthOfDate(Date date) {
		return Integer.valueOf(DateTimeUtils.toStringDefault(date, "yyyyMM"));
	}

	public static BigDecimal getQtMeses(Date dtInicio, Date dtFinal) {

		// caso o mes de inicio e fim seja o mesmo e tenha 30 dias ou mais, retorna 1
		// direto
//			if(getMonthOfDate(dtInicio).equals(getMonthOfDate(dtFinal)) && getYearOfDate(dtInicio).equals(getYearOfDate(dtFinal))) {
//				if(getDaysBetween(dtInicio, dtFinal) >= 30) {
//					return new BigDecimal(1);
//				}
//			}

		Date fisrtDayOfStart = DateTimeUtils.getFirstDayOfMonth(dtInicio);
		Date fisrtDayOfEnd = DateTimeUtils.getFirstDayOfMonth(dtFinal);

		Date lastDayOfStart = DateTimeUtils.addDay(DateTimeUtils.getLastDayOfMonth(dtInicio), 1);
		DateTimeUtils.addDay(DateTimeUtils.getLastDayOfMonth(dtFinal), 1);

		Date fisrtDayOfStartPlusOne = DateTimeUtils.addMonth(fisrtDayOfStart, 1);

		// alterado de fisrtDayOfEnd para lastDayOfEnd na corre��o de um bug que 01/01
		// at� 30/04 ficava 3 meses
		BigDecimal qtMesCheio = BigDecimal
				.valueOf(DateTimeUtils.getMonthBetween(fisrtDayOfStartPlusOne, fisrtDayOfEnd).intValue());
		qtMesCheio = qtMesCheio.signum() == 1 ? qtMesCheio : BigDecimal.ZERO;

		if (((2 == DateTimeUtils.getMonthOfDate(fisrtDayOfStartPlusOne))
				|| (2 == DateTimeUtils.getMonthOfDate(fisrtDayOfEnd))) && (qtMesCheio.intValue() == 0)) {
			qtMesCheio = DateTimeUtils.getDaysBetween(fisrtDayOfStartPlusOne, fisrtDayOfEnd) >= 28 ? BigDecimal.ONE
					: BigDecimal.ZERO;
			;
		} else if (qtMesCheio.intValue() == 0) {
			qtMesCheio = DateTimeUtils.getDaysBetween(fisrtDayOfStartPlusOne, fisrtDayOfEnd) >= 30 ? BigDecimal.ONE
					: BigDecimal.ZERO;
			;
		}

//			if (getMonthBetween(dtInicio, addDay(dtFinal, 1)) <= 1 ) {
//				return BigDecimal.valueOf(getMonthBetween(dtInicio, addDay(dtFinal, 1)));
//			}

		long qtDiasPrimeiroMes = 0;
		long qtDiasUltimoMes = 0;

		qtDiasPrimeiroMes = DateTimeUtils.getDaysBetween(dtInicio,
				lastDayOfStart.before(dtFinal) ? lastDayOfStart : DateTimeUtils.addDay(dtFinal, 1));
//			qtDiasPrimeiroMes = ((2 == getMonthOfDate(dtInicio)) && qtDiasPrimeiroMes>= 28) || qtDiasPrimeiroMes>= 30  ? 30l : qtDiasPrimeiroMes;

		qtDiasUltimoMes = DateTimeUtils.getDaysBetween(fisrtDayOfEnd, dtFinal) + 1;
//			qtDiasUltimoMes = ((2 == getMonthOfDate(dtFinal)) && qtDiasUltimoMes >= 28) || qtDiasUltimoMes >= 30 ? 30 : qtDiasUltimoMes;

		if (DateTimeUtils.getYearMonthOfDate(dtInicio).equals(DateTimeUtils.getYearMonthOfDate(dtFinal))) {
			qtDiasUltimoMes = 0;
		}

//			BigDecimal mes = BigDecimal.valueOf(30);

		long qtDayOfFirstMonth = DateTimeUtils.getDaysBetween(DateTimeUtils.getFirstDayOfMonth(dtInicio),
				DateTimeUtils.getLastDayOfMonth(dtInicio)) + 1;
		long qtDayOfLastMonth = DateTimeUtils.getDaysBetween(DateTimeUtils.getFirstDayOfMonth(dtFinal),
				DateTimeUtils.getLastDayOfMonth(dtFinal)) + 1;

		if (qtDayOfFirstMonth == qtDiasPrimeiroMes) {
			qtDiasPrimeiroMes = 30;
		} else if (qtDayOfLastMonth == qtDiasUltimoMes) {
			qtDiasUltimoMes = 30;
		}
		Double temp = (Double.valueOf(qtDiasPrimeiroMes) / 30)
				+ (qtDiasUltimoMes == 0 ? 0 : Double.valueOf(qtDiasUltimoMes) / 30);

//			BigDecimal qtTotalMes = add(qtMesCheio, , (.intValue() >= 30 ? BigDecimal.ONE : divide(, 4))));
//			boolean unicoMes = false;
//			if (DateTimeUtils.getYearMonthOfDate(dtInicio).equals(DateTimeUtils.getYearMonthOfDate(dtFinal)) ) {
//				unicoMes = true;
//			}
		//
//			//caso o calculo se perca e seja dentro do mesmo mes, retorna
//			if(qtMesCheio.add(BigDecimal.valueOf(temp)).compareTo(BigDecimal.ONE) > 0 && unicoMes) {
//				return BigDecimal.valueOf(getMonthBetween(dtInicio, dtFinal));
//			}

		return qtMesCheio.add(BigDecimal.valueOf(temp));
	}

	/**
	 * 
	 * @param dtInicio
	 * @param dtFinal
	 * @return valor proporcional entre as datas informadas
	 * 
	 * 
	 */
	public static BigDecimal getQtMeses2(Date dtInicio, Date dtFinal) {

		boolean ehUmMes = false;
		boolean ehMesCheio = false;

		BigDecimal valorProporcional = new BigDecimal("0.0");
		BigDecimal qtMeses = new BigDecimal("0.0");

		BigDecimal diasDoMes = new BigDecimal("0.0");

		if (getMonthOfDate(dtInicio).equals(getMonthOfDate(dtFinal))) {
			ehUmMes = true;
		}

		// caso seja dentro de um mesmo m�s, verifica se � o mes todo
		if (getFirstDayOfMonth(dtInicio).equals(dtInicio) && getLastDayOfMonth(dtFinal).equals(dtFinal)) {
			ehMesCheio = true;
			qtMeses = new BigDecimal("1.0");
			return qtMeses;
		}

		// caso seja dentro do mesmo mes mas n�o seja o mes todo e comece no dia
		// primeiro, calcula a proporcao de acordo com o per�odo informado
		if (ehUmMes && dtInicio.equals(getFirstDayOfMonth(dtInicio))) { // caso comece no inicio do mes
			MathContext mc = new MathContext(10, RoundingMode.HALF_UP);

			diasDoMes = new BigDecimal(getDaysBetween(dtInicio, getLastDayOfMonth(dtInicio)));
			diasDoMes = diasDoMes.add(BigDecimal.ONE);

//				diasDoMes = BigDecimal.valueOf(30);

			BigDecimal diasEntre = new BigDecimal(getDaysBetween(dtInicio, dtFinal));
			diasEntre = diasEntre.add(BigDecimal.ONE);

			qtMeses = diasEntre.divide(diasDoMes, mc);

			return qtMeses;

		} else if (ehUmMes && dtFinal.equals(getLastDayOfMonth(dtFinal))) { // caso termine no final do mes

			MathContext mc = new MathContext(10, RoundingMode.HALF_UP);

			diasDoMes = new BigDecimal(getDaysBetween(getFirstDayOfMonth(dtInicio), dtFinal)).add(BigDecimal.ONE);
			;
//				diasDoMes = BigDecimal.valueOf(30);
			BigDecimal diasEntre = new BigDecimal(getDaysBetween(dtInicio, dtFinal)).add(BigDecimal.ONE);

			qtMeses = diasEntre.divide(diasDoMes, mc);

			return qtMeses;

		} else { // caso nao comece no inicio nem termine no final
			MathContext mc = new MathContext(10, RoundingMode.HALF_UP);

			diasDoMes = new BigDecimal(getDaysBetween(getFirstDayOfMonth(dtInicio), getLastDayOfMonth(dtFinal)))
					.add(BigDecimal.ONE);
			BigDecimal diasEntre = new BigDecimal(getDaysBetween(dtInicio, dtFinal)).add(BigDecimal.ONE);

			qtMeses = diasEntre.divide(diasDoMes, mc);

			return qtMeses;

		}

	}

	public static String getCompentencia(Date init, Date dtCompentencia) {

		Calendar i = Calendar.getInstance();
		i.setTime(init);
		Calendar c = Calendar.getInstance();
		c.setTime(dtCompentencia);

		if (i.get(Calendar.DAY_OF_MONTH) > c.get(Calendar.DAY_OF_MONTH)) {
			c.add(Calendar.MONTH, -1);
		}
		return DateTimeUtils.toStringDefault(c.getTime(), "MM/yyyy");
	}

	public static Integer getYearOfDate(Date date) {
		return Integer.valueOf(DateTimeUtils.toStringDefault(date, "yyyy"));
	}

	public static String getMesAnoPorExtenso(String data) {
		return data.toUpperCase().replace("JAN", "janeiro").replace("FEV", "fevereiro").replace("MAR", "março")
				.replace("ABR", "abril").replace("MAI", "maio").replace("JUN", "junho").replace("JUL", "julho")
				.replace("AGO", "agosto").replace("SET", "setembro").replace("OUT", "outubro")
				.replace("NOV", "novembro").replace("DEZ", "dezembro");
	}

	public static String getDataPorExetenso(Date data) {
		DateFormat df = DateFormat.getDateInstance(DateFormat.FULL, new Locale("pt", "BR"));
		return df.format(data);

	}

	public static String whatDateFormat(String p_data) {
		String format = DateTimeUtils.FORMAT_109;
		String formats[] = DateTimeUtils.listFormatData();
		for(int i=0;i<formats.length;i++) {
			format = formats[i];
			if(DateTimeUtils.isValid(p_data,format)){
				return format;
			}
		}
		return format;
	}
	
	public static String[] listFormatData() {
		return new String[] { 
		          
				FORMAT_300,
				FORMAT_303,
				FORMAT_306,
				FORMAT_309,
				          
				FORMAT_200,
				FORMAT_203,
				FORMAT_206,
				FORMAT_209,
				          
				FORMAT_006,
				          
				          
				FORMAT_100,
				FORMAT_103,
				FORMAT_106,
				FORMAT_109		
				
				};		
	}
	
	public static boolean isValid(String dateStr,String dataFormat) {
        DateFormat sdf = new SimpleDateFormat(dataFormat);
        sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}
