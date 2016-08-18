package ru.devjava.averchenkov.staff.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.devjava.averchenkov.staff.model.entity.Staff;
import ru.devjava.averchenkov.staff.model.repositories.StaffRepositories;
import ru.devjava.averchenkov.staff.model.repositories.specification.SearchCriteria;
import ru.devjava.averchenkov.staff.model.repositories.specification.StaffSpecification;
import ru.devjava.averchenkov.staff.service.parse.ServiceParse;
import ru.devjava.averchenkov.staff.service.structure.IStaffService;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Класс работы с данными сотрудника.
 *
 * @author Averchenkov R.A.
 */
@Service
public class StaffService implements IStaffService {
    private static final Logger logger = LoggerFactory.getLogger(StaffService.class);

    private StaffRepositories staffRepositories;
    @Autowired
    public void setStaffRepositories(StaffRepositories staffRepositories) {
        this.staffRepositories = staffRepositories;
    }

    private Field[] declaredFields;

    @PostConstruct
    public void init(){
        declaredFields = Staff.class.getDeclaredFields();
    }

    @Override
    public Staff saveStaff(Staff staff) {
        logger.debug("[saveStaff] with arg: {}", staff);
        Staff saveStaff = staffRepositories.save(staff);
        logger.debug("Saved in [saveStaff]: {}", saveStaff);

        return saveStaff;
    }

    @Override
    public Page<Staff> findStaff(String page, String count, String[] params){
        logger.debug("[findStaff] with input args: params={}, page={}; count={}",
                new Object[]{ params, page, count });

        if (params.length != declaredFields.length){
            logger.error("Error [findStaff] count items in params != count fields in Staff class.");
            return new PageImpl<Staff>(Arrays.asList());
        }

        Integer p = ServiceParse.parsePage(page);
        Integer c = ServiceParse.parseCount(count);

        if (isNotCriteria(params)){
            logger.debug("[findStaff] return all items with args: page={}, count={}", page, count);
            return getStaff(Specifications.where(StaffSpecification.emptySpecification()), p, c);
        }

        // Настраиваем критерии выборки (специцикации)
        List<Specification> specs = new ArrayList<>();
        for (int i = 0; i < declaredFields.length; i++) {
            Field field = declaredFields[i];
            Class<?> type = field.getType();
            String value = params[i];

            if (type.equals(Long.class)){
                if(ServiceParse.checkSimpleNumber(value)){
                    specs.add(eqSpecification(field.getName(), ServiceParse.parseLong(value, 0l)));
                }
            } else if (type.equals(String.class)){
                if (ServiceParse.checkSimpleString(value)){
                    specs.add(eqSpecification(field.getName(), value));
                }
            } else if (type.equals(Date.class)){
                ServiceParse.checkSimpleDate(value);
                Date date = ServiceParse.parseDate(value, null);
                if (date != null){
                    specs.add(eqSpecification(field.getName(), date));
                }
            }
        }

        if (specs.size() == 0){
            logger.debug("[findStaff] not found criteria.");
            return new PageImpl<Staff>(Arrays.asList());
        }

        Page<Staff> staffs = getStaff(transformListToSpecs(specs), p, c);
        return staffs;
    }

    /**
     * Метод прелбразования List<Specification<Staff>> в Specifications<Staff>.
     * @param specs лист специцикаций
     * @return объект Specifications
     */
    private Specifications transformListToSpecs(List<Specification> specs){
        Specifications specifications = null;
        for (Specification spec : specs) {
            logger.debug("[transformListToSpecs] spec: ", spec);
            if (specifications == null){
                specifications = Specifications.where(spec);
            } else {
                specifications = specifications.and(spec);
            }
        }
        return specifications;
    }

    /**
     * Проверяет есть ли критерии выборки.
     * @param params параметры для выборки
     * @return true если нет критерив выборки
     */
    private boolean isNotCriteria(String[] params){
        for (String param : params) {
            if (!StringUtils.isEmpty(param)){
                return false;
            }
        }
        return true;
    }

    /**
     * Создает простую специцикацию (критерий выбоки) на равенство.
     * @param column наименование колонки (из полей Persistence класа)
     * @param value значение выбоки
     * @return объект спецификации
     */
    private Specification<Staff> eqSpecification(String column, Object value){
        return new StaffSpecification(new SearchCriteria(column, "=", value));
    }

    /**
     * Метод получение сотрудников из базы данных согласно критериям.
     * @param specifications набор критериев
     * @param page номер страницы
     * @param count количество элементов на странице
     * @return страницу сотрудников
     */
    private Page<Staff> getStaff(Specifications specifications, Integer page, Integer count) {
        logger.debug("[getStaff] with input args: page={}; count={}",
                new Object[]{ page, count});

        Page<Staff> staffs = staffRepositories.findAll(specifications,
                new PageRequest(page, count));

        logger.debug("[getStaff] return {} elements.",
                staffs.getTotalElements());
        return staffs;
    }
}
