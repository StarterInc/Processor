/**
 * 
 */
package io.starter;

/**
 * @author John McMahon Copyright 2015 Starter Inc., all rights reserved.
 *
 */
import java.io.*;

import javax.tools.*;

import java.util.*;

import javax.annotation.processing.*;
import javax.lang.model.*;
import javax.lang.model.element.*;

@SupportedAnnotationTypes(value = { "*" })
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class AspectProcessor extends AbstractProcessor {

	private Filer filer;

	public void init(ProcessingEnvironment env) {
		filer = env.getFiler();
	}

	public boolean process(Set elements, RoundEnvironment env) {

		// Discover anything marked with @AutoGenerate
		for (Element element : env.getElementsAnnotatedWith(AutoGenerate.class)) {
			if (element.getKind() == ElementKind.METHOD) {
				// For any methods we find, create an aspect:
				String methodName = element.getSimpleName().toString();
				String aspectText = "public aspect Advise_" + methodName
						+ " {\n" + "  before(): execution(* " + methodName
						+ "(..)) {\n" + "    System.out.println(\""
						+ methodName + " running AutoGenerate\");\n" + "  }\n"
						+ "}\n";
				try {
					JavaFileObject file = filer.createSourceFile("Advise_"
							+ methodName, element);
					file.openWriter().append(aspectText).close();
					System.err.println("Generated aspect to advise "
							+ methodName);
				} catch (IOException ioe) {
				}
			}
		}
		
		// Discover anything marked with @SuppressWarnings
		for (Element element : env
				.getElementsAnnotatedWith(SuppressWarnings.class)) {
			if (element.getKind() == ElementKind.METHOD) {
				// For any methods we find, create an aspect:
				String methodName = element.getSimpleName().toString();
				String aspectText = "public aspect Advise_" + methodName
						+ " {\n" + "  before(): execution(* " + methodName
						+ "(..)) {\n" + "    System.out.println(\""
						+ methodName + " running SUPPRESSWARNINGS\");\n"
						+ "  }\n" + "}\n";
				
				
				try {
					JavaFileObject file = filer.createSourceFile("Advise_"
							+ methodName, element);
					file.openWriter().append(aspectText).close();
					System.out.println("Generated aspect to advise "
							+ methodName);
				} catch (IOException ioe) {
				}
				
				// add a method why not?
				String methodsTest = "public Object get"+methodName+"Value(Object obj){\n"
						+ "			return obj.toString();\n" + "		};\r\n";
				
				
				try {
					JavaFileObject file = filer.createSourceFile("MethodGen_"
							+ methodName, element);
					file.openWriter().append(methodsTest).close();
					System.out.println("Generated method getValue"+methodName+ " for advised "
							+ methodName);
				} catch (IOException ioe) {
				}
				

			}
		}

		for (Element element : env.getElementsAnnotatedWith(Deprecated.class)) {
			if (element.getKind() == ElementKind.METHOD) {
				// For any methods we find, create an aspect:
				String methodName = element.getSimpleName().toString();
				String aspectText = "public aspect Advise_" + methodName
						+ " {\n" + "  before(): execution(* " + methodName
						+ "(..)) {\n" + "    System.out.println(\""
						+ methodName + " running DEPRECATED\");\n" + "  }\n"
						+ "}\n";
				try {
					JavaFileObject file = filer.createSourceFile("Advise_"
							+ methodName, element);
					file.openWriter().append(aspectText).close();
					System.out.println("Generated aspect to advise "
							+ methodName);
				} catch (IOException ioe) {
				}
			}
		}
		return true;
	}
}