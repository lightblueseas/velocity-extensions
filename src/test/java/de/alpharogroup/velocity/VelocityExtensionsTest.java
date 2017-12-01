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

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.parser.ParseException;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

/**
 * The unit test class for the class {@link VelocityExtensions}.
 */
public class VelocityExtensionsTest
{

	/**
	 * Test method for {@link VelocityExtensions#merge(VelocityContext, String)}.
	 */
	@Test
	public void testMergeVelocityContextString()
	{
		String expected;
		String actual;
		/* first, we init the runtime engine. Defaults are fine. */
		Velocity.init();

		/* lets make a Context and put data into it */
		final VelocityContext context = new VelocityContext();

		context.put("name", "Velocity");
		context.put("project", "Jakarta");

		/* lets make our own string to render */
		final String templateAsString = "We are using $project $name to render this.";
		actual = VelocityExtensions.merge(context, templateAsString);
		expected = "We are using Jakarta Velocity to render this.";
		/* check if equal */
		AssertJUnit.assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link VelocityExtensions#getTemplate(String)}.
	 * 
	 * @throws ParseException
	 */
	@Test
	public void testGetTemplate() throws ParseException
	{
		String expected;
		String actual;
		/* lets make our own string to render */
		final String templateAsString = "We are using $project $name to render this.";

		Template template = VelocityExtensions.getTemplate(templateAsString);
		AssertJUnit.assertNotNull(template);
		
		expected = "Template name";
		actual = template.getName();
		/* check if equal */
		AssertJUnit.assertEquals(expected, actual);

	}


}
