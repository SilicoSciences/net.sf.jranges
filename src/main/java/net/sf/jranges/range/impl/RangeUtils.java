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

package net.sf.jranges.range.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jranges.range.IntegerRange;
import net.sf.jranges.range.IntegerRangeFactory;
import net.sf.kerner.utils.Utils;

/**
 * 
 * Utility class for all kind of
 * {@link net.sf.kerner.commons.range.IntegerRange Range} related operations.
 * 
 * @author <a href="mailto:alex.kerner.24@googlemail.com">Alexander Kerner</a>
 * @version 2010-11-05
 * 
 */
public class RangeUtils {

	private RangeUtils() {
		// singleton
	}

	/**
	 * 
	 * Check whether a position is covered by at least one of the given
	 * {@code Range}s.
	 * <p>
	 * <b>Example:</b>
	 * 
	 * <pre>
	 * List&lt;Range&gt; ranges = new ArrayList&lt;Range&gt;();
	 * ranges.add(new DummyRange(1, 2));
	 * ranges.add(new DummyRange(3, 4));
	 * ranges.add(new DummyRange(5, 6));
	 * ranges.add(new DummyRange(7, 8));
	 * 
	 * assertTrue(RangeUtils.includedByOne(ranges, 8));
	 * assertFalse(RangeUtils.includedByOne(ranges, 0));
	 * </pre>
	 * 
	 * </p>
	 * 
	 * 
	 * @param ranges
	 *            {@code List} of {@code Range}s that are checked for the
	 *            coverage
	 * @param position
	 *            position that is checked
	 * @return true, if given position is contained by at least one of the given
	 *         ranges; false otherwise
	 */
	public static boolean includedByOne(
			List<? extends IntegerRange> ranges, int position) {
		for (IntegerRange r : ranges) {
			if (r.includes(position)) {
				return true;
			}
		}
		return false;
	}

	public static int numberOfAppearances(
			List<? extends IntegerRange> ranges, int position) {
		int result = 0;
		for (IntegerRange r : ranges) {
			if (r.includes(position)) {
				result++;
			}
		}
		return result;
	}

	public static long numberOfPositions(
			List<? extends IntegerRange> ranges) {
		long result = 0;
		for (IntegerRange r : ranges) {
			result += r.getLength();
		}
		return result;
	}

	/**
	 * Merge one or more {@code Range}s.
	 * <p>
	 * <b>Example:</b>
	 * 
	 * <pre>
	 * List&lt;Range&gt; ranges = new ArrayList&lt;Range&gt;();
	 * ranges.add(new DummyRange(1, 2));
	 * ranges.add(new DummyRange(3, 4));
	 * ranges.add(new DummyRange(5, 6));
	 * ranges.add(new DummyRange(7, 8));
	 * 
	 * assertEquals(new DummyRange(1, 8),
	 * 		RangeUtils.merge(ranges, new DummyRangeFactory()));
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param ranges
	 *            {@code Range}s that are to be merged
	 * @param factory
	 *            {@link net.sf.kerner.commons.range.IntegerRangeFactory RangeFactory}
	 *            that is used for retrieving a new {@code Range}-instance
	 * @return a new {@code Range}, that represents the merge of the given
	 *         {@code Range}s
	 * 
	 * @see IntegerRangeFactory
	 */
	public static IntegerRange merge(List<? extends IntegerRange> ranges,
			IntegerRangeFactory factory) {
		if (ranges.isEmpty())
			return factory.create();
		IntegerRange result = factory.create(ranges.iterator().next());
		int start = result.getStart();
		int stop = result.getStop();
		for (IntegerRange r : ranges) {
			if (r.getStart() < start)
				start = r.getStart();
			if (r.getStop() > stop)
				stop = r.getStop();
		}
		return factory.create(start, stop);
	}

	public static Map<Integer, Integer> positionFrequencies(
			final List<? extends IntegerRange> ranges,
			final List<? extends IntegerRange> ranges2) {
		Utils.checkForNull(ranges, ranges2);

		final Map<Integer, Integer> result = new HashMap<Integer, Integer>();

		for (IntegerRange range : ranges2) {
			for (int i = range.getStart(); i <= range.getStop(); i++) {
				final int n = numberOfAppearances(ranges, i);
				final Integer key = Integer.valueOf(i);
				Integer value = result.get(key);
				if (value == null)
					result.put(key, Integer.valueOf(n));
				else
					result.put(key, Utils.add(value, n));
			}
		}

		return result;
	}

	public static double medianPositionFrequencies(
			final List<? extends IntegerRange> ranges,
			final List<? extends IntegerRange> ranges2) {

		final Map<Integer, Integer> map = positionFrequencies(ranges, ranges2);

		if (map.size() == 0)
			return 0;

		int result = 0;

		for (Integer value : map.values()) {
			result += value.intValue();
		}

		return (double) result / (double) map.size();
	}

}
