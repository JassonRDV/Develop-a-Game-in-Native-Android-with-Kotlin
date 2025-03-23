package com.example.beatfranticallyidle.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.beatfranticallyidle.data.source.local.card.CardRepository
import com.example.beatfranticallyidle.data.source.local.card.model.Card
import com.example.beatfranticallyidle.data.source.local.card.model.CardType
import com.example.beatfranticallyidle.data.source.local.card.model.CardWithCardType
import com.example.beatfranticallyidle.data.source.local.card.model.listFireHero
import com.example.beatfranticallyidle.data.source.local.card.model.listGenericTypeHero
import com.example.beatfranticallyidle.data.source.local.reward.RewardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.math.BigDecimal
import javax.inject.Inject

@HiltViewModel
class CardViewModel @Inject constructor(
    private val cardRepository: CardRepository,
    private val rewardRepository: RewardRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CardUiState())
    val uiState: StateFlow<CardUiState> = _uiState.asStateFlow()

    init {
        loadCards()
    }

    private fun loadCards() {
        viewModelScope.launch {
            try {
                cardRepository.getAllCardsWithCardType()
                    .combine(rewardRepository.getRewards()) { cardsWithCardType, reward ->
                        CardUiState(
                            cardWithCardTypeEntity = cardsWithCardType,
                            listCardType = cardsWithCardType.map { it.cardType },
                            listCard = cardsWithCardType[0].cards,
                            gold = reward.gold,
                            purchaseCost = reward.purchaseCost
                        )
                    }.collect {
                    _uiState.update { currentState ->
                        currentState.copy(
                            cardWithCardTypeEntity = it.cardWithCardTypeEntity,
                            listCardType = it.listCardType,
                            listCard = it.listCard,
                            gold = it.gold,
                            purchaseCost = it.purchaseCost
                        )
                    }
                }
            } catch (e: Exception) {
                loadAllCards()
            }
        }
    }

    fun bottomBarTypeHero(currentList: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                listCard = currentState.cardWithCardTypeEntity?.get(currentList)?.cards
            )
        }
    }

    fun showingCardFullScreen(selectedHero: Card) {
        _uiState.update { currentState ->
            currentState.copy(
                currentCard = currentState.listCard?.get(selectedHero.id - 1),
                showCardFullScreen = true
            )
        }
    }

    fun hideCardFullScreen() {
        _uiState.update { currentState ->
            currentState.copy(
                currentCard = null,
                showCardFullScreen = false
            )
        }
    }

    fun buyCard() {
        val stage = _uiState.value
        if (stage.gold != null && stage.purchaseCost != null) {
            if (stage.gold >= stage.purchaseCost) {
                viewModelScope.launch {
                    _uiState.value.listCard?.random()?.let {
                        cardRepository.updateCard(
                            id = it.id,
                            discovered = true,
                            effectActivated = false,
                        )
                    }
                    updatePurchaseData()
                }
            }
        }
    }

    private fun updatePurchaseData() {
        viewModelScope.launch {
            rewardRepository.updateGoldBut()
            rewardRepository.updatePurchaseCost()
        }
    }

    private fun loadAllCards() {
        viewModelScope.launch {
            val cardTypes = cardRepository.getAllCardTypes().firstOrNull()
            if (cardTypes.isNullOrEmpty()) {
                cardRepository.insertAllCardTypes(listGenericTypeHero)
                cardRepository.insertAllCards(listFireHero)
                loadCards()
            }
        }
    }
}

data class CardUiState(
    val gold: BigDecimal? = null,
    val purchaseCost: BigDecimal? = null,
    val cardWithCardTypeEntity: List<CardWithCardType>? = emptyList(),
    val listCardType: List<CardType>? = emptyList(),
    val listCard: List<Card>? = emptyList(),
    val currentCard: Card? = null,
    val showCardFullScreen: Boolean = false
)