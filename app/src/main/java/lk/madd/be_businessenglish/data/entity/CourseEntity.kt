package lk.madd.be_businessenglish.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "courses")
data class CourseEntity(
    @PrimaryKey(autoGenerate = true) val courseId: Long = 0,
    val title: String,
    val level: String,
    val lessonsCount: Int,
    val rating: Double,
    val thumbnailUrl: String? = null,
    val description: String = ""
)
