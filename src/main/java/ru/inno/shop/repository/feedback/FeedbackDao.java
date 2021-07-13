package ru.inno.shop.repository.feedback;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.inno.shop.model.feedback.Feedback;
import ru.inno.shop.model.feedback.FeedbackStatus;
import java.util.List;

/**
 * @author Артём Матюнин
 * Репозиторий для Feedback-entities
 */

public interface FeedbackDao extends JpaRepository<Feedback, Integer> {

    /**
     * Сортировка по дате и Id
     * @param feedbackStatus Требуемый статус
     * @return Полученный список объектов
     */
    List<Feedback> findFeedbackByFeedbackStatusOrderByFeedbackDateDescFeedbackId(FeedbackStatus feedbackStatus);

    /**
     * Постаничный запрос по статусу. Сортивка по Id.
     * @param feedbackStatus Требуемый статус
     * @param pageable Включает номер страницы и количество записей на ней.
     * @return Запрошенный набор данных в виде страницы.
     */
    Page<Feedback> findAllByFeedbackStatusOrderByFeedbackId(FeedbackStatus feedbackStatus, Pageable pageable);
}
