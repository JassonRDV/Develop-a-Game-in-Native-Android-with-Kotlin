package com.example.beatfranticallyidle.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.beatfranticallyidle.data.source.local.card.CardRepository
import com.example.beatfranticallyidle.data.source.local.card.CardWithCardType
import com.example.beatfranticallyidle.data.source.local.card.model.Card
import com.example.beatfranticallyidle.data.source.local.card.model.CardType
import com.example.beatfranticallyidle.data.source.local.card.model.genericHero
import com.example.beatfranticallyidle.data.source.local.card.model.listFireHero
import com.example.beatfranticallyidle.data.source.local.card.model.listTypeHero
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
            cardRepository.getAllCardsWithCardType().collect { cardWithCardType ->
                _uiState.update { currentState ->
                    currentState.copy(
                        cardWithCardType = cardWithCardType
                    )
                }

            }

        }
    }

    fun bottomBarTypeHero(currentList: Int) {

    }

    fun showingCardFullScreen(selectedHero: Card) {

    }

    fun hideCardFullScreen() {

    }

    fun buyCard() {

    }

    fun loadAllCards(
        listCardType: List<CardType> = listTypeHero,
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
    val cardWithCardType: List<CardWithCardType> = emptyList(),
    val listCardType: List<CardType> = emptyList(),
    val listCard: List<Card> = emptyList(),
    val currentCard: Card = genericHero
)