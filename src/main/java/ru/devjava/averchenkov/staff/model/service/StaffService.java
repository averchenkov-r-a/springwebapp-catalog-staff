package ru.devjava.averchenkov.staff.model.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.devjava.averchenkov.staff.model.entity.Staff;
import ru.devjava.averchenkov.staff.model.repositories.StaffRepositories;
import ru.devjava.averchenkov.staff.model.repositories.specification.SearchCriteria;
import ru.devjava.averchenkov.staff.model.repositories.specification.StaffSpecification;
import ru.devjava.averchenkov.staff.model.service.structure.IStaffService;

import java.sql.Date;
import java.util.Arrays;
import java.util.regex.Pattern;


/**
 * Класс работы с данными сотрудника.
 *
 * @author Averchenkov R.A.
 */
@Service
public class StaffService implements IStaffService {
    private static final int DEFAULT_LIMIT_FIND_ALL = 10;
    private static final int LIMIT_COUNT_IN_PAGE = 100;

    private static final String REGEX_SIMPLE_STRING_DATA = "^[А-ЯЁа-яё\\-0-9a-zA-Z\\s]*$";

    private static final Logger logger = LoggerFactory.getLogger(StaffService.class);

    private StaffRepositories staffRepositories;
    @Autowired
    public void setStaffRepositories(StaffRepositories staffRepositories) {
        this.staffRepositories = staffRepositories;
    }

    @Override
    public Staff saveStaff(Staff staff) {
        logger.debug("[saveStaff] with arg: {}", staff);
        Staff saveStaff = staffRepositories.save(staff);
        logger.debug("Saved in [saveStaff]: {}", saveStaff);

        return saveStaff;
    }

    @Override
    public Page<Staff> getStaffById(String stfId) {
        return getStaff("stfId", stfId, 0, 1);
    }

    @Override
    public Page<Staff> getAllStaff(String page, String count) {
        return getStaff(null, "", page, count);
    }

    @Override
    public Page<Staff> getStaffByName(String stfName, String page, String count) {
        return getStaff("stfName", stfName, page, count);
    }

    @Override
    public Page<Staff> getStaffBySurname(String stfSurName, String page, String count) {
        return getStaff("stfSurname", stfSurName, page, count);
    }

    @Override
    public Page<Staff> getStaffByPatronymic(String stfPatronymic, String page, String count) {
        return getStaff("stfPatronymic", stfPatronymic, page, count);
    }

    @Override
    public Page<Staff> getStaffByPost(String stfPost, String page, String count) {
        return getStaff("stfPost", stfPost, page, count);
    }

    @Override
    public Page<Staff> getStaffByBirthday(String stfBirthday, String page, String count) {
        Date date = null;
        try {
            date = Date.valueOf(stfBirthday);
        } catch (IllegalArgumentException ex){
            logger.error("Error [getStaffByBirthday] parse to Date: '{}'", stfBirthday);
        }

        return getStaff("stfBirthday", date, page, count);
    }

    /**
     * Проверяет удовлетворяет ли входная строка регулярному выражению '{@value #REGEX_SIMPLE_STRING_DATA}'.
     * @param data входная строка
     * @return удовлетворяет или нет
     */
    private static boolean checkSimpleString(String data){
        logger.debug("[checkSimpleString] with arg: data={}", data);
        if (data == null){
            return false;
        }
        return Pattern.matches(REGEX_SIMPLE_STRING_DATA, data);
    }

    /**
     * Метод парсинга в Integer.
     * @param integer значение для приведения к Integer
     * @param defaultValue значение по-умолчанию
     * @return значение в Integer
     */
    private Integer parseInteger(String integer, Integer defaultValue){
        Integer value = defaultValue;
        try {
            value = Integer.parseInt(integer);
        } catch (NumberFormatException ex){
            logger.error("Error [parseInteger] parse to Integer: '{}'", integer);
        }
        return value;
    }

    /**
     * Метод парсинга страницы.
     * @param page страница
     * @return номер страницы
     */
    private Integer parsePage(String page){
        logger.debug("[parsePage] with arg: page='{}'", page);
        return parseInteger(page, 0);
    }

    /**
     * Метод парсинга количества элементов на странице.
     * @param count количество элементов
     * @return количества элементов
     */
    private Integer parseCount(String count){
        logger.debug("[parseCount] with arg: count='{}'", count);
        Integer c = parseInteger(count, DEFAULT_LIMIT_FIND_ALL);
        return c > LIMIT_COUNT_IN_PAGE ? LIMIT_COUNT_IN_PAGE : c;
    }

    /**
     * Метод получение сотрудников согласно критериям.
     * @param stfColumn наименование поля колонки
     * @param stfValue искомое значение
     * @param page номер страницы
     * @param count количество элементов на странице
     * @return страницу сотрудников
     */
    private Page<Staff> getStaffWithValueObject(String stfColumn, Object stfValue,
                                                Integer page, Integer count) {
        logger.debug("[getStaff by {}] with input args: name={}, page={}; count={}",
                new Object[]{stfColumn, stfValue, page, count});

        Page<Staff> staffs = staffRepositories.findAll(
                new StaffSpecification(
                        new SearchCriteria(stfColumn, "=", stfValue)),
                new PageRequest(page, count,
                        new Sort(Sort.Direction.ASC, stfColumn != null ? stfColumn : "stfId")));

        logger.debug("[getStaff by {}] return {} values.", stfColumn, staffs.getSize());
        return staffs;
    }

    /**
     * Метод получение сотрудников согласно критериям.
     * @param stfColumn наименование поля колонки
     * @param stfValue искомое значение
     * @param page номер страницы
     * @param count количество элементов на странице
     * @return
     */
    private Page<Staff> getStaff(String stfColumn, Object stfValue,
                                 String page, String count) {
        Integer p = parsePage(page);
        Integer c = parseCount(count);

        return getStaffWithValueObject(stfColumn, stfValue, p, c);
    }

    /**
     * Метод получение сотрудников согласно критериям.
     * @param stfColumn наименование поля колонки
     * @param stfValue искомое значение
     * @param page номер страницы
     * @param count количество элементов на странице
     * @return страницу сотрудников
     */
    private Page<Staff> getStaff(String stfColumn, String stfValue,
                                 Integer page, Integer count) {
        if (!checkSimpleString(stfValue)){
            logger.error("Error [getStaff by {}] format data: '{}'", stfColumn, stfValue);
            return new PageImpl<Staff>(Arrays.asList());
        }
        return getStaffWithValueObject(stfColumn, stfValue, page, count);
    }
}
