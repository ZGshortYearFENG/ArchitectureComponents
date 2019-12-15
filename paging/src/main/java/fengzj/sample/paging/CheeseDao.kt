package fengzj.sample.paging

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface CheeseDao {

    // Each instance of PagedList loads an up-to-date snapshot of your app's data from its corresponding DataSource object.
    // PagedList的每个实例都从其对应的DataSource对象加载应用程序数据的最新快照

    @Query("SELECT * FROM Cheese ORDER BY name COLLATE NOCASE ASC")
    fun allCheeseByName(): DataSource.Factory<Int, Cheese>

    @Insert
    fun insert(cheeses: List<Cheese>)

    @Insert
    fun insert(cheese: Cheese)

    @Delete
    fun delete(cheese: Cheese)
}