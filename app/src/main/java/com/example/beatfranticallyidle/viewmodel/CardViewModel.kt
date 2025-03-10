package com.example.beatfranticallyidle.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.beatfranticallyidle.data.source.local.card.CardRepository
import com.example.beatfranticallyidle.data.source.local.card.model.Card
import com.example.beatfranticallyidle.data.source.local.card.model.CardType
import com.example.beatfranticallyidle.data.source.local.card.model.CardWithCardType
import com.example.beatfranticallyidle.data.source.local.card.model.listFireHero
import com.example.beatfranticallyidle.data.source.local.card.model.listGenericTypeHero
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardViewModel @Inject constructor(
    private val cardRepository: CardRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CardUiState())
    val uiState: StateFlow<CardUiState> = _uiState.asStateFlow()

    init {
        loadCards()
    }

    private fun loadCards() {
        viewModelScope.launch {
            cardRepository.getAllCardsWithCardType().collect { cardWithCardTypeList ->
                if (cardWithCardTypeList.isNotEmpty()) {
                    if (cardWithCardTypeList[0].cards.isNotEmpty())
                    _uiState.update { currentState ->
                        currentState.copy(
                            cardWithCardTypeEntity = cardWithCardTypeList,
                            listCard = cardWithCardTypeList[0].cards
                        )
                    }
                }
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

    // TODO preciso de outro banco de dados para fazer a compra das cartas
    fun buyCard() {

    }

    fun loadAllCards(
        listCardType: List<CardType> = listGenericTypeHero,
        listCard: List<Card> = listFireHero
    ) {
        viewModelScope.launch {
            val cardTypeList = cardRepository.getAllCardTypes().firstOrNull()
            val cardList = cardRepository.getAllCards().firstOrNull()
            if (cardTypeList.isNullOrEmpty()) {
                cardRepository.insertAllCardTypes(listCardType)
            } else if (cardList.isNullOrEmpty()) {
                cardRepository.insertAllCards(listCard)
            }
        }
    }
}

data class CardUiState(
    val cardWithCardTypeEntity: List<CardWithCardType>? = null,
    val listCardType: List<CardType>? = null,
    val listCard: List<Card>? = null,
    val currentCard: Card? = null,
    val showCardFullScreen: Boolean = false
)