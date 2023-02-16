package fr.minesstetienne.ci.dcn.service;

import org.apache.jena.graph.Graph;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.shacl.ShaclValidator;
import org.apache.jena.shacl.Shapes;
import org.apache.jena.shacl.ValidationReport;
import org.apache.jena.sparql.graph.GraphFactory;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @author YoucTagh
 */
public class ValidationService {

    public ValidationService() {
    }

    public static boolean isRepresentationValid(String data, String profileIRI) {
        try {

            InputStream stream = new ByteArrayInputStream(data.getBytes());

//            Graph dataGraph = RDFDataMgr.loadGraph(representationIRI);
            Graph dataGraph = GraphFactory.createDefaultGraph();
            RDFDataMgr.read(dataGraph, stream, Lang.TTL);


            Graph shapesGraph = RDFDataMgr.loadGraph(profileIRI);
            Shapes shapes = Shapes.parse(shapesGraph);


            if (dataGraph.isEmpty()) {
                return false;
            }

            ValidationReport report = ShaclValidator.get().validate(shapes, dataGraph);

            return report.getEntries().size() == 0;

        } catch (Exception ex) {
            return false;
        }
    }

    public static void main(String[] args) {
//        System.out.println(isRepresentationValid("http://localhost/cn-test/1/yousouf", "./profiles/example-shape-graph-1.ttl"));
//        String data = "@base          <https://example.com/> .\n" +
//                "@prefix :      <https://example.com/ontology#> .\n" +
//                "@prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> .\n" +
//                "@prefix dcterms: <http://purl.org/dc/terms/> .\n" +
//                "@prefix foaf:  <http://xmlns.com/foaf/0.1/> .\n" +
//                "\n" +
//                "<yousouf>  a              foaf:Person ;\n" +
//                "        rdfs:label     \"Taghzouti\"@ar ;\n" +
//                "        foaf:givenName      \"Yousouf\"@en ;\n" +
//                "        :workAt \"EMSE\"@en.";
//        System.out.println(isRepresentationValid(data, "./profiles/example-shape-graph-1.ttl"));
    }

}
