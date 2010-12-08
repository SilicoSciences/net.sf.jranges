/**********************************************************************
Copyright (c) 2009-2010 Alexander Kerner. All rights reserved.
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 ***********************************************************************/

package net.sf.jranges.range.integer;

import net.sf.jranges.range.RangeException;
import net.sf.jranges.range.integer.impl.DummyIntegerRange;
import net.sf.kerner.utils.math.ArithmeticSavety;

/**
 * 
 * 
 * A prototype implementation for {@link IntegerRange}.
 * 
 * <p>
 * In contrast to {@link DummyIntegerRange}, an {@code AbstractIntegerRange} is
 * immutable and also provides basic value checking e.g. {@code start <= stop}.
 * </p>
 * An {@code AbstractIntegerRange} also has limits, that means start cannot be
 * smaller than limit1 and stop cannot be greater than limit2.
 * <p>
 * 
 * </p>
 * 
 * @author <a href="mailto:alex.kerner.24@googlemail.com">Alexander Kerner</a>
 * @version 2010-12-08
 * 
 */
public abstract class AbstractIntegerRange extends VeryAbstractIntegerRange implements
		IntegerRange {
	
	protected final int start;
	
	protected final int stop;

	/**
	 * This {@code AbstractIntegerRange}'s lower limit, which is the smallest
	 * possible position that for {@code start} is valid.
	 */
	protected final int limit1;

	/**
	 * This {@code AbstractIntegerRange}'s upper limit, which is the greatest
	 * possible position that for {@code stop} is valid.
	 */
	protected final int limit2;

	/**
	 * This {@code AbstractIntegerRange}'s interval.
	 */
	protected final int interval;

	// Constructor //

	/**
	 * 
	 * 
	 * Construct a new {@code AbstractIntegerRange}.
	 * <p>
	 * For every {@code AbstractIntegerRange} following is true: <br>
	 * {@code limit1 <= start <= stop <= limit2}
	 * </p>
	 * 
	 * @param start
	 *            start point of this {@code AbstractIntegerRange}
	 * @param stop
	 *            stop point of this {@code AbstractIntegerRange}
	 * @param limit1
	 *            minimum value that is valid for this
	 *            {@code AbstractIntegerRange}'s start point
	 * @param limit2
	 *            maximum value that is valid for this
	 *            {@code AbstractIntegerRange}'s stop point
	 * @throws RangeException
	 *             if {@code limit1 > start || limit2 < stop || start > stop}
	 */
	public AbstractIntegerRange(int start, int stop, int limit1, int limit2)
			throws RangeException {
		if (limit1 > start || limit2 < stop || start > stop)
			throw new RangeException("invalid range" + " start=" + start
					+ " stop=" + stop + " limit1=" + limit1 + " limit2="
					+ limit2);
		this.interval = 1;
		this.limit1 = limit1;
		this.limit2 = limit2;
		this.stop = stop;
		this.start = start;
	}

	/**
	 * 
	 * 
	 * Construct a new {@code AbstractIntegerRange}.
	 * <p>
	 * For every {@code AbstractIntegerRange} following is true: <br>
	 * {@code limit1 <= start <= stop <= limit2}
	 * </p>
	 * 
	 * @param start
	 *            start point of this {@code AbstractIntegerRange}
	 * @param stop
	 *            stop point of this {@code AbstractIntegerRange}
	 * @param limit1
	 *            minimum value that is valid for this
	 *            {@code AbstractIntegerRange}'s start point
	 * @param limit2
	 *            maximum value that is valid for this
	 *            {@code AbstractIntegerRange}'s stop point
	 * @param interval
	 *            interval of this {@code AbstractIntegerRange}
	 * @throws RangeException
	 *             if
	 *             {@code limit1 > start || limit2 < stop || start > stop || (((stop - start) % interval) != 0)}
	 */
	public AbstractIntegerRange(int start, int stop, int limit1, int limit2,
			int interval) throws RangeException {
		if (limit1 > start || limit2 < stop || start > stop
				|| (((stop - start) % interval) != 0))
			throw new RangeException("invalid range" + " start=" + start
					+ " stop=" + stop + " limit1=" + limit1 + " limit2="
					+ limit2 + "interval="+interval);
		this.limit1 = limit1;
		this.limit2 = limit2;
		this.interval = interval;
		this.stop = stop;
		this.start = start;
	}

	// Public //

	/**
	 * 
	 * Retrieve this {@code AbstractIntegerRange}'s &quot;lower&quot; limit.
	 * This is the smallest possible value for {@code start}.
	 * 
	 * @return lower limit for this {@code AbstractIntegerRange}
	 */
	public int getLimit1() {
		return limit1;
	}

	/**
	 * 
	 * Retrieve this {@code AbstractIntegerRange}'s &quot;upper&quot; limit.
	 * This is the greatest possible value for {@code stop}.
	 * 
	 * @return upper limit for this {@code AbstractIntegerRange}
	 */
	public int getLimit2() {
		return limit2;
	}

	// Override //

	@Override
	public int getInterval() {
		return interval;
	}
	
	@Override
	public boolean includes(int position) {
		if (interval == 1)
			return super.includes(position);
		return ((position - start) % interval == 0) && position <= stop;
	}
	
	@Override
	public int getLength() {
		if(interval == 1)
			return super.getLength();
		
		// TODO this is falsch!!
		return super.getLength();
	}
	
	// Implement //

	public IntegerRange shift(int offset) throws RangeException {
		return newInstange(ArithmeticSavety.addInt(getStart(), offset),
				ArithmeticSavety.addInt(getStop(), offset), getLimit1(),
				getLimit2());

	}

	public IntegerRange expandRange(int offset) throws RangeException {
		return expandRange(offset, false);
	}

	public IntegerRange expandRange(int offset, boolean stayWithinLimits)
			throws RangeException {
		int start = this.start;
		int stop = this.stop;
		if (stayWithinLimits) {
			try{
			start = ArithmeticSavety.addInt(getStart(), -offset);
			}catch(ArithmeticException e){
				//
			}
			try{
			stop = ArithmeticSavety.addInt(getStop(), offset);
			}catch(ArithmeticException e){
				//
			}
			if (start < limit1) {
				start = limit1;
			}
			if (stop > limit2) {
				stop = limit2;
			}
			return newInstange(start, stop, getLimit1(), getLimit2());
		} else {
			start = ArithmeticSavety.addInt(getStart(), -offset);
			stop = ArithmeticSavety.addInt(getStop(), offset);
			return newInstange(start, stop, getLimit1(), getLimit2());
		}
	}

	// Abstract //

	/**
	 * 
	 * 
	 * Retrieve a new {@code AbstractIntegerRange} instance based on the given
	 * parameter.
	 * 
	 * @param start
	 *            start point for new {@code AbstractIntegerRange}
	 * @param stop
	 *            stop point for new {@code AbstractIntegerRange}
	 * @param limit1
	 *            limit1 point for new {@code AbstractIntegerRange}
	 * @param limit2
	 *            limit2 point for new {@code AbstractIntegerRange}
	 * @return the new {@code AbstractIntegerRange} instance
	 * @throws RangeException
	 */
	protected abstract AbstractIntegerRange newInstange(int start, int stop, int limit1,
			int limit2) throws RangeException;
}
