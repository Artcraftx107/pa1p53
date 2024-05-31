package alturas;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Mundo {
    private List<Pais> paises;

    public Mundo(List<Pais> paisList){
        this.paises=paisList;
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
        
    }
}
