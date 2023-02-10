package ru.skypro.homework.loger;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormLogInfo {


  public static String getInfo() {
    StackTraceElement STACK_TRACE_ELEMENT = new Throwable().getStackTrace()[1];
    String METHOD_NAME = STACK_TRACE_ELEMENT.getMethodName();
    String CLASS_NAME = STACK_TRACE_ELEMENT.getClassName();
    return new StringBuilder()
        .append("Старт метода ")
        .append("\"")
        .append(METHOD_NAME)
        .append("\"")
        .append(" из класса ")
        .append("\"")
        .append(CLASS_NAME)
        .append("\"").toString();
  }
  public static String getCatch() {
    StackTraceElement STACK_TRACE_ELEMENT = new Throwable().getStackTrace()[1];
    String METHOD_NAME = STACK_TRACE_ELEMENT.getMethodName();
    String CLASS_NAME = STACK_TRACE_ELEMENT.getClassName();
    return new StringBuilder()
        .append(" ВНИМАНЕИЕ: ")
        .append(" Вызван catch в методе ")
        .append("\"")
        .append(METHOD_NAME)
        .append("\"")
        .append(" из класса ")
        .append("\"")
        .append(CLASS_NAME)
        .append("\"").toString();
  }

  public static String getException() {
    StackTraceElement STACK_TRACE_ELEMENT = new Throwable().getStackTrace()[2];
    String METHOD_NAME = STACK_TRACE_ELEMENT.getMethodName();
    String CLASS_NAME = STACK_TRACE_ELEMENT.getClassName();
    return new StringBuilder()
        .append(" ВНИМАНЕИЕ: ")
        .append(" исключение в методе ")
        .append("\"")
        .append(METHOD_NAME)
        .append("\"")
        .append(" из класса ")
        .append("\"")
        .append(CLASS_NAME)
        .append("\"").toString();
  }

}
