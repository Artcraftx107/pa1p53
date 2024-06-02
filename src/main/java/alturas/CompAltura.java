package alturas;

import java.util.Comparator;

public class CompAltura implements Comparator<Pais> {
    @Override
    public int compare(Pais p1, Pais p2) {
        int heightComparison = Double.compare(p1.getAltura(), p2.getAltura());
        if (heightComparison != 0) {
            return heightComparison;
        } else {
            return p1.getNombre().compareTo(p2.getNombre());
        }
    }
}
