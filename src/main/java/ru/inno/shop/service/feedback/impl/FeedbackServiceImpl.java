package ru.inno.shop.service.feedback.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.inno.shop.model.feedback.Feedback;
import ru.inno.shop.model.feedback.FeedbackStatus;
import ru.inno.shop.repository.feedback.FeedbackDao;
import ru.inno.shop.service.feedback.FeedbackService;
import javax.transaction.Transactional;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Артём Матюнин
 * Реализация сервиса работы с обратными связями
 */

@Slf4j
@Service
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackDao feedbackDao;
    private final FeedbackListMapper flMap;

    @Autowired
    public FeedbackServiceImpl(FeedbackDao feedbackDao, FeedbackListMapper flMap) {
        this.feedbackDao = feedbackDao;
        this.flMap = flMap;
    }

    /**
     * Получение объекта по Id
     * @param id Идентификатор объекта в БД
     * @return Найденный в бД объект
     * @throws IllegalArgumentException если id <=0
     */
    @Override
    @Transactional
    public Feedback getById(int id) {
        if (id <= 0) {
            log.warn("Некорректное значение id {}", id);
            throw new IllegalArgumentException("Некорректные параметры запроса");
        }
        return feedbackDao.findById(id).orElse(new Feedback());


    }

    /**
     * Полчает объект для сохранения, устанавливает ему статус NEW, дату создания и обновления (одинаковые)
     * @param feedback Подготовленный для записи в БД объект
     * @return Сохраненный объект из БД
     * @throws IllegalArgumentException если feedback = null
     */
    @Override
    @Transactional
    public Feedback createFeedback(Feedback feedback) {
        if (feedback == null) {
            log.warn("Некорретный входной параметр feedback = null");
            throw new IllegalArgumentException("Некорретные параметры запроса");
        }
        log.info("Получено сообщение {}, {}", feedback.getFeedbackSubject(), feedback.getFeedbackMessage());
        feedback.setFeedbackDate(Date.from(Instant.now()));
        feedback.setFeedbackUpdDate(Date.from(Instant.now()));
        feedback.setFeedbackStatus(FeedbackStatus.NEW);
        return feedbackDao.save(feedback);

    }

    /**
     * Получаем объект, устанвливаем ему статус Complete, дату обновления и записываем в БД
     * @param feedback Подготовленный для обновления в БД объект
     * @throws IllegalArgumentException если feedback = null
     */
    @Override
    @Transactional
    public void updateFeedback(Feedback feedback) {
        if (feedback == null) {
            log.warn("Некорретный входной параметр feedback = null");
            throw new IllegalArgumentException("Некорретные параметры запроса");
        }
        feedback.setFeedbackUpdDate(Date.from(Instant.now()));
        feedback.setFeedbackStatus(FeedbackStatus.COMPLETE);
        feedbackDao.save(feedback);
        log.info("Пользователь {} обновил запись {}", feedback.getUser(),feedback.getFeedbackId());
    }

    /**
     * Получение списка обратных связей по статусу в виде страницы
     * @param feedbackStatus Требуемый статус
     * @return список найденных объектов
     * @throws IllegalArgumentException если pageable = null
     */
    @Override
    @Transactional
    public Page<Feedback> getFeedbackByStatus(FeedbackStatus feedbackStatus, Pageable pageable){
        if(feedbackStatus==null){
            log.warn("Некорретный входной параметр feedbackStatus = null");
            throw new IllegalArgumentException("Некорретные параметры запроса");
        }

        return feedbackDao.findAllByFeedbackStatusOrderByFeedbackId(feedbackStatus, pageable );

    }

    /**
     * Запрашиваем из БД весь списокновых обратных свзязей и группируем его по дате в более мелкие списки
     * @param feedbackStatus Требуемый статус
     * @return Список, содержащий списки, сгруппированные по дате
     * @throws IllegalArgumentException если feedbackStatus = null
     */
    @Override
    @Transactional
    public List<List<Feedback>> getAndGroupFeedbacks(FeedbackStatus feedbackStatus) {
        if (feedbackStatus == null) {
            log.warn("Некорретный входной параметр feedbackStatus = null");
            throw new IllegalArgumentException("Некорретные параметры запроса");
        }
        List<Feedback> feedbackList = new ArrayList<>(feedbackDao.findFeedbackByFeedbackStatusOrderByFeedbackDateDescFeedbackId(feedbackStatus));
        return flMap.convertToListOfLists(feedbackList);
    }
}