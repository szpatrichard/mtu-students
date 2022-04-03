package mtu_student_app.models.Comparators;

import mtu_student_app.models.Record;

import java.util.Comparator;

public class GradeComparator implements Comparator<Record> {
    @Override
    public int compare(Record r1, Record r2) {
        Integer g1 = r1.getGrade();
        Integer g2 = r2.getGrade();
        return g1.compareTo(g2);
    }
}
