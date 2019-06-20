package xmlform;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.Reader;

/**
 * Convert a simple XML file to text.
 *
 * @author Ian Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class XmlForm {
    protected Reader is;
    protected String fileName;

    protected static PrintStream msg = System.out;

    /**
     * Construct a converter given an input filename
     */
    public XmlForm(String fn) {
        fileName = fn;
    }

    /**
     * Convert the file
     */
    public void convert(boolean verbose) {
        try {
            if (verbose)
                System.err.println(">>>Parsing " + fileName + "...");
            // Make the document a URL so relative DTD works.
            //String uri = "file:" + new File(fileName).getAbsolutePath();
            InputStream uri = getClass().getResourceAsStream(fileName);
            DocumentBuilderFactory factory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(uri);
            if (verbose)
                System.err.println(">>>Walking " + fileName + "...");
            XmlFormWalker c = new GenMIF(doc, msg);
            c.convertAll();

        } catch (Exception ex) {
            System.err.println("+================================+");
            System.err.println("|         *Parse Error*          |");
            System.err.println("+================================+");
            System.err.println(ex.getClass());
            System.err.println(ex.getMessage());
            System.err.println("+================================+");
        }
        if (verbose)
            System.err.println(">>>Done " + fileName + "...");
    }

    public static void main(String[] av) {
        if (av.length == 0) {
            System.err.println("Usage: XmlForm file");
            return;
        }
        for (int i = 0; i < av.length; i++) {
            String name = av[i];
            new XmlForm(name).convert(true);
        }
        msg.close();
    }
}
// END main
