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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.runtime.RuntimeSingleton;
import org.apache.velocity.runtime.parser.ParseException;
import org.apache.velocity.runtime.parser.node.SimpleNode;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import de.alpharogroup.file.create.CreateFileQuietlyExtensions;
import lombok.experimental.UtilityClass;

/**
 * The class {@link VelocityExtensions} provides methods for create velocity template engines and
 * templates.
 */
@UtilityClass
public final class VelocityExtensions
{

	/** The Constant for the value of property 'resource.loader'. */
	public static final String CLASSPATH_VALUE = "classpath";
	/**
	 * The Constant for the key of property 'classpath.resource.loader.class'.
	 */
	public static final String KEY_CLASSPATH_RESOURCE_LOADER_CLASS = "classpath.resource.loader.class";
	/** The Constant VELOCITY_TEMPLATE_FILE_EXTENSION. */
	public static final String VELOCITY_TEMPLATE_FILE_EXTENSION = ".vm";

	/**
	 * Gets the velocity template engine that load resources from the class path.
	 *
	 * @return the velocity template engine
	 */
	public static VelocityEngine getClasspathResourceLoaderVelocityEngine()
	{
		final VelocityEngine ve = new VelocityEngine();
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, CLASSPATH_VALUE);
		ve.setProperty(KEY_CLASSPATH_RESOURCE_LOADER_CLASS,
			ClasspathResourceLoader.class.getName());
		ve.init();
		return ve;
	}

	/**
	 * Gets a velocity template from the given String.
	 *
	 * @param templateAsString
	 *            the template as string
	 * @return the template
	 * @throws ParseException
	 *             the parse exception
	 */
	public static Template getTemplate(final String templateAsString) throws ParseException
	{
		return getTemplate(templateAsString, "Template name", "UTF-8");
	}

	/**
	 * Gets a velocity template from the given String.
	 *
	 * @param templateAsString
	 *            the template as string
	 * @param templateName
	 *            the template name
	 * @return the template
	 * @throws ParseException
	 *             the parse exception
	 */
	public static Template getTemplate(final String templateAsString, final String templateName)
		throws ParseException
	{
		return getTemplate(templateAsString, templateName, "UTF-8");
	}

	/**
	 * Gets a velocity template from the given String.
	 *
	 * @param templateAsString
	 *            the template as string
	 * @param templateName
	 *            the template name
	 * @param encoding
	 *            the encoding
	 * @return the template
	 * @throws ParseException
	 *             the parse exception
	 */
	public static Template getTemplate(final String templateAsString, final String templateName,
		final String encoding) throws ParseException
	{
		final RuntimeServices runtimeServices = RuntimeSingleton.getRuntimeServices();
		final StringReader reader = new StringReader(templateAsString);
		final SimpleNode node = runtimeServices.parse(reader, templateName);
		final Template template = new Template();
		template.setName(templateName);
		template.setRuntimeServices(runtimeServices);
		template.setData(node);
		template.initDocument();
		template.setEncoding(encoding);
		return template;
	}

	/**
	 * Gets the velocity template with default encoding "UTF-8".
	 *
	 * @param ve
	 *            the velocity template engine
	 * @param path
	 *            the path
	 * @param templateName
	 *            the template name
	 * @return the template
	 */
	public static Template getTemplate(final VelocityEngine ve, final String path,
		final String templateName)
	{
		return getTemplate(ve, path, templateName, "UTF-8");
	}

	/**
	 * Gets the velocity template.
	 *
	 * @param ve
	 *            the velocity template engine
	 * @param path
	 *            the path
	 * @param templateName
	 *            the template name
	 * @param encoding
	 *            the encoding
	 * @return the template
	 */
	public static Template getTemplate(final VelocityEngine ve, final String path,
		final String templateName, final String encoding)
	{
		final String templatePath = path + templateName + VELOCITY_TEMPLATE_FILE_EXTENSION;
		return getVelocityTemplate(ve, templatePath, encoding);
	}

	/**
	 * Gets the velocity template.
	 *
	 * @param ve
	 *            the velocity template engine
	 * @param templatePath
	 *            the absolute path with the filename
	 * @return the velocity template
	 */
	private static Template getVelocityTemplate(final VelocityEngine ve, final String templatePath)
	{
		return getVelocityTemplate(ve, templatePath, "UTF-8");
	}

	/**
	 * Gets the velocity template.
	 *
	 * @param ve
	 *            the velocity template engine
	 * @param templatePath
	 *            the absolute path with the filename
	 * @param encoding
	 *            the encoding
	 * @return the velocity template
	 */
	private static Template getVelocityTemplate(final VelocityEngine ve, final String templatePath,
		final String encoding)
	{
		final Template template;
		if (ve != null)
		{
			template = ve.getTemplate(templatePath, encoding);
		}
		else
		{
			template = Velocity.getTemplate(templatePath, encoding);
		}
		return template;
	}

	/**
	 * Merges the given context with the given template that is a String object and returns the
	 * result.
	 *
	 * @param context
	 *            the context
	 * @param velocityProperties
	 *            the velocity properties
	 * @param logTag
	 *            to be used as the template name for log messages
	 * @param templateAsString
	 *            the template as string
	 * @return the string
	 */
	public static String merge(final VelocityContext context, final Properties velocityProperties,
		final String logTag, final String templateAsString)
	{
		final StringWriter writer = new StringWriter();
		if (velocityProperties != null && !velocityProperties.isEmpty())
		{
			Velocity.init(velocityProperties);
		}
		Velocity.evaluate(context, writer, logTag, templateAsString);
		return writer.toString();
	}

	/**
	 * Merges the given context with the given template that is a String object and returns the
	 * result.
	 *
	 * @param context
	 *            the context
	 * @param templateAsString
	 *            the template as string
	 * @return the string
	 */
	public static String merge(final VelocityContext context, final String templateAsString)
	{
		return merge(context, "VelocityUtilsMergeMethod", templateAsString);
	}

	/**
	 * Merges the given context with the given template that is a String object and returns the
	 * result.
	 *
	 * @param context
	 *            the context
	 * @param logTag
	 *            to be used as the template name for log messages
	 * @param templateAsString
	 *            the template as string
	 * @return the string
	 */
	public static String merge(final VelocityContext context, final String logTag,
		final String templateAsString)
	{
		return merge(context, null, logTag, templateAsString);
	}

	/**
	 * Merges the given template file to the given file in the given velocity context.
	 *
	 * @param ve
	 *            the velocity template engine
	 * @param context
	 *            the velocity context
	 * @param templateFileName
	 *            the file name of the template file
	 * @param fileName
	 *            the file name that will be created
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void mergeToContext(final VelocityEngine ve, final VelocityContext context,
		final String templateFileName, final String fileName) throws IOException
	{
		File generatedClassFile;
		generatedClassFile = new File(fileName);
		CreateFileQuietlyExtensions.newFileQuietly(generatedClassFile);
		final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));
		final Template template = getVelocityTemplate(ve, templateFileName);
		template.merge(context, bufferedWriter);
		bufferedWriter.flush();
		bufferedWriter.close();
	}

	/**
	 * Merges the given template file to the given file in the given velocity context.
	 *
	 * @param ve
	 *            the velocity template engine
	 * @param context
	 *            the velocity context
	 * @param path
	 *            the path
	 * @param templateName
	 *            the template name
	 * @param fileName
	 *            the file name that will be created
	 * @param encoding
	 *            the encoding
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void mergeToContext(final VelocityEngine ve, final VelocityContext context,
		final String path, final String templateName, final String fileName, final String encoding)
		throws IOException
	{
		File generatedClassFile;
		generatedClassFile = new File(fileName);
		CreateFileQuietlyExtensions.newFileQuietly(generatedClassFile);
		final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));
		final Template template = getTemplate(ve, path, templateName, encoding);
		template.merge(context, bufferedWriter);
		bufferedWriter.flush();
		bufferedWriter.close();
	}

	/**
	 * Factory method for create a new {@link VelocityContext}.
	 *
	 * @return the new {@link VelocityContext}
	 */
	public static VelocityContext newVelocityContext()
	{
		final VelocityContext context = new VelocityContext();
		return context;
	}

}
