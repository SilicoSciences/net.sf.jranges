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

package net.sf.jranges.range.integer.impl;

import net.sf.jranges.range.RangeException;
import net.sf.jranges.range.integer.IntegerRange;
import net.sf.jranges.range.integer.VeryAbstractIntegerRange;

/**
 * 
 * Most simple implementation for
 * {@link net.sf.jranges.range.integer.IntegerRange IntegerRange}. Arguments and
 * operations are not checked for validity.
 * 
 * <p>
 * A {@code DummyRange} is mutable. Start and Stop may be set independently.
 * </p>
 * 
 * <p>
 * <b>Example:</b><br>
 * 
 * </p>
 * <p>
 * 
 * <pre>
 * TODO example
 * </pre>
 * 
 * </p>
 * 
 * @author <a href="mailto:alex.kerner.24@googlemail.com">Alexander Kerner</a>
 * @version 2010-11-18
 * 
 */
public class DummyIntegerRange extends VeryAbstractIntegerRange implements IntegerRange {
	
	// Constructor //

	/**
	 * 
	 * Construct a new {@code DummyRange} with start and stop values initiated with {@code 0}.
	 *
	 */
	public DummyIntegerRange() {
		this.start = 0;
		this.stop = 0;
	}

	/**
	 * 
	 * Construct a new {@code DummyRange} with given start and stop values.
	 *
	 */
	public DummyIntegerRange(int start, int stop) {
		this.start = start;
		this.stop = stop;
	}
	
	// Public //

	/**
	 * 
	 * Set start position for this {@code DummyRange}.
	 *
	 * @param start new start position
	 */
	public void setStart(int start) {
		this.start = start;
	}

	/**
	 * 
	 * Set stop position for this {@code DummyRange}.
	 *
	 * @param stop new stop position
	 */
	public void setStop(int stop) {
		this.stop = stop;
	}
	
	// Implement //

	/**
	 * 
	 */
	public IntegerRange shift(int offset) {
		return new DummyIntegerRange(getStart() + offset, getStop() + offset);
	}

	/**
	 * 
	 */
	public IntegerRange expandRange(int offset) throws RangeException {
		return expandRange(offset, false);
	}

	/**
	 * 
	 */
	public IntegerRange expandRange(int offset, boolean stayWithinLimits)
			throws RangeException {
		int start = getStart() - offset;
		int stop = getStop() + offset;
		return new DummyIntegerRange(start, stop);
	}

}
