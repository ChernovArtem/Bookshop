package ru.inno.shop.controller.feedback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.inno.shop.model.feedback.Feedback;
import ru.inno.shop.model.feedback.FeedbackStatus;
import ru.inno.shop.model.user.User;
import ru.inno.shop.service.feedback.FeedbackService;
import ru.inno.shop.service.user.UserService;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author Артём Матюнин
 * Контроллер получения обратной связи и работы с ней
 */
@Slf4j
@Controller
@RequestMapping
public class FeedbackController {

    private final FeedbackService feedbackService;
    private final UserService userService;

    @Autowired
    public FeedbackController(FeedbackService feedbackService, UserService userService) {
        this.feedbackService = feedbackService;
        this.userService = userService;
    }

    /***
     * Вызов формы регистрации обратной связи
     * @param model модель для возврата на страницу
     * @return Страница регистрации
     */

    @GetMapping(value = "contact")
    public String viewForm(Model model) {
        model.addAttribute("feedback", new Feedback());
        return "contact/contact";
    }

    /**
     * Создание обратной связи после зполнени формы
     *
     * @param feedback Объект без Id для запсис в базу
     * @param model    Заполняем модкль сохраненной обратной связью для возврата на страницу
     * @return Возвращаем на форму зарегистрированный ответ
     */
    @PostMapping(value = "contact")
    public String create(@ModelAttribute("feedback") Feedback feedback, Model model) {
        if (feedback == null) {
            log.warn("Получен пустой объект feedback = null");
            return "error/error404";
        }
        Feedback savedFeedback = feedbackService.createFeedback(feedback);
        model.addAttribute("feedback", savedFeedback);
        return "contact/contact";

    }

    /**
     * Показать все необработанные обратные связи
     *
     * @param model Добавляем в модель список всех обратных связей, со статусом NEW, сгруппировнный по дате
     * @return Передаем на страницу модель со списком
     */
    @Secured(value = "ROLE_ADMIN")
    @GetMapping(value = "newfeedbacks")
    public String viewNewFeedbacks(Model model) {

        model.addAttribute("feedbacks", feedbackService.getAndGroupFeedbacks(FeedbackStatus.NEW));
        return "feedback/feedback";

    }

    /**
     * Поиск обратной связи по Id
     *
     * @param feedbackId Id для поиска записи в БД
     * @param model      Заполняем модель найденной обратной связью для возврата на страницу
     * @return Возвращаем на форму подробностей модель
     */
    @Secured(value = "ROLE_ADMIN")
    @GetMapping(value = "viewfeedback")
    public String viewOneFeedback(@RequestParam(value = "feedbackId") String feedbackId, Model model) {

        if (feedbackId == null || feedbackId.isEmpty()) {
            log.warn("Некорректный параметр feedbackId = {}", feedbackId);
            return "error/error404";
        }

        try {
            Feedback feedback = feedbackService.getById(Integer.parseInt(feedbackId));
            if (feedback.getUser() != null) {
                model.addAttribute("user", userService.findById(feedback.getUser()));
            }
            model.addAttribute("feedback", feedback);
        } catch (NumberFormatException e) {
            log.warn("Получен некорректный параметр feedbackId = {}", feedbackId);
            return "error/error404";
        }

        return "feedback/onefeedback";
    }

    /**
     * Показать все обработанные обратные связи
     *
     * @param model Заполняем модель найденным списком
     * @param page  Запрошенная страница
     * @return Передаем на страницу список по-странично
     */
    @Secured(value = "ROLE_ADMIN")
    @GetMapping(value = "oldfeedbacks", params = "page")
    public String viewOldFeedbacks(@RequestParam String page, Model model) {
        if (page == null || page.isEmpty()) {
            log.warn("Получен некорректный параметр page = {}", page);
            return "redirect:/error404";
        }
        try {
            Pageable pageable = PageRequest.of(Integer.parseInt(page), 5);
            model.addAttribute("oldfeedbacks", feedbackService.getFeedbackByStatus(FeedbackStatus.COMPLETE, pageable));
        } catch (NumberFormatException e) {
            log.warn("Получен некорректный параметр page = {}", page);
            return "redirect:/error404";
        }
        return "feedback/feedback";
    }

    /**
     * Обновление записи обратной связи(для администратора/менеджера)
     * Обновляется статус, дата обновления и пользователь
     *
     * @param date     Дата создания передается отдельным параметром, чтобы не запрашивать ее из БД и обработать ее форматтером для вставки в объект
     * @param feedback Подготовленный бъект обратной связи для обновления (без даты обновления)
     */
    @Secured(value = "ROLE_ADMIN")
    @PostMapping(value = "updfeedback", params = {"date"})
    public String updateFeedback(@ModelAttribute(value = "feedback") Feedback feedback, String date) {

        if (feedback == null) {
            log.warn("Из формы получен пустой объект feedback = NULL");
            return "error/error404";
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            feedback.setFeedbackDate(dateFormat.parse(date));
        } catch (ParseException e) {
            log.warn("Из формы получен некорректный объект даты {}", date);
            return "error/error404";
        }

        User currentUser = userService.getCurrentAuthUser();
        feedback.setUser(currentUser.getId());
        feedbackService.updateFeedback(feedback);

        return "redirect:/newfeedbacks";
    }
}
