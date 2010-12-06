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

import net.sf.jranges.range.integer.IntegerRange;
import net.sf.jranges.range.integer.IntegerRangeFactory;

/**
 * 
 * A factory that creates objects of type {@link DummyRange}.
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
 * @version 2010-11-19
 * 
 */
public class DummyRangeFactory implements IntegerRangeFactory{

	public DummyRange create() {
		return new DummyRange();
	}

	public DummyRange create(int start, int stop) {
		return new DummyRange(start, stop);
	}

	public DummyRange create(IntegerRange template) {
		return new DummyRange(template.getStart(), template.getStop());
	}

}