package ru.devjava.averchenkov.staff.service.parse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.sql.Date;
import java.util.regex.Pattern;

/**
 * Сервис парсинга данных.
 *
 * @author Averchenkov R.A.
 */
public final class ServiceParse {
    // Количество элементов на странице по умолчанию
    private static final int DEFAULT_COUNT_IN_PAGE = 10;
    // Максимальное количество элементов на странице
    private static final int LIMIT_COUNT_IN_PAGE = 100;

    private static final String REGEX_SIMPLE_STRING_DATA = "^[А-ЯЁа-яё\\-0-9a-zA-Z\\s]*$";
    private static final String REGEX_SIMPLE_NUMBER_DATA = "^[0-9-]*$";
    private static final String REGEX_SIMPLE_DATE_DATA = "^[0-9]{4}\\-[0-9]{1,2}\\-[0-9]{1,2}$";

    private static final Logger logger = LoggerFactory.getLogger(ServiceParse.class);

    /**
     * Проверяет удовлетворяет ли входная строка регулярному
     * выражению '{@value #REGEX_SIMPLE_STRING_DATA}'.
     * @param data входная строка
     * @return удовлетворяет или нет
     */
    public static boolean checkSimpleString(String data){
        logger.debug("[checkSimpleString] with arg: data={}", data);
        if (StringUtils.isEmpty(data)){
            return false;
        }
        return Pattern.matches(REGEX_SIMPLE_STRING_DATA, data);
    }

    /**
     * Проверяет удовлетворяет ли входная строка регулярному
     * выражению '{@value #REGEX_SIMPLE_NUMBER_DATA}'.
     * @param data входная строка
     * @return удовлетворяет или нет
     */
    public static boolean checkSimpleNumber(String data){
        logger.debug("[checkSimpleNumber] with arg: data={}", data);
        if (StringUtils.isEmpty(data)){
            return false;
        }
        return Pattern.matches(REGEX_SIMPLE_NUMBER_DATA, data);
    }

    /**
     * Проверяет удовлетворяет ли входная строка регулярному
     * выражению '{@value #REGEX_SIMPLE_DATE_DATA}'.
     * @param data входная строка
     * @return удовлетворяет или нет
     */
    public static boolean checkSimpleDate(String data){
        logger.debug("[checkSimpleDate] with arg: data={}", data);
        if (StringUtils.isEmpty(data)){
            return false;
        }
        return Pattern.matches(REGEX_SIMPLE_DATE_DATA, data);
    }

    /**
     * Метод парсинга в Integer.
     * @param integer значение для приведения к Integer
     * @param defaultValue значение по-умолчанию
     * @return значение в Integer
     */
    public static Integer parseInteger(String integer, Integer defaultValue){
        Integer value = defaultValue;
        try {
            value = Integer.parseInt(integer);
        } catch (NumberFormatException ex){
            logger.error("Error [parseInteger] parse to Integer: '{}'", integer);
        }
        return value;
    }

    /**
     * Метод парсинга в Long.
     * @param value значение для приведения к Long
     * @param defaultValue значение по-умолчанию
     * @return значение в Long
     */
    public static Long parseLong(String value, Long defaultValue){
        Long l = defaultValue;
        try {
            l = Long.parseLong(value);
        } catch (NumberFormatException ex){
            logger.error("Error [parseLong] parse to Long: '{}'", value);
        }
        return l;
    }

    /**
     * Метод парсинга в Date.
     * @param value значение для приведения к Date
     * @param defaultValue значение по-умолчанию
     * @return значение в Date
     */
    public static Date parseDate(String value, Date defaultValue){
        Date date = defaultValue;
        try {
            date = Date.valueOf(value);
        } catch (IllegalArgumentException ex){
            logger.error("Error [parseDate] parse to Date: '{}'", value);
        }
        return date;
    }

    /**
     * Метод парсинга страницы.
     * @param page страница
     * @return номер страницы
     */
    public static Integer parsePage(String page){
        logger.debug("[parsePage] with arg: page='{}'", page);
        Integer integer = parseInteger(page, 0);
        return (integer >= 0) ? integer : 0;
    }

    /**
     * Метод парсинга количества элементов на странице.
     * @param count количество элементов
     * @return количества элементов
     */
    public static Integer parseCount(String count){
        logger.debug("[parseCount] with arg: count='{}'", count);
        Integer c = parseInteger(count, DEFAULT_COUNT_IN_PAGE);
        c = ((c >= 0) ? c : DEFAULT_COUNT_IN_PAGE);
        return (c > LIMIT_COUNT_IN_PAGE) ? LIMIT_COUNT_IN_PAGE : c;
    }
}
