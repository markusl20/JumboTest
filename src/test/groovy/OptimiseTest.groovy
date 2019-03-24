import graphql.execution.Execution
import graphql.schema.GraphQLList
import groovy.json.JsonSlurper
import groovyx.net.http.RESTClient
import spock.lang.Specification
import spock.lang.Unroll
import java.sql.Timestamp
import gql.DSL

import static groovyx.net.http.ContentType.JSON

class Globals{
    static String api_key = ''
}

class OptimiseTest extends Specification {

    @Override
    String toString() {
        return super.toString()
    }

    def 'Find pet by ID'() {

        given:
        int petId = 2
        String petName = 'Tinku'
        String petCategorName = 'German Shepard'
        String testURL = 'https://petstore.swagger.io/v2/pet/' + petId.toString()
        int expectedResponse = 200

        RESTClient restClient = new RESTClient(testURL, JSON)

        when:
        //def response = restClient.post(path: '//pet', body: jsonObj, headers: [ Accept: 'application/json'])
        def response = restClient.get(headers: [ Accept: 'application/json'])

        then:
        response.status == expectedResponse
        ((Map) response.data).id == petId
        ((Map) response.data).name == petName
        ((Map) response.data).category.name == petCategorName
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



