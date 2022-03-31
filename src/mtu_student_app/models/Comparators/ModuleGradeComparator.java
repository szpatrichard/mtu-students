package mtu_student_app.models.Comparators;

import java.util.Comparator;

public class ModuleGradeComparator implements Comparator<Integer> {
    @Override
    public int compare(Integer g1, Integer g2) {
        return g1.compareTo(g2);
    }
}
