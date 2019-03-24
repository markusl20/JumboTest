import graphql.execution.Execution
import graphql.schema.GraphQLList
import groovy.json.JsonSlurper
import groovyx.net.http.RESTClient
import org.apache.http.client.HttpResponseException
import spock.lang.Specification
import spock.lang.Unroll
import java.sql.Timestamp
import gql.DSL

import static groovyx.net.http.ContentType.JSON

class Globals{
    static String api_key = ''

    //pet
    static int petId  = 868686
    static String status = 'available'
    static String name = 'unidog'

    //category
    static int categoryId  = 868686
    static String categoryName  = 'unicorn'

    //tag
    static int tagId = 0
    static String tagName = 'string'

}

class OptimiseTest extends Specification {

    @Override
    String toString() {
        return super.toString()
    }

      def 'Find pet by ID'() {

        given:
        int localPetId = Globals.petId
        String petName = Globals.name
        String petCategoryName = Globals.categoryName
        String testURL = 'https://petstore.swagger.io/v2/pet/' + Globals.petId
        int expectedResponse = 200

        RESTClient restClient = new RESTClient(testURL, JSON)

        when:
        def response = restClient.get(headers: [ Accept: 'application/json'])

        then:
        response.status == expectedResponse
        ((Map) response.data).id == localPetId
        ((Map) response.data).name == petName
        ((Map) response.data).category.name == petCategoryName
    }


    void LogToFile(String message){

        String filename = ".\\samplefile2.txt"
        File logfile = new File(filename)
        usingFileWriter(message, filename, logfile.exists())

    }

    static void usingFileWriter(String message, String fileName, boolean append) throws IOException {

        String output = ('\n'+new Timestamp((new Date()).getTime())).toString() + message

        FileWriter fileWriter = new FileWriter(fileName, append)
        fileWriter.write(output)
        fileWriter.close()
    }

}



