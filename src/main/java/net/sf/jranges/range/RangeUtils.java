package net.sf.jranges.range;

import net.sf.jranges.range.doublerange.DoubleRange;
import net.sf.jranges.range.integer.IntegerRange;

public class RangeUtils {
	
	public interface IntegerRangeCallback {
		void call(int i);
	}
	
	public interface DoubleRangeCallback {
		void call(double d);
	}
	
	private RangeUtils(){}
	
	public static void doForAllInRange(IntegerRange range, IntegerRangeCallback task){
		for(int i = range.getStart(); i <= range.getStop(); i+= range.getInterval()){
			task.call(i);
		}
	}
	
	public static void doForAllInRange(DoubleRange range, DoubleRangeCallback task){
		for(double i = range.getStart(); i <= range.getStop(); i+= range.getInterval()){
			task.call(i);
		}
	}

}