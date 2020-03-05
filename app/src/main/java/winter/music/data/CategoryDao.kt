package winter.music.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CategoryDao{
    @Query("SELECT * FROM categories WHERE id = :categoryId")
    fun getCategory(categoryId: Long): LiveData<Category>

    @Insert
    suspend fun insert(category: Category): Long
}
