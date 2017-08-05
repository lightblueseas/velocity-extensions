/**
 * Copyright (C) 2015 Asterios Raptis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.alpharogroup.velocity;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

public class VelocityExtensionsTest
{

	@Test
	public void testMergeVelocityContextString()
	{
		/* first, we init the runtime engine. Defaults are fine. */
		Velocity.init();

		/* lets make a Context and put data into it */
		final VelocityContext context = new VelocityContext();

		context.put("name", "Velocity");
		context.put("project", "Jakarta");

		/* lets make our own string to render */
		final String s = "We are using $project $name to render this.";
		final String actual = VelocityExtensions.merge(context, s);
		final String expected = "We are using Jakarta Velocity to render this.";
		/* check if equal */
		AssertJUnit.assertEquals(expected, actual);
	}

}