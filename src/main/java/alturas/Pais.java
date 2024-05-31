package alturas;

import java.util.Comparator;
import java.util.Objects;

public class Pais implements Comparator<Pais> {
    private final String nombre;
    private final String continente;
    private final double altura;

    public Pais(String n, String c, double a){
        if(n == null || n.trim().isEmpty()||c == null || c.trim().isEmpty()){
            throw new IllegalArgumentException("El nombre del pais o del continente no puede estar vacio");
        } else if (a<=0) {
            throw new IllegalArgumentException("La altura media del pais no puede ser menor o igual que 0");
        }else{
            this.nombre=n.trim();
            this.continente=c.trim();
            this.altura=a;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public double getAltura() {
        return altura;
    }

    public String getContinente() {
        return continente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pais pais = (Pais) o;
        return this.nombre.equalsIgnoreCase(pais.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre.toUpperCase());
    }

    @Override
    public String toString() {
        return "("+nombre+", "+continente+", "+altura+")";
    }

    @Override
    public int compare(Pais o1, Pais o2) {
        return o1.nombre.compareToIgnoreCase(o2.nombre);
    }
}
