package fr.minesstetienne.ci.dcn.service;

import fr.minesstetienne.ci.dcn.dto.RepresentationDetail;
import fr.minesstetienne.ci.dcn.dto.ResourceDetail;
import fr.minesstetienne.ci.dcn.dto.ResponseSameAsDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author YoucTagh
 */
//@Service
public class SameAsSearchService {

    private final MediaTypeDCNService mediaTypeDCNService;

    public SameAsSearchService(RestTemplateBuilder restTemplateBuilder, MediaTypeDCNService mediaTypeDCNService) {
//        this.restTemplate = restTemplateBuilder
//                .setConnectTimeout(Duration.ofSeconds(2))
//                .setReadTimeout(Duration.ofSeconds(2))
//                .build();
        this.mediaTypeDCNService = mediaTypeDCNService;
    }

    public static ResponseSameAsDTO findSameResources(String resourceIRI) {
        RestTemplate restTemplate = new RestTemplate();

        String uri = UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host("sameas.org")
                .path("/")
                .queryParam("uri", resourceIRI)
                .build()
                .toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Stream.of(MediaType.APPLICATION_JSON).collect(Collectors.toList()));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<ResponseSameAsDTO[]> respEntity = restTemplate.exchange(uri, HttpMethod.GET, entity, ResponseSameAsDTO[].class);
            if (respEntity.getStatusCode().equals(HttpStatus.OK)) {
                ResponseSameAsDTO[] resp = respEntity.getBody();
                if (resp != null)
                    return resp[0];
            }
        } catch (RestClientException e) {
            System.err.println(e.getMessage());
            return null;
        }

        return null;
    }

    private ResourceDetail checkRepresentationAvailability(ResponseSameAsDTO responseSameAsDTO, List<MediaType> mediaTypeList) {
        ResourceDetail resourceDetail = new ResourceDetail()
                .setMainIri(responseSameAsDTO.getUri())
                .setNumRepresentations(responseSameAsDTO.getNumDuplicates());

        responseSameAsDTO.getDuplicates().forEach(representationIRI -> {
            System.out.println(representationIRI);
            try {
                ResponseEntity<String> isRepresentationAvailable = mediaTypeDCNService.checkRepresentationIfAvailable(representationIRI, mediaTypeList);
                RepresentationDetail representationDetail = new RepresentationDetail()
                        .setIri(representationIRI)
                        .setStatus(isRepresentationAvailable.getStatusCode())
                        .setContentType(isRepresentationAvailable.getHeaders().getContentType());
                resourceDetail.getRepresentationDetails().add(representationDetail);
            } catch (HttpClientErrorException ex) {
                resourceDetail.getRepresentationDetails().add(new RepresentationDetail()
                        .setIri(responseSameAsDTO.getUri())
                        .setStatus(ex.getStatusCode())
                        .setContentType(ex.getResponseHeaders().getContentType()));
            } catch (Exception ex) {
                resourceDetail.getRepresentationDetails().add(new RepresentationDetail()
                        .setIri(responseSameAsDTO.getUri())
                        .setStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                        .setContentType(MediaType.ALL));
            }
        });
        return resourceDetail;
    }

}
