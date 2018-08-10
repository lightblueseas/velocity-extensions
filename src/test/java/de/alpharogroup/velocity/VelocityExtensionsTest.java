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

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.parser.ParseException;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.meanbean.factories.ObjectCreationException;
import org.meanbean.test.BeanTestException;
import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import de.alpharogroup.file.delete.DeleteFileExtensions;
import de.alpharogroup.file.read.ReadFileExtensions;

/**
 * The unit test class for the class {@link VelocityExtensions}.
 */
public class VelocityExtensionsTest
{

	/**
	 * Test method for {@link VelocityExtensions#getClasspathResourceLoaderVelocityEngine()}.
	 */
	@Test
	public void testGetClasspathResourceLoaderVelocityEngine()
	{
		final VelocityEngine velocityEngine = VelocityExtensions
			.getClasspathResourceLoaderVelocityEngine();
		assertNotNull(velocityEngine);
	}

	/**
	 * Test method for {@link VelocityExtensions#getTemplate(String)}.
	 *
	 * @throws ParseException
	 */
	@Test
	public void testGetTemplateString() throws ParseException
	{
		String expected;
		String actual;
		/* lets make our own string to render */
		final String templateAsString = "We are using $project $name to render this.";

		final Template template = VelocityExtensions.getTemplate(templateAsString);
		assertNotNull(template);

		expected = "Template name";
		actual = template.getName();
		/* check if equal */
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link VelocityExtensions#getTemplate(String, String)}.
	 *
	 * @throws ParseException
	 */
	@Test
	public void testGetTemplateStringString() throws ParseException
	{
		String expected;
		String actual;
		/* lets make our own string to render */
		final String templateAsString = "We are using $project $name to render this.";

		expected = "foo";
		final Template template = VelocityExtensions.getTemplate(templateAsString, expected);
		assertNotNull(template);

		actual = template.getName();
		/* check if equal */
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link VelocityExtensions#getTemplate(String, String, String)}.
	 *
	 * @throws ParseException
	 */
	@Test
	public void testGetTemplateStringStringString() throws ParseException
	{
		String expected;
		String actual;
		/* lets make our own string to render */
		final String templateAsString = "We are using $project $name to render this.";

		expected = "foo";
		final Template template = VelocityExtensions.getTemplate(templateAsString, expected,
			"UTF-8");
		assertNotNull(template);

		actual = template.getName();
		/* check if equal */
		assertEquals(expected, actual);

		expected = "UTF-8";
		actual = template.getEncoding();
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link VelocityExtensions#getTemplate(VelocityEngine, String, String)}.
	 */
	@Test
	public void testGetTemplateVelocityEngineStringString()
	{
		String expected;
		String actual;
		final VelocityEngine ve = new VelocityEngine();
		ve.init();

		final Template template = VelocityExtensions.getTemplate(ve, "src/test/resources/", "test");
		assertNotNull(template);

		final VelocityContext context = new VelocityContext();

		context.put("name", "Velocity");
		context.put("project", "Jakarta");

		final StringWriter writer = new StringWriter();
		template.merge(context, writer);

		actual = writer.toString();
		expected = "We are using Jakarta Velocity to render this.";
		/* check if equal */
		assertEquals(expected, actual);
	}

	/**
	 * Test method for
	 * {@link VelocityExtensions#getTemplate(VelocityEngine, String, String, String)}.
	 */
	@Test
	public void testGetTemplateVelocityEngineStringStringString()
	{
		String expected;
		String actual;
		VelocityEngine ve;
		Template template;
		VelocityContext context;
		StringWriter writer;
		// 1. scenario...
		ve = new VelocityEngine();
		ve.init();

		template = VelocityExtensions.getTemplate(ve, "src/test/resources/", "test", "UTF-8");
		assertNotNull(template);

		context = new VelocityContext();

		context.put("name", "Velocity");
		context.put("project", "Jakarta");

		writer = new StringWriter();
		template.merge(context, writer);

		actual = writer.toString();
		expected = "We are using Jakarta Velocity to render this.";
		/* check if equal */
		assertEquals(expected, actual);
		// 2. scenario...
		ve = null;

		template = VelocityExtensions.getTemplate(ve, "src/test/resources/", "test", "UTF-8");
		assertNotNull(template);

		context = new VelocityContext();

		context.put("name", "Velocity");
		context.put("project", "Jakarta");

		writer = new StringWriter();
		template.merge(context, writer);

		actual = writer.toString();
		expected = "We are using Jakarta Velocity to render this.";
		/* check if equal */
		assertEquals(expected, actual);
	}

	/**
	 * Test method for
	 * {@link VelocityExtensions#mergeToContext(VelocityEngine, VelocityContext, String, String)}.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testMergeToContextVelocityEngineVelocityContextStringString() throws IOException
	{
		String expected;
		String actual;

		final String fileName = "test.txt";
		final VelocityEngine engine = VelocityExtensions.getClasspathResourceLoaderVelocityEngine();
		final VelocityContext context = VelocityExtensions.newVelocityContext();
		File generatedClassFile;
		generatedClassFile = new File(fileName);

		context.put("name", "Velocity");
		context.put("project", "Jakarta");

		VelocityExtensions.mergeToContext(engine, context, "test.vm", fileName);

		actual = ReadFileExtensions.readFromFile(generatedClassFile);
		expected = "We are using Jakarta Velocity to render this.";
		/* check if equal */
		assertEquals(expected, actual);
		// clean up...
		DeleteFileExtensions.delete(generatedClassFile);
	}

	/**
	 * Test method for
	 * {@link VelocityExtensions#mergeToContext(VelocityEngine, VelocityContext, String, String, String, String)}.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testMergeToContextVelocityEngineVelocityContextStringStringStringString()
		throws IOException
	{
		String actual;
		String expected;

		VelocityEngine engine;
		String fileName;
		VelocityContext context;
		File generatedClassFile;

		fileName = "test.txt";
		engine = new VelocityEngine();
		engine.init();
		context = VelocityExtensions.newVelocityContext();
		generatedClassFile = new File(fileName);

		context.put("name", "Velocity");
		context.put("project", "Jakarta");

		VelocityExtensions.mergeToContext(engine, context, "src/test/resources/", "test", fileName,
			"UTF-8");

		actual = ReadFileExtensions.readFromFile(generatedClassFile);
		expected = "We are using Jakarta Velocity to render this.";
		/* check if equal */
		assertEquals(expected, actual);
		// clean up...
		DeleteFileExtensions.delete(generatedClassFile);
	}

	/**
	 * Test method for
	 * {@link VelocityExtensions#merge(VelocityContext, Properties, String, String)}.
	 */
	@Test
	public void testMergeVelocityContextPropertiesStringString()
	{
		String expected;
		String actual;
		/* first, we init the runtime engine. Defaults are fine. */
		Velocity.init();

		/* lets make a Context and put data into it */
		final VelocityContext context = new VelocityContext();

		context.put("name", "Velocity");
		context.put("project", "Jakarta");

		final Properties velocityProperties = new Properties();
		velocityProperties.setProperty(RuntimeConstants.RESOURCE_LOADER, "class");
		velocityProperties.setProperty(VelocityExtensions.KEY_CLASSPATH_RESOURCE_LOADER_CLASS,
			ClasspathResourceLoader.class.getName());

		/* lets make our own string to render */
		final String templateAsString = "We are using $project $name to render this.";
		actual = VelocityExtensions.merge(context, velocityProperties, "logTag", templateAsString);
		expected = "We are using Jakarta Velocity to render this.";
		/* check if equal */
		assertEquals(expected, actual);
	}

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
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link VelocityExtensions#merge(VelocityContext, String, String)}.
	 */
	@Test
	public void testMergeVelocityContextStringString()
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
		actual = VelocityExtensions.merge(context, "logTag", templateAsString);
		expected = "We are using Jakarta Velocity to render this.";
		/* check if equal */
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link VelocityExtensions#newVelocityContext()}.
	 */
	@Test
	public void testNewVelocityContext()
	{
		final VelocityContext context = VelocityExtensions.newVelocityContext();
		assertNotNull(context);
	}

	/**
	 * Test method for {@link VelocityExtensions}
	 */
	@Test(expectedExceptions = { BeanTestException.class, ObjectCreationException.class })
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(VelocityExtensions.class);
	}

}
