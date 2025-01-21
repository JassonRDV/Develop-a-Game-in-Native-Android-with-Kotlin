package com.example.beatfranticallyidle.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.beatfranticallyidle.R
import com.example.beatfranticallyidle.data.source.DefaultMonsterRepository
import com.example.beatfranticallyidle.data.source.local.OldMonsterData.RewardType
import com.example.beatfranticallyidle.data.source.local.card.HeroInfo
import com.example.beatfranticallyidle.data.source.local.card.listAllHeroes
import com.example.beatfranticallyidle.data.source.local.monster.MonsterEntity
import com.example.beatfranticallyidle.data.source.local.monster.listMonsterEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

val defaultMonsterEntity = listOf(
    MonsterEntity(
        imageResId = R.drawable.monster_placeholder,
        iconResId = R.drawable.monster_icone_placeholder,
        arenaResId = R.drawable.background_placeholder,
        name = "Monster",
        maxLife = 1f,
        currentLife = 1f,
        rewardType = RewardType.NULL,
        rewardValue = 1,
        deathCount = 0
    )
)

data class IdleStage(
    // Banco de dados
    val loadingDatabase: Boolean = true,

    // Informações do monstro atual
    val currentMonsterIndex: Int = 0,
    val currentMonster: MonsterEntity = defaultMonsterEntity[0],
    val lastMonsterIndex: Int = 0,

    // Recompensas e mortes
    val tookDamage: Boolean = false,
    val monsterDead: Boolean = false,
    var allReward: Int = 0,
    val allDeath: Int = 0,

    // Lista de heróis e herói atual
    val allListHero: List<List<HeroInfo.Hero>> = listAllHeroes,
    val currentListHero: List<HeroInfo.Hero> = allListHero[0],
    val currentHero: HeroInfo.Hero = currentListHero[0],

    // Estado do herói atual
    val showHeroDetails: Boolean = false,
    var purchaseCost: Float = 10f,
    val increasedPurchaseCost: Float = 1.25f
)

@HiltViewModel
class IdleViewModel @Inject constructor(
    private val defaultMonsterRepository: DefaultMonsterRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(IdleStage())
    val uiState: StateFlow<IdleStage> = _uiState.asStateFlow()

    private val monsterUiState: StateFlow<List<MonsterEntity>> =
        defaultMonsterRepository.getAllMonsters()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = defaultMonsterEntity
            )

    init {
        updateCurrentMonster()
    }

    private fun updateCurrentMonster() {
        viewModelScope.launch {
            monsterUiState.collectLatest { monster ->
                _uiState.update { currentState ->
                    currentState.copy(
                        currentMonster = monster.getOrNull(currentState.currentMonsterIndex)
                            ?: currentState.currentMonster,
                        lastMonsterIndex = monster.lastIndex,
                        allReward = monster.sumOf { it.deathCount * it.rewardValue },
                        allDeath = monster.sumOf { it.deathCount },
                        loadingDatabase = false
                    )
                }
            }
        }
    }

    // I will only use it to start the database
    fun insertAllMonsters(allMonsters: List<MonsterEntity> = listMonsterEntity) {
        viewModelScope.launch {
            defaultMonsterRepository.insertALL(allMonsters)
        }
    }

    fun previousMonster() {
        viewModelScope.launch {
            monsterUiState.collectLatest { monster ->
                _uiState.update { currentState ->
                    val monsterIndex = (currentState.currentMonsterIndex - 1)
                        .coerceAtLeast(0)
                    currentState.copy(
                        currentMonsterIndex = monsterIndex,
                        currentMonster = monster.getOrNull(monsterIndex)
                            ?: currentState.currentMonster
                    )
                }
            }
        }
    }

    fun nextMonster() {
        if (_uiState.value.currentMonsterIndex + 1 < monsterUiState.value.size) {
            viewModelScope.launch {
                monsterUiState.collectLatest { monster ->
                    _uiState.update { currentState ->
                        val monsterIndex = (currentState.currentMonsterIndex + 1)
                        currentState.copy(
                            currentMonsterIndex = monsterIndex,
                            currentMonster = monster.getOrNull(monsterIndex)
                                ?: currentState.currentMonster
                        )
                    }
                }
            }
        }
    }

    fun monsterTookDamage() {
        if (_uiState.value.currentMonster.currentLife > 0) {
            viewModelScope.launch {
                _uiState.update { currentState ->
                    currentState.currentMonster.currentLife -= 1f
                    currentState.copy(
                        tookDamage = true,
                    )
                }
                delay(50)
                _uiState.update { currentState ->
                    currentState.copy(
                        tookDamage = false
                    )
                }
            }
            monsterDied()
        }
    }

    private fun monsterDied() {
        if (_uiState.value.currentMonster.currentLife == 0f) {
            viewModelScope.launch {
                monsterUiState.collectLatest { monster ->
                    _uiState.update { currentState ->
                        currentState.currentMonster.deathCount += 1
                        currentState.copy(
                            monsterDead = true,
                        )
                    }
                    delay(500)
                    _uiState.update { currentState ->
                        currentState.currentMonster.currentLife =
                            currentState.currentMonster.maxLife
                        currentState.copy(
                            monsterDead = false,
                            allReward = monster.sumOf { it.deathCount * it.rewardValue },
                            allDeath = monster.sumOf { it.deathCount },
                        )
                    }
                    defaultMonsterRepository.updateMonster(
                        _uiState.value.currentMonster.name,
                        _uiState.value.currentMonster.deathCount
                    )
                }
            }
        }
    }

    fun bottomBarTypeHero(currentList: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                currentListHero = currentState.allListHero[currentList]
            )
        }
    }

    fun showingCardFullScreen(selectedHero: HeroInfo.Hero) {
        _uiState.update { currentState ->
            currentState.copy(
                showHeroDetails = true,
                currentHero = selectedHero
            )
        }
    }

    fun hideCardFullScreen() {
        _uiState.update { currentState ->
            currentState.copy(
                showHeroDetails = false,
            )
        }
    }

    fun buyCard() {
//        if (_uiState.value.allListHero[0] == _uiState.value.currentListHero
//            && _uiState.value.totalReward >= _uiState.value.purchaseCost
//        ) {
//            val randomIndex = (0..5).random()
//            _uiState.update { currentState ->
//                currentState.currentListHero[randomIndex].discovered = true
//                currentState.currentListHero[randomIndex].numberCardCount += 1
//                currentState.totalReward -= currentState.purchaseCost
//                currentState.purchaseCost *= currentState.increasedPurchaseCost
//                currentState.copy(
//                    currentHero = currentState.currentListHero[randomIndex]
//                )
//            }
//        }
//        if (
//            _uiState.value.currentListHero[0].discovered &&
//            !_uiState.value.currentListHero[0].effectActivated
//        ) {
//            _uiState.update { currentState ->
//                currentState.currentListHero[0].effectActivated = true
//                currentState.copy()
//            }
//            spiritEffect()
//        }
    }

    private fun spiritEffect() {
        viewModelScope.launch {
            while (uiState.value.allListHero[0][0].discovered) {
                monsterTookDamage()
                delay(1000)
            }
        }
    }
}