/*
 * oxAuth is available under the MIT License (2008). See http://opensource.org/licenses/MIT for full text.
 *
 * Copyright (c) 2014, Gluu
 */

package org.gluu.oxauth.model.register;

import org.apache.commons.lang.StringUtils;

/**
 * Listed all standard parameters involved in client registration request.
 *
 * @author Yuriy Zabrovarnyy
 * @author Javier Rojas Blum
 * @version December 4, 2018
 */

public enum RegisterRequestParam {

    /**
     * Array of redirect URIs values used in the Authorization Code and Implicit grant types. One of the these
     * registered redirect URI values must match the Scheme, Host, and Path segments of the redirect_uri parameter
     * value used in each Authorization Request.
     */
    REDIRECT_URIS("redirect_uris"),

    /**
     * UMA2 : Array of The Claims Redirect URIs to which the client wishes the authorization server to direct
     * the requesting party's user agent after completing its interaction.
     * The URI MUST be absolute, MAY contain an application/x-www-form-urlencoded-formatted query parameter component
     * that MUST be retained when adding additional parameters, and MUST NOT contain a fragment component.
     * The client SHOULD pre-register its claims_redirect_uri with the authorization server, and the authorization server
     * SHOULD require all clients to pre-register their claims redirection endpoints. Claims redirection URIs
     * are different from the redirection URIs defined in [RFC6749] in that they are intended for the exclusive use
     * of requesting parties and not resource owners. Therefore, authorization servers MUST NOT redirect requesting parties
     * to pre-registered redirection URIs defined in [RFC6749] unless such URIs are also pre-registered specifically as
     * claims redirection URIs. If the URI is pre-registered, this URI MUST exactly match one of the pre-registered claims
     * redirection URIs, with the matching performed as described in Section 6.2.1 of [RFC3986] (Simple String Comparison).
     */
    CLAIMS_REDIRECT_URIS("claims_redirect_uri"),

    /**
     * JSON array containing a list of the OAuth 2.0 response_type values that the Client is declaring that it will
     * restrict itself to using. If omitted, the default is that the Client will use only the code response type.
     */
    RESPONSE_TYPES("response_types"),

    /**
     * JSON array containing a list of the OAuth 2.0 grant types that the Client is declaring that it will restrict
     * itself to using.
     */
    GRANT_TYPES("grant_types"),

    /**
     * Kind of the application. The default if not specified is web. The defined values are native or web.
     * Web Clients using the OAuth implicit grant type must only register URLs using the https scheme as redirect_uris;
     * they may not use localhost as the hostname.
     * Native Clients must only register redirect_uris using custom URI schemes or URLs using the http: scheme with
     * localhost as the hostname.
     */
    APPLICATION_TYPE("application_type"),

    /**
     * Array of e-mail addresses of people responsible for this Client. This may be used by some providers to enable a
     * Web user interface to modify the Client information.
     */
    CONTACTS("contacts"),

    /**
     * Name of the Client to be presented to the user.
     */
    CLIENT_NAME("client_name"),

    /**
     * URL that references a logo for the Client application.
     */
    LOGO_URI("logo_uri"),

    /**
     * URL of the home page of the Client.
     */
    CLIENT_URI("client_uri"),

    /**
     * URL that the Relying Party Client provides to the End-User to read about the how the profile data will be used.
     */
    POLICY_URI("policy_uri"),

    /**
     * URL that the Relying Party Client provides to the End-User to read about the Relying Party's terms of service.
     */
    TOS_URI("tos_uri"),

    /**
     * URL for the Client's JSON Web Key Set (JWK) document containing key(s) that are used for signing requests to
     * the OP. The JWK Set may also contain the Client's encryption keys(s) that are used by the OP to encrypt the
     * responses to the Client.
     */
    JWKS_URI("jwks_uri"),

    /**
     * Client's JSON Web Key Set (JWK) document, passed by value. The semantics of the jwks parameter are the same as
     * the jwks_uri parameter, other than that the JWK Set is passed by value, rather than by reference.
     * This parameter is intended only to be used by Clients that, for some reason, are unable to use the jwks_uri
     * parameter, for instance, by native applications that might not have a location to host the contents of the JWK
     * Set. If a Client can use jwks_uri, it must not use jwks.
     * One significant downside of jwks is that it does not enable key rotation (which jwks_uri does, as described in
     * Section 10 of OpenID Connect Core 1.0). The jwks_uri and jwks parameters must not be used together.
     */
    JWKS("jwks"),

    /**
     * URL using the https scheme to be used in calculating Pseudonymous Identifiers by the OP.
     * The URL references a file with a single JSON array of redirect_uri values.
     */
    SECTOR_IDENTIFIER_URI("sector_identifier_uri"),

    /**
     * Subject type requested for the Client ID. Valid types include pairwise and public.
     */
    SUBJECT_TYPE("subject_type"),

    /**
     * Whether to return RPT as signed JWT
     */
    RPT_AS_JWT("rpt_as_jwt"),

    /**
     * Whether to return access token as signed JWT
     */
    ACCESS_TOKEN_AS_JWT("access_token_as_jwt"),

    /**
     * Algorithm used for signing of JWT
     */
    ACCESS_TOKEN_SIGNING_ALG("access_token_signing_alg"),

    /**
     * JWS alg algorithm (JWA)0 required for the issued ID Token.
     */
    ID_TOKEN_SIGNED_RESPONSE_ALG("id_token_signed_response_alg"),

    /**
     * JWE alg algorithm (JWA) required for encrypting the ID Token.
     */
    ID_TOKEN_ENCRYPTED_RESPONSE_ALG("id_token_encrypted_response_alg"),

    /**
     * JWE enc algorithm (JWA) required for symmetric encryption of the ID Token.
     */
    ID_TOKEN_ENCRYPTED_RESPONSE_ENC("id_token_encrypted_response_enc"),

    /**
     * JWS alg algorithm (JWA) required for UserInfo Responses.
     */
    USERINFO_SIGNED_RESPONSE_ALG("userinfo_signed_response_alg"),

    /**
     * JWE alg algorithm (JWA) required for encrypting UserInfo Responses.
     */
    USERINFO_ENCRYPTED_RESPONSE_ALG("userinfo_encrypted_response_alg"),

    /**
     * JWE enc algorithm (JWA) required for symmetric encryption of UserInfo Responses.
     */
    USERINFO_ENCRYPTED_RESPONSE_ENC("userinfo_encrypted_response_enc"),

    /**
     * JWS alg algorithm (JWA) that must be required by the Authorization Server.
     */
    REQUEST_OBJECT_SIGNING_ALG("request_object_signing_alg"),

    /**
     * JWS alg algorithm (JWA) that must be used for signing Request Objects sent to the OP.
     */
    REQUEST_OBJECT_ENCRYPTION_ALG("request_object_encryption_alg"),

    /**
     * JWE enc algorithm (JWA) the RP is declaring that it may use for encrypting Request Objects sent to the OP.
     */
    REQUEST_OBJECT_ENCRYPTION_ENC("request_object_encryption_enc"),

    /**
     * Requested authentication method for the Token Endpoint.
     */
    TOKEN_ENDPOINT_AUTH_METHOD("token_endpoint_auth_method"),

    /**
     * JWS alg algorithm (JWA) that MUST be used for signing the JWT used to authenticate the Client at the
     * Token Endpoint for the private_key_jwt and client_secret_jwt authentication methods.
     */
    TOKEN_ENDPOINT_AUTH_SIGNING_ALG("token_endpoint_auth_signing_alg"),

    /**
     * Default Maximum Authentication Age. Specifies that the End-User must be actively authenticated if the End-User
     * was authenticated longer ago than the specified number of seconds. The max_age request parameter overrides this
     * default value.
     */
    DEFAULT_MAX_AGE("default_max_age"),

    /**
     * Boolean value specifying whether the auth_time Claim in the ID Token is required. It is required when the value
     * is true. The auth_time Claim request in the Request Object overrides this setting.
     */
    REQUIRE_AUTH_TIME("require_auth_time"),

    /**
     * Default requested Authentication Context Class Reference values. Array of strings that specifies the default acr
     * values that the Authorization Server must use for processing requests from the Client.
     */
    DEFAULT_ACR_VALUES("default_acr_values"),

    /**
     * URI using the https scheme that the Authorization Server can call to initiate a login at the Client.
     */
    INITIATE_LOGIN_URI("initiate_login_uri"),

    /**
     * URL supplied by the RP to request that the user be redirected to this location after a logout has been performed,
     */
    POST_LOGOUT_REDIRECT_URIS("post_logout_redirect_uris"),

    /**
     * RP URL that will cause the RP to log itself out when rendered in an iframe by the OP.
     * A sid (session ID) query parameter MAY be included by the OP to enable the RP to validate the request and
     * to determine which of the potentially multiple sessions is to be logged out.
     */
    FRONT_CHANNEL_LOGOUT_URI("frontchannel_logout_uri"),

    /**
     * Boolean value specifying whether the RP requires that a sid (session ID) query parameter be included
     * to identify the RP session at the OP when the logout_uri is used. If omitted, the default value is false.
     */
    FRONT_CHANNEL_LOGOUT_SESSION_REQUIRED("frontchannel_logout_session_required"),

    /**
     * Array of request_uri values that are pre-registered by the Client for use at the Authorization Server.
     */
    REQUEST_URIS("request_uris"),

    /**
     * @deprecated This param will be removed in a future version because the correct is 'scope' not 'scopes', see (rfc7591).
     */
    SCOPES("scopes"),

    /**
     * String containing a space-separated list of claims that can be requested individually.
     */
    CLAIMS("claims"),

    /**
     * Optional string value specifying the JWT Confirmation Method member name (e.g. tbh) that the Relying Party expects when receiving Token Bound ID Tokens. The presence of this parameter indicates that the Relying Party supports Token Binding of ID Tokens. If omitted, the default is that the Relying Party does not support Token Binding of ID Tokens.
     */
    ID_TOKEN_TOKEN_BINDING_CNF("id_token_token_binding_cnf"),

    /**
     * string representation of the expected subject
     * distinguished name of the certificate, which the OAuth client will
     * use in mutual TLS authentication.
     */
    TLS_CLIENT_AUTH_SUBJECT_DN("tls_client_auth_subject_dn"),

    /**
     * boolean, whether to allow spontaneous scopes for client
     */
    ALLOW_SPONTANEOUS_SCOPES("allow_spontaneous_scopes"),

    /**
     * list of spontaneous scopes
     */
    SPONTANEOUS_SCOPES("spontaneous_scopes"),

    /**
     * boolean property which indicates whether to run introspection script and then include claims from result into access_token as JWT
     */
    RUN_INTROSPECTION_SCRIPT_BEFORE_ACCESS_TOKEN_CREATION_AS_JWT_AND_INCLUDE_CLAIMS("run_introspection_script_before_access_token_as_jwt_creation_and_include_claims"),

    /**
     * boolean property which indicates whether to keep client authorization after expiration
     */
    KEEP_CLIENT_AUTHORIZATION_AFTER_EXPIRATION("keep_client_authorization_after_expiration"),

    /**
     * String containing a space-separated list of scope values.
     */
    SCOPE("scope"),

    /**
     * Authorized JavaScript origins.
     */
    AUTHORIZED_ORIGINS("authorized_origins"),

    /**
     * Client-specific access token expiration. Set this value to null or zero to use the default value.
     */
    ACCESS_TOKEN_LIFETIME("access_token_lifetime"),

    /**
     * A unique identifier string (UUID) assigned by the client developer or software publisher used by
     * registration endpoints to identify the client software to be dynamically registered.
     */
    SOFTWARE_ID("software_id"),

    /**
     * A version identifier string for the client software identified by "software_id".
     * The value of the "software_version" should change on any update to the client software identified by the same
     * "software_id".
     */
    SOFTWARE_VERSION("software_version"),

    /**
     * A software statement containing client metadata values about the client software as claims.
     * This is a string value containing the entire signed JWT.
     */
    SOFTWARE_STATEMENT("software_statement");

    /**
     * Parameter name
     */
    private final String name;

    /**
     * Constructor
     *
     * @param name parameter name
     */
    private RegisterRequestParam(String name) {
        this.name = name;
    }

    /**
     * Gets parameter name.
     *
     * @return parameter name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns whether parameter is standard
     *
     * @param p_parameterName parameter name
     * @return whether parameter is standard
     */
    public static boolean isStandard(String p_parameterName) {
        if (StringUtils.isNotBlank(p_parameterName)) {
            for (RegisterRequestParam t : values()) {
                if (t.getName().equalsIgnoreCase(p_parameterName)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns whether custom parameter is valid.
     *
     * @param p_parameterName parameter name
     * @return whether custom parameter is valid
     */
    public static boolean isCustomParameterValid(String p_parameterName) {
        return StringUtils.isNotBlank(p_parameterName) && !isStandard(p_parameterName);
    }


    @Override
    public String toString() {
        return name;
    }
}