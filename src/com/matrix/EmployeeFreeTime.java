package com.matrix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author boyuangong created on 11/13/19 at 22:34
 */
public class EmployeeFreeTime {
    private class IntervalComparator implements Comparator<Interval> {
        @Override
        public int compare(Interval a, Interval b) {
            return a.start - b.start > 0? 1 : (a.start == b.start)? 0: -1;
        }
    }
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> list = new ArrayList<>();
        for (List<Interval> single_schedule: schedule) {
            list.addAll(single_schedule);
        }
        Collections.sort(list, new IntervalComparator());
        return   null;
    }
}

class Interval {
    public int start;
    public int end;

    public Interval() {}

    public Interval(int _start,int _end) {
        start = _start;
        end = _end;
    }
};