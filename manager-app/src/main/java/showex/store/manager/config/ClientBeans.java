package showex.store.manager.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.web.client.RestClient;
import showex.store.manager.client.RestClientProductsRestClient;
import showex.store.manager.security.OAuthClientHttpRequestInterceptor;

@Configuration
public class ClientBeans {

    @Bean
    public RestClientProductsRestClient productsRestClient(
            @Value("${book-store.services.catalogue.uri:http://localhost:8080}") String catalogueBaseUri,
            ClientRegistrationRepository clientRegistrationRepository,
            OAuth2AuthorizedClientRepository authorizedClientRepository,
            @Value("${book-store.services.catalogue.registration-id:keycloak}") String registrationId) {
        return new RestClientProductsRestClient(RestClient.builder()
                .baseUrl(catalogueBaseUri)
                .requestInterceptor(
                        new OAuthClientHttpRequestInterceptor(
                                new DefaultOAuth2AuthorizedClientManager(clientRegistrationRepository, authorizedClientRepository), registrationId
                        ))
                .build());
    }
}
