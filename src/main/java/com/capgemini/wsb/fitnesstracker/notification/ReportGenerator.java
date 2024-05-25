package com.capgemini.wsb.fitnesstracker.notification;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
@AllArgsConstructor
@Slf4j
public class ReportGenerator {
    //TODO mailtrap.io -- strona do testowania wysylki maili - konfiguracja jamlowa do springa [ lab 25.05 min 30] - trzeba wkleic do pliku application.yml w resources
    JavaMailSender javaMailSender;

    //TODO - uzyc CRONA zamiast fixed delay , patrz nagranie z lab 25.05 g. 14.30 - uzyc generatora
    //dla kazdego usera wygenerowac raport treningow i wyslac mailem, trzeba pobrac treningi zeby moc sie dostac do userow
    @Scheduled(fixedDelay=10000, initialDelay=1000)
    public void logToStdOut()
    {
        javaMailSender.send();
        log.info("Report generated");
    }

}
