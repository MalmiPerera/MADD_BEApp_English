package lk.madd.be_businessenglish.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "progress")
data class ProgressEntity(
    @PrimaryKey val lessonId: Long,
    val progressPercent: Int,
    val lastPositionSec: Int,
    val completed: Boolean,
    val updatedAt: Long
)
