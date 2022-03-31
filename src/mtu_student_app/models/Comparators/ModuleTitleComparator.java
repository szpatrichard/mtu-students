package mtu_student_app.models.Comparators;

import java.util.Comparator;

import mtu_student_app.models.Module;

public class ModuleTitleComparator implements Comparator<Module> {
    @Override
    public int compare(Module m1, Module m2) {
        String mt1 = m1.getTitle();
        String mt2 = m2.getTitle();
        return mt1.compareTo(mt2);
    }
}
