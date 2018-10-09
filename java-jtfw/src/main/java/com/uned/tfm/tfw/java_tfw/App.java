package com.uned.tfm.tfw.java_tfw;

import java.io.InputStream;

import org.apache.log4j.BasicConfigurator;
import org.apache.tika.Tika;
import org.apache.tika.config.TikaConfig;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
    	//org.apache.log4j
    	BasicConfigurator.configure();
    	
    	//com.uned.tfm.tfw.java_tfw.Jsopa
    	Jsopa jsopa = new Jsopa("url");
        for (int i=1; i<jsopa.getMaxPages(); i++){
			
            String urlPage = String.format(jsopa.getURL(), i);
            System.out.println("Comprobando entradas de: "+urlPage);
			
            // Compruebo si me da un 200 al hacer la petición
            if (jsopa.getStatusConnectionCode(urlPage) == 200) {
				
                // Obtengo el HTML de la web en un objeto Document2
                Document document = jsopa.getHtmlDocument(urlPage);
				
                // Busco todas las historias de meneame que estan dentro de: 
                Elements entradas = document.select("div.col-md-4.col-xs-12").not("div.col-md-offset-2.col-md-4.col-xs-12");
				
                // Paseo cada una de las entradas
                for (Element elem : entradas) {
                    String titulo = elem.getElementsByClass("tituloPost").text();
                    String autor = elem.getElementsByClass("autor").toString();
                    String fecha = elem.getElementsByClass("fecha").text();
					
                    System.out.println(titulo+"\n"+autor+"\n"+fecha+"\n");
					
                }

                
            	//org.apache.tika.Tika
            	//TikaConfig config = new TikaConfig(customConfFile.toURI().toURL(), App.class.getClass().getClassLoader());
            	//Parser p = new AutoDetectParser(config);
            	
            	Tika tika = new Tika();
            	//Maven carga recursos
            	
                try (InputStream stream = App.class.getResourceAsStream(document.toString())) {
        	    	System.out.println(document.toString()/*tika.parseToString(stream)*/);
        	        System.out.println( "Hello World!" );
                }
		
            }else{
                System.out.println("El Status Code no es OK es: " + jsopa.getStatusConnectionCode(urlPage));
                break;
            }
        }
        
    	//org.apache.tika.Tika
    	//TikaConfig config = new TikaConfig(customConfFile.toURI().toURL(), App.class.getClass().getClassLoader());
    	//Parser p = new AutoDetectParser(config);
    	
    	Tika tika = new Tika();
    	//Maven carga recursos
    	
        try (InputStream stream = App.class.getResourceAsStream("test2.docx")) {
	    	System.out.println(tika.parseToString(stream));
	        System.out.println( "Hello World!" );
        }
    }
}
