package alturas;

import java.io.*;
import java.util.*;

public class Mundo {
    // List to store countries (Pais)
    private List<Pais> paises;

    // Constructor that accepts a list of countries
    public Mundo(List<Pais> paisList){
        this.paises=paisList;
    }

    // Default constructor that initializes an empty list of countries
    public Mundo(){
        this.paises=new ArrayList<>();
    }

    // Getter method for the list of countries
    public List<Pais> getPaises() {
        return this.paises;
    }

    // Static method to create a Mundo object from a file
    public void cargar(String file) throws IOException {
        List<Pais> paisList = new ArrayList<>();
        // Try-with-resources to ensure the BufferedReader is closed after use
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            // Read each line from the file
            while ((line = br.readLine()) != null) {
                String[] hydroPump = line.split(",");
                // Ensure the line has exactly three parts
                if (hydroPump.length == 3) {
                    try {
                        String nombre = hydroPump[0].trim();
                        String continente = hydroPump[1].trim();
                        double altura = Double.parseDouble(hydroPump[2].trim());
                        // Create a Pais object and add it to the list
                        Pais paisAux = new Pais(nombre, continente, altura);
                        paisList.add(paisAux);
                        //System.out.println("Added: " + paisAux); // Linea para comprobar que se estan añadiendo a la lista (:
                    } catch (NumberFormatException ignored) {
                        //System.err.println("Skipping line due to parse error: " + line); // Linea para debug :)
                    }
                }
            }
        } catch (IOException e) {
            throw new FileNotFoundException("The file " + file + " does not exist in this route");
        }
        Mundo mundoFile = new Mundo(paisList);
        this.paises = mundoFile.getPaises();
    }

    // Method to print a map to the console
    public static <K,V> void presentaEnConsola(Map<K,V> map){
        for(Map.Entry<K, V> entry : map.entrySet()){
            System.out.println(entry.getKey()+" "+entry.getValue());
        }
    }

    // Method to count the number of countries per continent
    public Map<String, Integer> numeroDePaisesPorContinente(){
        Map<String, Integer> continentCountMap = new TreeMap<>();
        for(Pais pais : paises){
            String continente = pais.getContinente();
            // Increment the count for the continent
            continentCountMap.put(continente, continentCountMap.getOrDefault(continente, 0)+1);
        }
        return continentCountMap;
    }

    // Method to group countries by truncated height
    public Map<Double, List<Pais>> paisesPorAltura() {
        Map<Double, List<Pais>> mapeoAltura = new TreeMap<>();
        for (Pais p : paises) {
            double alturaTruncada = Math.floor(p.getAltura() * 10) / 10.0;
            // Add the country to the list for its truncated height
            mapeoAltura.computeIfAbsent(alturaTruncada, k -> new ArrayList<>()).add(p);
        }
        return mapeoAltura;
    }

    // Method to group countries by continent
    public Map<String, Set<Pais>> paisesPorContinente(){
        Map<String, Set<Pais>> mapeoDeContinente = new TreeMap<>();
        for(Pais pais : paises){
            String continent = pais.getContinente();
            // Add the country to the set for its continent
            mapeoDeContinente.computeIfAbsent(continent, k-> new TreeSet<>()).add(pais);
        }
        return mapeoDeContinente;
    }

    // Method to get countries sorted by height
    public Set<Pais> paisesOrdenadosPorAltura(){
        Set<Pais> ordenadoPorAltura = new TreeSet<>(new CompAltura());
        ordenadoPorAltura.addAll(paises);
        return ordenadoPorAltura;
    }

    // Method to group countries by continent and sort them by height within each continent
    public Map<String, Set<Pais>> paisesPorContinenteAltura(){
        Map<String, Set<Pais>> mapeoContinentes = new TreeMap<>();
        for(Pais pais : paises){
            String conti = pais.getContinente();
            // Add the country to the set for its continent, sorting by height
            mapeoContinentes.computeIfAbsent(conti, k->new TreeSet<>(new CompAltura())).add(pais);
        }
        return mapeoContinentes;
    }

    // Method to group countries by continent and sort them by height in descending order within each continent
    public Map<String, Set<Pais>> paisesPorContinenteAlturaDec(){
        Map<String, Set<Pais>> mapeoDeContinente = new TreeMap<>();
        for(Pais pais : paises){
            String continente = pais.getContinente();
            // Add the country to the set for its continent, sorting by height in descending order
            mapeoDeContinente.computeIfAbsent(continente, k->new TreeSet<>(Comparator.comparing(Pais::getAltura).reversed())).add(pais);
        }
        return mapeoDeContinente;
    }

    // Group continents by the initial letter of their name
    public Map<String, Set<Pais>> paisesPorInicial(){
        Map<String, Set<Pais>> mapeoInicial = new TreeMap<>();
        for(Pais pais : paises){
            String inicial = pais.getNombre().substring(0, 1).toUpperCase();
            // Add the country to the set for its initial letter
            mapeoInicial.computeIfAbsent(inicial, k-> new TreeSet<>(Comparator.comparing(Pais::getNombre))).add(pais);
        }
        return mapeoInicial;
    }

    // Method to calculate average height of countries per continent
    public Map<String, Double> mediaPorContinente(){
        Map<String, Set<Pais>> paisesPorContinente = paisesPorContinente();
        Map<String, Double> mediaAlturaPorContinente = new TreeMap<>();
        for(Map.Entry<String, Set<Pais>> entry : paisesPorContinente.entrySet()){
            String continente = entry.getKey();
            Set<Pais> paisSet = entry.getValue();
            double alturaTotal = 0.0;

            for(Pais pais : paisSet){
                alturaTotal+=pais.getAltura();
            }

            // Calculate the average height
            double mediaAltura = alturaTotal/paisSet.size();
            mediaAlturaPorContinente.put(continente, mediaAltura);
        }
        return mediaAlturaPorContinente;
    }

    // Find continents with the most countries
    public List<String> continentesConMasPaises(){
        Map<String, Integer> paisesPorContinente =  numeroDePaisesPorContinente();
        List<String> continentesMasPaises = new ArrayList<>();
        // Find the maximum number of countries
        int maxPaises = paisesPorContinente.values().stream().max(Integer::compare).orElse(0);
        for(Map.Entry<String, Integer> entry : paisesPorContinente.entrySet()){
            if(entry.getValue()==maxPaises){
                continentesMasPaises.add(entry.getKey());
            }
        }
        return continentesMasPaises;
    }

    // Print a given Map to a PrintWriter
    public static <K,V> void presentaEnPW(PrintWriter p, Map<K, V> kvMap){
        for(Map.Entry<K, V> entry : kvMap.entrySet()){
            p.println(entry.getKey()+"\t"+entry.getValue());
        }
    }
}
