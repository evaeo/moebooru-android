package im.mash.moebooru.common.data.local.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "posts", indices = [(Index(value = ["site", "id"], unique = true))])
data class Post(
        @PrimaryKey(autoGenerate = true)
        val uid: Int?,
        var site: String?,
        val id: Int,
        val tags: String?,
        val created_at: Int,
        val creator_id: Int,
        val author: String,
        val change: Int,
        val source: String?,
        val score: Int,
        val md5: String,
        val file_size: Int,
        val file_url: String,
        val is_shown_in_index: Boolean,
        val preview_url: String,
        val preview_width: Int,
        val preview_height: Int,
        val actual_preview_width: Int,
        val actual_preview_height: Int,
        val sample_url: String,
        val sample_width: Int,
        val sample_height: Int,
        val sample_file_size: Int,
        val jpeg_url: String,
        val jpeg_width: Int,
        val jpeg_height: Int,
        val jpeg_file_size: Int,
        val rating: String,
        val has_children: Boolean,
        val parent_id: Int?,
        val status: String,
        val width: Int,
        val height: Int,
        val is_held: Boolean
)