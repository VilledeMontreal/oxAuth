/*
 * oxAuth is available under the MIT License (2008). See http://opensource.org/licenses/MIT for full text.
 *
 * Copyright (c) 2014, Gluu
 */

package org.xdi.oxauth.client;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONObject;
import org.jboss.resteasy.client.ClientExecutor;
import org.jboss.resteasy.client.ClientRequest;
import org.xdi.oxauth.model.util.Util;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MediaType;

import static org.xdi.oxauth.model.configuration.ConfigurationResponseClaim.*;

/**
 * Encapsulates functionality to make OpenId Configuration request calls to an authorization server via REST Services.
 *
 * @author Javier Rojas Blum
 * @version 0.9 January 22, 2015
 */
public class OpenIdConfigurationClient extends BaseClient<OpenIdConfigurationRequest, OpenIdConfigurationResponse> {

    private static final Logger LOG = Logger.getLogger(OpenIdConfigurationClient.class);

    private static final String mediaType = MediaType.TEXT_PLAIN;

    /**
     * Constructs an OpenID Configuration Client by providing an url where the REST service is located.
     *
     * @param url The REST service location.
     */
    public OpenIdConfigurationClient(String url) {
        super(url);
    }

    @Override
    public OpenIdConfigurationRequest getRequest() {
        if (request instanceof OpenIdConfigurationRequest) {
            return (OpenIdConfigurationRequest) request;
        } else {
            return null;
        }
    }

    @Override
    public void setRequest(OpenIdConfigurationRequest request) {
        super.request = request;
    }

    @Override
    public OpenIdConfigurationResponse getResponse() {
        if (response instanceof OpenIdConfigurationResponse) {
            return (OpenIdConfigurationResponse) response;
        } else {
            return null;
        }
    }

    @Override
    public void setResponse(OpenIdConfigurationResponse response) {
        super.response = response;
    }

    @Override
    public String getHttpMethod() {
        return HttpMethod.GET;
    }

    public OpenIdConfigurationResponse execOpenIdConfiguration() {
        initClientRequest();

        return _execOpenIdConfiguration();
    }

    @Deprecated
    public OpenIdConfigurationResponse execOpenIdConfiguration(ClientExecutor executor) {
        this.clientRequest = new ClientRequest(getUrl(), executor);

        return _execOpenIdConfiguration();
    }

    /**
     * Executes the call to the REST Service requesting the OpenID Configuration and processes the response.
     *
     * @return The service response.
     */
    private OpenIdConfigurationResponse _execOpenIdConfiguration() {
        setRequest(new OpenIdConfigurationRequest());

        // Prepare request parameters
        clientRequest.accept(mediaType);
        clientRequest.setHttpMethod(getHttpMethod());

        // Call REST Service and handle response
        try {
            clientResponse = clientRequest.get(String.class);
            int status = clientResponse.getStatus();

            setResponse(new OpenIdConfigurationResponse(status));

            String entity = clientResponse.getEntity(String.class);
            getResponse().setEntity(entity);
            getResponse().setHeaders(clientResponse.getHeaders());
            if (StringUtils.isNotBlank(entity)) {
                JSONObject jsonObj = new JSONObject(entity);

                if (jsonObj.has(ISSUER)) {
                    getResponse().setIssuer(jsonObj.getString(ISSUER));
                }
                if (jsonObj.has(AUTHORIZATION_ENDPOINT)) {
                    getResponse().setAuthorizationEndpoint(jsonObj.getString(AUTHORIZATION_ENDPOINT));
                }
                if (jsonObj.has(TOKEN_ENDPOINT)) {
                    getResponse().setTokenEndpoint(jsonObj.getString(TOKEN_ENDPOINT));
                }
                if (jsonObj.has(USER_INFO_ENDPOINT)) {
                    getResponse().setUserInfoEndpoint(jsonObj.getString(USER_INFO_ENDPOINT));
                }
                if (jsonObj.has(CLIENT_INFO_ENDPOINT)) {
                    getResponse().setClientInfoEndpoint(jsonObj.getString(CLIENT_INFO_ENDPOINT));
                }
                if (jsonObj.has(CHECK_SESSION_IFRAME)) {
                    getResponse().setCheckSessionIFrame(jsonObj.getString(CHECK_SESSION_IFRAME));
                }
                if (jsonObj.has(END_SESSION_ENDPOINT)) {
                    getResponse().setEndSessionEndpoint(jsonObj.getString(END_SESSION_ENDPOINT));
                }
                if (jsonObj.has(END_SESSION_PAGE)) {
                    getResponse().setEndSessionPage(jsonObj.getString(END_SESSION_PAGE));
                }
                if (jsonObj.has(JWKS_URI)) {
                    getResponse().setJwksUri(jsonObj.getString(JWKS_URI));
                }
                if (jsonObj.has(REGISTRATION_ENDPOINT)) {
                    getResponse().setRegistrationEndpoint(jsonObj.getString(REGISTRATION_ENDPOINT));
                }
                if (jsonObj.has(VALIDATE_TOKEN_ENDPOINT)) {
                    getResponse().setValidateTokenEndpoint(jsonObj.getString(VALIDATE_TOKEN_ENDPOINT));
                }
                if (jsonObj.has(FEDERATION_METADATA_ENDPOINT)) {
                    getResponse().setFederationMetadataEndpoint(jsonObj.getString(FEDERATION_METADATA_ENDPOINT));
                }
                if (jsonObj.has(FEDERATION_ENDPOINT)) {
                    getResponse().setFederationEndpoint(jsonObj.getString(FEDERATION_ENDPOINT));
                }
                if (jsonObj.has(ID_GENERATION_ENDPOINT)) {
                    getResponse().setIdGenerationEndpoint(jsonObj.getString(ID_GENERATION_ENDPOINT));
                }
                if (jsonObj.has(INTROSPECTION_ENDPOINT)) {
                    getResponse().setIntrospectionEndpoint(jsonObj.getString(INTROSPECTION_ENDPOINT));
                }
                if (jsonObj.has(SCOPE_TO_CLAIMS_MAPPING)) {
                    getResponse().setScopeToClaimsMapping(OpenIdConfigurationResponse.parseScopeToClaimsMapping(jsonObj.getJSONArray(SCOPE_TO_CLAIMS_MAPPING)));
                }
                Util.addToListIfHas(getResponse().getScopesSupported(), jsonObj, SCOPES_SUPPORTED);
                Util.addToListIfHas(getResponse().getResponseTypesSupported(), jsonObj, RESPONSE_TYPES_SUPPORTED);
                Util.addToListIfHas(getResponse().getGrantTypesSupported(), jsonObj, GRANT_TYPES_SUPPORTED);
                Util.addToListIfHas(getResponse().getAcrValuesSupported(), jsonObj, ACR_VALUES_SUPPORTED);
                Util.addToListIfHas(getResponse().getSubjectTypesSupported(), jsonObj, SUBJECT_TYPES_SUPPORTED);
                Util.addToListIfHas(getResponse().getUserInfoSigningAlgValuesSupported(), jsonObj, USER_INFO_SIGNING_ALG_VALUES_SUPPORTED);
                Util.addToListIfHas(getResponse().getUserInfoEncryptionAlgValuesSupported(), jsonObj, USER_INFO_ENCRYPTION_ALG_VALUES_SUPPORTED);
                Util.addToListIfHas(getResponse().getUserInfoEncryptionEncValuesSupported(), jsonObj, USER_INFO_ENCRYPTION_ENC_VALUES_SUPPORTED);
                Util.addToListIfHas(getResponse().getIdTokenSigningAlgValuesSupported(), jsonObj, ID_TOKEN_SIGNING_ALG_VALUES_SUPPORTED);
                Util.addToListIfHas(getResponse().getIdTokenEncryptionAlgValuesSupported(), jsonObj, ID_TOKEN_ENCRYPTION_ALG_VALUES_SUPPORTED);
                Util.addToListIfHas(getResponse().getIdTokenEncryptionEncValuesSupported(), jsonObj, ID_TOKEN_ENCRYPTION_ENC_VALUES_SUPPORTED);
                Util.addToListIfHas(getResponse().getRequestObjectSigningAlgValuesSupported(), jsonObj, REQUEST_OBJECT_SIGNING_ALG_VALUES_SUPPORTED);
                Util.addToListIfHas(getResponse().getRequestObjectEncryptionAlgValuesSupported(), jsonObj, REQUEST_OBJECT_ENCRYPTION_ALG_VALUES_SUPPORTED);
                Util.addToListIfHas(getResponse().getRequestObjectEncryptionEncValuesSupported(), jsonObj, REQUEST_OBJECT_ENCRYPTION_ENC_VALUES_SUPPORTED);
                Util.addToListIfHas(getResponse().getTokenEndpointAuthMethodsSupported(), jsonObj, TOKEN_ENDPOINT_AUTH_METHODS_SUPPORTED);
                Util.addToListIfHas(getResponse().getTokenEndpointAuthSigningAlgValuesSupported(), jsonObj, TOKEN_ENDPOINT_AUTH_SIGNING_ALG_VALUES_SUPPORTED);
                Util.addToListIfHas(getResponse().getDisplayValuesSupported(), jsonObj, DISPLAY_VALUES_SUPPORTED);
                Util.addToListIfHas(getResponse().getClaimTypesSupported(), jsonObj, CLAIM_TYPES_SUPPORTED);
                Util.addToListIfHas(getResponse().getClaimsSupported(), jsonObj, CLAIMS_SUPPORTED);
                if (jsonObj.has(SERVICE_DOCUMENTATION)) {
                    getResponse().setServiceDocumentation(jsonObj.getString(SERVICE_DOCUMENTATION));
                }
                Util.addToListIfHas(getResponse().getClaimsLocalesSupported(), jsonObj, CLAIMS_LOCALES_SUPPORTED);
                Util.addToListIfHas(getResponse().getUiLocalesSupported(), jsonObj, UI_LOCALES_SUPPORTED);
                if (jsonObj.has(CLAIMS_PARAMETER_SUPPORTED)) {
                    getResponse().setClaimsParameterSupported(jsonObj.getBoolean(CLAIMS_PARAMETER_SUPPORTED));
                }
                if (jsonObj.has(REQUEST_PARAMETER_SUPPORTED)) {
                    getResponse().setRequestParameterSupported(jsonObj.getBoolean(REQUEST_PARAMETER_SUPPORTED));
                }
                if (jsonObj.has(REQUEST_URI_PARAMETER_SUPPORTED)) {
                    getResponse().setRequestUriParameterSupported(jsonObj.getBoolean(REQUEST_URI_PARAMETER_SUPPORTED));
                }
                if (jsonObj.has(REQUIRE_REQUEST_URI_REGISTRATION)) {
                    getResponse().setRequireRequestUriRegistration(jsonObj.getBoolean(REQUIRE_REQUEST_URI_REGISTRATION));
                }
                if (jsonObj.has(OP_POLICY_URI)) {
                    getResponse().setOpPolicyUri(jsonObj.getString(OP_POLICY_URI));
                }
                if (jsonObj.has(OP_TOS_URI)) {
                    getResponse().setOpTosUri(jsonObj.getString(OP_TOS_URI));
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        } finally {
            closeConnection();
        }

        return getResponse();
    }
}