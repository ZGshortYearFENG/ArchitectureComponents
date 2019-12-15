package fengzj.sample.paging

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.paging.Config
import androidx.paging.toLiveData

class CheeseViewModel(app: Application) : AndroidViewModel(app) {
    val cheeseDao = CheeseDb.get(app).cheeseDao()

    // 返回的是LiveData<PagedList<Value>>
    //
    val allCheeses = cheeseDao.allCheeseByName()
        .toLiveData(Config(pageSize = 30, enablePlaceholders = true, maxSize = 200))

    fun insert(text: CharSequence) =
        ioThread { cheeseDao.insert(Cheese(id = 0, name = text.toString())) }

    fun remove(cheese: Cheese) = ioThread { cheeseDao.delete(cheese) }

}
