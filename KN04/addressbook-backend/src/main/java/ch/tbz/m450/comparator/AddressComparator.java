package ch.tbz.m450.comparator;

import ch.tbz.m450.repository.Address;
import java.util.Comparator;

public class AddressComparator implements Comparator<Address> {

    @Override
    public int compare(Address a1, Address a2) {
        if (a1 == null && a2 == null) return 0;
        if (a1 == null) return -1;
        if (a2 == null) return 1;

        int lastnameCompare = compareNullSafe(a1.getLastname(), a2.getLastname());
        if (lastnameCompare != 0) return lastnameCompare;

        int firstnameCompare = compareNullSafe(a1.getFirstname(), a2.getFirstname());
        if (firstnameCompare != 0) return firstnameCompare;

        return compareNullSafe(a1.getStreet(), a2.getStreet());
    }

    private int compareNullSafe(String s1, String s2) {
        if (s1 == null && s2 == null) return 0;
        if (s1 == null) return -1;
        if (s2 == null) return 1;
        return s1.compareToIgnoreCase(s2);
    }
}