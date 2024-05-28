package com.capgemini.wsb.fitnesstracker.notification;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import com.capgemini.wsb.fitnesstracker.mail.api.EmailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static java.util.Calendar.DAY_OF_MONTH;

//TODO mailtrap.io -- strona do testowania wysylki maili - konfiguracja jamlowa do springa [ lab 25.05 min 30] - trzeba wkleic do pliku application.yml w resources
//TODO - uzyc CRONA zamiast fixed delay , patrz nagranie z lab 25.05 g. 14.30 - uzyc generatora
//dla kazdego usera wygenerowac raport treningow i wyslac mailem, trzeba pobrac treningi zeby moc sie dostac do userow

@EnableScheduling
@Service
@Data
@Component
@AllArgsConstructor
@Slf4j
public class ReportGenerator {

    private static final String TITLE = "Last week's training report";

    private final JavaMailSender javaMailSender;
    private final UserProvider userProvider;
    private final TrainingProvider trainingProvider;

    /*@Component
    @AllArgsConstructor
    public class JavaSpringEmailSender implements Email Sender (
    private final JavaMailSender javaMailSender ;
    @Override
    public void send (EmailDto email ) SimpleMailMessage message = new SimpleMailMessage ( ) ;
    message.setFrom ("cap.wsb@fitnesstracker.com") ;
    message.setTo (email.toAddress());
    message.setSubject(email.subject());
    message.setText(email.content());
    javaMailSender.send(message);*/


    //@Scheduled(fixedDelay=10000, initialDelay=1000)
    //public void logToStdOut()
    //{
        //javaMailSender.send();
        //log.info("Report generated");
    //} (*    */30    22    *    4,5,6,7    FRI)
    // @Scheduled(cron = "0 0 12 ? * 1") this cron is for sending emails as given in instructions, for testing other cron is used
    @Scheduled(cron = "0 * * * * *")
    public void generateReport() {
        log.info("Starting generation of training reports");
        final List<User> allUsers = userProvider.findAllUsers();
        allUsers.forEach(user -> {
            final SimpleMailMessage email = createEmail(user.getEmail(), TITLE, trainingProvider.getAllTrainingsForUser(user.getId()));
            log.info("Sending email to {}", user.getEmail());
            javaMailSender.send(email);
        });

        log.info("Generation of training reports finished");
    }
    private SimpleMailMessage createEmail(final String recipient, final String title, final List<Training> trainings) {
        final Date lastWeek = returnBeginningOfLastWeek();
        final Date yesterday = returnYesterday();
        final List<Training> lastWeekTrainings = trainings.stream().filter(training -> training.getStartTime().after(lastWeek) && training.getStartTime().before(yesterday)).toList();
        log.info("Creating email for {}", recipient);
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(title);
        email.setTo(recipient);
        final StringBuilder builder = new StringBuilder("""
                You had %s trainings last week,
                completing %s units of distance
                You have completed %s trainings overall.
                Below you can find detailed rundown of your trainings last week:
                ----
                """.formatted(lastWeekTrainings.size(),
                trainings.isEmpty() ? 0 : lastWeekTrainings.stream().mapToDouble(Training::getDistance).sum(),
                trainings.size()
        ));
        lastWeekTrainings.forEach(training -> {
            builder.append("""
                training start: %s
                training end: %s
                activity type: %s
                distance: %s
                average speed: %s
                ----
                """.formatted(training.getStartTime(),
                    training.getEndTime() == null ? "-" : training.getEndTime(),
                    training.getActivityType(),
                    training.getDistance(),
                    training.getAverageSpeed()
            ));
        });
        // Minimal requirement according to mr Weychan was to present data in the console
        email.setText(builder.toString());
        log.info("Email created");
        System.out.println(builder);
        return email;
    }

    private Date returnBeginningOfLastWeek() {
        final Calendar now = Calendar.getInstance();
        now.add(DAY_OF_MONTH, -7);
        return now.getTime();
    }

    private Date returnYesterday() {
        final Calendar now = Calendar.getInstance();
        now.add(DAY_OF_MONTH, -1);
        return now.getTime();
    }

}
