package es.in2.wallet.wca.service.impl


import es.in2.wallet.wca.exception.CredentialRequestDataNotFoundException
import es.in2.wallet.wca.model.repository.CredentialRequestDataRepository
import es.in2.wallet.wca.model.entity.CredentialRequestData
import es.in2.wallet.wca.service.CredentialRequestDataService
import jakarta.transaction.Transactional
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*

@Service
class CredentialRequestDataServiceImpl(
    private val credentialRequestDataRepository: CredentialRequestDataRepository
) : CredentialRequestDataService {

    private val log: Logger = LoggerFactory.getLogger(CredentialRequestDataServiceImpl::class.java)
    override fun saveCredentialRequestData(issuerName: String, issuerNonce: String, issuerAccessToken: String, userId: String) {
        try {
            val requestData = getCredentialRequestDataByIssuerName(issuerName, userId)
            // Update the existent data
            val appCredentialRequestDataUpdated = requestData.get().copy(
                issuerNonce = issuerNonce,
                issuerAccessToken = issuerAccessToken
            )
            credentialRequestDataRepository.save(appCredentialRequestDataUpdated)
            log.debug("Updated the nonce value and the access token value.")

        }catch (e: CredentialRequestDataNotFoundException){
            // Create new data
            val credentialRequestData = CredentialRequestData(
                id = UUID.randomUUID(),
                issuerName = issuerName,
                userId = userId,
                issuerNonce = issuerNonce,
                issuerAccessToken = issuerAccessToken
            )
            credentialRequestDataRepository.save(credentialRequestData)
            log.debug("AppCredentialRequestData created.")
        }
    }

    override fun getCredentialRequestDataByIssuerName(issuerName: String, userId: String):  Optional<CredentialRequestData>{
        val requestData = credentialRequestDataRepository.findAppCredentialRequestDataByIssuerNameAndUserId(issuerName, userId)
        if (requestData.isPresent) {
            return requestData
        }
        else{throw CredentialRequestDataNotFoundException("The $issuerName was not found")
        }
    }
    @Transactional
    override fun saveNewIssuerNonceByIssuerName(issuerName: String, freshNonce: String, userId: String) {
        credentialRequestDataRepository.updateIssuerNonceWithNewValueByIssuerNameAndUserId(issuerName,userId,freshNonce)
    }

}