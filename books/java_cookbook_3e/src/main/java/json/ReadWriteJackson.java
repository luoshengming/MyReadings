package json;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Person;

import java.io.IOException;

// BEGIN main
public class ReadWriteJackson {

    public static void main(String[] args) throws IOException {
        // <1>
        ObjectMapper mapper = new ObjectMapper();

        // <2>
        String jsonInput = "{\"id\":0,\"firstName\":\"Robin\",\"lastName\":\"Wilson\"}";
        Person q = mapper.readValue(jsonInput, Person.class);
        System.out.println("Read and parsed Person from JSON: " + q);

        // <3>
        Person p = new Person("Roger", "Rabbit");
        System.out.print("Person object " + p + " as JSON = ");
        mapper.writeValue(System.out, p);
    }
}
// END main
