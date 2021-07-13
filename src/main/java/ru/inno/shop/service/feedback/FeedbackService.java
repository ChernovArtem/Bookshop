package ru.inno.shop.service.feedback;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.inno.shop.model.feedback.Feedback;
import ru.inno.shop.model.feedback.FeedbackStatus;
import javax.transaction.Transactional;
import java.util.List;

/***
 * @author Артём
 * Сервис работы с обратными связями
 */
public interface FeedbackService {

    /**
     * Постарничный запрос по статусу
     * @param feedbackStatus треуемый статус
     * @return Страница найденных объектов (Page)
     */
    @Transactional
    Page<Feedback> getFeedbackByStatus(FeedbackStatus feedbackStatus, Pageable pageable);

    /**
     *
     * @param feedback Объект Feedback без Id
     * @return сохраненный в БД объект Feedback (с Id)
     */
    @Transactional
    Feedback createFeedback(Feedback feedback);

    /**
     *
     * @param feedback Объект Feedback с Id
     */
    @Transactional
    void updateFeedback(Feedback feedback);

    /**
     *
     * @param feedbackStatus треуемый статус
     * @return Список сгруппированных списков обратных связей
     */
    @Transactional
    List<List<Feedback>>getAndGroupFeedbacks(FeedbackStatus feedbackStatus);

    /**
     *
     * @param id Идентификатор записи в БД
     * @return Найденный в БД объект Feedback
     */
    @Transactional
    Feedback getById(int id);
}
