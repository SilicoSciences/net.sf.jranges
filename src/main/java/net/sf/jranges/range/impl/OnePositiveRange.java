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

import net.sf.jranges.range.AbstractIntegerRange;
import net.sf.jranges.range.RangeException;


/**
 * 
 * 
 * {@code OnePositiveRange} is an implementation for
 * {@link net.sf.kerner.commons.range.IntegerRange IntegerRange}, for which the
 * following is true:<br> {@code 1 <= start <= stop <= Integer.MAX_VALUE}
 * 
 * <p>
 * <b>Example:</b>
 * 
 * <pre>
 * TODO example
 * </pre>
 * 
 * </p>
 * 
 * @author <a href="mailto:alex.kerner.24@googlemail.com">Alexander Kerner</a>
 * @version 2010-10-19
 * 
 */
public class OnePositiveRange extends AbstractIntegerRange {

	public OnePositiveRange(int start, int stop) throws RangeException {
		super(start, stop, 1, Integer.MAX_VALUE);
	}
	
	public OnePositiveRange(int start, int stop, int interval) throws RangeException {
		super(start, stop, 1, Integer.MAX_VALUE, interval);
	}

	@Override
	protected
	OnePositiveRange newInstange(int start, int stop, int limit1, int limit2)
			throws RangeException {
		return new OnePositiveRange(start, stop, interval);
	}

}
