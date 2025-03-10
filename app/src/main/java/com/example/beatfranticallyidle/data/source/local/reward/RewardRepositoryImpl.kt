package com.example.beatfranticallyidle.data.source.local.reward

import com.example.beatfranticallyidle.data.source.local.reward.model.Reward
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RewardRepositoryImpl @Inject constructor(
    private val rewardDao: RewardDao
) : RewardRepository {

    override suspend fun insertReward(reward: Reward) =
        rewardDao.insertReward(reward.toRewardEntity())

    override suspend fun updateReward(reward: Reward) =
        rewardDao.updateReward(reward.toRewardEntity())

    override fun getRewards(): Flow<Reward> =
        rewardDao.getRewards().map { it.toReward() }
}