package ru.inno.shop.model.feedback;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/***
 * @author Артём Матюнин
 * Модель обратной связи, поступающей с сайта
 */

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "feedback")
public class Feedback {

    @Id
    @Column(name = "feedback_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer feedbackId;

    /** Имя */
    @Basic
    @Column(name = "feedback_f_name")
    private String feedbackFName;

    /** Фамилия */
    @Basic
    @Column(name = "feedback_l_name")
    private String feedbackLName;

    /** Адрес почты */
    @Basic
    @Column(name = "feedback_email")
    private String feedbackEmail;

    /** Телефон */
    @Basic
    @Column(name = "feedback_phone")
    private String feedbackPhone;

    /** Текст обратной связи */
    @Basic
    @Column(name = "feedback_message")
    private String feedbackMessage;

    /** Дата регистрации */
    @Temporal(TemporalType.DATE)
    @Column(name = "feedback_create_date")
    private Date feedbackDate;

    /** Статус */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "feedback_status")
    private FeedbackStatus feedbackStatus;

    /** Тема */
    @Basic
    @Column(name = "feedback_subject")
    private String feedbackSubject;

    /** Комментарий пользователя */
    @Basic
    @Column(name="feedback_comment")
    private String feedbackComment;

    /** Пользователь */
    @Basic
    @Column(name = "feedback_user")
    private Long user;

    /** Дата реакции */
    @Temporal(TemporalType.DATE)
    @Column(name = "feedback_upd_date")
    private Date feedbackUpdDate;
}
