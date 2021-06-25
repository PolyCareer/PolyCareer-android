package com.example.polycareer.data.repository

import com.example.polycareer.domain.model.ProfessionInfo
import com.example.polycareer.exception.DatabaseException
import com.example.polycareer.exception.LostConnectionException
import com.example.polycareer.exception.WrongResponseException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProfessionsUseCase(
    private val localRepository: ProfessionsLocalRepository,
    private val remoteRepository: ProfessionsRemoteRepository,
) {
    private var professionsList: List<ProfessionInfo>? = null

    suspend fun getProfession(id: Long): ProfessionInfo {
        if (professionsList == null) {
            getAllProfessions()
        }
        return professionsList!!.first { it.id == id }
    }

    private suspend fun getAllProfessions() = withContext(Dispatchers.IO) {
        try {
            professionsList = localRepository.getAllProfessions()
        } catch (e: DatabaseException) {
            try {
                val remoteRepositoryData = remoteRepository.getAllProfessions()
                professionsList = remoteRepositoryData
                if (!localRepository.setAllProfessions(remoteRepositoryData))
                    throw DatabaseException()
            } catch (e: LostConnectionException) {
                throw LostConnectionException()
            } catch (e: WrongResponseException) {
                throw WrongResponseException(e.message)
            }
        }
    }
}