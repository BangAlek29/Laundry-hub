/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.AbstractSpinnerModel;

/**
 *
 * @author dzikr
 */
public class SpinerTimeModel extends AbstractSpinnerModel {
        private SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
        private Calendar calendar;
        private Calendar startTime;
        private Calendar endTime;
    
        public SpinerTimeModel() {
            calendar = Calendar.getInstance();

            startTime = Calendar.getInstance();
            startTime.set(Calendar.HOUR_OF_DAY, 8);
            startTime.set(Calendar.MINUTE, 0);

            endTime = Calendar.getInstance();
            endTime.set(Calendar.HOUR_OF_DAY, 21);
            endTime.set(Calendar.MINUTE, 0);

            calendar.set(Calendar.HOUR_OF_DAY, 8);
            calendar.set(Calendar.MINUTE, 0);
        }

        @Override
        public Object getValue() {
            return timeFormat.format(calendar.getTime());
        }

        @Override
        public void setValue(Object value) {
            if (value instanceof String) {
                try {
                    calendar.setTime(timeFormat.parse((String) value));
                    fireStateChanged();
                } catch (ParseException e) {
                    
                }
            }
        }

        @Override
        public Object getNextValue() {
            Calendar next = (Calendar) calendar.clone();
            next.add(Calendar.MINUTE, 30);

            if (next.after(endTime)) {
                return null;
            }
            return timeFormat.format(next.getTime());
        }

        @Override
        public Object getPreviousValue() {
            Calendar prev = (Calendar) calendar.clone();
            prev.add(Calendar.MINUTE, -30);

            if (prev.before(startTime)) {
                return null;
            }
            return timeFormat.format(prev.getTime());
        }
}
