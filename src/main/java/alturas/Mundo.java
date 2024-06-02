package alturas;

import java.io.*;
import java.util.*;

public class Mundo {
    private List<Pais> paises;

    public Mundo(List<Pais> paisList){
        this.paises=paisList;
    }

    public Mundo(){
        this.paises=new ArrayList<>();
    }

    public List<Pais> getPaises() {
        return paises;
    }

    public static Mundo createFromFile(String file) throws IOException {
        List<Pais> hehe = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String linea;
            while((linea=br.readLine())!=null){
                String[] hydroPump = linea.split(",");
                if(hydroPump.length==3){
                    try{
                        Pais paisAux = new Pais(hydroPump[0], hydroPump[1], Double.parseDouble(hydroPump[2]));
                        hehe.add(paisAux);
                    }catch (NumberFormatException ignored){

                    }
                }
            }
        }catch (IOException exception){
            throw new FileNotFoundException("The file "+file+" does not exist in this route");
        }
        return new Mundo(hehe);
    }

    public static <K,V> void presentaEnConsola(Map<K,V> map){
        for(Map.Entry<K, V> entry : map.entrySet()){
            System.out.println(entry.getKey()+" "+entry.getValue());
        }
    }

    public Map<String, Integer> numeroDePaisesPorContinente(){
        Map<String, Integer> continentCountMap = new HashMap<>();
        for(Pais pais : paises){
            String continente = pais.getContinente();
            continentCountMap.put(continente, continentCountMap.getOrDefault(continente, 0)+1);
        }
        return continentCountMap;
    }

    public Map<Double, List<Pais>> paisesPorAltura(){
        Map<Double, List<Pais>> mapeoAltura = new HashMap<>();
        for(Pais p : paises){
            double alturaTruncada = Math.floor((p.getAltura()*10)/10.0);
            mapeoAltura.computeIfAbsent(alturaTruncada, k -> new ArrayList<>()).add(p);
        }
        return mapeoAltura;
    }

    public Map<String, Set<Pais>> paisesPorContinente(){
        Map<String, Set<Pais>> mapeoDeContinente = new TreeMap<>();
        for(Pais pais : paises){
            String continent = pais.getContinente();
            mapeoDeContinente.computeIfAbsent(continent, k-> new TreeSet<>()).add(pais);
        }
        return mapeoDeContinente;
    }

    public Set<Pais> paisesOrdenadosPorAltura(){
        Set<Pais> ordenadoPorAltura = new TreeSet<>(new CompAltura());
        ordenadoPorAltura.addAll(paises);
        return ordenadoPorAltura;
    }

    public Map<String, Set<Pais>> paisesPorContinenteAltura(){
        Map<String, Set<Pais>> mapeoContinentes = new TreeMap<>();
        for(Pais pais : paises){
            String conti = pais.getContinente();
            mapeoContinentes.computeIfAbsent(conti, k->new TreeSet<>(new CompAltura())).add(pais);
        }
        return mapeoContinentes;
    }

    public Map<String, Set<Pais>> paisesPorContinenteAlturaDec(){
        Map<String, Set<Pais>> mapeoDeContinente = new TreeMap<>();
        for(Pais pais : paises){
            String continente = pais.getContinente();
            mapeoDeContinente.computeIfAbsent(continente, k->new TreeSet<>(Comparator.comparing(Pais::getAltura).reversed())).add(pais);
        }
        return mapeoDeContinente;
    }

    public Map<String, Set<Pais>> paisesPorInicial(){
        Map<String, Set<Pais>> mapeoInicial = new TreeMap<>();
        for(Pais pais : paises){
            String inicial = pais.getNombre().substring(0, 1).toUpperCase();
            mapeoInicial.computeIfAbsent(inicial, k-> new TreeSet<>(Comparator.comparing(Pais::getNombre))).add(pais);
        }
        return mapeoInicial;
    }

    public Map<String, Double> mediaPorContinente(){
        Map<String, Set<Pais>> paisesPorContinente = paisesPorContinente();
        Map<String, Double> mediaAlturaPorContinente = new TreeMap<>();
        for(Map.Entry<String, Set<Pais>> entry : paisesPorContinente.entrySet()){
            String continente = entry.getKey();
            Set<Pais> paisSet = entry.getValue();
            double alturaTotal = 0.0;

            for(Pais pais : paises){
                alturaTotal+=pais.getAltura();
            }

            double mediaAltura = alturaTotal/paises.size();
            mediaAlturaPorContinente.put(continente, mediaAltura);
        }
        return mediaAlturaPorContinente;
    }

    public List<String> continentesConMasPaises(){
        Map<String, Integer> paisesPorContinente =  numeroDePaisesPorContinente();
        List<String> continentesMasPaises = new ArrayList<>();
        int maxPaises = paisesPorContinente.values().stream().max(Integer::compare).orElse(0);
        for(Map.Entry<String, Integer> entry : paisesPorContinente.entrySet()){
            if(entry.getValue()==maxPaises){
                continentesMasPaises.add(entry.getKey());
            }
        }
        return continentesMasPaises;
    }

    public static <K,V> void presentaEnPW(PrintWriter p, Map<K, V> kvMap){
        for(Map.Entry<K, V> entry : kvMap.entrySet()){
            p.println(entry.getKey()+"\t"+entry.getValue());
        }
    }

    public void cargar(String file) throws IOException{
        createFromFile(file);
    }
}
