package event;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Michael
 * Date: 17.07.13
 * Time: 20:49
 * To change this template use File | Settings | File Templates.
 */
public class TimedEventSinkUtil {

    private static List<AWTEvent> recordedDelayedEvents = new ArrayList<AWTEvent>();
    private static int delayedEventCount = 0;
    private static long lastRecordedEventTime = new Date().getTime();
    private static AWTEvent lastEvent = null;

    private TimedEventSinkUtil() {
    }

    public static AWTEvent sinkEvent(final AWTEvent event, final int minTimeMillis) {
        long timePassedSinceLastEvent = new Date().getTime() - lastRecordedEventTime;
        lastRecordedEventTime = new Date().getTime();
        if (timePassedSinceLastEvent > minTimeMillis) {
            System.out.println(String.format("Returning single event that respected max frequency of [%s]", minTimeMillis));
            delayedEventCount = 0;
            recordedDelayedEvents.clear();
            lastEvent = null;
            return event;
        } else {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    System.out.println(String.format("To many consecutive events max frequency is [%s]", minTimeMillis));
                    recordedDelayedEvents.add(event);
                    delayedEventCount++;
                    System.out.println(String.format("Current sunk event count: [%s]", delayedEventCount));
                    Timer countDown = new Timer(minTimeMillis, createDelayActionListener());
                    countDown.setActionCommand(String.valueOf(delayedEventCount));
                    countDown.setRepeats(false);
                    countDown.start();
                }
            });
            return lastEvent;
        }
    }

    private static ActionListener createDelayActionListener() {
        ActionListener afterDelayActionListener = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getActionCommand().equals(String.valueOf(delayedEventCount))) {
                    System.out.println(String.format("Returning *LATEST* event after sinking [%s] events", delayedEventCount));
                    lastEvent = recordedDelayedEvents.get(recordedDelayedEvents.size() - 1);
                    delayedEventCount = 0;
                    recordedDelayedEvents.clear();
                } else {
                    lastEvent = null;
                }
            }
        };
        return afterDelayActionListener;
    }
}

