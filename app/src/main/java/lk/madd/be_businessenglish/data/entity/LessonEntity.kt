package lk.madd.be_businessenglish.data.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "lessons",
    indices = [Index(value = ["courseId"])]
)
data class LessonEntity(
    @PrimaryKey(autoGenerate = true) val lessonId: Long = 0,
    val courseId: Long,
    val title: String,
    val durationSec: Int,
    val orderIndex: Int,
    val audioUrl: String? = null
)
