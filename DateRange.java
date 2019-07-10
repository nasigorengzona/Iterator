import java.time.LocalDate;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DateRange implements Iterable<LocalDate> {
	private LocalDate from;
	private LocalDate to;


	public DateRange(LocalDate from, LocalDate to) {
		this.from = from;
		this.to = to;
	}
	
	public boolean contains(LocalDate date) {
		if ((date.equals(from) || date.isAfter(from)) && (date.isBefore(to)|| date.equals(to)))return true;
		return false;
	}
	
    public boolean overlap(DateRange daterange){
        DateRange dr = new DateRange(from,to);
        for(LocalDate e : dr) {
        	if(!daterange.contains(e))return false;
        }
        return true;
    }
    
	public Iterator iterator() {
		return new DateRangeIterator(from, to);
	}
}

class DateRangeIterator implements Iterator<LocalDate> {
	private LocalDate from,to,point;

	public DateRangeIterator(LocalDate from, LocalDate to) {
		this.from = from;
		this.to = to;
		this.point = this.from;
	}
	
	public boolean hasNext() {
		return this.point != null;
	}
	
	public LocalDate next() {
		if(!hasNext())throw new NoSuchElementException();
		else {
            LocalDate ret = point;
            point = point.plusDays(1);
            if (point.compareTo(to) > 0)
            {
            	point = null;
            }
            return ret;
		}
	}
}
