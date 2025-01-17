package es.in2.wallet.api.util

class WalletUtils

const val SERVICE_MATRIX = "service-matrix.properties"
const val SIOP_AUDIENCE = "https://self-issued.me/v2"
const val USER_ROLE = "USER"
const val JWT = "JWT"
const val OPEN_ID_PREFIX = "openid://"
const val ISSUER_TOKEN_PROPERTY_NAME = "iss"
const val ISSUER_SUB = "sub"
const val UNIVERSAL_RESOLVER_URL = "https://dev.uniresolver.io/1.0/identifiers"
const val PRE_AUTH_CODE_GRANT_TYPE = "urn:ietf:params:oauth:grant-type:pre-authorized_code"
const val CONTENT_TYPE = "Content-Type"
const val CONTENT_TYPE_APPLICATION_JSON = "application/json"
const val CONTENT_TYPE_URL_ENCODED_FORM = "application/x-www-form-urlencoded"
const val HEADER_AUTHORIZATION = "Authorization"
const val BEARER_PREFIX = "Bearer "
const val ALL = "*"
const val API_SECURED_PATTERN = "/api/**"
const val STRING_FORMAT = "String"
const val JSON_FORMAT = "JSON"
// fixme: vc_jwt and jwt_vc are the same? (vc_jwt must be not used)
const val VC_JWT = "vc_jwt"
const val JWT_VC = "jwt_vc"
const val JWT_VP = "jwt_vp"
const val VC_LDP = "vc_ldp"
const val VC_JSON = "vc_json"
const val VP_JWT = "vp_jwt"
const val VP_LDP = "vp_ldp"