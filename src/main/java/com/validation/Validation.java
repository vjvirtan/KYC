package com.validation;

import java.io.*;
import java.lang.reflect.*;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import javax.tools.StandardJavaFileManager;
import javax.tools.JavaFileObject;

public record Validation<T>(Class<T> dataClass) {

  // 1. Do Class, variables, Constructur(Builder builder)
  // 2. Builder class
  // 3. Builder constructors

  // private ValidationRule field

  public Validation(Class<T> dataClass) {
    this.dataClass = dataClass;
    String newClassName = dataClass.getSimpleName() + "Rules";
    try {
      Class<?> testClass = Class.forName("com.temp." + newClassName);
      if (testClass != null) {
        return;
      }
    } catch (ClassNotFoundException e) {
    }
    StringBuilder classBuid = new StringBuilder();
    String[] parts = constructClassElements("builder");

    classBuid.append("package com.temp;");
    classBuid.append("import com.exeption.valid.*;");

    classBuid.append("public class " + newClassName + "{");
    classBuid.append(parts[0]);
    classBuid.append("public " + newClassName + "(Builder builder) {");
    classBuid.append(parts[2]);
    classBuid.append("}");
    classBuid.append(" public static Builder builder() { return new Builder(); }");

    classBuid.append(" public static class Builder{");
    classBuid.append(parts[0]);
    classBuid.append("public Builder(){}");
    classBuid.append(" public Builder(" + parts[3] + " ) {");
    classBuid.append(parts[4]);
    classBuid.append("}");
    classBuid.append(parts[1]);
    classBuid.append(" public " + newClassName + " build() { return new " + newClassName + " (this);}");
    // BUILDER CLASS END
    classBuid.append("}");
    classBuid.append("public final String toString() { return {" + parts[5] + "}}");
    classBuid.append("}");

    /////////////////////
    try (FileWriter fw = new FileWriter(
        "src/main/java/com/temp/" + newClassName + ".java")) {
      fw.write(classBuid.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    runClass(newClassName);
  }

  private void runClass(String newClassName) {
    String path = "src/main/java/com/temp/" + newClassName + ".java";
    File source = new File(path);
    JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
    try (StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null)) {
      Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjects(source);
      JavaCompiler.CompilationTask task = compiler.getTask(
          null,
          fileManager,
          null,
          null,
          null,
          compilationUnits);

      task.call();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private String[] constructClassElements(String builderVariable) {
    String[] ret = new String[6];
    StringBuilder methods = new StringBuilder();
    StringBuilder variables = new StringBuilder();
    StringBuilder builderVariables = new StringBuilder();
    StringBuilder builderConstructorImports = new StringBuilder();
    StringBuilder builderConstructorVariables = new StringBuilder();
    StringBuilder toString = new StringBuilder();

    for (Field field : dataClass.getDeclaredFields()) {
      variables.append("private ValidationRule " + field.getName() + ";");
      builderConstructorImports.append("ValidationRule " + field.getName() + ",");
      methods.append("public Builder" + " " + field.getName() + "(ValidationRule validationRule ) { ");
      methods.append("this." + field.getName() + " =  validationRule;");
      methods.append("return this; }");
      builderVariables
          .append("this." + field.getName() + " = " + builderVariable + "." + field.getName() + ";");
      builderConstructorVariables.append("this." + field.getName() + " = " + field.getName() + ";");
      toString.append("\"" + field.getName() + "\":" + "\"" + field.getName() + "\".toString(),");

    }

    builderConstructorImports.deleteCharAt(builderConstructorImports.length() - 1);
    ret[0] = variables.toString();
    ret[1] = methods.toString();
    ret[2] = builderVariables.toString();
    ret[3] = builderConstructorImports.toString();
    ret[4] = builderConstructorVariables.toString();
    ret[5] = toString.toString();
    return ret;
  }

}
