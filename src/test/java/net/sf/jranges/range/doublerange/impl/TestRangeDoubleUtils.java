/*******************************************************************************
 * Copyright (c) 2010-2014 Alexander Kerner. All rights reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package net.sf.jranges.range.doublerange.impl;

import static org.junit.Assert.assertEquals;

import java.util.List;

import net.sf.jranges.range.doublerange.DoubleRange;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class TestRangeDoubleUtils {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testSplit01() {
		DoubleRange range = new DummyDoubleRange(0, 10);
		List<? extends DoubleRange> result = RangeDoubleUtils.split(range, 2, new FactoryRangeDoubleZeroPositive());
		assertEquals(10, result.size());
	}

	@Ignore
	@Test
	public final void testSplit02() {
		DoubleRange range = new ZeroPositiveDoubleRange(0, 10, 0.2);
		List<? extends DoubleRange> result = RangeDoubleUtils.split(range, 2, new FactoryRangeDoubleZeroPositive());
		System.out.println(result);
	}

}
