package fr.minesstetienne.ci.dcn.service;

import fr.minesstetienne.ci.dcn.dto.ResponseSameAsDTO;
import org.springframework.http.*;
import org.springframework.http.client.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author YoucTagh
 */
public class SameAsSearchService {

    static RestTemplate restTemplate = customRestTemplate();


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

    public static boolean checkRepresentationAvailability(String iri, List<MediaType> mediaTypeList) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(mediaTypeList);
            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<String> isRepresentationAvailable = restTemplate.exchange(iri, HttpMethod.HEAD, entity, String.class);
            return isRepresentationAvailable.getStatusCode().equals(HttpStatus.OK)
                    && UtilService.isMediaTypeContainsInList(isRepresentationAvailable.getHeaders().getContentType(), mediaTypeList);
        } catch (Exception ex) {
            return false;
        }
    }

    public static ResponseEntity checkRepresentationValidity(String iri, List<MediaType> mediaTypeList) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(mediaTypeList);
            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<String> isRepresentationAvailable = restTemplate.exchange(iri, HttpMethod.GET, entity, String.class);

            if(isRepresentationAvailable.getStatusCode().equals(HttpStatus.OK)
                    && UtilService.isMediaTypeContainsInList(isRepresentationAvailable.getHeaders().getContentType(), mediaTypeList))
                return isRepresentationAvailable;

            return null;
        } catch (Exception ex) {
            return null;
        }
    }
    public static RestTemplate customRestTemplate() {
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setConnectionRequestTimeout(2000);
        httpRequestFactory.setConnectTimeout(2000);
        httpRequestFactory.setReadTimeout(2000);
        return new RestTemplate(httpRequestFactory);
    }


}
