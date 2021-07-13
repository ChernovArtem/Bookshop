package ru.inno.shop.service.feedback.impl;

import org.springframework.stereotype.Component;
import ru.inno.shop.model.feedback.Feedback;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Артём Матюни
 * класс-маппер
 */
@Component
public class FeedbackListMapper {

    /**
     * Маппер общего списка в список списков
     * Проходим по общему списку объектов на несколько маленьких списков
     * @param feedbackList Список всех овых обратных связей (статус NEW)
     * @return Список, содержащий списки, сгруппированные по дате
     */
    public List<List<Feedback>> convertToListOfLists(List<Feedback> feedbackList) {
        List<List<Feedback>> mapList = new ArrayList<>();
        Date date;
        for (int i = 0; i < feedbackList.size() - 1; ) {
            date = feedbackList.get(i).getFeedbackDate();
            List<Feedback> map = new ArrayList<>();
            map.add(feedbackList.get(i));
            i++;
            while (date.equals(feedbackList.get(i).getFeedbackDate()) && i < feedbackList.size() - 1) {
                map.add(feedbackList.get(i));
                i++;
            }
            mapList.add(map);
        }
        return mapList;
    }
}
