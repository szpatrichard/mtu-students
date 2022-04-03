package mtu_student_app.models.Comparators;

import java.util.Comparator;

import mtu_student_app.models.Record;

public class ModuleTitleComparator implements Comparator<Record> {
    @Override
    public int compare(Record r1, Record r2) {
        String mt1 = r1.getModule().getTitle();
        String mt2 = r2.getModule().getTitle();
        return mt1.compareTo(mt2);
    }
}
