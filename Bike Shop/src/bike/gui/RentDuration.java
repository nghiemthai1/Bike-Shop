package bike.gui;

import java.io.Serializable;
import java.time.LocalDateTime;

@SuppressWarnings("serial")
public class RentDuration implements Serializable {

	private long days;
	private long hours;
	private LocalDateTime returnTime;
	
	public RentDuration(long days, long hours) {
		this.days = days;
		this.hours = hours;
		returnTime = LocalDateTime.now().plusHours(hours).plusDays(days);
	}
	
	public long getDays() {
		return days;
	}
	
	public long getHours() {
		return hours;
	}
	
	public LocalDateTime getReturnTime() {
		return returnTime;
	}
	
	public int getDurationInHours() {
		return Math.toIntExact(((days * 24) + hours));
	}
	
	public String displayDuration() {
		return days + " days | " + hours + " hours";
	}
	
	public boolean isZero() {
		return (days == 0) && (hours == 0);
	}
}
