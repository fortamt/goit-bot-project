package ua.goit.moneybot.model;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Scheduler {
    Calendar calendar = Calendar.getInstance();
    Timer time = new Timer();
    Date date = new Date();
    UserService userService = new UserService();

    TimerTask timerTask2 = new TimerTask() {
        @Override
        public void run() {
            //System.out.println("Current time user_2222222222222222222222222 " + date.getTime());
            for (int i = 0; i <= userService.userList.size(); i++) {
                if (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) == userService.userList.get(i).getNotificationTime()) {

                    //выводим текущий курс валют

                }
            }
        }
    };

    public void findTimeNotificationList() {

        calendar.set(Calendar.HOUR_OF_DAY, 9);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        time.schedule(timerTask2, calendar.getTime(), TimeUnit.HOURS.toMillis(1));

    }
}
