package es.in2.wallet.wca.model.repository

import es.in2.wallet.wca.model.entity.Issuer
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface IssuerRepository : CrudRepository<Issuer, UUID> {
    fun findAppIssuerDataByName(issuerName: String): Optional<Issuer>
}